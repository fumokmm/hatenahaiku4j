package hatenahaiku4j;

import static hatenahaiku4j.Const.*;

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
		return API_NAME + " " + VERSION;
	}
}
