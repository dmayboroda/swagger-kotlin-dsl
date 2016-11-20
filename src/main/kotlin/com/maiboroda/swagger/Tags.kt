package com.maiboroda.swagger

import io.swagger.models.Tag

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
    fun tag(init: Tag.()->Unit) {
        val tag = Tag()
        tag.init()
        this.tags.add(tag)
    }
}