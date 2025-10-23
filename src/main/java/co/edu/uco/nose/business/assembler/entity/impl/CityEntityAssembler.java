package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.StateEntityAssembler.getStateEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.entity.CityEntity;
import java.util.ArrayList;
import java.util.List;

public final class CityEntityAssembler implements EntityAssembler<CityEntity, CityDomain> {
    private static final EntityAssembler<CityEntity, CityDomain> INSTANCE = new CityEntityAssembler();
    public static EntityAssembler<CityEntity, CityDomain> getCityEntityAssembler() {
        return INSTANCE;
    }

    @Override
    public CityEntity toEntity(final CityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, CityDomain.getDefaultObject());
        var stateEntity = getStateEntityAssembler().toEntity(domainTmp.getState());
        return new CityEntity(domainTmp.getId(), domainTmp.getName(), stateEntity);
    }

    @Override
    public CityDomain toDomain(final CityEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new CityEntity());
        var stateDomain = getStateEntityAssembler().toDomain(entityTmp.getState());
        return new CityDomain(entityTmp.getId(), entityTmp.getName(), stateDomain);
    }

    @Override
    public List<CityEntity> toEntityList(final List<CityDomain> domainList) {
        var list = new ArrayList<CityEntity>();
        if (domainList == null) return list;
        for (var domain : domainList) {
            list.add(toEntity(domain));
        }
        return list;
    }

    @Override
    public List<CityDomain> toDomainList(final List<CityEntity> entityList) {
        var list = new ArrayList<CityDomain>();
        if (entityList == null) return list;
        for (var entity : entityList) {
            list.add(toDomain(entity));
        }
        return list;
    }
}
