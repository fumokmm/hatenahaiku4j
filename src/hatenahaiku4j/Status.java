package hatenahaiku4j;

import hatenahaiku4j.util.HatenaUtil;

import java.util.Date;
import java.util.List;

/**
 * �͂Ăȃn�C�N�X�e�[�^�X����\������N���X
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Status {
	/** �X�e�[�^�XID */
	private String id;
	/** �쐬���� */
	private Date createdAt;
	/** ���C�ɓ����i�����Ă��ꂽ�l���j */
	private int favorited;
	/** �ԐM���X�e�[�^�XID */
	private String inReplyToStatusId;
	/** �ԐM�����[�UID */
	private String inReplyToUserId;
	/** �L�[���[�h */
	private String keyword;
	/** �����N */
	private String link;
	/** ���̃X�e�[�^�X�ւ̕ԐM */
	private List<Status> replies;
	/** �\�[�X�i�N���C�A���g���j */
	private String source;
	/** ���e���e */
	private String text;
	/** ���[�U��� */
	private User user;
	
	/** ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ��� */
	private boolean shadow;
	/** �X�e�[�^�XAPI */
	public final StatusAPI api;

	/**
	 * �R���X�g���N�^�ł��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @since v0.0.1
	 */
	private Status() {
		this.api = new StatusAPI();
	}
	
	/**
	 * �w�肵�����[�U�ł��̃��[�U���㏑�����܂��B
	 * 
	 * @param other �㏑�����郆�[�U
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
	 * �C���X�^���X���擾���܂��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @param apiAuth �͂Ăȃn�C�NAPI�i�F�؂���j
	 * @since v0.2.0
	 */
	static Status create(HatenaHaikuAPI apiAuth) {
		Status status = new Status();
		status.api.init(status, apiAuth);
		return status;
	}

	/**
	 * �C���X�^���X���擾���܂��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @param apiLight �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j
	 * @since v0.2.0
	 */
	static Status create(HatenaHaikuAPILight apiLight) {
		Status status = new Status();
		status.api.init(status, apiLight);
		return status;
	}
	
	/**
	 * �X�e�[�^�XID���擾���܂��B
	 * 
	 * @return �X�e�[�^�XID 
	 * @since v0.0.1
	 */
	public String getStatusId() {
		return id;
	}

	/**
	 * �쐬�������擾���܂��B
	 * 
	 * @return �쐬����
	 * @since v0.0.1
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * �쐬�������͂Ăȓ��t�`��������Ŏ擾���܂��B
	 * 
	 * @return �쐬�����i�͂Ăȓ��t�`��������j
	 * @since v0.2.0
	 */
	public String getCreatedAtString() {
		return HatenaUtil.formatDate(createdAt);
	}

	/**
	 * ���C�ɓ����i�����Ă��ꂽ�l���j���擾���܂��B<br/>
	 * �t�����X�^�[�̐��łȂ����Ƃɒ��ӂ��Ă��������B
	 * 
	 * @return ���C�ɓ����i�����Ă��ꂽ�l���j
	 * @since v0.0.1
	 */
	public int getFavorited() {
		return favorited;
	}

	/**
	 * �ԐM���X�e�[�^�XID���擾���܂��B
	 * 
	 * @return �ԐM���X�e�[�^�XID
	 * @since v0.0.1
	 */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/**
	 * �ԐM�����[�UID���擾���܂��B
	 * 
	 * @return �ԐM�����[�UID
	 * @since v0.0.1
	 */
	public String getInReplyToUserId() {
		return inReplyToUserId;
	}

	/**
	 * �L�[���[�h���擾���܂��B
	 * 
	 * @return �L�[���[�h 
	 * @since v0.0.1
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * id�y�[�W���ǂ����擾���܂��B
	 * 
	 * @return id�y�[�W���ǂ���
	 * @since v0.0.1
	 */
	public boolean isIdPage() {
		return HatenaUtil.isIdNotation(keyword);
	}

	/**
	 * �����N���擾���܂��B
	 * 
	 * @return �����N 
	 * @since v0.0.1
	 */
	public String getLink() {
		return link;
	}

	/**
	 * ���o�C���p�����N���擾���܂��B
	 * 
	 * @return ���o�C���p�����N 
	 * @since v1.0.0
	 */
	public String getMobileLink() {
		return link.replace(Const.BASE_URL, Const.MOBILE_BASE_URL);
	}

	/**
	 * ���̃X�e�[�^�X�ւ̕ԐM��ԋp���܂��B
	 * 
	 * @return ���̃X�e�[�^�X�ւ̕ԐM
	 * @since v0.1.0
	 */
	public List<Status> getReplies() {
		// �����X�V����Ȃ�
		if (api.apiLight.isAutoRefreshReplies()) {
			if (shadow) {
				try {
					this.api.refreshReplies();
				} catch (HatenaHaikuException e) {
					System.out.println("�ԐM��擾���s�B");
				}
			}
		}
		return replies;
	}
	
	/**
	 * �\�[�X�i�N���C�A���g���j���擾���܂��B
	 * 
	 * @return �\�[�X�i�N���C�A���g���j
	 * @since v0.0.1
	 */
	public String getSource() {
		return source;
	}

	/**
	 * ���e���e���擾���܂��B
	 * 
	 * @return ���e���e
	 * @since v0.0.1
	 */
	public String getText() {
		return text;
	}

	/**
	 * ���[�U�����擾���܂��B
	 * 
	 * @return ���[�U���
	 * @since v0.0.1
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * ���[�UID���擾���܂��B
	 * 
	 * {@link #getUser()}�Ŏ擾�ł��郆�[�U��{@link User#getUserId()}�̃G�C���A�X�ł��B
	 * @return ���[�UID
	 * @since v0.0.1
	 */
	public String getUserId() {
		return user.getUserId();
	}

	/**
	 * id�L�@�̃��[�UID���擾���܂��B<br/>
	 * {@link #getUser()}�Ŏ擾�ł��郆�[�U��{@link User#getUserIdNotation()}�̃G�C���A�X�ł��B
	 * 
	 * @return ���[�Uid�L�@
	 * @since v0.2.0
	 */
	public String getUserIdNotation() {
		return user.getUserIdNotation();
	}

	/**
	 * ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ������擾���܂��B
	 * 
	 * @return ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ���
	 * @since v0.1.0
	 */
	public boolean isShadow() {
		return shadow;
	}
	
	/**
	 * �X�e�[�^�XID��ݒ肵�܂��B
	 * 
	 * @param statusId �X�e�[�^�XID
	 * @since v0.0.1
	 */
	void setStatusId(String statusId) {
		this.id = statusId;
	}

	/**
	 * �쐬������ݒ肵�܂��B
	 * 
	 * @param createdAt�@�쐬����
	 * @since v0.0.1
	 */
	void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * ���C�ɓ�����ݒ肵�܂��B
	 * 
	 * @param favorited ���C�ɓ����
	 * @since v0.0.1
	 */
	void setFavorited(int favorited) {
		this.favorited = favorited;
	}

	/**
	 * �ԐM���X�e�[�^�XID��ݒ肵�܂��B
	 * 
	 * @param inReplyToStatusId �ԐM���X�e�[�^�XID
	 * @since v0.0.1
	 */
	void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	/**
	 * �ԐM�����[�UID��ݒ肵�܂��B
	 * 
	 * @param inReplyToUserId �ԐM�����[�UID
	 * @since v0.0.1
	 */
	void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	/**
	 * �L�[���[�h��ݒ肵�܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @since v0.0.1
	 */
	void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * �����N��ݒ肵�܂��B
	 * 
	 * @param link �����N
	 * @since v0.0.1
	 */
	void setLink(String link) {
		this.link = link;
	}

	/**
	 * ���̃X�e�[�^�X�ւ̕ԐM��ݒ肵�܂��B
	 * 
	 * @param replies ���̃X�e�[�^�X�ւ̕ԐM
	 * @since v0.0.1
	 */
	void setReplies(List<Status> replies) {
		this.replies = replies;
	}
	
	/**
	 * �\�[�X�i�N���C�A���g���j��ݒ肵�܂��B
	 * 
	 * @param source �\�[�X�i�N���C�A���g���j
	 * @since v0.0.1
	 */
	void setSource(String source) {
		this.source = source;
	}

	/**
	 * ���e���e��ݒ肵�܂��B
	 * 
	 * @param text ���e���e
	 * @since v0.0.1
	 */
	void setText(String text) {
		this.text = text;
	}

	/**
	 * ���[�U����ݒ肵�܂��B
	 * 
	 * @param user ���[�U���
	 * @since v0.0.1
	 */
	void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ�����ݒ肵�܂��B
	 * 
	 * @param shadow ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ���
	 * @since v0.1.0
	 */
	void setShadow(boolean shadow) {
		this.shadow = shadow;
	}

	/**
	 * text�́uxxxx=�{���v�� "xxxx="��������菜���B<br/>
	 * �O��Fkeyword, text���ݒ肳��Ă��邱�ƁB�ikeyword��id�y�[�W�̏ꍇid:xxx�`���Ŋi�[�ς݂ł��邱�Ɓj
	 * 
	 * @since v0.1.0
	 */
	void removeKeywordEqualOnText() {
		if (!"".equals(keyword) && !isIdPage()) {
			// �󔒂ł�id�y�[�W�ł��Ȃ��ꍇ�Atext�ɂ́u�L�[���[�h=�v�����ɂ��Ă���̂ŁA��菜��
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

    <keyword>�͂Ăȃf�t�H���g����</keyword>
    <link>http://h.hatena.ne.jp/jkondo/12345678901234</link>
    <replies>                                         |
    	in_reply_to_status_id, in_reply_to_user_id    | �������Areplies��
		keyword, link, replies ������<status>           | �g�b�v���x��status�ɂ̂ݕt�������B
	</replies>                                        |
		�E
		�E�ȉ��ԐM�敪�Areplies�̌J��Ԃ�
		�E
    <source>web</source>
    <text>�͂Ăȃf�t�H���g����=����ɂ��́A���킢���f�t�H���g����ł��ˁB</text>
    <user>
      <name>jkondo</name>
      <followers_count>1</followers_count>
      <id>jkondo</id>
      <profile_image_url>http://www.hatena.ne.jp/users/jk/jkondo/profile.gif</profile_image_url>
      <screen_name>jkondo</screen_name>
      <url>http://h.hatena.ne.jp/jkondo/</url>
    </user>
  </status>
  <!-- status �v�f�������܂��B -->
</statuses>
--------------------------------------------- */
