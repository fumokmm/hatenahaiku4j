package hatenahaiku4j;

import hatenahaiku4j.util.DateUtil;
import hatenahaiku4j.util.HttpUtil;
import hatenahaiku4j.util.XmlUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * �͂Ăȃn�C�NAPI���b�s���O�N���X�i�F�؂Ȃ��j<br/>
 * Light�͔F�؂Ȃ���"����y"�̈Ӗ��ł��B
 * 
 * @see <a href="http://h.hatena.ne.jp/api">�͂Ăȃn�C�NAPI</a>
 * @since v0.2.0
 * @author fumokmm
 */
public class HatenaHaikuAPILight {
	/** URL: �p�u���b�N�^�C�����C��(XML) */
	protected static final String URL_PUBLIC_TIMELINE_XML		= "http://h.hatena.ne.jp/api/statuses/public_timeline.xml";
	/** URL: �t�����h�^�C�����C�� */
	protected static final String URL_FRIENDS_TIMELINE			= "http://h.hatena.ne.jp/api/statuses/friends_timeline/";
	/** URL: ���[�U�^�C�����C�� */
	protected static final String URL_USER_TIMELINE				= "http://h.hatena.ne.jp/api/statuses/user_timeline/";
	/** URL: �L�[���[�h�^�C�����C�� */
	protected static final String URL_KEYWORD_TIMELINE			= "http://h.hatena.ne.jp/api/statuses/keyword_timeline/";
	/** URL: �A���o���^�C�����C��(XML) */
	protected static final String URL_ALBUM_TIMELINE_XML		= "http://h.hatena.ne.jp/api/statuses/album.xml";
	/** URL: �L�[���[�h�^�C�����C�� */
	protected static final String URL_ALBUM_TIMELINE			= "http://h.hatena.ne.jp/api/statuses/album/";
	/** URL: �X�e�[�^�X��� */
	protected static final String URL_STATUS					= "http://h.hatena.ne.jp/api/statuses/show/";
	/** URL: ���[�U���t�H���[���Ă��郆�[�U�̃��X�g */
	protected static final String URL_FOLLOWING_LIST			= "http://h.hatena.ne.jp/api/statuses/friends/";
	/** URL: ���[�U���t�H���[���Ă��郆�[�U�̃��X�g */
	protected static final String URL_FOLLOWERS_LIST			= "http://h.hatena.ne.jp/api/statuses/followers/";
	/** URL: ���[�U��� */
	protected static final String URL_USER						= "http://h.hatena.ne.jp/api/friendships/show/";
	/** URL: �z�b�g�L�[���[�h�̃��X�g(XML) */
	protected static final String URL_HOT_KEYWORD_LIST_XML		= "http://h.hatena.ne.jp/api/keywords/hot.xml";
	/** URL: �L�[���[�h�̃��X�g(XML) */
	protected static final String URL_KEYWORD_LIST_XML			= "http://h.hatena.ne.jp/api/keywords/list.xml";
	/** URL: ���[�U���t�H���[���Ă���L�[���[�h�̃��X�g */
	protected static final String URL_FOLLOWING_KEYWORD_LIST	= "http://h.hatena.ne.jp/api/statuses/keywords/";
	/** URL: �L�[���[�h��� */
	protected static final String URL_KEYWORD					= "http://h.hatena.ne.jp/api/keywords/show/";

	/** HTTP�ʐM���O�o�͗v�� */
	protected boolean needHttpLog;
	
	/**
	 * HTTP�ʐM���O�o�͗v�ۂ�ԋp���܂��B
	 * 
	 * @return HTTP�ʐM���O�o�͗v��
	 * @since v0.2.0
	 */
	public boolean isNeedHttpLog() {
		return needHttpLog;
	}
	
	/**
	 * HTTP�ʐM���O�o�͗v�ۂ�ݒ肵�܂��B
	 * 
	 * @param needHttpLog HTTP�ʐM���O�o�͗v��
	 * @since v0.2.0
	 */
	public void setNeedHttpLog(boolean needHttpLog) {
		this.needHttpLog = needHttpLog;
	}
	
