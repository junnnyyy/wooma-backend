package dotolhee.daramhee.woomabackend.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource


@Configuration
class LocaleConfig {
    
    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("messages/messages")
        messageSource.setFallbackToSystemLocale(false)
        messageSource.setDefaultEncoding("UTF-8")
        
        return messageSource
    }
}