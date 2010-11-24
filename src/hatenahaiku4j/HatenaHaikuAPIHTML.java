package hatenahaiku4j;

import hatenahaiku4j.op.ReduceOp;
import hatenahaiku4j.util.HatenaUtil;
import hatenahaiku4j.util.HttpUtil;
import hatenahaiku4j.util.StringUtil;
import hatenahaiku4j.util.XmlUtil;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * はてなハイクAPIクラス（HTMLスクレイピング版）<br/>
 * HTMLスクレイピングして情報を取得するため、APIのページ制限を受けません。<br/>
 * その代償として、取得できる情報は多少少なくなります。
 * 
 * @since v1.2.0
 * @author fumokmm
 */
public class HatenaHaikuAPIHTML {
	/** URL: パブリックタイムライン */
	protected static final String URL_PUBLIC_TIMELINE		= Const.BASE_URL + ".body";
	/** URL: アルバムタイムライン */
	protected static final String URL_ALBUM_TIMELINE		= Const.BASE_URL + "album.body";
	/** URL: フレンドタイムライン */
	protected static final String URL_FRIENDS_TIMELINE	= Const.BASE_URL + "【ID】/following.body";
	/** URL: ユーザタイムライン */
	protected static final String URL_USER_TIMELINE		= Const.BASE_URL + "【ID】/.body";
	/** URL: キーワードタイムライン */
	protected static final String URL_KEYWORD_TIMELINE	= Const.BASE_URL + "target.body";
	/** URL: キーワードリスト */
	protected static final String URL_KEYWORD_LIST		= Const.BASE_URL + "keywords.body";
	/** URL: ホットキーワード */
	protected static final String URL_HOT_KEYWORD_LIST	= Const.BASE_URL + "hotkeywords";

	/** キーワードの投稿数のフォーマット */
	protected static final MessageFormat KEYWORD_ENTRY_COUNT_FORMAT = new MessageFormat("({0,number,integer})");
	/** キーワードのクラウドのフォーマット */
	protected static final MessageFormat KEYWORD_CLOUD_FORMAT = new MessageFormat("cloud{0,number,integer}");

	/** HTTP通信ログ出力要否 */
	protected boolean needHttpLog;
	
	/**
	 * HTTP通信ログ出力要否を返却します。
	 * 
	 * @return HTTP通信ログ出力要否
	 * @since v1.2.0
	 */
	public boolean isNeedHttpLog() {
		return needHttpLog;
	}
	
	/**
	 * HTTP通信ログ出力要否を設定します。
	 * 
	 * @param needHttpLog HTTP通信ログ出力要否
	 * @since v1.2.0
	 */
	public void setNeedHttpLog(boolean needHttpLog) {
		this.needHttpLog = needHttpLog;
	}
	
	/** APIシリアル値 */
	private final String serial;

	/**
	 * APIシリアル値を取得する。
	 *
	 * @return APIシリアル値
	 * @since v1.2.0
	 */
	protected String getSerial() {
		return serial;
	}

	/**
	 * コンストラクタ。
	 * 
	 * @since v1.2.0
	 */
	public HatenaHaikuAPIHTML() {
		// APIシリアル値
		this.serial = StringUtil.getSerial();
	}
	
	//-------------------------------------------------------------
	// タイムライン関係
	//-------------------------------------------------------------

	/**
	 * パブリックタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/.body?page=【ページ数】</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 * @since v1.2.0
	 */
	public List<Status> getPublicTimeline() throws HatenaHaikuException {
		return getPublicTimeline(0);
	}

	/**
	 * パブリックタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/.body?page=【ページ数】</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @param page 取得するページです。
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 * @since v1.2.0
	 */
	public List<Status> getPublicTimeline(int page) throws HatenaHaikuException {
		return getPublicTimeline(EntityAPI.<Status>createCollectOp(), page);
	}

