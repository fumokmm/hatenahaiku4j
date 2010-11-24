package hatenahaiku4j;

import hatenahaiku4j.util.Util;

import java.util.Date;
import java.util.List;

/**
 * �͂Ăȃn�C�N�X�e�[�^�X����\������N���X
 * 
 * @author fumokmm
 */
public class Status {
	/** �X�e�[�^�XID */
	private String id;
	/** �쐬���� */
	private Date createdAt;
	/** ���C�ɓ���� */
	private int favorited;
	/** �ԐM��X�e�[�^�XID */
	private String inReplyToStatusId;
	/** �ԐM�惆�[�UID */
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
	
	/** @return �X�e�[�^�XID */
	public String getStatusId() {
		return id;
	}

	/** @return �쐬���� */
	public Date getCreatedAt() {
		return createdAt;
	}

	/** @return ���C�ɓ���� */
	public int getFavorited() {
		return favorited;
	}

	/** @return �ԐM��X�e�[�^�XID */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/** @return �ԐM�惆�[�UID */
	public String getInReplyToUserId() {
		return inReplyToUserId;
	}

	/** @return �L�[���[�h */
	public String getKeyword() {
		return keyword;
	}

	/** @return id�y�[�W���ǂ��� */
	public boolean isIdPage() {
		return Util.isHatenaIdFormat(keyword);
	}

	/** @return �����N */
	public String getLink() {
		return link;
	}

	/** ���̃X�e�[�^�X�ւ̕ԐM */
	public List<Status> getReplies() {
		return replies;
	}
	
	/** @return �\�[�X�i�N���C�A���g���j */
	public String getSource() {
		return source;
	}

	/** @return ���e���e */
	public String getText() {
		return text;
	}

	/** @return ���[�U��� */
	public User getUser() {
		return user;
	}
	
	/** @return ���[�UID */
	public String getUserId() {
		return user.getUserId();
	}

	/**
	 * {@link #getShadow()}�̃G�C���A�X�ł��B
	 * @return ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ���
	 */
	public boolean isShadow() {
		return shadow;
	}
	/** @return ���̃X�e�[�^�X�ւ̕ԐM�Ƃ��Ď擾�������e���ǂ��� */
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
	 * text�́uxxxx=�{���v�� "xxxx="��������菜���B<br/>
	 * �O��Fkeyword, text���ݒ肳��Ă��邱�ƁB�ikeyword��id�y�[�W�̏ꍇid:xxx�`���Ŋi�[�ς݂ł��邱�Ɓj
	 */
	void removeKeywordEqualOnText() {
		if (!keyword.equals("") && !isIdPage()) {
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
