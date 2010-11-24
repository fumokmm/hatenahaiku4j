package hatenahaiku4j.util;

import hatenahaiku4j.Const;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文字列処理に関するユーティリティクラス
 * 
 * @since v0.0.3
 * @author fumokmm
 */
public class StringUtil {

	/**
	 * 空文字かどうか判定する。
	 * 
	 * @param str 判定する文字列
	 * @return 空文字の場合true, そうでない場合false
	 * @since v0.0.3
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 文字列が等しいか判定する。(NULL許容)
	 * 
	 * @param str1 判定する文字列1
	 * @param str2 判定する文字列1
	 * @return 等しい場合true, そうでない場合false
	 * @since v0.0.3
	 */
	public static boolean isSame(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * 引数の文字列をUTF-8でURLEncodeします。<br/>
	 * UTF-8でエンコードできないことはないはずなので、エラー処理は省略しています。
	 * 
	 * @param str URLEncodeする文字列
	 * @return エンコードされた文字列
	 * @see <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/net/URLEncoder.html">http://java.sun.com/j2se/1.5.0/docs/api/java/net/URLEncoder.html</a>
	 * @since v1.0.0
	 */
	public static String encode(String str) {
		String encoded = "";
		try {
			// UTF-8でエンコードしてから
			// 「+」を「%20」に(半角スペース)、
			// 「.」を「%2E」に、
			// 「-」を「%2D」に、
			// 「*」を「%2A」に、
			// 「_」を「%5F」に変換する。
			encoded = URLEncoder.encode(str, Const.UTF8)
				.replaceAll("\\+", urlEncodeOther(" "))
				.replaceAll("\\.", urlEncodeOther("."))
				.replaceAll("-", urlEncodeOther("-"))
				.replaceAll("\\*", urlEncodeOther("*"))
				.replaceAll("_", urlEncodeOther("_"))
			;
		} catch (UnsupportedEncodingException e) {
			// ignore it
		}
		return encoded;
	}

	/**
	 * {@link URLEncoder#encode(String, String)}にて変換してくれない文字をエンコードする。
	 * 
	 * @param str エンコードする文字列
	 * @since v1.1.4
	 * @return 変換後の文字列
	 */
	private static String urlEncodeOther(String str) {
		try {
			return "%" + String.format("%02x", str.getBytes(Const.UTF8)[0]).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			// ignore it
		}
		return "";
	}
	
	/**
	 * シリアル値を取得します。<br/>
	 * シリアル値＝現在時刻のミリ秒＋ランダム値
	 * 
	 * @return シリアル値を取得します。
	 * @since v1.0.0
	 */
	public static String getSerial() {
		return String.valueOf(System.currentTimeMillis())
		+ String.valueOf(Math.random()).replaceAll("[-.]", "");
	}
	
	/**
	 * 一括置換を行います。<br/>
	 * templateがnull, replaceがnullまたは長さが不正(2の倍数でない)場合、空文字を返却します。
	 * 
	 * @param template 置換を施すベースとなるテンプレート
	 * @param replace 置換文字列
	 * @return 一括置換された文字列
	 * @since v1.2.0
	 */
	public static String lumpReplace(String template, String... replace) {
		if (template == null) {
			return "";
		}
		if (replace == null) {
			return "";
		}
		if (replace.length < 2 || replace.length % 2 != 0) {
			return "";
		}
		String result = template;
		for (int i = 0; i < replace.length; i += 2) {
			result = result.replaceAll(replace[i], replace[i + 1]);
		}
		return result;
	}
	
}
