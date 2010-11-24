package hatenahaiku4j;

/**
 * はてなハイク例外を表現するクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class HatenaHaikuException extends Exception {
	/** シリアルバージョンUID */
	private static final long serialVersionUID = 8268709340102051276L;

	/**
	 * コンストラクタです。
	 * 
	 * @since v0.0.1
	 */
	HatenaHaikuException() {
		super();
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param message メッセージ
	 * @since v0.0.1
	 */
	HatenaHaikuException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタです。
	 * 
	 * @param message メッセージ
	 * @param cause 原因となった例外
	 * @since v0.0.1
	 */
	HatenaHaikuException(String message, Throwable cause) {
		super(message, cause);
	}
}
