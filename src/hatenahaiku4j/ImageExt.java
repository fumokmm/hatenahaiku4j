package hatenahaiku4j;

/**
 * �摜�̊g���q
 * 
 * @author fumokmm
 * @since v1.0.0
 */
public enum ImageExt {
	/** JPG�摜 */
	JPG,
	/** JPEG�摜 */
	JPEG,
	/** GIF�摜 */
	GIF,
	/** PNG�摜 */
	PNG,
	/** BMP�摜 */
	BMP;
	
	/**
	 * �������Ŋg���q���擾����B
	 * 
	 * @return �������̊g���q�B
	 */
	public String getLowerCaseName() {
		return name().toLowerCase();
	}
}