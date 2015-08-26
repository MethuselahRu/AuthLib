package ru.methuselah.authlib.links;

public final class LinksElyBy extends Links
{
	public LinksElyBy()
	{
		super("http://minecraft.ely.by",
			"/auth/authenticate",
			"/auth/refresh",
			"/auth/validate",
			"/auth/invalidate",
			"/auth/signout",
			"/session/join",
			"/session/hasJoined",
			"/session/legacy/join",
			"/session/legacy/hasJoined",
			"/api/users/profiles/minecraft/",
			"/api/user/profiles/",
			"/api/profiles/minecraft",
			"/session/profile/");
		super.setProvider(Links.LinksProvider.elyby);
	}
}
