package hatenahaiku4j.util;

/**
 * �����񏈗��Ɋւ��郆�[�e�B���e�B�N���X
 * 
 * @author fumokmm
 */
public class StringUtil {

	/**
	 * �󕶎����ǂ������肷��B
	 * 
	 * @param str ���肷�镶����
	 * @return �󕶎��̏ꍇtrue, �����łȂ��ꍇfalse
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
	 */
	public static boolean isSame(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

}
