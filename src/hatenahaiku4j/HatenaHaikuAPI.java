package hatenahaiku4j;

import hatenahaiku4j.util.HttpUtil;
import hatenahaiku4j.util.XmlUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * はてなハイクAPIラッピングクラス（認証あり）
 * 
 * @see <a href="http://h.hatena.ne.jp/api">はてなハイクAPI</a>
 * @author fumokmm
 */
public class HatenaHaikuAPI extends HatenaHaikuAPIWithoutAuth {
	/** URL: フレンドタイムライン(XML) */
	protected static final String URL_FRIENDS_TIMELINE_XML			= "http://h.hatena.ne.jp/api/statuses/friends_timeline.xml";
	/** URL: ユーザタイムライン(XML) */
	protected static final String URL_USER_TIMELINE_XML				= "http://h.hatena.ne.jp/api/statuses/user_timeline.xml";
	/** URL: 投稿(XML) */
	protected static final String URL_UPDATE_STATUS_XML				= "http://h.hatena.ne.jp/api/statuses/update.xml";
	/** URL: エントリーを削除 */
	protected static final String URL_DELETE_STATUS					= "http://h.hatena.ne.jp/api/statuses/destroy/";
	/** URL: エントリーにスターを一つ追加 */
	protected static final String URL_ADD_STAR						= "http://h.hatena.ne.jp/api/favorites/create/";
	/** URL: エントリーのスターを一つ減らす */
	protected static final String URL_DELETE_STAR					= "http://h.hatena.ne.jp/api/favorites/destroy/";
	/** URL: ユーザがフォローしているユーザのリスト(XML) */
	protected static final String URL_FOLLOWING_LIST_XML			= "http://h.hatena.ne.jp/api/statuses/friends.xml";
	/** URL: ユーザをフォローしているユーザのリスト(XML) */
	protected static final String URL_FOLLOWERS_LIST_XML			= "http://h.hatena.ne.jp/api/statuses/followers.xml";
	/** URL: ユーザをフォローする */
	protected static final String URL_FOLLOW_USER					= "http://h.hatena.ne.jp/api/friendships/create/";
	/** URL: ユーザのフォローをやめる */
	protected static final String URL_UNFOLLOW_USER					= "http://h.hatena.ne.jp/api/friendships/destroy/";
	/** URL: ユーザがフォローしているキーワードのリスト(XML) */
	protected static final String URL_FOLLOWING_KEYWORD_LIST_XML	= "http://h.hatena.ne.jp/api/statuses/keywords.xml";
	/** URL: キーワードをフォローする */
	protected static final String URL_FOLLOW_KEYWORD				= "http://h.hatena.ne.jp/api/keywords/create/";
	/** URL: キーワードのフォローをやめる */
	protected static final String URL_UNFOLLOW_KEYWORD				= "http://h.hatena.ne.jp/api/keywords/destroy/";
	/** URL: 関連キーワードを設定(XML) */
	protected static final String URL_RELATE_KEYWORD_XML			= "http://h.hatena.ne.jp/api/keywords/relation/create.xml";
	/** URL: 関連キーワードを解除(XML) */
	protected static final String URL_UNRELATE_KEYWORD_XML			= "http://h.hatena.ne.jp/api/keywords/relation/destroy.xml";

	/** ログインユーザ */
	private LoginUser loginUser;
	
	/**
	 * コンストラクタ。
	 * 
	 * @param loginUser ログインユーザ
	 */
	public HatenaHaikuAPI(LoginUser loginUser) {
		this.changeLoginUser(loginUser);
	}

	/**
	 * ログインユーザを切り替えます。
	 * 
	 * @param loginUser ログインユーザ
	 */
	public void changeLoginUser(LoginUser loginUser) {
		if (loginUser == null) this.loginUser = LoginUser.NULL;
		else this.loginUser = loginUser;
	}
	
	//-------------------------------------------------------------
	// statuses/timeline
	//-------------------------------------------------------------

