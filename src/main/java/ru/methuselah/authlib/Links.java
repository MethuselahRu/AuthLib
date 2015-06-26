package ru.methuselah.authlib;

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
}
