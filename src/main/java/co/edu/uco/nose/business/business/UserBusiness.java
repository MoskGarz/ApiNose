package co.edu.uco.nose.business.business;

import co.edu.uco.nose.business.domain.UserDomain;

import java.util.List;
import java.util.UUID;

public interface UserBusiness {

    void registerNewUser(UserDomain userDomain);
    void dropUser(UUID userId);
    void updateUserInformation(UUID userid, UserDomain userDomain);
    List<UserDomain> findAllUsers();
    List<UserDomain> findUsersByFilter(UserDomain userFilters);
    UserDomain findSpecificUser(UUID userId);
    void confirmMobileNumber(UUID userId, int confirmationCode);
    void confirmEmail(UUID userid, int confirmationCode);
    void sendMobileNumberConfirmation(UUID userId);
    void sendEmailConfirmation(UUID userId);
}
