package ru.makoveev.testapp.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;
import ru.makoveev.testapp.model.tables.records.EmployersRecord;
import ru.makoveev.testapp.repository.EmployersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    EmployersRepository repository;

    @Override
    public Employer addEmployer(Employer employer) {
        return repository.add(employer);
    }

    @Override
    public List<Employer> readAll() {
        return repository.readAll();
    }

    @Override
    public Optional<Employer> getEmployerById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Employer updateEmployer(Employer employer, Long id) {
        return repository.update(employer, id);
    }

    @Override
    public Employer setEmployersBoss(Long bossId, Long id) {
        return repository.setBoss(bossId, id);
    }

    @Override
    public int deleteEmployer(Long id) {
        return repository.delete(id);
    }
}
