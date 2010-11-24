package hatenahaiku4j.sample;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.ImageExt;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.FileUtil;

import java.io.File;

/**
 * HatenaHaiku4J ����T���v���N���X�ł� #003
 * 
 * @since v1.0.0
 * @author fumokmm
 */
public class Sample003 {
	
	/**
	 * v1.0.0����g����悤�ɂȂ����摜�̃A�b�v���[�h�����̃T���v���ł��B
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

		printSeparate("#003 Sample START");
		try {
			Keyword myIdPage = apiAuth.getMyKeyword();

			// entry
			printSeparate("���[�J���摜�t�@�C�����A�b�v���[�h(jpg)");
			String text = "���[�J���摜�A�b�v�e�X�g(jpg)";
			// ��) c:/temp/test.jpg
			File file = new File("your local jpg image file path here...");
			Status result = myIdPage.api.entry(text, file);
			printStatus(result);

			// entry
			printSeparate("���[�J���摜�t�@�C���A�b�v���[�h(png)");
			text = "���[�J���摜�A�b�v�e�X�g(png)";
			// ��) c:/temp/test.png
			file = new File("your local png image file path here...");
			result = myIdPage.api.entry(text, file);
			printStatus(result);
			
			// reply
			printSeparate("�o�C�i���f�[�^�ŃA�b�v���[�h(bmp)");
			text = "�o�C�i���f�[�^(byte[])�Ń��[�J���摜�A�b�v�e�X�g(bmp)";
			// ��) c:/temp/test.bmp
			byte[] data = FileUtil.toByteArray(new File("your local bmp image file path here..."));
			result = result.api.reply(text, data, ImageExt.BMP);
			printStatus(result);

			// reply
			printSeparate("�l�b�g��̉摜�t�@�C�����A�b�v���[�h(gif)");
			text = "�l�b�g��̉摜�A�b�v�e�X�g(gif)";
			// ��) http://h.hatena.ne.jp/images/haiku_logo.gif
			String url = "some jpg image file on the web URL here...";
			result = result.api.reply(text, url);
			printStatus(result);
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#003 Sample END");
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
