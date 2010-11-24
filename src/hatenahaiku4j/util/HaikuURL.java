package hatenahaiku4j.util;

import hatenahaiku4j.Const;

/**
 * はてなハイクのURLを生成するユーティリティクラスです。
 * 
 * @author fumokmm
 * @since v1.0.1
 */
public class HaikuURL {

	//----------------------------------------------
	// Fields
	
	/** リンク */
	private String link;
	/** モバイル用のリンク */
	private String mobileLink;
	
	/** エスケープされたリンク */
	private String escapedLink;
	/** モバイル用のエスケープされたリンク */
	private String escapedMobileLink;

	//----------------------------------------------
	// Constructors

	/* プライベートコンストラクタ */
	private HaikuURL() {
		this.link = "";
		this.mobileLink = "";
		this.escapedLink = "";
		this.escapedMobileLink = "";
	}
	
	//----------------------------------------------
	// Create Methods

	/**
	 * キーワードを指定して、HaikuURLを生成します。<br/>
	 * キーワードが空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param keyword キーワード
	 * @return キーワードのHaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byKeyword(String keyword) {
		return _byKeyword(keyword, null);
	}

	/**
	 * キーワードとラベルを指定して、HaikuURLを生成します。<br/>
	 * エスケープはラベルに施されます。<br/>
	 * キーワードが空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param keyword キーワード
	 * @param label リンクを置き換えるラベル
	 * @return キーワードのHaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byKeyword(String keyword, String label) {
		return _byKeyword(keyword, label);
	}
	
	/**
	 * キーワードとラベルを指定して、HaikuURLを生成します。<br/>
	 * エスケープはラベルに施されます。<br/>
	 * ラベルが空だった場合、通常のキーワードリンクを施します。<br/>
	 * キーワードが空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param keyword キーワード
	 * @param label リンクを置き換えるラベル
	 * @return キーワードのHaikuURL
	 * @since v1.0.1
	 */
	private static HaikuURL _byKeyword(String keyword, String label) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(keyword)) {
			return haikuUrl;
		}
		// ラベルがあるときは、ラベルでURLリンクにする
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
	 * ユーザIDを指定して、HaikuURLを生成します。<br/>
	 * ユーザIDが空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param userId ユーザID
	 * @return ユーザIDのHaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byUserId(String userId) {
		return _byUserId(userId, null);
	}
	
	/**
	 * ユーザIDとラベルを指定して、HaikuURLを生成します。<br/>
	 * エスケープはラベルに施されます。<br/>
	 * ユーザIDが空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param userId ユーザID
	 * @param label リンクを置き換えるラベル
	 * @return ユーザIDのHaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byUserId(String userId, String label) {
		return _byUserId(userId, label);
	}
	
	/**
	 * ユーザIDとラベルを指定して、HaikuURLを生成します。<br/>
	 * エスケープはラベルに施されます。<br/>
	 * ラベルが空だった場合、通常のユーザIDリンクを施します。<br/>
	 * ユーザIDが空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param userId ユーザID
	 * @param label リンクを置き換えるラベル
	 * @return ユーザIDのHaikuURL
	 * @since v1.0.1
	 */
	private static HaikuURL _byUserId(String userId, String label) {
		HaikuURL haikuUrl = new HaikuURL();
		if (StringUtil.isEmpty(userId)) {
			return haikuUrl;
		}
		// ラベルがあるときは、ラベルでIDリンクにする
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
	 * ASINコードを指定して、asin記法のHaikuURLを生成します。
	 * 
	 * @param asinCode ASINコード
	 * @return asin記法のHaikuURL
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
	 * YouTubeの"watch?v="以降の動画IDを指定して、youtube動画のHaikuURLを生成します。
	 * 
	 * @param youtubeId YouTube動画の動画ID
	 * @return YouTube動画のリンクのHaikuURL
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
	 * ニコニコ動画の動画ID("sm～～"形式)を指定して、ニコニコ動画のURLを生成します。
	 * 
	 * @param nico2Id ニコニコ動画の動画ID
	 * @return ニコニコ動画のリンクのHaikuURL
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
	 * URL情報を指定して、HaikuURLを生成します。<br/>
	 * URL情報が空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param url URL情報
	 * @return URL情報のHaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byURL(String url) {
		return _byURL(url, null);
	}

	/**
	 * URL情報とラベルを指定して、HaikuURLを生成します。<br/>
	 * エスケープはラベルに施されます。<br/>
	 * URL情報が空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param url URL情報
	 * @param label リンクを置き換えるラベル
	 * @return URL情報のHaikuURL
	 * @since v1.0.1
	 */
	public static HaikuURL byURL(String url, String label) {
		return _byURL(url, label);
	}
	
	/**
	 * URL情報とラベルを指定して、HaikuURLを生成します。<br/>
	 * エスケープはラベルに施されます。<br/>
	 * ラベルが空だった場合、通常のリンクとなります。<br/>
	 * URL情報が空だった場合、空のHaikuURLが生成されます。
	 * 
	 * @param url URL情報
	 * @param label リンクを置き換えるラベル
	 * @return URL情報のHaikuURL
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
	 * リンクを取得します。
	 * 
	 * @return リンク
	 * @since v1.0.1
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * エスケープされたリンクを取得します。
	 * 
	 * @return エスケープされたリンク
	 * @since v1.0.1
	 */
	public String getEscapedLink() {
		return this.escapedLink;
	}

	/**
	 * モバイル用リンクを取得します。
	 * 
	 * @return モバイル用リンク
	 * @since v1.0.1
	 */
	public String getMobileLink() {
		return this.mobileLink;
	}

	/**
	 * モバイル用エスケープされたリンクを取得します。
	 * 
	 * @return モバイル用エスケープされたリンク
	 * @since v1.0.1
	 */
	public String getEscapedMobileLink() {
		return this.escapedMobileLink;
	}

	//----------------------------------------------
	// Utility Methods
	
	/**
	 * キーワードリンクに変換します。
	 * 
	 * @param keyword キーワード
	 * @return キーワードリンク
	 * @since v1.0.1
	 */
	private static String toKeywordLink(String keyword) {
		return "[[" + keyword + "]]";
	}
	
	/**
	 * IDリンクに変換します。
	 * 
	 * @param userId ユーザID
	 * @return IDリンク
	 * @since v1.0.1
	 */
	private static String toIdLink(String userId) {
		return Const.ID_COLON + userId;
	}
	
	/**
	 * ASIN記法のURLに変換します。
	 * 
	 * @param asinCode ASINコード
	 * @return ASIN記法のURL
	 * @since v1.0.1
	 */
	private static String toASINLink(String asinCode) {
		return "asin:" + asinCode;
	}

	/**
	 * YouTube記法のURLに変換します。
	 * 
	 * @param youtubeId YouTube動画の動画ID
	 * @return YouTube記法のURL
	 * @since v1.0.1
	 */
	private static String toYouTubeLink(String youtubeId) {
		return "http://www.youtube.com/watch?v=" + youtubeId;
	}
	
	/**
	 * ニコニコ動画記法のURLに変換します。
	 * 
	 * @param nico2Id ニコニコ動画の動画ID
	 * @return ニコニコ動画記法のURL
	 * @since v1.0.1
	 */
	private static String toNico2Link(String nico2Id) {
		return "http://www.nicovideo.jp/watch/" + nico2Id;
	}
	
	/**
	 * URLリンク記法のURLに変換します。
	 * 
	 * @param baseUrl ベースURL
	 * @param info 情報
	 * @param label ラベル
	 * @param needEscape エスケープ要否
	 * @return URLリンク記法のURL
	 * @since v1.0.1
	 */
	private static String toUrlLink(String baseUrl, String info, String label, boolean needEscape) {
		String url = StringUtil.isEmpty(baseUrl) ?
				shortenURL(info) :
				shortenURL(baseUrl + encode(info));
		// URL構築(URL記法)
		return needEscape ?
			"[" + url + ":title=" + escape(label) + "]" :
			"[" + url + ":title=" + label + "]";
	}

	/**
	 * URLエンコードを施す
	 * 
	 * @see StringUtil#encode(String)
	 * @param str URLEncodeする文字列
	 * @return エンコードされた文字列
	 * @since v1.0.1
	 */
	private static String encode(String str) {
		return StringUtil.encode(str);
	}

	/**
	 * :などの文字をエスケープする
	 * 
	 * @see HatenaUtil#escapeHatenaNotation(String)
	 * @param str エスケープ対象の文字列
	 * @return エスケープした文字列
	 * @since v1.0.1
	 */
	private static String escape(String str) {
		return HatenaUtil.escapeHatenaNotation(str);
	}

	/**
	 * URLを短縮する。※未実装
	 * 
	 * @param str 短縮対象のURL
	 * @return 短縮後のURL
	 */
	private static String shortenURL(String str) {
		return str;
	}
}
