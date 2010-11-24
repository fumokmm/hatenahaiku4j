package hatenahaiku4j.util;

import hatenahaiku4j.Const;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.QueryParameter;
import hatenahaiku4j.UpdateParameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * HTTP���N�G�X�g�Ɋւ��郆�[�e�B���e�B�N���X
 * 
 * @author fumokmm
 */
public class HttpUtil {
	/** METHOD */
	private static enum Method { POST, GET }
	/** ���N�G�X�g�v���p�e�B�FBASIC�F�� */
	protected static final String REQUEST_PROPERTY_AUTHORIZATION = "Authorization";
	
	/**
	 * �Q�b�g���܂��B
	 * 
	 * @param url ���N�G�X�gURL
	 * @param param ���N�G�X�g�p�����[�^���
	 * @return ���X�|���X�{�f�B�̕�����
	 */
	public static String doGet(String url, QueryParameter param) throws HatenaHaikuException {
		return doGet(null, url, param);
	}

	/**
	 * �Q�b�g���܂��B
	 * 
	 * @param loginUser ���O�C�����[�U
	 * @param url ���N�G�X�gURL
	 * @param param ���N�G�X�g�p�����[�^���
	 * @return ���X�|���X�{�f�B�̕�����
	 */
	public static String doGet(LoginUser loginUser, String url, QueryParameter param) throws HatenaHaikuException {
		HttpURLConnection urlconn = null; // �R�l�N�V����
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.GET.name());
			if (loginUser != null) {
				urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			}
			urlconn.setDoOutput(true);
			
			// �|�X�g���e�̕\��
			if (param != null) {
				param.outputPostInfo();
			}

			PrintWriter writer = new PrintWriter(urlconn.getOutputStream());
			if (param != null) {
				writer.write(param.toParameter());
			}
			writer.flush();
			writer.close();

			// ���X�|���X
			return getResponse(urlconn);

		} catch (MalformedURLException e) {
			System.out.println(urlconn.getRequestMethod() + " failure");
			throw new HatenaHaikuException("�s����URL�`���ł��B", e);

		} catch (IOException e) {
			System.out.println(urlconn.getRequestMethod() + " failure");
			throw new HatenaHaikuException("IOException���������܂����B", e);

		} finally {
			// �R�l�N�V�����ؒf
			urlconn.disconnect();
		}
	}

	/**
	 * �|�X�g���܂��B
	 * 
	 * @param loginUser ���O�C�����[�U
	 * @param url ���N�G�X�gURL
	 * @param param ���N�G�X�g�p�����[�^���
	 * @return ���X�|���X�{�f�B�̕�����
	 */
	public static String doPost(LoginUser loginUser, String url, UpdateParameter param) throws HatenaHaikuException {
		HttpURLConnection urlconn = null; // �R�l�N�V����
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.POST.name());
			urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			// �L�[���[�h���w��̏ꍇ�́A���O�C�����[�U��ID�Ƃ���B
			if (param.getKeyword() == null || param.getKeyword().equals("")) {
				param.setKeyword(loginUser.getHatenaIdFormat());
			}
			urlconn.setDoOutput(true);
			
			// �|�X�g���e�̕\��
			param.outputPostInfo(loginUser);

			PrintWriter writer = new PrintWriter(urlconn.getOutputStream());
			// TODO �摜�t�@�C���𓊍e (file �p�����[�^���w��) ����ꍇ�́Amultipart/form-data �ŃG���R�[�h���ĉ������B
			writer.write(param.toParameter(loginUser));
			writer.flush();
			writer.close();
			
			// ���X�|���X
			return getResponse(urlconn);

		} catch (MalformedURLException e) {
			System.out.println(urlconn.getRequestMethod() + " failure");
			throw new HatenaHaikuException("�s����URL�`���ł��B", e);

		} catch (IOException e) {
			System.out.println(urlconn.getRequestMethod() + " failure");
			throw new HatenaHaikuException("IOException���������܂����B", e);

		} finally {
			// �R�l�N�V�����ؒf
			urlconn.disconnect();
		}
	}
	
	/**
	 * ���X�|���X��ԋp���܂��B
	 * 
	 * @param urlconn�@HTTP�R�l�N�V����
	 * @return ���X�|���X�{�f�B�̕�����
	 */
	private static String getResponse(HttpURLConnection urlconn) throws HatenaHaikuException {
		try {
			// ���X�|���X
			Map<String, List<String>> headers = urlconn.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			// �{�f�B
			StringBuilder responseBody = new StringBuilder();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), Const.UTF8));
			String line;
			while((line = responseReader.readLine()) != null) {
				responseBody.append(line);
			}

			System.out.println("----------------");
			System.out.println("���X�|���X�R�[�h: " + urlconn.getResponseCode());
			System.out.println("���X�|���X���b�Z�[�W: " + urlconn.getResponseMessage());
			System.out.println("----------------");
			System.out.println(urlconn.getRequestMethod() + " successfully");

			return responseBody.toString();

		} catch (UnsupportedEncodingException e) {
			System.out.println(urlconn.getRequestMethod() + " failure");
			throw new HatenaHaikuException("�G���R�[�f�B���O���T�|�[�g����Ă��܂���B", e);

		} catch (IOException e) {
			System.out.println(urlconn.getRequestMethod() + " failure");
			throw new HatenaHaikuException("IOException���������܂����B", e);

		} finally {
			// �R�l�N�V�����ؒf
			urlconn.disconnect();
		}
	}
}
