package com.maiboroda.swagger.autoconfigure

import com.maiboroda.swagger.ApiService
import com.maiboroda.swagger.ApiController
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
@AutoConfigureAfter(*arrayOf(WebMvcAutoConfiguration::class))
open class ApiWebConfig : WebMvcConfigurerAdapter(), BeanDefinitionRegistryPostProcessor {

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory?) {}

    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry?) {
        val definition = RootBeanDefinition(ApiController::class.java, Autowire.BY_TYPE.value(), true)
        registry?.registerBeanDefinition("apiController", definition)
    }

    @Bean
    open fun getApiService(): ApiService {
        return ApiService()
    }
}