package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CountryEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class CountrySqlServerDAO extends SqlConnection implements CountryDAO {
    public CountrySqlServerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<CountryEntity> findAll() {
        return null;
    }

    @Override
    public List<CountryEntity> findByFilter(CountryEntity filterEntity) {
        return null;
    }

    @Override
    public CountryEntity findById(UUID uuid) {
        return null;
    }
}
