package com.maiboroda.swagger

import io.swagger.models.ModelImpl
import io.swagger.models.Operation
import io.swagger.models.parameters.BodyParameter
import io.swagger.models.parameters.PathParameter

/**
 * Class that defines parameters section of swagger YML format
 * Parameters section belongs to one of an Operation like post, get, head, etc.
 */
class Parameters(val operation:Operation)

fun Operation.parameters(init:Parameters.()->Unit) {
    val parameters = Parameters(this)
    parameters.init()
}
/**
 * These are minimal required settings for Path parameter
 * <code>
 *     parameters:
 *       - name: "token"
 *         in: "path"
 *         required: true
 *         type: "integer"
 *  </code>
 */
fun Parameters.path(pathVariable:String, init: PathParameter.() -> Unit) {
    val parameter = PathParameter()
    parameter.init()
    parameter.name = pathVariable
    this.operation.addParameter(parameter)
}

/**
 * Swagger hub needs to have `name:body` explicitly defined for Body Parameters
 */
fun <T> Parameters.body(model:Class<T>, init: BodyParameter.() -> Unit) {
    val parameter = BodyParameter()
    parameter.init()
    parameter.schema = getModel(model)
    parameter.name = "body"
    this.operation.addParameter(parameter)
}

fun <T> getModel(model: Class<T>): ModelImpl {
    val modelImpl = ModelImpl()
    model.declaredFields.forEach { m ->
        modelImpl.addProperty(m.name, getProperty(m))
    }
    return modelImpl
}