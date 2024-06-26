package dotolhee.daramhee.woomabackend.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfig(
    @Value("\${kakao.oauth-api-host}")
    val kakaoOAuthApiHost: String
) {

    private val logger: Logger = LoggerFactory.getLogger(WebClientConfig::class.java)
    @Bean
    fun kakaoOAuthClient(): WebClient {
        return webClientBuilder(10000, kakaoOAuthApiHost)
    }

    private fun webClientBuilder(timeout: Long?, url: String): WebClient {

        val httpClient = if (timeout != null) {
            HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout.toInt())
                .responseTimeout(Duration.ofMillis(timeout))
                .doOnConnected { conn ->
                    conn.addHandlerLast(ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
                        .addHandlerLast(WriteTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
                }
        } else {
            HttpClient.create()
        }

        return WebClient.builder()
            .baseUrl(url)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .filter(logRequest())
            .filter(logResponse())
            .build()
    }

    // 요청로그 남기는 함수
    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url())
            logger.info("--- Http Headers: {}", clientRequest.headers())
            Mono.just(clientRequest)
        }
    }

    // 응답로그 남기는 함수
    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
            logger.info("Response CODE: {}", clientResponse.statusCode())
            logger.info("--- Http Headers: {}", clientResponse.headers().asHttpHeaders())
            Mono.just(clientResponse)
        }
    }
}