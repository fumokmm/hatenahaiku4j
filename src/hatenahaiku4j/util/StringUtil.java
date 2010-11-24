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
	 * @since v1.0.0
	 */
	public static String encode(String str) {
		String encoded = "";
		try {
			encoded = URLEncoder.encode(str, Const.UTF8);
		} catch ( UnsupportedEncodingException e ) {
			// ignore it
		}
		return encoded;
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

}
