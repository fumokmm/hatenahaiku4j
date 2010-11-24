package hatenahaiku4j;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * �X�V�p�p�����[�^��\������N���X�ł��B
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class UpdateParameter {
	/** ���e���e */
	private String text;
	/** �ԐM���X�e�[�^�XID */
	private String inReplyToStatusId;
	/** ���e��L�[���[�h */
	private String keyword;
	/** �摜�t�@�C�� */
	private File file;
	
	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @since v0.0.1
	 */
	UpdateParameter() {}
	
	/**
	 * ���e���e��ԋp���܂��B
	 * 
	 * @return ���e���e
	 * @since v0.0.1
	 */
	public String getText() {
		return text;
	}

	/**
	 * ���e���e��ݒ肵�܂��B
	 * 
	 * @param text ���e���e
	 * @since v0.0.1
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * �ԐM���X�e�[�^�XID���擾���܂��B
	 * 
	 * @return �ԐM���X�e�[�^�XID
	 * @since v0.0.1
	 */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/**
	 * �ԐM���X�e�[�^�XID��ݒ肵�܂��B
	 * 
	 * @param inReplyToStatusId �ԐM���X�e�[�^�XID
	 * @since v0.0.1
	 */
	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	/**
	 * ���e��L�[���[�h���擾���܂��B
	 * 
	 * @return ���e��L�[���[�h
	 * @since v0.0.1
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * ���e��L�[���[�h��ݒ肵�܂��B
	 * 
	 * @param keyword ���e��L�[���[�h
	 * @since v0.0.1
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * �摜�t�@�C��
	 * 
	 * @return �摜�t�@�C��
	 * @since v0.0.1
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * �摜�t�@�C����ݒ肵�܂��B
	 * 
	 * @param file �摜�t�@�C��
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
	 * �p�����[�^�ɕϊ����܂��B
	 * 
	 * @return �p�����[�^������
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
		// file TODO ����
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
	 * �|�X�g���e��\�����܂��B
	 * 
	 * @since v0.0.1
	 */
	public void outputPostInfo(LoginUser loginUser) {
		// �|�X�g���e�̕\��
		System.out.println("[keyword: " + keyword + "]");
		System.out.println("[inReplyToStatusId: " + inReplyToStatusId + "]");
		System.out.println("[source: " + loginUser.getSource() + "]");
		System.out.println("[text: " + text+ "]");
	}
}
