package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

import java.util.UUID;

public final class StateEntity {
    private UUID id;
    private String name;
    private CountryEntity country;

    public StateEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
        setCountry(new CountryEntity());
    }

    public StateEntity(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
        setCountry(new CountryEntity());
    }

    public StateEntity(final UUID id, final String name, final CountryEntity country) {
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(final CountryEntity country) {
        this.country = ObjectHelper.getDefault(country, new CountryEntity());
    }
}
