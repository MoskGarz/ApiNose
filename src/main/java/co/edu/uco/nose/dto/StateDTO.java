package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

public final class StateDTO {

    private UUID id;
    private String name;
    private CountryDTO country;

    public static final StateDTO DEFAULT = new StateDTO();

    public static StateDTO getDefaultObject() {
        return DEFAULT;
    }

    public StateDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
        setCountry(CountryDTO.getDefaultObject());
    }

    public StateDTO(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
        setCountry(CountryDTO.getDefaultObject());
    }

    public StateDTO(final UUID id, final String name, final CountryDTO country) {
        setId(id);
        setName(name);
        setCountry(country);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(final CountryDTO country) {
        this.country = ObjectHelper.getDefault(country, CountryDTO.getDefaultObject());
    }

}
