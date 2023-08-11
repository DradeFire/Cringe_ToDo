package com.cringeteam.todoproject.data.rest.model.token

data class Token(
    val token: String,
    val userId: Long,
    val login: String,
)