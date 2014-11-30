package ru.methuselah.authlib.data;

public class AuthenticatePayload
{
	private class Agent
	{
		public final String name = "minecraft";
		public final int version = 1;
	}
	private final Agent agent = new Agent();
	public String username;
	public String password;
	public String clientToken;
	public String project;
	public boolean guest = false;
}
