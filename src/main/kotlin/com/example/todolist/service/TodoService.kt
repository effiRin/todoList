package com.example.todolist.service

import com.example.todolist.database.Todo
import com.example.todolist.database.convertTodo
import com.example.todolist.model.http.TodoDTO
import com.example.todolist.model.http.convertTodoDTO
import com.example.todolist.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

@Service
class TodoService (
    val todoRepositoryImpl: TodoRepositoryImpl
        ){

    // C
    fun create(todoDTO: TodoDTO): TodoDTO?{
       // 넘어오면 바꿔줘야 함
        return todoDTO.let {
            Todo().convertTodo(it)
        }.let {
            // 다시 let을 받으면 it이 Todo로 바뀜
            todoRepositoryImpl.save(it)
            // save 해주면 생성이 될 것이고 이 결과를 return 해준다.
        }?.let{
            // let으로 TodoDTO로 한번 더 전환해준다.
            TodoDTO().convertTodoDTO(it)
        }

     }

    // R
    fun read(index:Int): TodoDTO?{
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDTO().convertTodoDTO(it)
        }
    }

    fun readAll(): MutableList<TodoDTO>{
        return todoRepositoryImpl.findAll().map{
            TodoDTO().convertTodoDTO(it)
        }.toMutableList()
    }

    // U  - update는 create와 동일함
    fun update(todoDTO: TodoDTO): TodoDTO?{
        return todoDTO.let {
            Todo().convertTodo(it)
        }.let {
            todoRepositoryImpl.save(it)
        }?.let {
            TodoDTO().convertTodoDTO(it)
        }
    }

    // D
    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }

}