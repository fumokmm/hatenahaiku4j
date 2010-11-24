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
 * はてなハイクAPIラッピングクラス（認証あり）
 * 
 * @see <a href="http://h.hatena.ne.jp/api">はてなハイクAPI</a>
 * @since v0.0.1
 * @author fumokmm
 */
public class HatenaHaikuAPI extends HatenaHaikuAPILight {
	/** URL: フレンドタイムライン(XML) */
	protected static final String URL_FRIENDS_TIMELINE_XML			= "http://h.hatena.ne.jp/api/statuses/friends_timeline.xml";
	/** URL: ユーザタイムライン(XML) */
	protected static final String URL_USER_TIMELINE_XML				= "http://h.hatena.ne.jp/api/statuses/user_timeline.xml";
	/** URL: 投稿(XML) */
	protected static final String URL_UPDATE_STATUS_XML				= "http://h.hatena.ne.jp/api/statuses/update.xml";
	/** URL: エントリを削除 */
	protected static final String URL_DELETE_STATUS					= "http://h.hatena.ne.jp/api/statuses/destroy/";
	/** URL: エントリにスターを一つ追加 */
	protected static final String URL_ADD_STAR						= "http://h.hatena.ne.jp/api/favorites/create/";
	/** URL: エントリのスターを一つ減らす */
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
	 * 認証したユーザのフレンドタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @return 認証したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
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
	 * @since v0.0.1
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
	 * @since v0.0.1
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
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline() throws HatenaHaikuException {
		return _getUserTimeline(0, 0, null, false);
	}

