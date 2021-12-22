package ru.makoveev.testapp.service;

import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Task;

import java.util.List;
import java.util.Optional;


public interface TaskService {

    Task addTask(Task task);

    List<Task> readAll();

    Optional<Task> getTaskById(Long id);

    Task updateTask(Task task, Long id);

    Task setExecutor(Long executorId, Long id);

    int deleteTask(Long id);

}
