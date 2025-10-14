package co.edu.uco.nose.dto;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;



public final class CountryDTO {
    private UUID id;
    private String name;
    public static final CountryDTO DEFAULT = new CountryDTO();
    public static CountryDTO getDefaultObject() {
        return DEFAULT;
    }

    public CountryDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }

    public CountryDTO(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
    }

    public CountryDTO(final UUID id, final String name) {
        setId(id);
        setName(name);
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


}
