package hatenahaiku4j;

import hatenahaiku4j.util.HttpUtil.PostStream;

import java.io.File;
import java.io.IOException;

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
	/** �摜�t�@�C��URL */
	private String imageUrl;
	/** �摜�f�[�^ */
	private byte[] imageData;
	/** �摜�f�[�^�g���q */
	private ImageExt imageDataExt;
	
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

	/**
	 * �摜�t�@�C��URL
	 * 
	 * @return �摜�t�@�C��URL
	 * @since v1.0.0
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	
	/**
	 * �摜�t�@�C��URL��ݒ肵�܂��B
	 * 
	 * @param imageUrl �摜�t�@�C��URL
	 * @since v1.0.0
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * �摜�f�[�^
	 * 
	 * @return �摜�t�@�C��URL
	 * @since v1.0.0
	 */
	public byte[] getImageData() {
		return imageData;
	}
	
	/**
	 * �摜�f�[�^��ݒ肵�܂��B
	 * 
	 * @param imageData �摜�f�[�^
	 * @since v1.0.0
	 */
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	/**
	 * �摜�f�[�^�g���q
	 * 
	 * @return �摜�f�[�^�g���q
	 * @since v1.0.0
	 */
	public ImageExt getImageDataExt() {
		return imageDataExt;
	}
	
	/**
	 * �摜�f�[�^�g���q��ݒ肵�܂��B
	 * 
	 * @param imageDataExt �摜�f�[�^�g���q
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
	 * �|�X�g����p�����[�^��ݒ肵�܂��B
	 * 
	 * @param loginUser ���O�C�����[�U���
	 * @param ps �|�X�g�����⏕�N���X
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
	 * �|�X�g���e��\�����܂��B
	 * 
	 * @since v0.0.1
	 */
	public void outputPostInfo(LoginUser loginUser) {
		// �|�X�g���e�̕\��
		System.out.println("[keyword: " + keyword + "]");
		System.out.println("[inReplyToStatusId: " + inReplyToStatusId + "]");
		System.out.println("[source: " + loginUser.getSource() + "]");
		System.out.println("[text: " + text + "]");
		System.out.println("[file: " + (file == null ? "null" : file.getName()) + "]");
		System.out.println("[imageUrl: " + imageUrl + "]");
		System.out.println("[imageDataExt: " + imageDataExt + "]");
	}
}
