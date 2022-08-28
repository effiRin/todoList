package com.example.todolist.repository

import com.example.todolist.database.Todo
import com.example.todolist.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl : TodoRepository {

    @Autowired
    lateinit var todoDataBase: TodoDataBase

    override fun save(todo: Todo): Todo? {

        // 1. index가 있는가 없는가?

        return todo.index?.let { index ->     // index 값을 가져와서

            // update인 경우
            // apply로 넘긴다. / 기존의 Todo를 찾아온다.
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()

                // created와 index는 건들이지 않는다.
            }
        }?: kotlin.run {
            // null - insert인 경우

            todo.apply {
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDataBase.todoList.add(todo)
                this
            }
        }

//        return todo.apply {
//            this.index = ++todoDataBase.index
//            this.createdAt = LocalDateTime.now()
//            this.updatedAt = LocalDateTime.now()
//        }.run {
//            todoDataBase.todoList.add(todo)
//            this
//        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {

        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun delete(index: Int): Boolean {

        return findOne(index)?.let {
            todoDataBase.todoList.remove(it)
            true
        } ?: kotlin.run {
            false
        }

    }

    override fun findOne(index: Int): Todo? {
        return todoDataBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}