package ru.methuselah.authlib;

public final class LinksMethuselah extends Links
{
	public LinksMethuselah()
	{
		super("https://auth.methuselah.ru",
			"/authenticate.php",
			"/refresh.php",
			"/validate.php",
			"/invalidate.php",
			"/signout.php",
			"/join.php",
			"/hasJoined.php",
			"/legacy/legacy_join.php",
			"/legacy/legacy_check.php",
			"/api/getuuid.php",
			"/api/namehistory.php",
			"/api/namestouuids.php",
			"/api/clothes.php");
	}
}
