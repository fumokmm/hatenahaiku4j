package hatenahaiku4j;

/**
 * �͂Ăȃn�C�N�G���e�B�e�B�i���[�U�A�X�e�[�^�X�A�L�[���[�h�j�Ɋւ���API�̃x�[�X�N���X
 * 
 * @since v0.2.0
 * @author fumokmm
 */
class EntityAPI {
	/** �͂Ăȃn�C�NAPI�i�F�؂���j */
	protected HatenaHaikuAPI apiAuth;
	/** �͂Ăȃn�C�NAPI�i�F�؂Ȃ��j */
	protected HatenaHaikuAPILight apiLight;

	/**
	 * �R���X�g���N�^�B�i�p�b�P�[�W�v���C�x�[�g�j
	 * 
	 * @since v0.2.0
	 */
	EntityAPI() {}

	/**
	 * �F�؂��肩�ǂ����ԋp���܂��B
	 * 
	 * @return �F�؂��肩�ǂ���
	 * @since v0.2.0
 	 */
	protected boolean isAuth() {
		return apiAuth != null;
	}
}
