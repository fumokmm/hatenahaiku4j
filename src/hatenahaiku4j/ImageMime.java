package hatenahaiku4j;

/**
 * 画像のマイムタイプ
 * 
 * @author fumokmm
 * @since v1.0.0
 */
public enum ImageMime {
	/** JPG画像 */
	JPG(ImageExt.JPG, "image/jpeg"),
	/** JPEG画像 */
	JPEG(ImageExt.JPEG, "image/jpeg"),
	/** GIF画像 */
	GIF(ImageExt.GIF, "image/gif"),
	/** PNG画像 */
	PNG(ImageExt.PNG, "image/png"),
	/** BMP画像 */
	BMP(ImageExt.BMP, "image/bmp");

	/** 拡張子 */
	private ImageExt ext;
	/** マイムタイプ */
	private String mimeType;
	
	/**
	 * コンストラクタです。
	 * 
	 * @param ext 拡張子
	 * @param mimeType マイムタイプ
	 */
	private ImageMime(ImageExt ext, String mimeType) {
		this.ext = ext;
		this.mimeType = mimeType;
	}
	
	/**
	 * マイムタイプを取得する。
	 * 
	 * @return マイムタイプ
	 */
	public String getMimeType() {
		return this.mimeType;
	}
	
	/**
	 * 拡張子を取得する。
	 * 
	 * @return 拡張子
	 */
	public String getExt() {
		return this.ext.name();
	}

	/**
	 * 引数の拡張子から拡張子のマイムタイプを取得します。
	 * 
	 * @param ext 拡張子
	 * @return マイムタイプ
	 */
	public static ImageMime getImageMime(ImageExt ext) {
		for (ImageMime mime : values()) {
			try {
				if (ImageMime.valueOf(ext.name()) == mime) {
					return mime;
				}
			} catch(IllegalArgumentException e) {}
		}
		return null;
	}
	
	/**
	 * 引数のファイル名から拡張子のマイムタイプを取得します。
	 * 
	 * @param fileName ファイル名
	 * @return マイムタイプ
	 */
	public static ImageMime getImageMime(String fileName) {
		String[] name = fileName.split("\\.");
		String ext = name[name.length - 1].toUpperCase();
		for (ImageMime mime : values()) {
			try {
				if (ImageMime.valueOf(ext) == mime) {
					return mime;
				}
			} catch(IllegalArgumentException e) {}
		}
		return null;
	}
	
	/**
	 * 一時的なファイル名
	 * 
	 * @return 一時的なファイル名
	 */
	public String toTemporaryFileName() {
		return this.mimeType.replace('/', '.').replaceAll("e", "");
	}
}