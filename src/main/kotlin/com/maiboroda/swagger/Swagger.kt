package com.maiboroda.swagger

import io.swagger.models.Info
import io.swagger.models.Path
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

fun Swagger.paths(url:String, init:Paths.()->Unit) {
    val path = Path()
    val paths = Paths(path)
    paths.init()
    // add path to swagger map
    this.path(url, path)
}

fun Swagger.toYml():String {
    return Yaml.mapper().writeValueAsString(this)
}