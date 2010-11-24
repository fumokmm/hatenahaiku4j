package hatenahaiku4j;

import hatenahaiku4j.util.HttpUtil;
import hatenahaiku4j.util.StringUtil;
import hatenahaiku4j.util.XmlUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * �͂Ăȃn�C�NAPI���b�s���O�N���X�i�F�؂���j
 * 
 * @see <a href="http://h.hatena.ne.jp/api">�͂Ăȃn�C�NAPI</a>
 * @since v0.0.1
 * @author fumokmm
 */
public class HatenaHaikuAPI extends HatenaHaikuAPILight {
	/** URL: �t�����h�^�C�����C��(XML) */
	protected static final String URL_FRIENDS_TIMELINE_XML			= "http://h.hatena.ne.jp/api/statuses/friends_timeline.xml";
	/** URL: ���[�U�^�C�����C��(XML) */
	protected static final String URL_USER_TIMELINE_XML				= "http://h.hatena.ne.jp/api/statuses/user_timeline.xml";
	/** URL: ���e(XML) */
	protected static final String URL_UPDATE_STATUS_XML				= "http://h.hatena.ne.jp/api/statuses/update.xml";
	/** URL: �G���g�����폜 */
	protected static final String URL_DELETE_STATUS					= "http://h.hatena.ne.jp/api/statuses/destroy/";
	/** URL: �G���g���ɃX�^�[����ǉ� */
	protected static final String URL_ADD_STAR						= "http://h.hatena.ne.jp/api/favorites/create/";
	/** URL: �G���g���̃X�^�[������炷 */
	protected static final String URL_DELETE_STAR					= "http://h.hatena.ne.jp/api/favorites/destroy/";
	/** URL: ���[�U���t�H���[���Ă��郆�[�U�̃��X�g(XML) */
	protected static final String URL_FOLLOWING_LIST_XML			= "http://h.hatena.ne.jp/api/statuses/friends.xml";
	/** URL: ���[�U���t�H���[���Ă��郆�[�U�̃��X�g(XML) */
	protected static final String URL_FOLLOWERS_LIST_XML			= "http://h.hatena.ne.jp/api/statuses/followers.xml";
	/** URL: ���[�U���t�H���[���� */
	protected static final String URL_FOLLOW_USER					= "http://h.hatena.ne.jp/api/friendships/create/";
	/** URL: ���[�U�̃t�H���[����߂� */
	protected static final String URL_UNFOLLOW_USER					= "http://h.hatena.ne.jp/api/friendships/destroy/";
	/** URL: ���[�U���t�H���[���Ă���L�[���[�h�̃��X�g(XML) */
	protected static final String URL_FOLLOWING_KEYWORD_LIST_XML	= "http://h.hatena.ne.jp/api/statuses/keywords.xml";
	/** URL: �L�[���[�h���t�H���[���� */
	protected static final String URL_FOLLOW_KEYWORD				= "http://h.hatena.ne.jp/api/keywords/create/";
	/** URL: �L�[���[�h�̃t�H���[����߂� */
	protected static final String URL_UNFOLLOW_KEYWORD				= "http://h.hatena.ne.jp/api/keywords/destroy/";
	/** URL: �֘A�L�[���[�h��ݒ�(XML) */
	protected static final String URL_RELATE_KEYWORD_XML			= "http://h.hatena.ne.jp/api/keywords/relation/create.xml";
	/** URL: �֘A�L�[���[�h������(XML) */
	protected static final String URL_UNRELATE_KEYWORD_XML			= "http://h.hatena.ne.jp/api/keywords/relation/destroy.xml";

	/** ���O�C�����[�U */
	private LoginUser loginUser;
	
	/**
	 * �R���X�g���N�^�B
	 * 
	 * @param loginUser ���O�C�����[�U
	 * @since v0.0.1
	 */
	public HatenaHaikuAPI(LoginUser loginUser) {
		super();
		this.loginUser = loginUser;
	}

	//-------------------------------------------------------------
	// statuses/timeline
	//-------------------------------------------------------------

