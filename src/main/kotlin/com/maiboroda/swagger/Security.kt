package com.maiboroda.swagger

import io.swagger.models.Swagger
import io.swagger.models.auth.ApiKeyAuthDefinition
import io.swagger.models.auth.BasicAuthDefinition
import io.swagger.models.auth.In
import io.swagger.models.auth.OAuth2Definition

class Security(val swagger:Swagger) {

    fun apiKey(key:String, name: String, description: String, `in`: In): ApiKeyAuthDefinition {
        val definition = Definitions.apiKey(name, description, `in`)
        this.swagger.securityDefinition(key, definition)
        return definition
    }

    fun apiKey(key:String, definition:ApiKeyAuthDefinition) {
        this.swagger.securityDefinition(key, definition)
    }

    fun basic(key:String, description: String?=""):BasicAuthDefinition {
        val definition = Definitions.basic(description)
        this.swagger.securityDefinition(key, definition)
        return definition
    }

    fun basic(key:String, definition: BasicAuthDefinition) {
        this.swagger.securityDefinition(key, definition)
    }

    fun oauth2(key:String, flow:OAuth2Flow, description: String="", authUrl:String, tokenUrl:String):OAuth2Definition {
        val definition = OAuth2Definition()
        when(flow.getFlow()) {
            "implicit" -> definition.implicit(authUrl)
            "password" -> definition.password(tokenUrl)
            "application" -> definition.application(tokenUrl)
            "accessCode" -> definition.accessCode(authUrl, tokenUrl)
        }
        definition.description = description
        this.swagger.securityDefinition(key, definition)
        return definition
    }

    companion object Definitions {
        fun apiKey(name: String, description: String, `in`: In): ApiKeyAuthDefinition {
            val definition = ApiKeyAuthDefinition()
            definition.`in` = `in`
            definition.name = name
            definition.description = description
            return definition
        }

        fun basic(description: String? = ""): BasicAuthDefinition {
            val definition = BasicAuthDefinition()
            definition.description = description
            return definition
        }
/*
        fun oauth2(description:String, flow:OAuth2Flow, authUrl:String, tokenUrl:String, scopes:Map<String,String>) : OAuth2Definition {
            val definition = OAuth2Definition()
            definition.authorizationUrl = authUrl
            definition.flow = flow.getFlow()
            definition.scopes = scopes
            definition.tokenUrl = tokenUrl
            definition.description = description
            return definition
        }*/
    }
}

class OAuth2DefinitionKt: OAuth2Definition() {
    lateinit var flow:OAuth2Flow
    lateinit var name:String
}

fun Security.oauth2(init:OAuth2DefinitionKt.()->Unit): OAuth2DefinitionKt {
    val definition = OAuth2DefinitionKt()
    definition.init()
    this.swagger.securityDefinition(definition.name, definition)
    return definition
}

interface OAuth2Flow {
    fun getFlow():String
}

enum class OAuth2Flows(val key:String):OAuth2Flow {
    IMPLICIT("implicit"), PASSWORD("password"), APPLICATION("application"), ACCESS_CODE("accessCode");

    override fun getFlow():String {
        return this.key
    }
}

fun Swagger.security(init:Security.() -> Unit):Security {
    val security = Security(this)
    security.init()
    return security
}