package co.edu.uco.nose.business.business.rule.generics;

import java.util.regex.Pattern;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class StringFormatValueIsValidRule implements Rule {

    private static final Rule instance = new StringFormatValueIsValidRule();

    private StringFormatValueIsValidRule() { }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_RULE_NULL_PARAMS.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().concat( " [StringFormatValueIsValidRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<5) {
            var userMessage = MessagesEnum.USER_ERROR_RULE_MISSING_PARAMS.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().concat( " [StringFormatValueIsValidRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var pattern = (String) data[0];
        var stringData = (String) data[1];
        var dataName = (String) data[2];
        var mustApplyTrim = (boolean) data[3];
        var isOptional = (boolean) data[4];

        var candidate = mustApplyTrim ? TextHelper.getDefaultWithTrim(stringData) : TextHelper.getDefault(stringData);

        if (isOptional && TextHelper.isEmpty(candidate)) {
            return; 
        }

        if (!Pattern.compile(pattern).matcher(candidate).matches()) {
            var userMessage = MessagesEnum.USER_ERROR_FORMAT_INVALID.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_FORMAT_INVALID.getContent().concat( " [StringFormatValueIsValidRule] dato=" ).concat( dataName).concat( ", patron=").concat(  pattern);
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

    
    
}
