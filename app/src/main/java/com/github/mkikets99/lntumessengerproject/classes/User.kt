package com.github.mkikets99.lntumessengerproject.classes

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

data class User(val name: String = "No_name", val uuid: String = "No_uuid", val friends: ArrayList<String> = ArrayList()) {

    companion object {
        fun fromMap(map: Map<String,Any?>): User {
            var name: String? = null
            var uuid: String? = null
            var friends: ArrayList<String>? = null

            if(map.containsKey("name")){
                name = map["name"] as String
            }
            if(map.containsKey("uuid")){
                uuid = map["uuid"] as String
            }
            if(map.containsKey("friends")){
                friends = map["friends"] as ArrayList<String>
            }

            return User(name!!,uuid!!,friends!!)
        }
    }

    var _key: String? = null
    override fun toString(): String {
        return name
    }
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