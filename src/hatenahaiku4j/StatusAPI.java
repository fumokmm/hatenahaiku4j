package hatenahaiku4j;

import java.io.File;

/**
 * ステータスに関するAPI
 * 
 * @since v0.2.0
 * @author fumokmm
 */
public class StatusAPI extends EntityAPI {
	/** ステータス情報 */
	private Status status;

	/**
	 * コンストラクタ。（パッケージプライベート）
	 * 
	 * @since v0.2.0
	 */
	StatusAPI() {
		super();
	}

	/**
	 * 初期化します。
	 * 
	 * @param status ステータス情報
	 * @param apiAuth はてなハイクAPI（認証あり）
	 * @since v0.2.0
	 */
	void init(Status status, HatenaHaikuAPI apiAuth) {
		this.status = status;
		this.apiAuth = apiAuth;
		this.apiLight = apiAuth;
	}

	/**
	 * 初期化します。
	 * 
	 * @param status ステータス情報
	 * @param apiLight はてなハイクAPI（認証なし）
	 * @since v0.2.0
	 */
	void init(Status status, HatenaHaikuAPILight apiLight) {
		this.status = status;
		this.apiLight = apiLight;
	}
	
	/**
	 * このステータスを返信先状態に取得しなおします。
	 * 
	 * @see HatenaHaikuAPILight#getStatus(String)
	 * @return 返信先状態を取得しなおされたこのステータス
	 * @since v0.2.0
	 */
	Status refreshReplies() throws HatenaHaikuException {
		Status newStatus = apiLight.getStatus(status.getStatusId());
		status.setReplies(newStatus.getReplies());
		status.setShadow(false);
		return this.status;
	}
	
	// ------------------以下、認証が不要なAPI

	/**
	 * このステータスのユーザ情報を取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/friendships/show/<font color="red">ユーザID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getUser(String)
	 * @return 指定したユーザ情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public User getUser() throws HatenaHaikuException {
		return apiLight.getUser(status.getUserId());
	}
	
	/**
	 * このステータスのキーワード情報を取得します。<br/>
	 * <i>http://h.hatena.ne.jp/api/keywords/show/<font color="red">キーワード</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPILight#getKeyword(String)
	 * @return 指定したキーワード情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Keyword getKeyword() throws HatenaHaikuException {
		return apiLight.getKeyword(status.getKeyword());
	}
	
	// ------------------以下、認証が必要なAPI
	
	/**
	 * このエントリに新しく返信エントリを投稿します。
	 * 
	 * @see HatenaHaikuAPI#reply(String, String)
	 * @param text 投稿内容
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v0.2.0
	 */
	public Status reply(String text) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.reply(status.getStatusId(), text);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * このエントリに新しく返信エントリを投稿します。<br/>
	 * 画像付きでエントリします。
	 * 
	 * @see HatenaHaikuAPI#reply(String, String, File)
	 * @param text 投稿内容
	 * @param file 画像ファイル
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status reply(String text, File file) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.reply(status.getStatusId(), text, file);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}
	
	/**
	 * このエントリに新しく返信エントリを投稿します。<br/>
	 * URL上にある画像付きでエントリします。
	 * 
	 * @see HatenaHaikuAPI#reply(String, String, String)
	 * @param text 投稿内容
	 * @param imageUrl 画像のURL
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status reply(String text, String imageUrl) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.reply(status.getStatusId(), text, imageUrl);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}
	
	/**
	 * このエントリに新しく返信エントリを投稿します。<br/>
	 * 画像のバイナリデータとその拡張子を指定し、画像付きでエントリします。
	 * 
	 * @see HatenaHaikuAPI#reply(String, String, byte[], ImageExt)
	 * @param text 投稿内容
	 * @param imageData 画像のバイナリデータ
	 * @param imageDataExt 画像の拡張子
	 * @return 投稿結果のステータス情報
	 * @throws HatenaHaikuException
	 * @since v1.0.0
	 */
	public Status reply(String text, byte[] imageData, ImageExt imageDataExt) throws HatenaHaikuException {
		if (isAuth()) {
			return apiAuth.reply(status.getStatusId(), text, imageData, imageDataExt);
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}
	
	/**
	 * このエントリを削除します。<br/>
	 * <i>http://h.hatena.ne.jp/api/statuses/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#deleteEntry(String)
	 * @return この削除したステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public Status delete() throws HatenaHaikuException {
		if (isAuth()) {
			Status deleted = apiAuth.deleteEntry(status.getStatusId());
			status.overwrite(deleted);
			return status;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}
	
	/**
	 * このエントリにスターを一つ追加します。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/create/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#addStar(String)
	 * @return スターを一つ追加した結果のこのステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public Status addStar() throws HatenaHaikuException {
		if (isAuth()) {
			Status starAdded = apiAuth.addStar(status.getStatusId());
			status.overwrite(starAdded);
			return status;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

	/**
	 * 指定したエントリのスターを一つ減らします。<br/>
	 * <i>http://h.hatena.ne.jp/api/favorites/destroy/<font color="red">ステータスID</font>.xml</i>
	 * 
	 * @see HatenaHaikuAPI#deleteStar(String)
	 * @return スターを一つ減らした結果のこのステータス情報
	 * @throws HatenaHaikuException 
	 * @since v0.2.0
	 */
	public Status deleteStar() throws HatenaHaikuException {
		if (isAuth()) {
			Status starDeleted = apiAuth.deleteStar(status.getStatusId());
			status.overwrite(starDeleted);
			return status;
		} else {
			throw new HatenaHaikuException("認証される必要があります。HatenaHaikuAPIをご利用ください。");
		}
	}

}
