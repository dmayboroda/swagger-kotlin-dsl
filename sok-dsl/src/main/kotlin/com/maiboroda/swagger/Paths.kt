package com.maiboroda.swagger

import io.swagger.models.HttpMethod
import io.swagger.models.Operation
import io.swagger.models.Path
import io.swagger.models.Swagger

/**
 * Class defines section paths. It is one-to-one representation of swagger `paths` from YML format
 * In swagger you define a map of path -> operation. In kotlin you have a set of handy methods for each
 * operation
 * - post
 * - get
 * - etc.
 *
 * You can also define an operation in a separate object with a help of an `operation` function like so
 * ```kotlin
 * val api = swagger {
 *   paths {
 *     path("/users", mapOf("post" to createUser, "get" to listUsers))
 *   }
 * }
 * ```
 */
class Paths(val swagger:Swagger) {
    companion object Operations {
        fun addMethod(path:Path, method:HttpMethod, init:Operation.()->Unit):Operation {
            val operation = Operation()
            operation.init()
            path.set(method.name.toLowerCase(), operation)
            return operation
        }
    }
}

fun Paths.path(url:String, init:Path.()->Unit):Path {
    val path = Path()
    path.init()
    this.swagger.path(url, path)
    return path
}

fun Paths.path(url:String, operations:Map<String, Operation>):Path {
    val path = Path()
    operations.forEach { e -> path.set(e.key, e.value)}
    this.swagger.path(url, path)
    return path
}

/**
 * There is a  minimal required fields to setup for request calls:
 * - at least one valid response
 */
fun Path.post(init: Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.POST, init)
}

fun Path.get(init:Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.GET, init)
}

fun Path.head(init:Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.HEAD, init)
}

fun Path.put(init:Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.PUT, init)
}
// TODO: need tests for all these methods, but I need to have data providers in tests
fun Path.patch(init:Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.PATCH, init)
}

fun Path.delete(init:Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.DELETE, init)
}

fun Path.options(init:Operation.()->Unit):Operation {
    return Paths.addMethod(this, HttpMethod.OPTIONS, init)
}


/**
 * Function that can be used to construct an `Operation` object that can be assigned
 * to a variable and used later on
 *
 * @param methodName name of an operation that will be used as a method name during code generation
 * @return [Operation] swagger object
 */
fun operation(methodName:String, init:Operation.()->Unit):Operation {
    val operation = Operation()
    operation.init()
    operation.operationId = methodName
    return operation
}