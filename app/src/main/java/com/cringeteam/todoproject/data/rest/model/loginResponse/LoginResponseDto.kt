package com.cringeteam.todoproject.data.rest.model.loginResponse

data class LoginResponseDto(
    val token: String,
    val userId: Long,
    val login: String,
)