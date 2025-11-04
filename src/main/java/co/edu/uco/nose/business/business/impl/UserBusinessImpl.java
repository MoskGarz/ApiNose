package co.edu.uco.nose.business.business.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler.getUserEntityAssembler;

import co.edu.uco.nose.business.assembler.entity.impl.IdentificationTypeEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.business.validator.city.ValidateCityExistById;
import co.edu.uco.nose.business.business.validator.idtype.ValidateIdTypeExistById;
import co.edu.uco.nose.business.business.validator.user.ValidateUserMobileNumberDoesNotExist;
import co.edu.uco.nose.business.business.validator.user.ValidateUserEmailDoesNotExist;
import co.edu.uco.nose.business.business.validator.user.ValidateUserDoesNotExistWithSameIdNumberAndIdType;
import co.edu.uco.nose.business.business.validator.user.ValidateDataUserConsistencyForFindUserByFilter;
import co.edu.uco.nose.business.business.validator.user.ValidateDataUserConsistencyForRegisterNewUserInformation;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

import java.util.List;
import java.util.UUID;

public final class UserBusinessImpl implements UserBusiness {

    private final DAOFactory daoFactory;

    public UserBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registerNewUser(UserDomain userDomain) {

        ValidateDataUserConsistencyForRegisterNewUserInformation.executeValidation(userDomain);
        ValidateIdTypeExistById.executeValidation(userDomain.getIdentificationType().getId(), daoFactory);
        ValidateCityExistById.executeValidation(userDomain.getCity().getId(), daoFactory);

        var idTypeEntity = IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler().toEntity(userDomain.getIdentificationType());
        ValidateUserDoesNotExistWithSameIdNumberAndIdType.executeValidation(idTypeEntity, userDomain.getIdentificationNumber(), daoFactory);
        ValidateUserEmailDoesNotExist.executeValidation(userDomain.getEmail(), daoFactory);
        ValidateUserMobileNumberDoesNotExist.executeValidation(userDomain.getPhoneNumber(), daoFactory);
        var userId = generateUniqueUserId();

        var userEntity = getUserEntityAssembler().toEntity(userDomain);
        userEntity.setId(userId);
        daoFactory.getUserDAO().create(userEntity);
    }

    @Override
    public void dropUser(UUID userId) {

        var userEntity = daoFactory.getUserDAO().findById(userId);

        if (!userEntity.equals(UserEntity.getDefaultObject())) {
            daoFactory.getUserDAO().delete(userId);
        }
        else{
            throw NoseException
            .create(
            MessagesEnum.USER_ERROR_DELETING_USER_NOT_FOUND.getContent(), 
            MessagesEnum.TECHNICAL_ERROR_DELETING_USER_NOT_FOUND.getContent());
        }
    }

    @Override
    public void updateUserInformation(UUID userId, UserDomain userDomain) {

        var current = daoFactory.getUserDAO().findById(userId);

        if (!current.equals(UserEntity.getDefaultObject())) {
            var userEntity = getUserEntityAssembler().toEntity(userDomain);
            userEntity.setId(userId);
            daoFactory.getUserDAO().update(userEntity);
        }
        else{
            throw NoseException
            .create(
            MessagesEnum.USER_ERROR_UPDATING_USER_NOT_FOUND.getContent(), 
            MessagesEnum.TECHNICAL_ERROR_UPDATING_USER_NOT_FOUND.getContent());
        }
        

    }

    @Override
    public List<UserDomain> findAllUsers() {
        var userEntityList = daoFactory.getUserDAO().findAll();
        return UserEntityAssembler.getUserEntityAssembler().toDomainList(userEntityList);
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {

        ValidateDataUserConsistencyForFindUserByFilter.executeValidation(userFilters);
        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(userFilters.getIdentificationType().getId())) {
            ValidateIdTypeExistById.executeValidation(userFilters.getIdentificationType().getId(), daoFactory);
        }
        if (!UUIDHelper.getUUIDHelper().isDefaultUUID(userFilters.getCity().getId())) {
            ValidateCityExistById.executeValidation(userFilters.getCity().getId(), daoFactory);
        }

        var filterEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userFilters);
        var userEntityList = daoFactory.getUserDAO().findByFilter(filterEntity);
        return UserEntityAssembler.getUserEntityAssembler().toDomainList(userEntityList);
    }

    @Override
    public UserDomain findSpecificUser(UUID userId) {
        var userEntity = daoFactory.getUserDAO().findById(userId);
        return UserEntityAssembler.getUserEntityAssembler().toDomain(userEntity);
    }

    private UUID generateUniqueUserId() {
        var id = UUIDHelper.getUUIDHelper().generateNewUUID();
        var userEntity = daoFactory.getUserDAO().findById(id);

        while (!UUIDHelper.getUUIDHelper().isDefaultUUID(userEntity.getId())){
            id = UUIDHelper.getUUIDHelper().generateNewUUID();
            userEntity = daoFactory.getUserDAO().findById(id);
        } 
        return id;
    }

    @Override
    public void confirmMobileNumber(UUID userId, int confirmationCode) {
       throw new UnsupportedOperationException(MessagesEnum.UNSUPORTED_OPERATION_MESSAGE.getContent());
    }

    @Override
    public void confirmEmail(UUID userid, int confirmationCode) {
        throw new UnsupportedOperationException(MessagesEnum.UNSUPORTED_OPERATION_MESSAGE.getContent());
    }

    @Override
    public void sendMobileNumberConfirmation(UUID userId) {
        throw new UnsupportedOperationException(MessagesEnum.UNSUPORTED_OPERATION_MESSAGE.getContent());
    }

    @Override
    public void sendEmailConfirmation(UUID userId) {
        throw new UnsupportedOperationException(MessagesEnum.UNSUPORTED_OPERATION_MESSAGE.getContent());
    }


}