	/** API�V���A���l */
	private final String serial;

	/**
	 * API�V���A���l���擾����B
	 *
	 * @return API�V���A���l
	 * @since v0.2.0
	 */
	protected String getSerial() {
		return serial;
	}

	/**
	 * �X�e�[�^�X�̏�Ԃ�shadow�̎��ɕԐM��������擾���邩�ǂ���<br/>
	 * �f�t�H���g:true
	 */
	private boolean autoRefreshReplies;
	
	/**
	 * �R���X�g���N�^�B
	 * 
	 * @since v0.0.1
	 */
	public HatenaHaikuAPILight() {
		// API�V���A���l
		this.serial = String.valueOf(System.currentTimeMillis())
				+ String.valueOf(Math.random());
		// �X�e�[�^�X�̏�Ԃ�shadow�̎��ɕԐM��������擾���邩�ǂ���
		this.autoRefreshReplies = true;
	}
	
	/**
	 * �X�e�[�^�X�̏�Ԃ�shadow�̎��ɕԐM��������擾���邩�ǂ�����ԋp���܂��B
	 * 
	 * @return �����擾����:true�^�����擾���Ȃ�:false
	 * @since v0.2.0
	 */
	public boolean isAutoRefreshReplies() {
		return autoRefreshReplies;
	}
	
	/**
	 * �X�e�[�^�X�̏�Ԃ�shadow�̎��ɕԐM��������擾���邩�ǂ����ݒ肵�܂��B
	 * 
	 * @param autoRefreshReplies �ԐM��������擾���邩�ǂ���
	 * @since v0.2.0
	 */
	public void setAutoRefreshReplies(boolean autoRefreshReplies) {
		this.autoRefreshReplies = autoRefreshReplies;
	}
	
	//-------------------------------------------------------------
	// �^�C�����C���֌W
	//-------------------------------------------------------------

