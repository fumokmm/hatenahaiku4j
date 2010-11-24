package hatenahaiku4j.sample;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.User;

import java.util.List;

/**
 * HatenaHaiku4J ����T���v���N���X�ł� #002
 * 
 * @since v0.2.1
 * @author fumokmm
 */
public class Sample002 {
	
	/**
	 * v0.2.1���_�ł̃f���ł��B<br/>
	 * v0.2.0������_��Ɏ擾���ʂ���F�X�ł���悤�ɂȂ�܂����B<br/>
	 * �f�����e��v0.0.1���_�Ɠ������Ƃ�����ɁA���[�U��ID�y�[�W�̃^�C�����C�����擾���Ă��܂��B
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

		printSeparate("#002 Sample START");
		try {
			printSeparate("�p�u���b�N�^�C�����C�����o�͂���");
			List<Status> publicTimeline = apiLight.getPublicTimeline();
			for (Status status : publicTimeline) {
				printStatus(status);
			}

			printSeparate("�L�[���[�h�^�C�����C�����o�͂���(�ЂƂ育��)");
			Keyword hitorigoto = apiLight.getKeyword("�ЂƂ育��");
			// Keyword, Status, User �� #api �Ƃ����t�B�[���h���t���������߁A
			// �e�C���X�^���X����api���Ăяo����悤�ɂȂ�܂����B
			for (Status status : hitorigoto.api.getTimeline()) {
				printStatus(status);
			}
			
			printSeparate("�F�؂������[�U�̐l�C�̃��[�U�^�C�����C�����o�͂���");
			User me = apiAuth.getMe();	// �F�؂������[�U���g��User���擾
			List<Status> hotUserTimeline = me.api.getHotTimeline();
			for (Status status : hotUserTimeline) {
				printStatus(status);
			}

			printSeparate("���O�C�����[�U��id�y�[�W�ɓ��e����B");
			Keyword idPage = apiAuth.getMyKeyword(); // �F�؂������[�U�̃L�[���[�h(=id�y�[�W)���擾
			Status result = idPage.api.entry("����ɂ���\n�͂Ăȃn�C�N�S�i�I\n�f���e�X�g���ł��B");
			printStatus(result);
			
			printSeparate("���łɍ��������o�^���������̃G���g���ɃX�^�[��3�قǂ��Ă݂�");
			for (int i = 0; i < 3; i++) {
				printStatus(result.api.addStar());
			}

			printSeparate("����ɁAReply���Ă݂�");
			printStatus(result.api.reply("���v���C���ł��܂��B"));
			
			printSeparate("���O�C�����[�U��id�y�[�W�^�C�����C�����o�͂���");
			for (Status idStatus : apiAuth.getMe().api.getIdTimeline()) {
				printStatus(idStatus);
			}
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#002 Sample END");
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
