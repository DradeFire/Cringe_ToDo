package com.cringeteam.todoproject.presentation.model.user

import com.cringeteam.todoproject.domain.model.User

class UserFormatter {

    fun format(user: User): UserVo {
        return UserVo(user.login)
    }
}