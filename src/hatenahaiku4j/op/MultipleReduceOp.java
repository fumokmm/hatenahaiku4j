package hatenahaiku4j.op;


import hatenahaiku4j.Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>複数の{@link ReduceOp 集約操作}をまとめ上げる集約操作</p>
 * 
 * @since v1.1.0
 * @author v593kdrg
 * @param <E> 追加する要素
 */
public final class MultipleReduceOp<E extends Entity<E>> implements ReduceOp<E, List<?>> {

	/**
	 * <p>集約操作の集合</p>
	 */
	private final List<ReduceOp<E, ?>> opset = new LinkedList<ReduceOp<E, ?>>();

	/**
	 * <p>登録した全ての集約操作の{@link ReduceOp#add(Entity)}を呼び出す
	 *
	 * @param arg 要素
	 */
	@Override
	public final void add(final E arg) {
		for (ReduceOp<E, ?> e : opset) {
			e.add(arg);
		}
	}

	/**
	 * <p>操作を登録する</p>
	 * 
	 * @param op 登録する操作
	 */
	public final void addOp(final ReduceOp<E, ?> op) {
		assert op != null && !opset.contains(op);
		opset.add(op);
	}

	/**
	 * <p>操作を削除する</p>
	 * 
	 * @param op 削除する操作
	 */
	public final void removeOp(final ReduceOp<E, ?> op) {
		assert op != null;
		opset.remove(op);
	}

	/**
	 * <p>登録した集約操作の結果を取り出す</p>
	 * 
	 * @return 集約操作の結果の登録順の結果のリスト
	 */
	@Override
	public final List<?> value() {
		final List<Object> result = new LinkedList<Object>();
		for (ReduceOp<E, ?> e : opset) {
			result.add(e.value());
		}
		return result;
	}

}