	/**
	 * �p�u���b�N�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">�͂Ăȃn�C�N</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @return �p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getPublicTimeline() throws HatenaHaikuException {
		return getPublicTimeline(0, null);
	}

	/**
	 * �p�u���b�N�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">�͂Ăȃn�C�N</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getPublicTimeline(int page) throws HatenaHaikuException {
		return getPublicTimeline(page, null);
	}

	/**
	 * �p�u���b�N�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">�͂Ăȃn�C�N</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getPublicTimeline(int page, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_PUBLIC_TIMELINE_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵�����[�U�̃t�����h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ���[�UID
	 * @return �w�肵�����[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(String userId) throws HatenaHaikuException {
		return getFriendsTimeline(userId, 0, 0, null);
	}

	/**
	 * �w�肵�����[�U�̃t�����h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �w�肵�����[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(String userId, int page) throws HatenaHaikuException {
		return getFriendsTimeline(userId, page, 0, null);
	}

	/**
	 * �w�肵�����[�U�̃t�����h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �w�肵�����[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return getFriendsTimeline(userId, page, count, null);
	}

	/**
	 * �w�肵�����[�U�̃t�����h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �w�肵�����[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_FRIENDS_TIMELINE + userId + Const.EXT_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	/**
	 * �w�肵�����[�U�̃��[�U�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ���[�UID
	 * @return �w�肵�����[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(String userId) throws HatenaHaikuException {
		return getUserTimeline(userId, 0, 0, null);
	}

	/**
	 * �w�肵�����[�U�̃��[�U�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �w�肵�����[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(String userId, int page) throws HatenaHaikuException {
		return getUserTimeline(userId, page, 0, null);
	}

	/**
	 * �w�肵�����[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �w�肵�����[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return getUserTimeline(userId, page, count, null);
	}

	/**
	 * �w�肵�����[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �w�肵�����[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_USER_TIMELINE + userId + Const.EXT_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	/**
	 * �w�肵�����[�U��id�y�[�W�̃^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @param userId ���[�UID
	 * @return �w�肵�����[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(String userId) throws HatenaHaikuException {
		return getKeywordTimeline(Const.ID_COLON + userId, 0, 0, null);
	}

	/**
	 * �w�肵�����[�U��id�y�[�W�̃^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �w�肵�����[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(String userId, int page) throws HatenaHaikuException {
		return getKeywordTimeline(Const.ID_COLON + userId, page, 0, null);
	}

	/**
	 * �w�肵�����[�U��id�y�[�W�̃^�C�����C�����擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �w�肵�����[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return getKeywordTimeline(Const.ID_COLON + userId, page, count, null);
	}

	/**
	 * �w�肵�����[�U��id�y�[�W�̃^�C�����C�����擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param userId ���[�UID
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �w�肵�����[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		return getKeywordTimeline(Const.ID_COLON + userId, page, count, since);
	}
	
	/**
	 * �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword �L�[���[�h
	 * @return �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getKeywordTimeline(String keyword) throws HatenaHaikuException {
		return getKeywordTimeline(keyword, 0, 0, null);
	}

	/**
	 * �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword �L�[���[�h
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getKeywordTimeline(String keyword, int page) throws HatenaHaikuException {
		return getKeywordTimeline(keyword, page, 0, null);
	}

	/**
	 * �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword �L�[���[�h
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getKeywordTimeline(String keyword, int page, int count) throws HatenaHaikuException {
		return getKeywordTimeline(keyword, page, count, null);
	}

	/**
	 * �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword �L�[���[�h
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �w�肵���L�[���[�h�̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getKeywordTimeline(String keyword, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_KEYWORD_TIMELINE + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	/**
	 * �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @return �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumTimeline() throws HatenaHaikuException {
		return getAlbumTimeline(0, 0, null);
	}

	/**
	 * �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumTimeline(int page) throws HatenaHaikuException {
		return getAlbumTimeline(page, 0, null);
	}

	/**
	 * �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumTimeline(int page, int count) throws HatenaHaikuException {
		return getAlbumTimeline(page, count, null);
	}

	/**
	 * �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �摜���܂ލŐV�̃G���g���̃p�u���b�N�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumTimeline(int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_ALBUM_TIMELINE_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵���L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param keyword �L�[���[�h
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumKeywordTimeline(String keyword) throws HatenaHaikuException {
		return getAlbumKeywordTimeline(keyword, 0, 0, null);
	}

	/**
	 * �w�肵���L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param keyword �L�[���[�h
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumKeywordTimeline(String keyword, int page) throws HatenaHaikuException {
		return getAlbumKeywordTimeline(keyword, page, 0, null);
	}

	/**
	 * �w�肵���L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param keyword �L�[���[�h
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumKeywordTimeline(String keyword, int page, int count) throws HatenaHaikuException {
		return getAlbumKeywordTimeline(keyword, page, count, null);
	}

	/**
	 * �w�肵���L�[���[�h�̉摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param keyword �L�[���[�h
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return �摜���܂ލŐV�̃G���g���̃L�[���[�h�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getAlbumKeywordTimeline(String keyword, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_ALBUM_TIMELINE + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, param, isNeedHttpLog());
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	//-------------------------------------------------------------
	// �X�e�[�^�X�֌W
	//-------------------------------------------------------------
	
	/**
	 * �w�肵���X�e�[�^�X�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/show/<font color="red">�X�e�[�^�XID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-show">statuses/show</a>
	 * @param statusId �X�e�[�^�XID
	 * @return �X�e�[�^�X���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Status getStatus(String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_STATUS + statusId + Const.EXT_XML, null, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

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

	//-------------------------------------------------------------
	// ���[�U�^�t�H���[�֌W
	//-------------------------------------------------------------

	/**
	 * �w�肵�����[�U���t�H���[���Ă��郆�[�U�̃��X�g��100���擾���܂��B�i�P�y�[�W�ځj<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @param userId ���[�UID
	 * @return �w�肵�����[�U���t�H���[���Ă��郆�[�U�̃��X�g�i�P�y�[�W�ځj
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<User> getFollowingList(String userId) throws HatenaHaikuException {
		return getFollowingList(userId, 0);
	}

	/**
	 * �w�肵�����[�U���t�H���[���Ă��郆�[�U�̃��X�g��100���擾���܂��B�i�w��y�[�W�j<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">���[�UID</font>.xml&page=<font color="red">�y�[�W</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @param userId ���[�UID
	 * @param page �y�[�W
	 * @return �w�肵�����[�U���t�H���[���Ă��郆�[�U�̃��X�g�i�w��y�[�W�j
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<User> getFollowingList(String userId, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			String resultXml = HttpUtil.doGet(URL_FOLLOWING_LIST + userId + Const.EXT_XML, param, isNeedHttpLog());
			return toUserList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵�����[�U���t�H���[���Ă��郆�[�U�̃��X�g���擾���܂��B<br/>
	 * �t�H�����[�̓y�[�W�w��ł����A��C�ɑS�����擾�����悤�ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-followers">statuses/followers</a>
	 * @param userId ���[�UID
	 * @return �w�肵�����[�U���t�H���[���Ă��郆�[�U�̃��X�g
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<User> getFollowersList(String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_FOLLOWERS_LIST + userId + Const.EXT_XML, null, isNeedHttpLog());
			return toUserList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵�����[�U�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/show/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-show">friendships/show</a>
	 * @param userId ���[�UID
	 * @return �w�肵�����[�U���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public User getUser(String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_USER + userId + Const.EXT_XML, null, isNeedHttpLog());
			return toUser(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	//-------------------------------------------------------------
	// �L�[���[�h�֌W
	//-------------------------------------------------------------

	/**
	 * �z�b�g�L�[���[�h�̃��X�g���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/hot.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/hotkeywords">���ڃL�[���[�h</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-hot">keywords/hot</a>
	 * @return �z�b�g�L�[���[�h�̃��X�g
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Keyword> getHotKeywordList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_HOT_KEYWORD_LIST_XML, null, isNeedHttpLog());
			return toKeywordList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �L�[���[�h���X�g���擾���܂��B�i�P�y�[�W�ځj<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/list.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">�L�[���[�h���X�g</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-list">keywords/list</a>
	 * @return �L�[���[�h���X�g�i�P�y�[�W�ځj
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Keyword> getKeywordList() throws HatenaHaikuException {
		return getKeywordList(null, 1);
	}

	/**
	 * �L�[���[�h���X�g���擾���܂��B�i�w��y�[�W�j<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/list.xml?page=<font color="red">�y�[�W</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">�L�[���[�h���X�g</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-list">keywords/list</a>
	 * @param page �y�[�W
	 * @return�@�L�[���[�h���X�g�i�w��y�[�W�j
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Keyword> getKeywordList(int page) throws HatenaHaikuException {
		return getKeywordList(null, page);
	}

	/**
	 * �w�肵�����[�h�ɕ�����v����L�[���[�h���X�g���擾���܂��B�i�w��y�[�W�j<br/>
	 * �p�����[�^��?word=00, page=1�Ȃǂ��g����B<br/>
	 * page�̍ő吔��100��API����y�[�W�ɏ�����Ă��邪�A����ȏ�ł��擾�ł���悤�ł���B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/list.xml?word=<font color="red">�������[�h</font>&page=<font color="red">�y�[�W</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">�L�[���[�h���X�g</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-list">keywords/list</a>
	 * @param searchWord �������[�h
	 * @param page �y�[�W
	 * @return�@�L�[���[�h���X�g�i�w��y�[�W�j
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Keyword> getKeywordList(String searchWord, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setWord(searchWord);
			param.setPage(page);
			String resultXml = HttpUtil.doGet(URL_KEYWORD_LIST_XML, param, isNeedHttpLog());
			return toKeywordList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵�����[�U���t�H���[���Ă���L�[���[�h�̃��X�g���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keywords/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keywords">statuses/keywords</a>
	 * @param userId ���[�UID
	 * @return�@�w�肵�����[�U���t�H���[���Ă���L�[���[�h���X�g
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Keyword> getFollowingKeywordList(String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_FOLLOWING_KEYWORD_LIST + userId + Const.EXT_XML, null, isNeedHttpLog());
			return toKeywordList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}

	/**
	 * �w�肵���L�[���[�h�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/show/<font color="red">�L�[���[�h</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-show">keywords/show</a>
	 * @param keyword �L�[���[�h
	 * @return �w�肵���L�[���[�h���
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Keyword getKeyword(String keyword) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_KEYWORD + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, null, isNeedHttpLog());
			return toKeyword(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException�����B", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException�����B", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException�����B", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException�����B", e);
		}
	}
	
	//----------------------------------
	// �ȉ��A�ϊ����\�b�h
	//----------------------------------
	
	/**
	 * �X�e�[�^�X���̃C���X�^���X���擾���܂��B
	 * 
	 * @since v0.2.0
	 */
	protected Status createStatus() {
		return Status.create(this);
	}

