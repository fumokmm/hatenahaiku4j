package hatenahaiku4j;

/**
 * API未実装を表現するクラス
 * 
 * @author fumokmm
 */
public class APINotSupportedException extends HatenaHaikuException {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 5764076642207916324L;

	/**
	 * コンストラクタです。
	 */
	public APINotSupportedException() {
		super("ごめんなさい、未実装です。バージョンアップをお待ちください。");
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param message メッセージ
	 */
	public APINotSupportedException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタです。
	 * 
	 * @param message メッセージ
	 * @param cause 原因となった例外
	 */
	public APINotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}
}
