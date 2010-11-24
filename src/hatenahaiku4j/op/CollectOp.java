package hatenahaiku4j.op;

import hatenahaiku4j.Entity;

import java.util.Collection;

/**
 * <p>要素をコレクション型オブジェクトに集約する</p>
 * <p>集約先コレクションは副作用を受ける</p>
 * 
 * @since v1.1.0
 * @author v593kdrg
 * @param <E> 集約先コレクションに追加する要素の型
 * @param <U> 集約先コレクション
 */
public final class CollectOp<E extends Entity<E>, U extends Collection<E>> implements ReduceOp<E, U> {

	/**
	 * <p>集約先コレクション</p>
	 */
	private final U dest;

	/**
	 * <p>集約先コレクションを指定して、この操作を構築する</p>
	 * 
	 * @param dest 集約先コレクション 非nullでなければならない
	 */
	public CollectOp(final U dest) {
		assert dest != null;
		this.dest = dest;
	}

	/**
	 * <p>コンストラクタで与えた集約先コレクションに、要素を追加する</p>
	 *
	 * @param arg コレクションに追加する要素
	 */
	@Override
	public final void add(final E arg) {
		dest.add(arg);
	}

	/**
	 * <p>コンストラクタで与えた集約先コレクションを取り出す</p>
	 * 
	 * @return 集約先コレクションこれは、コンストラクタで与えたコレクションと参照同値である
	 */
	@Override
	public final U value() {
		return dest;
	}

}