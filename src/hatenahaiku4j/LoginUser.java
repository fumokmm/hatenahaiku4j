package hatenahaiku4j;

import hatenahaiku4j.util.Base64Util;

/**
 * ���O�C�����[�U���
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class LoginUser {
	/** ���[�UID */
	private String userId;
	/** ���e�p�p�X���[�h */
	private String password;
	/** �N���C�A���g�� */
	private String source;

	/**
	 * �R���X�g���N�^�B
	 * 
	 * @param userId ���[�UID
	 * @param password ���e�p�p�X���[�h
	 * @param source �N���C�A���g��
	 * @since v0.0.1
	 */
	private LoginUser(String userId, String password, String source) {
		this.userId = userId;
		this.password = password;
		this.source = source;
	}

	/**
	 * ���O�C�����[�U�𐶐����܂��B
	 * 
	 * @param userId ���[�UID
	 * @param password �p�X���[�h
	 * @since v0.0.1
	 */
	public static LoginUser create(String userId, String password) {
		if (userId == null || userId.equals("")) return NULL;
		if (password == null || password.equals("")) return NULL;
		return new LoginUser(userId, password, Const.API_NAME);
	}
	
	/**
	 * ���O�C�����[�U�𐶐����܂��B
	 * 
	 * @param userId ���[�UID
	 * @param password �p�X���[�h
	 * @param source �N���C�A���g��
	 * @since v0.0.1
	 */
	public static LoginUser create(String userId, String password, String source) {
		if (userId == null || userId.equals("")) return NULL;
		if (password == null || password.equals("")) return NULL;
		if (source == null || source.equals("")) return NULL;
		return new LoginUser(userId, password, source);
	}

	/**
	 * ���[�UID���擾���܂��B
	 * 
	 * @return ���[�UID
	 * @since v0.0.1
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * id�L�@�̃��[�UID���擾���܂��B (id:xxx)
	 * 
	 * @return ���[�Uid�L�@ (id:xxx)
	 * @since v0.2.0
	 */
	public String getUserIdNotation() {
		return Const.ID_COLON + userId;
	}
	
	/**
	 * ���e�p�p�X���[�h���擾���܂��B
	 * 
	 * @return ���e�p�p�X���[�h
	 * @since v0.0.1
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * �N���C�A���g�����擾���܂��B
	 * 
	 * @return �N���C�A���g��
	 * @since v0.0.1
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

	/**
	 * ���[�UID + ":" + �p�X���[�h�`���ɕϊ����܂��B
	 * 
	 * @return ���[�UID + ":" + �p�X���[�h
	 * @since v0.0.1
	 */
	String toUserPassword() {
		return userId + ":" + password;
	}
	
	/**
	 * BASIC�F�ؗp������ɕϊ����܂��B
	 * 
	 * @return BASIC�F�ؗp������
	 * @since v0.0.1
	 */
	public String toBasicAuthenticationString() {
		String encodedUserPass = Base64Util.encodeBase64(toUserPassword());
		return "Basic " + encodedUserPass;
	}

	/** NULL���[�U */
	static LoginUser NULL = new LoginUser("", "", "");
	
	/**
	 * NULL���[�U���ǂ����擾���܂��B
	 * 
	 * @return NULL���[�U�̏ꍇtrue
	 * @since v0.0.1
	 */
	boolean isNull() {
		return this == NULL;
	}
}
