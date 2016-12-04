package com.maiboroda.swagger

import io.swagger.models.auth.In
import io.swagger.models.auth.OAuth2Definition
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

// TODO change asserts from equalTo(type) to equalTo(object)
class SecurityKtTest {

    @Test
    fun securityDefinitionApiKey() {
        val api = swagger {
            security {
                apiKey("api_key", "Header Name", "ApiKey Description", In.QUERY)
            }
        }

        assertThat(api.securityDefinitions?.get("api_key")?.type, equalTo("apiKey"))
    }

    @Test
    fun securityDefinitionAsValue() {
        val secDef = Security.apiKey("Header Name", "ApiKey Description", In.HEADER)

        val api = swagger {
            security {
                apiKey("api_key", secDef)
            }
        }

        assertThat(api.securityDefinitions?.get("api_key")?.type, equalTo(secDef.type))
    }

    @Test
    fun basicDefinition() {
        val api = swagger {
            security {
                basic("BasicSecurity", "Basic security description")
            }
        }

        assertThat(api.securityDefinitions.get("BasicSecurity")?.type, equalTo(Security.basic().type))
    }

    @Test
    fun basicDefinitionAsValue() {
        val definition = Security.basic()
        val api = swagger {
            security {
                basic("BasicSecurity", definition)
            }
        }

        assertThat(api.securityDefinitions.get("BasicSecurity")?.type, equalTo(definition.type))
    }

    @Test
    fun oauth2DefinitionAccessCode() {
        val api = swagger {
            security {
                oauth2("OAuth2Security", OAuth2Flows.ACCESS_CODE, "Description", "https://oauth.simple.api/authorization", "https://oauth.simple.api/token")
            }
        }

        assertThat((api.securityDefinitions?.get("OAuth2Security") as? OAuth2Definition)?.authorizationUrl, equalTo("https://oauth.simple.api/authorization"))
    }

    @Test
    fun oauth2DefinitionExtension() {
        val api = swagger {
            security {
                oauth2 {
                    name = "OAuth2Security"
                    flow = OAuth2Flows.ACCESS_CODE
                    description = "Description"
                    authorizationUrl = "https://oauth.simple.api/authorization"
                    tokenUrl = "https://oauth.simple.api/token"
                    scopes = mapOf("user:read" to "User read access", "user:write" to "User write access")
                }
            }
        }

        assertThat((api.securityDefinitions?.get("OAuth2Security") as? OAuth2Definition)?.authorizationUrl, equalTo("https://oauth.simple.api/authorization"))
    }
}