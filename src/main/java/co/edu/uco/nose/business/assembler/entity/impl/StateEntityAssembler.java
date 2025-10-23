package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.CountryEntityAssembler.getCountryEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.entity.StateEntity;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import java.util.ArrayList;
import java.util.List;

public final class StateEntityAssembler implements EntityAssembler<StateEntity, StateDomain> {

    private static final EntityAssembler<StateEntity, StateDomain> INSTANCE = new StateEntityAssembler();
    public static EntityAssembler<StateEntity, StateDomain> getStateEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public StateEntity toEntity(final StateDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, StateDomain.getDefaultObject());
        var countryEntity = getCountryEntityAssembler().toEntity(domainTmp.getCountry());
        return new StateEntity(domainTmp.getId(), domainTmp.getName(), countryEntity);
    }

    @Override
    public StateDomain toDomain(final StateEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new StateEntity());
        var countryDomain = getCountryEntityAssembler().toDomain(entityTmp.getCountry());
        return new StateDomain(entityTmp.getId(), entityTmp.getName(), countryDomain);
    }

    @Override
    public List<StateEntity> toEntityList(final List<StateDomain> domainList) {
        var list = new ArrayList<StateEntity>();
        if (domainList == null) return list;
        for (var domain : domainList) {
            list.add(toEntity(domain));
        }
        return list;
    }

    @Override
    public List<StateDomain> toDomainList(final List<StateEntity> entityList) {
        var list = new ArrayList<StateDomain>();
        if (entityList == null) return list;
        for (var entity : entityList) {
            list.add(toDomain(entity));
        }
        return list;
    }
}
