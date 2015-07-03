package ru.methuselah.authlib.methods;

import ru.methuselah.authlib.data.AuthenticatePayload;
import ru.methuselah.authlib.data.AuthenticateResponse;
import ru.methuselah.authlib.data.InvalidatePayload;
import ru.methuselah.authlib.data.JoinPayload;
import ru.methuselah.authlib.data.RefreshPayload;
import ru.methuselah.authlib.data.RefreshResponse;
import ru.methuselah.authlib.data.SignoutPayload;
import ru.methuselah.authlib.data.ValidatePayload;
import ru.methuselah.authlib.links.Links;

/**
 * Объект, совершающий вызовы веб-методов
 */
public class WebMethodCaller extends WebConnection
{
	private final Links links;

	/**
	 * Построение объекта, совершающего вызовы веб-методов.
	 * @param links Менеджер ссылок к веб-методам.
	 * @throws IllegalArgumentException Если links == null или links.getProvider() == invalid.
	 */
	public WebMethodCaller(Links links) throws IllegalArgumentException
	{
		if(links == null)
			throw new IllegalArgumentException("Argument links cannot be null.");
		if(links.getProvider().equals(Links.LinksProvider.invalid))
			throw new IllegalArgumentException("Argument links has to have valid urls.");
		this.links = links;
	}
	/**
	 * Вызов веб-метода authenticate
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return Декодированный JSON-объект с результатами вызова
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public AuthenticateResponse authenticate(AuthenticatePayload payload) throws ResponseException
	{
		return webExecute(links.getAuthenticate(), payload, AuthenticateResponse.class);
	}
	/**
	 * Вызов веб-метода refresh
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return Декодированный JSON-объект с результатами вызова
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public RefreshResponse refresh(RefreshPayload payload) throws ResponseException
	{
		return webExecute(links.getRefresh(), payload, RefreshResponse.class);
	}
	/**
	 * Вызов веб-метода validate
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return true в случае успеха
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public boolean validate(ValidatePayload payload) throws ResponseException
	{
		webExecute(links.getValidate(), payload, null);
		return true;
	}
	/**
	 * Вызов веб-метода invalidate
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return true в случае успеха
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public boolean invalidate(InvalidatePayload payload) throws ResponseException
	{
		webExecute(links.getInvalidate(), payload, null);
		return true;
	}
	/**
	 * Вызов веб-метода signout
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return true в случае успеха
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public boolean signout(SignoutPayload payload) throws ResponseException
	{
		webExecute(links.getSignout(), payload, null);
		return true;
	}
	/**
	 * Вызов веб-метода join
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return true в случае успеха
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public boolean join(JoinPayload payload) throws ResponseException
	{
		webExecute(links.getJoin(), payload, null);
		return true;
	}
	/**
	 * Вызов веб-метода hasJoined
	 * @param payload Объект, кодируемый в JSON и помещаемый в нагрузку POST запроса
	 * @return true в случае успеха
	 * @throws ResponseException При возникновении любых ошибок
	 */
	public boolean hasJoined(InvalidatePayload payload) throws ResponseException
	{
		webExecute(links.getHasJoined(), null, null);
		return true;
	}
}
