package com.maiboroda.swagger

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SwaggerKotlinDslApplication

fun main(args: Array<String>) {
    SpringApplication.run(SwaggerKotlinDslApplication::class.java, *args)
}
