package hatenahaiku4j;

/**
 * はてなハイクエンティティ（ユーザ、ステータス、キーワード）に関するAPIのベースクラス
 * 
 * @since v0.2.0
 * @author fumokmm
 */
class EntityAPI {
	/** はてなハイクAPI（認証あり） */
	protected HatenaHaikuAPI apiAuth;
	/** はてなハイクAPI（認証なし） */
	protected HatenaHaikuAPILight apiLight;

	/**
	 * コンストラクタ。（パッケージプライベート）
	 * 
	 * @since v0.2.0
	 */
	EntityAPI() {}

	/**
	 * 認証ありかどうか返却します。
	 * 
	 * @return 認証ありかどうか
	 * @since v0.2.0
 	 */
	protected boolean isAuth() {
		return apiAuth != null;
	}
}
