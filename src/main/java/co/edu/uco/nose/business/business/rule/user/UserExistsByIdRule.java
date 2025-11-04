package co.edu.uco.nose.business.business.rule.user;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.UserEntity;

public class UserExistsByIdRule implements Rule {

    private static final Rule instance = new UserExistsByIdRule();

    private UserExistsByIdRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){

        if (ObjectHelper.isNull(data)) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion";
            var technicalMessage = "No se recibieron los parametros requeridos para ejecutar la regla UserExistsByIdRule";
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<2) {
            var userMessage = "Se ha presentado un problema inesperado tratando de llevar a cabo la operacion";
            var technicalMessage = "Llegaron menos parametros de los requeridos para ejecutar la regla UserExistsByIdRule";
            throw NoseException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        UserEntity idType = daoFactory.getUserDAO().findById(id);

        if(UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getId())){
            var userMessage = "El usuario deseado no existe.";
            var technicalMessage = "El usuario con id [".concat(id.toString()).concat("] no existe.");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}