package hatenahaiku4j;

import hatenahaiku4j.util.HttpUtil.PostStream;

import java.io.File;
import java.io.IOException;

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
	/** 画像ファイルURL */
	private String imageUrl;
	/** 画像データ */
	private byte[] imageData;
	/** 画像データ拡張子 */
	private ImageExt imageDataExt;
	
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

	/**
	 * 画像ファイルURL
	 * 
	 * @return 画像ファイルURL
	 * @since v1.0.0
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/**
	 * 画像ファイルURLを設定します。
	 * 
	 * @param imageUrl 画像ファイルURL
	 * @since v1.0.0
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * 画像データ
	 * 
	 * @return 画像ファイルURL
	 * @since v1.0.0
	 */
	public byte[] getImageData() {
		return imageData;
	}
	
	/**
	 * 画像データを設定します。
	 * 
	 * @param imageData 画像データ
	 * @since v1.0.0
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	/**
	 * 画像データ拡張子
	 * 
	 * @return 画像データ拡張子
	 * @since v1.0.0
	 */
	public ImageExt getImageDataExt() {
		return imageDataExt;
	}
	
	/**
	 * 画像データ拡張子を設定します。
	 * 
	 * @param imageDataExt 画像データ拡張子
	 * @since v1.0.0
	 */
	public void setImageDataExt(ImageExt imageDataExt) {
		this.imageDataExt = imageDataExt;
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
	 * ポストするパラメータを設定します。
	 * 
	 * @param loginUser ログインユーザ情報
	 * @param ps ポスト処理補助クラス
	 * @throws IOException 
	 * @since v1.0.0
	 */
	public void addParameter(LoginUser loginUser, PostStream ps) throws IOException {
		// status
		ps.addProperty(PARAM_KEY_STATUS, text);

		// keyword
		if (keyword == null || keyword.equals("")) {
			ps.addProperty(PARAM_KEY_KEYWORD, loginUser.getUserIdNotation());
		} else {
			ps.addProperty(PARAM_KEY_KEYWORD, keyword);
		}
		
		// source
		ps.addProperty(PARAM_KEY_SOURCE, loginUser.getSource());
		
		// in_reply_to_status_id
		if (inReplyToStatusId != null && !inReplyToStatusId.equals("")) {
			ps.addProperty(PARAM_KEY_IN_REPLY_TO_STATUS_ID, inReplyToStatusId);
		}
		
		// file
		if (file != null) {
			ImageMime mime = ImageMime.getImageMime(file.getName());
			if (mime != null) {
				ps.addFile(file, PARAM_KEY_FILE, mime);
			}
		}
		
		// imageUrl
		if (imageUrl != null) {
			ImageMime mime = ImageMime.getImageMime(imageUrl);
			if (mime != null) {
				ps.addImageUrl(imageUrl, PARAM_KEY_FILE, mime);
			}
		}
		
		// imageData
		if (imageData != null && imageDataExt != null) {
			ImageMime mime = ImageMime.getImageMime(imageDataExt);
			if (mime != null) {
				ps.addImageData(imageData, PARAM_KEY_FILE, mime);
			}
		}
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
		System.out.println("[text: " + text + "]");
		System.out.println("[file: " + (file == null ? "null" : file.getName()) + "]");
		System.out.println("[imageUrl: " + imageUrl + "]");
		System.out.println("[imageDataExt: " + imageDataExt + "]");
	}
}
