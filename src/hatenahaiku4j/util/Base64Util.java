package hatenahaiku4j.util;

/**
 * Base64エンコード／デコードに関するユーティリティクラス
 * 
 * @since v0.0.1
 * @author fumokmm
 */
public class Base64Util {

	/** 変換テーブル */
	private static final char[] T_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();

	/**
	 * 指定された文字列をBASE64エンコードして返却します。
	 *
	 * @param value　変換前の文字列
	 * @return BASE64エンコードした文字列
	 * @since v0.0.1
	 */
	public static String encodeBase64(String value) {
		final byte[] data = value.getBytes();
		final int dLimit = (data.length / 3) * 3;
		final StringBuilder buffer = new StringBuilder();
        
		for (int dIndex = 0; dIndex != dLimit; dIndex += 3) {
			int d = ((data[dIndex] & 0XFF) << 16) | ((data[dIndex + 1] & 0XFF) << 8) | (data[dIndex + 2] & 0XFF);
			
			buffer.append(T_TABLE[d >> 18]);
			buffer.append(T_TABLE[(d >> 12) & 0X3F]);
			buffer.append(T_TABLE[(d >> 6) & 0X3F]);
			buffer.append(T_TABLE[d & 0X3F]);
		}

		if (dLimit != data.length) {
			int d = (data[dLimit] & 0XFF) << 16;
			
			if (dLimit + 1 != data.length) {
				d |= (data[dLimit + 1] & 0XFF) << 8;
			}
			
			buffer.append(T_TABLE[d >> 18]);
			buffer.append(T_TABLE[(d >> 12) & 0X3F]);
			buffer.append((dLimit + 1 < data.length) ? T_TABLE[(d >> 6) & 0X3F] : '=');
			buffer.append('=');
		}

		return buffer.toString();
	}

	/** 翻訳テーブル */
	private static final byte[] TRANSLATE_TABLE = (
	        //
			"\u0042\u0042\u0042\u0042\u0042\u0042\u0042\u0042"
			//                    \t    \n                \r
			+ "\u0042\u0042\u0041\u0041\u0042\u0042\u0041\u0042"
			//
			+ "\u0042\u0042\u0042\u0042\u0042\u0042\u0042\u0042"
			//
			+ "\u0042\u0042\u0042\u0042\u0042\u0042\u0042\u0042"
			//        sp    !     "     #     $     %     &     '
			+ "\u0041\u0042\u0042\u0042\u0042\u0042\u0042\u0042"
			//         (    )     *     +     ,     -     .     /
			+ "\u0042\u0042\u0042\u003E\u0042\u0042\u0042\u003F"
			//         0    1     2     3     4     5     6     7
			+ "\u0034\u0035\u0036\u0037\u0038\u0039\u003A\u003B"
			//         8    9     :     ;     <     =     >     ?
			+ "\u003C\u003D\u0042\u0042\u0042\u0040\u0042\u0042"
			//         @    A     B     C     D     E     F     G
			+ "\u0042\u0000\u0001\u0002\u0003\u0004\u0005\u0006"
			//         H    I   J K   L     M   N   O
			+ "\u0007\u0008\t\n\u000B\u000C\r\u000E"
			//         P    Q     R     S     T     U     V    W
			+ "\u000F\u0010\u0011\u0012\u0013\u0014\u0015\u0016"
			//         X    Y     Z     [     \     ]     ^    _
			+ "\u0017\u0018\u0019\u0042\u0042\u0042\u0042\u0042"
			//         '    a     b     c     d     e     f     g
			+ "\u0042\u001A\u001B\u001C\u001D\u001E\u001F\u0020"
			//        h   i   j     k     l     m     n     o    p
			+ "\u0021\"\u0023\u0024\u0025\u0026\u0027\u0028"
			//        p     q     r     s     t     u     v     w
			+ "\u0029\u002A\u002B\u002C\u002D\u002E\u002F\u0030"
			//        x     y     z
			+ "\u0031\u0032\u0033"
	).getBytes();

	/**
	 * BASE64エンコードされた文字列をBASE64デコードして返却します。
	 *
	 * @param value BASE64エンコードされた文字列
	 * @return BASE64デコードした文字列
	 * @since v0.0.1
	 */
	public static String decodeBase64(String value) {
		int byteShift = 4;
		int tmp = 0;
		boolean done = false;
		final StringBuilder buffer = new StringBuilder();

		for (int i = 0; i != value.length(); i++) {
			final char c = value.charAt(i);
			final int sixBit = (c < 123) ? TRANSLATE_TABLE[c] : 66;
			
			if (sixBit < 64) {
				if (done) {
					throw new RuntimeException("= character not at end of base64 value");
				}
				tmp = (tmp << 6) | sixBit;
				
				if (byteShift-- != 4) {
					buffer.append((char) ((tmp >> (byteShift * 2)) & 0XFF));
				}

			} else if (sixBit == 64) {
				byteShift--;
				done = true;
			
			} else if (sixBit == 66) {
				throw new RuntimeException("bad character in base64 value");
			}
			
			if (byteShift == 0) byteShift = 4;
		}
		
		return buffer.toString();
	}
}