	/**
	 * ���[�U���̃C���X�^���X���擾���܂��B
	 * 
	 * @since v0.2.0
	 */
	protected User createUser() {
		return User.create(this);
	}
	
	/**
	 * �L�[���[�h���̃C���X�^���X���擾���܂��B
	 * 
	 * @since v0.2.0
	 */
	protected Keyword createKeyword() {
		return Keyword.create(this);
	}
	
	/**
	 * �X�e�[�^�X�G�������g��Status���ɕϊ����܂��B
	 * 
	 * @param elemStatus �X�e�[�^�X�G�������g
	 * @return Status���
	 * @since v0.0.1
	 */
	protected Status toStatus(Element elemStatus) throws HatenaHaikuException {
		try {
			Status status = createStatus();
			// �X�e�[�^�XID
			status.setStatusId(XmlUtil.getText(elemStatus, "id"));
			// �쐬����
			status.setCreatedAt(DateUtil.toJSTDate(XmlUtil.getText(elemStatus, "created_at")));
			// ���C�ɓ����
			status.setFavorited(Integer.parseInt(XmlUtil.getText(elemStatus, "favorited")));
			// �ԐM��X�e�[�^�XID
			status.setInReplyToStatusId(XmlUtil.getText(elemStatus, "in_reply_to_status_id"));
			// �ԐM�惆�[�UID
			status.setInReplyToUserId(XmlUtil.getText(elemStatus, "in_reply_to_user_id"));
			// �L�[���[�h
			status.setKeyword(XmlUtil.getText(elemStatus, "keyword"));
			// �����N
			status.setLink(XmlUtil.getText(elemStatus, "link"));
			// �\�[�X�i�N���C�A���g���j
			status.setSource(XmlUtil.getText(elemStatus, "source"));
			// ���e���e
			status.setText(XmlUtil.getText(elemStatus, "text"));
			// ���[�U���
			status.setUser(toUser(XmlUtil.getFirstChildElement(elemStatus, "user")));
			// ���̃X�e�[�^�X�ւ̕ԐM
			List<Status> replies = new ArrayList<Status>();
			for (Element replyStatus : XmlUtil.getChildElementsByTagName(elemStatus, "replies")) {
				Status reply = toStatus(replyStatus);
				reply.setShadow(true);
				reply.setInReplyToStatusId(status.getStatusId());
				reply.setInReplyToUserId(status.getUserId());
				reply.setKeyword(status.getKeyword());
				reply.setLink(reply.getUser().getEntriesUrl() + reply.getStatusId());
				// text�́u�L�[���[�h=�v�����̍폜
				reply.removeKeywordEqualOnText();
				replies.add(reply);
			}
			status.setReplies(replies);
			
			// text�́u�L�[���[�h=�v�����̍폜
			status.removeKeywordEqualOnText();
			
			return status;
		} catch (ParseException e) {
			throw new HatenaHaikuException("ParseException����", e);
		}
	}
	
