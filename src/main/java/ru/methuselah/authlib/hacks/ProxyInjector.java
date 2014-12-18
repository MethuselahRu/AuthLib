package ru.methuselah.authlib.hacks;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class ProxyInjector
{
	public static void injectProxy()
	{
		final URLStreamHandlerFactory factory = new URLStreamHandlerFactory()
		{
			@Override
			public URLStreamHandler createURLStreamHandler(String protocol)
			{
				return (protocol.equals("http") || protocol.equals("https")) ? new ProxyStreamHandler(protocol) : null;
			}
		};
		try
		{
			synchronized(getLock())
			{
				final Field factoryField = URL.class.getDeclaredField("factory");
				final boolean isAccessible = factoryField.isAccessible();
				factoryField.setAccessible(true);
				factoryField.set(null, null);
				URL.setURLStreamHandlerFactory(factory);
				factoryField.setAccessible(isAccessible);
			}
		} catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
		}
	}
	private static Object getLock() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		final Field lockField = URL.class.getDeclaredField("streamHandlerLock");
		lockField.setAccessible(true);
		return lockField.get(null);
	}
}
