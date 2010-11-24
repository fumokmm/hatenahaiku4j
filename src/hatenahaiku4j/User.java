package hatenahaiku4j;

/**
 * �͂Ăȃn�C�N���[�U����\������N���X
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class User {
	/** ���[�U�� */
	private String name;
	/** �t�H�����[�� */
	private int followersCount;
	/** ���[�UID */
	private String id;
	/** �v���t�B�[���摜URL */
	private String profileImageUrl;
	/** �\���� */
	private String screenName;
	/** ���[�U�̃G���g���y�[�W��URL */
	private String url;

	/** ���[�UAPI */
	public final UserAPI api;

	/**
	 * �R���X�g���N�^�ł��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @since v0.0.1
	 */
	private User() {
		this.api = new UserAPI();
	}
	
	/**
	 * �w�肵�����[�U�ł��̃��[�U���㏑�����܂��B
	 * 
	 * @param other �㏑�����郆�[�U
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
	 * �C���X�^���X���擾���܂��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @param apiAuth �͂Ăȃn�C�NAPI�i�F�؂���j
	 * @since v0.2.0
	 */
	static User create(HatenaHaikuAPI apiAuth) {
		User user = new User();
		user.api.init(user, apiAuth);
		return user;
	}

	/**
	 * �C���X�^���X���擾���܂��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @param apiLight �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j
	 * @since v0.2.0
	 */
	static User create(HatenaHaikuAPILight apiLight) {
		User user = new User();
		user.api.init(user, apiLight);
		return user;
	}

	/**
	 * ���[�U�����擾���܂��B
	 * 
	 * @return ���[�U��
	 * @since v0.0.1
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * �t�H�����[�����擾���܂��B
	 * 
	 * @return �t�H�����[��
	 * @since v0.0.1
	 */
	public int getFollowersCount() {
		return followersCount;
	}

	/**
	 * ���[�UID���擾���܂��B
	 * 
	 * @return ���[�UID
	 * @since v0.0.1
	 */
	public String getUserId() {
		return id;
	}

	/**
	 * id�L�@�̃��[�UID���擾���܂��B (id:xxx)
	 * 
	 * @return ���[�Uid�L�@ (id:xxx)
	 * @since v0.2.0
	 */
	public String getUserIdNotation() {
		return Const.ID_COLON + id;
	}
	
	/**
	 * �v���t�B�[���摜URL���擾���܂��B
	 * 
	 * @return �v���t�B�[���摜URL
	 * @since v0.0.1
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	
	/**
	 * �v���t�B�[���摜(��)URL���擾���܂��B
	 * 
	 * @return �v���t�B�[���摜(��)URL
	 * @since v0.0.1
	 */
	public String getProfileImageSmallUrl() {
		return profileImageUrl.replaceFirst("profile\\.gif$", "profile_s.gif");
	}
	
	/**
	 * �\�������擾���܂��B
	 * 
	 * @return �\����
	 * @since v0.0.1
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * ���[�U�̃G���g���y�[�W��URL���擾���܂��B
	 * 
	 * @return ���[�U�̃G���g���y�[�W��URL
	 * @since v0.0.1
	 */
	public String getEntriesUrl() {
		return url;
	}
	
	/**
	 * ���[�U�̃t�H���E�B���O�y�[�W��URL���擾���܂��B
	 * 
	 * @return ���[�U�̃t�H���E�B���O�y�[�W��URL
	 * @since v0.0.1
	 */
	public String getFollowingUrl() {
		return url + FOLLOWING;
	}
	
	/** URL: following */
	private static final String FOLLOWING = "following";

	/**
	 * ���[�U�̃v���t�B�[���y�[�W��URL���擾���܂��B
	 * 
	 * @return ���[�U�̃v���t�B�[���y�[�W��URL
	 * @since v0.0.1
	 */
	public String getProfileUrl() {
		return Const.BASE_URL + Const.ID + "/" + getUserId();
	}

	/**
	 * ���[�U����ݒ肵�܂��B
	 * 
	 * @param name ���[�U��
	 * @since v0.0.1
	 */
	void setName(String name) {
		this.name = name;
	}
	
	/**
	 * �t�H�����[����ݒ肵�܂��B
	 * 
	 * @param followersCount �t�H�����[��
	 * @since v0.0.1
	 */
	void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * ���[�UID��ݒ肵�܂��B
	 * 
	 * @param userId ���[�UID
	 * @since v0.0.1
	 */
	void setUserId(String userId) {
		this.id = userId;
	}

	/**
	 * �v���t�B�[���摜URL��ݒ肵�܂��B
	 * 
	 * @param profileImageUrl �v���t�B�[���摜URL
	 * @since v0.0.1
	 */
	void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	/**
	 * �\������ݒ肵�܂��B
	 * 
	 * @param screenName �\����
	 * @since v0.0.1
	 */
	void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	/**
	 * ���[�U�̃G���g���y�[�W��URL��ݒ肵�܂��B
	 * 
	 * @param url ���[�U�̃G���g���y�[�W��URL
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
