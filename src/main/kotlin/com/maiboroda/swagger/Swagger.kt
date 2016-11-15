package com.maiboroda.swagger

import io.swagger.models.*
import io.swagger.models.parameters.BodyParameter
import io.swagger.models.parameters.PathParameter

fun Info.contact(init:Contact.()->Unit) {
    val contact = Contact()
    contact.init()
    this.contact = contact
}

fun Info.license(init:License.()->Unit) {
    val license = License()
    license.init()
    this.license = license
}

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

class Paths(val swagger:Swagger) {}

fun Paths.post(path:String, init:Operation.()->Unit) {
    val operation = Operation()
    operation.init()
    this.swagger.path(path, Path().set("post", operation))
}

fun Operation.path(pathVariable:String, init:PathParameter.() -> Unit) {
    val parameter = PathParameter()
    parameter.init()
    parameter.name = pathVariable
    this.addParameter(parameter)
}

fun <T> Operation.body(model:Class<T>, init:BodyParameter.() -> Unit) {
    val parameter = BodyParameter()
    parameter.init()
    val modelImpl = ModelImpl()
    model.declaredFields.forEach { m ->
        modelImpl.addProperty(m.name, getProperty(m))
    }
    parameter.schema = modelImpl
    this.addParameter(parameter)
}


fun Swagger.paths(init:Paths.()->Unit) {
    val paths = Paths(this)
    paths.init()
}

fun swagger(init:Swagger.()->Unit):Swagger {
    val swagger = Swagger()
    swagger.init()
    return swagger
}