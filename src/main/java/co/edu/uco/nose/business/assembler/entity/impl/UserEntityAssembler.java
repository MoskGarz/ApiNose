package co.edu.uco.nose.business.assembler.entity.impl;

import static co.edu.uco.nose.business.assembler.entity.impl.IdentificationTypeEntityAssembler.getIdentificationTypeEntityAssembler;
import static co.edu.uco.nose.business.assembler.entity.impl.CityEntityAssembler.getCityEntityAssembler;
import co.edu.uco.nose.business.assembler.entity.EntityAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;

public final class UserEntityAssembler implements EntityAssembler<UserEntity, UserDomain> {

    private static final EntityAssembler<UserEntity, UserDomain> INSTANCE = new UserEntityAssembler();
    public static EntityAssembler<UserEntity, UserDomain> getUserEntityAssembler() {
        return INSTANCE;
    }
    @Override
    public UserEntity toEntity(final UserDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, UserDomain.getDefaultObject());
        var identificationTypeEntity = getIdentificationTypeEntityAssembler().toEntity(domainTmp.getIdentificationType());
        var cityEntity = getCityEntityAssembler().toEntity(domainTmp.getCity());
        return new UserEntity(
                domainTmp.getId(),
                identificationTypeEntity,
                domainTmp.getIdentificationNumber(),
                domainTmp.getFirstName(),
                domainTmp.getSecondName(),
                domainTmp.getFirstLastName(),
                domainTmp.getSecondLastName(),
                cityEntity,
                domainTmp.getEmail(),
                domainTmp.getPhoneNumber(),
                domainTmp.getEmailVerified(),
                domainTmp.getPhoneNumberVerified()
        );
    }

    @Override
    public UserDomain toDomain(final UserEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, UserEntity.getDefaultObject());
        var identificationTypeDomain = getIdentificationTypeEntityAssembler().toDomain(entityTmp.getIdentificationType());
        var cityDomain = getCityEntityAssembler().toDomain(entityTmp.getCity());
        return new UserDomain(
                entityTmp.getId(),
                identificationTypeDomain,
                entityTmp.getIdentificationNumber(),
                entityTmp.getFirstName(),
                entityTmp.getSecondName(),
                entityTmp.getFirstLastname(),
                entityTmp.getSecondLastname(),
                cityDomain,
                entityTmp.getEmail(),
                entityTmp.getPhoneNumber(),
                entityTmp.getEmailVerified(),
                entityTmp.getPhoneNumberVerified()
        );
    }

    @Override
    public List<UserEntity> toEntityList(final List<UserDomain> domainList) {
        var list = new ArrayList<UserEntity>();
        if (domainList == null) return list;
        for (var domain : domainList) {
            list.add(toEntity(domain));
        }
        return list;
    }

    @Override
    public List<UserDomain> toDomainList(final List<UserEntity> entityList) {
        var list = new ArrayList<UserDomain>();
        if (entityList == null) return list;
        for (var entity : entityList) {
            list.add(toDomain(entity));
        }
        return list;
    }
}
