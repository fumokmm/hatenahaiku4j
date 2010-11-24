package hatenahaiku4j;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * �X�V�p�p�����[�^��\������N���X�ł��B
 * 
 * @author fumokmm
 */
public class UpdateParameter {
	/** ���e���e */
	private String text;
	/** �ԐM��X�e�[�^�XID */
	private String inReplyToStatusId;
	/** ���e��L�[���[�h */
	private String keyword;
	/** �摜�t�@�C�� */
	private File file;
	
	/** �R���X�g���N�^ */
	UpdateParameter() {}
	
	/** @return ���e���e */
	public String getText() {
		return text;
	}

	/**
	 * ���e���e��ݒ肵�܂��B
	 * @param text ���e���e
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/** @return �ԐM��X�e�[�^�XID */
	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	/**
	 * �ԐM��X�e�[�^�XID��ݒ肵�܂��B
	 * @param inReplyToStatusId �ԐM��X�e�[�^�XID
	 */
	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	/** @return ���e��L�[���[�h */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * ���e��L�[���[�h��ݒ肵�܂��B
	 * @param keyword ���e��L�[���[�h
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/** @return �摜�t�@�C�� */
	public File getFile() {
		return file;
	}
	
	/**
	 * �摜�t�@�C����ݒ肵�܂��B
	 * @param file �摜�t�@�C��
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
	 * @throws UnsupportedEncodingException, APINotSupportedException 
	 */
	public String toParameter(LoginUser loginUser) throws HatenaHaikuException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// status
			map.put(PARAM_KEY_STATUS, text);
			// keyword
			if (keyword == null || keyword.equals("")) {
				map.put(PARAM_KEY_KEYWORD, loginUser.getHatenaIdFormat());
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

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("�G���R�[�f�B���O���s��", e);
		}
	}

	public void outputPostInfo(LoginUser loginUser) {
		// �|�X�g���e�̕\��
		System.out.println("[keyword: " + keyword + "]");
		System.out.println("[inReplyToStatusId: " + inReplyToStatusId + "]");
		System.out.println("[source: " + loginUser.getSource() + "]");
		System.out.println("[text: " + text+ "]");
	}
}
