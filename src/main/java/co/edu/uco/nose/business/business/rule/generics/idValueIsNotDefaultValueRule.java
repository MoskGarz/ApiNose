package co.edu.uco.nose.business.business.rule.generics;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class idValueIsNotDefaultValueRule implements Rule{
    
    private static final Rule instance = new idValueIsNotDefaultValueRule();

    private idValueIsNotDefaultValueRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().replace("regla/validador", "regla [idValueIsNotDefaultValueRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<2) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().replace("regla/validador", "regla [idValueIsNotDefaultValueRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var dataName = (String) data[1];

        if (UUIDHelper.getUUIDHelper().getDefault().equals(id)) {
            var userMessage = MessagesEnum.USER_ERROR_DEFAULT_ID_DETECTED.getContent().replace("la variable", dataName);
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_DEFAULT_ID_DETECTED.getContent().replace("la variable", dataName); 
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

}
