package co.edu.uco.nose.business.business.rule.user;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public class UserExistsById implements Rule {

    @Override
    public void execute(final Object... data){
        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        IdentificationTypeEntity idType = daoFactory.getIdentificationTypeDAO().findById(id);

        if(UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getId())){
            var userMessage = "El tipo de identificación deseado no existe.";
            var technicalMessage = "El tipo de identificacion con id [".concat(id.toString()).concat("] no existe.");
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}