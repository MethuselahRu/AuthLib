package ru.methuselah.authlib.exceptions;
import ru.methuselah.authlib.data.ErrorResponse;

public class ResponseException extends Exception
{
	public final ErrorResponse errorResponse;
	public ResponseException(ErrorResponse info)
	{
		this.errorResponse = info;
	}
}
