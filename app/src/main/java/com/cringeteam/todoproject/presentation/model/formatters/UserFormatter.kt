package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.User
import com.cringeteam.todoproject.presentation.model.UserVo

class UserFormatter {

    fun format(user: User): UserVo {
        return UserVo(user.login)
    }
}