package ru.methuselah.authlib;

/**
 * Список ролей пользователя
 */
public enum UserRole
{
	/**
	 * Неавторизован
	 */
	nonauth,
	/**
	 * Гостевой аккаунт
	 */
	guest,
	/**
	 * Обычный игрок
	 */
	player,
	/**
	 * Администратор
	 */
	administrator,
	/**
	 * Разработчик
	 */
	developer,
}
