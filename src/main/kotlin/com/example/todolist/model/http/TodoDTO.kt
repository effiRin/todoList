package com.example.todolist.model.http

import com.example.todolist.database.Todo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

class TodoDTO (
    var index: Int?=null,                  // 일정 index

    @field:NotBlank
    var title: String?=null,              // 일정 title

    var description: String?=null,          // 일정 설명

    @field:NotBlank
    // yyyy-MM-dd HH:mm:ss
    var schedule: String?=null,     // 일정 시간

    var createdAt: LocalDateTime?=null,    // 생성 시간

    var updatedAt: LocalDateTime?=null      // 업데이트 시간
        ){

    // Todo 이전에 학습했던 custom annotation으로 변경하는 것을 추천
    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss 포맷이 맞지 않습니다.")
    fun validSchedule(): Boolean{
        return try{
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e : Exception){
            false
        }
        // 파싱을 해서 에러가 안나면 true, 에러가 나면 false 반환
    }
}

fun TodoDTO.convertTodoDTO(todo: Todo): TodoDTO {

    return TodoDTO().apply {
        this.index = todo.index
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        // 포맷 이용해서 다시 문자열로 바꿔줌
        this.createdAt = todo.createdAt
        this.updatedAt = todo.updatedAt
    }
}
