package hatenahaiku4j;

/**
 * �͂Ăȃn�C�N��O��\������N���X
 * 
 * @author fumokmm
 */
public class HatenaHaikuException extends Exception {

	/** �V���A���o�[�W����UID */
	private static final long serialVersionUID = 8268709340102051276L;

	/**
	 * �R���X�g���N�^�ł��B
	 */
	public HatenaHaikuException() {
		super();
	}

	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param message ���b�Z�[�W
	 */
	public HatenaHaikuException(String message) {
		super(message);
	}
	
	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param message ���b�Z�[�W
	 * @param cause �����ƂȂ�����O
	 */
	public HatenaHaikuException(String message, Throwable cause) {
		super(message, cause);
	}
}
