package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.data.dao.entity.StateDAO;
import co.edu.uco.nose.entity.StateEntity;

import java.util.List;
import java.util.UUID;

public class StateSqlServerDAO implements StateDAO {
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
