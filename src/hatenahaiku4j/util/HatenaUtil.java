package hatenahaiku4j.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/** �͂ĂȋL�@�R�[�h */
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

	/** �͂ĂȋL�@�R�[�h�p�^�[�� */
	private static final Pattern HATENA_NOTATION_CODE_PATTERN = Pattern.compile("(?s)([" + HATENA_NOTATION_CODE + "])");
	
	/**
	 * �u:�v��u[]�v��u:�v�Ȃǂ̂͂ĂȋL�@�Ŏg�p���镶����HTML�G�X�P�[�v���܂��B<br/>
	 * ����𗘗p���邱�ƂŁA�Ⴆ��id:xxx��idxxx�ƂȂ邽�߁Aid�R�[������Ȃ��Ȃ�܂��B<br/>
	 * <br/>
	 * �G�X�P�[�v����镶��<br/>
	 * <ul>
	 * <li>" �� &amp;#34;</li>
	 * <li>& �� &amp;#38;</li>
	 * <li>' �� &amp;#39;</li>
	 * <li>( �� &amp;#40;</li>
	 * <li>) �� &amp;#41;</li>
	 * <li>: �� &amp;#58;</li>
	 * <li>; �� &amp;#59;</li>
	 * <li>< �� &amp;#60;</li>
	 * <li>= �� &amp;#61;</li>
	 * <li>> �� &amp;#62;</li>
	 * <li>[ �� &amp;#91;</li>
	 * <li>] �� &amp;#93;</li>
	 * </ul>
	 * 
	 * @param str �G�X�P�[�v�Ώۂ̕�����
	 * @return �G�X�P�[�v����������
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
}
