package hatenahaiku4j;

import java.util.Date;
import java.util.List;

/**
 * ���[�U�Ɋւ���API
 * 
 * @since v0.2.0
 * @author fumokmm
 */
public class UserAPI extends EntityAPI {
	/** ���[�U��� */
	private User user;

	/**
	 * �R���X�g���N�^�B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @since v0.2.0
	 */
	UserAPI() {
		super();
	}

	/**
	 * ���������܂��B
	 * 
	 * @param user ���[�U���
	 * @param apiAuth �͂Ăȃn�C�NAPI�i�F�؂���j
	 * @since v0.2.0
	 */
	void init(User user, HatenaHaikuAPI apiAuth) {
		this.user = user;
		this.apiAuth = apiAuth;
		this.apiLight = apiAuth;
	}

	/**
	 * ���������܂��B
	 * 
	 * @param user ���[�U���
	 * @param apiLight �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j
	 * @since v0.2.0
	 */
	void init(User user, HatenaHaikuAPILight apiLight) {
		this.user = user;
		this.apiLight = apiLight;
	}

	// ------------------�ȉ��A�F�؂��s�v��API

	/**
	 * ���̃��[�U�̃t�����h�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String)
	 * @return ���̃��[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline() throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId());
	}

	/**
	 * ���̃��[�U�̃t�����h�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return ���̃��[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline(int page) throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId(), page);
	}

	/**
	 * ���̃��[�U�̃t�����h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return ���̃��[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId(), page, count);
	}

	/**
	 * ���̃��[�U�̃t�����h�^�C�����C�����擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return ���̃��[�U�̃t�����h�^�C�����C��
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * ���̃��[�U�̃��[�U�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String)
	 * @return ���̃��[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline() throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId());
	}

	/**
	 * ���̃��[�U�̃��[�U�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return ���̃��[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page) throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId(), page);
	}

	/**
	 * ���̃��[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return ���̃��[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId(), page, count);
	}

	/**
	 * ���̃��[�U�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return ���̃��[�U�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * ���̃��[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String)
	 * @return ���̃��[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline() throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId());
	}

	/**
	 * ���̃��[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return ���̃��[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page) throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId(), page);
	}

	/**
	 * ���̃��[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return ���̃��[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId(), page, count);
	}

	/**
	 * ���̃��[�U�̐l�C�̃��[�U�^�C�����C�����擾���܂��B<br/>
	 *�@<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">���[�UID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return ���̃��[�U�̐l�C�̃��[�U�^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * ���̃��[�U��id�y�[�W�̃^�C�����C�����擾���܂��B�ŐV�y�[�W��20���擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String)
	 * @return ���̃��[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline() throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId());
	}

	/**
	 * ���̃��[�U��id�y�[�W�̃^�C�����C�����擾���܂��B�擾������20���ł��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @return ���̃��[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page) throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId(), page);
	}

	/**
	 * ���̃��[�U��id�y�[�W�̃^�C�����C�����擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String, int, int)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @return ���̃��[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId(), page, count);
	}

	/**
	 * ���̃��[�U��id�y�[�W�̃^�C�����C�����擾���܂��B<br/>
	 * ���̃^�C�����C���� "id:xxxx" �̃L�[���[�h�^�C�����C���Ɠ������̂ł��B
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String, int, int, Date)
	 * @param page �擾����y�[�W�ł��B�ő吔��100�ł��B
	 * @param count �擾�����w�肵�܂��B�ő吔�� 200 �ł��B
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * @return ���̃��[�U��id�y�[�W�̃^�C�����C��
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * ���̃��[�U���t�H���[���Ă��郆�[�U�̃��X�g��100���擾���܂��B�i�P�y�[�W�ځj<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowingList(String)
	 * @return ���̃��[�U���t�H���[���Ă��郆�[�U�̃��X�g�i�P�y�[�W�ځj
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<User> getFollowingList() throws HatenaHaikuException {
		return apiLight.getFollowingList(user.getUserId());
	}

	/**
	 * ���̃��[�U���t�H���[���Ă��郆�[�U�̃��X�g��100���擾���܂��B�i�w��y�[�W�j<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">���[�UID</font>.xml&page=<font color="red">�y�[�W</font></i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowingList(String, int)
	 * @param page �y�[�W
	 * @return ���̃��[�U���t�H���[���Ă��郆�[�U�̃��X�g�i�w��y�[�W�j
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<User> getFollowingList(int page) throws HatenaHaikuException {
		return apiLight.getFollowingList(user.getUserId(), page);
	}

	/**
	 * ���̃��[�U���t�H���[���Ă��郆�[�U�̃��X�g���擾���܂��B<br/>
	 * �t�H�����[�̓y�[�W�w��ł����A��C�ɑS�����擾�����悤�ł��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowersList(String)
	 * @return ���̃��[�U���t�H���[���Ă��郆�[�U�̃��X�g
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<User> getFollowersList() throws HatenaHaikuException {
		return apiLight.getFollowingList(user.getUserId());
	}

	/**
	 * ���̃��[�U���t�H���[���Ă���L�[���[�h�̃��X�g���擾���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keywords/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowingKeywordList(String)
	 * @return�@���̃��[�U���t�H���[���Ă���L�[���[�h���X�g
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Keyword> getFollowingKeywordList() throws HatenaHaikuException {
		return apiLight.getFollowingKeywordList(user.getUserId());
	}
	
	// ------------------�ȉ��A�F�؂��K�v��API
	
	/**
	 * ���̃��[�U���t�H���[���܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/create/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#followUser(String)
	 * @return �t�H���[�������̃��[�U���
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public User follow() throws HatenaHaikuException {
		if (isAuth()) {
			User followed = apiAuth.followUser(user.getUserId());
			user.overwrite(followed);
			return user;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

	/**
	 * ���̃��[�U�̃t�H���[����߂܂��B<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">���[�UID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unfollowUser(String)
	 * @return �t�H���[����߂����̃��[�U���
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public User unfollow(String userId) throws HatenaHaikuException {
		if (isAuth()) {
			User unfollowed = apiAuth.unfollowUser(user.getUserId());
			user.overwrite(unfollowed);
			return user;
		} else {
			throw new HatenaHaikuException("�F�؂����K�v������܂��BHatenaHaikuAPI�������p���������B");
		}
	}

}