	/**
	 * パブリックタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/.body?page=【ページ数】</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/">はてなハイク</a>
	 * @param op 集合操作
	 * @param page 取得するページです。
	 * @return パブリックタイムライン
	 * @throws HatenaHaikuException 
	 * @since v1.2.0
	 */
	public <T> T getPublicTimeline(ReduceOp<Status, T> op, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter(true);
			param.setPage(page);

			// 取得して、XMLに加工する
			String resultXml = HttpUtil.doGet(URL_PUBLIC_TIMELINE, param, isNeedHttpLog());
			resultXml = Const.XML_HEADER + AddingRootTag.STATUS_LIST.surround(resultXml);

			return toStatusList(op, XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

// TODO Statusの形式が違うため、v1.2.0時点ではひとまず保留とする。
//	/**
//	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。最新ページを20件取得します。<br/>
//	 * <i>http://h.hatena.ne.jp/album.body?page=【ページ数】</i>
//	 * 
//	 * @return 画像を含む最新のエントリのパブリックタイムライン
//	 * @throws HatenaHaikuException
//	 * @since v1.2.0
//	 */
//	public List<Status> getAlbumTimeline() throws HatenaHaikuException {
//		return getAlbumTimeline(0);
//	}
//
//	/**
//	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。取得件数は20件です。<br/>
//	 * <i>http://h.hatena.ne.jp/album.body?page=【ページ数】</i>
//	 * 
//	 * @param page 取得するページです。最大数は100です。
//	 * @return 画像を含む最新のエントリのパブリックタイムライン
//	 * @throws HatenaHaikuException
//	 * @since v1.2.0
//	 */
//	public List<Status> getAlbumTimeline(int page) throws HatenaHaikuException {
//		return getAlbumTimeline(this.<Status>createCollectOp(), page);
//	}
//
//	/**
//	 * 画像を含む最新のエントリのパブリックタイムラインを取得します。取得件数は20件です。<br/>
//	 * <i>http://h.hatena.ne.jp/album.body?page=【ページ数】</i>
//	 * 
//	 * @param op 集合操作
//	 * @param page 取得するページです。
//	 * @return 画像を含む最新のエントリのパブリックタイムライン
//	 * @throws HatenaHaikuException
//	 * @since v1.2.0
//	 */
//	public <T> T getAlbumTimeline(ReduceOp<Status, T> op, int page) throws HatenaHaikuException {
//		try {
//			QueryParameter param = new QueryParameter(true);
//			param.setPage(page);
//
//			// 取得して、XMLに加工する
//			String resultXml = HttpUtil.doGet(URL_ALBUM_TIMELINE, param, isNeedHttpLog());
//			resultXml = StringUtil.removeIllegalChar(resultXml);
//			resultXml = Const.XML_HEADER + AddingRootTag.STATUS_LIST.surround(resultXml);
//
//			return toStatusList(op, XmlUtil.getRootElement(resultXml));
//
//		} catch (ParserConfigurationException e) {
//			throw new HatenaHaikuException("ParserConfigurationException発生。", e);
//
//		} catch (SAXException e) {
//			throw new HatenaHaikuException("SAXException発生。", e);
//
//		} catch (IOException e) {
//			throw new HatenaHaikuException("IOException発生。", e);
//		}
//	}

	/**
	 * 指定したユーザのフレンドタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/following.body?page=【ページ数】</i>
	 * 
	 * @param userId ユーザID
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v1.2.0
	 */
	public List<Status> getFriendsTimeline(String userId) throws HatenaHaikuException {
		return getFriendsTimeline(userId, 0);
	}

	/**
	 * 指定したユーザのフレンドタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/following.body?page=【ページ数】</i>
	 * 
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v1.2.0
	 */
	public List<Status> getFriendsTimeline(String userId, int page) throws HatenaHaikuException {
		return getFriendsTimeline(EntityAPI.<Status>createCollectOp(), userId, page);
	}

	/**
	 * 指定したユーザのフレンドタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/following.body?page=【ページ数】</i>
	 * 
	 * @param op 集合操作
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのフレンドタイムライン
	 * @throws HatenaHaikuException 
	 * @since v1.2.0
	 */
	public <T> T getFriendsTimeline(ReduceOp<Status, T> op, String userId, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter(true);
			param.setPage(page);

			// 取得して、XMLに加工する
			String resultXml = HttpUtil.doGet(StringUtil.lumpReplace(URL_FRIENDS_TIMELINE, "【ID】", userId), param, isNeedHttpLog());
			resultXml = Const.XML_HEADER + AddingRootTag.STATUS_LIST.surround(resultXml);

			return toStatusList(op, XmlUtil.getRootElement(resultXml));

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
	 * <i>http://h.hatena.ne.jp/【ID】/.body?page=【ページ数】</i>
	 *
	 * @param userId ユーザID
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getUserTimeline(String userId) throws HatenaHaikuException {
		return getUserTimeline(userId, 0);
	}

	/**
	 * 指定したユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?page=【ページ数】</i>
	 *
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getUserTimeline(String userId, int page) throws HatenaHaikuException {
		return getUserTimeline(EntityAPI.<Status>createCollectOp(), userId, page);
	}

	/**
	 * 指定したユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?page=【ページ数】</i>
	 *
	 * @param op 集合操作
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getUserTimeline(ReduceOp<Status, T> op, String userId, int page) throws HatenaHaikuException {
		return _getUserTimeline(op, userId, page, false);
	}
	
	/**
	 * 指定したユーザの人気のユーザタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?mode=hot&page=【ページ数】</i>
	 *
	 * @param userId ユーザID
	 * @return 指定したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getHotUserTimeline(String userId) throws HatenaHaikuException {
		return getHotUserTimeline(userId, 0);
	}

	/**
	 * 指定したユーザの人気のユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?mode=hot&page=【ページ数】</i>
	 *
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getHotUserTimeline(String userId, int page) throws HatenaHaikuException {
		return getHotUserTimeline(EntityAPI.<Status>createCollectOp(), userId, page);
	}

	/**
	 * 指定したユーザの人気のユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?mode=hot&page=【ページ数】</i>
	 *
	 * @param op 集合操作
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザの人気のユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getHotUserTimeline(ReduceOp<Status, T> op, String userId, int page) throws HatenaHaikuException {
		return _getUserTimeline(op, userId, page, true);
	}

	/**
	 * 指定したユーザのユーザタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/【ID】/.body?mode=hot&page=【ページ数】</i>
	 *
	 * @param op 集合操作
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @param isHot 人気順取得用かどうか
	 * @return 指定したユーザのユーザタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	private <T> T _getUserTimeline(ReduceOp<Status, T> op, String userId, int page, boolean isHot) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter(true);
			if (isHot) {
				param.setMode(QueryParameter.HOT);	// 人気順
			}
			param.setPage(page);

			// 取得して、XMLに加工する
			String resultXml = HttpUtil.doGet(StringUtil.lumpReplace(URL_USER_TIMELINE, "【ID】", userId), param, isNeedHttpLog());
			resultXml = Const.XML_HEADER + AddingRootTag.STATUS_LIST.surround(resultXml);

			return toStatusList(op, XmlUtil.getRootElement(resultXml));

		} catch (ParserConfigurationException e) {
			throw new HatenaHaikuException("ParserConfigurationException発生。", e);

		} catch (SAXException e) {
			throw new HatenaHaikuException("SAXException発生。", e);

		} catch (IOException e) {
			throw new HatenaHaikuException("IOException発生。", e);
		}
	}

	/**
	 * 指定したユーザのidページのタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=id:【ID】&page=【ページ数】</i>
	 * 
	 * @param userId ユーザID
	 * @return 指定したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getIdTimeline(String userId) throws HatenaHaikuException {
		return getIdTimeline(userId, 0);
	}

	/**
	 * 指定したユーザのidページのタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=id:【ID】&page=【ページ数】</i>
	 * 
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getIdTimeline(String userId, int page) throws HatenaHaikuException {
		return getIdTimeline(EntityAPI.<Status>createCollectOp(), userId, page);
	}
	
	/**
	 * 指定したユーザのidページのタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=id:【ID】&page=【ページ数】</i>
	 * 
	 * @param op 集合操作
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのidページのタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getIdTimeline(ReduceOp<Status, T> op, String userId, int page) throws HatenaHaikuException {
		return _getKeywordTimeline(op, userId, page, KeywordTimelineMode.ID);
	}

	// TODO #getHotIdTimelineはAPI版の方には要らないのか？？
	/**
	 * 指定したユーザのidページの人気のタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=id:【ID】&page=【ページ数】</i>
	 * 
	 * @param userId ユーザID
	 * @return 指定したユーザのidページの人気のタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getHotIdTimeline(String userId) throws HatenaHaikuException {
		return getHotIdTimeline(userId, 0);
	}

	/**
	 * 指定したユーザのidページの人気のタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=id:【ID】&page=【ページ数】</i>
	 * 
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのidページの人気のタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getHotIdTimeline(String userId, int page) throws HatenaHaikuException {
		return getHotIdTimeline(EntityAPI.<Status>createCollectOp(), userId, page);
	}
	
	/**
	 * 指定したユーザのidページの人気のタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=id:【ID】&page=【ページ数】</i>
	 * 
	 * @param op 集合操作
	 * @param userId ユーザID
	 * @param page 取得するページです。
	 * @return 指定したユーザのidページの人気のタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getHotIdTimeline(ReduceOp<Status, T> op, String userId, int page) throws HatenaHaikuException {
		return _getKeywordTimeline(op, userId, page, KeywordTimelineMode.HOT_ID);
	}

	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=keyword:【キーワード】&page=【ページ数】</i>
	 * 
	 * @param keyword キーワード
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getKeywordTimeline(String keyword) throws HatenaHaikuException {
		/*
		 * HTML版ではIDタイムラインと、キーワードタイムラインでリクエストURLの形式が
		 * 異なるため、キーワードがid形式だった場合は、#getIdTimelineに処理を移譲する。
		 */
		if (HatenaUtil.isIdNotation(keyword)) {
			return getIdTimeline(keyword.replaceFirst("^" + Const.ID_COLON, ""));
		}
		return getKeywordTimeline(keyword, 0);
	}

	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=keyword:【キーワード】&page=【ページ数】</i>
	 * 
	 * @param keyword キーワード
	 * @param page 取得するページです。
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getKeywordTimeline(String keyword, int page) throws HatenaHaikuException {
		/*
		 * HTML版ではIDタイムラインと、キーワードタイムラインでリクエストURLの形式が
		 * 異なるため、キーワードがid形式だった場合は、#getIdTimelineに処理を移譲する。
		 */
		if (HatenaUtil.isIdNotation(keyword)) {
			return getIdTimeline(keyword.replaceFirst("^" + Const.ID_COLON, ""), page);
		}
		return getKeywordTimeline(EntityAPI.<Status>createCollectOp(), keyword, page);
	}

	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=keyword:【キーワード】&page=【ページ数】</i>
	 * 
	 * @param op 集合操作
	 * @param keyword キーワード
	 * @param page 取得するページです。
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getKeywordTimeline(ReduceOp<Status, T> op, String keyword, int page) throws HatenaHaikuException {
		/*
		 * HTML版ではIDタイムラインと、キーワードタイムラインでリクエストURLの形式が
		 * 異なるため、キーワードがid形式だった場合は、#getIdTimelineに処理を移譲する。
		 */
		if (HatenaUtil.isIdNotation(keyword)) {
			return getIdTimeline(op, keyword.replaceFirst("^" + Const.ID_COLON, ""), page);
		}
		return _getKeywordTimeline(op, keyword, page, KeywordTimelineMode.NONE);
	}

	/**
	 * 指定したキーワードの人気のキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=keyword:【キーワード】&page=【ページ数】</i>
	 * 
	 * @param keyword キーワード
	 * @return 指定したキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getHotKeywordTimeline(String keyword) throws HatenaHaikuException {
		/*
		 * HTML版ではIDタイムラインと、キーワードタイムラインでリクエストURLの形式が
		 * 異なるため、キーワードがid形式だった場合は、#getIdTimelineに処理を移譲する。
		 */
		if (HatenaUtil.isIdNotation(keyword)) {
			return getHotIdTimeline(keyword.replaceFirst("^" + Const.ID_COLON, ""));
		}
		return getHotKeywordTimeline(keyword, 0);
	}

	/**
	 * 指定したキーワードの人気のキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=keyword:【キーワード】&page=【ページ数】</i>
	 * 
	 * @param keyword キーワード
	 * @param page 取得するページです。
	 * @return 指定したキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Status> getHotKeywordTimeline(String keyword, int page) throws HatenaHaikuException {
		/*
		 * HTML版ではIDタイムラインと、キーワードタイムラインでリクエストURLの形式が
		 * 異なるため、キーワードがid形式だった場合は、#getIdTimelineに処理を移譲する。
		 */
		if (HatenaUtil.isIdNotation(keyword)) {
			return getHotIdTimeline(keyword.replaceFirst("^" + Const.ID_COLON, ""), page);
		}
		return getHotKeywordTimeline(EntityAPI.<Status>createCollectOp(), keyword, page);
	}

