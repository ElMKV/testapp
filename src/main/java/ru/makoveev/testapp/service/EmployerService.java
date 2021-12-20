package ru.makoveev.testapp.service;

import ru.makoveev.testapp.model.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerService {

    Employer addEmployer(Employer employer);

    List<Employer> readAll();

    Optional<Employer> getEmployerById(Long id);

    Employer updateEmployer(Employer employer, Long id);

    Employer setEmployersBoss(Long bossId, Long id);

    int deleteEmployer(Long id);


}
