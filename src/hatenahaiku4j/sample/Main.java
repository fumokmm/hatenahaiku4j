package hatenahaiku4j.sample;

import hatenahaiku4j.HatenaHaikuAPI;
import hatenahaiku4j.HatenaHaikuAPIWithoutAuth;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.Status;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		LoginUser fumo = LoginUser.create("fumokmm", "xxxxxx");
		HatenaHaikuAPI api = new HatenaHaikuAPI(fumo);
		HatenaHaikuAPIWithoutAuth apiWithoutAuth = new HatenaHaikuAPIWithoutAuth();
		try {
			// パブリックタイムラインを取得
			List<Status> publicTimeline = apiWithoutAuth.getPublicTimeline();
			for (Status status : publicTimeline) {
				System.out.println("[" + status.getKeyword() + "] " + status.getText() + " by " + status.getUserId());
			}
			System.out.println("----------");
			// フレンドタイムラインを取得
			List<Status> friendTimeline = apiWithoutAuth.getFriendsTimeline("fumokmm");
			for (Status status : friendTimeline) {
				System.out.println("[" + status.getKeyword() + "] " + status.getText() + " by " + status.getUserId());
			}

			// 3重Replyテスト(idページ)
			String statusId = null;
			for (int i = 0; i < 3; i++) {
				if (statusId == null) {
					statusId = api.entry("3重Reply: " + i).getStatusId();
				} else {
					statusId = api.reply(statusId, "3重Reply: " + i).getStatusId();
				}
			}
		} catch( HatenaHaikuException hhe) {
			hhe.printStackTrace();
		}
	}
}
