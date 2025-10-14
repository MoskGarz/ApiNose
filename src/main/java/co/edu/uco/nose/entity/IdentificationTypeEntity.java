package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

import java.util.UUID;

public final class IdentificationTypeEntity {
    private UUID id;
    private String name;

    public IdentificationTypeEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
    }
    public IdentificationTypeEntity(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
    }
    public IdentificationTypeEntity(final UUID id, final String name) {
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
