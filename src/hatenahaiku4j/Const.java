package hatenahaiku4j;

/**
 * 定数定義クラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Const {
	/** API名 */
	public static final String API_NAME = "HatenaHaiku4J";
	/** バージョン */
	public static final String VERSION = "v1.2.1";

	/** コロン */
	public static final String COLON = ":";
	/** セミコロン */
	public static final String SEMICOLON = ";";
	/** アンパサンド */
	public static final String AMP = "&";
	/** イコール */
	public static final String EQUAL = "=";
	/** / */
	public static final String SLASH = "/";

	/** id */
	public static final String ID = "id";
	/** keyword */
	public static final String KEYWORD = "keyword";
	/** following */
	public static final String FOLLOWING = "following";
	/** mobile */
	public static final String MOBILE = "mobile";
	/** api */
	public static final String API = "api";

	/** URL: はてなハイクベースURL */
	public static final String BASE_URL = "http://h.hatena.ne.jp/";
	/** URL: はてなハイクIDベースURL */
	public static final String ID_BASE_URL = BASE_URL + ID + SLASH;
	/** URL: はてなハイクキーワードベースURL */
	public static final String KEYWORD_BASE_URL = BASE_URL + KEYWORD + SLASH;

	/** URL: はてなハイクモバイルベースURL */
	public static final String MOBILE_BASE_URL = BASE_URL + MOBILE + SLASH;
	/** URL: はてなハイクモバイルIDベースURL */
	public static final String MOBILE_ID_BASE_URL = MOBILE_BASE_URL + ID + SLASH;
	/** URL: はてなハイクモバイルキーワードベースURL */
	public static final String MOBILE_KEYWORD_BASE_URL = MOBILE_BASE_URL + KEYWORD + SLASH;

	/** URL: はてなプロフィールベースURL */
	public static final String HATENA_PROFILE_BASE_URL = "http://www.hatena.ne.jp/";
	/** URL: はてなstベースURL */
	public static final String HATENA_ST_BASE_URL = "http://www.st-hatena.com/";

	/** URL: はてなハイクAPIベースURL */
	public static final String API_BASE_URL = BASE_URL + API + SLASH;
	
	/** はてなIDプレフィックス */
	public static final String ID_COLON = ID + COLON;
	/** キーワードプレフィックス */
	public static final String KEYWORD_COLON = KEYWORD + COLON;

	/** エンコーディング：UTF-8 */
	public static final String UTF8 = "UTF-8";
	/** 拡張子:XML (.xml) */
	public static final String EXT_XML = ".xml";

	/** XMLヘッダ */
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"" + UTF8 + "\" ?>";
	
	/** URL: http://www.youtube.com/v/ */
	public static final String URL_YOUTUBE_V = "http://www.youtube.com/v/";
	
	/** URL: http://www.youtube.com/watch?v= */
	public static final String URL_YOUTUBE_LINK = "http://www.youtube.com/watch?v=";
	
	/** URL: http://www.nicovideo.jp/thumb_watch/ */
	public static final String URL_NICO2_THUMB_WATCH = "http://www.nicovideo.jp/thumb_watch/";
	
	/** URL: http://www.nicovideo.jp/watch/ */
	public static final String URL_NICO2_LINK = "http://www.nicovideo.jp/watch/";
	
	/** URL: http://g.hatena.ne.jp/images/podcasting.gif */
	public static final String URL_IMG_PODCASTING_GIF = "http://g.hatena.ne.jp/images/podcasting.gif";
}
