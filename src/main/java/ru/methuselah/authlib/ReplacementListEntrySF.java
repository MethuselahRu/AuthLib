package ru.methuselah.authlib;

public class ReplacementListEntrySF
{
	public static final String[] possibleClassPrefixes =
	{
		// Vanilla
		"com.mojang.",
		// CraftBukkit
		"net.minecraft.util.com.mojang.",
		// Spigot
		"org.spigotmc.",
		// Other
		"",
	};
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
