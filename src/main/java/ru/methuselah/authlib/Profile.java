package ru.methuselah.authlib;
import java.util.Formatter;

public class Profile
{
	public String id;
	public String name;
	public boolean legacy;
	public static class ProfileProperty
	{
		public String name;
		public String value;
		public String signature;
	}
	public static class ProfilePropertyTextures
	{
		public long timestamp;
		public String profileId;
		public String profileName;
		public boolean isPublic;
		public static class ProfileTextures
		{
			public static class ProfileSkin
			{
				public String url;
			}
			public static class ProfileCape
			{
				public String url;
			}
			public ProfileSkin SKIN;
			public ProfileCape CAPE;
		}
		public ProfileTextures textures;
	}
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
	public ProfilePropertyTextures.ProfileTextures getTextures()
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
