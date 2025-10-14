package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

import java.util.UUID;

public final class UserEntity {
    private UUID id;
    private IdentificationTypeEntity identificationType;
    private String identificationNumber;
    private String firstName;
    private String secondName;
    private String firstLastname;
    private String secondLastname;
    private CityEntity city;
    private String email;
    private String phoneNumber;
    private Boolean emailVerified;
    private Boolean phoneNumberVerified;
    private Boolean isEmailVerifiedDefaultValue;
    private Boolean isPhoneNumberVerifiedDefaultValue;

    public UserEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationType(new IdentificationTypeEntity());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastname(TextHelper.getDefault());
        setSecondLastname(TextHelper.getDefault());
        setCity(new CityEntity());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailVerified(false);
        setPhoneNumberVerified(false);
        setIsEmailVerifiedDefaultValue(true);
        setIsPhoneNumberVerifiedDefaultValue(true);
    }

    public UserEntity(final UUID id) {
        setId(id);
        setIdentificationType(new IdentificationTypeEntity());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastname(TextHelper.getDefault());
        setSecondLastname(TextHelper.getDefault());
        setCity(new CityEntity());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailVerified(false);
        setPhoneNumberVerified(false);
        setIsEmailVerifiedDefaultValue(true);
        setIsPhoneNumberVerifiedDefaultValue(true);
    }

    public UserEntity(final UUID id, final IdentificationTypeEntity identificationType, final String identificationNumber, final String firstName,
                      final String secondName, final String firstLastname, final String secondLastname, final CityEntity city, final String email,
                      final String phoneNumber, final Boolean emailVerified, final Boolean phoneNumberVerified) {
        setId(id);
        setIdentificationType(identificationType);
        setIdentificationNumber(identificationNumber);
        setFirstName(firstName);
        setSecondName(secondName);
        setFirstLastname(firstLastname);
        setSecondLastname(secondLastname);
        setCity(city);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setEmailVerified(emailVerified);
        setPhoneNumberVerified(phoneNumberVerified);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public IdentificationTypeEntity getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(final IdentificationTypeEntity identificationType) {
        this.identificationType = ObjectHelper.getDefault(identificationType, new IdentificationTypeEntity());
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(final String identificationNumber) {
        this.identificationNumber = TextHelper.getDefaultWithTrim(identificationNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = TextHelper.getDefaultWithTrim(firstName);
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = TextHelper.getDefaultWithTrim(secondName);
    }

    public String getFirstLastname() {
        return firstLastname;
    }

    public void setFirstLastname(final String firstLastname) {
        this.firstLastname = TextHelper.getDefaultWithTrim(firstLastname);
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public void setSecondLastname(final String secondLastname) {
        this.secondLastname = TextHelper.getDefaultWithTrim(secondLastname);
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(final CityEntity city) {
        this.city = ObjectHelper.getDefault(city, new CityEntity());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = TextHelper.getDefaultWithTrim(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = TextHelper.getDefaultWithTrim(phoneNumber);
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(final Boolean emailVerified) {
        this.emailVerified = emailVerified;
        setIsEmailVerifiedDefaultValue(false);
    }

    public Boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(final Boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
        setIsPhoneNumberVerifiedDefaultValue(false);
    }

    public Boolean getIsEmailVerifiedDefaultValue() {
        return isEmailVerifiedDefaultValue;
    }
    private void setIsEmailVerifiedDefaultValue(final Boolean isEmailVerifiedDefaultValue) {
        this.isEmailVerifiedDefaultValue = isEmailVerifiedDefaultValue;
    }
    public Boolean getIsPhoneNumberVerifiedDefaultValue() {
        return isPhoneNumberVerifiedDefaultValue;
    }
    private void setIsPhoneNumberVerifiedDefaultValue(final Boolean isPhoneNumberVerifiedDefaultValue) {
        this.isPhoneNumberVerifiedDefaultValue = isPhoneNumberVerifiedDefaultValue;
    }
}
