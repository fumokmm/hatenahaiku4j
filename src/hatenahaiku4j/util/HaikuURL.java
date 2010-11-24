package hatenahaiku4j.util;

import hatenahaiku4j.Const;

/**
 * �͂Ăȃn�C�N��URL�𐶐����郆�[�e�B���e�B�N���X�ł��B
 * 
 * @author fumokmm
 * @since v1.0.1
 */
public class HaikuURL {

	//----------------------------------------------
	// Fields
	
	/** �����N */
	private String link;
	/** ���o�C���p�̃����N */
	private String mobileLink;
	
	/** �G�X�P�[�v���ꂽ�����N */
	private String escapedLink;
	/** ���o�C���p�̃G�X�P�[�v���ꂽ�����N */
	private String escapedMobileLink;

	//----------------------------------------------
	// Constructors

	/* �v���C�x�[�g�R���X�g���N�^ */
	private HaikuURL() {
		this.link = "";
		this.mobileLink = "";
		this.escapedLink = "";
		this.escapedMobileLink = "";
	}
	
	//----------------------------------------------
	// Create Methods

	/**
	 * �L�[���[�h���w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �L�[���[�h���󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @return �L�[���[�h��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byKeyword(String keyword) {
		return _byKeyword(keyword, null);
	}

	/**
	 * �L�[���[�h�ƃ��x�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �G�X�P�[�v�̓��x���Ɏ{����܂��B<br/>
	 * �L�[���[�h���󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @param label �����N��u�������郉�x��
	 * @return �L�[���[�h��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byKeyword(String keyword, String label) {
		return _byKeyword(keyword, label);
	}
	
	/**
	 * �L�[���[�h�ƃ��x�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �G�X�P�[�v�̓��x���Ɏ{����܂��B<br/>
	 * ���x�����󂾂����ꍇ�A�ʏ�̃L�[���[�h�����N���{���܂��B<br/>
	 * �L�[���[�h���󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @param label �����N��u�������郉�x��
	 * @return �L�[���[�h��HaikuURL
	 * @since v1.0.1
	 */
	private static HaikuURL _byKeyword(String keyword, String label) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(keyword)) {
			return haikuUrl;
		}
		// ���x��������Ƃ��́A���x����URL�����N�ɂ���
		if (StringUtil.isEmpty(label)) {
			haikuUrl.link				= toKeywordLink(keyword);
			haikuUrl.mobileLink			= toKeywordLink(keyword);
			haikuUrl.escapedLink		= toUrlLink(Const.KEYWORD_BASE_URL,			keyword, keyword, true);
			haikuUrl.escapedMobileLink	= toUrlLink(Const.MOBILE_KEYWORD_BASE_URL,	keyword, keyword, true);
		} else {
			haikuUrl.link				= toUrlLink(Const.KEYWORD_BASE_URL,			keyword, label, false);
			haikuUrl.mobileLink			= toUrlLink(Const.MOBILE_KEYWORD_BASE_URL,	keyword, label, false);
			haikuUrl.escapedLink		= toUrlLink(Const.KEYWORD_BASE_URL,			keyword, label, true);
			haikuUrl.escapedMobileLink	= toUrlLink(Const.MOBILE_KEYWORD_BASE_URL,	keyword, label, true);
		}
		return haikuUrl;
	}
	
	/**
	 * ���[�UID���w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * ���[�UID���󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param userId ���[�UID
	 * @return ���[�UID��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byUserId(String userId) {
		return _byUserId(userId, null);
	}
	
	/**
	 * ���[�UID�ƃ��x�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �G�X�P�[�v�̓��x���Ɏ{����܂��B<br/>
	 * ���[�UID���󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param userId ���[�UID
	 * @param label �����N��u�������郉�x��
	 * @return ���[�UID��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byUserId(String userId, String label) {
		return _byUserId(userId, label);
	}
	
	/**
	 * ���[�UID�ƃ��x�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �G�X�P�[�v�̓��x���Ɏ{����܂��B<br/>
	 * ���x�����󂾂����ꍇ�A�ʏ�̃��[�UID�����N���{���܂��B<br/>
	 * ���[�UID���󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param userId ���[�UID
	 * @param label �����N��u�������郉�x��
	 * @return ���[�UID��HaikuURL
	 * @since v1.0.1
	 */
	private static HaikuURL _byUserId(String userId, String label) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(userId)) {
			return haikuUrl;
		}
		// ���x��������Ƃ��́A���x����ID�����N�ɂ���
		if (StringUtil.isEmpty(label)) {
			haikuUrl.link				= toIdLink(userId);
			haikuUrl.mobileLink			= toIdLink(userId);
			haikuUrl.escapedLink		= toUrlLink(Const.BASE_URL,			userId, Const.ID_COLON + userId, true);
			haikuUrl.escapedMobileLink	= toUrlLink(Const.MOBILE_BASE_URL,	userId, Const.ID_COLON + userId, true);
		} else {
			haikuUrl.link				= toUrlLink(Const.BASE_URL,			userId, label, false);
			haikuUrl.mobileLink			= toUrlLink(Const.MOBILE_BASE_URL,	userId, label, false);
			haikuUrl.escapedLink		= toUrlLink(Const.BASE_URL,			userId, label, true);
			haikuUrl.escapedMobileLink	= toUrlLink(Const.MOBILE_BASE_URL,	userId, label, true);
		}
		return haikuUrl;
	}

	/**
	 * ASIN�R�[�h���w�肵�āAasin�L�@��HaikuURL�𐶐����܂��B
	 * 
	 * @param asinCode ASIN�R�[�h
	 * @return asin�L�@��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byASIN(String asinCode) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(asinCode)) {
			return haikuUrl;
		}
		String link = toASINLink(asinCode);
		haikuUrl.link				= link;
		haikuUrl.mobileLink			= link;
		haikuUrl.escapedLink		= link;
		haikuUrl.escapedMobileLink	= link;
		return haikuUrl;
	}

	/**
	 * YouTube��"watch?v="�ȍ~�̓���ID���w�肵�āAyoutube�����HaikuURL�𐶐����܂��B
	 * 
	 * @param youtubeId YouTube����̓���ID
	 * @return YouTube����̃����N��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byYouTube(String youtubeId) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(youtubeId)) {
			return haikuUrl;
		}
		String link = toYouTubeLink(youtubeId);
		haikuUrl.link				= link;
		haikuUrl.mobileLink			= link;
		haikuUrl.escapedLink		= link;
		haikuUrl.escapedMobileLink	= link;
		return haikuUrl;
	}

	/**
	 * �j�R�j�R����̓���ID("sm�`�`"�`��)���w�肵�āA�j�R�j�R�����URL�𐶐����܂��B
	 * 
	 * @param nico2Id �j�R�j�R����̓���ID
	 * @return �j�R�j�R����̃����N��HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byNico2(String nico2Id) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(nico2Id)) {
			return haikuUrl;
		}
		String link = toNico2Link(nico2Id);
		haikuUrl.link				= link;
		haikuUrl.mobileLink			= link;
		haikuUrl.escapedLink		= link;
		haikuUrl.escapedMobileLink	= link;
		return haikuUrl;
	}

	/**
	 * URL�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * URL��񂪋󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param url URL���
	 * @return URL����HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byURL(String url) {
		return _byURL(url, null);
	}

	/**
	 * URL���ƃ��x�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �G�X�P�[�v�̓��x���Ɏ{����܂��B<br/>
	 * URL��񂪋󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param url URL���
	 * @param label �����N��u�������郉�x��
	 * @return URL����HaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byURL(String url, String label) {
		return _byURL(url, label);
	}
	
	/**
	 * URL���ƃ��x�����w�肵�āAHaikuURL�𐶐����܂��B<br/>
	 * �G�X�P�[�v�̓��x���Ɏ{����܂��B<br/>
	 * ���x�����󂾂����ꍇ�A�ʏ�̃����N�ƂȂ�܂��B<br/>
	 * URL��񂪋󂾂����ꍇ�A���HaikuURL����������܂��B
	 * 
	 * @param url URL���
	 * @param label �����N��u�������郉�x��
	 * @return URL����HaikuURL
	 * @since v1.0.1
	 */
	private static HaikuURL _byURL(String url, String label) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(url)) {
			return haikuUrl;
		}
		if (StringUtil.isEmpty(label)) {
			haikuUrl.link				= url;
			haikuUrl.mobileLink			= url;
			haikuUrl.escapedLink		= toUrlLink(null, url, url, true);
			haikuUrl.escapedMobileLink	= toUrlLink(null, url, url, true);
		} else {
			haikuUrl.link				= toUrlLink(null, url, label, false);
			haikuUrl.mobileLink			= toUrlLink(null, url, label, false);
			haikuUrl.escapedLink		= toUrlLink(null, url, label, true);
			haikuUrl.escapedMobileLink	= toUrlLink(null, url, label, true);
		}
		return haikuUrl;
	}

	//----------------------------------------------
	// Getter/Setter Methods

	/**
	 * �����N���擾���܂��B
	 * 
	 * @return �����N
	 * @since v1.0.1
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * �G�X�P�[�v���ꂽ�����N���擾���܂��B
	 * 
	 * @return �G�X�P�[�v���ꂽ�����N
	 * @since v1.0.1
	 */
	public String getEscapedLink() {
		return this.escapedLink;
	}

	/**
	 * ���o�C���p�����N���擾���܂��B
	 * 
	 * @return ���o�C���p�����N
	 * @since v1.0.1
	 */
	public String getMobileLink() {
		return this.mobileLink;
	}

	/**
	 * ���o�C���p�G�X�P�[�v���ꂽ�����N���擾���܂��B
	 * 
	 * @return ���o�C���p�G�X�P�[�v���ꂽ�����N
	 * @since v1.0.1
	 */
	public String getEscapedMobileLink() {
		return this.escapedMobileLink;
	}

	//----------------------------------------------
	// Utility Methods
	
	/**
	 * �L�[���[�h�����N�ɕϊ����܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @return �L�[���[�h�����N
	 * @since v1.0.1
	 */
	private static String toKeywordLink(String keyword) {
		return "[[" + keyword + "]]";
	}
	
	/**
	 * ID�����N�ɕϊ����܂��B
	 * 
	 * @param userId ���[�UID
	 * @return ID�����N
	 * @since v1.0.1
	 */
	private static String toIdLink(String userId) {
		return Const.ID_COLON + userId;
	}
	
	/**
	 * ASIN�L�@��URL�ɕϊ����܂��B
	 * 
	 * @param asinCode ASIN�R�[�h
	 * @return ASIN�L�@��URL
	 * @since v1.0.1
	 */
	private static String toASINLink(String asinCode) {
		return "asin:" + asinCode;
	}

	/**
	 * YouTube�L�@��URL�ɕϊ����܂��B
	 * 
	 * @param youtubeId YouTube����̓���ID
	 * @return YouTube�L�@��URL
	 * @since v1.0.1
	 */
	private static String toYouTubeLink(String youtubeId) {
		return "http://www.youtube.com/watch?v=" + youtubeId;
	}
	
	/**
	 * �j�R�j�R����L�@��URL�ɕϊ����܂��B
	 * 
	 * @param nico2Id �j�R�j�R����̓���ID
	 * @return �j�R�j�R����L�@��URL
	 * @since v1.0.1
	 */
	private static String toNico2Link(String nico2Id) {
		return "http://www.nicovideo.jp/watch/" + nico2Id;
	}
	
	/**
	 * URL�����N�L�@��URL�ɕϊ����܂��B
	 * 
	 * @param baseUrl �x�[�XURL
	 * @param info ���
	 * @param label ���x��
	 * @param needEscape �G�X�P�[�v�v��
	 * @return URL�����N�L�@��URL
	 * @since v1.0.1
	 */
	private static String toUrlLink(String baseUrl, String info, String label, boolean needEscape) {
		String url = StringUtil.isEmpty(baseUrl) ?
				shortenURL(info) :
				shortenURL(baseUrl + encode(info));
		// URL�\�z(URL�L�@)
		return needEscape ?
			"[" + url + ":title=" + escape(label) + "]" :
			"[" + url + ":title=" + label + "]";
	}

	/**
	 * URL�G���R�[�h���{��
	 * 
	 * @see StringUtil#encode(String)
	 * @param str URLEncode���镶����
	 * @return �G���R�[�h���ꂽ������
	 * @since v1.0.1
	 */
	private static String encode(String str) {
		return StringUtil.encode(str);
	}

	/**
	 * :�Ȃǂ̕������G�X�P�[�v����
	 * 
	 * @see HatenaUtil#escapeHatenaNotation(String)
	 * @param str �G�X�P�[�v�Ώۂ̕�����
	 * @return �G�X�P�[�v����������
	 * @since v1.0.1
	 */
	private static String escape(String str) {
		return HatenaUtil.escapeHatenaNotation(str);
	}

	/**
	 * URL��Z�k����B��������
	 * 
	 * @param str �Z�k�Ώۂ�URL
	 * @return �Z�k���URL
	 */
	private static String shortenURL(String str) {
		return str;
	}
}
