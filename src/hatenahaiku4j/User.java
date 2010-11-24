package hatenahaiku4j;

/**
 * はてなハイクユーザ情報を表現するクラス
 * 
 * @author fumokmm
 */
public class User {
	/** ユーザ名？ */
	private String name;
	/** フォロワー数 */
	private int followersCount;
	/** ユーザID */
	private String id;
	/** プロフィール画像URL */
	private String profileImageUrl;
	/** 表示名？ */
	private String screenName;
	/** ユーザのエントリページのURL */
	private String url;

	/** @return ユーザ名？ */
	public String getName() {
		return name;
	}
	/** @return フォロワー数 */
	public int getFollowersCount() {
		return followersCount;
	}
	/** @return ユーザID */
	public String getUserId() {
		return id;
	}
	/** @return プロフィール画像URL */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	/** @return プロフィール画像(小)URL */
	public String getProfileImageSmallUrl() {
		return profileImageUrl.replaceFirst("profile\\.gif$", "profile_s.gif");
	}
	/** @return 表示名？ */
	public String getScreenName() {
		return screenName;
	}
	/** @return ユーザのエントリページのURL */
	public String getEntriesUrl() {
		return url;
	}
	/** @return ユーザのフォロウィングページのURL */
	public String getFollowingUrl() {
		return url + "following";
	}
	/** @return ユーザのプロフィールページのURL */
	public String getProfileUrl() {
		return Const.BASE_URL + Const.ID + "/" + getUserId();
	}
	/**
	 * @param name the name to set
	 */
	void setName(String name) {
		this.name = name;
	}
	/**
	 * @param followersCount the followersCount to set
	 */
	void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}
	/**
	 * @param id the id to set
	 */
	void setUserId(String userId) {
		this.id = userId;
	}
	/**
	 * @param profileImageUrl the profileImageUrl to set
	 */
	void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	/**
	 * @param screenName the screenName to set
	 */
	void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @param url the url to set
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
