package hatenahaiku4j;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * �L�[���[�h�Ɋւ���API
 * 
 * @since v0.2.0
 * @author fumokmm
 */
public class KeywordAPI extends EntityAPI {
	/** �L�[���[�h��� */
	private Keyword keyword;

	/**
	 * �R���X�g���N�^�B�i�p�b�P�[�W�v���C�x�[�g�j
	 * @since v0.2.0
	 */
	KeywordAPI() {
		super();
	}

	/**
	 * ���������܂��B
	 * 
	 * @param keyword �L�[���[�h���
	 * @param apiAuth �͂Ăȃn�C�NAPI�i�F�؂���j
	 * @since v0.2.0
	 */
	void init(Keyword keyword, HatenaHaikuAPI apiAuth) {
		this.keyword = keyword;
		this.apiAuth = apiAuth;
		this.apiLight = apiAuth;
	}

	/**
	 * ���������܂��B
	 * 
	 * @param keyword �L�[���[�h���
	 * @param apiLight �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j
	 * @since v0.2.0
	 */
	void init(Keyword keyword, HatenaHaikuAPILight apiLight) {
		this.keyword = keyword;
		this.apiLight = apiLight;
	}
	
	// ------------------�ȉ��A�F�؂��s�v��API

	/**
	 * ���̃L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @return ���̃L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline() throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle());
	}

	/**
	 * ���̃L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return ���̃L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle(), page);
	}

	/**
	 * ���̃L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return ���̃L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle(), page, count);
	}

	/**
	 * ���̃L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return ���̃L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle(), page, count, since);
	}
	
	/**
	 * ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @return ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline() throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle());
	}

	/**
	 * ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle(), page);
	}

	/**
	 * ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle(), page, count);
	}

	/**
	 * ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return ���̃L�[���[�h�̐l�C�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle(), page, count, since);
	}
	
	/**
	 * ���̃L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String)
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline() throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle());
	}

	/**
	 * ���̃L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline(int page) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle(), page);
	}

	/**
	 * ���̃L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle(), page, count);
	}

	/**
	 * ���̃L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle(), page, count, since);
	}

	// ------------------�ȉ��A�F�؂��K�v��API
	
	/**
	 * ���̃L�[���[�h�ɐV�����G���g���𓊍e���܂��B
	 * 
	 * @see HatenaHaikuAPI#entry(String, String)
	 * @param text ���e���e
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Status entry(String text) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text);
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃L�[���[�h�ɐV�����G���g���𓊍e���܂��B<br/>
	 * �摜�t���ŃG���g�����܂��B
	 * 
	 * @see HatenaHaikuAPI#entry(String, String, File)
	 * @param text ���e���e
	 * @param file �摜�t�@�C��
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String text, File file) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text, file);
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}
	
	/**
	 * ���̃L�[���[�h�ɐV�����G���g���𓊍e���܂��B<br/>
	 * URL��ɂ���摜�t���ŃG���g�����܂��B
	 * 
	 * @see HatenaHaikuAPI#entry(String, String, String)
	 * @param text ���e���e
	 * @param imageUrl �摜��URL
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String text, String imageUrl) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text, imageUrl);
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃L�[���[�h�ɐV�����G���g���𓊍e���܂��B<br/>
	 * �摜�̃o�C�i���f�[�^�Ƃ��̊g���q���w�肵�A�摜�t���ŃG���g�����܂��B
	 * 
	 * @see HatenaHaikuAPI#entry(String, String, byte[], ImageExt)
	 * @param text ���e���e
	 * @param imageData �摜�̃o�C�i���f�[�^
	 * @param imageDataExt �摜�̊g���q
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String text, byte[] imageData, ImageExt imageDataExt) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text, imageData, imageDataExt);
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃L�[���[�h���t�H���[���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/create/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#followKeyword(String)
	 * @return �t�H���[�����L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword follow() throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.followKeyword(keyword.getTitle());
			keyword.overwrite(newKeyword);
			return keyword;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃L�[���[�h�̃t�H���[����߂܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unfollowKeyword(String)
	 * @return �t�H���[����߂��L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword unfollow() throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.unfollowKeyword(keyword.getTitle());
			keyword.overwrite(newKeyword);
			return keyword;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}
	
	/**
	 * ���̃L�[���[�h�Ɏw��̃L�[���[�h���֘A�t���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/create.xml</i>
	 * 
	 * @see HatenaHaikuAPI#relateKeyword(String, String)
	 * @param keyword ���̃L�[���[�h�Ɋ֘A�t����L�[���[�h
	 * @return �֘A�L�[���[�h��ݒ��̂��̃L�[���[�h
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword relate(String keyword) throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.relateKeyword(this.keyword.getTitle(), keyword);
			this.keyword.overwrite(newKeyword);
			return this.keyword;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃L�[���[�h�Ɏw��̃L�[���[�h���֘A�t���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/create.xml</i>
	 * 
	 * @see HatenaHaikuAPI#relateKeyword(String, String)
	 * @param keyword ���̃L�[���[�h�Ɋ֘A�t����L�[���[�h
	 * @return �֘A�L�[���[�h��ݒ��̂��̃L�[���[�h
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword relate(Keyword keyword) throws HatenaHaikuException {
		return relate(keyword.getTitle());
	}

	/**
	 * ���̃L�[���[�h����w��̃L�[���[�h�̊֘A�t�����������܂��B<br/>
	 * �֘A�t���������͎������ݒ肵�����̂Ɍ����܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/destroy.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unrelateKeyword(String, String)
	 * @param keyword ���̃L�[���[�h����֘A�t������������L�[���[�h
	 * @return �֘A�L�[���[�h��������̂��̃L�[���[�h
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword unrelate(String keyword) throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.unrelateKeyword(this.keyword.getTitle(), keyword);
			this.keyword.overwrite(newKeyword);
			return this.keyword;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃L�[���[�h����w��̃L�[���[�h�̊֘A�t�����������܂��B<br/>
	 * �֘A�t���������͎������ݒ肵�����̂Ɍ����܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/destroy.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unrelateKeyword(String, String)
	 * @param keyword ���̃L�[���[�h����֘A�t������������L�[���[�h
	 * @return �֘A�L�[���[�h��������̂��̃L�[���[�h
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword unrelate(Keyword keyword) throws HatenaHaikuException {
		return unrelate(keyword.getTitle());
	}
}
