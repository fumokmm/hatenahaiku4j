package hatenahaiku4j;

import hatenahaiku4j.util.Base64Util;

/**
 * ログインユーザ情報
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class LoginUser {
	/** ユーザID */
	private String userId;
	/** 投稿用パスワード */
	private String password;
	/** クライアント名 */
	private String source;

	/**
	 * コンストラクタです。<br/>
	 * ログインユーザをデフォルトクライアント名("HatenaHaiku4J")で生成します。
	 * 
	 * @param userId ユーザID
	 * @param password パスワード
	 * @since v0.2.1
	 */
	public LoginUser(String userId, String password) {
		this(userId, password, Const.API_NAME);
	}
	
	/**
	 * コンストラクタです。<br/>
	 * ログインユーザを生成します。
	 * 
	 * @param userId ユーザID
	 * @param password 投稿用パスワード
	 * @param source クライアント名
	 * @since v0.2.1
	 */
	public LoginUser(String userId, String password, String source) {
		this.userId = userId;
		this.password = password;
		this.source = source;
	}

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 * @since v0.0.1
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * id記法のユーザIDを取得します。 (id:xxx)
	 * 
	 * @return ユーザid記法 (id:xxx)
	 * @since v0.2.0
	 */
	public String getUserIdNotation() {
		return Const.ID_COLON + userId;
	}
	
	/**
	 * 投稿用パスワードを取得します。
	 * 
	 * @return 投稿用パスワード
	 * @since v0.0.1
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * クライアント名を取得します。
	 * 
	 * @return クライアント名
	 * @since v0.0.1
	 */
	public String getSource() {
		return source;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginUser [userId=" + userId + ", client=" + source
				+ ", password=" + password + "]";
	}

	/**
	 * ユーザID + ":" + パスワード形式に変換します。
	 * 
	 * @return ユーザID + ":" + パスワード
	 * @since v0.0.1
	 */
	String toUserPassword() {
		return userId + ":" + password;
	}
	
	/**
	 * BASIC認証用文字列に変換します。
	 * 
	 * @return BASIC認証用文字列
	 * @since v0.0.1
	 */
	public String toBasicAuthenticationString() {
		String encodedUserPass = Base64Util.encodeBase64(toUserPassword());
		return "Basic " + encodedUserPass;
	}
}
