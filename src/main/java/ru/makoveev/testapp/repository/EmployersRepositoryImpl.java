
package ru.makoveev.testapp.repository;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.makoveev.testapp.model.Employer;
import ru.makoveev.testapp.model.Tables;
import ru.makoveev.testapp.model.tables.records.EmployersRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Repository
public class EmployersRepositoryImpl implements EmployersRepository{

    @Autowired
    DSLContext dslContext;

    private Employer convertDatabaseRecordToEmployer(EmployersRecord record) {

        if(record == null)
            return null;

        Employer result = Employer.builder()
                .id(record.getId())
                .companyName(record.getCompanyname())
                .fio(record.getFio())
                .position(record.getPosition())
                .build();




        if(record.getBoss() != null) {
            Optional<Employer> boss = getById(record.getBoss());
            if(boss.isPresent()) {
                result.setBoss(boss.get());
            }
        }
        return result;
    }

    public Employer add(Employer employer) {
        return convertDatabaseRecordToEmployer(
                dslContext.insertInto(Tables.EMPLOYERS,
                        Tables.EMPLOYERS.COMPANYNAME,
                        Tables.EMPLOYERS.FIO,
                        Tables.EMPLOYERS.POSITION)
                .values(employer.getCompanyName(),employer.getFio(),employer.getPosition())
                .returning().fetchOne());
    }

    public List<Employer> readAll() {
        List<EmployersRecord> records = dslContext
                .selectFrom(Tables.EMPLOYERS)
                .fetchInto(EmployersRecord.class);
        return records.stream()
                .map(this::convertDatabaseRecordToEmployer).collect(Collectors.toList());
    }

    @Override
    public Optional<Employer> getById(Long id) {
        return Optional.ofNullable(convertDatabaseRecordToEmployer(
                dslContext
                        .selectFrom(Tables.EMPLOYERS)
                        .where(Tables.EMPLOYERS.ID.eq(id))
                        .fetchAny()));
    }

    @Override
    public Employer update(Employer employer, Long id) {
        return convertDatabaseRecordToEmployer(
                dslContext.update(Tables.EMPLOYERS)
                        .set(Tables.EMPLOYERS.FIO, employer.getFio())
                        .set(Tables.EMPLOYERS.POSITION, employer.getPosition())
                        .set(Tables.EMPLOYERS.COMPANYNAME, employer.getCompanyName())
                        .where(Tables.EMPLOYERS.ID.eq(id))
                        .returning().fetchOne());
    }

    @Override
    public Employer setBoss(Long bossId, Long id) {
        return convertDatabaseRecordToEmployer(
                dslContext.update(Tables.EMPLOYERS)
                        .set(Tables.EMPLOYERS.BOSS, bossId)
                        .where(Tables.EMPLOYERS.ID.eq(id))
                        .returning().fetchOne());
    }

    @Override
    public int delete(Long id) {
        return dslContext.delete(Tables.EMPLOYERS)
                .where(Tables.EMPLOYERS.ID.eq(id))
                .execute();
    }

}

