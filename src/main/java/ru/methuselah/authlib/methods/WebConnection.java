package ru.methuselah.authlib.methods;

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
import ru.methuselah.authlib.data.ErrorResponse;

/**
 * Объект, совершающий веб-запросы
 */
abstract class WebConnection
{
	protected <T> T webExecute(String url, Object payload, Class<T> responseClass) throws ResponseException
	{
		if(url.startsWith("https"))
			hackSSL();
		final Gson gson = new Gson();
		final ErrorResponse rex = new ErrorResponse();
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
	public static void hackSSL()
	{
		try
		{
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, fakeTrustManagerList, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		} catch(NoSuchAlgorithmException ex) {
		} catch(KeyManagementException ex) {
		} catch(RuntimeException ex) {
		}
	}
	private static final X509TrustManager[] fakeTrustManagerList =
	{
		new X509TrustManager()
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
		},
	};
}
