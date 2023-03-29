package com.cringeteam.todoproject.domain.model

data class LoginRequest(
    val login: String,
    val password: String,
)