package com.cringeteam.todoproject.data.rest.model.login

data class LoginResponseDto(
    val token: String,
    val userId: Long,
    val login: String,
)