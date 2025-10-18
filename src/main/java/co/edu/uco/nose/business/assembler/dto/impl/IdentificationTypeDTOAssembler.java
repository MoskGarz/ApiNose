package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.IdentificationTypeDomain;
import co.edu.uco.nose.dto.IdentificationTypeDTO;

import java.util.List;

public final class IdentificationTypeDTOAssembler implements DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> {

    private static final DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> INSTANCE = new IdentificationTypeDTOAssembler();

    private IdentificationTypeDTOAssembler() {
    }

    public static DTOAssembler<IdentificationTypeDTO, IdentificationTypeDomain> getIdentificationTypeDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public IdentificationTypeDTO toDTO(final IdentificationTypeDomain domain) {
        //coco
        return null;
    }

    @Override
    public IdentificationTypeDomain toDomain(final IdentificationTypeDTO dto) {
        //coco
        return null;
    }

    @Override
    public List<IdentificationTypeDTO> toDTOList(List<IdentificationTypeDomain> domainList) {
        return null;
    }

    @Override
    public List<IdentificationTypeDomain> toDomainList(List<IdentificationTypeDTO> dtoList) {
        return null;
    }
}
