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
 * Statuses that have there own methods
 * - 200 : ok
 * - 201 : created
 * - 202 : accepted
 * - 204 : noContent
 * - 400 : badRequest
 * - 404 : notFound
 * - 409 : conflict
 * - 500 : error/else
 *
 * The rest statuses must be set over status(code:HttpCode,...) method
 *
 * @param operation Swagger operation object that contains all response
 */
class Responses(val operation:Operation) {
    // TODO: generic method to add response
}

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
fun Responses.ok(description:String, scheme:Class<*> = Unit::class.java, init:Response.()->Unit) {
    val response = Response()
    response.init()
    response.description = description
    response.schema = getProperty(scheme)
    // TODO: this.headers =
    // TODO: this.examples =
    this.operation.addResponse("200", response)
}