package ru.methuselah.authlib.hacks;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ProxyConnection extends HttpURLConnection
{
	private final Proxy proxy;
	private ByteArrayInputStream inputStream;
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private boolean outClosed = false;
	public ProxyConnection(URL url, Proxy proxy)
	{
		super(url);
		this.proxy = proxy;
	}
	@Override
	public void disconnect()
	{
	}
	@Override
	public boolean usingProxy()
	{
		return proxy != null;
	}
	@Override
	public void connect() throws IOException
	{
	}
	@Override
	public int getResponseCode()
	{
		return 200;
	}
	@Override
	public InputStream getInputStream() throws IOException
	{
		if(inputStream == null)
		{
			outClosed = true;
			JsonArray users = new JsonParser().parse(new String(outputStream.toByteArray(), Charset.forName("UTF-8"))).getAsJsonArray();
			StringBuilder reply = new StringBuilder("[");
			for(JsonElement user : users)
			{
				String username = user.getAsString();
				/*
				PlayerProfile info = MojangAPIProxy.getMojangAPIProxy().getCachedData().getPlayerProfile(username);
				reply.append("{")
					.append("\"id\":").append("\"").append(info.getUUID().toString().replace("-", "")).append("\"")
					.append(",")
					.append("\"name\":").append("\"").append(info.getName()).append("\"")
					.append("}").append(",");
				*/
			}
			reply.deleteCharAt(reply.length() - 1).append("]");
			inputStream = new ByteArrayInputStream(reply.toString().getBytes(Charset.forName("UTF-8")));
		}
		return inputStream;
	}
	@Override
	public OutputStream getOutputStream() throws IOException
	{
		if(outClosed)
			throw new RuntimeException("Write after send");
		return outputStream;
	}
}
