package com.cringeteam.todoproject.presentation.model.task

import com.cringeteam.todoproject.domain.model.Task
import java.text.SimpleDateFormat
import java.util.Locale

class TaskFormatter {

    private val simpleDateFormat = SimpleDateFormat(TIME_PATTERN, Locale.ROOT)
    fun format(task: Task): TaskVO {
        return TaskVO(
            id = task.id,
            groupId = task.groupId,
            parentId = task.parentId,
            title = task.title,
            description = task.description,
            isDone = task.isDone,
            deadline = task.deadline,
            priority = task.priority,
            notification = simpleDateFormat.format(task.notification),
        )
    }

    companion object {
        private const val TIME_PATTERN = "HH:mm dd.MM.yyyy"
    }
}