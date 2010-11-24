package hatenahaiku4j.sample;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.HaikuURL;
import hatenahaiku4j.util.HatenaUtil;

/**
 * HatenaHaiku4J 動作サンプルクラスです #004
 * 
 * @since v1.0.1
 * @author fumokmm
 */
public class Sample004 {
	
	/**
	 * v1.0.1から使えるようになったHaikuURLと<br/>
	 * HatenaUtilに#escapeHatenaNotation(String)のサンプルです。
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

		printSeparate("#004 Sample START");
		try {
			// エントリ内容を作成
			StringBuilder entryContents = new StringBuilder();

			HaikuURL idUrl = HaikuURL.byUserId(loginUser.getUserId());
			entryContents.append("idページリンク（通常版:idコールする）\n");
			entryContents.append(idUrl.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("idページリンク（エスケープ版:idコールなし）\n");
			entryContents.append(idUrl.getEscapedLink() + "\n");
			entryContents.append(" \n");
			HaikuURL idUrl2 = HaikuURL.byUserId(loginUser.getUserId(), loginUser.getUserIdNotation() + "さんとこ");
			entryContents.append("ラベルで置き換えたidページリンク（通常版:idコールする）\n");
			entryContents.append(idUrl2.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("ラベルで置き換えたidページリンク（エスケープ版:idコールなし）\n");
			entryContents.append(idUrl2.getEscapedLink() + "\n");
			entryContents.append(" \n");

			HaikuURL keywordUrl = HaikuURL.byKeyword(loginUser.getUserIdNotation() + "だけど、HatenaHaiku4Jのテストするよ！");
			entryContents.append("キーワードページリンク（通常版:idコールする）\n");
			entryContents.append(keywordUrl.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("キーワードページリンク（エスケープ版:idコールなし）\n");
			entryContents.append(keywordUrl.getEscapedLink() + "\n");
			entryContents.append(" \n");
			HaikuURL keywordUrl2 = HaikuURL.byKeyword(loginUser.getUserIdNotation() + "だけど、HatenaHaiku4Jのテストするよ！", "[[" + loginUser.getUserIdNotation() + "だけど、HatenaHaiku4Jのテストするよ！" + "]]");
			entryContents.append("ラベルで置き換えたキーワードページリンク（通常版:idコールする）\n");
			entryContents.append(keywordUrl2.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("キーワードページリンク（エスケープ版:idコールなし）\n");
			entryContents.append(keywordUrl2.getEscapedLink() + "\n");
			entryContents.append(" \n");

			HaikuURL urlUrl = HaikuURL.byURL("http://h.hatena.ne.jp");
			entryContents.append("URLリンク\n");
			entryContents.append(urlUrl.getLink() + "\n");
			entryContents.append(" \n");
			HaikuURL urlUrl2 = HaikuURL.byURL("http://h.hatena.ne.jp", "はてなハイク");
			entryContents.append("ラベルで置き換えたURLリンク\n");
			entryContents.append(urlUrl2.getLink() + "\n");
			entryContents.append(" \n");
			
			HaikuURL asinUrl = HaikuURL.byASIN("4798110523");
			entryContents.append("ASINリンク\n");
			entryContents.append(asinUrl.getLink() + "\n");
			entryContents.append(" \n");

			HaikuURL youtubeUrl = HaikuURL.byYouTube("HaYs1Ni4ts0");
			entryContents.append("YouTubeリンク\n");
			entryContents.append(youtubeUrl.getLink() + "\n");
			entryContents.append(" \n");

			HaikuURL nico2Url = HaikuURL.byNico2("sm2476246");
			entryContents.append("ニコニコ動画リンク\n");
			entryContents.append(nico2Url.getLink() + "\n");
			entryContents.append(" \n");
			
			// エスケープデモ
			entryContents.append("------------------------\n");
			entryContents.append(HatenaUtil.escapeHatenaNotation("HatenaUtil#escapeHatenaNotation(String)を使うと、\n"));
			entryContents.append(HatenaUtil.escapeHatenaNotation("id:xxxxxなどのようなidコールや[[キーワードリンク]]のサンプルなどを\n"));
			entryContents.append(HatenaUtil.escapeHatenaNotation("エントリをしたい場合に、適切にエスケープしてくれます。\n"));
			entryContents.append(HatenaUtil.escapeHatenaNotation("HaikuURL内部でもこのメソッドを利用しています。\n"));

			// ログインユーザのidページにエントリ
			Keyword myIdPage = apiAuth.getMyKeyword();
			printStatus(myIdPage.api.entry(entryContents.toString()));
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#004 Sample END");
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
