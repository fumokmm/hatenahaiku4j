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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * はてなハイクAPIラッピングクラス（認証なし）
 * 
 * @see <a href="http://h.hatena.ne.jp/api">はてなハイクAPI</a>
 * @author fumokmm
 */
public class HatenaHaikuAPIWithoutAuth {
	/** URL: パブリックタイムライン(XML) */
	protected static final String URL_PUBLIC_TIMELINE_XML		= "http://h.hatena.ne.jp/api/statuses/public_timeline.xml";
	/** URL: フレンドタイムライン */
	protected static final String URL_FRIENDS_TIMELINE			= "http://h.hatena.ne.jp/api/statuses/friends_timeline/";
	/** URL: ユーザタイムライン */
	protected static final String URL_USER_TIMELINE				= "http://h.hatena.ne.jp/api/statuses/user_timeline/";
	/** URL: キーワードタイムライン */
	protected static final String URL_KEYWORD_TIMELINE			= "http://h.hatena.ne.jp/api/statuses/keyword_timeline/";
	/** URL: アルバムタイムライン(XML) */
	protected static final String URL_ALBUM_TIMELINE_XML		= "http://h.hatena.ne.jp/api/statuses/album.xml";
	/** URL: キーワードタイムライン */
	protected static final String URL_ALBUM_TIMELINE			= "http://h.hatena.ne.jp/api/statuses/album/";
	/** URL: ステータス情報 */
	protected static final String URL_STATUS					= "http://h.hatena.ne.jp/api/statuses/show/";
	/** URL: ユーザがフォローしているユーザのリスト */
	protected static final String URL_FOLLOWING_LIST			= "http://h.hatena.ne.jp/api/statuses/friends/";
	/** URL: ユーザをフォローしているユーザのリスト */
	protected static final String URL_FOLLOWERS_LIST			= "http://h.hatena.ne.jp/api/statuses/followers/";
	/** URL: ユーザ情報 */
	protected static final String URL_USER						= "http://h.hatena.ne.jp/api/friendships/show/";
	/** URL: ホットキーワードのリスト(XML) */
	protected static final String URL_HOT_KEYWORD_LIST_XML		= "http://h.hatena.ne.jp/api/keywords/hot.xml";
	/** URL: キーワードのリスト(XML) */
	protected static final String URL_KEYWORD_LIST_XML			= "http://h.hatena.ne.jp/api/keywords/list.xml";
	/** URL: ユーザがフォローしているキーワードのリスト */
	protected static final String URL_FOLLOWING_KEYWORD_LIST	= "http://h.hatena.ne.jp/api/statuses/keywords/";
	/** URL: キーワード情報 */
	protected static final String URL_KEYWORD					= "http://h.hatena.ne.jp/api/keywords/show/";

	/**
	 * コンストラクタ。
	 */
	public HatenaHaikuAPIWithoutAuth() {}
	
	//-------------------------------------------------------------
	// タイムライン関係
	//-------------------------------------------------------------

