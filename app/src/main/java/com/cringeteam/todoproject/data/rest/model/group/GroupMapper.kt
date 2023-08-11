package com.cringeteam.todoproject.data.rest.model.group

import com.cringeteam.todoproject.domain.model.Group

class GroupMapper {
    fun map(dto: GroupDto): Group {
        return Group(
            dto.id,
            dto.title,
            dto.description,
        )
    }
}