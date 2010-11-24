package hatenahaiku4j;

import hatenahaiku4j.util.Util;

import java.util.List;

/**
 * �͂Ăȃn�C�N�L�[���[�h����\������N���X
 * 
 * @author fumokmm
 */
public class Keyword {
	/** ���e�� */
	private int entryCount;
	/** �t�H�����[�� */
	private int followersCount;
	/** �L�[���[�h�y�[�W�̃����N  */
	private String link;
	/** �֘A�L�[���[�h */
	private List<String> relatedKeywords;
	/** �L�[���[�h�^�C�g�� */
	private String title;
	
	/** @return ���e�� */
	public int getEntryCount() {
		return entryCount;
	}
	
	/** @return �t�H�����[�� */
	public int getFollowersCount() {
		return followersCount;
	}
	
	/** @return �L�[���[�h�y�[�W�̃����N  */
	public String getLink() {
		return link;
	}
	
	/** @return �֘A�L�[���[�h�̃��X�g */
	public List<String> getRelatedKeywords() {
		return relatedKeywords;
	}

	/** @return �L�[���[�h�^�C�g�� */
	public String getTitle() {
		return title;
	}
	
	/** @return id�y�[�W���ǂ��� */
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
	<link>http://h.hatena.ne.jp/�͂Ăȃn�C�N</link>
	<related_keywords>RELATED_KEYWORD</related_keywords>
	<!-- related_keywords �������܂��B -->
	<title>�͂Ăȃn�C�N</title>
</keyword>
--------------------------------------------- */
