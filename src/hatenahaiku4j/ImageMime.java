package hatenahaiku4j;

/**
 * �摜�̃}�C���^�C�v
 * 
 * @author fumokmm
 * @since v1.0.0
 */
public enum ImageMime {
	/** JPG�摜 */
	JPG(ImageExt.JPG, "image/jpeg"),
	/** JPEG�摜 */
	JPEG(ImageExt.JPEG, "image/jpeg"),
	/** GIF�摜 */
	GIF(ImageExt.GIF, "image/gif"),
	/** PNG�摜 */
	PNG(ImageExt.PNG, "image/png"),
	/** BMP�摜 */
	BMP(ImageExt.BMP, "image/bmp");

	/** �g���q */
	private ImageExt ext;
	/** �}�C���^�C�v */
	private String mimeType;
	
	/**
	 * �R���X�g���N�^�ł��B
	 * 
	 * @param ext �g���q
	 * @param mimeType �}�C���^�C�v
	 */
	private ImageMime(ImageExt ext, String mimeType) {
		this.ext = ext;
		this.mimeType = mimeType;
	}
	
	/**
	 * �}�C���^�C�v���擾����B
	 * 
	 * @return �}�C���^�C�v
	 */
	public String getMimeType() {
		return this.mimeType;
	}
	
	/**
	 * �g���q���擾����B
	 * 
	 * @return �g���q
	 */
	public String getExt() {
		return this.ext.name();
	}

	/**
	 * �����̊g���q����g���q�̃}�C���^�C�v���擾���܂��B
	 * 
	 * @param ext �g���q
	 * @return �}�C���^�C�v
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
	 * �����̃t�@�C��������g���q�̃}�C���^�C�v���擾���܂��B
	 * 
	 * @param fileName �t�@�C����
	 * @return �}�C���^�C�v
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
	 * �ꎞ�I�ȃt�@�C����
	 * 
	 * @return �ꎞ�I�ȃt�@�C����
	 */
	public String toTemporaryFileName() {
		return this.mimeType.replace('/', '.').replaceAll("e", "");
	}
}