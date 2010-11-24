package hatenahaiku4j.util;

import hatenahaiku4j.Const;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/** はてな記法コード */
	private static final String HATENA_NOTATION_CODE = new String(new char[]{
			'"',
			'&',
			'\'',
			'\\','(',
			'\\',')',
			':',
			';',
			'<',
			'=',
			'>',
			'\\','[',
			'\\',']',
	});

	/** はてな記法コードパターン */
	private static final Pattern HATENA_NOTATION_CODE_PATTERN = Pattern.compile("(?s)([" + HATENA_NOTATION_CODE + "])");
	
	/**
	 * 「:」や「[]」や「:」などのはてな記法で使用する文字をHTMLエスケープします。<br/>
	 * これを利用することで、例えばid:xxxはidxxxとなるため、idコールされなくなります。<br/>
	 * <br/>
	 * エスケープされる文字<br/>
	 * <ul>
	 * <li>" → &amp;#34;</li>
	 * <li>& → &amp;#38;</li>
	 * <li>' → &amp;#39;</li>
	 * <li>( → &amp;#40;</li>
	 * <li>) → &amp;#41;</li>
	 * <li>: → &amp;#58;</li>
	 * <li>; → &amp;#59;</li>
	 * <li>< → &amp;#60;</li>
	 * <li>= → &amp;#61;</li>
	 * <li>> → &amp;#62;</li>
	 * <li>[ → &amp;#91;</li>
	 * <li>] → &amp;#93;</li>
	 * </ul>
	 * 
	 * @param str エスケープ対象の文字列
	 * @return エスケープした文字列
	 * @since v1.0.1
	 */
	public static String escapeHatenaNotation(String str) {
		Matcher matcher = HATENA_NOTATION_CODE_PATTERN.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "&#" + String.valueOf((int) matcher.group(0).charAt(0)) + ";");
		}
	    matcher.appendTail(sb);
	    return sb.toString();
	}
	
	/** ユーザ名の&lt;TITLE&gt;タグパターン */
	private static final Pattern USER_NAME_TITLE_PATTERN =
		Pattern.compile("<title>(.+)さんのプロフィール - はてな</title>");
	
	/**
	 * はてなユーザIDからはてなユーザ名を取得します。<br/>
	 * 取得には、はてなプロフィール(http://www.hatena.ne.jp/はてなユーザID/)に表示される<br/>
	 * ユーザ名を利用します。
	 * 取得に失敗した場合、空文字を返却します。
	 * 
	 * @param userId はてなユーザID
	 * @return はてなユーザ名
	 * @since v1.0.3
	 */
	public static String getUserName(String userId) {
		String result = "";
		try {
			// GET:はてなプロフィールのHTML
			String html = HttpUtil.getText(Const.HATENA_PROFILE_BASE_URL + userId + Const.SLASH);
			Matcher matcher =  USER_NAME_TITLE_PATTERN.matcher(html);
			// 最初の一件目のみ取得、なければ空文字のまま
			if (matcher.find()) {
			    result = matcher.group(1);
			}
		} catch(Exception e) {
			// do nothing
		}
		return result;
	}
}
