package ru.makoveev.testapp.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;
import ru.makoveev.testapp.model.Task;
import ru.makoveev.testapp.model.tables.Employers;
import ru.makoveev.testapp.model.tables.Tasks;
import ru.makoveev.testapp.model.tables.records.TasksRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.count;

@Transactional
@Repository
public class TaskRepositoryImpl implements TaskRepository{

    @Autowired
    DSLContext dslContext;

    @Autowired
    EmployersRepository employersRepository;

    private Task convertDatabaseRecordToTask(TasksRecord record) {

        if(record == null)
            return null;

        Task result = Task.builder()
                .id(record.getId())
                .priority(record.getPriority())
                .description(record.getDescription())
                .build();


        if(record.getExecutor() != null) {
            Optional<Employer> executor = employersRepository.getById(record.getExecutor());
            if(executor.isPresent()) {
                result.setExecutor(executor.get());
            }
        }

        return result;
    }

    public Task add(Task task, Long id) {
        return convertDatabaseRecordToTask (
                dslContext.insertInto(Tables.TASKS,
                                Tables.TASKS.EXECUTOR,
                                Tables.TASKS.DESCRIPTION,
                                Tables.TASKS.PRIORITY)
                        .values(
                                id,
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

    public Optional<Task> getById(Long id) {
        return Optional.ofNullable(convertDatabaseRecordToTask(
                dslContext
                        .selectFrom(Tables.TASKS)
                        .where(Tables.TASKS.ID.eq(id))
                        .fetchAny()));
    }

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

    public int countTasks(Long executorId) {
        Record count = dslContext.select(count())
                .from(Tables.TASKS)
                .where(Tables.TASKS.EXECUTOR.eq(executorId))
                .fetchOne();
        if (count != null) {
            return count.get(0, int.class);
        } else return 0;
    }
}
