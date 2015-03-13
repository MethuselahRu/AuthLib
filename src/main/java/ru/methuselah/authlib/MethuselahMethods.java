package ru.methuselah.authlib;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import ru.methuselah.authlib.data.AuthenticatePayload;
import ru.methuselah.authlib.data.AuthenticateResponse;
import ru.methuselah.authlib.data.InvalidatePayload;
import ru.methuselah.authlib.data.RefreshPayload;
import ru.methuselah.authlib.data.RefreshResponse;
import ru.methuselah.authlib.data.ErrorResponse;
import ru.methuselah.authlib.data.SignoutPayload;
import ru.methuselah.authlib.data.ValidatePayload;
import ru.methuselah.authlib.exceptions.ResponseException;

public class MethuselahMethods
{
	public static final String urlBase = "https://auth.methuselah.ru/";
	public static final String urlAuthenticate = urlBase + "authenticate.php";
	public static final String urlRefresh      = urlBase + "refresh.php";
	public static final String urlValidate     = urlBase + "validate.php";
	public static final String urlInvalidate   = urlBase + "invalidate.php";
	public static final String urlSignout      = urlBase + "signout.php";
	public static final String urlJoin         = urlBase + "join.php";
	public static final String urlHasJoined    = urlBase + "hasJoined.php";
	public static final String urlProfileInfo  = urlBase + "profile.php?uuid=";
	public static final String urlLegacyCheck  = urlBase + "legacy_check.php";
	public static final String urlLegacyJoin   = urlBase + "legacy_join.php";
	public static AuthenticateResponse authenticate(AuthenticatePayload payload) throws ResponseException
	{
		return action(urlAuthenticate, payload, AuthenticateResponse.class);
	}
	public static RefreshResponse refresh(RefreshPayload payload) throws ResponseException
	{
		return action(urlRefresh, payload, RefreshResponse.class);
	}
	public static boolean validate(ValidatePayload payload) throws ResponseException
	{
		action(urlValidate, payload, null);
		return true;
	}
	public static boolean signout(SignoutPayload payload) throws ResponseException
	{
		action(urlSignout, payload, null);
		return true;
	}
	public static boolean invalidate(InvalidatePayload payload) throws ResponseException
	{
		action(urlInvalidate, payload, null);
		return true;
	}
	protected static <T> T action(String url, Object payload, Class<T> responseClass) throws ResponseException
	{
		try
		{
			if(url.toLowerCase().startsWith("https"))
			{
				final SSLContext sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustManagers, null);
				HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			}
		} catch(NoSuchAlgorithmException ex) {
		} catch(KeyManagementException ex) {
		} catch(RuntimeException ex) {
		}
		final Gson gson = new Gson();
		final ErrorResponse rex = new ErrorResponse();
		rex.error = "";
		try
		{
			final HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setUseCaches(false);
			final DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			dos.write(gson.toJson(payload).getBytes("UTF-8"));
			dos.flush();
			dos.close();
			int responseCode = connection.getResponseCode();
			rex.errorMessage = Integer.toString(responseCode) + ": " + connection.getResponseMessage();
			if(responseCode == HttpURLConnection.HTTP_OK)
			{
				final JsonReader jr = new JsonReader(new InputStreamReader(connection.getInputStream()));
				return gson.fromJson(jr, responseClass);
			} else {
				final JsonReader jr = new JsonReader(new InputStreamReader(connection.getErrorStream()));
				final ErrorResponse info = gson.fromJson(jr, ErrorResponse.class);
				throw new ResponseException(info);
			}
		} catch(JsonParseException ex) {
			rex.error = ex.getLocalizedMessage();
			rex.errorMessage = ex.getMessage();
			throw new ResponseException(rex);
		} catch(MalformedURLException ex) {
			rex.error = ex.getLocalizedMessage();
			rex.errorMessage = ex.getMessage();
			throw new ResponseException(rex);
		} catch(IOException ex) {
			rex.error = ex.getLocalizedMessage();
			rex.errorMessage = ex.getMessage();
			throw new ResponseException(rex);
		} catch(ResponseException ex) {
			throw ex;
		}
	}
	private static class FakeTrustManager implements X509TrustManager
	{
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
		{
		}
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
		{
		}
		@Override
		public X509Certificate[] getAcceptedIssuers()
		{
			return new X509Certificate[0];
		}
	}
	private static final FakeTrustManager[] trustManagers = { new FakeTrustManager(), };
}
