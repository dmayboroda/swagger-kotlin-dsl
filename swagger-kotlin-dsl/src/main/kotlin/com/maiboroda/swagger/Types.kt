package com.maiboroda.swagger

import io.swagger.models.properties.*

/**
 * Shortcuts for all types available in swagger
 */

class Types {
    companion object FormattedTypes {
        val int32 = IntegerProperty()
        val int64 = LongProperty()
        val double = DoubleProperty()
        val float = FloatProperty()
        val dateTime = DateTimeProperty()
        val url = StringProperty(StringProperty.Format.URL)
        var uri = StringProperty(StringProperty.Format.URI)
    }

}
