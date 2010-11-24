package hatenahaiku4j;

/**
 * API��������\������N���X
 * 
 * @author fumokmm
 */
public class APINotSupportedException extends HatenaHaikuException {

	/** �V���A���o�[�W����UID */
	private static final long serialVersionUID = 5764076642207916324L;

	/**
	 * �R���X�g���N�^�ł��B
	 */
	public APINotSupportedException() {
		super("���߂�Ȃ����A�������ł��B�o�[�W�����A�b�v�����҂����������B");
	}

	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param message ���b�Z�[�W
	 */
	public APINotSupportedException(String message) {
		super(message);
	}
	
	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause �����ƂȂ�����O
	 */
	public APINotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}
}
