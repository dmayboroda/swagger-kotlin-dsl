package com.maiboroda.swagger

import io.swagger.models.Tag
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Assert.assertThat
import org.junit.Test

class SwaggerKtTest {

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
                path("/users") {
                    post {
                        summary = "Create user"
                        description = "Description of creation"
                        tags = listOf("users", "management")
                        parameters {
                            path("token") {
                                pattern = "xxxx-xxxx-xxxx"
                            }
                            body(User::class.java) {}
                        }

                        responses {
                            ok("Response description"){}
                        }
                    }

                    get {
                        summary = "List of users"
                        description = "Returns a list of users sorted by created date"
                        tags = listOf("users")

                        responses {
                            ok("Successful result", User::class.java){}
                        }
                    }
                }
            }
        }

        assertThat(spec.getPath("/users").post.summary, equalTo("Create user"))
        assertThat(spec.getPath("/users").post.parameters[0].name, equalTo("token"))
        assertThat(spec.getPath("/users").post.parameters[0].required, equalTo(true))
        assertThat(spec.getPath("/users").post.parameters[0].pattern, equalTo("xxxx-xxxx-xxxx"))

        assertThat(spec.getPath("/users").post.responses["200"]?.description, equalTo("Response description"))
    }

    data class User(val username:String, val password:String) {}

    @Test
    fun should_create_yml() {
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
                license {
                    name = "Apache 2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0"
                }
            }
            paths {
                path("/token/{token}") {
                    post {
                        summary = "Create private customer"
                        description = "You can create private customer data only after customerId initialization"
                        tags = listOf("token", "bearer")
                        parameters {
                            path("token") {
                                pattern = "xxxx-xxxx-xxxx"
                            }
                            header("X-Header", String::class.java) {}
                            //form()
                            //cookie()
                            //query()
                            body(User::class.java) {
                            }
                        }
                        responses {
                            ok("Response description", User::class.java) {
                            }
                        }
                    }
                }
                path("/user") {
                    post {
                        parameters {
                            body(User::class.java) {}
                        }
                        responses {
                            ok("Successful response") {}
                        }
                    }
                    get {
                        summary = "List of users"
                        description = "Returns a list of users sorted by created date"
                        tags = listOf("users")

                        responses {
                            ok("Successful result", User::class.java){}
                        }
                    }
                }
            }
        }

        System.out.println(spec.toYml())
        assertThat(spec.toYml(), notNullValue())
    }
}