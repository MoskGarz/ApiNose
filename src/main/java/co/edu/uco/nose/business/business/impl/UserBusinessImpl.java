package co.edu.uco.nose.business.business.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler.getUserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public final class UserBusinessImpl implements UserBusiness {

    private DAOFactory daoFactory;

    public UserBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewUser(UserDomain userDomain) {

        //1. Validar que la informacion sea consistente a nivel tipo de dato y reglas

        //2. validar que no exista previamente otro usuario con el mismo tipo y numero de identificacion

        //3. validar que no exista previamente otro usuario con el mismo correo

        //4. validar que no exista previamente otro usuario con el mismo numero de telefono celular

        //5. generar un UUID hasta que sea unico

        var userId = UUIDHelper.getUUIDHelper().generateNewUUID();
        var userEntity = getUserEntityAssembler().toEntity(userDomain);
        userEntity.setId(userId);

        //6. registrar la informacion del nuevo usuario
        daoFactory.getUserDAO().create(userEntity);
    }

    @Override
    public void dropUser(UUID userId) {

    }

    @Override
    public void updateUserInformation(UUID userid, UserDomain userDomain) {

    }

    @Override
    public List<UserDomain> findAllUsers() {
        return null;
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {
        return null;
    }

    @Override
    public UserDomain findSpecificUser(UUID userId) {
        return null;
    }

    @Override
    public void confirmMobileNumber(UUID userId, int confirmationCode) {

    }

    @Override
    public void confirmEmail(UUID userid, int confirmationCode) {

    }

    @Override
    public void sendMobileNumberConfirmation(UUID userId) {

    }

    @Override
    public void sendEmailConfirmation(UUID userId) {

    }
}
