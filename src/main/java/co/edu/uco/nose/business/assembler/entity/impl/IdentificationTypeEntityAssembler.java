package co.edu.uco.nose.business.assembler.entity.impl;

import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.entity.IdentificationTypeEntity;

public final class IdentificationTypeEntityAssembler implements EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> {

    private static final EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> INSTANCE = new IdentificationTypeEntityAssembler();
    public static EntityAssembler<IdentificationTypeEntity, IdentificationTypeDomain> getIdentificationTypeEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public IdentificationTypeEntity toEntity(final IdentificationTypeDomain domain) {
        // coco
        return null;
    }

    @Override
    public IdentificationTypeDomain toDomain(final IdentificationTypeEntity entity) {
        // coco
        return null;
    }
}
