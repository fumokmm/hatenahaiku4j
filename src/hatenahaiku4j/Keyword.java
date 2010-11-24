package hatenahaiku4j;

import hatenahaiku4j.util.Util;

import java.util.List;

/**
 * はてなハイクキーワード情報を表現するクラス
 * 
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
	
	/** @return 投稿数 */
	public int getEntryCount() {
		return entryCount;
	}
	
	/** @return フォロワー数 */
	public int getFollowersCount() {
		return followersCount;
	}
	
	/** @return キーワードページのリンク  */
	public String getLink() {
		return link;
	}
	
	/** @return 関連キーワードのリスト */
	public List<String> getRelatedKeywords() {
		return relatedKeywords;
	}

	/** @return キーワードタイトル */
	public String getTitle() {
		return title;
	}
	
	/** @return idページかどうか */
	public boolean isIdPage() {
		return Util.isHatenaIdFormat(title);
	}

	/**
	 * @param entryCount the entryCount to set
	 */
	void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
	}

	/**
	 * @param followersCount the followersCount to set
	 */
	void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * @param link the link to set
	 */
	void setLink(String link) {
		this.link = link;
	}

	/**
	 * @param relatedKeywords the relatedKeywords to set
	 */
	void setRelatedKeywords(List<String> relatedKeywords) {
		this.relatedKeywords = relatedKeywords;
	}

	/**
	 * @param title the title to set
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
