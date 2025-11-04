package co.edu.uco.nose.business.business.rule.user;

import java.util.List;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

public class UserMobileNumberDoesNotExistRule implements Rule{
    private static final Rule instance = new UserMobileNumberDoesNotExistRule();

    private UserMobileNumberDoesNotExistRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().replace("regla/validador", "regla [UserMobileNumberDoesNotExistRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<2) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().replace("regla/validador", "regla [UserMobileNumberDoesNotExistRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var mobileNumber = (String) data[0];
        var daoFactory = (DAOFactory) data[1];

        UserEntity userFilter = new UserEntity();
        userFilter.setPhoneNumber(mobileNumber);

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(userFilter);
        if (!matches.isEmpty()) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_PHONE.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_PHONE.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
}
