package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.user.UserMobileNumberDoesNotExistRule;
import co.edu.uco.nose.business.business.validator.Validator;

public class ValidateUserMobileNumberDoesNotExist implements Validator{
    private static final Validator instance = new ValidateUserMobileNumberDoesNotExist();

    private ValidateUserMobileNumberDoesNotExist(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){
        UserMobileNumberDoesNotExistRule.executeRule(data);
    }
}
