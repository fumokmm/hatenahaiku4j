package hatenahaiku4j;

/**
 * �͂Ăȃn�C�N��O��\������N���X
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class HatenaHaikuException extends Exception {
	/** �V���A���o�[�W����UID */
	private static final long serialVersionUID = 8268709340102051276L;

	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @since v0.0.1
	 */
	HatenaHaikuException() {
		super();
	}

	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param message ���b�Z�[�W
	 * @since v0.0.1
	 */
	HatenaHaikuException(String message) {
		super(message);
	}
	
	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause �����ƂȂ�����O
	 * @since v0.0.1
	 */
	HatenaHaikuException(String message, Throwable cause) {
		super(message, cause);
	}
}
