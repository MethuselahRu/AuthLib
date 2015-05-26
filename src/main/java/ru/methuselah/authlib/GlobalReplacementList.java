package ru.methuselah.authlib;

public final class GlobalReplacementList
{
	public static final ReplacementListEntrySF[] defaultReplacementsSF =
	{
		// Legacy
		new ReplacementListEntrySF("authlib.legacy.LegacyMinecraftSessionService", "JOIN_URL",            MethuselahMethods.urlLegacyJoin),
		new ReplacementListEntrySF("authlib.legacy.LegacyMinecraftSessionService", "CHECK_URL",           MethuselahMethods.urlLegacyCheck),
		// Yggdrasil
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "BASE_URL",           MethuselahMethods.urlBase),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_AUTHENTICATE", MethuselahMethods.urlAuthenticate),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_REFRESH",      MethuselahMethods.urlRefresh),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_VALIDATE",     MethuselahMethods.urlValidate),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_INVALIDATE",   MethuselahMethods.urlInvalidate),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_SIGNOUT",      MethuselahMethods.urlSignout),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "BASE_URL",      MethuselahMethods.urlBase),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "JOIN_URL",      MethuselahMethods.urlJoin),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "CHECK_URL",     MethuselahMethods.urlHasJoined),
		// BungeeCord: For cancelled Pull Request #1481, https://github.com/SpigotMC/BungeeCord/pull/1481
		new ReplacementListEntrySF("net.md_5.bungee.connection.InitialHandler.class", "baseAuthURL",      MethuselahMethods.urlHasJoined + "?username="),
	};
	public static final ReplacementListEntrySH[] defaultReplacementsSH =
	{
		// (GET) Username -> UUID at time
		new ReplacementListEntrySH(
			"https://api.mojang.com/users/profiles/minecraft/",
			MethuselahMethods.urlBase + "api/getuuid.php?name="),
		// (GET) UUID -> Name history
		new ReplacementListEntrySH(
			"https://api.mojang.com/user/profiles/",
			MethuselahMethods.urlBase + "api/namehistory.php?uuid="),
		// (JSON POST) Playernames (bulk) -> UUIDs/Names
		new ReplacementListEntrySH(
			"https://api.mojang.com/profiles/minecraft",
			MethuselahMethods.urlBase + "api/namestouuids.php"),
		// (GET) UUID -> Profile + Skin/Cape
		new ReplacementListEntrySH(
			"https://sessionserver.mojang.com/session/minecraft/profile/",
			MethuselahMethods.urlBase + "api/clothes.php?uuid="),
		// This is needed for BungeeCord
		new ReplacementListEntrySH(
			"https://sessionserver.mojang.com/session/minecraft/hasJoined",
			MethuselahMethods.urlHasJoined),
	};
	public final ReplacementListEntrySF[] replacementsSF = defaultReplacementsSF;
	public final ReplacementListEntrySH[] replacementsSH = defaultReplacementsSH;
	public GlobalReplacementList()
	{
	}
}
