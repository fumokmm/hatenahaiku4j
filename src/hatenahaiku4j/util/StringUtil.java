package hatenahaiku4j.util;

/**
 * 文字列処理に関するユーティリティクラス
 * 
 * @author fumokmm
 */
public class StringUtil {

	/**
	 * 空文字かどうか判定する。
	 * 
	 * @param str 判定する文字列
	 * @return 空文字の場合true, そうでない場合false
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
	 */
	public static boolean isSame(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

}
