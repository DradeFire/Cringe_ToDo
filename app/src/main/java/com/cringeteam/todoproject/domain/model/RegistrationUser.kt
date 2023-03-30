package com.cringeteam.todoproject.domain.model

data class RegistrationUser(
    val login: String,
    val password: String,
    val passwordConfirm: String,
)
