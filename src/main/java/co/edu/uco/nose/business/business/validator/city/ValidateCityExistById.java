package co.edu.uco.nose.business.business.validator.city;

import co.edu.uco.nose.business.business.rule.city.CityExistsByIdRule;
import co.edu.uco.nose.business.business.validator.Validator;

public class ValidateCityExistById implements Validator {
    private static final Validator instance = new ValidateCityExistById();

    private ValidateCityExistById(){

    }

    public static void executeValidation(final Object... data){
        instance.validate(data);
    }

    @Override
    public void validate(final Object... data){
        CityExistsByIdRule.executeRule(data);
    }
}
