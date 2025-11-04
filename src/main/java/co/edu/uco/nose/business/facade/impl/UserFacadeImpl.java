package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.business.impl.UserBusinessImpl;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public final class UserFacadeImpl implements UserFacade {

    @Override
    public void registerNewUser(UserDTO userDTO) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.innitTransaction();

            var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDTO);
            business.registerNewUser(domain);

            daoFactory.commitTransaction();

        } catch (final NoseException exception){
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception){
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void dropUser(UUID userId) {

         
        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);

        try {

            daoFactory.innitTransaction();
            business.dropUser(userId);
            daoFactory.commitTransaction();

        } catch (final NoseException exception){
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception){
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void updateUserInformation(UUID userid, UserDTO userDTO) {
        
        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);
        var userDomain = UserDTOAssembler.getUserDTOAssembler().toDomain(userDTO);

        try {

            daoFactory.innitTransaction();
            business.updateUserInformation(userid, userDomain);
            daoFactory.commitTransaction();

        } catch (final NoseException exception){
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (final Exception exception){
            daoFactory.rollbackTransaction();

            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UserDTO> findAllUsers() {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);
        

        try {

            daoFactory.innitTransaction();
            List<UserDomain> domainList = business.findAllUsers();
            return UserDTOAssembler.getUserDTOAssembler().toDTOList(domainList);

        } catch (final NoseException exception){

            throw exception;
        } catch (final Exception exception){

            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UserDTO> findUsersByFilter(UserDTO userFilters) {
        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);
        var domain = UserDTOAssembler.getUserDTOAssembler().toDomain(userFilters);
        

        try {

            daoFactory.innitTransaction();
            List<UserDomain> resultsList = business.findUsersByFilter(domain);
            return UserDTOAssembler.getUserDTOAssembler().toDTOList(resultsList);

        } catch (final NoseException exception){

            throw exception;
        } catch (final Exception exception){

            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UserDTO findSpecificUser(UUID userId) {

        var daoFactory = DAOFactory.getFactory();
        var business = new UserBusinessImpl(daoFactory);
        

        try {

            daoFactory.innitTransaction();
            UserDomain domain = business.findSpecificUser(userId);
            return UserDTOAssembler.getUserDTOAssembler().toDTO(domain);

        } catch (final NoseException exception){

            throw exception;
        } catch (final Exception exception){

            var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_PERSISTENCE_UNEXPECTED.getContent();

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void confirmMobileNumber(UUID userId, int confirmationCode){
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
