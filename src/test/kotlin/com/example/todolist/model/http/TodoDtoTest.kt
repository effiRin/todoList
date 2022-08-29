package com.example.todolist.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDTOTest(){

        val todoDTO = TodoDTO().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2020-10-20 13:00:00"
        }

        val result = validator.validate(todoDTO)
        Assertions.assertEquals(true, result.isEmpty())

    }
}