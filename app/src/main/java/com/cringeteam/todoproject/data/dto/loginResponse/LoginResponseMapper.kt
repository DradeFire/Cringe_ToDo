package com.cringeteam.todoproject.data.dto.loginResponse

import com.cringeteam.todoproject.domain.model.LoginResponse

class LoginResponseMapper {

    fun format(dto: LoginResponseDto): LoginResponse {
        return LoginResponse(
            code = dto.code
        )
    }
}