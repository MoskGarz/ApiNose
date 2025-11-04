package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public final class StringValueIsPresentRule implements Rule{

    private static final Rule instance = new StringValueIsPresentRule();

    private StringValueIsPresentRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().replace("regla/validador", "regla [StringValueIsPresentRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<3) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().replace("regla/validador", "regla [StringValueIsPresentRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        boolean mustApplyTrim = (Boolean) data[2];

        if(Boolean.TRUE.equals((mustApplyTrim)
            ?TextHelper.isEmptyWithTrim(stringData)
                :TextHelper.isEmpty(stringData))) {
            
            var userMessage = MessagesEnum.USER_ERROR_REQUIRED_FIELD_MISSING.getContent().replace("variable", dataName);
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_REQUIRED_FIELD_MISSING.getContent().replace("variable", dataName);
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}
