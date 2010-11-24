package hatenahaiku4j.sample;

import hatenahaiku4j.Config;
import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.User;

import java.util.List;

/**
 * HatenaHaiku4J 動作サンプルクラスです #002
 * 
 * @since v0.2.1
 * @author fumokmm
 */
public class Sample002 {
	
	/**
	 * v0.2.1時点でのデモです。<br/>
	 * v0.2.0からより柔軟に取得結果から色々できるようになりました。<br/>
	 * デモ内容はv0.0.1時点と同じことした後に、ユーザのIDページのタイムラインを取得しています。
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

		printSeparate("#002 Sample START (" + Config.getVersion() + ")");
		try {
			printSeparate("パブリックタイムラインを出力する");
			List<Status> publicTimeline = apiLight.getPublicTimeline();
			for (Status status : publicTimeline) {
				printStatus(status);
			}

			printSeparate("キーワードタイムラインを出力する(ひとりごと)");
			Keyword hitorigoto = apiLight.getKeyword("ひとりごと");
			// Keyword, Status, User に #api というフィールドが付属したため、
			// 各インスタンスからapiが呼び出せるようになりました。
			for (Status status : hitorigoto.api.getTimeline()) {
				printStatus(status);
			}
			
			printSeparate("認証したユーザの人気のユーザタイムラインを出力する");
			User me = apiAuth.getMe();	// 認証したユーザ自身のUserを取得
			List<Status> hotUserTimeline = me.api.getHotTimeline();
			for (Status status : hotUserTimeline) {
				printStatus(status);
			}

			printSeparate("ログインユーザのidページに投稿する。");
			Keyword idPage = apiAuth.getMyKeyword(); // 認証したユーザのキーワード(=idページ)を取得
			Status result = idPage.api.entry("こんにちは\nはてなハイク４Ｊ！\nデモテスト中です。");
			printStatus(result);
			
			printSeparate("ついでに今しがた登録した自分のエントリにスターを3つほどつけてみる");
			for (int i = 0; i < 3; i++) {
				printStatus(result.api.addStar());
			}

			printSeparate("さらに、Replyしてみる");
			printStatus(result.api.reply("リプライもできます。"));
			
			printSeparate("ログインユーザのidページタイムラインを出力する");
			for (Status idStatus : apiAuth.getMe().api.getIdTimeline()) {
				printStatus(idStatus);
			}
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#002 Sample END (" + Config.getVersion() + ")");
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
