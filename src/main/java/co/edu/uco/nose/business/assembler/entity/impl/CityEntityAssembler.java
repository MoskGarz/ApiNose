package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.StateEntityAssembler.getStateEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.entity.CityEntity;

public final class CityEntityAssembler implements EntityAssembler<CityEntity, CityDomain> {
    private static final EntityAssembler<CityEntity, CityDomain> INSTANCE = new CityEntityAssembler();
    public static EntityAssembler<CityEntity, CityDomain> getCityEntityAssembler() {
        return INSTANCE;
    }

    @Override
    public CityEntity toEntity(final CityDomain domain) {
        // coco
        return null;
    }

    @Override
    public CityDomain toDomain(final CityEntity entity) {
        // coco
        return null;
    }
}
