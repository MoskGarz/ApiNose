package co.edu.uco.nose.business.business.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler.getUserEntityAssembler;

import co.edu.uco.nose.business.assembler.entity.impl.UserEntityAssembler;
import co.edu.uco.nose.business.business.UserBusiness;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
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

        validateDataConsistency(userDomain);

        ensureIdentificationIsUnique(userDomain);
        ensureEmailIsUnique(userDomain);
        ensurePhoneIsUnique(userDomain);

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
            "El usuario que se quiere eliminar no existe", 
            "la funcion findById no logró encontrar el usuario que se quiere eliminar");
        }
        
    }

    @Override
    public void updateUserInformation(UUID userId, UserDomain userDomain) {

        validateDataConsistency(userDomain);
        var current = daoFactory.getUserDAO().findById(userId);

        if (!current.equals(UserEntity.getDefaultObject())) {
            
            var emailChanged = !current.getEmail().equals(userDomain.getEmail());
            var phoneChanged = !current.getPhoneNumber().equals(userDomain.getPhoneNumber());
            var idTypeChanged = !current.getIdentificationType().getId().equals(userDomain.getIdentificationType().getId());
            var idNumberChanged = !current.getIdentificationNumber().equals(userDomain.getIdentificationNumber());
            var identificationChanged = idTypeChanged || idNumberChanged;

            if (emailChanged) {
                ensureEmailIsUniqueExcludingUser(userId, userDomain);
            }
            if (phoneChanged) {
                ensurePhoneIsUniqueExcludingUser(userId, userDomain);
            }
            if (identificationChanged) {
                ensureIdentificationIsUniqueExcludingUser(userId, userDomain);
            }

        
            var userEntity = getUserEntityAssembler().toEntity(userDomain);
            userEntity.setId(userId);
            daoFactory.getUserDAO().update(userEntity);
        }
        else{
            throw NoseException
            .create(
            "El usuario que se quiere editar no existe", 
            "la funcion findById no logró encontrar el usuario que se quiere editar");
        }
        

    }

    @Override
    public List<UserDomain> findAllUsers() {
        var userEntityList = daoFactory.getUserDAO().findAll();
        return UserEntityAssembler.getUserEntityAssembler().toDomainList(userEntityList);
    }

    @Override
    public List<UserDomain> findUsersByFilter(UserDomain userFilters) {
        var filterEntity = UserEntityAssembler.getUserEntityAssembler().toEntity(userFilters);
        var userEntityList = daoFactory.getUserDAO().findByFilter(filterEntity);
        return UserEntityAssembler.getUserEntityAssembler().toDomainList(userEntityList);
    }

    @Override
    public UserDomain findSpecificUser(UUID userId) {
        var userEntity = daoFactory.getUserDAO().findById(userId);
        return UserEntityAssembler.getUserEntityAssembler().toDomain(userEntity);
    }

    private void ensureIdentificationIsUnique(final UserDomain userDomain) {
        var filter = new UserEntity();
        var idType = new IdentificationTypeEntity();
        idType.setId(userDomain.getIdentificationType().getId());
        filter.setIdentificationType(idType);
        filter.setIdentificationNumber(userDomain.getIdentificationNumber());

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(filter);
        if (!matches.isEmpty()) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }
    }

    private void ensureEmailIsUnique(final UserDomain userDomain) {
        var filter = new UserEntity();
        filter.setEmail(userDomain.getEmail());

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(filter);
        if (!matches.isEmpty()) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_EMAIL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_EMAIL.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }
    }

    private void ensurePhoneIsUnique(final UserDomain userDomain) {
        var filter = new UserEntity();
        filter.setPhoneNumber(userDomain.getPhoneNumber());

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(filter);
        if (!matches.isEmpty()) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_PHONE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_PHONE.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }
    }

    private UUID generateUniqueUserId() {
        UUID newId;
        do {
            newId = UUIDHelper.getUUIDHelper().generateNewUUID();
            var idFilter = new UserEntity();
            idFilter.setId(newId);
            var exists = !daoFactory.getUserDAO().findByFilter(idFilter).isEmpty();
            if (!exists) {
                break;
            }
        } while (true);
        return newId;
    }

    private void validateDataConsistency(final UserDomain userDomain) {
        final String lettersPattern = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'\\-\\s]+$";
        final String digitsPattern = "^[0-9]+$";
        final String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

        if (userDomain.getIdentificationNumber() == null || userDomain.getIdentificationNumber().isBlank() || !userDomain.getIdentificationNumber().matches(digitsPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_ID_NUMBER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_ID_NUMBER.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }

        if (userDomain.getFirstName() == null || userDomain.getFirstName().isBlank() || !userDomain.getFirstName().matches(lettersPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_FIRST_NAME.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_FIRST_NAME.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }

        if (userDomain.getSecondName() != null && !userDomain.getSecondName().isBlank() && !userDomain.getSecondName().matches(lettersPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_SECOND_NAME.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_SECOND_NAME.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }

        if (userDomain.getFirstLastName() == null || userDomain.getFirstLastName().isBlank() || !userDomain.getFirstLastName().matches(lettersPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_FIRST_LASTNAME.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_FIRST_LASTNAME.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }

        if (userDomain.getSecondLastName() != null && !userDomain.getSecondLastName().isBlank() && !userDomain.getSecondLastName().matches(lettersPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_SECOND_LASTNAME.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_SECOND_LASTNAME.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }

        if (userDomain.getPhoneNumber() == null || userDomain.getPhoneNumber().isBlank() || !userDomain.getPhoneNumber().matches(digitsPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_PHONE_NUMBER.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_PHONE_NUMBER.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }

        if (userDomain.getEmail() == null || userDomain.getEmail().isBlank() || !userDomain.getEmail().matches(emailPattern)) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_INVALID_EMAIL.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_INVALID_EMAIL.getContent();
            throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
        }
    }

    private void ensureEmailIsUniqueExcludingUser(final UUID userId, final UserDomain userDomain) {
        var filter = new UserEntity();
        filter.setEmail(userDomain.getEmail());

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(filter);
        for (var it : matches) {
            if (!it.getId().equals(userId)) {
                var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_EMAIL.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_EMAIL.getContent();
                throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
            }
        }
    }

    private void ensurePhoneIsUniqueExcludingUser(final UUID userId, final UserDomain userDomain) {
        var filter = new UserEntity();
        filter.setPhoneNumber(userDomain.getPhoneNumber());

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(filter);
        for (var it : matches) {
            if (!it.getId().equals(userId)) {
                var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_PHONE.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_PHONE.getContent();
                throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
            }
        }
    }

    private void ensureIdentificationIsUniqueExcludingUser(final UUID userId, final UserDomain userDomain) {
        var filter = new UserEntity();
        var idType = new IdentificationTypeEntity();
        idType.setId(userDomain.getIdentificationType().getId());
        filter.setIdentificationType(idType);
        filter.setIdentificationNumber(userDomain.getIdentificationNumber());

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(filter);
        for (var it : matches) {
            if (!it.getId().equals(userId)) {
                var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION.getContent();
                var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION.getContent();
                throw co.edu.uco.nose.crosscuting.exception.NoseException.create(userMessage, technicalMessage);
            }
        }
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
