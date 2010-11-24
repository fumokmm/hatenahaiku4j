package hatenahaiku4j.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * はてなユーティリティクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class HatenaUtil {

	/**
	 * IDパターン<br>
	 * <ul>
	 * <li>ユーザidは半角英数字か、ハイフン（ - ）、アンダーバー（ _ ）</li>
	 * <li>先頭はアルファベット</li>
	 * <li>末尾はアルファベットか数字</li>
	 * <li>3文字以上</li>
	 * </ul>
	 * 
	 * @since v0.0.1
	 */
	private static final String ID_NOTATION_PATTERN = "id:[a-zA-Z][a-zA-Z0-9_-]{1,}[a-zA-Z0-9]";

	/** はてな標準日付フォーマット(yyyy-MM-dd HH:mm:ss) */
	private static final DateFormat HATENA_DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 指定された文字列がはてなIDパターンにマッチするかどうか判定します。
	 * 
	 * @param idStr はてなIDパターン文字列
	 * @return はてなIDパターンの場合true, でなければfalse
	 * @since v0.2.0
	 */
	public static boolean isIdNotation(String idStr) {
		if (idStr == null) return false;
		return idStr.matches("^" + ID_NOTATION_PATTERN + "$");
	}

	/**
	 * はてな標準日付フォーマット形式（例：2009-09-14 20:18:59)を日本標準時のDateに変換します。
	 * 
	 * @param dateString はてな標準日付フォーマット形式の文字列
	 * @return 日本標準時のDate
	 * @since v0.2.0
	 */
	public static Date parseDate(String dateString) {
		try {
			return DateUtil.adjustToJST(HATENA_DEFAULT_DATE_FORMAT.parse(dateString));
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Dateをはてな標準日付フォーマット形式（例：2009-09-14 20:18:59)の文字列に変換します。
	 * 
	 * @param date 日付
	 * @return はてな標準日付フォーマット形式の文字列
	 * @since v0.2.0
	 */
	public static String formatDate(Date date) {
		return HATENA_DEFAULT_DATE_FORMAT.format(date);
	}

}
