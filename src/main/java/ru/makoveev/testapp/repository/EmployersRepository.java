
package ru.makoveev.testapp.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployersRepository {

    Employer add(Employer employer);

    List<Employer> readAll();

    Optional<Employer> getById(Long id);

    Employer update(Employer employer, Long id);

    Employer setBoss(Long bossId, Long id);

    int delete(Long id);
}

