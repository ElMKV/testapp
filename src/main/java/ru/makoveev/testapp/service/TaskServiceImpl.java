package ru.makoveev.testapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Task;
import ru.makoveev.testapp.repository.TaskRepository;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository repository;

    @Override
    public Task addTask(Task task) {
        return repository.add(task);
    }

    @Override
    public List<Task> readAll() {
        return repository.readAll();    }


    @Override
    public Optional<Task> getTaskById(Long id) {
        return repository.getById(id);

    }

    @Override
    public Task updateTask(Task task, Long id) {
        return repository.update(task, id);
    }

    @Override
    public Task setExecutor(Long executorId, Long id) {
        return repository.setExecutor(executorId, id);

    }

    @Override
    public int deleteTask(Long id) {
        return repository.delete(id);
    }
}
