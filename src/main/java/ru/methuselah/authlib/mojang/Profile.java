package ru.methuselah.authlib.mojang;

import java.util.Formatter;

public class Profile
{
	public String  id;
	public String  name;
	public boolean legacy;
	public ProfileProperty[] properties = new ProfileProperty[] {};
	// TO DO LATER!
	public static String binToHex(byte[] binary)
	{
		Formatter formatter = new Formatter();
		for(byte b : binary)
			formatter.format("%02x", b);
		return formatter.toString();
	}
	public static String binToHex(String binary)
	{
		return binToHex(binary.getBytes());
	}
	public ProfileTextures getTextures()
	{
		if(properties != null)
			for(ProfileProperty property : properties)
				if("textures".equals(property.name))
				{
					binToHex(name);
					return null;
				}
		return null;
	}
}
