package co.edu.uco.nose.business.business.rule.user;

import java.util.List;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.entity.UserEntity;

public class UserDoesNotExistWithSameIdNumberAndIdTypeRule implements Rule{
    
    private static final Rule instance = new UserDoesNotExistWithSameIdNumberAndIdTypeRule();

    private UserDoesNotExistWithSameIdNumberAndIdTypeRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().replace("regla/validador", "regla [UserDoesNotExistWithSameIdNumberAndIdTypeRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<3) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().replace("regla/validador", "regla [UserDoesNotExistWithSameIdNumberAndIdTypeRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var idType = (IdentificationTypeEntity) data[0];
        var idNumber = (String) data[1];
        var daoFactory = (DAOFactory) data[2];

        UserEntity userFilter = new UserEntity();
        userFilter.setIdentificationType(idType);
        userFilter.setIdentificationNumber(idNumber);

        List<UserEntity> matches = daoFactory.getUserDAO().findByFilter(userFilter);
        if (!matches.isEmpty()) {
            var userMessage = MessagesEnum.USER_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_VALIDATION_DUPLICATED_IDENTIFICATION.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }   
    }
}
