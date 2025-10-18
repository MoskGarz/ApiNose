package co.edu.uco.nose.business.assembler.dto.impl;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.CountryDomain;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.dto.CountryDTO;

import java.util.ArrayList;
import java.util.List;

public final class CountryDTOAssembler implements DTOAssembler<CountryDTO, CountryDomain> {

    private static final DTOAssembler<CountryDTO, CountryDomain> INSTANCE = new CountryDTOAssembler();

    private CountryDTOAssembler() {
    }

    public static DTOAssembler<CountryDTO, CountryDomain> getCountryDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public CountryDTO toDTO(final CountryDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, CountryDomain.getDefaultObject());
        return new CountryDTO(domainTmp.getId(), domainTmp.getName());

    }

    @Override
    public CountryDomain toDomain(final CountryDTO dto) {
        var dtoTmp = ObjectHelper.getDefault(dto, CountryDTO.getDefaultObject());
        return new CountryDomain(dtoTmp.getId(), dtoTmp.getName());
    }

    @Override
    public List<CountryDTO> toDTOList(List<CountryDomain> domainList) {
        List<CountryDTO> dtoList = new ArrayList<CountryDTO>();
        for(CountryDomain domain : domainList){
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }

    @Override
    public List<CountryDomain> toDomainList(List<CountryDTO> dtoList) {
        List<CountryDomain> domainList = new ArrayList<CountryDomain>();
        for(CountryDTO dto : dtoList){
            domainList.add(toDomain(dto));
        }
        return domainList;
    }


}
