package com.maiboroda.swagger

import io.swagger.models.Info
import io.swagger.models.Path
import io.swagger.models.Swagger
import io.swagger.models.Tag
import io.swagger.util.Yaml

fun Swagger.info(init:Info.()->Unit) {
    val info = Info()
    info.init()
    this.info = info
}

/**
 * Tag in specification must be declared as
 * <code><pre>
 * {
 *  tags {
 *      tag {
 *       name = ""
 *       description = ""
 *      }
 *      tag {
 *       name = ""
 *       description = ""
 *      }
 *  }
 * }
 * </pre></code>
 */
class Tags(val tags:MutableList<Tag> = mutableListOf()) {
    fun tag(init:Tag.()->Unit) {
        val tag = Tag()
        tag.init()
        this.tags.add(tag)
    }
}

fun Swagger.tags(init:Tags.()->Unit) {
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

fun swagger(init:Swagger.()->Unit):Swagger {
    val swagger = Swagger()
    swagger.init()
    return swagger
}