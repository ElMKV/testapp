package ru.makoveev.testapp.service;

import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Task;

import java.util.List;

public interface TaskService {
    void create(Task task);

    List<Task> readAll();

    Task read(int id);

    boolean update(Task task, int id);

    boolean delete(int id);
}
