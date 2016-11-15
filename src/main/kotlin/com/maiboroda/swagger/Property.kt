package com.maiboroda.swagger

import io.swagger.models.properties.*
import java.io.File
import java.lang.reflect.Array
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotNull

fun getProperty(field: Field): Property? {
    val property = when (field.type) {
    // BinaryProperty
    // ByteArrayProperty
        java.lang.Object::class.java -> ObjectProperty()
        Integer::class.java -> IntegerProperty()
        Long::class.java -> LongProperty()
        Double::class.java -> DoubleProperty()
        Float::class.java -> FloatProperty()
    // PasswordProperty
    // EmailProperty
        String::class.java -> StringProperty()
        LocalDate::class.java -> DateProperty()
        LocalDateTime::class.java -> DateTimeProperty()
        UUID::class.java -> UUIDProperty()
        Boolean::class.java -> BooleanProperty()
        File::class.java -> FileProperty()
        Array::class.java -> ArrayProperty()
        Map::class.java -> MapProperty()
        else -> {
            ObjectProperty()
        }
    }
    property.apply {
        name = field.name
        readOnly = Modifier.isFinal(field.modifiers)
        required = field.declaredAnnotations.filter { it.javaClass.isAssignableFrom(NotNull::class.java) }.isNotEmpty()
    }
    return property
}