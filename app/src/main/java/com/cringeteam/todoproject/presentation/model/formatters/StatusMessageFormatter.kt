package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.StatusMessage
import com.cringeteam.todoproject.presentation.model.StatusMessageVo

class StatusMessageFormatter {

    fun formatter(statusMessage: StatusMessage): StatusMessageVo {
        return StatusMessageVo(
            codeStatus = statusMessage.codeStatus,
            message = statusMessage.message,
        )
    }
}