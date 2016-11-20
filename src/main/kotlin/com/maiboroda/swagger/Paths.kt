package com.maiboroda.swagger

import io.swagger.models.Operation
import io.swagger.models.Path

/**
 * Class defines section paths. There is no one-to-one representation in swagger YML format
 * In swagger you define a map of path -> operation. In kotlin you have a set of handy methods to
 */
class Paths(val path:Path) {}

/**
 * There is a  minimal required fields to setup for request calls:
 * - at least one valid response
 */
fun Paths.post(init: Operation.()->Unit) {
    val operation = Operation()
    operation.init()
    path.set("post", operation)
}