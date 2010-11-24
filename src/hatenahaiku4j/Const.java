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
	public static final String VERSION = "v1.1.4";

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

	/** URL: はてなハイクベースURL */
	public static final String BASE_URL = "http://h.hatena.ne.jp/";
	/** URL: はてなハイクIDベースURL */
	public static final String ID_BASE_URL = BASE_URL + ID + SLASH;
	/** URL: はてなハイクキーワードベースURL */
	public static final String KEYWORD_BASE_URL = BASE_URL + KEYWORD + SLASH;

	/** URL: はてなハイクモバイルベースURL */
	public static final String MOBILE_BASE_URL = BASE_URL + MOBILE + SLASH;
	/** URL: はてなハイクモバイルキーワードベースURL */
	public static final String MOBILE_ID_BASE_URL = MOBILE_BASE_URL + ID + SLASH;
	/** URL: はてなハイクモバイルキーワードベースURL */
	public static final String MOBILE_KEYWORD_BASE_URL = MOBILE_BASE_URL + KEYWORD + SLASH;

	/** URL: はてなプロフィールベースURL */
	public static final String HATENA_PROFILE_BASE_URL = "http://www.hatena.ne.jp/";

	/** はてなIDプレフィックス */
	public static final String ID_COLON = ID + COLON;

	/** エンコーディング：UTF-8 */
	public static final String UTF8 = "UTF-8";
	/** 拡張子:XML (.xml) */
	public static final String EXT_XML = ".xml";
}
