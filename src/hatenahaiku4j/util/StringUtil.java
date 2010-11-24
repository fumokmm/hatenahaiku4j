package hatenahaiku4j.util;

import hatenahaiku4j.Const;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * �����񏈗��Ɋւ��郆�[�e�B���e�B�N���X
 * 
 * @since v0.0.3
 * @author fumokmm
 */
public class StringUtil {

	/**
	 * �󕶎����ǂ������肷��B
	 * 
	 * @param str ���肷�镶����
	 * @return �󕶎��̏ꍇtrue, �����łȂ��ꍇfalse
	 * @since v0.0.3
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * �����񂪓����������肷��B(NULL���e)
	 * 
	 * @param str1 ���肷�镶����1
	 * @param str2 ���肷�镶����1
	 * @return �������ꍇtrue, �����łȂ��ꍇfalse
	 * @since v0.0.3
	 */
	public static boolean isSame(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * �����̕������UTF-8��URLEncode���܂��B<br/>
	 * UTF-8�ŃG���R�[�h�ł��Ȃ����Ƃ͂Ȃ��͂��Ȃ̂ŁA�G���[�����͏ȗ����Ă��܂��B
	 * 
	 * @param str URLEncode���镶����
	 * @return �G���R�[�h���ꂽ������
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
	 * �V���A���l���擾���܂��B<br/>
	 * �V���A���l�����ݎ����̃~���b�{�����_���l
	 * 
	 * @return �V���A���l���擾���܂��B
	 * @since v1.0.0
	 */
	public static String getSerial() {
		return String.valueOf(System.currentTimeMillis())
		+ String.valueOf(Math.random()).replaceAll("[-.]", "");
	}

}
