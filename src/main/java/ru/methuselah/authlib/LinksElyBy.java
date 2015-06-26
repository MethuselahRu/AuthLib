package ru.methuselah.authlib;

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
			null,
			null,
			null,
			"/session/profile/");
	}
}
