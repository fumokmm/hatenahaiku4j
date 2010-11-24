package hatenahaiku4j.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日時に関するユーティリティクラス
 * 
 * @author fumokmm
 */
public class DateUtil {

	/** TimeZone(JST) */
	static final TimeZone TZ_GMT0 = TimeZone.getTimeZone("GMT+00");
	/** TimeZone(JST) */
	static final TimeZone TZ_JST = TimeZone.getTimeZone("GMT+09");
	/** datetimeTZ形式フォーマッター */
	private static final DateFormat TZFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	/**
	 * datetimeTZ形式からDateに変換
	 * 
	 * @param datetimeTZ datetimeTZ形式の文字列
	 * @return 日本標準時のDate
	 * @throws ParseException 
	 */
	public static Date toJSTDate(String datetimeTZ) throws ParseException {
	    TZFormatter.setTimeZone(TZ_GMT0);
	    return adjustToJST(TZFormatter.parse(datetimeTZ));
	}

	/**
	 * DateをdatetimeTZ形式に変換
	 * 
	 * @param defaultDate 日本標準時のDate
	 * @return datetimeTZ形式の文字列
	 */
	public static String toDefaultTZ(Date defaultDate) {
	    TZFormatter.setTimeZone(TZ_GMT0);
	    return TZFormatter.format(adjustToDefault(defaultDate));
	}

	/**
	 * 時間をずらして現在時刻を取得します。(+9時間)
	 */
	public static Date getNow() {
		return adjustToJST(new Date());
	}

	/**
	 * 日本時間になるよう、時間を調整します。(GMT+0→JST(GMT+09))
	 */
	public static Date adjustToJST(Date date) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() - TimeZone.getDefault().getRawOffset());
		newDate.setTime(newDate.getTime() + TZ_JST.getRawOffset());
		return newDate;
	}

	/**
	 * GMT+0になるよう、時間を調整します。(JST(GMT+09)→GMT+0)
	 */
	public static Date adjustToDefault(Date date) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() - TZ_JST.getRawOffset());
		newDate.setTime(newDate.getTime() + TimeZone.getDefault().getRawOffset());
		return newDate;
	}

}
