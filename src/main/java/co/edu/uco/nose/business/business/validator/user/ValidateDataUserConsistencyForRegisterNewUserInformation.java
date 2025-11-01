package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.generics.StringLengthIsValidRule;
import co.edu.uco.nose.business.business.rule.generics.StringValueIsPresentRule;
import co.edu.uco.nose.business.business.validator.Validator;
import co.edu.uco.nose.business.domain.UserDomain;

public class ValidateDataUserConsistencyForRegisterNewUserInformation implements Validator {
    
    private static final Validator instance = new ValidateDataUserConsistencyForRegisterNewUserInformation();

    private ValidateDataUserConsistencyForRegisterNewUserInformation(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){

        //validaciones del objeto data
        var userDomainData = (UserDomain) data[0];

        //valid empty data
        validateEmptyData(userDomainData);

        //valid data lenght
        validateDataLenght(userDomainData);

        //valid data format
        //valid data valid range
    }

    private void validateEmptyData(final UserDomain data){
    

        StringValueIsPresentRule.executeRule(data.getIdentificationNumber(), "numero de identificación", true);
        StringValueIsPresentRule.executeRule(data.getFirstName(), "numero de identificación", true);
        StringValueIsPresentRule.executeRule(data.getFirstLastName(), "numero de identificación", true);
    }

    private void validateDataLenght(final UserDomain data){
        StringLengthIsValidRule.executeRule(data.getIdentificationNumber(), "numero de identificación", true);
        StringLengthIsValidRule.executeRule(data.getFirstName(), "numero de identificación", true);
        StringLengthIsValidRule.executeRule(data.getFirstLastName(), "numero de identificación", true);
    }
}
