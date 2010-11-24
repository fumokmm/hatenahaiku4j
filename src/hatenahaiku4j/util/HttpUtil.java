package hatenahaiku4j.util;

import hatenahaiku4j.Const;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.QueryParameter;
import hatenahaiku4j.UpdateParameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * HTTPリクエストに関するユーティリティクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class HttpUtil {
	/** METHOD */
	private static enum Method { POST, GET }
	/** リクエストプロパティ：BASIC認証 */
	protected static final String REQUEST_PROPERTY_AUTHORIZATION = "Authorization";
	
	/**
	 * ゲットします。
	 * 
	 * @param url リクエストURL
	 * @param param リクエストパラメータ情報
	 * @param needLog ログ出力要否
	 * @return レスポンスボディの文字列
	 * @throws MalformedURLException
	 * @throws IOException
	 * @since v0.0.1
	 */
	public static String doGet(String url, QueryParameter param, boolean needLog) throws MalformedURLException, IOException {
		return doGet(null, url, param, needLog);
	}

	/**
	 * ゲットします。
	 * 
	 * @param loginUser ログインユーザ
	 * @param url リクエストURL
	 * @param param リクエストパラメータ情報
	 * @param needLog ログ出力要否
	 * @return レスポンスボディの文字列
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @since v0.0.1
	 */
	public static String doGet(LoginUser loginUser, String url,
			QueryParameter param, boolean needLog) throws UnsupportedEncodingException,
			MalformedURLException, IOException {

		HttpURLConnection urlconn = null; // コネクション
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.GET.name());
			if (loginUser != null) {
				urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			}
			urlconn.setDoOutput(true);
			
			// ポスト内容の表示
			if (param != null) {
				if (needLog) {
					param.outputPostInfo();
				}
			}

			PrintWriter writer = new PrintWriter(urlconn.getOutputStream());
			if (param != null) {
				writer.write(param.toParameter());
			}
			writer.flush();
			writer.close();

			// レスポンス
			return getResponse(urlconn, needLog);

		} catch (UnsupportedEncodingException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (MalformedURLException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} finally {
			// コネクション切断
			urlconn.disconnect();
		}
	}

	/**
	 * ポストします。
	 * 
	 * @param loginUser ログインユーザ
	 * @param url リクエストURL
	 * @param param リクエストパラメータ情報
	 * @param needLog ログ出力要否
	 * @return レスポンスボディの文字列
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public static String doPost(LoginUser loginUser, String url,
			UpdateParameter param, boolean needLog) throws UnsupportedEncodingException,
			MalformedURLException, IOException, HatenaHaikuException {

		HttpURLConnection urlconn = null; // コネクション
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.POST.name());
			urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			// キーワード未指定の場合は、ログインユーザのIDとする。
			if (param.getKeyword() == null || param.getKeyword().equals("")) {
				param.setKeyword(loginUser.getUserIdNotation());
			}
			urlconn.setDoOutput(true);
			
			// ポスト内容の表示
			if (needLog) {
				param.outputPostInfo(loginUser);
			}

			PrintWriter writer = new PrintWriter(urlconn.getOutputStream());
			// TODO 画像ファイルを投稿 (file パラメータを指定) する場合は、multipart/form-data でエンコードして下さい。
			writer.write(param.toParameter(loginUser));
			writer.flush();
			writer.close();
			
			// レスポンス
			return getResponse(urlconn, needLog);

		} catch (UnsupportedEncodingException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (MalformedURLException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} finally {
			// コネクション切断
			urlconn.disconnect();
		}
	}
	
	/**
	 * レスポンスを返却します。
	 * 
	 * @param urlconn　HTTPコネクション
	 * @param needLog ログ出力要否
	 * @return レスポンスボディの文字列
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @since v0.0.1
	 */
	private static String getResponse(HttpURLConnection urlconn, boolean needLog)
			throws MalformedURLException, IOException {

		try {
			// レスポンス
			Map<String, List<String>> headers = urlconn.getHeaderFields();
			if (needLog) {
				for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
			}
			// ボディ
			StringBuilder responseBody = new StringBuilder();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), Const.UTF8));
			String line;
			while((line = responseReader.readLine()) != null) {
				responseBody.append(line);
			}

			if (needLog) {
				System.out.println("----------------");
				System.out.println("レスポンスコード: " + urlconn.getResponseCode());
				System.out.println("レスポンスメッセージ: " + urlconn.getResponseMessage());
				System.out.println("----------------");
				System.out.println(urlconn.getRequestMethod() + " successfully");
			}
			return responseBody.toString();

		} catch (UnsupportedEncodingException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} finally {
			// コネクション切断
			urlconn.disconnect();
		}
	}
}
