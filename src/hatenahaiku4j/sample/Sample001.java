package hatenahaiku4j.sample;

import hatenahaiku4j.Config;
import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.HatenaUtil;
import hatenahaiku4j.util.StringUtil;

import java.util.List;

/**
 * HatenaHaiku4J 動作サンプルクラスです #001
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Sample001 {
	
	/**
	 * v0.0.1時点でのデモです。
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

		printSeparate("#001 Sample START (" + Config.getVersion() + ")");
		try {
			printSeparate("パブリックタイムラインを出力する");
			List<Status> publicTimeline = apiLight.getPublicTimeline();
			for (Status status : publicTimeline) {
				printStatus(status);
			}

			printSeparate("キーワードタイムラインを出力する(ひとりごと)");
			List<Status> hitorigotoTimeline = apiLight.getKeywordTimeline("ひとりごと");
			// apiLightを利用していますが、apiAuthを利用することも可能です。
			for (Status status : hitorigotoTimeline) {
				printStatus(status);
			}
			
			printSeparate("認証したユーザの人気のユーザタイムラインを出力する");
			List<Status> hotUserTimeline = apiAuth.getHotUserTimeline();
			// apiLightを利用していますが、apiAuthを利用することも可能です。
			for (Status status : hotUserTimeline) {
				printStatus(status);
			}

			printSeparate("ログインユーザのidページに投稿する。");
			Status result = apiAuth.entry("こんにちは\nはてなハイク４Ｊ！\nデモテスト中です。");
			printStatus(result);
			
			printSeparate("ついでに今しがた登録した自分のエントリにスターを3つほどつけてみる");
			String targetStatusId = result.getStatusId();
			for (int i = 0; i < 3; i++) {
				Status addStarResult = apiAuth.addStar(targetStatusId);
				printStatus(addStarResult);
			}

			printSeparate("さらに、Replyしてみる");
			Status replyResult = apiAuth.reply(targetStatusId, "リプライもできます。");
			printStatus(replyResult);
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#001 Sample END (" + Config.getVersion() + ")");
	}
	
	/**
	 * ステータス情報を標準出力に出力します。
	 * 
	 * @param status ステータス情報
	 */
	public static void printStatus(Status status) {
		StringBuilder sb = new StringBuilder();
		sb.append("【").append(status.getKeyword()).append("】");
		sb.append(HatenaUtil.formatDate(status.getCreatedAt()));
		sb.append("〔☆×").append(status.getFavorited()).append("〕");
		if (!StringUtil.isEmpty(status.getInReplyToStatusId())) {
			sb.append(" @").append(status.getInReplyToUserId())
			.append("(").append(status.getInReplyToStatusId()).append(")");
		}
		sb.append(" ").append(status.getText());
		sb.append(" by ").append(status.getUserId());
		sb.append(" from ").append(status.getSource());
		if (!status.getReplies().isEmpty()) {
			StringBuilder replies = new StringBuilder();
			for (Status reply : status.getReplies()) {
				if (replies.length() > 0) {
					replies.append(", ");
				}
				replies.append(reply.getUserId());
				replies.append("(").append(reply.getStatusId()).append(")");
			}
			sb.append(" replied from ").append(replies);
		}
		System.out.println(sb.toString());
	}

	/**
	 * キーワード情報を標準出力に出力します。
	 * 
	 * @param keyword キーワード情報
	 */
	public static void printKeyword(Keyword keyword) {
		StringBuilder sb = new StringBuilder();
		sb.append("【").append(keyword.getTitle()).append("】");
		sb.append("(" + keyword.getEntryCount() + ")");
		sb.append(" cloud").append(keyword.getCloud());
		sb.append(" ");
		sb.append(keyword.getLink());
		System.out.println(sb.toString());
	}

	/**
	 * 区切りを標準出力に出力します。
	 * 
	 * @param label ラベル
	 */
	public static void printSeparate(String label) {
		System.out.println("----------" + label + "----------");
	}
}
