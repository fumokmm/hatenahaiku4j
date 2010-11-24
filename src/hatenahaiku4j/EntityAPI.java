package hatenahaiku4j;

import hatenahaiku4j.op.CollectOp;

import java.util.ArrayList;
import java.util.List;

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
	/** はてなハイクAPI（HTMLスクレイピング版） */
	protected HatenaHaikuAPIHTML apiHtml;

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

	/**
	 * HTMLスクレイピング版かどうか返却します。
	 * 
	 * @return HTMLスクレイピング版かどうか
	 * @since v1.2.0
 	 */
	protected boolean isHTML() {
		return apiHtml != null;
	}

	/**
	 * 標準の集合操作を返却します。<br/>
	 * {@link java.util.ArrayList}にaddしていく。
	 * 
	 * @param <E> 集めるEntity
	 * @return 指定したEntityのArrayListによる集合操作
	 * @since v1.1.0
	 */
	static <E extends Entity<E>> CollectOp<E, List<E>> createCollectOp() {
		return new CollectOp<E, List<E>>(new ArrayList<E>());
	}

}
