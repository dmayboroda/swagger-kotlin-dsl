package com.maiboroda.swagger

import io.swagger.models.Contact
import io.swagger.models.License
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class InfoKtTest {

    @Test
    fun contact() {
        val spec = swagger {
            info {

                title = "Swagger Sample App"
                description = "This is a sample server Petstore server."
                termsOfService = "http://swagger.io/terms/"
                version = "1.0.1"
                contact {
                    name = "API Support"
                    url = "http://www.swagger.io/support"
                    email = "support@swagger.io"
                }
            }
        }

        assertThat(spec.info.contact, equalTo(expectedContact()))
    }

    private fun expectedContact() = Contact()
            .name("API Support")
            .url("http://www.swagger.io/support")
            .email("support@swagger.io")

    @Test
    fun license() {
        val spec = swagger {
            info {
                license {
                    name = "Apache 2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0"
                }
            }
        }

        assertThat(spec.info.license, equalTo(expectedLicense()))
    }

    private fun expectedLicense() = License()
            .name("Apache 2.0")
            .url("https://www.apache.org/licenses/LICENSE-2.0")

}