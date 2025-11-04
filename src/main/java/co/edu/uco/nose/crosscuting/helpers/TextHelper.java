package co.edu.uco.nose.crosscuting.helpers;

public final class TextHelper 
{
	public static final String EMPTY = "";
	
	private TextHelper()
	{
		
	}
	
	public static String getDefault()
	{
		return EMPTY;
	}
	
	public static String getDefault(final String value)
	{
		return ObjectHelper.getDefault(value, getDefault());
	}
	
	public static String getDefaultWithTrim(final String value)
	{
		return getDefault(value).trim();
	}

	public static boolean isEmpty(final String value){
		return EMPTY.equals(getDefault(value));
	}

	public static boolean isEmptyWithTrim(final String value){
		return EMPTY.equals(getDefaultWithTrim(value));
	}

	public static boolean isLenghtValid(final String value, final int min, final int max, final boolean mustApplyTrim){
		var length = (mustApplyTrim ? getDefaultWithTrim(value) : getDefault(value)).length();
		return length >= min && length  <= max;
	}

	public static boolean isLenghtValidWithTrim(final String value, final int min, final int max){
		return isLenghtValid(getDefaultWithTrim(value), min, max, true);
	}
}
