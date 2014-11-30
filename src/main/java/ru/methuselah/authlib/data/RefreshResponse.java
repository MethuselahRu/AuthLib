package ru.methuselah.authlib.data;
import ru.methuselah.authlib.Profile;

public class RefreshResponse
{
	public String accessToken;
	public String clientToken;
	public Profile selectedProfile = new Profile();
}
