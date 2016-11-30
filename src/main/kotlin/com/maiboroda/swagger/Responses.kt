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
 * - 302 : found
 * - 303 : seeOther
 * - 400 : badRequest
 * - 404 : notFound
 * - 409 : conflict
 * - 500 : error/else
 *
 * The rest statuses must be set over status(code:HttpCode,...) method
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

fun Responses.response(code:Int, description: String, scheme: Class<*> = Unit::class.java, init: Response.() -> Unit = {}): Response {
    val response = Response()
    response.init()
    response.description = description
    response.schema = getProperty(scheme)
    this.operation.addResponse(code.toString(), response)
    return response
}
/**
 * Add response for `200` HTTP status
 *
 * @param description Required description of a response
 * @param scheme java class of a return value
 * @param init receiver for swagger response object
 */
fun Responses.ok(description:String, scheme:Class<*> = Unit::class.java, init:Response.()->Unit = {}):Response {
    return response(200, description, scheme, init)
}

fun Responses.created(description: String, urlDescription:String="", init:Response.()->Unit = {}):Response {
    val response = response(201, description) {
        headers {
            header("Location", urlDescription, Types.url)
        }
    }
    response.init()
    return response
}

fun Responses.accepted(description: String, scheme: Class<*> = Unit::class.java, init: Response.() -> Unit = {}): Response {
    return response(202, description, scheme, init)
}

fun Responses.noContent(description: String, init: Response.() -> Unit = {}): Response {
    return response(204, description, Unit::class.java, init)
}

fun Responses.badRequest(description: String, scheme: Class<*> = Unit::class.java, init: Response.() -> Unit = {}): Response {
    return response(400, description, scheme, init)
}

fun Responses.noFound(description: String, scheme: Class<*> = Unit::class.java, init: Response.() -> Unit = {}): Response {
    return response(404, description, scheme, init)
}

fun Responses.conflict(description: String, scheme: Class<*> = Unit::class.java, init: Response.() -> Unit = {}): Response {
    return response(409, description, scheme, init)
}

fun Responses.error(description: String, scheme: Class<*> = Unit::class.java, init: Response.() -> Unit = {}): Response {
    return response(500, description, scheme, init)
}

fun Response.headers(init: Headers.()->Unit):Headers {
    val headers = Headers(this)
    headers.init()
    return headers
}