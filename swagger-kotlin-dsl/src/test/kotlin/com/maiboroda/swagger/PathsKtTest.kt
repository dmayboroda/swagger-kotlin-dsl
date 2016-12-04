package com.maiboroda.swagger

import io.swagger.models.Path
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class PathsKtTest {
    // TODO: Please add Data providers to JUnit or switch to TestNG add cover all methods
    @Test
    fun should_generate_head_path_operation() {
        val path = Path()
        val operation = path.head {
            summary = "Head operation"
        }

        assertThat(path.head, equalTo(operation))
    }

    @Test
    fun should_generate_put_path_operation() {
        val path = Path()
        val operation = path.put {
            summary = "Put operation"
        }

        assertThat(path.put, equalTo(operation))
    }
}