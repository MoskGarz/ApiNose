package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

public final class UserDomain extends Domain
{
	private IdentificationTypeDomain identificationType;
	private String identificationNumber;
	private String firstName;
	private String secondName;
	private String firstLastName;
	private String secondLastName;
	private CityDomain city;
	private String email;
	private String phoneNumber;
	private Boolean emailVerified;
	private Boolean phoneNumberVerified;
	
	public static final UserDomain DEFAULT = new UserDomain();
	public static UserDomain getDefaultObject()
	{
		return DEFAULT;
	}
	
	public UserDomain() 
	{
		super(UUIDHelper.getUUIDHelper().getDefault());
		setIdentificationType(IdentificationTypeDomain.getDefaultObject());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setCity(CityDomain.getDefaultObject());
		setEmail(TextHelper.getDefault());
		setPhoneNumber(TextHelper.getDefault());
		setEmailVerified(false);
		setPhoneNumberVerified(false);
	}
	
	public UserDomain(UUID id) 
	{
		super(id);
		setIdentificationType(IdentificationTypeDomain.getDefaultObject());
		setIdentificationNumber(TextHelper.getDefault());
		setFirstName(TextHelper.getDefault());
		setSecondName(TextHelper.getDefault());
		setFirstLastName(TextHelper.getDefault());
		setSecondLastName(TextHelper.getDefault());
		setCity(CityDomain.getDefaultObject());
		setEmail(TextHelper.getDefault());
		setPhoneNumber(TextHelper.getDefault());
		setEmailVerified(false);
		setPhoneNumberVerified(false);
	}
	
	public UserDomain(UUID id, IdentificationTypeDomain identificationType, String identificationNumber, String firstName,
			String secondName, String firstLastName, String secondLastName, CityDomain city, String email,
			String phoneNumber, Boolean emailVerified, Boolean phoneNumberVerified) 
	{
		super(id);
		setIdentificationType(identificationType);
		setIdentificationNumber(identificationNumber);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstLastName(firstLastName);
		setSecondLastName(secondLastName);
		setCity(city);
		setEmail(email);
		setPhoneNumber(phoneNumber);
		setEmailVerified(emailVerified);
		setPhoneNumberVerified(phoneNumberVerified);
	}
	
	
	public IdentificationTypeDomain getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(IdentificationTypeDomain identificationType) {
		this.identificationType = identificationType;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getFirstLastName() {
		return firstLastName;
	}
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}
	public String getSecondLastName() {
		return secondLastName;
	}
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	public CityDomain getCity() {
		return city;
	}
	public void setCity(CityDomain city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public Boolean getPhoneNumberVerified() {
		return phoneNumberVerified;
	}
	public void setPhoneNumberVerified(Boolean phoneNumberVerified) {
		this.phoneNumberVerified = phoneNumberVerified;
	}
	
	

}
