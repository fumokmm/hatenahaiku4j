package hatenahaiku4j;

import hatenahaiku4j.util.Util;

import java.util.Date;

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
	/** �\�[�X�i�N���C�A���g���j */
	private String source;
	/** ���e���e */
	private String text;
	/** ���[�U��� */
	private User user;
	
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
