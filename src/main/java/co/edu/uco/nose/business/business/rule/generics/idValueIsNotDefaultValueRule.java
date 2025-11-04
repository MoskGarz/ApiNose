package co.edu.uco.nose.business.business.rule.generics;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

public class idValueIsNotDefaultValueRule implements Rule{
    
    private static final Rule instance = new idValueIsNotDefaultValueRule();

    private idValueIsNotDefaultValueRule() {

    }

    public static void executeRule(final Object... data) {
        instance.execute(data);
    }


    @Override
    public void execute(Object... data) {

        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla idValueIsNotDefaultValueRule";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<2) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion";
            var technicalMessage = "Llegaron menos parametros de los requeridos para ejecutar la regla idValueIsNotDefaultValueRule";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var dataName = (String) data[1];

        if (UUIDHelper.getUUIDHelper().getDefault().equals(id)) {
            var userMessage = "El identificador de [".concat(dataName).concat("] es inválido");
            var technicalMessage = "idValueIsNotDefaultValueRule fallo: [" .concat(dataName).concat("] llegó con el UUID por defecto");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }

}
