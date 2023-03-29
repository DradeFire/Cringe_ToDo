package com.cringeteam.todoproject.presentation.model

data class LoginResponseVo(
    val token: String,
    val userId: Long,
    val login: String,
)