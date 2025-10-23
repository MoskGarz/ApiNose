package co.edu.uco.nose.dto;

import java.util.UUID;
import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;


public class UserDTO {
    private UUID id;
    private IdentificationTypeDTO identificationType;
    private String identificationNumber;
    private String firstName;
    private String secondName;
    private String firstLastname;
    private String secondLastname;
    private CityDTO city;
    private String email;
    private String phoneNumber;
    private Boolean emailVerified;
    private Boolean phoneNumberVerified;

    
    public static final UserDTO DEFAULT = new UserDTO();

    public static UserDTO getDefaultObject() {
        return DEFAULT;
    }

    public UserDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationType(IdentificationTypeDTO.getDefaultObject());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastname(TextHelper.getDefault());
        setSecondLastname(TextHelper.getDefault());
        setCity(CityDTO.getDefaultObject());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailVerified(false);
        setPhoneNumberVerified(false);
    }
    public UserDTO(final UUID id) {
        setId(id);
        setIdentificationType(IdentificationTypeDTO.getDefaultObject());
        setIdentificationNumber(TextHelper.getDefault());
        setFirstName(TextHelper.getDefault());
        setSecondName(TextHelper.getDefault());
        setFirstLastname(TextHelper.getDefault());
        setSecondLastname(TextHelper.getDefault());
        setCity(CityDTO.getDefaultObject());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setEmailVerified(false);
        setPhoneNumberVerified(false);
    }
    public UserDTO(final UUID id, final IdentificationTypeDTO identificationType, final String identificationNumber, final String firstName,
                   final String secondName, final String firstLastname, final String secondLastname, final CityDTO city, final String email,
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
    public IdentificationTypeDTO getIdentificationType() {
        return identificationType;
    }
    public void setIdentificationType(final IdentificationTypeDTO identificationType) {
        this.identificationType = ObjectHelper.getDefault(identificationType, IdentificationTypeDTO.getDefaultObject());
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
    public CityDTO getCity() {
        return city;
    }
    public void setCity(final CityDTO city) {
        this.city = ObjectHelper.getDefault(city, CityDTO.getDefaultObject());
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
        this.emailVerified = ObjectHelper.getDefault(emailVerified, false);
    }
    public Boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }
    public void setPhoneNumberVerified(final Boolean phoneNumberVerified) {
        this.phoneNumberVerified = ObjectHelper.getDefault(phoneNumberVerified, false);
    }

}
