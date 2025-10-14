package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

import java.util.UUID;

public final class CityEntity {
    private UUID id;
    private String name;
    private StateEntity state;
    public CityEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setName(TextHelper.getDefault());
        setState(new StateEntity());
    }
    public CityEntity(final UUID id) {
        setId(id);
        setName(TextHelper.getDefault());
        setState(new StateEntity());
    }
    public CityEntity(final UUID id, final String name, final StateEntity state) {
        setId(id);
        setName(name);
        setState(state);
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
    public StateEntity getState() {
        return state;
    }
    public void setState(final StateEntity state) {
        this.state = ObjectHelper.getDefault(state, new StateEntity());
    }
}