	/**
	 * �F�؂������[�U�̃t�����h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @return �F�؂������[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline() throws HatenaHaikuException {
		return getFriendsTimeline(0, 0, null);
	}

	/**
	 * �F�؂������[�U�̃t�����h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �F�؂������[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(int page) throws HatenaHaikuException {
		return getFriendsTimeline(page, 0, null);
	}

	/**
	 * �F�؂������[�U�̃t�����h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �F�؂������[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(int page, int count) throws HatenaHaikuException {
		return getFriendsTimeline(page, count, null);
	}

	/**
	 * �F�؂������[�U�̃t�����h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �F�؂������[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(loginUser, URL_FRIENDS_TIMELINE_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	/**
	 * �F�؂������[�U�̃��[�U�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @return �F�؂������[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline() throws HatenaHaikuException {
		return _getUserTimeline(0, 0, null, false);
	}

	/**
	 * �F�؂������[�U�̃��[�U�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �F�؂������[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(int page) throws HatenaHaikuException {
		return _getUserTimeline(page, 0, null, false);
	}

	/**
	 * �F�؂������[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �F�؂������[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(int page, int count) throws HatenaHaikuException {
		return _getUserTimeline(page, count, null, false);
	}

	/**
	 * �F�؂������[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �F�؂������[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return _getUserTimeline(page, count, since, false);
	}

	/**
	 * �F�؂������[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @return �F�؂������[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline() throws HatenaHaikuException {
		return _getUserTimeline(0, 0, null, true);
	}

	/**
	 * �F�؂������[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �F�؂������[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline(int page) throws HatenaHaikuException {
		return _getUserTimeline(page, 0, null, true);
	}

	/**
	 * �F�؂������[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �F�؂������[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline(int page, int count) throws HatenaHaikuException {
		return _getUserTimeline(page, count, null, true);
	}

	/**
	 * �F�؂������[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �F�؂������[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return _getUserTimeline(page, count, since, true);
	}
	
	/**
	 * �F�؂������[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @param isHot �l�C���擾�p���ǂ���
	 * @return �F�؂������[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	private List<Status> _getUserTimeline(int page, int count, Date since, boolean isHot) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter(isHot);
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(loginUser, URL_USER_TIMELINE_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	/**
	 * �F�؂������[�U��id�y�[�W�̃^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @return �F�؂������[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline() throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), 0, 0, null);
	}

	/**
	 * �F�؂������[�U��id�y�[�W�̃^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �F�؂������[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page) throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), page, 0, null);
	}

	/**
	 * �F�؂������[�U��id�y�[�W�̃^�C�����C�����擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �F�؂������[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count) throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), page, count, null);
	}

	/**
	 * �F�؂������[�U��id�y�[�W�̃^�C�����C�����擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �F�؂������[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), page, count, since);
	}
	
	//-------------------------------------------------------------
	// �X�e�[�^�X�֌W
	//-------------------------------------------------------------

	/**
	 * �V�����G���g���𓊍e���܂��B�i���O�C�����[�U�̃v���t�B�[���y�[�W�ɓ��e����܂��j
	 * 
	 * @param text ���e���e
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Status entry(String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setText(text);
		return _updateStatus(params);
	}

	/**
	 * �V�����G���g���𓊍e���܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @param text ���e���e
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Status entry(String keyword, String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setKeyword(keyword);
		params.setText(text);
		return _updateStatus(params);
	}

	/**
	 * �V�����G���g���𓊍e���܂��B<br/>
	 * �摜�t���ŃG���g�����܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @param text ���e���e
	 * @param file �摜�t�@�C��
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String keyword, String text, File file) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setKeyword(keyword);
		params.setText(text);
		params.setFile(file);
		return _updateStatus(params);
	}
	
	/**
	 * �V�����G���g���𓊍e���܂��B<br/>
	 * URL��ɂ���摜�t���ŃG���g�����܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @param text ���e���e
	 * @param imageUrl �摜��URL
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String keyword, String text, String imageUrl) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setKeyword(keyword);
		params.setText(text);
		params.setImageUrl(imageUrl);
		return _updateStatus(params);
	}

	/**
	 * �V�����G���g���𓊍e���܂��B<br/>
	 * �摜�̃o�C�i���f�[�^�Ƃ��̊g���q���w�肵�A�摜�t���ŃG���g�����܂��B
	 * 
	 * @param keyword �L�[���[�h
	 * @param text ���e���e
	 * @param imageData �摜�̃o�C�i���f�[�^
	 * @param imageDataExt �摜�̊g���q
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String keyword, String text, byte[] imageData, ImageExt imageDataExt) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setKeyword(keyword);
		params.setText(text);
		params.setImageData(imageData);
		params.setImageDataExt(imageDataExt);
		return _updateStatus(params);
	}

	/**
	 * �V�����ԐM�G���g���𓊍e���܂��B
	 * 
	 * @param inReplyToStatusId �ԐM���X�e�[�^�XID
	 * @param text ���e���e
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Status reply(String inReplyToStatusId, String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setInReplyToStatusId(inReplyToStatusId);
		params.setText(text);
		return _updateStatus(params);
	}

	/**
	 * �V�����ԐM�G���g���𓊍e���܂��B<br/>
	 * �摜�t���ŃG���g�����܂��B
	 * 
	 * @param inReplyToStatusId �ԐM���X�e�[�^�XID
	 * @param text ���e���e
	 * @param file �摜�t�@�C��
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status reply(String inReplyToStatusId, String text, File file) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setInReplyToStatusId(inReplyToStatusId);
		params.setText(text);
		params.setFile(file);
		return _updateStatus(params);
	}

	/**
	 * �V�����ԐM�G���g���𓊍e���܂��B<br/>
	 * URL��ɂ���摜�t���ŃG���g�����܂��B
	 * 
	 * @param inReplyToStatusId �ԐM���X�e�[�^�XID
	 * @param text ���e���e
	 * @param imageUrl �摜��URL
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status reply(String inReplyToStatusId, String text, String imageUrl) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setInReplyToStatusId(inReplyToStatusId);
		params.setText(text);
		params.setImageUrl(imageUrl);
		return _updateStatus(params);
	}

	/**
	 * �V�����ԐM�G���g���𓊍e���܂��B<br/>
	 * �摜�̃o�C�i���f�[�^�Ƃ��̊g���q���w�肵�A�摜�t���ŃG���g�����܂��B
	 * 
	 * @param inReplyToStatusId �ԐM���X�e�[�^�XID
	 * @param text ���e���e
	 * @param imageData �摜�̃o�C�i���f�[�^
	 * @param imageDataExt �摜�̊g���q
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status reply(String inReplyToStatusId, String text, byte[] imageData, ImageExt imageDataExt) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setInReplyToStatusId(inReplyToStatusId);
		params.setText(text);
		params.setImageData(imageData);
		params.setImageDataExt(imageDataExt);
		return _updateStatus(params);
	}

	/**
	 * �͂Ăȃn�C�N�ɓ��e���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/update.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-update">statuses/update</a>
	 * @param params �X�V�p�����[�^
	 * @return ���e���ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	private Status _updateStatus(UpdateParameter param) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doPost(loginUser, URL_UPDATE_STATUS_XML, param, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵���X�e�[�^�XID�̃G���g�����폜���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/destroy/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-destroy">statuses/destroy</a>
	 * @param statusId �X�e�[�^�XID
	 * @return �폜�����X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public Status deleteEntry(String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_DELETE_STATUS + statusId + Const.EXT_XML, null, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	//-------------------------------------------------------------
	// �X�^�[�֌W
	//-------------------------------------------------------------

	/**
	 * �w�肵���G���g���ɃX�^�[����ǉ����܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/create/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-create">favorites/create</a>
	 * @param statusId �X�e�[�^�XID
	 * @return �X�^�[����ǉ��������ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public Status addStar(String statusId) throws HatenaHaikuException {
		return _modifyStar(true, statusId);
	}

	/**
	 * �w�肵���G���g���̃X�^�[������炵�܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-destroy">favorites/destroy</a>
	 * @param statusId �X�e�[�^�XID
	 * @return �X�^�[������炵�����ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public Status deleteStar(String statusId) throws HatenaHaikuException {
		return _modifyStar(false, statusId);
	}
	
	/**
	 * �w�肵���G���g���̃X�^�[������₵���茸�炵���肵�܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-destroy">favorites/destroy</a>
	 * @param isAdd true:����₷�^false:����炷
	 * @param statusId �X�e�[�^�XID
	 * @return �X�^�[������₵���茸�炵���肵�����ʂ̃X�e�[�^�X���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	private Status _modifyStar(boolean isAdd, String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isAdd ? URL_ADD_STAR : URL_DELETE_STAR) + statusId + Const.EXT_XML, null, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	//-------------------------------------------------------------
	// ���[�U�^�t�H���[�֌W
	//-------------------------------------------------------------

	/**
	 * �F�؂������[�U���t�H���[���Ă��郆�[�U�̃��X�g��100���擾���܂��B�i�P�y�[�W�ځj<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @return �F�؂������[�U���t�H���[���Ă��郆�[�U�̃��X�g�i�P�y�[�W�ځj
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<User> getFollowingList() throws HatenaHaikuException {
		return getFollowingList(1);
	}

	/**
	 * �F�؂������[�U���t�H���[���Ă��郆�[�U�̃��X�g��100���擾���܂��B�i�w��y�[�W�j<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends.xml&page=<font color="red">�y�[�W</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @param page �y�[�W
	 * @return �F�؂������[�U���t�H���[���Ă��郆�[�U�̃��X�g�i�w��y�[�W�j
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<User> getFollowingList(int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWING_LIST_XML, param, isNeedHttpLog());
			return toUserList(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �F�؂������[�U���t�H���[���Ă��郆�[�U�̃��X�g���擾���܂��B<br/>
	 * �t�H�����[�̓y�[�W�w��ł����A��C�ɑS�����擾�����悤�ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-followers">statuses/followers</a>
	 * @return �F�؂������[�U���t�H���[���Ă��郆�[�U�̃��X�g
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<User> getFollowersList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWERS_LIST_XML, null, isNeedHttpLog());
			return toUserList(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * ���[�U���t�H���[���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/create/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-create">friendships/create</a>
	 * @param userId ���[�UID
	 * @return �t�H���[�������[�U���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public User followUser(String userId) throws HatenaHaikuException {
		return _modifyFollowUser(true, userId);
	}

	/**
	 * ���[�U�̃t�H���[����߂܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-destroy">friendships/destroy</a>
	 * @param userId ���[�UID
	 * @return �t�H���[����߂����[�U���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public User unfollowUser(String userId) throws HatenaHaikuException {
		return _modifyFollowUser(false, userId);
	}
	
	/**
	 * ���[�U�̃t�H���[�������߂��肵�܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-destroy">friendships/destroy</a>
	 * @param isFollow true:�t�H���[����^false:�t�H���[����߂�
	 * @param userId ���[�UID
	 * @return �t�H���[�������߂��肵�����[�U���
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	private User _modifyFollowUser(boolean isFollow, String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isFollow ? URL_FOLLOW_USER : URL_UNFOLLOW_USER) + userId + Const.EXT_XML, null, isNeedHttpLog());
			return toUser(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	/**
	 * ���O�C�����[�U�̃��[�U�����擾���܂��B<br/>
	 * 
	 * @see HatenaHaikuAPILight#getUser(String)
	 * @return ���O�C�����[�U�̃��[�U���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public User getMe() throws HatenaHaikuException {
		return getUser(loginUser.getUserId());
	}

	//-------------------------------------------------------------
	// �L�[���[�h�֌W
	//-------------------------------------------------------------

	/**
	 * �F�؂������[�U���t�H���[���Ă���L�[���[�h�̃��X�g���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keywords.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keywords">statuses/keywords</a>
	 * @return�@�F�؂������[�U���t�H���[���Ă���L�[���[�h���X�g
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Keyword> getFollowingKeywordList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWING_KEYWORD_LIST_XML, null, isNeedHttpLog());
			return toKeywordList(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �L�[���[�h���t�H���[���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/create/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-create">keywords/create</a>
	 * @param keyword �L�[���[�h
	 * @return �t�H���[�����L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Keyword followKeyword(String keyword) throws HatenaHaikuException {
		return _modifyFollowKeyword(true, keyword);
	}

	/**
	 * �L�[���[�h�̃t�H���[����߂܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-destroy">keywords/destroy</a>
	 * @param keyword �L�[���[�h
	 * @return �t�H���[����߂��L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Keyword unfollowKeyword(String keyword) throws HatenaHaikuException {
		return _modifyFollowKeyword(false, keyword);
	}

	/**
	 * �L�[���[�h�̃t�H���[������A��߂��肵�܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-destroy">keywords/destroy</a>
	 * @param isFollow true:�t�H���[����^false:�t�H���[����߂�
	 * @param keyword �L�[���[�h
	 * @return �t�H���[�����^��߂��L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	private Keyword _modifyFollowKeyword(boolean isFollow, String keyword) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isFollow ? URL_FOLLOW_KEYWORD : URL_UNFOLLOW_KEYWORD) + StringUtil.encode(keyword) + Const.EXT_XML, null, isNeedHttpLog());
			return toKeyword(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �֘A�L�[���[�h��ݒ肵�܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/create.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-relation-create">keywords/relation/create</a>
	 * @param keyword1 �ݒ肷��Ώۂ̃L�[���[�h�P
	 * @param keyword2 �ݒ肷��Ώۂ̃L�[���[�h�Q
	 * @return �֘A�L�[���[�h��ݒ��̃L�[���[�h�P�̃L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Keyword relateKeyword(String keyword1, String keyword2) throws HatenaHaikuException {
		return _modifyRelateKeyword(true, keyword1, keyword2);
	}

	/**
	 * �֘A�L�[���[�h���������܂��B<br/>
	 * �֘A�L�[���[�h�̐ݒ�̍폜�͎������ݒ肵�����̂Ɍ����܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/destroy.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-relation-destroy">keywords/relation/destroy</a>
	 * @param keyword1 ��������Ώۂ̃L�[���[�h�P
	 * @param keyword2 ��������Ώۂ̃L�[���[�h�Q
	 * @return �֘A�L�[���[�h��������̃L�[���[�h�P�̃L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Keyword unrelateKeyword(String keyword1, String keyword2) throws HatenaHaikuException {
		return _modifyRelateKeyword(false, keyword1, keyword2);
	}
		
	/**
	 * �֘A�L�[���[�h�ݒ�^����
	 * 
	 * @param isRelate true:�ݒ�^false:����
	 * @param keyword1 �ΏۃL�[���[�h�P
	 * @param keyword2 �ΏۃL�[���[�h�Q
	 * @return �֘A�L�[���[�h��ݒ�^������̃L�[���[�h�P�̃L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	private Keyword _modifyRelateKeyword(boolean isRelate, String keyword1, String keyword2) throws HatenaHaikuException {
		if (StringUtil.isSame(keyword1, keyword2)) {
			throw new HatenaHaikuException("�ΏۃL�[���[�h�ɓ����L�[���[�h�͎w��ł��܂���B");
		}
		try {
			QueryParameter param = new QueryParameter();
			param.setWord1(keyword1);
			param.setWord2(keyword2);
			String resultXml = HttpUtil.doGet(loginUser, (isRelate ? URL_RELATE_KEYWORD_XML : URL_UNRELATE_KEYWORD_XML), param, isNeedHttpLog());
			return toKeyword(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �F�؂������[�U��id�y�[�W�̃L�[���[�h�����擾���܂��B<br/>
	 * 
	 * @see HatenaHaikuAPILight#getKeyword(String)
	 * @return �F�؂������[�U��id�y�[�W�̃L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword getMyKeyword() throws HatenaHaikuException {
		return getKeyword(loginUser.getUserIdNotation());
	}

	//----------------------------------
	// �ȉ��A�ϊ����\�b�h
	//----------------------------------
	
	/**
	 * �X�e�[�^�X���̃C���X�^���X���擾���܂��B
	 * 
	 * @since v0.2.0
	 */
	@Override
	protected Status createStatus() {
		return Status.create(this);
	}

	/**
	 * ���[�U���̃C���X�^���X���擾���܂��B
	 * 
	 * @since v0.2.0
	 */
	@Override
	protected User createUser() {
		return User.create(this);
	}
	
	/**
	 * �L�[���[�h���̃C���X�^���X���擾���܂��B
	 * 
	 * @since v0.2.0
	 */
	@Override
	protected Keyword createKeyword() {
		return Keyword.create(this);
	}
	
}
