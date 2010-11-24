package hatenahaiku4j;

/**
 * はてなハイクユーザ情報を表現するクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class User {
	/** ユーザ名 */
	private String name;
	/** フォロワー数 */
	private int followersCount;
	/** ユーザID */
	private String id;
	/** プロフィール画像URL */
	private String profileImageUrl;
	/** 表示名 */
	private String screenName;
	/** ユーザのエントリページのURL */
	private String url;

	/** ユーザAPI */
	public final UserAPI api;

	/**
	 * コンストラクタです。（パッケージプライベート）
	 * 
	 * @since v0.0.1
	 */
	private User() {
		this.api = new UserAPI();
	}
	
	/**
	 * 指定したユーザでこのユーザを上書きします。
	 * 
	 * @param other 上書きするユーザ
	 * @since v0.2.0
	 */
	void overwrite(User other) {
		this.name = other.name;
		this.followersCount = other.followersCount;
		this.id = other.id;
		this.profileImageUrl = other.profileImageUrl;
		this.screenName = other.screenName;
		this.url = other.url;
	}
	
	/**
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiAuth はてなハイクAPI（認証あり）
	 * @since v0.2.0
	 */
	static User create(HatenaHaikuAPI apiAuth) {
		User user = new User();
		user.api.init(user, apiAuth);
		return user;
	}

	/**
	 * インスタンスを取得します。（パッケージプライベート）
	 * 
	 * @param apiLight はてなハイクAPI（認証なし）
	 * @since v0.2.0
	 */
	static User create(HatenaHaikuAPILight apiLight) {
		User user = new User();
		user.api.init(user, apiLight);
		return user;
	}

	/**
	 * ユーザ名を取得します。
	 * 
	 * @return ユーザ名
	 * @since v0.0.1
	 */
	public String getName() {
		return name;
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
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 * @since v0.0.1
	 */
	public String getUserId() {
		return id;
	}

	/**
	 * id記法のユーザIDを取得します。 (id:xxx)
	 * 
	 * @return ユーザid記法 (id:xxx)
	 * @since v0.2.0
	 */
	public String getUserIdNotation() {
		return Const.ID_COLON + id;
	}
	
	/**
	 * プロフィール画像URLを取得します。
	 * 
	 * @return プロフィール画像URL
	 * @since v0.0.1
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	
	/**
	 * プロフィール画像(小)URLを取得します。
	 * 
	 * @return プロフィール画像(小)URL
	 * @since v0.0.1
	 */
	public String getProfileImageSmallUrl() {
		return profileImageUrl.replaceFirst("profile\\.gif$", "profile_s.gif");
	}
	
	/**
	 * 表示名を取得します。
	 * 
	 * @return 表示名
	 * @since v0.0.1
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * ユーザのエントリページのURLを取得します。
	 * 
	 * @return ユーザのエントリページのURL
	 * @since v0.0.1
	 */
	public String getEntriesUrl() {
		return url;
	}
	
	/**
	 * ユーザのフォロウィングページのURLを取得します。
	 * 
	 * @return ユーザのフォロウィングページのURL
	 * @since v0.0.1
	 */
	public String getFollowingUrl() {
		return url + FOLLOWING;
	}
	
	/** URL: following */
	private static final String FOLLOWING = "following";

	/**
	 * ユーザのプロフィールページのURLを取得します。
	 * 
	 * @return ユーザのプロフィールページのURL
	 * @since v0.0.1
	 */
	public String getProfileUrl() {
		return Const.BASE_URL + Const.ID + "/" + getUserId();
	}

	/**
	 * ユーザ名を設定します。
	 * 
	 * @param name ユーザ名
	 * @since v0.0.1
	 */
	void setName(String name) {
		this.name = name;
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
	 * ユーザIDを設定します。
	 * 
	 * @param userId ユーザID
	 * @since v0.0.1
	 */
	void setUserId(String userId) {
		this.id = userId;
	}

	/**
	 * プロフィール画像URLを設定します。
	 * 
	 * @param profileImageUrl プロフィール画像URL
	 * @since v0.0.1
	 */
	void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	/**
	 * 表示名を設定します。
	 * 
	 * @param screenName 表示名
	 * @since v0.0.1
	 */
	void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	/**
	 * ユーザのエントリページのURLを設定します。
	 * 
	 * @param url ユーザのエントリページのURL
	 * @since v0.0.1
	 */
	void setUrl(String url) {
		this.url = url;
	}

}

/* ------ sample xml ---------------------------
<user>
	<name>jkondo</name>
	<followers_count>1</followers_count>
	<id>jkondo</id>
	<profile_image_url>http://www.hatena.ne.jp/users/jk/jkondo/profile.gif</profile_image_url>
	<screen_name>jkondo</screen_name>
	<url>http://h.hatena.ne.jp/jkondo/</url>
</user>
--------------------------------------------- */
