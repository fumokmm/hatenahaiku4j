package hatenahaiku4j.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �͂Ăȃ��[�e�B���e�B�N���X
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class HatenaUtil {

	/**
	 * ID�p�^�[��<br>
	 * <ul>
	 * <li>���[�Uid�͔��p�p�������A�n�C�t���i - �j�A�A���_�[�o�[�i _ �j</li>
	 * <li>�擪�̓A���t�@�x�b�g</li>
	 * <li>�����̓A���t�@�x�b�g������</li>
	 * <li>3�����ȏ�</li>
	 * </ul>
	 * 
	 * @since v0.0.1
	 */
	private static final String ID_NOTATION_PATTERN = "id:[a-zA-Z][a-zA-Z0-9_-]{1,}[a-zA-Z0-9]";

	/** �͂ĂȕW�����t�t�H�[�}�b�g(yyyy-MM-dd HH:mm:ss) */
	private static final DateFormat HATENA_DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * �w�肳�ꂽ�����񂪂͂Ă�ID�p�^�[���Ƀ}�b�`���邩�ǂ������肵�܂��B
	 * 
	 * @param idStr �͂Ă�ID�p�^�[��������
	 * @return �͂Ă�ID�p�^�[���̏ꍇtrue, �łȂ����false
	 * @since v0.2.0
	 */
	public static boolean isIdNotation(String idStr) {
		if (idStr == null) return false;
		return idStr.matches("^" + ID_NOTATION_PATTERN + "$");
	}

	/**
	 * �͂ĂȕW�����t�t�H�[�}�b�g�`���i��F2009-09-14 20:18:59)����{�W������Date�ɕϊ����܂��B
	 * 
	 * @param dateString �͂ĂȕW�����t�t�H�[�}�b�g�`���̕�����
	 * @return ���{�W������Date
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
	 * Date���͂ĂȕW�����t�t�H�[�}�b�g�`���i��F2009-09-14 20:18:59)�̕�����ɕϊ����܂��B
	 * 
	 * @param date ���t
	 * @return �͂ĂȕW�����t�t�H�[�}�b�g�`���̕�����
	 * @since v0.2.0
	 */
	public static String formatDate(Date date) {
		return HATENA_DEFAULT_DATE_FORMAT.format(date);
	}

}
