package com.cringeteam.todoproject.domain.model

data class Task(
    val id: Long,
    val groupId: Long?,
    val parentId: Long?,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val deadline: Long,
    val priority: Int,
    val notification: Long,
)
