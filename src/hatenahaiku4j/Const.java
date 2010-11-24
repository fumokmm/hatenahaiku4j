package hatenahaiku4j;

/**
 * �萔��`�N���X
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Const {
	/** API�� */
	public static final String API_NAME = "HatenaHaiku4J";

	/** �R���� */
	public static final String COLON = ":";
	/** �Z�~�R���� */
	public static final String SEMICOLON = ";";
	/** �A���p�T���h */
	public static final String AMP = "&";
	/** �C�R�[�� */
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

	/** URL: �͂Ăȃn�C�N�x�[�XURL */
	public static final String BASE_URL = "http://h.hatena.ne.jp/";
	/** URL: �͂Ăȃn�C�NID�x�[�XURL */
	public static final String ID_BASE_URL = BASE_URL + ID + SLASH;
	/** URL: �͂Ăȃn�C�N�L�[���[�h�x�[�XURL */
	public static final String KEYWORD_BASE_URL = BASE_URL + KEYWORD + SLASH;

	/** URL: �͂Ăȃn�C�N���o�C���x�[�XURL */
	public static final String MOBILE_BASE_URL = BASE_URL + MOBILE + SLASH;
	/** URL: �͂Ăȃn�C�N���o�C���L�[���[�h�x�[�XURL */
	public static final String MOBILE_ID_BASE_URL = MOBILE_BASE_URL + ID + SLASH;
	/** URL: �͂Ăȃn�C�N���o�C���L�[���[�h�x�[�XURL */
	public static final String MOBILE_KEYWORD_BASE_URL = MOBILE_BASE_URL + KEYWORD + SLASH;

	/** �͂Ă�ID�v���t�B�b�N�X */
	public static final String ID_COLON = ID + COLON;

	/** �G���R�[�f�B���O�FUTF-8 */
	public static final String UTF8 = "UTF-8";
	/** �g���q:XML (.xml) */
	public static final String EXT_XML = ".xml";
}
