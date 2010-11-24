package hatenahaiku4j;

import hatenahaiku4j.util.HatenaUtil;

import java.util.List;

/**
 * はてなハイクキーワード情報を表現するクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Keyword {
	/** 投稿数 */
	private int entryCount;
	/** フォロワー数 */
	private int followersCount;
	/** キーワードページのリンク  */
	private String link;
	/** 関連キーワード */
	private List<String> relatedKeywords;
	/** キーワードタイトル */
	private String title;

	/**
	 * キーワードAPI
	 * 
	 * @since v0.2.0
	 */
	public final KeywordAPI api;

	/**
	 * コンストラクタです。（パッケージプライベート）
	 * @since v0.0.1
	 */
	private Keyword() {
		this.api = new KeywordAPI();
	}
	
	/**
	 * 指定したキーワードでこのキーワードを上書きします。
	 * 
	 * @param other 上書きするキーワード
	 * @since v0.2.0
	 */
	void overwrite(Keyword other) {
		this.entryCount = other.entryCount;
		this.followersCount = other.followersCount;
		this.link = other.link;
		this.relatedKeywords = other.relatedKeywords;
		this.title = other.title;
	}
	
	/**
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiAuth はてなハイクAPI（認証あり）
	 * @since v0.2.0
	 */
	static Keyword create(HatenaHaikuAPI apiAuth) {
		Keyword keyword = new Keyword();
		keyword.api.init(keyword, apiAuth);
		return keyword;
	}

	/**
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiLight はてなハイクAPI（認証なし）
	 * @since v0.2.0
	 */
	static Keyword create(HatenaHaikuAPILight apiLight) {
		Keyword keyword = new Keyword();
		keyword.api.init(keyword, apiLight);
		return keyword;
	}

	/**
	 * 投稿数を取得します。
	 * 
	 * @return 投稿数
	 * @since v0.0.1
	 */
	public int getEntryCount() {
		return entryCount;
	}
	
	/**
	 * フォロワー数を取得します。
	 * 
	 * @return フォロワー数
	 * @since v0.0.1
	 */
	public int getFollowersCount() {
		return followersCount;
	}
	
	/**
	 * キーワードページのリンクを取得します。
	 * 
	 * @return キーワードページのリンク 
	 * @since v0.0.1
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * モバイル用キーワードページのリンクを取得します。
	 * 
	 * @return キーワードページのリンク 
	 * @since v1.0.0
	 */
	public String getMobileLink() {
		return link.replace(Const.BASE_URL, Const.MOBILE_BASE_URL);
	}

	/**
	 * 関連キーワードのリストを取得します。
	 * 
	 * @return 関連キーワードのリスト
	 * @since v0.0.1
	 */
	public List<String> getRelatedKeywords() {
		return relatedKeywords;
	}

	/**
	 * キーワードタイトルを取得します。
	 * 
	 * @return キーワードタイトル
	 * @since v0.0.1
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * idページかどうかを取得します。
	 * 
	 * @return idページかどうか
	 * @since v0.0.1
	 */
	public boolean isIdPage() {
		return HatenaUtil.isIdNotation(title);
	}

	/**
	 * 投稿数を設定します。
	 * 
	 * @param entryCount 投稿数
	 * @since v0.0.1
	 */
	void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
	}

	/**
	 * フォロワー数を設定します。
	 * 
	 * @param followersCount フォロワー数
	 * @since v0.0.1
	 */
	void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * キーワードページのリンクを設定します。
	 * 
	 * @param link キーワードページのリンク
	 * @since v0.0.1
	 */
	void setLink(String link) {
		this.link = link;
	}

	/**
	 * 関連キーワードを設定します。
	 * 
	 * @param relatedKeywords 関連キーワード
	 * @since v0.0.1
	 */
	void setRelatedKeywords(List<String> relatedKeywords) {
		this.relatedKeywords = relatedKeywords;
	}

	/**
	 * キーワードタイトルを設定します。
	 * 
	 * @param title キーワードタイトル
	 * @since v0.0.1
	 */
	void setTitle(String title) {
		this.title = title;
	}
}

/* ------ sample xml ---------------------------
<keyword>
	<entry_count>10</entry_count>
	<followers_count>100</followers_count>
	<link>http://h.hatena.ne.jp/はてなハイク</link>
	<related_keywords>RELATED_KEYWORD</related_keywords>
	<!-- related_keywords が続きます。 -->
	<title>はてなハイク</title>
</keyword>
--------------------------------------------- */
