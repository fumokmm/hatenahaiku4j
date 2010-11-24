package hatenahaiku4j;

import hatenahaiku4j.util.DateUtil;
import hatenahaiku4j.util.HttpUtil.PostStream;

import java.io.IOException;
import java.util.Date;

/**
 * �₢���킹�p�p�����[�^��\������N���X�ł��B
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class QueryParameter {
	/** �y�[�W�̍ŏ��l */
	private static final int MIN_PAGE = 0;
	/** �y�[�W�̍ő�l */
	private static final int MAX_PAGE = 100;
	/** �J�E���g�̍ŏ��l */
	private static final int MIN_COUNT = 0;
	/** �J�E���g�̍ő�l */
	private static final int MAX_COUNT = 200;
	/** �l�C��(HOT) */
	private static final String HOT = "hot";
	
	/** ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B  */
	private Date since;
	/** �擾����y�[�W�ł��B�ő吔��100�ł��B */
	private int page;
	/** �擾�����w�肵�܂��B�ő吔�� 200 �ł��B */
	private int count;
	/** �������� */
	private String word;
	/** �֘A�t���L�[���[�h1 */
	private String word1;
	/** �֘A�t���L�[���[�h2 */
	private String word2;
	/** �l�C�� */
	private String sort;
	
	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @since v0.0.1
	 */
	QueryParameter() {
		this(false);
	}

	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param isHot �l�C���擾�p���ǂ���
	 * @since v1.0.0
	 */
	QueryParameter(boolean isHot) {
		if (isHot) {
			this.sort = HOT;
		}
	}

	/**
	 * ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������擾���܂��B
	 * 
	 * @return ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓���
	 * @since v0.0.1
	 */
	public Date getSince() {
		return since;
	}

	/**
	 * ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B<br/>
	 * �w�肳�ꂽ�ꍇ�́A���̓��������V�������e�̂ݕԂ�܂��B
	 * 
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓���
	 * @since v0.0.1
	 */
	public void setSince(Date since) {
		this.since = since;
	}

	/** @return �擾����y�[�W */
	public int getPage() {
		return page;
	}

	/**
	 * �擾����y�[�W��ݒ肵�܂��B�ő吔��100�ł��B
	 * <ul>
	 * <li>�w�肳�ꂽ�y�[�W��0��菬�����ꍇ�A0�ɂȂ�܂��B</li>
	 * <li>�w�肳�ꂽ�y�[�W��100���傫���ꍇ�A100�ɂȂ�܂��B</li>
	 * <li>�w�肳�ꂽ�y�[�W��0�̏ꍇ�A�f�t�H���g�y�[�W(1�y�[�W��)���擾���܂��B</li>
	 * </ul>
	 * 
	 * @param page �擾����y�[�W
	 * @since v0.0.1
	 */
	public void setPage(int page) {
		this.page = Math.min(Math.max(page, MIN_PAGE), MAX_PAGE);
	}

	/**
	 * �擾�����擾���܂��B
	 * 
	 * @return �擾��
	 * @since v0.0.1
	 */
	public int getCount() {
		return count;
	}

	/**
	 * �擾����ݒ肵�܂��B�ő吔�� 200
	 * <ul>
	 * <li>�w�肳�ꂽ�擾����0��菬�����ꍇ�A0�ɂȂ�܂��B</li>
	 * <li>�w�肳�ꂽ�擾����200���傫���ꍇ�A100�ɂȂ�܂��B</li>
	 * <li>�w�肳�ꂽ�擾����0�̏ꍇ�A�f�t�H���g�̎擾�����Ŏ擾���܂��B</li>
	 * </ul>
	 * 
	 * @param count �擾��
	 * @since v0.0.1
	 */
	public void setCount(int count) {
		this.count = Math.min(Math.max(count, MIN_COUNT), MAX_COUNT);
	}
	
	/**
	 * �����������擾���܂��B
	 * 
	 * @return �������� 
	 * @since v0.0.1
	 */
	public String getWord() {
		return word;
	}

	/** 
	 * ����������ݒ肵�܂��B
	 * 
	 * @param word ��������
	 * @since v0.0.1
	 */
	public String setWord(String word) {
		return this.word = word;
	}
	
	/**
	 * �֘A�t���L�[���[�h1���擾���܂��B
	 * 
	 * @return �֘A�t���L�[���[�h1 
	 * @since v0.0.1
	 */
	public String getWord1() {
		return word1;
	}

	/** 
	 * �֘A�t���L�[���[�h1��ݒ肵�܂��B
	 * 
	 * @param word1 �֘A�t���L�[���[�h1
	 * @since v0.0.1
	 */
	public String setWord1(String word1) {
		return this.word1 = word1;
	}

	/**
	 * �֘A�t���L�[���[�h2���擾���܂��B
	 * 
	 * @return �֘A�t���L�[���[�h2
	 * @since v0.0.1
	 */
	public String getWord2() {
		return word2;
	}

	/** 
	 * �֘A�t���L�[���[�h2��ݒ肵�܂��B
	 * 
	 * @param word2 �֘A�t���L�[���[�h2
	 * @since v0.0.1
	 */
	public String setWord2(String word2) {
		return this.word2 = word2;
	}

	/**
	 * �l�C�����擾���܂��B
	 * 
	 * @return �l�C��
	 * @since v1.0.0
	 */
	public String getSort() {
		return sort;
	}

	/** 
	 * �l�C����ݒ肵�܂��B
	 * 
	 * @param sort �l�C��
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
	 * �|�X�g����p�����[�^��ݒ肵�܂��B
	 * 
	 * @param ps �|�X�g�����⏕�N���X
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
	 * �|�X�g���e��\�����܂��B
	 * 
	 * @since v0.0.1
	 */
	public void outputPostInfo() {
		// �|�X�g���e�̕\��
		System.out.println("[count: " + count + "]");
		System.out.println("[page: " + page + "]");
		System.out.println("[since: " + since + "]");
		System.out.println("[word: " + word + "]");
		System.out.println("[word1: " + word1 + "]");
		System.out.println("[word2: " + word2 + "]");
		System.out.println("[sort: " + sort + "]");
	}
}
