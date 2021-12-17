package ru.makoveev.testapp.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;
import ru.makoveev.testapp.model.tables.Employers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployerServiceImpl implements EmployerService{
    @Autowired
    DSLContext dslContext;
    // Хранилище работодателей
    private static final Map<Integer, Employer> EMPLOYER_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID работодателя
    private static final AtomicInteger EMPLOYER_ID_HOLDER = new AtomicInteger();

    public void create(Employer employer) {
        final int employerId = EMPLOYER_ID_HOLDER.incrementAndGet();
        employer.setId((long) employerId);
        EMPLOYER_REPOSITORY_MAP.put(employerId, employer);
        dslContext.insertInto(Tables.EMPLOYERS,
                Tables.EMPLOYERS.COMPANYNAME,
//                Tables.EMPLOYERS.BOSS,
                Tables.EMPLOYERS.FIO,
                Tables.EMPLOYERS.POSITION,
                Tables.EMPLOYERS.ID)
                .values(employer.getCompanyName(),employer.getFio(),employer.getPosition(), employer.getId())
                .execute();


    }

    public List<Employer> readAll(Employer employer) {
        return dslContext
                .selectFrom(Tables.EMPLOYERS)
                .fetchInto(Employer.class);
    }


    @Override
    public Employer read(int id) {
        return EMPLOYER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Employer employer, int id) {
        if (EMPLOYER_REPOSITORY_MAP.containsKey(id)) {
            employer.setId((long) id);
            EMPLOYER_REPOSITORY_MAP.put(id, employer);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return EMPLOYER_REPOSITORY_MAP.remove(id) != null;
    }
}
