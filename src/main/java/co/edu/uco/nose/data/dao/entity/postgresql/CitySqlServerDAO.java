package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.data.dao.entity.CityDAO;
import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.entity.CityEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class CitySqlServerDAO extends SqlConnection implements CityDAO {

    public CitySqlServerDAO(final Connection connection) {
        super(connection);
    }
    @Override
    public List<CityEntity> findAll() {
        return null;
    }

    @Override
    public List<CityEntity> findByFilter(CityEntity filterEntity) {
        return null;
    }

    @Override
    public CityEntity findById(UUID uuid) {
        return null;
    }
}
