package co.edu.uco.nose.business.facade.impl;

import co.edu.uco.nose.business.assembler.dto.impl.UserDTOAssembler;
import co.edu.uco.nose.business.business.impl.UserBusinessImpl;
import co.edu.uco.nose.business.facade.UserFacade;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
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

            var userMessage = "";
            var technicalMessage = "";

            throw NoseException.create(exception, userMessage, technicalMessage);

        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void dropUser(UUID userId) {

    }

    @Override
    public void updateUserInformation(UUID userid, UserDTO userDTO) {

    }

    @Override
    public List<UserDTO> findAllUsers() {
        return null;
    }

    @Override
    public List<UserDTO> findUsersByFilter(UserDTO userFilters) {
        return null;
    }

    @Override
    public UserDTO findSpecificUser(UUID userId) {
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
