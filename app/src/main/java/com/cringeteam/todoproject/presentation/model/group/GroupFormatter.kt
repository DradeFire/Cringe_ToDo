package com.cringeteam.todoproject.presentation.model.group

import com.cringeteam.todoproject.domain.model.Group

class GroupFormatter {
    fun format(group: Group): GroupVo {
        return GroupVo(
            group.id,
            group.title,
            group.description,
        )
    }
}