	/**
	 * パブリックタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getPublicTimeline() throws HatenaHaikuException {
		return getPublicTimeline(0, 0, null);
	}

	/**
	 * パブリックタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getPublicTimeline(int page) throws HatenaHaikuException {
		return getPublicTimeline(page, 0, null);
	}
	/**
	 * パブリックタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getPublicTimeline(int page, int count) throws HatenaHaikuException {
		return getPublicTimeline(page, count, null);
	}
	/**
	 * パブリックタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/public_timeline.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-public_timeline">statuses/public_timeline</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getPublicTimeline(int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_PUBLIC_TIMELINE_XML, param);
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
	 * 指定したユーザのフレンドタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ユーザID
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(String userId) throws HatenaHaikuException {
		return getFriendsTimeline(userId, 0, 0, null);
	}

	/**
	 * 指定したユーザのフレンドタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ユーザID
	 * @param page 取得するページです。最大数は100です。
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(String userId, int page) throws HatenaHaikuException {
		return getFriendsTimeline(userId, page, 0, null);
	}

	/**
	 * 指定したユーザのフレンドタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ユーザID
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return getFriendsTimeline(userId, page, count, null);
	}

	/**
	 * 指定したユーザのフレンドタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends_timeline/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends_timeline">statuses/friends_timeline</a>
	 * @param userId ユーザID
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 */
	public List<Status> getFriendsTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_FRIENDS_TIMELINE + userId + Const.EXT_XML, param);
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
	 * 指定したユーザのユーザタイムラインを取得します。最新ページを20件取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ユーザID
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(String userId) throws HatenaHaikuException {
		return getUserTimeline(userId, 0, 0, null);
	}

	/**
	 * 指定したユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ユーザID
	 * @param page 取得するページです。最大数は100です。
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(String userId, int page) throws HatenaHaikuException {
		return getUserTimeline(userId, page, 0, null);
	}

	/**
	 * 指定したユーザのユーザタイムラインを取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ユーザID
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(String userId, int page, int count) throws HatenaHaikuException {
		return getUserTimeline(userId, page, count, null);
	}

	/**
	 * 指定したユーザのユーザタイムラインを取得します。<br/>
	 *　<i>http://h.hatena.ne.jp/api/statuses/user_timeline/<font color="red">ユーザID</font>.xml</i>
	 *
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-user_timeline">statuses/user_timeline</a>
	 * @param userId ユーザID
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getUserTimeline(String userId, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_USER_TIMELINE + userId + Const.EXT_XML, param);
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
	 * 指定したキーワードのキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword キーワード
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getKeywordTimeline(String keyword) throws HatenaHaikuException {
		return getKeywordTimeline(keyword, 0, 0, null);
	}

	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword キーワード
	 * @param page 取得するページです。最大数は100です。
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getKeywordTimeline(String keyword, int page) throws HatenaHaikuException {
		return getKeywordTimeline(keyword, page, 0, null);
	}

	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword キーワード
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getKeywordTimeline(String keyword, int page, int count) throws HatenaHaikuException {
		return getKeywordTimeline(keyword, page, count, null);
	}

	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keyword_timeline">statuses/keyword_timeline</a>
	 * @param keyword キーワード
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getKeywordTimeline(String keyword, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_KEYWORD_TIMELINE + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, param);
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
	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @return 画像を含む最新のエントリのパブリックタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline() throws HatenaHaikuException {
		return getAlbumTimeline(0, 0, null);
	}

	/**
	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param page 取得するページです。最大数は100です。
	 * @return 画像を含む最新のエントリのパブリックタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(int page) throws HatenaHaikuException {
		return getAlbumTimeline(page, 0, null);
	}

	/**
	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 画像を含む最新のエントリのパブリックタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(int page, int count) throws HatenaHaikuException {
		return getAlbumTimeline(page, count, null);
	}

	/**
	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 画像を含む最新のエントリのパブリックタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_ALBUM_TIMELINE_XML, param);
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
	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(String keyword) throws HatenaHaikuException {
		return getAlbumTimeline(keyword, 0, 0, null);
	}

	/**
	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(String keyword, int page) throws HatenaHaikuException {
		return getAlbumTimeline(keyword, page, 0, null);
	}

	/**
	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(String keyword, int page, int count) throws HatenaHaikuException {
		return getAlbumTimeline(keyword, page, count, null);
	}

	/**
	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-album">statuses/album</a>
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 */
	public List<Status> getAlbumTimeline(String keyword, int page, int count, Date since) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			param.setCount(count);
			param.setSince(since);
			String resultXml = HttpUtil.doGet(URL_ALBUM_TIMELINE + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, param);
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
	 * 指定したステータス情報を取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/show/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-show">statuses/show</a>
	 * @param statusId ステータスID
	 * @return ステータス情報
	 * @throws HatenaHaikuException
	 */
	public Status getStatus(String statusId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_STATUS + statusId + Const.EXT_XML, null);
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

	//-------------------------------------------------------------
	// ユーザ／フォロー関係
	//-------------------------------------------------------------

	/**
	 * 指定したユーザがフォローしているユーザのリストを100件取得します。（１ページ目）<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @param userId ユーザID
	 * @return 指定したユーザがフォローしているユーザのリスト（１ページ目）
	 * @throws HatenaHaikuException
	 */
	public List<User> getFollowingList(String userId) throws HatenaHaikuException {
		return getFollowingList(userId, 0);
	}

	/**
	 * 指定したユーザがフォローしているユーザのリストを100件取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/friends/<font color="red">ユーザID</font>.xml&page=<font color="red">ページ</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-friends">statuses/friends</a>
	 * @param userId ユーザID
	 * @param page ページ
	 * @return 指定したユーザがフォローしているユーザのリスト（指定ページ）
	 * @throws HatenaHaikuException
	 */
	public List<User> getFollowingList(String userId, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setPage(page);
			String resultXml = HttpUtil.doGet(URL_FOLLOWING_LIST + userId + Const.EXT_XML, param);
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
	 * 指定したユーザをフォローしているユーザのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/followers/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-followers">statuses/followers</a>
	 * @param userId ユーザID
	 * @return 指定したユーザをフォローしているユーザのリスト
	 * @throws HatenaHaikuException
	 */
	public List<User> getFollowersList(String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_FOLLOWERS_LIST + userId + Const.EXT_XML, null);
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
	 * 指定したユーザ情報を取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/show/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#friendships-show">friendships/show</a>
	 * @param userId ユーザID
	 * @return 指定したユーザ情報
	 * @throws HatenaHaikuException
	 */
	public User getUser(String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_USER + userId + Const.EXT_XML, null);
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
	 * ホットキーワードのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/hot.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/hotkeywords">注目キーワード</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-hot">keywords/hot</a>
	 * @return ホットキーワードのリスト
	 * @throws HatenaHaikuException
	 */
	public List<Keyword> getHotKeywordList() throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_HOT_KEYWORD_LIST_XML, null);
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
	 * キーワードリストを取得します。（１ページ目）<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/list.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-list">keywords/list</a>
	 * @return キーワードリスト（１ページ目）
	 * @throws HatenaHaikuException
	 */
	public List<Keyword> getKeywordList() throws HatenaHaikuException {
		return getKeywordList(null, 1);
	}

	/**
	 * キーワードリストを取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/list.xml?page=<font color="red">ページ</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-list">keywords/list</a>
	 * @param page ページ
	 * @return　キーワードリスト（指定ページ）
	 * @throws HatenaHaikuException
	 */
	public List<Keyword> getKeywordList(int page) throws HatenaHaikuException {
		return getKeywordList(null, page);
	}

	/**
	 * 指定したワードに部分一致するキーワードリストを取得します。（指定ページ）<br/>
	 * パラメータに?word=00, page=1などが使える。<br/>
	 * pageの最大数が100とAPI解説ページに書かれているが、それ以上でも取得できるようである。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/list.xml?word=<font color="red">検索ワード</font>&page=<font color="red">ページ</font></i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-list">keywords/list</a>
	 * @param searchWord 検索ワード
	 * @param page ページ
	 * @return　キーワードリスト（指定ページ）
	 * @throws HatenaHaikuException
	 */
	public List<Keyword> getKeywordList(String searchWord, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter();
			param.setWord(searchWord);
			param.setPage(page);
			String resultXml = HttpUtil.doGet(URL_KEYWORD_LIST_XML, param);
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
	 * 指定したユーザがフォローしているキーワードのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keywords/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#statuses-keywords">statuses/keywords</a>
	 * @param userId ユーザID
	 * @return　指定したユーザがフォローしているキーワードリスト
	 * @throws HatenaHaikuException
	 */
	public List<Keyword> getFollowingKeywordList(String userId) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_FOLLOWING_KEYWORD_LIST + userId + Const.EXT_XML, null);
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
	 * 指定したキーワード情報を取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/show/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/api#keywords-show">keywords/show</a>
	 * @param keyword キーワード
	 * @return 指定したキーワード情報
	 * @throws HatenaHaikuException
	 */
	public Keyword getKeyword(String keyword) throws HatenaHaikuException {
		try {
			String resultXml = HttpUtil.doGet(URL_KEYWORD + URLEncoder.encode(keyword, Const.UTF8) + Const.EXT_XML, null);
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
	
	//----------------------------------
	// 以下、変換メソッド
	//----------------------------------
	
	/**
	 * ステータスエレメントをStatus情報に変換します。
	 * 
	 * @param elemStatus ステータスエレメント
	 * @return Status情報
	 */
	protected Status toStatus(Element elemStatus) throws HatenaHaikuException {
		try {
			Status status = new Status();
			// ステータスID
			status.setStatusId(XmlUtil.getText(elemStatus, "id"));
			// 作成日時
			status.setCreatedAt(DateUtil.toJSTDate(XmlUtil.getText(elemStatus, "created_at")));
			// お気に入られ
			status.setFavorited(Integer.parseInt(XmlUtil.getText(elemStatus, "favorited")));
			// 返信先ステータスID
			status.setInReplyToStatusId(XmlUtil.getText(elemStatus, "in_reply_to_status_id"));
			// 返信先ユーザID
			status.setInReplyToUserId(XmlUtil.getText(elemStatus, "in_reply_to_user_id"));
			// キーワード
			status.setKeyword(XmlUtil.getText(elemStatus, "keyword"));
			// リンク
			status.setLink(XmlUtil.getText(elemStatus, "link"));
			// ソース（クライアント名）
			status.setSource(XmlUtil.getText(elemStatus, "source"));
			// 投稿内容
			if (status.isIdPage()) {
				status.setText(XmlUtil.getText(elemStatus, "text"));
			} else {
				// idページでない場合、textには「キーワード=」が頭についているので、取り除く
				status.setText(XmlUtil.getText(elemStatus, "text").substring(status.getKeyword().length() + 1));
			}
			// ユーザ情報
			status.setUser(toUser(XmlUtil.getFirstChildElement(elemStatus, "user")));
			return status;
		} catch (ParseException e) {
			throw new HatenaHaikuException("", e);
		}
	}
	
	/**
	 * ステータスエレメントルートをStatus情報リストに変換します。
	 * 
	 * @param elemStatuses ステータスエレメントルート
	 * @return Status情報リスト
	 */
	protected List<Status> toStatusList(Element elemStatuses) throws HatenaHaikuException {
		List<Status> statusList = new ArrayList<Status>();
		NodeList elemStatusList = elemStatuses.getElementsByTagName("status");
		for (int i = 0; i < elemStatusList.getLength(); i++) {
			statusList.add(toStatus((Element)elemStatusList.item(i)));
		}
		return statusList;
	}

	/**
	 * ユーザエレメントをUser情報に変換します。
	 * 
	 * @param elemUser ユーザエレメント
	 * @return User情報
	 */
	protected User toUser(Element elemUser) {
		User user = new User();
		// ユーザ名
		user.setName(XmlUtil.getText(elemUser, "name"));
		// フォロワー数
		user.setFollowersCount(Integer.parseInt(XmlUtil.getText(elemUser, "followers_count")));
		// ユーザID
		user.setUserId(XmlUtil.getText(elemUser, "id"));
		// プロフィール画像URL
		user.setProfileImageUrl(XmlUtil.getText(elemUser, "profile_image_url"));
		// 表示名
		user.setScreenName(XmlUtil.getText(elemUser, "screen_name"));
		// ユーザのエントリページのURL
		user.setUrl(XmlUtil.getText(elemUser, "url"));
		return user;
	}

	/**
	 * ユーザエレメントルートをUser情報リストに変換します。
	 * 
	 * @param elemUsers ユーザエレメントルート
	 * @return User情報リスト
	 */
	protected List<User> toUserList(Element elemUsers) throws HatenaHaikuException {
		List<User> userList = new ArrayList<User>();
		NodeList elemUserList = elemUsers.getElementsByTagName("user");
		for (int i = 0; i < elemUserList.getLength(); i++) {
			userList.add(toUser((Element)elemUserList.item(i)));
		}
		return userList;
	}

	/**
	 * キーワードエレメントをKeyword情報に変換します。
	 * 
	 * @param elemKeyword キーワードエレメント
	 * @return Keyword情報
	 */
	protected Keyword toKeyword(Element elemKeyword) throws HatenaHaikuException {
		Keyword keyword = new Keyword();
		// 投稿数
		keyword.setEntryCount(Integer.parseInt(XmlUtil.getText(elemKeyword, "entry_count")));
		// フォロワー数
		keyword.setFollowersCount(Integer.parseInt(XmlUtil.getText(elemKeyword, "followers_count")));
		// キーワードページのリンク
		keyword.setLink(XmlUtil.getText(elemKeyword, "link"));
		// 関連キーワード
		keyword.setRelatedKeywords(XmlUtil.getTextList(elemKeyword, "related_keywords"));
		// キーワードタイトル
		keyword.setTitle(XmlUtil.getText(elemKeyword, "title"));
		return keyword;
	}

	/**
	 * キーワードエレメントルートをKeyword情報リストに変換します。
	 * 
	 * @param elemKeywords キーワードエレメントルート
	 * @return Keyword情報リスト
	 */
	protected List<Keyword> toKeywordList(Element elemKeywords) throws HatenaHaikuException {
		List<Keyword> keywordList = new ArrayList<Keyword>();
		NodeList elemKeywordList = elemKeywords.getElementsByTagName("keyword");
		for (int i = 0; i < elemKeywordList.getLength(); i++) {
			keywordList.add(toKeyword((Element)elemKeywordList.item(i)));
		}
		return keywordList;
	}
}
