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
 * HatenaHaiku4J 動作サンプルクラスです #003
 * 
 * @since v1.0.0
 * @author fumokmm
 */
public class Sample003 {
	
	/**
	 * v1.0.0から使えるようになった画像のアップロード処理のサンプルです。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ログインユーザ
		LoginUser loginUser = new LoginUser("userid", "password");
		// 認証ありAPI
		HatenaHaikuAPI apiAuth = new HatenaHaikuAPI(loginUser);
		apiAuth.setNeedHttpLog(true);	// HTTPログを出力する
		// 認証なしAPI
		HatenaHaikuAPILight apiLight = new HatenaHaikuAPILight();
		apiLight.setNeedHttpLog(true);	// HTTPログを出力する

		printSeparate("#003 Sample START");
		try {
			Keyword myIdPage = apiAuth.getMyKeyword();

			// entry
			printSeparate("ローカル画像ファイルをアップロード(jpg)");
			String text = "ローカル画像アップテスト(jpg)";
			// 例) c:/temp/test.jpg
			File file = new File("your local jpg image file path here...");
			Status result = myIdPage.api.entry(text, file);
			printStatus(result);

			// entry
			printSeparate("ローカル画像ファイルアップロード(png)");
			text = "ローカル画像アップテスト(png)";
			// 例) c:/temp/test.png
			file = new File("your local png image file path here...");
			result = myIdPage.api.entry(text, file);
			printStatus(result);
			
			// reply
			printSeparate("バイナリデータでアップロード(bmp)");
			text = "バイナリデータ(byte[])でローカル画像アップテスト(bmp)";
			// 例) c:/temp/test.bmp
			byte[] data = FileUtil.toByteArray(new File("your local bmp image file path here..."));
			result = result.api.reply(text, data, ImageExt.BMP);
			printStatus(result);

			// reply
			printSeparate("ネット上の画像ファイルをアップロード(gif)");
			text = "ネット上の画像アップテスト(gif)";
			// 例) http://h.hatena.ne.jp/images/haiku_logo.gif
			String url = "some jpg image file on the web URL here...";
			result = result.api.reply(text, url);
			printStatus(result);
			
		} catch(HatenaHaikuException e) {
			e.printStackTrace();
		}
		printSeparate("#003 Sample END");
	}
	
	/**
	 * ステータス情報を標準出力に出力します。
	 * 
	 * @param status ステータス情報
	 */
	public static void printStatus(Status status) {
		Sample001.printStatus(status);
	}

	/**
	 * 区切りを標準出力に出力します。
	 * 
	 * @param label ラベル
	 */
	public static void printSeparate(String label) {
		Sample001.printSeparate(label);
	}
}
