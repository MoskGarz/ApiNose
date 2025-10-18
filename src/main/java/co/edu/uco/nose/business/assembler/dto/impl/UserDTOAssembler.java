package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler.getCityDTOAssembler;
import static co.edu.uco.nose.business.assembler.dto.impl.IdentificationTypeDTOAssembler.getIdentificationTypeDTOAssembler;
import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public final class UserDTOAssembler implements DTOAssembler<UserDTO, UserDomain> {

    private static final DTOAssembler<UserDTO, UserDomain> INSTANCE = new UserDTOAssembler();

    private UserDTOAssembler() {
    }

    public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public UserDTO toDTO(final UserDomain domain) {
        //coco
        return null;
    }

    @Override
    public UserDomain toDomain(final UserDTO dto) {
        //coco
        return null;
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
