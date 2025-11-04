package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class StringLengthIsValidRule implements Rule{

    private static final Rule instance = new StringLengthIsValidRule();

    private StringLengthIsValidRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_RULE_NULL_PARAMS.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().concat(" [StringLengthIsValidRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<5) {
            var userMessage = MessagesEnum.USER_ERROR_RULE_MISSING_PARAMS.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().concat( " [StringLengthIsValidRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }
    
        int min = (Integer) data[0];
        int max = (Integer) data[1];
        var stringData = (String) data[2];
        var dataName = (String) data[3];
        var mustApplyTrim = (boolean) data[4];

        if (!TextHelper.isLenghtValid(stringData, min, max, mustApplyTrim)) {
            var userMessage = MessagesEnum.USER_ERROR_STRING_LENGTH_INVALID.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_STRING_LENGTH_INVALID.getContent().concat(" [StringLengthIsValidRule] dato=" ).concat( dataName ).concat(", rango=[" ).concat(String.valueOf(min)).concat(",").concat( String.valueOf(max)).concat("]");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}
