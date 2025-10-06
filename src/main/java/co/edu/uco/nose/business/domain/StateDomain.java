package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

public class StateDomain extends Domain
{
	private String name;
	private CountryDomain country;
	
	public static final StateDomain DEFAULT = new StateDomain();
	
	public static StateDomain getDefaultObject()
	{
		return DEFAULT;
	}
	
	public StateDomain()
	{
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
		setCountry(CountryDomain.getDefaultObject());
	}
	
	public StateDomain(final UUID id) 
	{
		super(id);
		setName(TextHelper.getDefault());
		setCountry(CountryDomain.getDefaultObject());
	}
	
	public StateDomain(final UUID id, final String name, final CountryDomain country) 
	{
		super(id);
		setName(name);
		setCountry(country);
	}

	public String getName() 
	{
		return name;
	}

	public void setName(final String name) 
	{
		this.name = TextHelper.getDefaultWithTrim(name);
	}

	public CountryDomain getCountry() 
	{
		return country;
	}

	public void setCountry(final CountryDomain country) 
	{
		this.country = ObjectHelper.getDefault(country, CountryDomain.getDefaultObject());
	}
}
