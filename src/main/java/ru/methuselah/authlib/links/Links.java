package ru.methuselah.authlib.links;

import java.util.ArrayList;

public abstract class Links
{
	private final String urlBase;
	private final String methodAuthenticate;
	private final String methodRefresh;
	private final String methodValidate;
	private final String methodInvalidate;
	private final String methodSignout;
	private final String methodJoin;
	private final String methodHasJoined;
	private final String legacyJoin;
	private final String legacyHasJoined;
	private final String apiNameToUUID;
	private final String apiNameHistory;
	private final String apiBulkNames;
	private final String apiProfile;
	
	protected final static String MOJANG_A    = "https://authserver.mojang.com/authenticate";
	protected final static String MOJANG_R    = "https://authserver.mojang.com/refresh";
	protected final static String MOJANG_V    = "https://authserver.mojang.com/validate";
	protected final static String MOJANG_I    = "https://authserver.mojang.com/invalidate";
	protected final static String MOJANG_S    = "https://authserver.mojang.com/signout";
	protected final static String MOJANG_J    = "https://sessionserver.mojang.com/session/minecraft/join";
	protected final static String MOJANG_HJ   = "https://sessionserver.mojang.com/session/minecraft/hasJoined";
	protected final static String MOJANG_LJ   = "http://session.minecraft.net/game/joinserver.jsp";
	protected final static String MOJANG_LHJ  = "http://session.minecraft.net/game/checkserver.jsp";
	protected final static String MOJANG_API1 = "https://api.mojang.com/users/profiles/minecraft/";
	protected final static String MOJANG_API2 = "https://api.mojang.com/user/profiles/";
	protected final static String MOJANG_API3 = "https://api.mojang.com/profiles/minecraft";
	protected final static String MOJANG_API4 = "https://sessionserver.mojang.com/session/minecraft/profile/";

	/**
	 *
	 * @param b   Базовый адрес для конкатенации с нижеследующими методами, например https://auth.methuselah.ru
	 * @param a   Адрес метода authenticate, например /authenticate.php
	 * @param r   Адрес метода refresh, например /refresh.php
	 * @param v   Адрес метода validate, например /validate.php
	 * @param i   Адрес метода invalidate, например /invalidate.php
	 * @param s   Адрес метода signout, например /signout.php
	 * @param j   Адрес метода join, например /join.php
	 * @param hj  Адрес метода hasJoined, например /hasJoined.php
	 * @param lj  Адрес метода join для устаревшей системы аутентификации, основанной на сессиях
	 * @param lhj Адрес метода hasJoined для устаревшей системы аутентификации, основанной на сессиях
	 * @param a1  Адрес метода Username → UUID at time
	 * @param a2  Адрес метода UUID → Name history
	 * @param a3  Адрес метода Playernames → UUIDs
	 * @param a4  Адрес метода UUID → Profile + Skin/Cape
	 */
	protected Links(String b, String a, String r, String v, String i, String s, String j, String hj, String lj, String lhj, String a1, String a2, String a3, String a4)
	{
		this.urlBase = b;
		this.methodAuthenticate = a;
		this.methodRefresh      = r;
		this.methodValidate     = v;
		this.methodInvalidate   = i;
		this.methodSignout      = s;
		this.methodJoin         = j;
		this.methodHasJoined    = hj;
		this.legacyJoin         = lj;
		this.legacyHasJoined    = lhj;
		this.apiNameToUUID      = a1;
		this.apiNameHistory     = a2;
		this.apiBulkNames       = a3;
		this.apiProfile         = a4;
	}
	
	/**
	 * Перечень возможных систем аутентификации
	 */
	public static enum LinksProvider
	{
		/**
		 * Некорректное значение
		 */
		invalid,

		/**
		 * Официальные скрипты от разработчика Mojang AG
		 */
		mojang,

		/**
		 * Скрипты, предоставленные http://ely.by
		 */
		elyby,

		/**
		 * Скрипты, предоставленные Мафусаил API (https://wiki.methuselah.ru)
		 */
		methuselah,

		/**
		 * Адреса скриптов задааны пользователем вручную
		 */
		custom,
	}
	private LinksProvider provider = LinksProvider.invalid;
	protected void setProvider(LinksProvider value)
	{
		this.provider = value;
	}

