package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler;
import static co.edu.uco.nose.business.assembler.entity.impl.CityEntityAssembler.getCityEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.entity.UserEntity;

public final class UserEntityAssembler implements EntityAssembler<UserEntity, UserDomain> {

    private static final EntityAssembler<UserEntity, UserDomain> INSTANCE = new UserEntityAssembler();
    public static EntityAssembler<UserEntity, UserDomain> getUserEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public UserEntity toEntity(final UserDomain domain) {
        // coco
        return null;
    }

    @Override
    public UserDomain toDomain(final UserEntity entity) {
        // coco
        return null;
    }
}
