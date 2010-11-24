package hatenahaiku4j;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 更新用パラメータを表現するクラスです。
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class UpdateParameter {
	/** 投稿内容 */
	private String text;
	/** 返信元ステータスID */
	private String inReplyToStatusId;
	/** 投稿先キーワード */
	private String keyword;
	/** 画像ファイル */
	private File file;
	
	/**
	 * コンストラクタです。
	 * 
	 * @since v0.0.1
	 */
	UpdateParameter() {}
	
	/**
	 * 投稿内容を返却します。
	 * 
	 * @return 投稿内容
	 * @since v0.0.1
	 */
	public String getText() {
		return text;
	}

	/**
	 * 投稿内容を設定します。
	 * 
	 * @param text 投稿内容
	 * @since v0.0.1
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 返信元ステータスIDを取得します。
	 * 
	 * @return 返信元ステータスID
	 * @since v0.0.1
	 */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/**
	 * 返信元ステータスIDを設定します。
	 * 
	 * @param inReplyToStatusId 返信元ステータスID
	 * @since v0.0.1
	 */
	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	/**
	 * 投稿先キーワードを取得します。
	 * 
	 * @return 投稿先キーワード
	 * @since v0.0.1
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 投稿先キーワードを設定します。
	 * 
	 * @param keyword 投稿先キーワード
	 * @since v0.0.1
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * 画像ファイル
	 * 
	 * @return 画像ファイル
	 * @since v0.0.1
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * 画像ファイルを設定します。
	 * 
	 * @param file 画像ファイル
	 * @since v0.0.1
	 */
	public void setFile(File file) {
		this.file = file;
	}

	//-----------------------------------
	/** status */
	private static final String PARAM_KEY_STATUS = "status";
	/** in_reply_to_status_id */
	private static final String PARAM_KEY_IN_REPLY_TO_STATUS_ID = "in_reply_to_status_id";
	/** keyword */
	private static final String PARAM_KEY_KEYWORD = "keyword";
	/** file */
	private static final String PARAM_KEY_FILE = "file";
	/** source */
	private static final String PARAM_KEY_SOURCE = "source";

	/**
	 * パラメータに変換します。
	 * 
	 * @return パラメータ文字列
	 * @throws UnsupportedEncodingException
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public String toParameter(LoginUser loginUser) throws UnsupportedEncodingException, HatenaHaikuException {
		Map<String, String> map = new HashMap<String, String>();

		// status
		map.put(PARAM_KEY_STATUS, text);
		// keyword
		if (keyword == null || keyword.equals("")) {
			map.put(PARAM_KEY_KEYWORD, loginUser.getUserIdNotation());
		} else {
			map.put(PARAM_KEY_KEYWORD, keyword);
		}
		// source
		map.put(PARAM_KEY_SOURCE, loginUser.getSource());
		// in_reply_to_status_id
		if (inReplyToStatusId != null && !inReplyToStatusId.equals("")) {
			map.put(PARAM_KEY_IN_REPLY_TO_STATUS_ID, inReplyToStatusId);
		}
		// file TODO 実装
		if (file != null) {
			throw new APINotSupportedException();
			//map.put(PARAM_KEY_FILE, "");
		}

		StringBuilder buffer = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (buffer.length() > 0) buffer.append(Const.AMP);
			buffer.append(entry.getKey())
			.append(Const.EQUAL)
			.append(URLEncoder.encode(entry.getValue(), Const.UTF8));
		}
		return buffer.toString();
	}

	/**
	 * ポスト内容を表示します。
	 * 
	 * @since v0.0.1
	 */
	public void outputPostInfo(LoginUser loginUser) {
		// ポスト内容の表示
		System.out.println("[keyword: " + keyword + "]");
		System.out.println("[inReplyToStatusId: " + inReplyToStatusId + "]");
		System.out.println("[source: " + loginUser.getSource() + "]");
		System.out.println("[text: " + text+ "]");
	}
}
