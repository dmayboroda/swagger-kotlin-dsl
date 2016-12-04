package com.maiboroda.swagger

import io.swagger.models.Response
import io.swagger.models.properties.Property
import io.swagger.models.properties.StringProperty

/**
 * Set of functions to work with headers in swagger object where
 * headers is Map<String, Property>
 */
class Headers(val response:Response) {}

fun Headers.header(name:String, description:String, property:Property = StringProperty()):Pair<String, Property> {
    property.description = description
    this.response.addHeader(name, property)
    return Pair(name, property)
}

fun header(name:String, description:String, property:Property):Pair<String, Property> {
    property.description = description
    return Pair(name, property)
}
