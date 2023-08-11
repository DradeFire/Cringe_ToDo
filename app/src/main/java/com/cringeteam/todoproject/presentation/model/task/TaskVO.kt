package com.cringeteam.todoproject.presentation.model.task

data class TaskVO(
    val id: Long,
    val groupId: Long?,
    val parentId: Long?,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val deadline: Long,
    val priority: Int,
    val notification: String,
)
