package hatenahaiku4j;

import hatenahaiku4j.util.DateUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * �₢���킹�p�p�����[�^��\������N���X�ł��B
 * 
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
	
	QueryParameter() {}

	/** @return ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓��� */
	public Date getSince() {
		return since;
	}

	/**
	 * ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓������w�肵�܂��B
	 * �w�肳�ꂽ�ꍇ�́A���̓��������V�������e�̂ݕԂ�܂��B
	 * 
	 * @param since ���̓��������V�������e�݂̂ɍi�荞�ނ��߂̓���
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
	 * �E�w�肳�ꂽ�y�[�W��0��菬�����ꍇ�A0�ɂȂ�܂��B
	 * �E�w�肳�ꂽ�y�[�W��100���傫���ꍇ�A100�ɂȂ�܂��B
	 * �E�w�肳�ꂽ�y�[�W��0�̏ꍇ�A�f�t�H���g�y�[�W(1�y�[�W��)���擾���܂��B
	 * 
	 * @param page �擾����y�[�W
	 */
	public void setPage(int page) {
		this.page = Math.min(Math.max(page, MIN_PAGE), MAX_PAGE);
	}

	/** @return �擾�� */
	public int getCount() {
		return count;
	}

	/**
	 * �擾����ݒ肵�܂��B�ő吔�� 200
	 * �E�w�肳�ꂽ�擾����0��菬�����ꍇ�A0�ɂȂ�܂��B
	 * �E�w�肳�ꂽ�擾����200���傫���ꍇ�A100�ɂȂ�܂��B
	 * �E�w�肳�ꂽ�擾����0�̏ꍇ�A�f�t�H���g�̎擾�����Ŏ擾���܂��B
	 * 
	 * @param count �擾��
	 */
	public void setCount(int count) {
		this.count = Math.min(Math.min(count, MIN_COUNT), MAX_COUNT);
	}
	
	/** @return �������� */
	public String getWord() {
		return word;
	}

	/** 
	 * ����������ݒ肵�܂��B
	 * 
	 * @param word ��������
	 */
	public String setWord(String word) {
		return this.word = word;
	}
	
	/** @return �֘A�t���L�[���[�h1 */
	public String getWord1() {
		return word1;
	}

	/** 
	 * �֘A�t���L�[���[�h1��ݒ肵�܂��B
	 * 
	 * @param word1 �֘A�t���L�[���[�h1
	 */
	public String setWord1(String word1) {
		return this.word1 = word1;
	}

	/** @return �֘A�t���L�[���[�h2 */
	public String getWord2() {
		return word2;
	}

	/** 
	 * �֘A�t���L�[���[�h2��ݒ肵�܂��B
	 * 
	 * @param word2 �֘A�t���L�[���[�h2
	 */
	public String setWord2(String word2) {
		return this.word2 = word2;
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

	/**
	 * �p�����[�^�ɕϊ����܂��B
	 * 
	 * @return �p�����[�^������
	 * @throws UnsupportedEncodingException, APINotSupportedException 
	 */
	public String toParameter() throws HatenaHaikuException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// page
			if (page > 0) {
				map.put(PARAM_KEY_PAGE, String.valueOf(page));
			}
			// count
			if (count > 0) {
				map.put(PARAM_KEY_COUNT, String.valueOf(count));
			}
			// since
			if (since != null) {
				map.put(PARAM_KEY_SINCE, DateUtil.toDefaultTZ(since));
			}
			// word
			if (word != null) {
				map.put(PARAM_KEY_WORD, word);
			}
			// word1
			if (word1 != null) {
				map.put(PARAM_KEY_WORD1, word1);
			}
			// word2
			if (word2 != null) {
				map.put(PARAM_KEY_WORD2, word2);
			}
	
			StringBuilder buffer = new StringBuilder();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (buffer.length() > 0) buffer.append(Const.AMP);
				buffer.append(entry.getKey())
				.append(Const.EQUAL)
				.append(URLEncoder.encode(entry.getValue(), Const.UTF8));
			}
			return buffer.toString();

		} catch (UnsupportedEncodingException e) {
			throw new HatenaHaikuException("�G���R�[�f�B���O���s��", e);
		}
	}

	public void outputPostInfo() {
		// �|�X�g���e�̕\��
		System.out.println("[count: " + count + "]");
		System.out.println("[page: " + page + "]");
		System.out.println("[since: " + since + "]");
		System.out.println("[word: " + word + "]");
		System.out.println("[word1: " + word1 + "]");
		System.out.println("[word2: " + word2 + "]");
	}
}
