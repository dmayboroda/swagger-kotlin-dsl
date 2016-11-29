package com.maiboroda.swagger

import io.swagger.models.Operation
import io.swagger.models.Response
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class ResponsesKtTest {

    @Test
    fun should_create_created_response() {
        val operation = Operation()
        val responses = Responses(operation)
        val created = responses.created("Response description", "Location header description")

        assertCreatedHeader(created, operation)
    }

    private fun assertCreatedHeader(res: Response, operation: Operation) {
        assertThat(res, equalTo(operation.responses.get("201")))
        assertThat(res.headers.get("Location"), equalTo(operation.responses.get("201")?.headers?.get("Location")))
    }

    @Test
    fun should_create_created_resopnse_with_properties() {
        val operation = Operation()
        val responses = Responses(operation)

        val created = responses.created("Response description", "Location header description", {
            headers {
                header("X-Rate-Limit", "calls per hour allowed by the user", Types.int32)
                header("X-Expires-After", "date in UTC when token expires", Types.dateTime)
            }
        })

        assertCreatedHeader(created, operation)
        assertThat(created.headers.get("X-Rate-Limit"), equalTo(header("X-Rate-Limit", "calls per hour allowed by the user", Types.int32).second))
    }
}