	/**
	 * 認証したユーザのフレンドタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @return 認証したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline() throws HatenaHaikuException {
		return getFriendsTimeline(0, 0, null);
	}

	/**
	 * 認証したユーザのフレンドタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @return 認証したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(int page) throws HatenaHaikuException {
		return getFriendsTimeline(page, 0, null);
	}

	/**
	 * 認証したユーザのフレンドタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 認証したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(int page, int count) throws HatenaHaikuException {
		return getFriendsTimeline(page, count, null);
	}

	/**
	 * 認証したユーザのフレンドタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 認証したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(loginUser, URL_FRIENDS_TIMELINE_XML, param);
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}
	
	/**
	 * 認証したユーザのユーザタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @return 認証したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline() throws HatenaHaikuException {
		return getUserTimeline(0, 0, null);
	}

	/**
	 * 認証したユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @return 認証したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(int page) throws HatenaHaikuException {
		return getUserTimeline(page, 0, null);
	}

	/**
	 * 認証したユーザのユーザタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 認証したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(int page, int count) throws HatenaHaikuException {
		return getUserTimeline(page, count, null);
	}

	/**
	 * 認証したユーザのユーザタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 認証したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(loginUser, URL_USER_TIMELINE_XML, param);
			return toStatusList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}
	
	//-------------------------------------------------------------
	// ステータス関係
	//-------------------------------------------------------------

	/**
	 * はてなハイクに投稿します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/update.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-update">statuses/update</a>
	 * @param params 更新パラメータ
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException 
	 */
	protected Status updateStatus(UpdateParameter param) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doPost(loginUser, URL_UPDATE_STATUS_XML, param);
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * 新しくエントリを投稿します。（ログインユーザのプロフィールページに投稿されます）
	 * 
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 */
	public Status entry(String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setText(text);
		return updateStatus(params);
	}

	/**
	 * 新しくエントリを投稿します。
	 * 
	 * @param keyword キーワード
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 */
	public Status entry(String keyword, String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setKeyword(keyword);
		params.setText(text);
		return updateStatus(params);
	}

