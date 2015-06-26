package ru.methuselah.authlib.links;

public class ReplacementListEntrySF
{
	public final String targetClass;
	public final String targetField;
	public final String newValue;
	public ReplacementListEntrySF(String targetClass, String fieldName, String newValue)
	{
		this.targetClass = targetClass;
		this.targetField = fieldName;
		this.newValue    = newValue;
	}
}
