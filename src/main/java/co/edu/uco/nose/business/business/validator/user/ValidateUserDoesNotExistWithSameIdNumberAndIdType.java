package co.edu.uco.nose.business.business.validator.user;

import co.edu.uco.nose.business.business.rule.user.UserDoesNotExistWithSameIdNumberAndIdTypeRule;
import co.edu.uco.nose.business.business.validator.Validator;

public class ValidateUserDoesNotExistWithSameIdNumberAndIdType implements Validator {
    private static final Validator instance = new ValidateUserDoesNotExistWithSameIdNumberAndIdType();

    private ValidateUserDoesNotExistWithSameIdNumberAndIdType(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){
        UserDoesNotExistWithSameIdNumberAndIdTypeRule.executeRule(data);
    }
    
}
