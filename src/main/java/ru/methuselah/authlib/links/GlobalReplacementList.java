package ru.methuselah.authlib.links;

public final class GlobalReplacementList
{
	/**
	 * Список замен приватных статических URL-полей в классах
	 */
	public final ReplacementListEntrySF[] replacementsSF;
	/**
	 * Список текстовых замен в URL-адресах
	 */
	public final ReplacementListEntrySH[] replacementsSH;
	/**
	 * Данный конструктор используется Gson для создания объекта
	 */
	public GlobalReplacementList()
	{
		this.replacementsSF = new ReplacementListEntrySF[] {};
		this.replacementsSH = new ReplacementListEntrySH[] {};
	}
	/**
	 * Построение списка замены адресов веб-методов
	 * @param rSFs список замен приватных статических URL-полей в классах
	 * @param rSHs список текстовых замен в URL-адресах
	 */
	public GlobalReplacementList(ReplacementListEntrySF[] rSFs, ReplacementListEntrySH[] rSHs)
	{
		this.replacementsSF = rSFs;
		this.replacementsSH = rSHs;
	}
}
