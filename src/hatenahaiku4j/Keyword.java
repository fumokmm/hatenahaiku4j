package hatenahaiku4j;

import hatenahaiku4j.util.HatenaUtil;

import java.util.List;

/**
 * �͂Ăȃn�C�N�L�[���[�h����\������N���X
 * 
 * @since v0.0.1
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

	/**
	 * �L�[���[�hAPI
	 * 
	 * @since v0.2.0
	 */
	public final KeywordAPI api;

	/**
	 * �R���X�g���N�^�ł��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * @since v0.0.1
	 */
	private Keyword() {
		this.api = new KeywordAPI();
	}
	
	/**
	 * �w�肵���L�[���[�h�ł��̃L�[���[�h���㏑�����܂��B
	 * 
	 * @param other �㏑������L�[���[�h
	 * @since v0.2.0
	 */
	void overwrite(Keyword other) {
		this.entryCount = other.entryCount;
		this.followersCount = other.followersCount;
		this.link = other.link;
		this.relatedKeywords = other.relatedKeywords;
		this.title = other.title;
	}
	
	/**
	 * �C���X�^���X���擾���܂��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @param apiAuth �͂Ăȃn�C�NAPI�i�F�؂���j
	 * @since v0.2.0
	 */
	static Keyword create(HatenaHaikuAPI apiAuth) {
		Keyword keyword = new Keyword();
		keyword.api.init(keyword, apiAuth);
		return keyword;
	}

	/**
	 * �C���X�^���X���擾���܂��B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @param apiLight �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j
	 * @since v0.2.0
	 */
	static Keyword create(HatenaHaikuAPILight apiLight) {
		Keyword keyword = new Keyword();
		keyword.api.init(keyword, apiLight);
		return keyword;
	}

	/**
	 * ���e�����擾���܂��B
	 * 
	 * @return ���e��
	 * @since v0.0.1
	 */
	public int getEntryCount() {
		return entryCount;
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
	 * �L�[���[�h�y�[�W�̃����N���擾���܂��B
	 * 
	 * @return �L�[���[�h�y�[�W�̃����N 
	 * @since v0.0.1
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * ���o�C���p�L�[���[�h�y�[�W�̃����N���擾���܂��B
	 * 
	 * @return �L�[���[�h�y�[�W�̃����N 
	 * @since v1.0.0
	 */
	public String getMobileLink() {
		return link.replace(Const.BASE_URL, Const.MOBILE_BASE_URL);
	}

	/**
	 * �֘A�L�[���[�h�̃��X�g���擾���܂��B
	 * 
	 * @return �֘A�L�[���[�h�̃��X�g
	 * @since v0.0.1
	 */
	public List<String> getRelatedKeywords() {
		return relatedKeywords;
	}

	/**
	 * �L�[���[�h�^�C�g�����擾���܂��B
	 * 
	 * @return �L�[���[�h�^�C�g��
	 * @since v0.0.1
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * id�y�[�W���ǂ������擾���܂��B
	 * 
	 * @return id�y�[�W���ǂ���
	 * @since v0.0.1
	 */
	public boolean isIdPage() {
		return HatenaUtil.isIdNotation(title);
	}

	/**
	 * ���e����ݒ肵�܂��B
	 * 
	 * @param entryCount ���e��
	 * @since v0.0.1
	 */
	void setEntryCount(int entryCount) {
		this.entryCount = entryCount;
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
	 * �L�[���[�h�y�[�W�̃����N��ݒ肵�܂��B
	 * 
	 * @param link �L�[���[�h�y�[�W�̃����N
	 * @since v0.0.1
	 */
	void setLink(String link) {
		this.link = link;
	}

	/**
	 * �֘A�L�[���[�h��ݒ肵�܂��B
	 * 
	 * @param relatedKeywords �֘A�L�[���[�h
	 * @since v0.0.1
	 */
	void setRelatedKeywords(List<String> relatedKeywords) {
		this.relatedKeywords = relatedKeywords;
	}

	/**
	 * �L�[���[�h�^�C�g����ݒ肵�܂��B
	 * 
	 * @param title �L�[���[�h�^�C�g��
	 * @since v0.0.1
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
