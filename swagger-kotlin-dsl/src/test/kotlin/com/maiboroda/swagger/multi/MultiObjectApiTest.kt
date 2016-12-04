package com.maiboroda.swagger.multi

import com.maiboroda.swagger.operation
import com.maiboroda.swagger.path
import com.maiboroda.swagger.paths
import com.maiboroda.swagger.swagger
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * This test is written to check multi-object API feature
 * With a help of multi-object feature your are able to:
 * - Define path with a map of operations defined as a separate objects
 */

class MultiObjectApiTest {

    @Test
    fun should_create_api_from_multiple_files() {
        val api = swagger {
            paths {
                path("/users", mapOf("post" to createUser, "get" to listUsers))
            }
        }

        assertThat(api, notNullValue())
    }

    val createUser = operation("createUser") {}

    val listUsers = operation("getUser") {}
}