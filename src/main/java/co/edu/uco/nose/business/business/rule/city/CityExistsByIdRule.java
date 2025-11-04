package co.edu.uco.nose.business.business.rule.city;

import java.util.UUID;

import co.edu.uco.nose.business.business.rule.Rule;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.data.dao.factory.DAOFactory;
import co.edu.uco.nose.entity.CityEntity;

public class CityExistsByIdRule implements Rule {

    private static final Rule instance = new CityExistsByIdRule();

    private CityExistsByIdRule(){

    }

    public static void executeRule(final Object... data){
        instance.execute(data);
    }

    @Override
    public void execute(final Object... data){

        if (ObjectHelper.isNull(data)) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_NULL_PARAMS.getContent().replace("regla/validador", "regla [CityExistsByIdRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        if (data.length<2) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED_RULE_ERROR.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_RULE_MISSING_PARAMS.getContent().replace("regla/validador", "regla [CityExistsByIdRule]");
            throw NoseException.create(userMessage, technicalMessage);
        }

        var id = (UUID) data[0];
        var daoFactory = (DAOFactory) data[1];

        CityEntity idType = daoFactory.getCityDAO().findById(id);

        if(UUIDHelper.getUUIDHelper().isDefaultUUID(idType.getId())){
            var userMessage = MessagesEnum.USER_ERROR_CITY_NOT_FOUND.getContent();
            var technicalMessage = MessagesEnum.TECHNICAL_ERROR_CITY_NOT_FOUND.getContent();
            throw NoseException.create(userMessage, technicalMessage);
        }
    }
    
}
