package com.cringeteam.todoproject.domain.model

data class LoginResponse(
    val token: String,
    val userId: Long,
    val login: String,
)