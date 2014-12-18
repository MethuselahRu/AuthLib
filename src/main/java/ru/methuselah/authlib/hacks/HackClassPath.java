package ru.methuselah.authlib.hacks;
import java.lang.reflect.Field;

public class HackClassPath
{
	public static void setFakeClassPath(String[] classPathEntries)
	{
		if(classPathEntries == null || classPathEntries.length == 0)
			return;
		final String pathSeparator = System.getProperty("path.separator");
		final StringBuilder sb = new StringBuilder(classPathEntries[0]);
		for(int entry = 1; entry < classPathEntries.length; entry += 1)
			sb.append(pathSeparator).append(classPathEntries[entry]);
		System.setProperty("java.class.path", sb.toString());
	}
	public static void updateNativesPath(String path)
	{
		if(path == null || "".equals(path))
			return;
		try
		{
			System.setProperty("java.library.path", path);
			final Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
			final boolean accessible = fieldSysPath.isAccessible();
			fieldSysPath.setAccessible(true);
			fieldSysPath.set(null, null);
			fieldSysPath.setAccessible(accessible);
		} catch(NoSuchFieldException ex) {
		} catch(SecurityException ex) {
		} catch(IllegalArgumentException ex) {
		} catch(IllegalAccessException ex) {
		}
	}
}
