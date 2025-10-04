package co.edu.uco.nose.crosscuting.helpers;

public final class ObjectHelper 
{
	private ObjectHelper()
	{
		
	}
	
	public static final <O> boolean isNull(final O object)
	{
		return object == null;
	}

	public static final <O> O getDefault(final O object, final O defaultValue)
	{
		return isNull(object) ? defaultValue : object;
	}
}
