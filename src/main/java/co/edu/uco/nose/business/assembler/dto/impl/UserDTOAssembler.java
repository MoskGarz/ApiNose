package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler.getCityDTOAssembler;
import static co.edu.uco.nose.business.assembler.dto.impl.IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler;
import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;

public final class UserDTOAssembler implements DTOAssembler<UserDTO, UserDomain> {

    private static final DTOAssembler<UserDTO, UserDomain> INSTANCE = new UserDTOAssembler();

    private UserDTOAssembler() {
    }

    public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public UserDTO toDTO(final UserDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, UserDomain.getDefaultObject());
        var identificationTypeDTO = getIdentificationTypeDTOAssembler().toDTO(domainTmp.getIdentificationType());
        var cityDTO = getCityDTOAssembler().toDTO(domainTmp.getCity());
        return new UserDTO(
                domainTmp.getId(),
                identificationTypeDTO,
                domainTmp.getIdentificationNumber(),
                domainTmp.getFirstName(),
                domainTmp.getSecondName(),
                domainTmp.getFirstLastName(),
                domainTmp.getSecondLastName(),
                cityDTO,
                domainTmp.getEmail(),
                domainTmp.getPhoneNumber(),
                domainTmp.getEmailVerified(),
                domainTmp.getPhoneNumberVerified()
        );
    }

    @Override
    public UserDomain toDomain(final UserDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, UserDTO.getDefaultObject());
        var identificationTypeDomain = getIdentificationTypeDTOAssembler().toDomain(dtoTmp.getIdentificationType());
        var cityDomain = getCityDTOAssembler().toDomain(dtoTmp.getCity());
        return new UserDomain(
                dtoTmp.getId(),
                identificationTypeDomain,
                dtoTmp.getIdentificationNumber(),
                dtoTmp.getFirstName(),
                dtoTmp.getSecondName(),
                dtoTmp.getFirstLastname(),
                dtoTmp.getSecondLastname(),
                cityDomain,
                dtoTmp.getEmail(),
                dtoTmp.getPhoneNumber(),
                dtoTmp.getEmailVerified(),
                dtoTmp.getPhoneNumberVerified()
        );
    }

    @Override
    public List<UserDTO> toDTOList(List<UserDomain> domainList) {
        var userDTOList = new ArrayList<UserDTO>();

        for (var userDomain : domainList){
            userDTOList.add(toDTO(userDomain));
        }

        return userDTOList;
    }

    @Override
    public List<UserDomain> toDomainList(List<UserDTO> dtoList) {
        var userDomainList = new ArrayList<UserDomain>();

        for (var userDTO : dtoList){
            userDomainList.add(toDomain(userDTO));
        }

        return userDomainList;
    }
}
