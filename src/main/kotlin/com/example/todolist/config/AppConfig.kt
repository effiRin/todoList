package com.example.todolist.config

import com.example.todolist.database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean (initMethod = "init")
    fun todoDatabase(): TodoDataBase {
        return TodoDataBase()
    }

}