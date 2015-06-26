package ru.methuselah.authlib.links;

import ru.methuselah.authlib.methods.WebMethodCaller;

public final class GlobalReplacementList
{
	public static final ReplacementListEntrySF[] defaultReplacementsSF =
	{
		// Legacy
		new ReplacementListEntrySF("authlib.legacy.LegacyMinecraftSessionService", "JOIN_URL",            WebMethodCaller.urlLegacyJoin),
		new ReplacementListEntrySF("authlib.legacy.LegacyMinecraftSessionService", "CHECK_URL",           WebMethodCaller.urlLegacyCheck),
		// Yggdrasil
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "BASE_URL",           WebMethodCaller.urlBase),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_AUTHENTICATE", WebMethodCaller.urlAuthenticate),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_REFRESH",      WebMethodCaller.urlRefresh),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_VALIDATE",     WebMethodCaller.urlValidate),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_INVALIDATE",   WebMethodCaller.urlInvalidate),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilUserAuthentication", "ROUTE_SIGNOUT",      WebMethodCaller.urlSignout),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "BASE_URL",      WebMethodCaller.urlBase),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "JOIN_URL",      WebMethodCaller.urlJoin),
		new ReplacementListEntrySF("authlib.yggdrasil.YggdrasilMinecraftSessionService", "CHECK_URL",     WebMethodCaller.urlHasJoined),
		// BungeeCord: For cancelled Pull Request #1481, https://github.com/SpigotMC/BungeeCord/pull/1481
		new ReplacementListEntrySF("net.md_5.bungee.connection.InitialHandler.class", "baseAuthURL",      WebMethodCaller.urlHasJoined + "?username="),
	};
	public static final ReplacementListEntrySH[] defaultReplacementsSH =
	{
		// (GET) Username -> UUID at time
		new ReplacementListEntrySH(
			"https://api.mojang.com/users/profiles/minecraft/",
			WebMethodCaller.urlBase + "api/getuuid.php?name="),
		// (GET) UUID -> Name history
		new ReplacementListEntrySH(
			"https://api.mojang.com/user/profiles/",
			WebMethodCaller.urlBase + "api/namehistory.php?uuid="),
		// (JSON POST) Playernames (bulk) -> UUIDs/Names
		new ReplacementListEntrySH(
			"https://api.mojang.com/profiles/minecraft",
			WebMethodCaller.urlBase + "api/namestouuids.php"),
		// (GET) UUID -> Profile + Skin/Cape
		new ReplacementListEntrySH(
			"https://sessionserver.mojang.com/session/minecraft/profile/",
			WebMethodCaller.urlBase + "api/clothes.php?uuid="),
		// This is needed for BungeeCord
		new ReplacementListEntrySH(
			"https://sessionserver.mojang.com/session/minecraft/hasJoined",
			WebMethodCaller.urlHasJoined),
	};
	public final ReplacementListEntrySF[] replacementsSF;
	public final ReplacementListEntrySH[] replacementsSH;
	public GlobalReplacementList()
	{
		this.replacementsSF = defaultReplacementsSF;
		this.replacementsSH = defaultReplacementsSH;
	}
	public GlobalReplacementList(ReplacementListEntrySF[] rSFs, ReplacementListEntrySH[] rSHs)
	{
		this.replacementsSF = rSFs;
		this.replacementsSH = rSHs;
	}
}
