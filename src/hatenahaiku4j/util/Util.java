package hatenahaiku4j.util;

/**
 * その他ユーティリティクラス
 * 
 * @author fumokmm
 */
public class Util {
	/**
	 * IDパターン<br>
	 * <ul>
	 * <li><li>ユーザidは半角英数字か、ハイフン（ - ）、アンダーバー（ _ ）</li>
	 * <li>先頭はアルファベット</li>
	 * <li>末尾はアルファベットか数字</li>
	 * <li>3文字以上</li>
	 * </ul>
	 */
	private static final String ID_PATTERN = "id:[a-zA-Z][a-zA-Z0-9_-]{1,}[a-zA-Z0-9]";

	/**
	 * 指定された文字列がはてなIDパターンにマッチするかどうか判定します。
	 * 
	 * @param idStr はてなIDパターン文字列
	 * @return はてなIDパターンの場合true, でなければfalse
	 */
	public static boolean isHatenaIdFormat(String idStr) {
		if (idStr == null) return false;
		return idStr.matches("^" + ID_PATTERN + "$");
	}
}
