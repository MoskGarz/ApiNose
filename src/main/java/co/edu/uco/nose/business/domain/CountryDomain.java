package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

public final class CountryDomain extends Domain
{
	private String name;
	
	public static final CountryDomain DEFAULT = new CountryDomain();

	public static CountryDomain getDefaultObject()
	{
		return DEFAULT;
	}
	
	CountryDomain()
	{
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public CountryDomain(final UUID id) 
	{
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public CountryDomain(final UUID id, final String name) 
	{
		super(id);
		setName(name);
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(final String name) 
	{
		this.name = TextHelper.getDefaultWithTrim(name);
	}
	
	
	
}
