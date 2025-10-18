package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.CountryEntityAssembler.getCountryEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.entity.StateEntity;

public final class StateEntityAssembler implements EntityAssembler<StateEntity, StateDomain> {

    private static final EntityAssembler<StateEntity, StateDomain> INSTANCE = new StateEntityAssembler();
    public static EntityAssembler<StateEntity, StateDomain> getStateEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public StateEntity toEntity(final StateDomain domain) {
        // coco
        return null;
    }

    @Override
    public StateDomain toDomain(final StateEntity entity) {
        // coco
        return null;
    }
}
