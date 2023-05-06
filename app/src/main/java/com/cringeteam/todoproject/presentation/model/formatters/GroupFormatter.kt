package com.cringeteam.todoproject.presentation.model.formatters

import com.cringeteam.todoproject.domain.model.Group
import com.cringeteam.todoproject.presentation.model.GroupVo

class GroupFormatter {
    fun format(group: Group): GroupVo {
        return GroupVo(
            group.id,
            group.title,
            group.description,
        )
    }
}