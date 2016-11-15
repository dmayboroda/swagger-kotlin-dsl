package com.maiboroda.swagger

import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.junit.Test
import javax.validation.constraints.NotNull


class PropertyKtTest {

    @Test
    fun should_get_string_property() {
        val username = getProperty(User::class.java.declaredFields[0])

        assertThat(username?.name, equalTo("username"))
        assertThat(username?.type, equalTo("string"))
        assertThat(username?.format, nullValue())
        assertThat(username?.readOnly, equalTo(true))
        assertThat(username?.required, equalTo(false))
    }

    @Test
    fun should_get_not_null_string_property() {
        val username = getProperty(User::class.java.declaredFields[1])

        assertThat(username?.name, equalTo("password"))
        assertThat(username?.type, equalTo("string"))
        assertThat(username?.format, nullValue())
        assertThat(username?.readOnly, equalTo(true))
        assertThat(username?.required, equalTo(true))

    }

    data class User(val username:String, @field:NotNull val password:String)
}