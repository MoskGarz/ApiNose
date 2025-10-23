package co.edu.uco.nose.business.assembler.dto.impl;

import static co.edu.uco.nose.business.assembler.dto.impl.CountryDTOAssembler.getCountryDTOAssembler;
import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.StateDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.dto.StateDTO;

import java.util.List;
import java.util.ArrayList;

public final class StateDTOAssembler implements DTOAssembler<StateDTO, StateDomain> {

    private static final DTOAssembler<StateDTO, StateDomain> INSTANCE = new StateDTOAssembler();
    public static DTOAssembler<StateDTO, StateDomain> getStateDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public StateDTO toDTO(final StateDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, StateDomain.getDefaultObject());
        var countryDtoTmp = getCountryDTOAssembler().toDTO(domainTmp.getCountry());
        return new StateDTO(domainTmp.getId(), domainTmp.getName(), countryDtoTmp);
    }

    @Override
    public StateDomain toDomain(final StateDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, StateDTO.getDefaultObject());
        var countryDomainTmp = getCountryDTOAssembler().toDomain(dtoTmp.getCountry());
        return new StateDomain(dtoTmp.getId(), dtoTmp.getName(), countryDomainTmp);
    }

    @Override
    public List<StateDTO> toDTOList(List<StateDomain> domainList) {
        var dtoList = new ArrayList<StateDTO>();
        for (var domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }

    @Override
    public List<StateDomain> toDomainList(List<StateDTO> dtoList) {
        var domainList = new ArrayList<StateDomain>();
        for (var dto : dtoList) {
            domainList.add(toDomain(dto));
        }
        return domainList;
    }
}
