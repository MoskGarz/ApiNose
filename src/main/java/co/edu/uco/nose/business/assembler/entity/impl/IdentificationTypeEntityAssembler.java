package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.entity.IdentificationTypeEntity;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import java.util.ArrayList;
import java.util.List;

public final class IdentificationTypeEntityAssembler implements EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> {

    private static final EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> INSTANCE = new IdentificationTypeEntityAssembler();
    public static EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> getIdentificationTypeEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public IdentificationTypeEntity toEntity(final IdentificationTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, IdentificationTypeDomain.getDefaultObject());
        return new IdentificationTypeEntity(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public IdentificationTypeDomain toDomain(final IdentificationTypeEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new IdentificationTypeEntity());
        return new IdentificationTypeDomain(entityTmp.getName(), entityTmp.getId());
    }

    @Override
    public List<IdentificationTypeEntity> toEntityList(final List<IdentificationTypeDomain> domainList) {
        var list = new ArrayList<IdentificationTypeEntity>();
        if (domainList == null) return list;
        for (var domain : domainList) {
            list.add(toEntity(domain));
        }
        return list;
    }

    @Override
    public List<IdentificationTypeDomain> toDomainList(final List<IdentificationTypeEntity> entityList) {
        var list = new ArrayList<IdentificationTypeDomain>();
        if (entityList == null) return list;
        for (var entity : entityList) {
            list.add(toDomain(entity));
        }
        return list;
    }
}
