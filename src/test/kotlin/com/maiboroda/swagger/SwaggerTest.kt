package com.maiboroda.swagger

import io.swagger.models.*
import io.swagger.models.properties.UUIDProperty
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Assert.assertThat
import org.junit.Test

class SwaggerTest {

    @Test
    fun should_create_empty_spec() {
        val spec = swagger { }

        assertThat(spec, notNullValue())
        assertThat(spec.swagger, equalTo("2.0"))
    }

    @Test
    fun should_create_spec_with_base_path_and_host() {
        val spec = swagger {
            host = "https://maiboroda.com"
            basePath = "/v1/api/"
        }
        assertThat(spec.host, equalTo("https://maiboroda.com"))
        assertThat(spec.basePath, equalTo("/v1/api/"))
    }

    @Test
    fun should_create_spec_with_info_contact() {
        val spec = swagger {
            host = "maiboroda.com"
            info {
                version = "1.0"
                title = "Test API Specification"
                description = "This is a small test API specification"
                termsOfService = "Terms"
                contact {
                    email = "mayboroda.de@yandex.ru"
                    name = "Dmitiriy Mayboroda"
                    url = "http://maiboroda.com"
                }
            }
        }

        assertThat(spec.info.contact, equalTo(Contact().email("mayboroda.de@yandex.ru").name("Dmitiriy Mayboroda").url("http://maiboroda.com")))
    }

    @Test
    fun should_create_spec_with_info_license() {
        val spec = swagger {
            info {
                license {
                    name = "Apache 2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0"
                }
            }
        }

        assertThat(spec.info.license, equalTo(License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
    }

    @Test
    fun should_create_spec_with_tags() {
        val spec = swagger {

            tags {
                tag {
                    name = "api"
                    description = "API of the project"
                }
                tag {
                    name = "v1"
                    description = "First version of the API"
                }
            }
        }

        assertThat(spec.tags, hasSize(2))
        assertThat(spec.tags[0], equalTo(Tag().name("api").description("API of the project")))
        assertThat(spec.tags[1], equalTo(Tag().name("v1").description("First version of the API")))
    }

    @Test
    fun should_create_spec_with_paths_post() {
        val spec = swagger {
            paths {
                post("/token/{token}") {
                    summary = "Create private customer"
                    description = "You can create private customer data only after customerId initialization"
                    tags = listOf("token", "bearer")
                    path("token") {
                        pattern = "xxxx-xxxx-xxxx"
                    }
                    //header("X-Header") {}
                    //form()
                    //cookie()
                    //query()
                    body(User::class.java) {
                    }
                }
            }
        }

        assertThat(spec.getPath("/token/{token}").post.summary, equalTo("Create private customer"))
        assertThat(spec.getPath("/token/{token}").post.parameters[0].name, equalTo("token"))
        assertThat(spec.getPath("/token/{token}").post.parameters[0].required, equalTo(true))
        assertThat(spec.getPath("/token/{token}").post.parameters[0].pattern, equalTo("xxxx-xxxx-xxxx"))
    }

    data class User(val username:String, val password:String) {}
}