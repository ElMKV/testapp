package ru.makoveev.testapp.service;

import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskServiceImpl implements TaskService{
    // Хранилище задач
    private static final Map<Integer, Task> TASK_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID задач
    private static final AtomicInteger TASK_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Task task) {
        final int taskId = TASK_ID_HOLDER.incrementAndGet();
        task.setId((long) taskId);
        TASK_REPOSITORY_MAP.put(taskId, task);
    }

    @Override
    public List<Task> readAll() {
        return new ArrayList<>(TASK_REPOSITORY_MAP.values());
    }

    @Override
    public Task read(int id) {
        return TASK_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Task task, int id) {
        if (TASK_REPOSITORY_MAP.containsKey(id)) {
            task.setId((long) id);
            TASK_REPOSITORY_MAP.put(id, task);
            return true;
        }

        return false;    }

    @Override
    public boolean delete(int id) {
        return TASK_REPOSITORY_MAP.remove(id) != null;
    }
}