	/**
	 * 指定したキーワードの人気のキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=keyword:【キーワード】&page=【ページ数】</i>
	 * 
	 * @param op 集合操作
	 * @param keyword キーワード
	 * @param page 取得するページです。
	 * @return 指定したキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getHotKeywordTimeline(ReduceOp<Status, T> op, String keyword, int page) throws HatenaHaikuException {
		/*
		 * HTML版ではIDタイムラインと、キーワードタイムラインでリクエストURLの形式が
		 * 異なるため、キーワードがid形式だった場合は、#getIdTimelineに処理を移譲する。
		 */
		if (HatenaUtil.isIdNotation(keyword)) {
			return getHotIdTimeline(op, keyword.replaceFirst("^" + Const.ID_COLON, ""), page);
		}
		return _getKeywordTimeline(op, keyword, page, KeywordTimelineMode.HOT);
	}

// TODO 形式が違う他のタイムラインと違うため、ひとまず保留
//	/**
//	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
//	 * <i>http://h.hatena.ne.jp/target.body?mode=album&word=keyword:【キーワード】&page=【ページ数】</i>
//	 * 
//	 * @param keyword キーワード
//	 * @return 画像を含む最新のエントリのキーワードタイムライン
//	 * @throws HatenaHaikuException
//	 * @since v1.2.0
//	 */
//	public List<Status> getAlbumKeywordTimeline(String keyword) throws HatenaHaikuException {
//		return getAlbumKeywordTimeline(keyword, 0);
//	}
//
//	/**
//	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。取得件数は20件です。<br/>
//	 * <i>http://h.hatena.ne.jp/target.body?mode=album&word=keyword:【キーワード】&page=【ページ数】</i>
//	 * 
//	 * @param keyword キーワード
//	 * @param page 取得するページです。
//	 * @return 画像を含む最新のエントリのキーワードタイムライン
//	 * @throws HatenaHaikuException
//	 * @since v1.2.0
//	 */
//	public List<Status> getAlbumKeywordTimeline(String keyword, int page) throws HatenaHaikuException {
//		return getAlbumKeywordTimeline(EntityAPI.<Status>createCollectOp(), keyword, page);
//	}
//
//	/**
//	 * 指定したキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。取得件数は20件です。<br/>
//	 * <i>http://h.hatena.ne.jp/target.body?mode=album&word=keyword:【キーワード】&page=【ページ数】</i>
//	 * 
//	 * @param op 集合操作
//	 * @param keyword キーワード
//	 * @param page 取得するページです。
//	 * @return 画像を含む最新のエントリのキーワードタイムライン
//	 * @throws HatenaHaikuException
//	 * @since v1.2.0
//	 */
//	public <T> T getAlbumKeywordTimeline(ReduceOp<Status, T> op, String keyword, int page) throws HatenaHaikuException {
//		return _getKeywordTimeline(op, keyword, page, KeywordTimelineMode.ALBUM);
//	}
	
	/**
	 * 指定したキーワードのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/target.body?word=keyword:【キーワード】&page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/target.body?word=id:【ID】&page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/target.body?word=【URL】&page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=keyword:【キーワード】&page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/target.body?mode=album&word=keyword:【キーワード】&page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=id:【ID】&page=【ページ数】</i>
	 * <i>http://h.hatena.ne.jp/target.body?mode=hot&word=【URL】&page=【ページ数】</i>
	 * 
	 * @param op 集合操作
	 * @param keyword キーワード
	 * @param page 取得するページです。
	 * @param mode キーワードタイムライン取得モード
	 * @return 指定したキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	private <T> T _getKeywordTimeline(ReduceOp<Status, T> op, String keyword, int page, KeywordTimelineMode mode) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter(true);
			// mode
			switch (mode) {
				case HOT :
				case HOT_URL :
				case HOT_ID :
					param.setMode(QueryParameter.HOT);		// 人気順
					break;
				
				case ALBUM :
					param.setMode(QueryParameter.ALBUM);	// アルバム表示
					break;

				default :
					break;
			}
			// word
			switch (mode) {
				case URL :
				case HOT_URL :
					param.setWord(keyword);
					break;
			
				case ID :
				case HOT_ID :
					param.setWord(Const.ID_COLON + keyword);
					break;

				default :
					param.setWord(Const.KEYWORD_COLON + keyword);
					break;
			}
			param.setPage(page);

			// 取得して、XMLに加工する
			String resultXml = HttpUtil.doGet(URL_KEYWORD_TIMELINE, param, isNeedHttpLog());
			resultXml = Const.XML_HEADER + AddingRootTag.STATUS_LIST.surround(resultXml);

			return toStatusList(op, XmlUtil.getRootElement(resultXml));
		
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
	
	//-------------------------------------------------------------
	// スター関係
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	// ユーザ／フォロー関係
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	// キーワード関係
	//-------------------------------------------------------------

	/**
	 * ホットキーワードのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/hotkeywords</i><br/>
	 * <br/>
	 * <h2>利用できる項目</h2>
	 * <ul>
	 * <li>投稿数({@link Keyword#getEntryCount()})</li>
	 * <li>キーワードページのリンク({@link Keyword#getLink()} {@link Keyword#getMobileLink()})</li>
	 * <li>キーワードタイトル({@link Keyword#getTitle()})</li>
	 * <li>idページかどうか({@link Keyword#isIdPage()})</li>
	 * </ul>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/hotkeywords">注目キーワード</a>
	 * @return ホットキーワードのリスト
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Keyword> getHotKeywordList() throws HatenaHaikuException {
		return getHotKeywordList(EntityAPI.<Keyword>createCollectOp());
	}

	/**
	 * ホットキーワードのリストを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/hotkeywords</i>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/hotkeywords">注目キーワード</a>
	 * @param op 集合操作
	 * @return ホットキーワードのリスト
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getHotKeywordList(ReduceOp<Keyword, T> op) throws HatenaHaikuException {
		try {
			// 取得して、XMLに加工する
			String resultXml = HttpUtil.doGet(URL_HOT_KEYWORD_LIST, null, isNeedHttpLog());
			// <ul class="cloud">と</ul>で囲まれた内容だけ取得
			resultXml = resultXml.replaceAll("(?s)^(.+<ul class=\"cloud\">)|(</ul>.+)$", "");
			resultXml = Const.XML_HEADER + AddingRootTag.KEYWORD_LIST.surround(resultXml);

			return toKeywordList(op, XmlUtil.getRootElement(resultXml));

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
	 * <i>http://h.hatena.ne.jp/keywords.body?word=【検索文字列】&page=【ページ数】</i><br/>
	 * <br/>
	 * <h2>利用できる項目</h2>
	 * <ul>
	 * <li>投稿数({@link Keyword#getEntryCount()})</li>
	 * <li>キーワードページのリンク({@link Keyword#getLink()} {@link Keyword#getMobileLink()})</li>
	 * <li>キーワードタイトル({@link Keyword#getTitle()})</li>
	 * <li>idページかどうか({@link Keyword#isIdPage()})</li>
	 * </ul>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @return キーワードリスト（１ページ目）
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Keyword> getKeywordList() throws HatenaHaikuException {
		return getKeywordList(null, 1);
	}

	/**
	 * キーワードリストを取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/keywords.body?word=【検索文字列】&page=【ページ数】</i><br/>
	 * <br/>
	 * <h2>利用できる項目</h2>
	 * <ul>
	 * <li>投稿数({@link Keyword#getEntryCount()})</li>
	 * <li>キーワードページのリンク({@link Keyword#getLink()} {@link Keyword#getMobileLink()})</li>
	 * <li>キーワードタイトル({@link Keyword#getTitle()})</li>
	 * <li>idページかどうか({@link Keyword#isIdPage()})</li>
	 * </ul>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @param page
	 * @return　キーワードリスト（指定ページ）
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Keyword> getKeywordList(int page) throws HatenaHaikuException {
		return getKeywordList(null, page);
	}

	/**
	 * 指定したワードに部分一致するキーワードリストを取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/keywords.body?word=【検索文字列】&page=【ページ数】</i><br/>
	 * <br/>
	 * <h2>利用できる項目</h2>
	 * <ul>
	 * <li>投稿数({@link Keyword#getEntryCount()})</li>
	 * <li>キーワードページのリンク({@link Keyword#getLink()} {@link Keyword#getMobileLink()})</li>
	 * <li>キーワードタイトル({@link Keyword#getTitle()})</li>
	 * <li>idページかどうか({@link Keyword#isIdPage()})</li>
	 * </ul>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @param searchWord 検索ワード
	 * @param page ページ
	 * @return　キーワードリスト（指定ページ）
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public List<Keyword> getKeywordList(String searchWord, int page) throws HatenaHaikuException {
		return getKeywordList(EntityAPI.<Keyword>createCollectOp(), searchWord, page);
	}

	/**
	 * 指定したワードに部分一致するキーワードリストを取得します。（指定ページ）<br/>
	 * <i>http://h.hatena.ne.jp/keywords.body?word=【検索文字列】&page=【ページ数】</i><br/>
	 * <br/>
	 * <h2>利用できる項目</h2>
	 * <ul>
	 * <li>投稿数({@link Keyword#getEntryCount()})</li>
	 * <li>キーワードページのリンク({@link Keyword#getLink()} {@link Keyword#getMobileLink()})</li>
	 * <li>キーワードタイトル({@link Keyword#getTitle()})</li>
	 * <li>idページかどうか({@link Keyword#isIdPage()})</li>
	 * </ul>
	 * 
	 * @see <a href="http://h.hatena.ne.jp/keywords">キーワードリスト</a>
	 * @param op 集合操作
	 * @param searchWord 検索ワード
	 * @param page ページ
	 * @return　キーワードリスト（指定ページ）
	 * @throws HatenaHaikuException
	 * @since v1.2.0
	 */
	public <T> T getKeywordList(ReduceOp<Keyword, T> op, String searchWord, int page) throws HatenaHaikuException {
		try {
			QueryParameter param = new QueryParameter(true);
			param.setWord(searchWord);
			param.setPage(page);
			
			// 取得して、XMLに加工する
			String resultXml = HttpUtil.doGet(URL_KEYWORD_LIST, param, isNeedHttpLog());
			resultXml = Const.XML_HEADER + AddingRootTag.KEYWORD_LIST.surround(resultXml);

			return toKeywordList(op, XmlUtil.getRootElement(resultXml));

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
	 * ステータス情報のインスタンスを取得します。
	 * 
	 * @since v1.2.0
	 */
	protected Status createStatus() {
		return Status.create(this);
	}

	/**
	 * ユーザ情報のインスタンスを取得します。
	 * 
	 * @since v1.2.0
	 */
	protected User createUser() {
		return User.create(this);
	}
	
	/**
	 * キーワード情報のインスタンスを取得します。
	 * 
	 * @since v1.2.0
	 */
	protected Keyword createKeyword() {
		return Keyword.create(this);
	}
	
	/**
	 * ステータスエレメントをStatus情報に変換します。
	 * 
	 * @param elemStatus ステータスエレメント
	 * @return Status情報
	 * @since v1.2.0
	 */
	protected Status toStatus(Element elemStatus) throws HatenaHaikuException {
		Status status = createStatus();
		status.setShadow(true); // お気に入られなどは取れない
		
		/*
		 * <div>
		 *   <div class="list-image">
		 *     <a href="/${userId}/">...</a>
		 *   </div>
		 *   <div class="list-body">
		 *     <h2>...</h2>
		 *     <div class="body">${投稿内容}</div>
		 *     <div class="info">
		 *       <span class="username"></span>
		 *       <span class="timestamp"></span>
		 *       <span class="source"></span>
		 *       <span class="delete"></span>
		 *       <span class="reply"></span>
		 *       <span class="repies"></span>
		 *     </div>
		 *   </div>
		 * </div>
		 */
			
		List<Element> topDivs = XmlUtil.getChildElementsByTagName(elemStatus, "div");
		Element listBody = topDivs.get(1);
		List<Element> listBodyDivs = XmlUtil.getChildElements(listBody);
		Element h2 = listBodyDivs.get(0);
		Element divBody = listBodyDivs.get(1);
		Element divinfo = listBodyDivs.get(2);
			
		/*
		 * キーワード
		 * 
		 * IDページの時は最初にユーザの画像が来るため、
		 * 最後の要素からキーワード取得
		 */
		status.setKeyword(h2.getLastChild().getTextContent());

		// 投稿内容
		status.setText(getTextContent(divBody));
		
		/*
		 * お気に入られは取得できない
		 */

		/*
		 * 返信元ユーザID
		 * 返信元ステータスID
		 * 
		 * divBodyの1つめの要素として<a>があり、その子要素が
		 * <img src="/images/icon-replylink.gif" alt="Reply to" title="Reply to" class="icon-reply-link" />
		 * だった場合、<a>が返信元である。
		 */

		// 空文字で初期化
		status.setInReplyToUserId("");
		status.setInReplyToStatusId("");

		Element divBodyA = XmlUtil.getFirstChildElement(divBody, "a");
		if (divBodyA != null) {
			// 返信イメージのリンクか？
			Element replyImg = XmlUtil.getFirstChildElement(divBodyA, "img");
			if (isReplyLinkImage(replyImg)) {
				// "/userId/statusId" 形式
				String[] inReplyInfo = divBodyA.getAttribute("href").split("/");
				/*
				 * 削除されたエントリ？などの場合、href="" となるようなので、
				 * 長さチェックして設定する。
				 */
				if (inReplyInfo.length == 3) {
					status.setInReplyToUserId(inReplyInfo[1]);
					status.setInReplyToStatusId(inReplyInfo[2]);
				}
			}
		}
	
		List<String> repliesHref = null;
		for (Element span : XmlUtil.getChildElementsByTagName(divinfo, "span")) {
			String attrClass = span.getAttribute("class"); 

			if ("timestamp".equals(attrClass)) {
				Element timestampAnchor = XmlUtil.getFirstChildElement(span, "a");
				String[] link = timestampAnchor.getAttribute("href").split("/");
				if (link.length == 3) {
					String userId = link[1];
					String statusId = link[2];
					
					// ユーザ情報
					status.setUser(User.create(this, userId));
					// リンク
					status.setLink(Const.BASE_URL + userId + Const.SLASH + statusId);
					// ステータスID
					status.setStatusId(statusId);
				}
				// 作成日時
				status.setCreatedAt(HatenaUtil.parseDate(XmlUtil.getText(span, "a")));

			} else 	if ("source".equals(attrClass)) {
				// ソース（クライアント名）
				status.setSource(XmlUtil.getText(span, "a"));

			} else 	if ("replies".equals(attrClass)) {
				// このステータスへの返信
				if (repliesHref == null) {
					repliesHref = new ArrayList<String>();
				}
				for (Element replyA : XmlUtil.getChildElementsByTagName(span, "a")) {
					// "/userId/statusId" という形式になっている
					repliesHref.add(replyA.getAttribute("href"));
				}
			}
		}
		
		// このステータスへの返信
		List<Status> replies = new ArrayList<Status>();
		if (repliesHref != null) {
			for (String rep : repliesHref) {
				Status reply = createStatus();
				// "/userId/statusId" 形式
				String[] repInfo = rep.split("/");
				if (repInfo.length == 3) {
					reply.setShadow(true);
					reply.setUser(User.create(this, repInfo[1]));
					reply.setStatusId(repInfo[2]);
					reply.setInReplyToStatusId(status.getStatusId());
					reply.setInReplyToUserId(status.getUserId());
					reply.setKeyword(status.getKeyword());
					reply.setLink(reply.getUser().getEntriesUrl() + reply.getStatusId());
					replies.add(reply);
				}
			}
		}
		status.setReplies(replies);

		return status;
	}
	
	/**
	 * ステータスエレメントルートをStatus情報リストに変換します。
	 * 
	 * @param op 集合操作
	 * @param elemStatuses ステータスエレメントルート
	 * @return Status情報リスト
	 * @since v1.2.0
	 */
	protected <T> T toStatusList(ReduceOp<Status, T> op, Element elemStatuses) throws HatenaHaikuException {
		for (Element elemDiv : XmlUtil.getChildElementsByTagName(elemStatuses, "div")) {
			op.add(toStatus(elemDiv));
		}
		return op.value();
	}

// TODO 今のところユーザを取得するメソッドは作成していない
//	/**
//	 * ユーザエレメントをUser情報に変換します。
//	 * 
//	 * @param elemUser ユーザエレメント
//	 * @return User情報
//	 * @since v1.2.0
//	 */
//	protected User toUser(Element elemUser) {
//		User user = createUser();
//		// ユーザ名
//		user.setName(XmlUtil.getText(elemUser, "name"));
//		// フォロワー数
//		user.setFollowersCount(Integer.parseInt(XmlUtil.getText(elemUser, "followers_count")));
//		// ユーザID
//		user.setUserId(XmlUtil.getText(elemUser, "id"));
//		// プロフィール画像URL
//		user.setProfileImageUrl(XmlUtil.getText(elemUser, "profile_image_url"));
//		// 表示名
//		user.setScreenName(XmlUtil.getText(elemUser, "screen_name"));
//		// ユーザのエントリページのURL
//		user.setUrl(XmlUtil.getText(elemUser, "url"));
//		return user;
//	}

// TODO 今のところユーザを取得するメソッドは作成していない
//	/**
//	 * ユーザエレメントルートをUser情報リストに変換します。
//	 * 
//	 * @param op 集合操作
//	 * @param elemUsers ユーザエレメントルート
//	 * @return User情報リスト
//	 * @since v1.2.0
//	 */
//	protected <T> T toUserList(ReduceOp<User, T> op, Element elemUsers) throws HatenaHaikuException {
//		for (Element elemUser : XmlUtil.getChildElementsByTagName(elemUsers, "user")) {
//			op.add(toUser(elemUser));
//		}
//		return op.value();
//	}

	/**
	 * キーワードエレメントをKeyword情報に変換します。
	 * 
	 * @param elemKeyword キーワードエレメント
	 * @return Keyword情報
	 * @since v1.2.0
	 */
	protected Keyword toKeyword(Element elemKeyword) throws HatenaHaikuException {
		Keyword keyword = createKeyword();

		// <li class="cloud1"><a href="/keyword/あああああ">あああああ</a></li>
		//   or
		// <li><a href="/keyword/あああああ">あああああ</a></li>
		//   or
		// <li><a href="/keyword/あああああ">あああああ</a><span>(999)</span></li>
		
		String classCloud = elemKeyword.getAttribute("class");
		Element anchor = XmlUtil.getFirstChildElement(elemKeyword, "a");
		Element span = XmlUtil.getFirstChildElement(elemKeyword, "span");
		
		int cloud = 0;
		try {
			if (!classCloud.equals("")) {
				cloud = ((Long) KEYWORD_CLOUD_FORMAT.parse(classCloud)[0]).intValue();
			}
		} catch (ParseException e) {}
		
		int entryCount = 0;
		try {
			if (span != null) {
				entryCount = ((Long) (KEYWORD_ENTRY_COUNT_FORMAT.parse(span.getTextContent()))[0]).intValue();
			}
		} catch (ParseException e) {}

		String link = Const.BASE_URL + anchor.getAttribute("href").substring(1); // 1文字目の "/" は要らない
		String title = anchor.getTextContent();
		
		// クラウド
		keyword.setCloud(cloud);
		// 投稿数
		keyword.setEntryCount(entryCount);
		// キーワードページのリンク
		keyword.setLink(link);
		// キーワードタイトル
		keyword.setTitle(title);
		
		return keyword;
	}

	/**
	 * キーワードエレメントルートをKeyword情報リストに変換します。
	 * 
	 * @param op 集合操作
	 * @param elemKeywords キーワードエレメントルート
	 * @return Keyword情報リスト
	 * @since v1.2.0
	 */
	protected <T> T toKeywordList(ReduceOp<Keyword, T> op, Element elemKeywords) throws HatenaHaikuException {
		for (Element elemLi : XmlUtil.getChildElementsByTagName(elemKeywords, "li")) {
			op.add(toKeyword(elemLi));
		}
		return op.value();
	}

	/**
	 * HTMLタグを解析して、テキストコンテンツを取得します。
	 * 
	 * @author fumokmm
	 * @param node 解析するノード
	 * @return テキストコンテンツ
	 * @since v1.2.0
	 */
	protected static String getTextContent(Node node) {
		StringBuilder sb = new StringBuilder();
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node child = nodeList.item(i);
			switch (child.getNodeType()) {
				case Node.ELEMENT_NODE:
					final Element childElem = (Element) child;
					final String nodeName = childElem.getNodeName();
					final String attrSrc = childElem.getAttribute("src");
					if (nodeName.equals("br")) {
						sb.append("\r\n");
						continue;
					}
					if (nodeName.equals("a")) {
						if (isKeywordLink(childElem)) {
							sb.append("[[").append(childElem.getTextContent()).append("]]");
						} else {
							sb.append(getTextContent(child));
						}
						continue;
					}
					if (nodeName.equals("img")) {
						if (!isReplyLinkImage(childElem)) {
							sb.append(attrSrc);
						}
						continue;
					}
					if (nodeName.equals("embed")) {
						if (attrSrc.startsWith("http://www.youtube.com/v/")) {
							sb.append(attrSrc);
						}
					}
					if (nodeName.equals("script")) {
						if (attrSrc.startsWith("http://www.nicovideo.jp/thumb_watch/")) {
							sb.append(attrSrc.split("\\?")[0].replace("thumb_", ""));
						}
					}
					// TODO asin記法の対応
					
					if (child.hasChildNodes()) {
						sb.append(getTextContent(child));
					}
					break;

				case Node.TEXT_NODE:
					String text = child.getNodeValue();
					sb.append(text.matches(" +") ? text : text.trim());
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * 指定されたエレメントがリプライリンク用画像か判定します。
	 * 
	 * @author fumokmm
	 * @param elem 判定するXMLエレメント
	 * @return リプライリンク用画像かどうか
	 * @since v1.2.0
	 */
	protected static boolean isReplyLinkImage(Element elem) {
		return elem != null
			&& "/images/icon-replylink.gif".equals(elem.getAttribute("src"))
			&& "Reply to".equals(elem.getAttribute("alt"))
			&& "Reply to".equals(elem.getAttribute("title"))
			&& "icon-reply-link".equals(elem.getAttribute("class"));
	}
	
	/**
	 * 指定されたエレメントがキーワードリンクか判定します。
	 * 
	 * @author fumokmm
	 * @param elem 判定するXMLエレメント
	 * @return キーワードリンクかどうか
	 * @since v1.2.0
	 */
	protected static boolean isKeywordLink(Element elem) {
		return elem != null
			&& elem.getAttribute("href") != null
			&& elem.getAttribute("href").startsWith("/keyword/")
			&& "keyword".equals(elem.getAttribute("class"));
	}
	
	/**
	 * キーワードタイムライン取得モード
	 * 
	 * @author fumokmm
	 * @since v1.2.0
	 */
	protected static enum KeywordTimelineMode {
		NONE, HOT, ALBUM,
		ID, HOT_ID,
		URL, HOT_URL
	}
	
	/**
	 * 付加するXMLのルートタグ
	 * 
	 * @author fumokmm
	 * @since v1.2.0
	 */
	protected static enum AddingRootTag {
		
		/**
		 * ステータスリスト
		 * @see HatenaHaikuAPIHTML#getPublicTimeline()
		 * @see HatenaHaikuAPIHTML#getFriendsTimeline()
		 * @see HatenaHaikuAPIHTML#_getUserTimeline()
		 * @see HatenaHaikuAPIHTML#_getKeywordTimeline()
		 */
		STATUS_LIST,
		
		/**
		 * キーワードリスト
		 * @see HatenaHaikuAPIHTML#getHotKeywordList()
		 * @see HatenaHaikuAPIHTML#getKeywordList()
		 */
		KEYWORD_LIST;

		/**
		 * @return 開始タグ
		 */
		public String getStartTag() {
			return "<" + name().toLowerCase() + ">";
		}

		/**
		 * @return 終了タグ
		 */
		public String getEndTag() {
			return "</" + name().toLowerCase() + ">";
		}
		
		/**
		 * 開始タグと終了タグで囲んだ文字列
		 * 
		 * @param contents 囲む文字列
		 * @return 開始タグと終了タグで囲んだ文字列
		 */
		public String surround(String contents) {
			return getStartTag() + contents + getEndTag();
		}
	}
}
