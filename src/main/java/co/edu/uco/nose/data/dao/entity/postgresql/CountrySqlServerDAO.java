package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.data.dao.entity.CountryDAO;
import co.edu.uco.nose.entity.CountryEntity;

import java.util.List;
import java.util.UUID;

public class CountrySqlServerDAO implements CountryDAO {
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
