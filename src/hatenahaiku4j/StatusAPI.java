package hatenahaiku4j;

import java.io.File;

/**
 * �X�e�[�^�X�Ɋւ���API
 * 
 * @since v0.2.0
 * @author fumokmm
 */
public class StatusAPI extends EntityAPI {
	/** �X�e�[�^�X��� */
	private Status status;

	/**
	 * �R���X�g���N�^�B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @since v0.2.0
	 */
	StatusAPI() {
		super();
	}

	/**
	 * ���������܂��B
	 * 
	 * @param status �X�e�[�^�X���
	 * @param apiAuth �͂Ăȃn�C�NAPI�i�F�؂���j
	 * @since v0.2.0
	 */
	void init(Status status, HatenaHaikuAPI apiAuth) {
		this.status = status;
		this.apiAuth = apiAuth;
		this.apiLight = apiAuth;
	}

	/**
	 * ���������܂��B
	 * 
	 * @param status �X�e�[�^�X���
	 * @param apiLight �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j
	 * @since v0.2.0
	 */
	void init(Status status, HatenaHaikuAPILight apiLight) {
		this.status = status;
		this.apiLight = apiLight;
	}
	
	/**
	 * ���̃X�e�[�^�X��ԐM���ԂɎ擾���Ȃ����܂��B
	 * 
	 * @see HatenaHaikuAPILight#getStatus(String)
	 * @return �ԐM���Ԃ��擾���Ȃ����ꂽ���̃X�e�[�^�X
	 * @since v0.2.0
	 */
	Status refreshReplies() throws HatenaHaikuException {
		Status newStatus = apiLight.getStatus(status.getStatusId());
		status.setReplies(newStatus.getReplies());
		status.setShadow(false);
		return this.status;
	}
	
	// ------------------�ȉ��A�F�؂��s�v��API

	/**
	 * ���̃X�e�[�^�X�̃��[�U�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/show/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getUser(String)
	 * @return �w�肵�����[�U���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public User getUser() throws HatenaHaikuException {
		return apiLight.getUser(status.getUserId());
	}
	
	/**
	 * ���̃X�e�[�^�X�̃L�[���[�h�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/show/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeyword(String)
	 * @return �w�肵���L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword getKeyword() throws HatenaHaikuException {
		return apiLight.getKeyword(status.getKeyword());
	}
	
	// ------------------�ȉ��A�F�؂��K�v��API
	
	/**
	 * ���̃G���g���ɐV�����ԐM�G���g���𓊍e���܂��B
	 * 
	 * @see HatenaHaikuAPI#reply(String, String)
	 * @param text ���e���e
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Status reply(String text) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.reply(status.getStatusId(), text);
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃G���g���ɐV�����ԐM�G���g���𓊍e���܂��B�摜�t���ŃG���g�����܂��B
	 * 
	 * @see HatenaHaikuAPI#reply(String, String, File)
	 * @param text ���e���e
	 * @param file �摜�t�@�C��
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Status reply(String text, File file) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.reply(status.getStatusId(), text, file);
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}
	
	/**
	 * ���̃G���g�����폜���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/destroy/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#deleteEntry(String)
	 * @return ���̍폜�����X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public Status delete() throws HatenaHaikuException {
		if (isAuth()) {
			Status deleted = apiAuth.deleteEntry(status.getStatusId());
			status.overwrite(deleted);
			return status;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}
	
	/**
	 * ���̃G���g���ɃX�^�[����ǉ����܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/create/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#addStar(String)
	 * @return �X�^�[����ǉ��������ʂ̂��̃X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public Status addStar() throws HatenaHaikuException {
		if (isAuth()) {
			Status starAdded = apiAuth.addStar(status.getStatusId());
			status.overwrite(starAdded);
			return status;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * �w�肵���G���g���̃X�^�[������炵�܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#deleteStar(String)
	 * @return �X�^�[������炵�����ʂ̂��̃X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public Status deleteStar() throws HatenaHaikuException {
		if (isAuth()) {
			Status starDeleted = apiAuth.deleteStar(status.getStatusId());
			status.overwrite(starDeleted);
			return status;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

}
