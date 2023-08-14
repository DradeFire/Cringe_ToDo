package com.cringeteam.todoproject.data.rest.model.task

import com.cringeteam.todoproject.domain.model.Task

class TaskMapper {
    fun map(dto: TaskDto): Task {
        return Task(
            id = dto.id,
            groupId = dto.groupId,
            parentId = dto.parentId,
            title = dto.title,
            description = dto.description,
            isDone = dto.isDone,
            deadline = dto.deadline,
            priority = dto.priority,
            notification = dto.notification,
        )
    }
}