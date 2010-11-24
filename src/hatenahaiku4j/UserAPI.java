package hatenahaiku4j;

import java.util.Date;
import java.util.List;

/**
 * ユーザに関するAPI
 * 
 * @since v0.2.0
 * @author fumokmm
 */
public class UserAPI extends EntityAPI {
	/** ユーザ情報 */
	private User user;

	/**
	 * コンストラクタ。（パッケージプライベート）
	 * 
	 * @since v0.2.0
	 */
	UserAPI() {
		super();
	}

	/**
	 * 初期化します。
	 * 
	 * @param user ユーザ情報
	 * @param apiAuth はてなハイクAPI（認証あり）
	 * @since v0.2.0
	 */
	void init(User user, HatenaHaikuAPI apiAuth) {
		this.user = user;
		this.apiAuth = apiAuth;
		this.apiLight = apiAuth;
	}

	/**
	 * 初期化します。
	 * 
	 * @param user ユーザ情報
	 * @param apiLight はてなハイクAPI（認証なし）
	 * @since v0.2.0
	 */
	void init(User user, HatenaHaikuAPILight apiLight) {
		this.user = user;
		this.apiLight = apiLight;
	}

	// ------------------以下、認証が不要なAPI

	/**
	 * このユーザのフレンドタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String)
	 * @return このユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline() throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId());
	}

	/**
	 * このユーザのフレンドタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return このユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline(int page) throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId(), page);
	}

	/**
	 * このユーザのフレンドタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return このユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId(), page, count);
	}

	/**
	 * このユーザのフレンドタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFriendsTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getFriendsTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * このユーザのユーザタイムラインを取得します。最新ページを20件取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String)
	 * @return このユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline() throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId());
	}

	/**
	 * このユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return このユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page) throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId(), page);
	}

	/**
	 * このユーザのユーザタイムラインを取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return このユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId(), page, count);
	}

	/**
	 * このユーザのユーザタイムラインを取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getUserTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getUserTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * このユーザの人気のユーザタイムラインを取得します。最新ページを20件取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String)
	 * @return このユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline() throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId());
	}

	/**
	 * このユーザの人気のユーザタイムラインを取得します。取得件数は20件です。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return このユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page) throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId(), page);
	}

	/**
	 * このユーザの人気のユーザタイムラインを取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return このユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId(), page, count);
	}

	/**
	 * このユーザの人気のユーザタイムラインを取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see HatenaHaikuAPILight#getHotUserTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getHotUserTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * このユーザのidページのタイムラインを取得します。最新ページを20件取得します。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String)
	 * @return このユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline() throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId());
	}

	/**
	 * このユーザのidページのタイムラインを取得します。取得件数は20件です。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return このユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page) throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId(), page);
	}

	/**
	 * このユーザのidページのタイムラインを取得します。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return このユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId(), page, count);
	}

	/**
	 * このユーザのidページのタイムラインを取得します。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 *
	 * @see HatenaHaikuAPILight#getIdTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getIdTimeline(user.getUserId(), page, count, since);
	}
	
	/**
	 * このユーザがフォローしているユーザのリストを100件取得します。（１ページ目）<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowingList(String)
	 * @return このユーザがフォローしているユーザのリスト（１ページ目）
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<User> getFollowingList() throws HatenaHaikuException {
		return apiLight.getFollowingList(user.getUserId());
	}

	/**
	 * このユーザがフォローしているユーザのリストを100件取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">ユーザID</font>.xml&page=<font color="red">ページ</font></i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowingList(String, int)
	 * @param page ページ
	 * @return このユーザがフォローしているユーザのリスト（指定ページ）
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<User> getFollowingList(int page) throws HatenaHaikuException {
		return apiLight.getFollowingList(user.getUserId(), page);
	}

	/**
	 * このユーザをフォローしているユーザのリストを取得します。<br/>
	 * フォロワーはページ指定できず、一気に全員分取得されるようです。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowersList(String)
	 * @return このユーザをフォローしているユーザのリスト
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<User> getFollowersList() throws HatenaHaikuException {
		return apiLight.getFollowingList(user.getUserId());
	}

	/**
	 * このユーザがフォローしているキーワードのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keywords/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getFollowingKeywordList(String)
	 * @return　このユーザがフォローしているキーワードリスト
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Keyword> getFollowingKeywordList() throws HatenaHaikuException {
		return apiLight.getFollowingKeywordList(user.getUserId());
	}
	
	// ------------------以下、認証が必要なAPI
	
	/**
	 * このユーザをフォローします。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/create/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#followUser(String)
	 * @return フォローしたこのユーザ情報
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public User follow() throws HatenaHaikuException {
		if (isAuth()) {
			User followed = apiAuth.followUser(user.getUserId());
			user.overwrite(followed);
			return user;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このユーザのフォローをやめます。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unfollowUser(String)
	 * @return フォローをやめたこのユーザ情報
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public User unfollow(String userId) throws HatenaHaikuException {
		if (isAuth()) {
			User unfollowed = apiAuth.unfollowUser(user.getUserId());
			user.overwrite(unfollowed);
			return user;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

}
