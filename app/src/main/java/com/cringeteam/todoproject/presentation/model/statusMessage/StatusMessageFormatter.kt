package com.cringeteam.todoproject.presentation.model.statusMessage

import com.cringeteam.todoproject.domain.model.StatusMessage

class StatusMessageFormatter {

    fun formatter(statusMessage: StatusMessage): StatusMessageVo {
        return StatusMessageVo(
            codeStatus = statusMessage.codeStatus,
            message = statusMessage.message,
        )
    }
}