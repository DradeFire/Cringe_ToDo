package com.cringeteam.todoproject.data.rest.model.registration

data class RegistrationUserDto(
    val login: String,
    val password: String,
    val passwordConfirm: String,
)
