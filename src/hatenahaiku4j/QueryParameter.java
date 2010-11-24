package hatenahaiku4j;

import hatenahaiku4j.util.DateUtil;
import hatenahaiku4j.util.HttpUtil.PostStream;

import java.io.IOException;
import java.util.Date;

/**
 * 問い合わせ用パラメータを表現するクラスです。
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class QueryParameter {
	/** ページの最小値 */
	private static final int MIN_PAGE = 0;
	/** ページの最大値 */
	private static final int MAX_PAGE = 100;
	/** カウントの最小値 */
	private static final int MIN_COUNT = 0;
	/** カウントの最大値 */
	private static final int MAX_COUNT = 200;
	/** 人気順(HOT) */
	private static final String HOT = "hot";
	
	/** その日時よりも新しい投稿のみに絞り込むための日時を指定します。  */
	private Date since;
	/** 取得するページです。最大数は100です。 */
	private int page;
	/** 取得数を指定します。最大数は 200 です。 */
	private int count;
	/** 検索文字 */
	private String word;
	/** 関連付けキーワード1 */
	private String word1;
	/** 関連付けキーワード2 */
	private String word2;
	/** 人気順 */
	private String sort;
	
	/**
	 * コンストラクタです。
	 * 
	 * @since v0.0.1
	 */
	QueryParameter() {
		this(false);
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param isHot 人気順取得用かどうか
	 * @since v1.0.0
	 */
	QueryParameter(boolean isHot) {
		if (isHot) {
			this.sort = HOT;
		}
	}

	/**
	 * その日時よりも新しい投稿のみに絞り込むための日時を取得します。
	 * 
	 * @return その日時よりも新しい投稿のみに絞り込むための日時
	 * @since v0.0.1
	 */
	public Date getSince() {
		return since;
	}

	/**
	 * その日時よりも新しい投稿のみに絞り込むための日時を指定します。<br/>
	 * 指定された場合は、その日時よりも新しい投稿のみ返ります。
	 * 
	 * @param since その日時よりも新しい投稿のみに絞り込むための日時
	 * @since v0.0.1
	 */
	public void setSince(Date since) {
		this.since = since;
	}

	/** @return 取得するページ */
	public int getPage() {
		return page;
	}

	/**
	 * 取得するページを設定します。最大数は100です。
	 * <ul>
	 * <li>指定されたページが0より小さい場合、0になります。</li>
	 * <li>指定されたページが100より大きい場合、100になります。</li>
	 * <li>指定されたページが0の場合、デフォルトページ(1ページ目)を取得します。</li>
	 * </ul>
	 * 
	 * @param page 取得するページ
	 * @since v0.0.1
	 */
	public void setPage(int page) {
		this.page = Math.min(Math.max(page, MIN_PAGE), MAX_PAGE);
	}

	/**
	 * 取得数を取得します。
	 * 
	 * @return 取得数
	 * @since v0.0.1
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 取得数を設定します。最大数は 200
	 * <ul>
	 * <li>指定された取得数が0より小さい場合、0になります。</li>
	 * <li>指定された取得数が200より大きい場合、100になります。</li>
	 * <li>指定された取得数が0の場合、デフォルトの取得件数で取得します。</li>
	 * </ul>
	 * 
	 * @param count 取得数
	 * @since v0.0.1
	 */
	public void setCount(int count) {
		this.count = Math.min(Math.max(count, MIN_COUNT), MAX_COUNT);
	}
	
	/**
	 * 検索文字を取得します。
	 * 
	 * @return 検索文字 
	 * @since v0.0.1
	 */
	public String getWord() {
		return word;
	}

	/** 
	 * 検索文字を設定します。
	 * 
	 * @param word 検索文字
	 * @since v0.0.1
	 */
	public String setWord(String word) {
		return this.word = word;
	}
	
	/**
	 * 関連付けキーワード1を取得します。
	 * 
	 * @return 関連付けキーワード1 
	 * @since v0.0.1
	 */
	public String getWord1() {
		return word1;
	}

	/** 
	 * 関連付けキーワード1を設定します。
	 * 
	 * @param word1 関連付けキーワード1
	 * @since v0.0.1
	 */
	public String setWord1(String word1) {
		return this.word1 = word1;
	}

	/**
	 * 関連付けキーワード2を取得します。
	 * 
	 * @return 関連付けキーワード2
	 * @since v0.0.1
	 */
	public String getWord2() {
		return word2;
	}

	/** 
	 * 関連付けキーワード2を設定します。
	 * 
	 * @param word2 関連付けキーワード2
	 * @since v0.0.1
	 */
	public String setWord2(String word2) {
		return this.word2 = word2;
	}

	/**
	 * 人気順を取得します。
	 * 
	 * @return 人気順
	 * @since v1.0.0
	 */
	public String getSort() {
		return sort;
	}

	/** 
	 * 人気順を設定します。
	 * 
	 * @param sort 人気順
	 * @since v1.0.0
	 */
	public String setSort(String sort) {
		return this.sort = sort;
	}

	//-----------------------------------
	/** status */
	private static final String PARAM_KEY_PAGE = "page";
	/** in_reply_to_status_id */
	private static final String PARAM_KEY_COUNT = "count";
	/** keyword */
	private static final String PARAM_KEY_SINCE = "since";
	/** word */
	private static final String PARAM_KEY_WORD = "word";
	/** word1 */
	private static final String PARAM_KEY_WORD1 = "word1";
	/** word2 */
	private static final String PARAM_KEY_WORD2 = "word2";
	/** sort */
	private static final String PARAM_KEY_SORT = "sort";

	/**
	 * ポストするパラメータを設定します。
	 * 
	 * @param ps ポスト処理補助クラス
	 * @throws IOException 
	 * @since v1.0.0
	 */
	public void addParameter(PostStream ps) throws IOException {
		// page
		if (page > 0) {
			ps.addProperty(PARAM_KEY_PAGE, String.valueOf(page));
		}
		// count
		if (count > 0) {
			ps.addProperty(PARAM_KEY_COUNT, String.valueOf(count));
		}
		// since
		if (since != null) {
			ps.addProperty(PARAM_KEY_SINCE, DateUtil.toDefaultTZ(since));
		}
		// word
		if (word != null) {
			ps.addProperty(PARAM_KEY_WORD, word);
		}
		// word1
		if (word1 != null) {
			ps.addProperty(PARAM_KEY_WORD1, word1);
		}
		// word2
		if (word2 != null) {
			ps.addProperty(PARAM_KEY_WORD2, word2);
		}
		// sort
		if (sort != null) {
			ps.addProperty(PARAM_KEY_SORT, sort);
		}
	}

	/**
	 * ポスト内容を表示します。
	 * 
	 * @since v0.0.1
	 */
	public void outputPostInfo() {
		// ポスト内容の表示
		System.out.println("[count: " + count + "]");
		System.out.println("[page: " + page + "]");
		System.out.println("[since: " + since + "]");
		System.out.println("[word: " + word + "]");
		System.out.println("[word1: " + word1 + "]");
		System.out.println("[word2: " + word2 + "]");
		System.out.println("[sort: " + sort + "]");
	}
}
