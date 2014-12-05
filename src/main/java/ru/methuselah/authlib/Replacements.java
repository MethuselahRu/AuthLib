package ru.methuselah.authlib;

public class Replacements
{
	public static final String[] possibleClassPrefixes = 
	{
		// Vanilla
		"com.mojang.",
		// CraftBukkit
		"net.minecraft.util.com.mojang.",
		// Spigot
		"org.spigotmc."
	};
	public static final ReplacementSF[] defaultReplacementsSF =
	{
		// Legacy
		new ReplacementSF("authlib.legacy.LegacyMinecraftSessionService", "JOIN_URL",            MethuselahMethods.urlLegacyJoin),
		new ReplacementSF("authlib.legacy.LegacyMinecraftSessionService", "CHECK_URL",           MethuselahMethods.urlLegacyCheck),
		// Yggdrasil
		new ReplacementSF("authlib.yggdrasil.YggdrasilUserAuthentication", "BASE_URL",           MethuselahMethods.urlMethuselah),
		new ReplacementSF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_AUTHENTICATE", MethuselahMethods.urlAuthenticate),
		new ReplacementSF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_REFRESH",      MethuselahMethods.urlRefresh),
		new ReplacementSF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_VALIDATE",     MethuselahMethods.urlValidate),
		new ReplacementSF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_INVALIDATE",   MethuselahMethods.urlInvalidate),
		new ReplacementSF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_SIGNOUT",      MethuselahMethods.urlSignout),
		new ReplacementSF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "BASE_URL",      MethuselahMethods.urlMethuselah),
		new ReplacementSF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "JOIN_URL",      MethuselahMethods.urlJoin),
		new ReplacementSF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "CHECK_URL",     MethuselahMethods.urlHasJoined),
	};
	public static final ReplacementCP[] defaultReplacementsCP =
	{
		// Mojang API
		new ReplacementCP("authlib.yggdrasil.YggdrasilMinecraftSessionService",
			new ReplacementCP.ReplacementPair[]
			{
				new ReplacementCP.ReplacementPair("https://sessionserver.mojang.com/session/minecraft/profile/", MethuselahMethods.urlProfileInfo),
			}),
	};
	public static class ReplacementCP
	{
		public static class ReplacementPair
		{
			public final String oldValue;
			public final String newValue;
			private ReplacementPair(String oldValue, String newValue)
			{
				this.oldValue = oldValue;
				this.newValue = newValue;
			}
		}
		public final String targetClass;
		public final ReplacementPair[] pairs;
		public ReplacementCP(String targetClass, ReplacementPair[] pairs)
		{
			this.targetClass = targetClass;
			this.pairs = pairs;
		}
	}
	public static class ReplacementSF
	{
		public final String targetClass;
		public final String targetField;
		public final String newValue;
		public ReplacementSF(String targetClass, String fieldName, String newValue)
		{
			this.targetClass = targetClass;
			this.targetField = fieldName;
			this.newValue    = newValue;
		}
	}
}
