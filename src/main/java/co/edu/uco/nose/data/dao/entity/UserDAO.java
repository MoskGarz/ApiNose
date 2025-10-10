package co.edu.uco.nose.data.dao.entity;

import co.edu.uco.nose.data.dao.CreateDAO;
import co.edu.uco.nose.data.dao.DeleteDAO;
import co.edu.uco.nose.data.dao.RetrieveDAO;
import co.edu.uco.nose.data.dao.UpdateDAO;
import co.edu.uco.nose.entity.UserEntity;

import java.util.UUID;

public interface UserDAO extends CreateDAO<UserEntity>, RetrieveDAO<UserEntity, UUID>, UpdateDAO<UserEntity>, DeleteDAO<UUID> {

}
