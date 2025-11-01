package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;

public class StringLengthIsValidRule implements Rule{

    private static final Rule instance = new StringLengthIsValidRule();

    private StringLengthIsValidRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){
    
        var min = (int) data[0];
        var max = (int) data[1];
        var stringData = (String) data[2];
        var dataName = (String) data[3];
        var mustApplyTrim = (boolean) data[4];

        if (TextHelper.isLenghtValid(stringData, min, max, mustApplyTrim)) {
            var userMessage = "El dato [".concat(dataName).concat("]tiene que tener una longitud entre ").concat(String.valueOf(min)).concat(" y ").concat(String.valueOf(max)).concat("para poder llevar a cabo la operacion");
            var technicalMessage = "El dato [".concat(dataName).concat("no cumple la regla StringLenghtIsValidRule");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}
