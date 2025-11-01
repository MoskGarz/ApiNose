package co.edu.uco.nose.business.business.rule.generics;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;

public final class StringValueIsPresentRule implements Rule{

    private static final Rule instance = new StringValueIsPresentRule();

    private StringValueIsPresentRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){
        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<3) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion";
            var technicalMessage = "Llegaron menos parametros de los requeridos para ejecutar la regla StringValueIsPresentRule";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var stringData = (String) data[0];
        var dataName = (String) data[1];
        var mustApplyTrim = (boolean) data[2];

        if((mustApplyTrim)
            ?TextHelper.isEmptyWithTrim(stringData)
                :TextHelper.isEmpty(stringData)) {
            
            var userMessage = "el dato [".concat(dataName).concat("] es requerido para llevar a cabo la operacion");
            var technicalMessage = "La regla StringValueIsPresentRule fallo porque el dato [".concat(dataName).concat("] era requerido y no llegó");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}
