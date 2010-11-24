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
 * @since v0.0.1
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
	 * @param needLog ���O�o�͗v��
	 * @return ���X�|���X�{�f�B�̕�����
	 * @throws MalformedURLException
	 * @throws IOException
	 * @since v0.0.1
	 */
	public static String doGet(String url, QueryParameter param, boolean needLog) throws MalformedURLException, IOException {
		return doGet(null, url, param, needLog);
	}

	/**
	 * �Q�b�g���܂��B
	 * 
	 * @param loginUser ���O�C�����[�U
	 * @param url ���N�G�X�gURL
	 * @param param ���N�G�X�g�p�����[�^���
	 * @param needLog ���O�o�͗v��
	 * @return ���X�|���X�{�f�B�̕�����
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @since v0.0.1
	 */
	public static String doGet(LoginUser loginUser, String url,
			QueryParameter param, boolean needLog) throws UnsupportedEncodingException,
			MalformedURLException, IOException {

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
				if (needLog) {
					param.outputPostInfo();
				}
			}

			PrintWriter writer = new PrintWriter(urlconn.getOutputStream());
			if (param != null) {
				writer.write(param.toParameter());
			}
			writer.flush();
			writer.close();

			// ���X�|���X
			return getResponse(urlconn, needLog);

		} catch (UnsupportedEncodingException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (MalformedURLException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

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
	 * @param needLog ���O�o�͗v��
	 * @return ���X�|���X�{�f�B�̕�����
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws HatenaHaikuException 
	 * @since v0.0.1
	 */
	public static String doPost(LoginUser loginUser, String url,
			UpdateParameter param, boolean needLog) throws UnsupportedEncodingException,
			MalformedURLException, IOException, HatenaHaikuException {

		HttpURLConnection urlconn = null; // �R�l�N�V����
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.POST.name());
			urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			// �L�[���[�h���w��̏ꍇ�́A���O�C�����[�U��ID�Ƃ���B
			if (param.getKeyword() == null || param.getKeyword().equals("")) {
				param.setKeyword(loginUser.getUserIdNotation());
			}
			urlconn.setDoOutput(true);
			
			// �|�X�g���e�̕\��
			if (needLog) {
				param.outputPostInfo(loginUser);
			}

			PrintWriter writer = new PrintWriter(urlconn.getOutputStream());
			// TODO �摜�t�@�C���𓊍e (file �p�����[�^���w��) ����ꍇ�́Amultipart/form-data �ŃG���R�[�h���ĉ������B
			writer.write(param.toParameter(loginUser));
			writer.flush();
			writer.close();
			
			// ���X�|���X
			return getResponse(urlconn, needLog);

		} catch (UnsupportedEncodingException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (MalformedURLException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} finally {
			// �R�l�N�V�����ؒf
			urlconn.disconnect();
		}
	}
	
	/**
	 * ���X�|���X��ԋp���܂��B
	 * 
	 * @param urlconn�@HTTP�R�l�N�V����
	 * @param needLog ���O�o�͗v��
	 * @return ���X�|���X�{�f�B�̕�����
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @since v0.0.1
	 */
	private static String getResponse(HttpURLConnection urlconn, boolean needLog)
			throws MalformedURLException, IOException {

		try {
			// ���X�|���X
			Map<String, List<String>> headers = urlconn.getHeaderFields();
			if (needLog) {
				for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
			}
			// �{�f�B
			StringBuilder responseBody = new StringBuilder();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), Const.UTF8));
			String line;
			while((line = responseReader.readLine()) != null) {
				responseBody.append(line);
			}

			if (needLog) {
				System.out.println("----------------");
				System.out.println("���X�|���X�R�[�h: " + urlconn.getResponseCode());
				System.out.println("���X�|���X���b�Z�[�W: " + urlconn.getResponseMessage());
				System.out.println("----------------");
				System.out.println(urlconn.getRequestMethod() + " successfully");
			}
			return responseBody.toString();

		} catch (UnsupportedEncodingException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} catch (IOException e) {
			if (needLog) {
				System.out.println(urlconn.getRequestMethod() + " failure");
			}
			e.printStackTrace();
			throw e;

		} finally {
			// �R�l�N�V�����ؒf
			urlconn.disconnect();
		}
	}
}
