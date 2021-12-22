package ru.makoveev.testapp.repository;

import org.springframework.stereotype.Repository;
import ru.makoveev.testapp.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {

    Task add(Task task);

    List<Task> readAll();

    Optional<Task> getById(Long id);

    Task update(Task task, Long id);

    Task setExecutor(Long executorId, Long id);

    int delete(Long id);

}
