package com.maiboroda.swagger

import io.swagger.models.Contact
import io.swagger.models.Info
import io.swagger.models.License

/**
 * Extension for swagger.Info object
 * Two methods `contact` and `license` for builder way
 * to configure appropriate objects in [io.swagger.models.Info] class
 */

/**
 * Extension method for [io.swagger.models.Contact] class
 */
fun Info.contact(init: Contact.()->Unit) {
    val contact = Contact()
    contact.init()
    this.contact = contact
}

/**
 * Extension method for [io.swagger.models.License] class
 */
fun Info.license(init: License.()->Unit) {
    val license = License()
    license.init()
    this.license = license
}