	/**
	 * 新しくエントリを投稿します。画像付きでエントリします。
	 * 
	 * @param text 投稿内容
	 * @param file 画像ファイル
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 */
	public Status entry(String keyword, String text, File file) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setKeyword(keyword);
		params.setText(text);
		params.setFile(file);
		return updateStatus(params);
	}
	
	/**
	 * 新しく返信エントリを投稿します。
	 * 
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 */
	public Status reply(String inReplyToStatusId, String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setInReplyToStatusId(inReplyToStatusId);
		params.setText(text);
		return updateStatus(params);
	}

	/**
	 * 新しく返信エントリを投稿します。画像付きでエントリします。
	 * 
	 * @param text 投稿内容
	 * @param file 画像ファイル
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 */
	public Status reply(String inReplyToStatusId, String text, File file) throws HatenaHaikuException {
		throw new APINotSupportedException();
	}

	/**
	 * 指定したステータスIDのエントリーを削除します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-destroy">statuses/destroy</a>
	 * @param statusId ステータスID
	 * @return 削除したステータス情報
	 * @throws HatenaHaikuException 
	 */
	public Status deleteEntry(String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_DELETE_STATUS + statusId + Const.EXT_XML, null);
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}
	
	//-------------------------------------------------------------
	// スター関係
	//-------------------------------------------------------------

	/**
	 * 指定したエントリーにスターを一つ追加します。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/create/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-create">favorites/create</a>
	 * @param statusId ステータスID
	 * @return スターを一つ追加した結果のステータス情報
	 * @throws HatenaHaikuException 
	 */
	public Status addStar(String statusId) throws HatenaHaikuException {
		return modifyStar(true, statusId);
	}

	/**
	 * 指定したエントリーのスターを一つ減らします。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-destroy">favorites/destroy</a>
	 * @param statusId ステータスID
	 * @return スターを一つ減らした結果のステータス情報
	 * @throws HatenaHaikuException 
	 */
	public Status deleteStar(String statusId) throws HatenaHaikuException {
		return modifyStar(false, statusId);
	}
	
	/**
	 * 指定したエントリーのスターを一つ増やしたり減らしたりします。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-destroy">favorites/destroy</a>
	 * @param isAdd true:一つ増やす／false:一つ減らす
	 * @param statusId ステータスID
	 * @return スターを一つ増やしたり減らしたりした結果のステータス情報
	 * @throws HatenaHaikuException 
	 */
	private Status modifyStar(boolean isAdd, String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isAdd ? URL_ADD_STAR : URL_DELETE_STAR) + statusId + Const.EXT_XML, null);
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	//-------------------------------------------------------------
	// ユーザ／フォロー関係
	//-------------------------------------------------------------

	/**
	 * 認証したユーザがフォローしているユーザのリストを100件取得します。（１ページ目）<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @return 認証したユーザがフォローしているユーザのリスト（１ページ目）
	 * @throws HatenaHaikuException 
	 */
	public List<User> getFollowingList() throws HatenaHaikuException {
		return getFollowingList(1);
	}

	/**
	 * 認証したユーザがフォローしているユーザのリストを100件取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends.xml&page=<font color="red">ページ</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @param page ページ
	 * @return 認証したユーザがフォローしているユーザのリスト（指定ページ）
	 * @throws HatenaHaikuException 
	 */
	public List<User> getFollowingList(int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWING_LIST_XML, param);
			return toUserList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * 認証したユーザをフォローしているユーザのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-followers">statuses/followers</a>
	 * @return 認証したユーザをフォローしているユーザのリスト
	 * @throws HatenaHaikuException 
	 */
	public List<User> getFollowersList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWERS_LIST_XML, null);
			return toUserList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * ユーザをフォローします。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/create/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-create">friendships/create</a>
	 * @param userId ユーザID
	 * @return フォローしたユーザ情報
	 * @throws HatenaHaikuException 
	 */
	public User followUser(String userId) throws HatenaHaikuException {
		return modifyFollowUser(true, userId);
	}

	/**
	 * ユーザのフォローをやめます。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-destroy">friendships/destroy</a>
	 * @param userId ユーザID
	 * @return フォローをやめたユーザ情報
	 * @throws HatenaHaikuException 
	 */
	public User unfollowUser(String userId) throws HatenaHaikuException {
		return modifyFollowUser(false, userId);
	}
	
	/**
	 * ユーザのフォローしたりやめたりします。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-destroy">friendships/destroy</a>
	 * @param isFollow true:フォローする／false:フォローをやめる
	 * @param userId ユーザID
	 * @return フォローしたりやめたりしたユーザ情報
	 * @throws HatenaHaikuException 
	 */
	private User modifyFollowUser(boolean isFollow, String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isFollow ? URL_FOLLOW_USER : URL_UNFOLLOW_USER) + userId + Const.EXT_XML, null);
			return toUser(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	//-------------------------------------------------------------
	// キーワード関係
	//-------------------------------------------------------------

	/**
	 * 認証したユーザがフォローしているキーワードのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keywords.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keywords">statuses/keywords</a>
	 * @return　認証したユーザがフォローしているキーワードリスト
	 * @throws HatenaHaikuException
	 */
	public List<Keyword> getFollowingKeywordList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWING_KEYWORD_LIST_XML, null);
			return toKeywordList(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * キーワードをフォローします。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/create/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-create">keywords/create</a>
	 * @param keyword キーワード
	 * @return フォローしたキーワード情報
	 * @throws HatenaHaikuException
	 */
	public Keyword followKeyword(String keyword) throws HatenaHaikuException {
		return modifyFollowKeyword(true, keyword);
	}

	/**
	 * キーワードのフォローをやめます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-destroy">keywords/destroy</a>
	 * @param keyword キーワード
	 * @return フォローをやめたキーワード情報
	 * @throws HatenaHaikuException
	 */
	public Keyword unfollowKeyword(String keyword) throws HatenaHaikuException {
		return modifyFollowKeyword(false, keyword);
	}

	/**
	 * キーワードのフォローしたり、やめたりします。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-destroy">keywords/destroy</a>
	 * @param isFollow true:フォローする／false:フォローをやめる
	 * @param keyword キーワード
	 * @return フォローした／やめたキーワード情報
	 * @throws HatenaHaikuException
	 */
	private Keyword modifyFollowKeyword(boolean isFollow, String keyword) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isFollow ? URL_FOLLOW_KEYWORD : URL_UNFOLLOW_KEYWORD) + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, null);
			return toKeyword(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * 関連キーワードを設定します。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/create.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-relation-create">keywords/relation/create</a>
	 * @param keyword1 設定する対象のキーワード１
	 * @param keyword2 設定する対象のキーワード２
	 * @return 関連キーワードを設定後のキーワード１のキーワード情報
	 * @throws HatenaHaikuException
	 */
	public Keyword relateKeyword(String keyword1, String keyword2) throws HatenaHaikuException {
		return modifyRelateKeyword(true, keyword1, keyword2);
	}

	/**
	 * 関連キーワードを解除します。<br/>
	 * 関連キーワードの設定の削除は自分が設定したものに限られます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/destroy.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-relation-destroy">keywords/relation/destroy</a>
	 * @param keyword1 解除する対象のキーワード１
	 * @param keyword2 解除する対象のキーワード２
	 * @return 関連キーワードを解除後のキーワード１のキーワード情報
	 * @throws HatenaHaikuException
	 */
	public Keyword unrelateKeyword(String keyword1, String keyword2) throws HatenaHaikuException {
		return modifyRelateKeyword(false, keyword1, keyword2);
	}
		
	/**
	 * 関連キーワード設定／解除
	 * 
	 * @param isRelate true:設定／false:解除
	 * @param keyword1 対象キーワード１
	 * @param keyword2 対象キーワード２
	 * @return 関連キーワードを設定／解除後のキーワード１のキーワード情報
	 * @throws HatenaHaikuException
	 */
	private Keyword modifyRelateKeyword(boolean isRelate, String keyword1, String keyword2) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setWord1(keyword1);
			param.setWord2(keyword2);
			String resultXml = HttpUtil.doGet(loginUser, (isRelate ? URL_RELATE_KEYWORD_XML : URL_UNRELATE_KEYWORD_XML), param);
			return toKeyword(XmlUtil.getRootElement(resultXml));

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("UnsupportedEncodingException発生。", e);

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}
}
