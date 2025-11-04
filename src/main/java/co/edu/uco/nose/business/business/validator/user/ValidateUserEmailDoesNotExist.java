package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.user.UserEmailDoesNotExistRule;
import co.edu.uco.nose.business.business.validator.Validator;

public class ValidateUserEmailDoesNotExist implements Validator{
    private static final Validator instance = new ValidateUserEmailDoesNotExist();

    private ValidateUserEmailDoesNotExist(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){
        UserEmailDoesNotExistRule.executeRule(data);
    }
}
