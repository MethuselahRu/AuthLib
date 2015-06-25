package ru.methuselah.authlib.data;
import ru.methuselah.authlib.mojang.Agent;

public class AuthenticatePayload
{
	public final Agent agent = new Agent();
	public String  username;
	public String  password;
	public String  clientToken;
	public String  project;
	public boolean guest;
}
