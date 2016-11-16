package com.maiboroda.swagger

import io.swagger.models.Operation
import io.swagger.models.Response

/**
 * Builder for responses section
 *
 * For example:
 * ```
 *     responses {
 *       ok ("Successful response", User::class.java)
 *       badRequest("Resource not found")
 *       conflict("Resource in an inpropriate state", Error::class.java)
 *     }
 * ```
 *
 * @param operation Swagger operation object that contains all response
 */
class Responses(val operation:Operation) {}

/**
 * Definition of `responses` section for swagger operation
 *
 * @param init receiver for all responses of one operation
 */
fun Operation.responses(init:Responses.()->Unit) {
    val responses = Responses(this)
    responses.init()
}

/**
 * Add response for `200` HTTP status
 *
 * @param description Required description of a response
 * @param scheme java class of a return value
 * @param init receiver for swagger response object
 */
fun <T> Responses.ok(description:String, scheme:Class<T>, init:Response.()->Unit) {
    val response = Response()
    response.init()
    response.description = description
    response.schema = getProperty(scheme)
    // TODO: this.headers =
    // TODO: this.examples =
    this.operation.addResponse("200", response)
}