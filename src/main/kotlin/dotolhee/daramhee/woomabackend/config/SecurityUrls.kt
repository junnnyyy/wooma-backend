package dotolhee.daramhee.woomabackend.config

import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.servlet.HandlerMapping


@Component
class SecurityUrls {
    fun corsUrls() = mutableListOf(
        "http://localhost",
        "http://localhost:8080",
        "http://localhost:9090"
    )

    val NO_AUTH_URLS = mutableListOf(
        "/",
        "/swagger-ui/**",
        "/swagger/**",
        "/api-docs/**",
        "/register",
        "/sign_in",
        "/**",
    )
    fun noAuthRequestMatcher():RequestMatcher = RequestMatcher { request ->
        val antPathMatcher = AntPathMatcher()
        NO_AUTH_URLS.any { pattern ->
            val requestUri = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) as? String
                ?: request.servletPath
            antPathMatcher.match(pattern, requestUri)
        }
    }
}
