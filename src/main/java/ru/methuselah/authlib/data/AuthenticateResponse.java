package ru.methuselah.authlib.data;
import ru.methuselah.authlib.UserProvider;
import ru.methuselah.authlib.UserRole;
import ru.methuselah.authlib.mojang.Profile;

public class AuthenticateResponse
{
	/**
	 * Токен доступа: строка из 32-х шестьнадцатеричных цифр
	 */
	public String accessToken;
	/**
	 * Клиентский токен: строка из 32-х шестьнадцатеричных цифр
	 */
	public String clientToken;
	/**
	 * Список доступных игровых профилей. Присутствует в ответе только если в запросе был задан agent.
	 */
	public Profile availableProfiles[];
	/**
	 * Описание выбранного профиля
	 */
	public Profile selectedProfile = new Profile();
	/**
	 * Роль игрока (определяет уровень доступа)
	*/
	public UserRole role = UserRole.nonauth;
	/**
	 * Провайдер аутентификации, принявший игрока
	 */
	public UserProvider provider = UserProvider.nonauth;
}