	/**
	 * 認証したユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @return 認証したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(int page) throws HatenaHaikuException {
		return _getUserTimeline(page, 0, null, false);
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
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(int page, int count) throws HatenaHaikuException {
		return _getUserTimeline(page, count, null, false);
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
	 * @since v0.0.1
	 */
	public List<Status> getUserTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return _getUserTimeline(page, count, since, false);
	}

	/**
	 * 認証したユーザの人気のユーザタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @return 認証したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline() throws HatenaHaikuException {
		return _getUserTimeline(0, 0, null, true);
	}

	/**
	 * 認証したユーザの人気のユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @return 認証したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline(int page) throws HatenaHaikuException {
		return _getUserTimeline(page, 0, null, true);
	}

	/**
	 * 認証したユーザの人気のユーザタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 認証したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline(int page, int count) throws HatenaHaikuException {
		return _getUserTimeline(page, count, null, true);
	}

	/**
	 * 認証したユーザの人気のユーザタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 認証したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotUserTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return _getUserTimeline(page, count, since, true);
	}
	
	/**
	 * 認証したユーザのユーザタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/user_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @param isHot 人気順取得用かどうか
	 * @return 認証したユーザのユーザタイムライン
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
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}
	
	/**
	 * 認証したユーザのidページのタイムラインを取得します。最新ページを20件取得します。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @return 認証したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline() throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), 0, 0, null);
	}

	/**
	 * 認証したユーザのidページのタイムラインを取得します。取得件数は20件です。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return 認証したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page) throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), page, 0, null);
	}

	/**
	 * 認証したユーザのidページのタイムラインを取得します。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 認証したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count) throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), page, count, null);
	}

	/**
	 * 認証したユーザのidページのタイムラインを取得します。<br/>
	 * このタイムラインは "id:xxxx" のキーワードタイムラインと同じものです。
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 認証したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getIdTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return getKeywordTimeline(loginUser.getUserIdNotation(), page, count, since);
	}
	
	//-------------------------------------------------------------
	// ステータス関係
	//-------------------------------------------------------------

	/**
	 * 新しくエントリを投稿します。（ログインユーザのプロフィールページに投稿されます）
	 * 
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Status entry(String text) throws HatenaHaikuException {
		UpdateParameter params = new UpdateParameter();
		params.setText(text);
		return _updateStatus(params);
	}

	/**
	 * 新しくエントリを投稿します。
	 * 
	 * @param keyword キーワード
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
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
	 * 新しくエントリを投稿します。<br/>
	 * 画像付きでエントリします。
	 * 
	 * @param keyword キーワード
	 * @param text 投稿内容
	 * @param file 画像ファイル
	 * @return 投稿結果のステータス情報
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
	 * 新しくエントリを投稿します。<br/>
	 * URL上にある画像付きでエントリします。
	 * 
	 * @param keyword キーワード
	 * @param text 投稿内容
	 * @param imageUrl 画像のURL
	 * @return 投稿結果のステータス情報
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
	 * 新しくエントリを投稿します。<br/>
	 * 画像のバイナリデータとその拡張子を指定し、画像付きでエントリします。
	 * 
	 * @param keyword キーワード
	 * @param text 投稿内容
	 * @param imageData 画像のバイナリデータ
	 * @param imageDataExt 画像の拡張子
	 * @return 投稿結果のステータス情報
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
	 * 新しく返信エントリを投稿します。
	 * 
	 * @param inReplyToStatusId 返信元ステータスID
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
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
	 * 新しく返信エントリを投稿します。<br/>
	 * 画像付きでエントリします。
	 * 
	 * @param inReplyToStatusId 返信元ステータスID
	 * @param text 投稿内容
	 * @param file 画像ファイル
	 * @return 投稿結果のステータス情報
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
	 * 新しく返信エントリを投稿します。<br/>
	 * URL上にある画像付きでエントリします。
	 * 
	 * @param inReplyToStatusId 返信元ステータスID
	 * @param text 投稿内容
	 * @param imageUrl 画像のURL
	 * @return 投稿結果のステータス情報
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
	 * 新しく返信エントリを投稿します。<br/>
	 * 画像のバイナリデータとその拡張子を指定し、画像付きでエントリします。
	 * 
	 * @param inReplyToStatusId 返信元ステータスID
	 * @param text 投稿内容
	 * @param imageData 画像のバイナリデータ
	 * @param imageDataExt 画像の拡張子
	 * @return 投稿結果のステータス情報
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
	 * はてなハイクに投稿します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/update.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-update">statuses/update</a>
	 * @param params 更新パラメータ
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	private Status _updateStatus(UpdateParameter param) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doPost(loginUser, URL_UPDATE_STATUS_XML, param, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * 指定したステータスIDのエントリを削除します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-destroy">statuses/destroy</a>
	 * @param statusId ステータスID
	 * @return 削除したステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public Status deleteEntry(String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_DELETE_STATUS + statusId + Const.EXT_XML, null, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

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
	 * 指定したエントリにスターを一つ追加します。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/create/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-create">favorites/create</a>
	 * @param statusId ステータスID
	 * @return スターを一つ追加した結果のステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public Status addStar(String statusId) throws HatenaHaikuException {
		return _modifyStar(true, statusId);
	}

	/**
	 * 指定したエントリのスターを一つ減らします。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-destroy">favorites/destroy</a>
	 * @param statusId ステータスID
	 * @return スターを一つ減らした結果のステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public Status deleteStar(String statusId) throws HatenaHaikuException {
		return _modifyStar(false, statusId);
	}
	
	/**
	 * 指定したエントリのスターを一つ増やしたり減らしたりします。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#favorites-destroy">favorites/destroy</a>
	 * @param isAdd true:一つ増やす／false:一つ減らす
	 * @param statusId ステータスID
	 * @return スターを一つ増やしたり減らしたりした結果のステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	private Status _modifyStar(boolean isAdd, String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isAdd ? URL_ADD_STAR : URL_DELETE_STAR) + statusId + Const.EXT_XML, null, isNeedHttpLog());
			return toStatus(XmlUtil.getRootElement(resultXml));

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
	 * @since v0.0.1
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
	 * @since v0.0.1
	 */
	public List<User> getFollowingList(int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWING_LIST_XML, param, isNeedHttpLog());
			return toUserList(XmlUtil.getRootElement(resultXml));

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
	 * フォロワーはページ指定できず、一気に全員分取得されるようです。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-followers">statuses/followers</a>
	 * @return 認証したユーザをフォローしているユーザのリスト
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public List<User> getFollowersList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWERS_LIST_XML, null, isNeedHttpLog());
			return toUserList(XmlUtil.getRootElement(resultXml));

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
	 * @since v0.0.1
	 */
	public User followUser(String userId) throws HatenaHaikuException {
		return _modifyFollowUser(true, userId);
	}

	/**
	 * ユーザのフォローをやめます。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/destroy/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-destroy">friendships/destroy</a>
	 * @param userId ユーザID
	 * @return フォローをやめたユーザ情報
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public User unfollowUser(String userId) throws HatenaHaikuException {
		return _modifyFollowUser(false, userId);
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
	 * @since v0.0.1
	 */
	private User _modifyFollowUser(boolean isFollow, String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isFollow ? URL_FOLLOW_USER : URL_UNFOLLOW_USER) + userId + Const.EXT_XML, null, isNeedHttpLog());
			return toUser(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}
	
	/**
	 * ログインユーザのユーザ情報を取得します。<br/>
	 * 
	 * @see HatenaHaikuAPILight#getUser(String)
	 * @return ログインユーザのユーザ情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public User getMe() throws HatenaHaikuException {
		return getUser(loginUser.getUserId());
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
	 * @since v0.0.1
	 */
	public List<Keyword> getFollowingKeywordList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, URL_FOLLOWING_KEYWORD_LIST_XML, null, isNeedHttpLog());
			return toKeywordList(XmlUtil.getRootElement(resultXml));

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
	 * @since v0.0.1
	 */
	public Keyword followKeyword(String keyword) throws HatenaHaikuException {
		return _modifyFollowKeyword(true, keyword);
	}

	/**
	 * キーワードのフォローをやめます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-destroy">keywords/destroy</a>
	 * @param keyword キーワード
	 * @return フォローをやめたキーワード情報
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	public Keyword unfollowKeyword(String keyword) throws HatenaHaikuException {
		return _modifyFollowKeyword(false, keyword);
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
	 * @since v0.0.1
	 */
	private Keyword _modifyFollowKeyword(boolean isFollow, String keyword) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(loginUser, (isFollow ? URL_FOLLOW_KEYWORD : URL_UNFOLLOW_KEYWORD) + StringUtil.encode(keyword) + Const.EXT_XML, null, isNeedHttpLog());
			return toKeyword(XmlUtil.getRootElement(resultXml));

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
	 * @since v0.0.1
	 */
	public Keyword relateKeyword(String keyword1, String keyword2) throws HatenaHaikuException {
		return _modifyRelateKeyword(true, keyword1, keyword2);
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
	 * @since v0.0.1
	 */
	public Keyword unrelateKeyword(String keyword1, String keyword2) throws HatenaHaikuException {
		return _modifyRelateKeyword(false, keyword1, keyword2);
	}
		
	/**
	 * 関連キーワード設定／解除
	 * 
	 * @param isRelate true:設定／false:解除
	 * @param keyword1 対象キーワード１
	 * @param keyword2 対象キーワード２
	 * @return 関連キーワードを設定／解除後のキーワード１のキーワード情報
	 * @throws HatenaHaikuException
	 * @since v0.0.1
	 */
	private Keyword _modifyRelateKeyword(boolean isRelate, String keyword1, String keyword2) throws HatenaHaikuException {
		if (StringUtil.isSame(keyword1, keyword2)) {
			throw new HatenaHaikuException("対象キーワードに同じキーワードは指定できません。");
		}
		try {
			QueryParameter param = new QueryParameter();
			param.setWord1(keyword1);
			param.setWord2(keyword2);
			String resultXml = HttpUtil.doGet(loginUser, (isRelate ? URL_RELATE_KEYWORD_XML : URL_UNRELATE_KEYWORD_XML), param, isNeedHttpLog());
			return toKeyword(XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * 認証したユーザのidページのキーワード情報を取得します。<br/>
	 * 
	 * @see HatenaHaikuAPILight#getKeyword(String)
	 * @return 認証したユーザのidページのキーワード情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword getMyKeyword() throws HatenaHaikuException {
		return getKeyword(loginUser.getUserIdNotation());
	}

	//----------------------------------
	// 以下、変換メソッド
	//----------------------------------
	
	/**
	 * ステータス情報のインスタンスを取得します。
	 * 
	 * @since v0.2.0
	 */
	@Override
	protected Status createStatus() {
		return Status.create(this);
	}

	/**
	 * ユーザ情報のインスタンスを取得します。
	 * 
	 * @since v0.2.0
	 */
	@Override
	protected User createUser() {
		return User.create(this);
	}
	
	/**
	 * キーワード情報のインスタンスを取得します。
	 * 
	 * @since v0.2.0
	 */
	@Override
	protected Keyword createKeyword() {
		return Keyword.create(this);
	}
	
}
