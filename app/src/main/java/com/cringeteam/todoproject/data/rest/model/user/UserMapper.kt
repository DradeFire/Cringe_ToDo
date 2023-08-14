package com.cringeteam.todoproject.data.rest.model.user

import com.cringeteam.todoproject.domain.model.User

class UserMapper {

    fun map(user: UserDto): User {
        return User(
            user.login
        )
    }
}