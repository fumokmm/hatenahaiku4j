package hatenahaiku4j.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Fileに関するユーティリティクラス
 * 
 * @since v1.0.0
 * @author fumokmm
 */
public class FileUtil {

	/**
	 * ファイルをバイト配列に変換します。
	 * 
	 * @param file バイト配列に変換するファイル
	 * @since v1.0.0
	 */
	public static byte[] toByteArray(File file) {
		InputStream in = null;
		ByteArrayOutputStream out = null;
	    try {
	    	try {
				in = new BufferedInputStream(new FileInputStream(file));
		    	out = new ByteArrayOutputStream();
		    	byte[] buffer = new byte[8192];
		    	int rsize;
		    	while ((rsize = in.read(buffer)) != -1) {
		    		out.write(buffer, 0, rsize);
		    	}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return new byte[0];
			} catch (IOException e) {
				e.printStackTrace();
				return new byte[0];
			}
	    } finally {
	    	try {
	    		if (in != null) {
	    			in.close();
	    		}
	    		if (out != null) {
	    			out.flush();
	    			out.close();
	    		}
	    	} catch (Exception e) {
	    	}
	    }
	    
	    return out.toByteArray();
	}
	
}
