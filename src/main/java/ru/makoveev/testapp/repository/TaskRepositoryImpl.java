package ru.makoveev.testapp.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;
import ru.makoveev.testapp.model.Task;
import ru.makoveev.testapp.model.tables.records.EmployersRecord;
import ru.makoveev.testapp.model.tables.records.TasksRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    DSLContext dslContext;

    private Task convertDatabaseRecordToTask(TasksRecord record) {

        if(record == null)
            return null;

        Task result = Task.builder()
                .id(record.getId())
                .priority(record.getPriority())
                .description(record.getDescription())
                .build();

        if(record.getExecutor() != null) {
            Optional<Task> executor = getById(record.getExecutor());
            if(executor.isPresent()) {
                result.setExecutor(executor.get().getExecutor());
            }
        }
        return result;
    }

    public Task add(Task task) {
        return convertDatabaseRecordToTask(

                dslContext.insertInto(Tables.TASKS,
                                Tables.TASKS.EXECUTOR,
                                Tables.TASKS.DESCRIPTION,
                                Tables.TASKS.PRIORITY)
                        .values(
                                29L,
                                task.getDescription(),
                                task.getPriority())
                        .returning().
                        fetchOne());
    }




    public List<Task> readAll() {
        List<TasksRecord> records = dslContext
                .selectFrom(Tables.TASKS)
                .fetchInto(TasksRecord.class);
        return records.stream()
                .map(this::convertDatabaseRecordToTask).collect(Collectors.toList());
    }

    @Override
    public Optional<Task> getById(Long id) {
        return Optional.ofNullable(convertDatabaseRecordToTask(
                dslContext
                        .selectFrom(Tables.TASKS)
                        .where(Tables.TASKS.ID.eq(id))
                        .fetchAny()));
    }

    @Override
    public Task update(Task task, Long id) {
        return convertDatabaseRecordToTask(
                dslContext.update(Tables.TASKS)
                        .set(Tables.TASKS.DESCRIPTION, task.getDescription())
                        .set(Tables.TASKS.PRIORITY, task.getPriority())
                        .set(Tables.TASKS.EXECUTOR, task.getExecutor().getId())
                        .where(Tables.EMPLOYERS.ID.eq(id))
                        .returning().fetchOne());
    }


    public Task setExecutor(Long executorId, Long id) {
        return convertDatabaseRecordToTask(
                dslContext.update(Tables.TASKS)
                        .set(Tables.TASKS.EXECUTOR, executorId)
                        .where(Tables.TASKS.ID.eq(id))
                        .returning().fetchOne());
    }

    public int delete(Long id) {
        return dslContext.delete(Tables.TASKS)
                .where(Tables.TASKS.ID.eq(id))
                .execute();
    }
}
