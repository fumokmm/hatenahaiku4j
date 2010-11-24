package hatenahaiku4j.op;

import hatenahaiku4j.Entity;

/**
 * <p>集約操作</p>
 * <p>要素型のオブジェクトを集約した、結果型のオブジェクトを構築する</p>
 * 
 * @since v1.1.0
 * @author v593kdrg
 * @param <E> 要素型
 * @param <U> 結果型
 */
public interface ReduceOp<E extends Entity<E>, U> {

	/**
	 * <p>指定の要素を集約結果に追加する</p>
	 * 
	 * @param arg 要素
	 */
	public abstract void add(E arg);

	/**
	 * <p>要素を集約した結果オブジェクトを示す</p>
	 * 
	 * @return 集約結果
	 */
	public abstract U value();

}