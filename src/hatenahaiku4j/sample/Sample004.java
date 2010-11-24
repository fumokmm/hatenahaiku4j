package hatenahaiku4j.sample;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.HaikuURL;
import hatenahaiku4j.util.HatenaUtil;

/**
 * HatenaHaiku4J ����T���v���N���X�ł� #004
 * 
 * @since v1.0.1
 * @author fumokmm
 */
public class Sample004 {
	
	/**
	 * v1.0.1����g����悤�ɂȂ���HaikuURL��<br/>
	 * HatenaUtil��#escapeHatenaNotation(String)�̃T���v���ł��B
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ���O�C�����[�U
		LoginUser loginUser = new LoginUser("userid", "password");
		// �F�؂���API
		HatenaHaikuAPI apiAuth = new HatenaHaikuAPI(loginUser);
		apiAuth.setNeedHttpLog(true);	// HTTP���O���o�͂���
		// �F�؂Ȃ�API
		HatenaHaikuAPILight apiLight = new HatenaHaikuAPILight();
		apiLight.setNeedHttpLog(true);	// HTTP���O���o�͂���

		printSeparate("#004 Sample START");
		try {
			// �G���g�����e���쐬
			StringBuilder entryContents = new StringBuilder();

			HaikuURL idUrl = HaikuURL.byUserId(loginUser.getUserId());
			entryContents.append("id�y�[�W�����N�i�ʏ��:id�R�[������j\n");
			entryContents.append(idUrl.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("id�y�[�W�����N�i�G�X�P�[�v��:id�R�[���Ȃ��j\n");
			entryContents.append(idUrl.getEscapedLink() + "\n");
			entryContents.append(" \n");
			HaikuURL idUrl2 = HaikuURL.byUserId(loginUser.getUserId(), loginUser.getUserIdNotation() + "����Ƃ�");
			entryContents.append("���x���Œu��������id�y�[�W�����N�i�ʏ��:id�R�[������j\n");
			entryContents.append(idUrl2.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("���x���Œu��������id�y�[�W�����N�i�G�X�P�[�v��:id�R�[���Ȃ��j\n");
			entryContents.append(idUrl2.getEscapedLink() + "\n");
			entryContents.append(" \n");

			HaikuURL keywordUrl = HaikuURL.byKeyword(loginUser.getUserIdNotation() + "�����ǁAHatenaHaiku4J�̃e�X�g�����I");
			entryContents.append("�L�[���[�h�y�[�W�����N�i�ʏ��:id�R�[������j\n");
			entryContents.append(keywordUrl.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("�L�[���[�h�y�[�W�����N�i�G�X�P�[�v��:id�R�[���Ȃ��j\n");
			entryContents.append(keywordUrl.getEscapedLink() + "\n");
			entryContents.append(" \n");
			HaikuURL keywordUrl2 = HaikuURL.byKeyword(loginUser.getUserIdNotation() + "�����ǁAHatenaHaiku4J�̃e�X�g�����I", "[[" + loginUser.getUserIdNotation() + "�����ǁAHatenaHaiku4J�̃e�X�g�����I" + "]]");
			entryContents.append("���x���Œu���������L�[���[�h�y�[�W�����N�i�ʏ��:id�R�[������j\n");
			entryContents.append(keywordUrl2.getLink() + "\n");
			entryContents.append(" \n");
			entryContents.append("�L�[���[�h�y�[�W�����N�i�G�X�P�[�v��:id�R�[���Ȃ��j\n");
			entryContents.append(keywordUrl2.getEscapedLink() + "\n");
			entryContents.append(" \n");

			HaikuURL urlUrl = HaikuURL.byURL("http://h.hatena.ne.jp");
			entryContents.append("URL�����N\n");
			entryContents.append(urlUrl.getLink() + "\n");
			entryContents.append(" \n");
			HaikuURL urlUrl2 = HaikuURL.byURL("http://h.hatena.ne.jp", "�͂Ăȃn�C�N");
			entryContents.append("���x���Œu��������URL�����N\n");
			entryContents.append(urlUrl2.getLink() + "\n");
			entryContents.append(" \n");
			
			HaikuURL asinUrl = HaikuURL.byASIN("4798110523");
			entryContents.append("ASIN�����N\n");
			entryContents.append(asinUrl.getLink() + "\n");
			entryContents.append(" \n");

			HaikuURL youtubeUrl = HaikuURL.byYouTube("HaYs1Ni4ts0");
			entryContents.append("YouTube�����N\n");
			entryContents.append(youtubeUrl.getLink() + "\n");
			entryContents.append(" \n");

			HaikuURL nico2Url = HaikuURL.byNico2("sm2476246");
			entryContents.append("�j�R�j�R���惊���N\n");
			entryContents.append(nico2Url.getLink() + "\n");
			entryContents.append(" \n");
			
			// �G�X�P�[�v�f��
			entryContents.append("------------------------\n");
			entryContents.append(HatenaUtil.escapeHatenaNotation("HatenaUtil#escapeHatenaNotation(String)���g���ƁA\n"));
			entryContents.append(HatenaUtil.escapeHatenaNotation("id:xxxxx�Ȃǂ̂悤��id�R�[����[[�L�[���[�h�����N]]�̃T���v���Ȃǂ�\n"));
			entryContents.append(HatenaUtil.escapeHatenaNotation("�G���g�����������ꍇ�ɁA�K�؂ɃG�X�P�[�v���Ă���܂��B\n"));
			entryContents.append(HatenaUtil.escapeHatenaNotation("HaikuURL�����ł����̃��\�b�h�𗘗p���Ă��܂��B\n"));

			// ���O�C�����[�U��id�y�[�W�ɃG���g��
			Keyword myIdPage = apiAuth.getMyKeyword();
			printStatus(myIdPage.api.entry(entryContents.toString()));
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#004 Sample END");
	}
	
	/**
	 * �X�e�[�^�X����W���o�͂ɏo�͂��܂��B
	 * 
	 * @param status �X�e�[�^�X���
	 */
	public static void printStatus(Status status) {
		Sample001.printStatus(status);
	}

	/**
	 * ��؂��W���o�͂ɏo�͂��܂��B
	 * 
	 * @param label ���x��
	 */
	public static void printSeparate(String label) {
		Sample001.printSeparate(label);
	}
}