	/**
	 * �X�e�[�^�X�G�������g���[�g��Status��񃊃X�g�ɕϊ����܂��B
	 * 
	 * @param elemStatuses �X�e�[�^�X�G�������g���[�g
	 * @return Status��񃊃X�g
	 * @since v0.0.1
	 */
	protected List<Status> toStatusList(Element elemStatuses) throws HatenaHaikuException {
		List<Status> statusList = new ArrayList<Status>();
		for (Element elemStatus : XmlUtil.getChildElementsByTagName(elemStatuses, "status")) {
			statusList.add(toStatus(elemStatus));
		}
		return statusList;
	}

	/**
	 * ���[�U�G�������g��User���ɕϊ����܂��B
	 * 
	 * @param elemUser ���[�U�G�������g
	 * @return User���
	 * @since v0.0.1
	 */
	protected User toUser(Element elemUser) {
		User user = createUser();
		// ���[�U��
		user.setName(XmlUtil.getText(elemUser, "name"));
		// �t�H�����[��
		user.setFollowersCount(Integer.parseInt(XmlUtil.getText(elemUser, "followers_count")));
		// ���[�UID
		user.setUserId(XmlUtil.getText(elemUser, "id"));
		// �v���t�B�[���摜URL
		user.setProfileImageUrl(XmlUtil.getText(elemUser, "profile_image_url"));
		// �\����
		user.setScreenName(XmlUtil.getText(elemUser, "screen_name"));
		// ���[�U�̃G���g���y�[�W��URL
		user.setUrl(XmlUtil.getText(elemUser, "url"));
		return user;
	}

