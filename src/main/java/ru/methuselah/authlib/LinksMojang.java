package ru.methuselah.authlib;

public final class LinksMojang extends Links
{
	public LinksMojang()
	{
		super("",
			"https://authserver.mojang.com/authenticate",
			"https://authserver.mojang.com/refresh",
			"https://authserver.mojang.com/validate",
			"https://authserver.mojang.com/invalidate",
			"https://authserver.mojang.com/signout",
			"https://sessionserver.mojang.com/session/minecraft/join",
			"https://sessionserver.mojang.com/session/minecraft/hasJoined",
			"http://session.minecraft.net/game/joinserver.jsp",
			"http://session.minecraft.net/game/checkserver.jsp",
			"https://api.mojang.com/users/profiles/minecraft/",
			"https://api.mojang.com/user/profiles/",
			"https://api.mojang.com/profiles/minecraft",
			"https://sessionserver.mojang.com/session/minecraft/profile/");
	}
}
