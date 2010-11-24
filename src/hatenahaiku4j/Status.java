package hatenahaiku4j;

import hatenahaiku4j.util.Util;

import java.util.Date;
import java.util.List;

/**
 * はてなハイクステータス情報を表現するクラス
 * 
 * @author fumokmm
 */
public class Status {
	/** ステータスID */
	private String id;
	/** 作成日時 */
	private Date createdAt;
	/** お気に入られ */
	private int favorited;
	/** 返信先ステータスID */
	private String inReplyToStatusId;
	/** 返信先ユーザID */
	private String inReplyToUserId;
	/** キーワード */
	private String keyword;
	/** リンク */
	private String link;
	/** このステータスへの返信 */
	private List<Status> replies;
	/** ソース（クライアント名） */
	private String source;
	/** 投稿内容 */
	private String text;
	/** ユーザ情報 */
	private User user;
	
	/** このステータスへの返信として取得した内容かどうか */
	private boolean shadow;
	
	/** @return ステータスID */
	public String getStatusId() {
		return id;
	}

	/** @return 作成日時 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/** @return お気に入られ */
	public int getFavorited() {
		return favorited;
	}

	/** @return 返信先ステータスID */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/** @return 返信先ユーザID */
	public String getInReplyToUserId() {
		return inReplyToUserId;
	}

	/** @return キーワード */
	public String getKeyword() {
		return keyword;
	}

	/** @return idページかどうか */
	public boolean isIdPage() {
		return Util.isHatenaIdFormat(keyword);
	}

	/** @return リンク */
	public String getLink() {
		return link;
	}

	/** このステータスへの返信 */
	public List<Status> getReplies() {
		return replies;
	}
	
	/** @return ソース（クライアント名） */
	public String getSource() {
		return source;
	}

	/** @return 投稿内容 */
	public String getText() {
		return text;
	}

	/** @return ユーザ情報 */
	public User getUser() {
		return user;
	}
	
	/** @return ユーザID */
	public String getUserId() {
		return user.getUserId();
	}

	/**
	 * {@link #getShadow()}のエイリアスです。
	 * @return このステータスへの返信として取得した内容かどうか
	 */
	public boolean isShadow() {
		return shadow;
	}
	/** @return このステータスへの返信として取得した内容かどうか */
	public boolean getShadow() {
		return shadow;
	}
	
	/**
	 * @param id the id to set
	 */
	void setStatusId(String statusId) {
		this.id = statusId;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param favorited the favorited to set
	 */
	void setFavorited(int favorited) {
		this.favorited = favorited;
	}

	/**
	 * @param inReplyToStatusId the inReplyToStatusId to set
	 */
	void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	/**
	 * @param inReplyToUserId the inReplyToUserId to set
	 */
	void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	/**
	 * @param keyword the keyword to set
	 */
	void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @param link the link to set
	 */
	void setLink(String link) {
		this.link = link;
	}

	/**
	 * @param replies the replies to set
	 */
	void setReplies(List<Status> replies) {
		this.replies = replies;
	}
	
	/**
	 * @param source the source to set
	 */
	void setSource(String source) {
		this.source = source;
	}

	/**
	 * @param text the text to set
	 */
	void setText(String text) {
		this.text = text;
	}

	/**
	 * @param user the user to set
	 */
	void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * @param shadow the shadow to set
	 */
	void setShadow(boolean shadow) {
		this.shadow = shadow;
	}
	
	/**
	 * textの「xxxx=本文」の "xxxx="部分を取り除く。<br/>
	 * 前提：keyword, textが設定されていること。（keywordはidページの場合id:xxx形式で格納済みであること）
	 */
	void removeKeywordEqualOnText() {
		if (!keyword.equals("") && !isIdPage()) {
			// 空白でもidページでもない場合、textには「キーワード=」が頭についているので、取り除く
			text = text.substring(keyword.length() + 1);
		}
	}
}

/* ------ sample xml ---------------------------
<?xml version='1.0' standalone='yes'?>
<statuses type="array">
  <status>
    <id>12345678901234</id>
    <created_at>2008-08-19T00:00:00Z</created_at>
    <favorited>0</favorited>
    <in_reply_to_status_id>98765432109876</in_reply_to_status_id>
    <in_reply_to_user_id>jkondo</in_reply_to_user_id>

    <keyword>はてなデフォルトさん</keyword>
    <link>http://h.hatena.ne.jp/jkondo/12345678901234</link>
    <replies>                                         |
    	in_reply_to_status_id, in_reply_to_user_id    | ただし、repliesは
		keyword, link, replies を除く<status>           | トップレベルstatusにのみ付加される。
	</replies>                                        |
		・
		・以下返信先分、repliesの繰り返し
		・
    <source>web</source>
    <text>はてなデフォルトさん=こんにちは、かわいいデフォルトさんですね。</text>
    <user>
      <name>jkondo</name>
      <followers_count>1</followers_count>
      <id>jkondo</id>
      <profile_image_url>http://www.hatena.ne.jp/users/jk/jkondo/profile.gif</profile_image_url>
      <screen_name>jkondo</screen_name>
      <url>http://h.hatena.ne.jp/jkondo/</url>
    </user>
  </status>
  <!-- status 要素が続きます。 -->
</statuses>
--------------------------------------------- */
