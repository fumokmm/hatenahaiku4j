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
	 * �R���X�g���N�^�ł��B<br/>
	 * ���O�C�����[�U���f�t�H���g�N���C�A���g��("HatenaHaiku4J")�Ő������܂��B
	 * 
	 * @param userId ���[�UID
	 * @param password �p�X���[�h
	 * @since v0.2.1
	 */
	public LoginUser(String userId, String password) {
		this(userId, password, Const.API_NAME);
	}
	
	/**
	 * �R���X�g���N�^�ł��B<br/>
	 * ���O�C�����[�U�𐶐����܂��B
	 * 
	 * @param userId ���[�UID
	 * @param password ���e�p�p�X���[�h
	 * @param source �N���C�A���g��
	 * @since v0.2.1
	 */
	public LoginUser(String userId, String password, String source) {
		this.userId = userId;
		this.password = password;
		this.source = source;
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
}
