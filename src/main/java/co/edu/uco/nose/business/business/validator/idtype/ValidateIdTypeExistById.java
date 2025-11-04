package co.edu.uco.nose.business.business.validator.idtype;

import co.edu.uco.nose.business.business.rule.idtype.IdTypeExistsByIdRule;
import co.edu.uco.nose.business.business.validator.Validator;

public class ValidateIdTypeExistById implements Validator {

    private static final Validator instance = new ValidateIdTypeExistById();

    private ValidateIdTypeExistById(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){
        IdTypeExistsByIdRule.executeRule(data);
    }
    
}
