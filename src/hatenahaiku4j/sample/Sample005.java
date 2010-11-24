package hatenahaiku4j.sample;

import hatenahaiku4j.Config;
import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.op.CollectOp;
import hatenahaiku4j.op.MultipleReduceOp;
import hatenahaiku4j.op.ReduceOp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * HatenaHaiku4J 動作サンプルクラスです #005
 * 
 * @since v1.1.0
 * @author fumokmm
 */
public class Sample005 {
	
	/**
	 * v1.1.0時点でのデモです。<br/>
	 * v1.1.0から導入した{@link ReduceOp}の動作サンプルです。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ログインユーザ
		LoginUser loginUser = new LoginUser("userid", "password");
		// 認証ありAPI
		HatenaHaikuAPI apiAuth = new HatenaHaikuAPI(loginUser);
		apiAuth.setNeedHttpLog(true);	// HTTPログを出力する
		// 認証なしAPI
		HatenaHaikuAPILight apiLight = new HatenaHaikuAPILight();
		apiLight.setNeedHttpLog(true);	// HTTPログを出力する

		printSeparate("#005 Sample START (" + Config.getVersion() + ")");
		try {
			printSeparate("パブリックタイムラインを色んなソートの形に加工して取得する");

			// 加工無し（通常の HatenaHaikuAPI#getPublicTimeline()と同様)
			final CollectOp<Status, Collection<Status>> normal
				= new CollectOp<Status, Collection<Status>>(new ArrayList<Status>());
			
			// ユーザーIDでソートした結果を取得,セットだけどユーザーIDの重複を含む
			final CollectOp<Status, Collection<Status>> sortByUserId
				= new CollectOp<Status, Collection<Status>>(
					new TreeSet<Status>(new Comparator<Status>() {
						public final int compare(final Status lhs, final Status rhs) {
							int result = lhs.getUserId().compareTo(rhs.getUserId());
							return result == 0 ? 1 : result; // かぶったら右優先
						}
					})
				);

			// 作成日でソートした結果を取得,セットだけど作成日の重複を含む
			final CollectOp<Status, Collection<Status>> sortByCreateAt
				= new CollectOp<Status, Collection<Status>>(
					new TreeSet<Status>(new Comparator<Status>() {
						public final int compare(final Status lhs, final Status rhs) {
							int result = lhs.getCreatedAt().compareTo(rhs.getCreatedAt());
							return result == 0 ? 1 : result; // かぶったら右優先
						}
					})
				);

			// 件数をカウントする匿名クラスを定義する
			final ReduceOp<Status, Integer> statusCounter
				= new ReduceOp<Status, Integer>() {
					private int numberOfStatuses = 0;
	
					public final void add(final Status e) {
						numberOfStatuses++;
					}
	
					public final Integer value() {
						return numberOfStatuses;
					}
				};
			
			// キーワードの件数をカウントする匿名クラスを定義する
			final ReduceOp<Status, Set<String>> keywordSet
				= new ReduceOp<Status, Set<String>>() {
					private Set<String> keywordSet = new HashSet<String>();
					
					public final void add(final Status e) {
						keywordSet.add(e.getKeyword());
					}
					
					public final Set<String> value() {
						return keywordSet;
					}
				};

			// 複数の操作をまとめる
			final MultipleReduceOp<Status> opset = new MultipleReduceOp<Status>();
			opset.addOp(normal);
			opset.addOp(sortByUserId);
			opset.addOp(sortByCreateAt);
			opset.addOp(statusCounter);
			opset.addOp(keywordSet);

			// 3つの集約操作をまとめて実行する
			apiLight.getPublicTimeline(opset, 1, null);

			printSeparate("パブリックタイムラインの件数:" + statusCounter.value());
			printSeparate("パブリックタイムラインをノーマル状態で取得する");
			for (Status e : normal.value()) {
				printStatus(e);
			}
			printSeparate("パブリックタイムラインを加工して取得する/ユーザーIDでソート");
			for (Status e : sortByUserId.value()) {
				printStatus(e);
			}

			printSeparate("パブリックタイムラインを加工して取得する/作成日でソート");
			for (Status e : sortByCreateAt.value()) {
				printStatus(e);
			}
			
			printSeparate("パブリックタイムラインにあるキーワード");
			for (String keyword : keywordSet.value()) {
				System.out.print("【" + keyword + "】");
			}
			System.out.println();
			printSeparate("キーワード計: " + keywordSet.value().size() + "件");
			

		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#005 Sample END (" + Config.getVersion() + ")");
	}

	/**
	 * ステータス情報を標準出力に出力します。
	 * 
	 * @param status ステータス情報
	 */
	public static void printStatus(Status status) {
		Sample001.printStatus(status);
	}

	/**
	 * 区切りを標準出力に出力します。
	 * 
	 * @param label ラベル
	 */
	public static void printSeparate(String label) {
		Sample001.printSeparate(label);
	}
}
