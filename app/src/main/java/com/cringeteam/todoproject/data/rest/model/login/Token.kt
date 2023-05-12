package com.cringeteam.todoproject.data.rest.model.login

data class Token(
    val token: String,
    val userId: Long,
    val login: String,
)