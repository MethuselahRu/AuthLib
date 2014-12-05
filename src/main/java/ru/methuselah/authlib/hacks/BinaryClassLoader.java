package ru.methuselah.authlib.hacks;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinaryClassLoader extends URLClassLoader
{
	private final Map<String, byte[]> extraClassDefs = new HashMap<>();
	public BinaryClassLoader(URL[] urls, ClassLoader parent)
	{
		super(urls, parent);
	}
	public void addClass(String fqdn, byte[] binary)
	{
		this.extraClassDefs.put(fqdn, Arrays.copyOf(binary, binary.length));
	}
	public void addClasses(Map<String, byte[]> definitions)
	{
		for(Map.Entry<String, byte[]> definition : definitions.entrySet())
			this.extraClassDefs.put(definition.getKey(), Arrays.copyOf(definition.getValue(), definition.getValue().length));
	}
	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException
	{
		final byte[] classBytes = this.extraClassDefs.get(name);
		return (classBytes != null) ? defineClass(name, classBytes, 0, classBytes.length) : super.findClass(name);
	}
}
