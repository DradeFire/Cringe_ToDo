package com.cringeteam.todoproject.data.rest.model.registration

import com.cringeteam.todoproject.domain.model.RegistrationUser

class RegistrationUserMapper {

    fun map(request: RegistrationUser): RegistrationUserDto {
        return RegistrationUserDto(
            login = request.login,
            password = request.password,
            passwordConfirm = request.passwordConfirm,
        )
    }
}