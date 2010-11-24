package hatenahaiku4j.util;

/**
 * ���̑����[�e�B���e�B�N���X
 * 
 * @author fumokmm
 */
public class Util {
	/**
	 * ID�p�^�[��<br>
	 * <ul>
	 * <li><li>���[�Uid�͔��p�p�������A�n�C�t���i - �j�A�A���_�[�o�[�i _ �j</li>
	 * <li>�擪�̓A���t�@�x�b�g</li>
	 * <li>�����̓A���t�@�x�b�g������</li>
	 * <li>3�����ȏ�</li>
	 * </ul>
	 */
	private static final String ID_PATTERN = "id:[a-zA-Z][a-zA-Z0-9_-]{1,}[a-zA-Z0-9]";

	/**
	 * �w�肳�ꂽ�����񂪂͂Ă�ID�p�^�[���Ƀ}�b�`���邩�ǂ������肵�܂��B
	 * 
	 * @param idStr �͂Ă�ID�p�^�[��������
	 * @return �͂Ă�ID�p�^�[���̏ꍇtrue, �łȂ����false
	 */
	public static boolean isHatenaIdFormat(String idStr) {
		if (idStr == null) return false;
		return idStr.matches("^" + ID_PATTERN + "$");
	}
}
