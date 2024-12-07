package com.github.mkikets99.lntumessengerproject.classes

data class Chat (val users: ArrayList<String>? = ArrayList(), val messages: ArrayList<Message>? = ArrayList())
{
    var _key: String? = null
}