	/**
	 * Позволяет узнать в общих чертах, которая из систем аутентификации используется.
	 * @return Одно из значений enum LinksProvider
	 */
	public LinksProvider getProvider()
	{
		return this.provider;
	}
	
	/**
	 * Получение базы для URL-адресов всех веб-методов
	 * @return Общая начальная часть URL-адресов
	 */
	public String getBaseURL()
	{
		return (urlBase != null ? urlBase : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getAuthenticate()
	{
		return (urlBase != null ? urlBase : "") + (methodAuthenticate != null ? methodAuthenticate : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getRefresh()
	{
		return (urlBase != null ? urlBase : "") + (methodRefresh != null ? methodRefresh : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getValidate()
	{
		return (urlBase != null ? urlBase : "") + (methodValidate != null ? methodValidate : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getInvalidate()
	{
		return (urlBase != null ? urlBase : "") + (methodInvalidate != null ? methodInvalidate : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getSignout()
	{
		return (urlBase != null ? urlBase : "") + (methodSignout != null ? methodSignout : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getJoin()
	{
		return (urlBase != null ? urlBase : "") + (methodJoin != null ? methodJoin : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getHasJoined()
	{
		return (urlBase != null ? urlBase : "") + (methodHasJoined != null ? methodHasJoined : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getLegacyJoin()
	{
		return (urlBase != null ? urlBase : "") + (legacyJoin != null ? legacyJoin : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getLegacyHasJoined()
	{
		return (urlBase != null ? urlBase : "") + (legacyHasJoined != null ? legacyHasJoined : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getNameToUUID()
	{
		return (urlBase != null ? urlBase : "") + (apiNameToUUID != null ? apiNameToUUID : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getNameHistory()
	{
		return (urlBase != null ? urlBase : "") + (apiNameHistory != null ? apiNameHistory : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getBulkNames()
	{
		return (urlBase != null ? urlBase : "") + (apiBulkNames != null ? apiBulkNames : "");
	}
	/**
	 * Получение URL-адреса для веб-метода
	 * @return URL-адрес соответствующего метода
	 */
	public String getProfile()
	{
		return (urlBase != null ? urlBase : "") + (apiProfile != null ? apiProfile : "");
	}
	
	/**
	 * Генерация списка правил модификации интернет-адресов на основе текущего списка ссылок к скриптам
	 * @return Объект, описывающий правила модификации используемых URL-адресов
	 */
	public GlobalReplacementList buildReplacements()
	{
		final String prefix = (urlBase != null ? urlBase : "");
		
		// SFs
		final ArrayList<ReplacementListEntrySF> rSFs = new ArrayList<>();
		for(String packagePrefix : authlibClassPackagePrefixes)
		{
			// Yggdrasil
			final String yggdrasilUA  = packagePrefix + "authlib.yggdrasil.YggdrasilUserAuthentication";
			final String yggdrasilMSS = packagePrefix + "authlib.yggdrasil.YggdrasilMinecraftSessionService";
			final String legacyMSS    = packagePrefix + "authlib.legacy.LegacyMinecraftSessionService";
			if(urlBase != null && !"".equals(urlBase))
			{
				rSFs.add(new ReplacementListEntrySF(yggdrasilUA,  "BASE_URL", urlBase));
				rSFs.add(new ReplacementListEntrySF(yggdrasilMSS, "BASE_URL", urlBase));
			}
			if(methodAuthenticate != null && !"".equals(methodAuthenticate))
				rSFs.add(new ReplacementListEntrySF(yggdrasilUA, "ROUTE_AUTHENTICATE", prefix + methodAuthenticate));
			if(methodRefresh != null && !"".equals(methodRefresh))
				rSFs.add(new ReplacementListEntrySF(yggdrasilUA, "ROUTE_REFRESH",      prefix + methodRefresh));
			if(methodValidate != null && !"".equals(methodValidate))
				rSFs.add(new ReplacementListEntrySF(yggdrasilUA, "ROUTE_VALIDATE",     prefix + methodValidate));
			if(methodInvalidate != null && !"".equals(methodInvalidate))
				rSFs.add(new ReplacementListEntrySF(yggdrasilUA, "ROUTE_INVALIDATE",   prefix + methodInvalidate));
			if(methodSignout != null && !"".equals(methodSignout))
				rSFs.add(new ReplacementListEntrySF(yggdrasilUA, "ROUTE_SIGNOUT",      prefix + methodSignout));
			// Join, HasJoined
			if(methodJoin != null && !"".equals(methodJoin))
				rSFs.add(new ReplacementListEntrySF(yggdrasilMSS, "JOIN_URL",  prefix + methodJoin));
			if(methodHasJoined != null && !"".equals(methodHasJoined))
				rSFs.add(new ReplacementListEntrySF(yggdrasilMSS, "CHECK_URL", prefix + methodHasJoined));
			// Legacy
			if(methodJoin != null && !"".equals(methodJoin))
				rSFs.add(new ReplacementListEntrySF(legacyMSS, "JOIN_URL",  prefix + legacyJoin));
			if(methodHasJoined != null && !"".equals(methodHasJoined))
				rSFs.add(new ReplacementListEntrySF(legacyMSS, "CHECK_URL", prefix + legacyHasJoined));
		}
		
		// SHs
		final ArrayList<ReplacementListEntrySH> rSHs = new ArrayList<>();
		// ... arvis
		if(methodAuthenticate != null && !"".equals(methodAuthenticate))
			rSHs.add(new ReplacementListEntrySH(MOJANG_A, prefix + methodAuthenticate));
		if(methodRefresh != null && !"".equals(methodRefresh))
			rSHs.add(new ReplacementListEntrySH(MOJANG_R, prefix + methodRefresh));
		if(methodValidate != null && !"".equals(methodValidate))
			rSHs.add(new ReplacementListEntrySH(MOJANG_V, prefix + methodValidate));
		if(methodInvalidate != null && !"".equals(methodInvalidate))
			rSHs.add(new ReplacementListEntrySH(MOJANG_I, prefix + methodInvalidate));
		if(methodSignout != null && !"".equals(methodSignout))
			rSHs.add(new ReplacementListEntrySH(MOJANG_S, prefix + methodSignout));
		// Join, HasJoined, Legacy J/HJ
		if(methodJoin != null && !"".equals(methodJoin))
			rSHs.add(new ReplacementListEntrySH(MOJANG_J,   prefix + methodJoin));
		if(methodHasJoined != null && !"".equals(methodHasJoined))
			rSHs.add(new ReplacementListEntrySH(MOJANG_HJ,  prefix + methodHasJoined));
		if(legacyJoin != null && !"".equals(legacyJoin))
			rSHs.add(new ReplacementListEntrySH(MOJANG_LJ,  prefix + legacyJoin));
		if(legacyHasJoined != null && !"".equals(legacyHasJoined))
			rSHs.add(new ReplacementListEntrySH(MOJANG_LHJ, prefix + legacyHasJoined));
		// APIs
		if(apiNameToUUID != null && !"".equals(apiNameToUUID))
			rSHs.add(new ReplacementListEntrySH(MOJANG_API1, prefix + apiNameToUUID));
		if(apiNameHistory != null && !"".equals(apiNameHistory))
			rSHs.add(new ReplacementListEntrySH(MOJANG_API2, prefix + apiNameHistory));
		if(apiBulkNames != null && !"".equals(apiBulkNames))
			rSHs.add(new ReplacementListEntrySH(MOJANG_API3, prefix + apiBulkNames));
		if(apiProfile != null && !"".equals(apiProfile))
			rSHs.add(new ReplacementListEntrySH(MOJANG_API4, prefix + apiProfile));
		
		// Вернуть результат
		return new GlobalReplacementList(
			rSFs.toArray(new ReplacementListEntrySF[rSFs.size()]),
			rSHs.toArray(new ReplacementListEntrySH[rSHs.size()]));
	}
	private static final String[] authlibClassPackagePrefixes =
	{
		// На ванильных серверах/клиентах
		"com.mojang.",
		// В нёдрах серверных ядер CraftBukkit
		"net.minecraft.util.com.mojang.",
		// В нёдрах серверных ядер Spigot
		"org.spigotmc.",
		// Альтернативные сервера/клиенты указывают полный путь к классу
		"",
	};
}
