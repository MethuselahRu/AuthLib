package ru.methuselah.authlib.hacks;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ProxyStreamHandler extends URLStreamHandler
{
	private final URLStreamHandler handler;
	private final Method openConn;
	private final Method openConnProxy;
	public ProxyStreamHandler(String protocol)
	{
		handler = (protocol.equals("http")
			? new sun.net.www.protocol.http.Handler()
			: new sun.net.www.protocol.https.Handler());
		try
		{
			openConn = handler.getClass().getDeclaredMethod("openConnection", URL.class);
			openConn.setAccessible(true);
			openConnProxy = handler.getClass().getDeclaredMethod("openConnection", URL.class, Proxy.class);
			openConnProxy.setAccessible(true);
		} catch(NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected URLConnection openConnection(URL u) throws IOException
	{
		if((u.getHost().toLowerCase().equals("api.mojang.com") && u.getPath().toLowerCase().startsWith("/profiles/minecraft")) && shouldProxyRequest())
			return getProxyConnection(u, null);
		return getDefaultConnection(u);
	}
	@Override
	protected URLConnection openConnection(URL u, Proxy p) throws IOException
	{
		if((u.getHost().toLowerCase().equals("api.mojang.com") && u.getPath().toLowerCase().startsWith("/profiles/minecraft")) && shouldProxyRequest())
			return getProxyConnection(u, p);
		return getDefaultConnection(u, p);
	}
	private URLConnection getProxyConnection(URL u, Proxy p)
	{
		return new ProxyConnection(u, p);
	}
	public URLConnection getDefaultConnection(URL u)
	{
		try
		{
			return (URLConnection)openConn.invoke(handler, u);
		} catch(IllegalAccessException | InvocationTargetException e) {
		}
		return null;
	}
	public URLConnection getDefaultConnection(URL u, Proxy p)
	{
		try
		{
			return (URLConnection)openConnProxy.invoke(handler, u, p);
		} catch(IllegalAccessException | InvocationTargetException e) {
		}
		return null;
	}
	private boolean shouldProxyRequest()
	{
		/*
		if(ProxyUtils.isPluginIgnored(ProxyUtils.getRequestingPlugin()))
			return false;
		*/
		return true;
	}
}
