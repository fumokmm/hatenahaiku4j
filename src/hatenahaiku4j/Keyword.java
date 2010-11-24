package hatenahaiku4j;

import static hatenahaiku4j.Const.*;
import hatenahaiku4j.util.HatenaUtil;

import java.util.List;

/**
 * はてなハイクキーワード情報を表現するクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Keyword implements Entity<Keyword> {

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
	/** クラウド */
	private int cloud;

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
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiHtml はてなハイクAPI（HTMLスクレイピング版）
	 * @since v1.2.0
	 */
	static Keyword create(HatenaHaikuAPIHTML apiHtml) {
		Keyword keyword = new Keyword();
		keyword.api.init(keyword, apiHtml);
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
		return link.replace(BASE_URL, MOBILE_BASE_URL);
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
	 * クラウドを取得します。<br/>
	 * クラウドはタグクラウド用の値(1～7)らしい。<br/>
	 * 尚、当値は、{@link HatenaHaikuAPIHTML#getHotKeywordList()}にて<br/>
	 * Keywordを取得時にしか設定されない値であることに注意されたい。<br/>
	 * （未設定時は常に0が返却されます）
	 * 
	 * <table>
	 * <tr><th>class名</th><th>重要度</th></tr>
	 * <tr><td>cloud7</td><td rowspan="7">高<br/><br/>↑<br/>｜<br/>↓<br/><br/>低</td></tr>
	 * <tr><td>cloud6</td></tr>
	 * <tr><td>cloud5</td></tr>
	 * <tr><td>cloud4</td></tr>
	 * <tr><td>cloud3</td></tr>
	 * <tr><td>cloud2</td></tr>
	 * <tr><td>cloud1</td></tr>
	 * </table>
	 * 
	 * 
	 * @return cloudの値(数値)。未設定の場合0。
	 * @since v1.2.0
	 */
	public int getCloud() {
		return cloud;
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

	/**
	 * クラウドを設定します。
	 * 
	 * @param cloud クラウド
	 * @since v1.2.0
	 */
	void setCloud(int cloud) {
		this.cloud = cloud;
	}

	/**
	 * このキーワード情報と指定されたキーワード情報の順序を比較します。<br/>
	 * このキーワード情報が指定されたキーワード情報より小さい場合は負の整数、<br/>
	 * 等しい場合はゼロ、大きい場合は正の整数を返します。<br/>
	 * <br/>
	 * 比較はキーワードタイトルで行います。
	 * 
	 * @param keyword 指定されたキーワード情報
	 * @since v1.1.0
	 */
	@Override
	public int compareTo(Keyword keyword) {
		return this.title.compareTo(keyword.title);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.title.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Keyword other = (Keyword) obj;
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		return true;
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
