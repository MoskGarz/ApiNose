package co.edu.uco.nose.business.facade;

import co.edu.uco.nose.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserFacade {
    void registerNewUser(UserDTO userDTO);
    void dropUser(UUID userId);
    void updateUserInformation(UUID userid, UserDTO userDTO);
    List<UserDTO> findAllUsers();
    List<UserDTO> findUsersByFilter(UserDTO userFilters);
    UserDTO findSpecificUser(UUID userId);
    void confirmMobileNumber(UUID userId, int confirmationCode);
    void confirmEmail(UUID userid, int confirmationCode);
    void sendMobileNumberConfirmation(UUID userId);
    void sendEmailConfirmation(UUID userId);
}

