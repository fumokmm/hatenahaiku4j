package hatenahaiku4j;

import hatenahaiku4j.util.HatenaUtil;

import java.util.Date;
import java.util.List;

/**
 * はてなハイクステータス情報を表現するクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Status {
	/** ステータスID */
	private String id;
	/** 作成日時 */
	private Date createdAt;
	/** お気に入られ（☆してくれた人数） */
	private int favorited;
	/** 返信元ステータスID */
	private String inReplyToStatusId;
	/** 返信元ユーザID */
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
	/** ステータスAPI */
	public final StatusAPI api;

	/**
	 * コンストラクタです。（パッケージプライベート）
	 * 
	 * @since v0.0.1
	 */
	private Status() {
		this.api = new StatusAPI();
	}
	
	/**
	 * 指定したユーザでこのユーザを上書きします。
	 * 
	 * @param other 上書きするユーザ
	 * @since v0.2.0
	 */
	void overwrite(Status other) {
		this.id = other.id;
		this.createdAt = other.createdAt;
		this.favorited = other.favorited;
		this.inReplyToStatusId = other.inReplyToStatusId;
		this.inReplyToUserId = other.inReplyToUserId;
		this.keyword = other.keyword;
		this.link = other.link;
		this.replies = other.replies;
		this.source = other.source;
		this.text = other.text;
		this.user = other.user;
		this.shadow = other.shadow;
	}
	
	/**
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiAuth はてなハイクAPI（認証あり）
	 * @since v0.2.0
	 */
	static Status create(HatenaHaikuAPI apiAuth) {
		Status status = new Status();
		status.api.init(status, apiAuth);
		return status;
	}

	/**
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiLight はてなハイクAPI（認証なし）
	 * @since v0.2.0
	 */
	static Status create(HatenaHaikuAPILight apiLight) {
		Status status = new Status();
		status.api.init(status, apiLight);
		return status;
	}
	
	/**
	 * ステータスIDを取得します。
	 * 
	 * @return ステータスID 
	 * @since v0.0.1
	 */
	public String getStatusId() {
		return id;
	}

	/**
	 * 作成日時を取得します。
	 * 
	 * @return 作成日時
	 * @since v0.0.1
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時をはてな日付形式文字列で取得します。
	 * 
	 * @return 作成日時（はてな日付形式文字列）
	 * @since v0.2.0
	 */
	public String getCreatedAtString() {
		return HatenaUtil.formatDate(createdAt);
	}

	/**
	 * お気に入られ（☆してくれた人数）を取得します。<br/>
	 * 付いたスターの数でないことに注意してください。
	 * 
	 * @return お気に入られ（☆してくれた人数）
	 * @since v0.0.1
	 */
	public int getFavorited() {
		return favorited;
	}

	/**
	 * 返信元ステータスIDを取得します。
	 * 
	 * @return 返信元ステータスID
	 * @since v0.0.1
	 */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/**
	 * 返信元ユーザIDを取得します。
	 * 
	 * @return 返信元ユーザID
	 * @since v0.0.1
	 */
	public String getInReplyToUserId() {
		return inReplyToUserId;
	}

	/**
	 * キーワードを取得します。
	 * 
	 * @return キーワード 
	 * @since v0.0.1
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * idページかどうか取得します。
	 * 
	 * @return idページかどうか
	 * @since v0.0.1
	 */
	public boolean isIdPage() {
		return HatenaUtil.isIdNotation(keyword);
	}

	/**
	 * リンクを取得します。
	 * 
	 * @return リンク 
	 * @since v0.0.1
	 */
	public String getLink() {
		return link;
	}

	/**
	 * モバイル用リンクを取得します。
	 * 
	 * @return モバイル用リンク 
	 * @since v1.0.0
	 */
	public String getMobileLink() {
		return link.replace(Const.BASE_URL, Const.MOBILE_BASE_URL);
	}

	/**
	 * このステータスへの返信を返却します。
	 * 
	 * @return このステータスへの返信
	 * @since v0.1.0
	 */
	public List<Status> getReplies() {
		// 自動更新するなら
		if (api.apiLight.isAutoRefreshReplies()) {
			if (shadow) {
				try {
					this.api.refreshReplies();
				} catch (HatenaHaikuException e) {
					System.out.println("返信先取得失敗。");
				}
			}
		}
		return replies;
	}
	
	/**
	 * ソース（クライアント名）を取得します。
	 * 
	 * @return ソース（クライアント名）
	 * @since v0.0.1
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 投稿内容を取得します。
	 * 
	 * @return 投稿内容
	 * @since v0.0.1
	 */
	public String getText() {
		return text;
	}

	/**
	 * ユーザ情報を取得します。
	 * 
	 * @return ユーザ情報
	 * @since v0.0.1
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * ユーザIDを取得します。
	 * 
	 * {@link #getUser()}で取得できるユーザの{@link User#getUserId()}のエイリアスです。
	 * @return ユーザID
	 * @since v0.0.1
	 */
	public String getUserId() {
		return user.getUserId();
	}

	/**
	 * id記法のユーザIDを取得します。<br/>
	 * {@link #getUser()}で取得できるユーザの{@link User#getUserIdNotation()}のエイリアスです。
	 * 
	 * @return ユーザid記法
	 * @since v0.2.0
	 */
	public String getUserIdNotation() {
		return user.getUserIdNotation();
	}

	/**
	 * このステータスへの返信として取得した内容かどうかを取得します。
	 * 
	 * @return このステータスへの返信として取得した内容かどうか
	 * @since v0.1.0
	 */
	public boolean isShadow() {
		return shadow;
	}
	
	/**
	 * ステータスIDを設定します。
	 * 
	 * @param statusId ステータスID
	 * @since v0.0.1
	 */
	void setStatusId(String statusId) {
		this.id = statusId;
	}

	/**
	 * 作成日時を設定します。
	 * 
	 * @param createdAt　作成日時
	 * @since v0.0.1
	 */
	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * お気に入られを設定します。
	 * 
	 * @param favorited お気に入られ
	 * @since v0.0.1
	 */
	void setFavorited(int favorited) {
		this.favorited = favorited;
	}

	/**
	 * 返信元ステータスIDを設定します。
	 * 
	 * @param inReplyToStatusId 返信元ステータスID
	 * @since v0.0.1
	 */
	void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	/**
	 * 返信元ユーザIDを設定します。
	 * 
	 * @param inReplyToUserId 返信元ユーザID
	 * @since v0.0.1
	 */
	void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	/**
	 * キーワードを設定します。
	 * 
	 * @param keyword キーワード
	 * @since v0.0.1
	 */
	void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * リンクを設定します。
	 * 
	 * @param link リンク
	 * @since v0.0.1
	 */
	void setLink(String link) {
		this.link = link;
	}

	/**
	 * このステータスへの返信を設定します。
	 * 
	 * @param replies このステータスへの返信
	 * @since v0.0.1
	 */
	void setReplies(List<Status> replies) {
		this.replies = replies;
	}
	
	/**
	 * ソース（クライアント名）を設定します。
	 * 
	 * @param source ソース（クライアント名）
	 * @since v0.0.1
	 */
	void setSource(String source) {
		this.source = source;
	}

	/**
	 * 投稿内容を設定します。
	 * 
	 * @param text 投稿内容
	 * @since v0.0.1
	 */
	void setText(String text) {
		this.text = text;
	}

	/**
	 * ユーザ情報を設定します。
	 * 
	 * @param user ユーザ情報
	 * @since v0.0.1
	 */
	void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * このステータスへの返信として取得した内容かどうかを設定します。
	 * 
	 * @param shadow このステータスへの返信として取得した内容かどうか
	 * @since v0.1.0
	 */
	void setShadow(boolean shadow) {
		this.shadow = shadow;
	}

	/**
	 * textの「xxxx=本文」の "xxxx="部分を取り除く。<br/>
	 * 前提：keyword, textが設定されていること。（keywordはidページの場合id:xxx形式で格納済みであること）
	 * 
	 * @since v0.1.0
	 */
	void removeKeywordEqualOnText() {
		if (!"".equals(keyword) && !isIdPage()) {
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
