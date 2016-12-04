package com.maiboroda.swagger

import io.swagger.models.Swagger
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Main controller that returns information about all swagger objects
 * found in the class path
 */
@RestController
class ApiController(val apiService: ApiService) {

    /**
     * Returns all available APIs registered in the classpath
     *
     * @return `List<Api>` list of all APIs
     */
    @GetMapping(value = "/apis", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getAllApis() = ResponseEntity.ok(apiService.apis)

    /**
     * Returns specific Swagger API based on the name and version of the API
     *
     * @param name  Unique name of the API
     * @param version  Version of the API
     * @return  Swagger instance of the API
     */
    @GetMapping(value = "/apis/{name}/versions/{version}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getApi(@PathVariable("name") name: String, @PathVariable("version") version: String): ResponseEntity<Swagger> {
        return ResponseEntity.ok(Swagger())
    }
}