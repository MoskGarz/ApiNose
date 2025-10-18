package co.edu.uco.nose.business.assembler.dto.impl;


import static co.edu.uco.nose.business.assembler.dto.impl.StateDTOAssembler.getStateDTOAssembler;
import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.dto.CityDTO;

import java.util.List;

public final class CityDTOAssembler implements DTOAssembler<CityDTO, CityDomain> {

    private static final DTOAssembler<CityDTO, CityDomain> INSTANCE = new CityDTOAssembler();
    public static DTOAssembler<CityDTO, CityDomain> getCityDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public CityDTO toDTO(final CityDomain domain) {
        //coco
        return null;
    }

    @Override
    public CityDomain toDomain(final CityDTO dto) {
        //coco
        return null;
    }

    @Override
    public List<CityDTO> toDTOList(List<CityDomain> domainList) {
        //coco
        return null;
    }

    @Override
    public List<CityDomain> toDomainList(List<CityDTO> dtoList) {
        return null;
    }
}
