package hatenahaiku4j.sample;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPILight;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;
import hatenahaiku4j.util.HatenaUtil;
import hatenahaiku4j.util.StringUtil;

import java.util.List;

/**
 * HatenaHaiku4J ����T���v���N���X�ł� #001
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Sample001 {
	
	/**
	 * v0.0.1���_�ł̃f���ł��B
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

		printSeparate("#001 Sample START");
		try {
			printSeparate("�p�u���b�N�^�C�����C�����o�͂���");
			List<Status> publicTimeline = apiLight.getPublicTimeline();
			for (Status status : publicTimeline) {
				printStatus(status);
			}

			printSeparate("�L�[���[�h�^�C�����C�����o�͂���(�ЂƂ育��)");
			List<Status> hitorigotoTimeline = apiLight.getKeywordTimeline("�ЂƂ育��");
			// apiLight�𗘗p���Ă��܂����AapiAuth�𗘗p���邱�Ƃ��\�ł��B
			for (Status status : hitorigotoTimeline) {
				printStatus(status);
			}
			
			printSeparate("�F�؂������[�U�̐l�C�̃��[�U�^�C�����C�����o�͂���");
			List<Status> hotUserTimeline = apiAuth.getHotUserTimeline();
			// apiLight�𗘗p���Ă��܂����AapiAuth�𗘗p���邱�Ƃ��\�ł��B
			for (Status status : hotUserTimeline) {
				printStatus(status);
			}

			printSeparate("���O�C�����[�U��id�y�[�W�ɓ��e����B");
			Status result = apiAuth.entry("����ɂ���\n�͂Ăȃn�C�N�S�i�I\n�f���e�X�g���ł��B");
			printStatus(result);
			
			printSeparate("���łɍ��������o�^���������̃G���g���ɃX�^�[��3�قǂ��Ă݂�");
			String targetStatusId = result.getStatusId();
			for (int i = 0; i < 3; i++) {
				Status addStarResult = apiAuth.addStar(targetStatusId);
				printStatus(addStarResult);
			}

			printSeparate("����ɁAReply���Ă݂�");
			Status replyResult = apiAuth.reply(targetStatusId, "���v���C���ł��܂��B");
			printStatus(replyResult);
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#001 Sample END");
	}
	
	/**
	 * �X�e�[�^�X����W���o�͂ɏo�͂��܂��B
	 * 
	 * @param status �X�e�[�^�X���
	 */
	public static void printStatus(Status status) {
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
	public static void printSeparate(String label) {
		System.out.println("----------" + label + "----------");
	}
}
