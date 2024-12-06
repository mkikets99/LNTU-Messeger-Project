package com.github.mkikets99.lntumessengerproject.classes

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

data class Message (val from: String, val message: String){

    fun <T : Any> toMap(obj: T): Map<String, Any?> {
        return (obj::class as KClass<T>).memberProperties.associate { prop ->
            prop.name to prop.get(obj)?.let { value ->
                if (value::class.isData) {
                    toMap(value)
                } else {
                    value
                }
            }
        }
    }
}