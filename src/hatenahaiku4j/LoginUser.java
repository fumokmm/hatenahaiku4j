package hatenahaiku4j;

import hatenahaiku4j.util.Base64Util;

/**
 * ログインユーザ情報
 * 
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
	 * コンストラクタ。
	 * 
	 * @param userId ユーザID
	 * @param password 投稿用パスワード
	 * @param source クライアント名
	 */
	private LoginUser(String userId, String password, String source) {
		this.userId = userId;
		this.password = password;
		this.source = source;
	}

	/**
	 * ログインユーザを生成します。
	 * 
	 * @param userId ユーザID
	 * @param password パスワード
	 */
	public static LoginUser create(String userId, String password) {
		if (userId == null || userId.equals("")) return NULL;
		if (password == null || password.equals("")) return NULL;
		return new LoginUser(userId, password, Const.API_NAME);
	}
	
	/**
	 * ログインユーザを生成します。
	 * 
	 * @param userId ユーザID
	 * @param password パスワード
	 * @param source クライアント名
	 */
	public static LoginUser create(String userId, String password, String source) {
		if (userId == null || userId.equals("")) return NULL;
		if (password == null || password.equals("")) return NULL;
		if (source == null || source.equals("")) return NULL;
		return new LoginUser(userId, password, source);
	}

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDをはてなIDフォーマット（"id:xxx"）で取得します。
	 * 
	 * @return id:<ユーザID>
	 */
	public String getHatenaIdFormat() {
		return Const.ID_COLON + userId;
	}
	
	/**
	 * 投稿用パスワードを取得します。
	 * 
	 * @return 投稿用パスワード
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * クライアント名を取得します。
	 * 
	 * @return クライアント名
	 */
	public String getSource() {
		return source;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginUser other = (LoginUser) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginUser [userId=" + userId + ", client=" + source
				+ ", password=" + password + "]";
	}

	/** BASIC認証用 */
	public String toBasicAuthenticationString() {
		String encodedUserPass = Base64Util.encodeBase64(userId + ":" + password);
		return "Basic " + encodedUserPass;
	}

	/** ヌルユーザ */
	static LoginUser NULL = new LoginUser(null, null, null);
}
