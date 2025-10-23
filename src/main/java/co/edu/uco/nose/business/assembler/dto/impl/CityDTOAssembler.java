package co.edu.uco.nose.business.assembler.dto.impl;


import static co.edu.uco.nose.business.assembler.dto.impl.StateDTOAssembler.getStateDTOAssembler;
import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CityDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.dto.CityDTO;

import java.util.List;
import java.util.ArrayList;

public final class CityDTOAssembler implements DTOAssembler<CityDTO, CityDomain> {

    private static final DTOAssembler<CityDTO, CityDomain> INSTANCE = new CityDTOAssembler();
    public static DTOAssembler<CityDTO, CityDomain> getCityDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public CityDTO toDTO(final CityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, CityDomain.getDefaultObject());
        var stateDto = getStateDTOAssembler().toDTO(domainTmp.getState());
        return new CityDTO(domainTmp.getId(), domainTmp.getName(), stateDto);
    }

    @Override
    public CityDomain toDomain(final CityDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, CityDTO.getDefaultObject());
        var stateDomain = getStateDTOAssembler().toDomain(dtoTmp.getState());
        return new CityDomain(dtoTmp.getId(), dtoTmp.getName(), stateDomain);
    }

    @Override
    public List<CityDTO> toDTOList(List<CityDomain> domainList) {
        var dtoList = new ArrayList<CityDTO>();
        for (var domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }

    @Override
    public List<CityDomain> toDomainList(List<CityDTO> dtoList) {
        var domainList = new ArrayList<CityDomain>();
        for (var dto : dtoList) {
            domainList.add(toDomain(dto));
        }
        return domainList;
    }
}
