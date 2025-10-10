package co.edu.uco.nose.data.dao.entity.postgresql;

import co.edu.uco.nose.data.dao.entity.UserDAO;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public class UserSqlServerDAO implements UserDAO {

    @Override
    public void create(UserEntity entity) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public List<UserEntity> findByFilter(UserEntity filterEntity) {
        return null;
    }

    @Override
    public UserEntity findById(UUID uuid) {
        return null;
    }

    @Override
    public void update(UserEntity entity) {

    }
}
