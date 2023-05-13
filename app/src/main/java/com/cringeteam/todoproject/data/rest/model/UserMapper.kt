package com.cringeteam.todoproject.data.rest.model

import com.cringeteam.todoproject.domain.model.User

class UserMapper {

    fun map(user: UserDto): User {
        return User(
            user.login
        )
    }
}