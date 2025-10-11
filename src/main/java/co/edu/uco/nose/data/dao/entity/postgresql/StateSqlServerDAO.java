package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.data.dao.entity.SqlConnection;
import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.StateEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public final class StateSqlServerDAO extends SqlConnection implements StateDAO {
    public StateSqlServerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<StateEntity> findAll() {
        return null;
    }

    @Override
    public List<StateEntity> findByFilter(StateEntity filterEntity) {
        return null;
    }

    @Override
    public StateEntity findById(UUID uuid) {
        return null;
    }
}
