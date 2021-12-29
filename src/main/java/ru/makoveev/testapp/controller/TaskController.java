package ru.makoveev.testapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Task;
import ru.makoveev.testapp.service.EmployerService;
import ru.makoveev.testapp.service.TaskService;

import java.util.List;
import java.util.Optional;


@RestController
public class TaskController {

    @Autowired
    TaskService taskService;


    @PostMapping(value = "/task/{id}")
    public ResponseEntity<Task> create(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        Task result = taskService.addTask(task, id);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/task")
    @ResponseBody
    public List<Task> readAll(){
        return this.taskService.readAll();
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<Task> getById(@PathVariable(name = "id") Long id) {
        final Optional<Task> taskOptional = taskService.getTaskById(id);

        return taskOptional.isPresent()
                ? new ResponseEntity<>(taskOptional.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/task/{id}/executor/{executorId}")
    public ResponseEntity<Task> update(@PathVariable(name = "id") Long id, @PathVariable(name = "executorId") Long executorId) {
        Task updated = taskService.setExecutor(executorId, id);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<Integer> delete(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(Integer.valueOf(taskService.deleteTask(id)), HttpStatus.OK);
    }


}



