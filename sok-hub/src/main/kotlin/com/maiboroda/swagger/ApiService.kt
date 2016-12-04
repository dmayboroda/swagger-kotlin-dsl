package com.maiboroda.swagger

import com.maiboroda.swagger.Api
import com.maiboroda.swagger.Version
import io.swagger.models.Swagger
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service

/**
 * Service that operates with Api instances available in classpath
 */
class ApiService : ApplicationContextAware {
    private var context: ApplicationContext? = null

    val apis: Collection<Api>
        get() = context!!.getBeansOfType(Api::class.java).values

    fun getApi(api: Api, version: Version): Swagger {
        return api.versions[version] as Swagger
    }

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.context = applicationContext
    }
}