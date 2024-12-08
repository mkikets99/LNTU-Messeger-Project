package com.github.mkikets99.lntumessengerproject.classes

import com.google.firebase.util.nextAlphanumericString
import kotlin.random.Random

data class Chat (val chatId: String = Random.nextAlphanumericString(16), val users: ArrayList<String>? = ArrayList(), val messages: ArrayList<Message>? = ArrayList())