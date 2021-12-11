package ru.makoveev.testapp.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;

import java.util.List;

@Service
public class EmployersRepository {

    @Autowired
    DSLContext dslContext;

    public List<Employer> getEmployersList() {
        return dslContext
                .selectFrom(Tables.EMPLOYERS)
                .fetchInto(Employer.class);
    }

    public void insertEmployer() {
         dslContext
            .insertInto(Tables.EMPLOYERS,
                    Tables.EMPLOYERS.COMPANYNAME,
                    Tables.EMPLOYERS.FIO,
                    Tables.EMPLOYERS.POSITION)
            .values("check","check", "check")
            .execute();
    }
}