	/**
	 * ���[�U�G�������g���[�g��User��񃊃X�g�ɕϊ����܂��B
	 * 
	 * @param elemUsers ���[�U�G�������g���[�g
	 * @return User��񃊃X�g
	 * @since v0.0.1
	 */
	protected List<User> toUserList(Element elemUsers) throws HatenaHaikuException {
		List<User> userList = new ArrayList<User>();
		for (Element elemUser : XmlUtil.getChildElementsByTagName(elemUsers, "user")) {
			userList.add(toUser(elemUser));
		}
		return userList;
	}

	/**
	 * �L�[���[�h�G�������g��Keyword���ɕϊ����܂��B
	 * 
	 * @param elemKeyword �L�[���[�h�G�������g
	 * @return Keyword���
	 * @since v0.0.1
	 */
	protected Keyword toKeyword(Element elemKeyword) throws HatenaHaikuException {
		Keyword keyword = createKeyword();
		// ���e��
		keyword.setEntryCount(Integer.parseInt(XmlUtil.getText(elemKeyword, "entry_count")));
		// �t�H�����[��
		keyword.setFollowersCount(Integer.parseInt(XmlUtil.getText(elemKeyword, "followers_count")));
		// �L�[���[�h�y�[�W�̃����N
		keyword.setLink(XmlUtil.getText(elemKeyword, "link"));
		// �֘A�L�[���[�h
		keyword.setRelatedKeywords(XmlUtil.getTextList(elemKeyword, "related_keywords"));
		// �L�[���[�h�^�C�g��
		keyword.setTitle(XmlUtil.getText(elemKeyword, "title"));
		return keyword;
	}

	/**
	 * �L�[���[�h�G�������g���[�g��Keyword��񃊃X�g�ɕϊ����܂��B
	 * 
	 * @param elemKeywords �L�[���[�h�G�������g���[�g
	 * @return Keyword��񃊃X�g
	 * @since v0.0.1
	 */
	protected List<Keyword> toKeywordList(Element elemKeywords) throws HatenaHaikuException {
		List<Keyword> keywordList = new ArrayList<Keyword>();
		for (Element elemKeyword : XmlUtil.getChildElementsByTagName(elemKeywords, "keyword")) {
			keywordList.add(toKeyword(elemKeyword));
		}
		return keywordList;
	}
}
