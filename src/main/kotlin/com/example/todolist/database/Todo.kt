package com.example.todolist.database

import com.example.todolist.model.http.TodoDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Todo (
    var index: Int?=null,                   // 일정 index
    var title: String?=null,                // 일정 title
    var description: String?=null,          // 일정 설명
    var schedule: LocalDateTime?=null,      // 일정 시간
    var createdAt: LocalDateTime?=null,     // 생성 시간
    var updatedAt: LocalDateTime?=null      // 업데이트 시간
        )

fun Todo.convertTodo(todoDTO: TodoDTO): Todo{

    return Todo().apply {
        this.index = todoDTO.index
        this.title = todoDTO.title
        this.description = todoDTO.description
        this.schedule = LocalDateTime.parse(todoDTO.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        this.createdAt = todoDTO.createdAt
        this.updatedAt = todoDTO.updatedAt
    }
}