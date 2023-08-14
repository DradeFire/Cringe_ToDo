package com.cringeteam.todoproject.data.rest.model.task

data class TaskDto(
    val id: Long,
    val groupId: Long?,
    val parentId: Long?,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val deadline: Long,
    // TODO: clarify about priority. 2 = red, 1 = yellow, 0 = black?
    val priority: Int,
    val notification: Long,
)
