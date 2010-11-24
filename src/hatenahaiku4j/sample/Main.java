package hatenahaiku4j.sample;

import java.util.List;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.Keyword;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.HatenaUtil;
import hatenahaiku4j.util.StringUtil;

/**
 * HatenaHaiku4J ����T���v���N���X�ł�
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Main {
	
	/**
	 * ���C�������ł��B
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ���O�C�����[�U
		LoginUser loginUser = LoginUser.create("userid", "password");
		// �F�؂���API
		HatenaHaikuAPI api = new HatenaHaikuAPI(loginUser);
		// �F�؂Ȃ�API
		HatenaHaikuAPILight apiLight = new HatenaHaikuAPILight();

		try {
//			demo_v0_0_1(api, apiLight);	// v0.0.1�f��
			demo_v0_2_0(api, apiLight);	// v0.2.0�f��
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * v0.0.1���_�ł̃f���ł��B
	 * 
	 * @param api �F�؂���API
	 * @param apiLight �F�؂Ȃ�API
	 */
	private static void demo_v0_0_1(HatenaHaikuAPI api, HatenaHaikuAPILight apiLight)
		throws HatenaHaikuException {

		printSeparate("�p�u���b�N�^�C�����C�����o�͂���");
		List<Status> publicTimeline = apiLight.getPublicTimeline();
		for (Status status : publicTimeline) {
			printStatus(status);
		}

		printSeparate("�L�[���[�h�^�C�����C�����o�͂���(�ЂƂ育��)");
		List<Status> hitorigotoTimeline = apiLight.getKeywordTimeline("�ЂƂ育��");
		// apiLight�𗘗p���Ă��܂����Aapi�𗘗p���邱�Ƃ��\�ł��B
		for (Status status : hitorigotoTimeline) {
			printStatus(status);
		}
		
		printSeparate("���O�C�����[�U��id�y�[�W�ɓ��e����B");
		Status result = api.entry("����ɂ���\n�͂Ăȃn�C�N�S�i�I\n�f���e�X�g���ł��B");
		printStatus(result);
		
		printSeparate("���łɍ��������o�^���������̃G���g���ɃX�^�[��3�قǂ��Ă݂�");
		String targetStatusId = result.getStatusId();
		for (int i = 0; i < 3; i++) {
			Status addStarResult = api.addStar(targetStatusId);
			printStatus(addStarResult);
		}

		printSeparate("����ɁAReply���Ă݂�");
		Status replyResult = api.reply(targetStatusId, "���v���C���ł��܂��B");
		printStatus(replyResult);
	}
	
	/**
	 * v0.2.0���_�ł̃f���ł��B<br/>
	 * v0.2.0������_��Ɏ擾���ʂ���F�X�ł���悤�ɂȂ�܂����B<br/>
	 * �f�����e��v0.0.1���_�Ɠ������Ƃ�����ɁA���[�U��ID�y�[�W�̃^�C�����C�����擾���Ă��܂��B
	 * 
	 * @param api �F�؂���API
	 * @param apiLight �F�؂Ȃ�API
	 */
	private static void demo_v0_2_0(HatenaHaikuAPI api, HatenaHaikuAPILight apiLight)
		throws HatenaHaikuException {

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
		
		printSeparate("���O�C�����[�U��id�y�[�W�ɓ��e����B");
		Keyword idPage = api.getMyKeyword();
		Status result = idPage.api.entry("����ɂ���\n�͂Ăȃn�C�N�S�i�I\n�f���e�X�g���ł��B");
		printStatus(result);
		
		printSeparate("���łɍ��������o�^���������̃G���g���ɃX�^�[��3�قǂ��Ă݂�");
		for (int i = 0; i < 3; i++) {
			printStatus(result.api.addStar());
		}

		printSeparate("����ɁAReply���Ă݂�");
		printStatus(result.api.reply("���v���C���ł��܂��B"));
		
		printSeparate("���O�C�����[�U��id�y�[�W�^�C�����C�����o�͂���");
		for (Status idStatus : api.getMe().api.getIdTimeline()) {
			printStatus(idStatus);
		}
	}

	/**
	 * �X�e�[�^�X����W���o�͂ɏo�͂��܂��B
	 * 
	 * @param status �X�e�[�^�X���
	 */
	private static void printStatus(Status status) {
		StringBuilder sb = new StringBuilder();
		sb.append("�y").append(status.getKeyword()).append("�z");
		sb.append(HatenaUtil.formatDate(status.getCreatedAt()));
		sb.append("�k���~").append(status.getFavorited()).append("�l");
		sb.append(status.getText());
		if (!StringUtil.isEmpty(status.getInReplyToStatusId())) {
			sb.append(" reply to ").append(status.getInReplyToUserId());
		}
		sb.append(" by ").append(status.getUserId());
		sb.append(" from ").append(status.getSource());
		if (!status.getReplies().isEmpty()) {
			StringBuilder replies = new StringBuilder();
			for (Status reply : status.getReplies()) {
				if (replies.length() > 0) {
					replies.append(", ");
				}
				replies.append(reply.getUserId());
			}
			sb.append(" replied from ").append(replies);
		}
		System.out.println(sb.toString());
	}

	/**
	 * ��؂��W���o�͂ɏo�͂��܂��B
	 * 
	 * @param label ���x��
	 */
	private static void printSeparate(String label) {
		System.out.println("----------" + label + "----------");
	}
}
