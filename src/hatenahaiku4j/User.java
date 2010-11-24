package hatenahaiku4j;

/**
 * �͂Ăȃn�C�N���[�U����\������N���X
 * 
 * @author fumokmm
 */
public class User {
	/** ���[�U���H */
	private String name;
	/** �t�H�����[�� */
	private int followersCount;
	/** ���[�UID */
	private String id;
	/** �v���t�B�[���摜URL */
	private String profileImageUrl;
	/** �\�����H */
	private String screenName;
	/** ���[�U�̃G���g���y�[�W��URL */
	private String url;

	/** @return ���[�U���H */
	public String getName() {
		return name;
	}
	/** @return �t�H�����[�� */
	public int getFollowersCount() {
		return followersCount;
	}
	/** @return ���[�UID */
	public String getUserId() {
		return id;
	}
	/** @return �v���t�B�[���摜URL */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	/** @return �v���t�B�[���摜(��)URL */
	public String getProfileImageSmallUrl() {
		return profileImageUrl.replaceFirst("profile\\.gif$", "profile_s.gif");
	}
	/** @return �\�����H */
	public String getScreenName() {
		return screenName;
	}
	/** @return ���[�U�̃G���g���y�[�W��URL */
	public String getEntriesUrl() {
		return url;
	}
	/** @return ���[�U�̃t�H���E�B���O�y�[�W��URL */
	public String getFollowingUrl() {
		return url + "following";
	}
	/** @return ���[�U�̃v���t�B�[���y�[�W��URL */
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
