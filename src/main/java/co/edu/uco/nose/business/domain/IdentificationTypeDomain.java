package co.edu.uco.nose.business.domain;

import java.util.UUID;

import co.edu.uco.nose.crosscuting.helpers.TextHelper;
import co.edu.uco.nose.crosscuting.helpers.UUIDHelper;

public final class IdentificationTypeDomain extends Domain
{
	private String name;
	
	public static final IdentificationTypeDomain DEFAULT = new IdentificationTypeDomain();
	
	public static IdentificationTypeDomain getDefaultObject()
	{
		return DEFAULT;
	}
	
	public IdentificationTypeDomain()
	{
		super(UUIDHelper.getUUIDHelper().getDefault());
		setName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDomain(final UUID id) 
	{
		super(id);
		setName(TextHelper.getDefault());
	}
	
	public IdentificationTypeDomain(final String name, final UUID id) 
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
