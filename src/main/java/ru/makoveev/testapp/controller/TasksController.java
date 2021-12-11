package ru.makoveev.testapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.makoveev.testapp.model.Task;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public final class TasksController {

//    @GetMapping
//    public ResponseEntity<List<Task>> getTodoList() {

//		return ResponseEntity.ok(this.jdbcOperations.query("select * from todo", (resultSet, i) ->
//				new model.Task(UUID.fromString(resultSet.getString("id")),
//						resultSet.getTimestamp("date_created").toLocalDateTime(),
//						resultSet.getBoolean("done"),
//						resultSet.getString("task"))));
//    }

}

