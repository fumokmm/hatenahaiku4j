package hatenahaiku4j.sample;

import hatenahaiku4j.Config;
import hatenahaiku4j.HatenaHaikuAPIHTML;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.Status;
import hatenahaiku4j.op.CollectOp;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * HatenaHaiku4J 動作サンプルクラスです #006
 * 
 * @since v1.2.0
 * @author fumokmm
 */
public class Sample006 {
	
	/**
	 * v1.2.0時点でのデモです。<br/>
	 * v1.2.0から導入した{@link HatenaHaikuAPIHTML}の動作サンプルです。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// API（HTMLスクレイピング版）
		HatenaHaikuAPIHTML apiHtml = new HatenaHaikuAPIHTML();
		apiHtml.setNeedHttpLog(true);	// HTTPログを出力する

		printSeparate("#006 Sample START (" + Config.getVersion() + ")");
		try {
			printSeparate("パブリックタイムラインを出力する");
			List<Status> publicTimeline = apiHtml.getPublicTimeline(3000);
			for (Status status : publicTimeline) {
				printStatus(status);
			}

			printSeparate("フレンズタイムラインを出力する");
			List<Status> friendsTimeline = apiHtml.getFriendsTimeline("hatenahaiku");
			for (Status status : friendsTimeline) {
				printStatus(status);
			}

			printSeparate("ユーザタイムラインを出力する");
			List<Status> userTimeline = apiHtml.getUserTimeline("hatenahaiku");
			for (Status status : userTimeline) {
				printStatus(status);
			}

			printSeparate("IDタイムラインを出力する");
			List<Status> idTimeline = apiHtml.getIdTimeline("fumokmm");
			for (Status status : idTimeline) {
				printStatus(status);
			}
			
			printSeparate("キーワードタイムラインを出力する(ひとりごと)");
			List<Status> hitorigotoTimeline = apiHtml.getKeywordTimeline("ひとりごと");
			for (Status status : hitorigotoTimeline) {
				printStatus(status);
			}

			printSeparate("ホットキーワードタイムラインを出力する(ひとりごと)");
			List<Status> hotHitorigotoTimeline = apiHtml.getHotKeywordTimeline("ひとりごと");
			for (Status status : hotHitorigotoTimeline) {
				printStatus(status);
			}

// TODO 形式が違う他のタイムラインと違うため、ひとまず保留
//			printSeparate("アルバムキーワードタイムラインを出力する(ひとりごと)");
//			List<Status> albumHitorigotoTimeline = apiHtml.getAlbumKeywordTimeline("ひとりごと");
//			for (Status status : albumHitorigotoTimeline) {
//				printStatus(status);
//			}
			
			printSeparate("キーワードリストを取得する。1ページ目");
			List<Keyword> keywordPage1 = apiHtml.getKeywordList();
			for (Keyword keyword : keywordPage1) {
				printKeyword(keyword);
			}

			printSeparate("キーワードリストを取得する。3000ページ目");
			List<Keyword> keywordPage3000 = apiHtml.getKeywordList(3000);
			for (Keyword keyword : keywordPage3000) {
				printKeyword(keyword);
			}

			printSeparate("キーワードリストを取得する。10000ページ目");
			List<Keyword> keywordPage10000 = apiHtml.getKeywordList(10000);
			for (Keyword keyword : keywordPage10000) {
				printKeyword(keyword);
			}

			printSeparate("「ひとりごと」の付くキーワードを全部集める。");
			CollectOp<Keyword, Set<Keyword>> buKeywords = new CollectOp<Keyword, Set<Keyword>>(new LinkedHashSet<Keyword>());
			int page = 1;
			// 取得実行前件数 < 取得実行後の件数 の場合、繰り返す。
			while (	buKeywords.value().size() < apiHtml.getKeywordList(buKeywords, "ひとりごと", page).size()) {
				buKeywords = new CollectOp<Keyword, Set<Keyword>>(buKeywords.value()); // 更新
				page++;
			}
			for (Keyword keyword : buKeywords.value()) {
				printKeyword(keyword);
			}
			System.out.println("「ひとりごと」の付くキーワードの件数: " + buKeywords.value().size() + "件。");
		
			printSeparate("ホットキーワードを取得する。");
			for (Keyword keyword : apiHtml.getHotKeywordList()) {
				printKeyword(keyword);
			}
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#006 Sample END (" + Config.getVersion() + ")");
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
	 * キーワード情報を標準出力に出力します。
	 * 
	 * @param keyword キーワード情報
	 */
	public static void printKeyword(Keyword keyword) {
		Sample001.printKeyword(keyword);
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
