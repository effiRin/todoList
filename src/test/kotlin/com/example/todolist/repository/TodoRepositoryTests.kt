package com.example.todolist.repository

import com.example.todolist.config.AppConfig
import com.example.todolist.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTests {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest() {
        val todo = Todo().apply {
            this.title = "공부하기"
            this.description = "코틀린과 스프링부트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
        Assertions.assertEquals("공부하기", result?.title)
        Assertions.assertEquals("코틀린과 스프링부트", result?.description)

        println("Test1 : $result")
    }

    @Test
    fun saveAllTest() {

        val todoList = mutableListOf(
            Todo().apply {
                this.title = "공부하기"
                this.description = "코틀린과 스프링부트"
                this.schedule = LocalDateTime.now()
            },

            Todo().apply {
                this.title = "공부하기"
                this.description = "코틀린과 스프링부트"
                this.schedule = LocalDateTime.now()
            },

            Todo().apply {
                this.title = "공부하기"
                this.description = "코틀린과 스프링부트"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        println("Test2 : $result")

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest() {

        val todoList = mutableListOf(
            Todo().apply {
                this.title = "공부하기1"
                this.description = "코틀린"
                this.schedule = LocalDateTime.now()
            },

            Todo().apply {
                this.title = "공부하기2"
                this.description = "스프링부트"
                this.schedule = LocalDateTime.now()
            },

            Todo().apply {
                this.title = "공부하기3"
                this.description = "인생 공부"
                this.schedule = LocalDateTime.now()
            }
        )

        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(3)
        println("Test3 : $result")

        Assertions.assertNotNull(result)
        Assertions.assertEquals("공부하기3", result?.title)
        // result가 null일 수도 있어서
    }

    @Test
    fun updateTest(){

        //업데이트를 위해서 우선 save
        val todo = Todo().apply {
            this.title = "공부하기"
            this.description = "코틀린과 스프링부트"
            this.schedule = LocalDateTime.now()
        }

        val testTodo = todoRepositoryImpl.save(todo)

        val newTodo = Todo().apply {
            this.index = testTodo?.index
            this.title = "밥먹기"
            this.description = "스파게티?"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(newTodo)

        println(result)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(testTodo?.index, result?.index)
        Assertions.assertEquals("밥먹기", result?.title)
        Assertions.assertEquals("스파게티?", result?.description)
    }
}