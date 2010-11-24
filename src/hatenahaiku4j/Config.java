package hatenahaiku4j;

/**
 * 設定ファイル
 * 
 * @since v1.1.1
 * @author fumokmm
 */
public class Config {
	
	/**
	 * HatenaHaiku4Jのバージョン情報を返却します。
	 * 
	 * @return HatenaHaiku4Jのバージョン情報
	 * @since v1.1.1
	 */
	public static String getVersion() {
		return Const.API_NAME + " " + Const.VERSION;
	}
}
