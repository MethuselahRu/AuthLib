package ru.methuselah.authlib.links;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Links
{
	private final String urlBase;
	private final String methodAuthenticate;
	private final String methodRefresh;
	private final String methodValidate;
	private final String methodInvalidate;
	private final String methodSighout;
	private final String methodJoin;
	private final String methodHasJoined;
	private final String legacyJoin;
	private final String legacyHasJoined;
	private final String apiNameToUUID;
	private final String apiNameHistory;
	private final String apiBulkNames;
	private final String apiProfile;
	
	protected final static String mojangA    = "https://authserver.mojang.com/authenticate";
	protected final static String mojangR    = "https://authserver.mojang.com/refresh";
	protected final static String mojangV    = "https://authserver.mojang.com/validate";
	protected final static String mojangI    = "https://authserver.mojang.com/invalidate";
	protected final static String mojangS    = "https://authserver.mojang.com/signout";
	protected final static String mojangJ    = "https://sessionserver.mojang.com/session/minecraft/join";
	protected final static String mojangHJ   = "https://sessionserver.mojang.com/session/minecraft/hasJoined";
	protected final static String mojangLJ   = "http://session.minecraft.net/game/joinserver.jsp";
	protected final static String mojangLHJ  = "http://session.minecraft.net/game/checkserver.jsp";
	protected final static String mojangAPI1 = "https://api.mojang.com/users/profiles/minecraft/";
	protected final static String mojangAPI2 = "https://api.mojang.com/user/profiles/";
	protected final static String mojangAPI3 = "https://api.mojang.com/profiles/minecraft";
	protected final static String mojangAPI4 = "https://sessionserver.mojang.com/session/minecraft/profile/";

	/**
	 *
	 * @param b Базовый адрес для конкатенации с нижеследующими методами, например https://auth.methuselah.ru
	 * @param a Адрес метода authenticate, например /authenticate.php
	 * @param r Адрес метода refresh, например /refresh.php
	 * @param v Адрес метода validate, например /validate.php
	 * @param i Адрес метода invalidate, например /invalidate.php
	 * @param s Адрес метода sighout, например /sighout.php
	 * @param j Адрес метода join, например /join.php
	 * @param hj Адрес метода hasJoined, например /hasJoined.php
	 * @param lj Адрес метода join для устаревшей системы аутентификации, основанной на сессиях
	 * @param lhj Адрес метода hasJoined для устаревшей системы аутентификации, основанной на сессиях
	 * @param a1 Адрес метода Username → UUID at time
	 * @param a2 Адрес метода UUID → Name history
	 * @param a3 Адрес метода Playernames → UUIDs
	 * @param a4 Адрес метода UUID → Profile + Skin/Cape
	 */
	protected Links(String b, String a, String r, String v, String i, String s, String j, String hj, String lj, String lhj, String a1, String a2, String a3, String a4)
	{
		this.urlBase = b;
		this.methodAuthenticate = a;
		this.methodRefresh      = r;
		this.methodValidate     = v;
		this.methodInvalidate   = i;
		this.methodSighout      = s;
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
	
	GlobalReplacementList buildReplacements()
	{
		final String prefix = (urlBase != null ? urlBase : "");
		
		// SFs
		final ArrayList<ReplacementListEntrySF> rSFs = new ArrayList<ReplacementListEntrySF>();
		
		// SHs
		final ArrayList<ReplacementListEntrySH> rSHs = new ArrayList<ReplacementListEntrySH>();
		// ... arvis
		if(methodAuthenticate != null && !"".equals(methodAuthenticate))
			rSHs.add(new ReplacementListEntrySH(mojangA, prefix + methodAuthenticate));
		if(methodRefresh != null && !"".equals(methodRefresh))
			rSHs.add(new ReplacementListEntrySH(mojangR, prefix + methodRefresh));
		if(methodValidate != null && !"".equals(methodValidate))
			rSHs.add(new ReplacementListEntrySH(mojangV, prefix + methodValidate));
		if(methodInvalidate != null && !"".equals(methodInvalidate))
			rSHs.add(new ReplacementListEntrySH(mojangI, prefix + methodInvalidate));
		if(methodSighout != null && !"".equals(methodSighout))
			rSHs.add(new ReplacementListEntrySH(mojangS, prefix + methodSighout));
		// j/hj, legacy j/hj
		if(methodJoin != null && !"".equals(methodJoin))
			rSHs.add(new ReplacementListEntrySH(mojangJ, prefix + methodJoin));
		if(methodHasJoined != null && !"".equals(methodHasJoined))
			rSHs.add(new ReplacementListEntrySH(mojangHJ, prefix + methodHasJoined));
		if(legacyJoin != null && !"".equals(legacyJoin))
			rSHs.add(new ReplacementListEntrySH(mojangLJ, prefix + legacyJoin));
		if(legacyHasJoined != null && !"".equals(legacyHasJoined))
			rSHs.add(new ReplacementListEntrySH(mojangLHJ, prefix + legacyHasJoined));
		// APIs
		if(apiNameToUUID != null && !"".equals(apiNameToUUID))
			rSHs.add(new ReplacementListEntrySH(mojangAPI1, prefix + apiNameToUUID));
		if(apiNameHistory != null && !"".equals(apiNameHistory))
			rSHs.add(new ReplacementListEntrySH(mojangAPI2, prefix + apiNameHistory));
		if(apiBulkNames != null && !"".equals(apiBulkNames))
			rSHs.add(new ReplacementListEntrySH(mojangAPI3, prefix + apiBulkNames));
		if(apiProfile != null && !"".equals(apiProfile))
			rSHs.add(new ReplacementListEntrySH(mojangAPI4, prefix + apiProfile));
		
		// Вернуть результат
		return new GlobalReplacementList(
			rSFs.toArray(new ReplacementListEntrySF[rSFs.size()]),
			rSHs.toArray(new ReplacementListEntrySH[rSHs.size()]));
	}
}