package com.github.mkikets99.lntumessengerproject.classes

data class User(val name: String = "No_name", val uuid: String = "No_uuid") {
    override fun toString(): String {
        return name
    }
}