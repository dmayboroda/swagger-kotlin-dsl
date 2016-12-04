package com.maiboroda.swagger

import io.swagger.models.Info
import io.swagger.models.Swagger
import io.swagger.util.Yaml

/**
 * Main file with all swagger object extension functions
 *
 * Swagger object consists of:
 * - swagger
 *   - info : Info
 *   - host: String
 *   - basePath: String
 *   - schemes: Array<Scheme>
 *   - consumes: Array<MimeType>
 *   - produces: Array<MimeType>
 *   - paths: List<Operation>
 *     - parameters: Global parameters for an Operation
 *     - responses: Responses of an Operation
*    - securityDefinitions: List<SecurityDefinition>
 *   - security: ??
 *   - tags: Map<String, Tag>
 *   - externalDocs: ???
 *
 */

fun swagger(init:Swagger.()->Unit):Swagger {
    val swagger = Swagger()
    swagger.init()
    return swagger
}

fun Swagger.info(init:Info.()->Unit) {
    val info = Info()
    info.init()
    this.info = info
}

fun Swagger.tags(init: Tags.()->Unit) {
    val tags = Tags()
    tags.init()
    this.tags = tags.tags
}

/**
 * Extension function that is used to construct `paths` section of swagger API
 * Method just paths swagger instance for further usage
 */
fun Swagger.paths(init:Paths.()->Unit) {
    val paths = Paths(this)
    paths.init()
}

fun Swagger.toYml():String {
    return Yaml.mapper().writeValueAsString(this)
}