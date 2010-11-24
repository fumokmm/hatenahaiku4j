package hatenahaiku4j.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * �����Ɋւ��郆�[�e�B���e�B�N���X
 * 
 * @author fumokmm
 */
public class DateUtil {

	/** TimeZone(JST) */
	static final TimeZone TZ_GMT0 = TimeZone.getTimeZone("GMT+00");
	/** TimeZone(JST) */
	static final TimeZone TZ_JST = TimeZone.getTimeZone("GMT+09");
	/** datetimeTZ�`���t�H�[�}�b�^�[ */
	private static final DateFormat TZFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	/**
	 * datetimeTZ�`������Date�ɕϊ�
	 * 
	 * @param datetimeTZ datetimeTZ�`���̕�����
	 * @return ���{�W������Date
	 * @throws ParseException 
	 */
	public static Date toJSTDate(String datetimeTZ) throws ParseException {
	    TZFormatter.setTimeZone(TZ_GMT0);
	    return adjustToJST(TZFormatter.parse(datetimeTZ));
	}

	/**
	 * Date��datetimeTZ�`���ɕϊ�
	 * 
	 * @param defaultDate ���{�W������Date
	 * @return datetimeTZ�`���̕�����
	 */
	public static String toDefaultTZ(Date defaultDate) {
	    TZFormatter.setTimeZone(TZ_GMT0);
	    return TZFormatter.format(adjustToDefault(defaultDate));
	}

	/**
	 * ���Ԃ����炵�Č��ݎ������擾���܂��B(+9����)
	 */
	public static Date getNow() {
		return adjustToJST(new Date());
	}

	/**
	 * ���{���ԂɂȂ�悤�A���Ԃ𒲐����܂��B(GMT+0��JST(GMT+09))
	 */
	public static Date adjustToJST(Date date) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() - TimeZone.getDefault().getRawOffset());
		newDate.setTime(newDate.getTime() + TZ_JST.getRawOffset());
		return newDate;
	}

	/**
	 * GMT+0�ɂȂ�悤�A���Ԃ𒲐����܂��B(JST(GMT+09)��GMT+0)
	 */
	public static Date adjustToDefault(Date date) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() - TZ_JST.getRawOffset());
		newDate.setTime(newDate.getTime() + TimeZone.getDefault().getRawOffset());
		return newDate;
	}

}
