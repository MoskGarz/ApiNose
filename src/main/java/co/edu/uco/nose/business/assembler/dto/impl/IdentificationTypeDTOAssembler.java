package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.dto.IdentificationTypeDTO;

import java.util.List;
import java.util.ArrayList;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;

public final class IdentificationTypeDTOAssembler implements DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> {

    private static final DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> INSTANCE = new IdentificationTypeDTOAssembler();

    private IdentificationTypeDTOAssembler() {
    }

    public static DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> getIdentificationTypeDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public IdentificationTypeDTO toDTO(final IdentificationTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, IdentificationTypeDomain.getDefaultObject());
        return new IdentificationTypeDTO(domainTmp.getId(), domainTmp.getName());
    }

    @Override
    public IdentificationTypeDomain toDomain(final IdentificationTypeDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, IdentificationTypeDTO.getDefaultObject());
        return new IdentificationTypeDomain(dtoTmp.getName(), dtoTmp.getId());
    }

    @Override
    public List<IdentificationTypeDTO> toDTOList(List<IdentificationTypeDomain> domainList) {
        var dtoList = new ArrayList<IdentificationTypeDTO>();
        for (var domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }

    @Override
    public List<IdentificationTypeDomain> toDomainList(List<IdentificationTypeDTO> dtoList) {
        var domainList = new ArrayList<IdentificationTypeDomain>();
        for (var dto : dtoList) {
            domainList.add(toDomain(dto));
        }
        return domainList;
    }
}
