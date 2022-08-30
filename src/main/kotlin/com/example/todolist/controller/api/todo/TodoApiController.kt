package com.example.todolist.controller.api.todo

import com.example.todolist.model.http.TodoDTO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController {

    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index :Int?){

    }

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDTO: TodoDTO){

    }

    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDTO: TodoDTO){

    }

    @DeleteMapping(path = [""])
    fun delete(@PathVariable(name = "index") _index:Int){

    }

}