package hatenahaiku4j.util;

import hatenahaiku4j.Const;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.ImageMime;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.QueryParameter;
import hatenahaiku4j.UpdateParameter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	/** リクエストプロパティ:コネクション */
	protected static final String REQUEST_PROPERTY_CONNECTION = "Connection";
	/** リクエストプロパティ:コネクション (値:close) */
	protected static final String REQUEST_PROPERTY_CONNECTION_CLOSE = "close";
	/** リクエストプロパティ:コンテントタイプ */
	protected static final String REQUEST_PROPERTY_CONTENT_TYPE = "Content-Type";
	
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
	 * @throws MalformedURLException
	 * @throws IOException
	 * @since v0.0.1
	 */
	public static String doGet(LoginUser loginUser, String url,
			QueryParameter param, boolean needLog) throws
			MalformedURLException, IOException {

		HttpURLConnection urlconn = null; // コネクション
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.GET.name());
			if (loginUser != null) {
				urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			}
			// GETだが、outputStreamを取得するたoutputもtrueとする
			urlconn.setDoInput(true);		// for GET
			urlconn.setDoOutput(true);		// for POST
			urlconn.setUseCaches(false);	// disable cache
			// 最初にして最後の要求
			urlconn.setRequestProperty(REQUEST_PROPERTY_CONNECTION, REQUEST_PROPERTY_CONNECTION_CLOSE);
			
			// ポスト内容の表示
			if (param != null) {
				if (needLog) {
					param.outputPostInfo();
				}
			}

			OutputStream os = urlconn.getOutputStream();
			PostStream ps = new PostStream(os);
			if (param != null) {
				param.addParameter(ps);
			}
			ps.close();

			// レスポンス
			return getResponse(urlconn, needLog);

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
			// POSTのため、Input/Outputともにtrue
			urlconn.setDoInput(true);		// for GET
			urlconn.setDoOutput(true);		// for POST
			urlconn.setUseCaches(false);	// disable cache
			// 最初にして最後の要求
			urlconn.setRequestProperty(REQUEST_PROPERTY_CONNECTION, REQUEST_PROPERTY_CONNECTION_CLOSE);

			// multipart/form-dataでポストするためのバウンダリ文字列を生成
			String boundary = "--------------------" + Const.API_NAME + StringUtil.getSerial();
			urlconn.setRequestProperty(REQUEST_PROPERTY_CONTENT_TYPE, "multipart/form-data; boundary=" + boundary);
			
			// ポスト内容の表示
			if (needLog) {
				param.outputPostInfo(loginUser);
			}

			OutputStream os = urlconn.getOutputStream();
			PostStream ps = new PostStream(os, boundary);
			param.addParameter(loginUser, ps);
			ps.close();
			
			// レスポンス
			return getResponse(urlconn, needLog);

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
	 * @throws MalformedURLException
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
	 * ポスト処理補助クラス。<br/>
	 * パラメータを生成してポストします。
	 * 
	 * @author fumokmm
	 * @since v1.0.0
	 */
	public static class PostStream {
		/** データ書き込み用 */
		protected final DataOutputStream out;
		/** テキスト書き込み用 */
		protected final Writer wout;
		
		/** バウンダリー */
		private final String boundary;
		/** セパレータ */
		private static final String SEPARATOR = "--";
		/** 行区切り */
		private static final byte[] CRLF = { 0x0d, 0x0a };
		/** バッファサイズ */
		private final int BUFSIZE = 8192;

		/** デリミタが必要かどうか */
		private boolean needDelimeter = false;

		/**
		 * 「URLエンコード」モードのデータストリームを作成します。<br>
		 * addProperty(java.lang.String, java.lang.String) で追加されるデータは適切に URL エンコードされます。<br>
		 * {@link #close()}すると、Content-Type を "application/x-www-urlencoded" として POST リクエストを実行します。
		 *
		 * @param o URLConnection.getOutputStream() で返される {@link URLConnection#getOutputStream}
		 * @since v1.0.0
		 */
		public PostStream(OutputStream o) { 
		    this(o, null);
		}
		
		/**
		 * 「マルチパート」モードのデータストリームを作成します。
		 * addProperty(java.lang.String, java.lang.String)や
		 * addFile(java.io.File, java.lang.String, java.lang.String)で
		 * 追加されるデータはマルチパート MIME 形式にフォーマットされます。
		 * close() すると、Content-Type を "multipart/form-data" として POST リクエストを実行します。
		 *
		 * @param o {@link URLConnection#getOutputStream} で返される OutputStream
		 * @param boundary マルチパート MIME の区切文字列
		 * @since v1.0.0
		 */
		public PostStream(OutputStream o, String boundary) {
			if (o == null) {
				throw new NullPointerException("o is null.");
			}
			
			out = new DataOutputStream(o);

			Writer tmp_wout = null;
			try {
				tmp_wout = new OutputStreamWriter(out, Const.UTF8);
			} catch (UnsupportedEncodingException e) {
				// ignore it
		    } finally {
		    	wout = tmp_wout;
		    }

		    this.boundary = boundary;
		}

		/**
		 * パラメータをストリームに追加します。
		 *
		 * @param name パラメータ名
		 * @param value パラメータの値
		 * @throws IOException 書き込み時にエラーが発生した
		 * @since v1.0.0
		 */
		public void addProperty(String name, String value) throws IOException {
			if (boundary == null) {
				if (needDelimeter) {
					wout.write('&');
				}
				String encoded = StringUtil.encode(name) + Const.EQUAL + StringUtil.encode(value);
				wout.write(encoded);
				wout.flush();
				needDelimeter = true;
				return;
			}
			writeSeparatorBoundary();
			writeContentDisposition(name);
			writeValue(value);
		}

		/**
		 * ファイルの内容をストリームに追加します。<br>
		 * 「マルチパート」モードのストリームでしか使用できません。
		 *
		 * @param file 追加するファイル
		 * @param name パラメータ名
		 * @param mimeType このファイルの MIME タイプ
		 * @throws IOException 通信エラーが発生
		 * @since v1.0.0
		 */
		public void addFile(File file, String name, ImageMime mimeType) throws IOException {
			if (boundary == null) {
				throw new IllegalStateException("could not add a file for this stream.");
			}
			writeSeparatorBoundary();
			// ファイル名はとりあえずなんでもいいようなので、mimeTypeを加工してファイル名としている
			writeContentDisposition(name, mimeType.toTemporaryFileName());
			writeContentType(mimeType.getMimeType());
			writeValue(file);
		}
		
		/**
		 * URL上からバイトストリームを取得し、ファイルとして追加します。<br>
		 * 「マルチパート」モードのストリームでしか使用できません。
		 *
		 * @param imageUrl 追加するファイル
		 * @param name パラメータ名
		 * @param mimeType このファイルの MIME タイプ
		 * @throws IOException 通信エラーが発生
		 * @since v1.0.0
		 */
		public void addImageUrl(String imageUrl, String name, ImageMime mimeType) throws IOException {
			if (boundary == null) {
				throw new IllegalStateException("could not add a image file for this stream.");
			}
			writeSeparatorBoundary();
			// ファイル名はとりあえずなんでもいいようなので、mimeTypeを加工してファイル名としている
			writeContentDisposition(name, mimeType.toTemporaryFileName());
			writeContentType(mimeType.getMimeType());
			writeValue(new URL(imageUrl));
		}

		/**
		 * 画像バイナリデータをストリームに追加します。<br>
		 * 「マルチパート」モードのストリームでしか使用できません。
		 *
		 * @param imageData 画像バイナリデータ
		 * @param name パラメータ名
		 * @param mimeType このファイルの MIME タイプ
		 * @throws IOException 通信エラーが発生
		 * @since v1.0.0
		 */
		public void addImageData(byte[] imageData, String name, ImageMime mimeType) throws IOException {
			if (boundary == null) {
				throw new IllegalStateException("could not add a image data for this stream.");
			}
			writeSeparatorBoundary();
			// ファイル名はとりあえずなんでもいいようなので、mimeTypeを加工してファイル名としている
			writeContentDisposition(name, mimeType.toTemporaryFileName());
			writeContentType(mimeType.getMimeType());
			writeValue(imageData);
		}

		/**
		 * このストリームを閉じて、ポストします。
		 * 
		 * @throws IOException
		 * @since v1.0.0
		 */
		public void close() throws IOException {
			if (boundary != null) {
				writeTerminate();
			}

			try {
				out.flush();
	    	} finally {
	    		try {
		    		if (out != null) {
						out.close();
		    		}
		    		if (wout != null) {
						wout.close();
		    		}
		    	} catch (Exception e) {
		    		// ignore it
		    	}
			}
		}
		
		/**
		 * セパレーター＋バウンダリーをストリームに書き込みます。
		 * 
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeSeparatorBoundary() throws IOException {
			out.writeBytes(SEPARATOR);
			out.writeBytes(boundary);
			out.write(CRLF);
		}
		
		/**
		 * コンテントディスポジションをストリームに書き込みます。
		 * 
		 * @param name パラメータ名
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeContentDisposition(String name) throws IOException {
			wout.write("Content-Disposition: form-data; name=\"" + name + "\"");
			wout.flush();
			out.write(CRLF);
		}
		
		/**
		 * コンテントディスポジションをストリームに書き込みます。
		 * 
		 * @param name パラメータ名
		 * @param fileName ファイル名
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeContentDisposition(String name, String fileName) throws IOException {
			wout.write("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"");
			wout.flush();
			out.write(CRLF);
		}
		
		/**
		 * コンテンツタイプをストリームに書き込みます。
		 * 
		 * @param mimeType マイムタイプ
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeContentType(String mimeType) throws IOException {
			wout.write("Content-Type: " + mimeType);
			wout.flush();
			out.write(CRLF);
		}
		
		/**
		 * 文字列をストリームに書き込みます。
		 * 
		 * @param value ストリームに書き込む文字列
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(String value) throws IOException {
			out.write(CRLF);
			wout.write(value);
			wout.flush();
			out.write(CRLF);
		}

		/**
		 * バイト配列をストリームに書き込みます。
		 * 
		 * @param value ストリームに書き込むバイト配列
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(byte[] value) throws IOException {
			out.write(CRLF);
			out.write(value, 0, value.length);
			out.write(CRLF);
		}
		
		/**
		 * ファイルをストリームに書き込みます。
		 * 
		 * @param file ストリームに書き込むファイル
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(File file) throws IOException {
		    writeValue(FileUtil.toByteArray(file));
		}

		/**
		 * 
		 * @param imageUrl
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(URL imageUrl) throws IOException {
			InputStream in = null;
			ByteArrayOutputStream out = null;
		    try {
		    	in = imageUrl.openStream();
		    	out = new ByteArrayOutputStream();
		    	byte[] buffer = new byte[BUFSIZE];
		    	int rsize;
		    	while ((rsize = in.read(buffer)) != -1) {
		    		out.write(buffer, 0, rsize);
		    	}
		    } finally {
		    	try {
		    		if (in != null) {
		    			in.close();
		    		}
		    		if (out != null) {
		    			out.flush();
		    			out.close();
		    		}
		    	} catch (Exception e) {
		    	}
		    }
		    
		    writeValue(out.toByteArray());
		}
		
		/**
		 * 
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeTerminate() throws IOException {
			out.writeBytes(SEPARATOR);
			out.writeBytes(boundary);
			out.writeBytes(SEPARATOR);
		}
	}
}
