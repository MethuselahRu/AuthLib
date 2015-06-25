package ru.methuselah.authlib.data;
import ru.methuselah.authlib.mojang.Profile;

public class RefreshPayload
{
	public String  accessToken;
	public String  clientToken;
	public Profile selectedProfile;
}
