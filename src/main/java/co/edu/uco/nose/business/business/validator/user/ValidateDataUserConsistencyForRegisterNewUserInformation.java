package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.generics.StringFormatValueIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringLengthIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.business.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;

public class ValidateDataUserConsistencyForRegisterNewUserInformation implements Validator {
    
    private static final Validator instance = new ValidateDataUserConsistencyForRegisterNewUserInformation();

    private ValidateDataUserConsistencyForRegisterNewUserInformation(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){
        
        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().replace("la regla/validador", "el validador [ValidateDataUserConsistencyForRegisterNewUserInformation]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<1) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().replace("la regla/validador", "el validador [ValidateDataUserConsistencyForRegisterNewUserInformation]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var userDomainData = (UserDomain) data[0];
        validateEmptyData(userDomainData);
        validateDataLenght(userDomainData);
        validateDataFormat(userDomainData);
    }

    private void validateEmptyData(final UserDomain data){
        StringValueIsPresentRule.executeRule(data.getIdentificationNumber(), "numero de identificación", true);
        StringValueIsPresentRule.executeRule(data.getFirstName(), "primer nombre", true);
        StringValueIsPresentRule.executeRule(data.getFirstLastName(), "primer apellido", true);
        StringValueIsPresentRule.executeRule(data.getEmail(), "email", true);
        StringValueIsPresentRule.executeRule(data.getPhoneNumber(), "numero de telefono", true);
    }

    private void validateDataLenght(final UserDomain data){
        StringLengthIsValidRule.executeRule(1, 50, data.getIdentificationNumber(), "numero de identificación", true);
        StringLengthIsValidRule.executeRule(1, 100, data.getFirstName(), "primer nombre", true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondName())) {
            StringLengthIsValidRule.executeRule(1, 100, data.getSecondName(), "segundo nombre", true);
        }

        StringLengthIsValidRule.executeRule(1, 100, data.getFirstLastName(), "primer apellido", true);

        if (!TextHelper.isEmptyWithTrim(data.getSecondLastName())) {
            StringLengthIsValidRule.executeRule(1, 100, data.getSecondLastName(), "segundo apellido", true);
        }

        StringLengthIsValidRule.executeRule(5, 100, data.getEmail(), "email", true);
        StringLengthIsValidRule.executeRule(7, 10, data.getPhoneNumber(), "numero de telefono", true);
    }

    private void validateDataFormat(final UserDomain data){

        final String lettersPattern = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'\\-\\s]+$";
        final String digitsPattern = "^[0-9]+$";
        final String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

        StringFormatValueIsValidRule.executeRule(digitsPattern, data.getIdentificationNumber(), "numero de identificacion", true, false );
        StringFormatValueIsValidRule.executeRule(lettersPattern, data.getFirstName(), "primer nombre", true, false );
        StringFormatValueIsValidRule.executeRule(lettersPattern, data.getSecondName(), "segundo nombre", true, true );
        StringFormatValueIsValidRule.executeRule(lettersPattern, data.getFirstLastName(), "primer apellido", true, false );
        StringFormatValueIsValidRule.executeRule(lettersPattern, data.getSecondLastName(), "segundo apellido", true, true );
        StringFormatValueIsValidRule.executeRule(digitsPattern, data.getPhoneNumber(), "numero de telefono", true, false );
        StringFormatValueIsValidRule.executeRule(emailPattern, data.getEmail(), "email", true, false);

    }
}
