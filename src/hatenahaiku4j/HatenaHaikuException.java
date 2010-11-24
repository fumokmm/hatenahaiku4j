package hatenahaiku4j;

/**
 * はてなハイク例外を表現するクラス
 * 
 * @author fumokmm
 */
public class HatenaHaikuException extends Exception {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 8268709340102051276L;

	/**
	 * コンストラクタです。
	 */
	public HatenaHaikuException() {
		super();
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param message メッセージ
	 */
	public HatenaHaikuException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタです。
	 * 
	 * @param message メッセージ
	 * @param cause 原因となった例外
	 */
	public HatenaHaikuException(String message, Throwable cause) {
		super(message, cause);
	}
}
