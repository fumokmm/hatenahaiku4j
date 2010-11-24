package hatenahaiku4j.sample;

import java.util.List;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.HatenaUtil;
import hatenahaiku4j.util.StringUtil;

/**
 * HatenaHaiku4J 動作サンプルクラスです
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Main {
	
	/**
	 * メイン処理です。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ログインユーザ
		LoginUser loginUser = LoginUser.create("userid", "password");
		// 認証ありAPI
		HatenaHaikuAPI api = new HatenaHaikuAPI(loginUser);
		// 認証なしAPI
		HatenaHaikuAPILight apiLight = new HatenaHaikuAPILight();

		try {
//			demo_v0_0_1(api, apiLight);	// v0.0.1デモ
			demo_v0_2_0(api, apiLight);	// v0.2.0デモ
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * v0.0.1時点でのデモです。
	 * 
	 * @param api 認証ありAPI
	 * @param apiLight 認証なしAPI
	 */
	private static void demo_v0_0_1(HatenaHaikuAPI api, HatenaHaikuAPILight apiLight)
		throws HatenaHaikuException {

		printSeparate("パブリックタイムラインを出力する");
		List<Status> publicTimeline = apiLight.getPublicTimeline();
		for (Status status : publicTimeline) {
			printStatus(status);
		}

		printSeparate("キーワードタイムラインを出力する(ひとりごと)");
		List<Status> hitorigotoTimeline = apiLight.getKeywordTimeline("ひとりごと");
		// apiLightを利用していますが、apiを利用することも可能です。
		for (Status status : hitorigotoTimeline) {
			printStatus(status);
		}
		
		printSeparate("ログインユーザのidページに投稿する。");
		Status result = api.entry("こんにちは\nはてなハイク４Ｊ！\nデモテスト中です。");
		printStatus(result);
		
		printSeparate("ついでに今しがた登録した自分のエントリにスターを3つほどつけてみる");
		String targetStatusId = result.getStatusId();
		for (int i = 0; i < 3; i++) {
			Status addStarResult = api.addStar(targetStatusId);
			printStatus(addStarResult);
		}

		printSeparate("さらに、Replyしてみる");
		Status replyResult = api.reply(targetStatusId, "リプライもできます。");
		printStatus(replyResult);
	}
	
	/**
	 * v0.2.0時点でのデモです。<br/>
	 * v0.2.0からより柔軟に取得結果から色々できるようになりました。<br/>
	 * デモ内容はv0.0.1時点と同じことした後に、ユーザのIDページのタイムラインを取得しています。
	 * 
	 * @param api 認証ありAPI
	 * @param apiLight 認証なしAPI
	 */
	private static void demo_v0_2_0(HatenaHaikuAPI api, HatenaHaikuAPILight apiLight)
		throws HatenaHaikuException {

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
		
		printSeparate("ログインユーザのidページに投稿する。");
		Keyword idPage = api.getMyKeyword();
		Status result = idPage.api.entry("こんにちは\nはてなハイク４Ｊ！\nデモテスト中です。");
		printStatus(result);
		
		printSeparate("ついでに今しがた登録した自分のエントリにスターを3つほどつけてみる");
		for (int i = 0; i < 3; i++) {
			printStatus(result.api.addStar());
		}

		printSeparate("さらに、Replyしてみる");
		printStatus(result.api.reply("リプライもできます。"));
		
		printSeparate("ログインユーザのidページタイムラインを出力する");
		for (Status idStatus : api.getMe().api.getIdTimeline()) {
			printStatus(idStatus);
		}
	}

	/**
	 * ステータス情報を標準出力に出力します。
	 * 
	 * @param status ステータス情報
	 */
	private static void printStatus(Status status) {
		StringBuilder sb = new StringBuilder();
		sb.append("【").append(status.getKeyword()).append("】");
		sb.append(HatenaUtil.formatDate(status.getCreatedAt()));
		sb.append("〔☆×").append(status.getFavorited()).append("〕");
		sb.append(status.getText());
		if (!StringUtil.isEmpty(status.getInReplyToStatusId())) {
			sb.append(" reply to ").append(status.getInReplyToUserId());
		}
		sb.append(" by ").append(status.getUserId());
		sb.append(" from ").append(status.getSource());
		if (!status.getReplies().isEmpty()) {
			StringBuilder replies = new StringBuilder();
			for (Status reply : status.getReplies()) {
				if (replies.length() > 0) {
					replies.append(", ");
				}
				replies.append(reply.getUserId());
			}
			sb.append(" replied from ").append(replies);
		}
		System.out.println(sb.toString());
	}

	/**
	 * 区切りを標準出力に出力します。
	 * 
	 * @param label ラベル
	 */
	private static void printSeparate(String label) {
		System.out.println("----------" + label + "----------");
	}
}
