package hatenahaiku4j;

import hatenahaiku4j.op.ReduceOp;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * キーワードに関するAPI
 * 
 * @since v0.2.0
 * @author fumokmm
 */
public class KeywordAPI extends EntityAPI {
	/** キーワード情報 */
	private Keyword keyword;

	/**
	 * コンストラクタ。（パッケージプライベート）
	 * @since v0.2.0
	 */
	KeywordAPI() {
		super();
	}

	/**
	 * 初期化します。
	 * 
	 * @param keyword キーワード情報
	 * @param apiAuth はてなハイクAPI（認証あり）
	 * @since v0.2.0
	 */
	void init(Keyword keyword, HatenaHaikuAPI apiAuth) {
		this.keyword = keyword;
		this.apiAuth = apiAuth;
		this.apiLight = apiAuth;
	}

	/**
	 * 初期化します。
	 * 
	 * @param keyword キーワード情報
	 * @param apiLight はてなハイクAPI（認証なし）
	 * @since v0.2.0
	 */
	void init(Keyword keyword, HatenaHaikuAPILight apiLight) {
		this.keyword = keyword;
		this.apiLight = apiLight;
	}
	
	// ------------------以下、認証が不要なAPI

	/**
	 * このキーワードのキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @return このキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline() throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle());
	}

	/**
	 * このキーワードのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return このキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle(), page);
	}

	/**
	 * このキーワードのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return このキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle(), page, count);
	}

	/**
	 * このキーワードのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(keyword.getTitle(), page, count, since);
	}
	
	/**
	 * このキーワードのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(ReduceOp, String, int, int, Date)
	 * @param op 集合操作
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このキーワードのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.1.1
	 */
	public <T> T getTimeline(ReduceOp<Status, T> op, int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getKeywordTimeline(op, keyword.getTitle(), page, count, since);
	}
	
	/**
	 * このキーワードの人気のキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String)
	 * @return このキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline() throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle());
	}

	/**
	 * このキーワードの人気のキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return このキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle(), page);
	}

	/**
	 * このキーワードの人気のキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return このキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle(), page, count);
	}

	/**
	 * このキーワードの人気のキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public List<Status> getHotTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(keyword.getTitle(), page, count, since);
	}
	
	/**
	 * このキーワードの人気のキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/keyword_timeline/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeywordTimeline(ReduceOp, String, int, int, Date)
	 * @param op 集合操作
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return このキーワードの人気のキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.1.1
	 */
	public <T> T getHotTimeline(ReduceOp<Status, T> op, int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getHotKeywordTimeline(op, keyword.getTitle(), page, count, since);
	}
	
	/**
	 * このキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。最新ページを20件取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String)
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline() throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle());
	}

	/**
	 * このキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。取得件数は20件です。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String, int)
	 * @param page 取得するページです。最大数は100です。
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline(int page) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle(), page);
	}

	/**
	 * このキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String, int, int)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline(int page, int count) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle(), page, count);
	}

	/**
	 * このキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(String, int, int, Date)
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public List<Status> getAlbumTimeline(int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(keyword.getTitle(), page, count, since);
	}

	/**
	 * このキーワードの画像を含む最新のエントリのキーワードタイムラインを取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/album/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getAlbumKeywordTimeline(ReduceOp, String, int, int, Date)
	 * @param op 集合操作
	 * @param page 取得するページです。最大数は100です。
	 * @param count 取得数を指定します。最大数は 200 です。
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時を指定します。
	 * @return 画像を含む最新のエントリのキーワードタイムライン
	 * @throws HatenaHaikuException
	 * @since v1.1.1
	 */
	public <T> T getAlbumTimeline(ReduceOp<Status, T> op, int page, int count, Date since) throws HatenaHaikuException {
		return apiLight.getAlbumKeywordTimeline(op, keyword.getTitle(), page, count, since);
	}

	// ------------------以下、認証が必要なAPI
	
	/**
	 * このキーワードに新しくエントリを投稿します。
	 * 
	 * @see HatenaHaikuAPI#entry(String, String)
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Status entry(String text) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このキーワードに新しくエントリを投稿します。<br/>
	 * 画像付きでエントリします。
	 * 
	 * @see HatenaHaikuAPI#entry(String, String, File)
	 * @param text 投稿内容
	 * @param file 画像ファイル
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String text, File file) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text, file);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}
	
	/**
	 * このキーワードに新しくエントリを投稿します。<br/>
	 * URL上にある画像付きでエントリします。
	 * 
	 * @see HatenaHaikuAPI#entry(String, String, String)
	 * @param text 投稿内容
	 * @param imageUrl 画像のURL
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String text, String imageUrl) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text, imageUrl);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このキーワードに新しくエントリを投稿します。<br/>
	 * 画像のバイナリデータとその拡張子を指定し、画像付きでエントリします。
	 * 
	 * @see HatenaHaikuAPI#entry(String, String, byte[], ImageExt)
	 * @param text 投稿内容
	 * @param imageData 画像のバイナリデータ
	 * @param imageDataExt 画像の拡張子
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status entry(String text, byte[] imageData, ImageExt imageDataExt) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.entry(keyword.getTitle(), text, imageData, imageDataExt);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このキーワードをフォローします。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/create/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#followKeyword(String)
	 * @return フォローしたキーワード情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword follow() throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.followKeyword(keyword.getTitle());
			keyword.overwrite(newKeyword);
			return keyword;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このキーワードのフォローをやめます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/destroy/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unfollowKeyword(String)
	 * @return フォローをやめたキーワード情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword unfollow() throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.unfollowKeyword(keyword.getTitle());
			keyword.overwrite(newKeyword);
			return keyword;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}
	
	/**
	 * このキーワードに指定のキーワードを関連付けます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/create.xml</i>
	 * 
	 * @see HatenaHaikuAPI#relateKeyword(String, String)
	 * @param keyword このキーワードに関連付けるキーワード
	 * @return 関連キーワードを設定後のこのキーワード
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword relate(String keyword) throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.relateKeyword(this.keyword.getTitle(), keyword);
			this.keyword.overwrite(newKeyword);
			return this.keyword;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このキーワードに指定のキーワードを関連付けます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/create.xml</i>
	 * 
	 * @see HatenaHaikuAPI#relateKeyword(String, String)
	 * @param keyword このキーワードに関連付けるキーワード
	 * @return 関連キーワードを設定後のこのキーワード
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword relate(Keyword keyword) throws HatenaHaikuException {
		return relate(keyword.getTitle());
	}

	/**
	 * このキーワードから指定のキーワードの関連付けを解除します。<br/>
	 * 関連付けを解除は自分が設定したものに限られます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/destroy.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unrelateKeyword(String, String)
	 * @param keyword このキーワードから関連付けを解除するキーワード
	 * @return 関連キーワードを解除後のこのキーワード
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword unrelate(String keyword) throws HatenaHaikuException {
		if (isAuth()) {
			Keyword newKeyword = apiAuth.unrelateKeyword(this.keyword.getTitle(), keyword);
			this.keyword.overwrite(newKeyword);
			return this.keyword;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このキーワードから指定のキーワードの関連付けを解除します。<br/>
	 * 関連付けを解除は自分が設定したものに限られます。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/relation/destroy.xml</i>
	 * 
	 * @see HatenaHaikuAPI#unrelateKeyword(String, String)
	 * @param keyword このキーワードから関連付けを解除するキーワード
	 * @return 関連キーワードを解除後のこのキーワード
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword unrelate(Keyword keyword) throws HatenaHaikuException {
		return unrelate(keyword.getTitle());
	}
}
