package com.example.todolist.controller.api.todo

import com.example.todolist.model.http.TodoDTO
import com.example.todolist.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
class TodoApiController(
    val todoService: TodoService
) {

    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any> {

        // index가 없을 때와 있을 때로 나눠줌
        return index?.let {
            // index 있을 때 - read 실행하고 ok 반환해준다.
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it)
        } ?: kotlin.run {
            // 없는 경우엔 301에러를 통해서 readAll로 redirect 시켜준다
            return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, "/api/todo/all")
                .build()
        }
    }

    // read가 2가지 리턴타입이 있어서 readAll 만들어줌

    @GetMapping
    fun readAll(): MutableList<TodoDTO> {
        return todoService.readAll()
    }

    @PostMapping(path = [""])
    fun create(@Valid @RequestBody todoDTO: TodoDTO): TodoDTO? {
        return todoService.create(todoDTO)
    }

    // PUT의 경우엔, create = 201, update = 200 떨어지도록 해야함. (현재는 무조건 200 떨어지는 상황 -> 과제)
    @PutMapping(path = [""])
    fun update(@Valid @RequestBody todoDTO: TodoDTO): TodoDTO? {
        return todoService.update(todoDTO)
    }

    @DeleteMapping(path = [""])
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Any> {

        if(!todoService.delete(_index)){    // false일 때
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()
    }

}