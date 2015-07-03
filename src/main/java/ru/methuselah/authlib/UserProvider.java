package ru.methuselah.authlib;

// Источник подтверждения успешной аутентификации пользователя

public enum UserProvider
{
	/**
	 * Неавторизован
	 */
	nonauth,
	/**
	 * Гостевая учётная запись
	 */
	guest,
	/**
	 * Использованные данные подтверждены веб-сервером или третьей стороной
	 */
	project,
	/**
	 * Использована лицензионная учётная запись
	 */
	mojang,
	/**
	 * Авторизован через учётную запись vk.com
	 */
	vk,
}
