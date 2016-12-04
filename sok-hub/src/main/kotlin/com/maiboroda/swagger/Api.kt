package com.maiboroda.swagger

import io.swagger.models.Swagger

class Api(val name:String, val versions:Map<Version, Swagger>)

data class Version(val major:Int, val minor:Int, val build:Int?=0, val revision:Int?=0)