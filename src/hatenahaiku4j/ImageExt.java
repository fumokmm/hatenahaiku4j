package hatenahaiku4j;

/**
 * 画像の拡張子
 * 
 * @author fumokmm
 * @since v1.0.0
 */
public enum ImageExt {
	/** JPG画像 */
	JPG,
	/** JPEG画像 */
	JPEG,
	/** GIF画像 */
	GIF,
	/** PNG画像 */
	PNG,
	/** BMP画像 */
	BMP;
	
	/**
	 * 小文字で拡張子を取得する。
	 * 
	 * @return 小文字の拡張子。
	 */
	public String getLowerCaseName() {
		return name().toLowerCase();
	}
}