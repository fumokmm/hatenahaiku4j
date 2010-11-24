package hatenahaiku4j.util;

import hatenahaiku4j.Const;
import hatenahaiku4j.HatenaHaikuException;
import hatenahaiku4j.ImageMime;
import hatenahaiku4j.LoginUser;
import hatenahaiku4j.QueryParameter;
import hatenahaiku4j.UpdateParameter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	/** ���N�G�X�g�v���p�e�B:�R�l�N�V���� */
	protected static final String REQUEST_PROPERTY_CONNECTION = "Connection";
	/** ���N�G�X�g�v���p�e�B:�R�l�N�V���� (�l:close) */
	protected static final String REQUEST_PROPERTY_CONNECTION_CLOSE = "close";
	/** ���N�G�X�g�v���p�e�B:�R���e���g�^�C�v */
	protected static final String REQUEST_PROPERTY_CONTENT_TYPE = "Content-Type";
	
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
	 * @throws MalformedURLException
	 * @throws IOException
	 * @since v0.0.1
	 */
	public static String doGet(LoginUser loginUser, String url,
			QueryParameter param, boolean needLog) throws
			MalformedURLException, IOException {

		HttpURLConnection urlconn = null; // �R�l�N�V����
		try {
			urlconn = (HttpURLConnection) new URL(url).openConnection();
			urlconn.setRequestMethod(Method.GET.name());
			if (loginUser != null) {
				urlconn.setRequestProperty(REQUEST_PROPERTY_AUTHORIZATION, loginUser.toBasicAuthenticationString());
			}
			// GET�����AoutputStream���擾���邽output��true�Ƃ���
			urlconn.setDoInput(true);		// for GET
			urlconn.setDoOutput(true);		// for POST
			urlconn.setUseCaches(false);	// disable cache
			// �ŏ��ɂ��čŌ�̗v��
			urlconn.setRequestProperty(REQUEST_PROPERTY_CONNECTION, REQUEST_PROPERTY_CONNECTION_CLOSE);
			
			// �|�X�g���e�̕\��
			if (param != null) {
				if (needLog) {
					param.outputPostInfo();
				}
			}

			OutputStream os = urlconn.getOutputStream();
			PostStream ps = new PostStream(os);
			if (param != null) {
				param.addParameter(ps);
			}
			ps.close();

			// ���X�|���X
			return getResponse(urlconn, needLog);

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
			// POST�̂��߁AInput/Output�Ƃ���true
			urlconn.setDoInput(true);		// for GET
			urlconn.setDoOutput(true);		// for POST
			urlconn.setUseCaches(false);	// disable cache
			// �ŏ��ɂ��čŌ�̗v��
			urlconn.setRequestProperty(REQUEST_PROPERTY_CONNECTION, REQUEST_PROPERTY_CONNECTION_CLOSE);

			// multipart/form-data�Ń|�X�g���邽�߂̃o�E���_��������𐶐�
			String boundary = "--------------------" + Const.API_NAME + StringUtil.getSerial();
			urlconn.setRequestProperty(REQUEST_PROPERTY_CONTENT_TYPE, "multipart/form-data; boundary=" + boundary);
			
			// �|�X�g���e�̕\��
			if (needLog) {
				param.outputPostInfo(loginUser);
			}

			OutputStream os = urlconn.getOutputStream();
			PostStream ps = new PostStream(os, boundary);
			param.addParameter(loginUser, ps);
			ps.close();
			
			// ���X�|���X
			return getResponse(urlconn, needLog);

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
	 * @throws MalformedURLException
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
	 * �|�X�g�����⏕�N���X�B<br/>
	 * �p�����[�^�𐶐����ă|�X�g���܂��B
	 * 
	 * @author fumokmm
	 * @since v1.0.0
	 */
	public static class PostStream {
		/** �f�[�^�������ݗp */
		protected final DataOutputStream out;
		/** �e�L�X�g�������ݗp */
		protected final Writer wout;
		
		/** �o�E���_���[ */
		private final String boundary;
		/** �Z�p���[�^ */
		private static final String SEPARATOR = "--";
		/** �s��؂� */
		private static final byte[] CRLF = { 0x0d, 0x0a };
		/** �o�b�t�@�T�C�Y */
		private final int BUFSIZE = 8192;

		/** �f���~�^���K�v���ǂ��� */
		private boolean needDelimeter = false;

		/**
		 * �uURL�G���R�[�h�v���[�h�̃f�[�^�X�g���[�����쐬���܂��B<br>
		 * addProperty(java.lang.String, java.lang.String) �Œǉ������f�[�^�͓K�؂� URL �G���R�[�h����܂��B<br>
		 * {@link #close()}����ƁAContent-Type �� "application/x-www-urlencoded" �Ƃ��� POST ���N�G�X�g�����s���܂��B
		 *
		 * @param o URLConnection.getOutputStream() �ŕԂ���� {@link URLConnection#getOutputStream}
		 * @since v1.0.0
		 */
		public PostStream(OutputStream o) { 
		    this(o, null);
		}
		
		/**
		 * �u�}���`�p�[�g�v���[�h�̃f�[�^�X�g���[�����쐬���܂��B
		 * addProperty(java.lang.String, java.lang.String)��
		 * addFile(java.io.File, java.lang.String, java.lang.String)��
		 * �ǉ������f�[�^�̓}���`�p�[�g MIME �`���Ƀt�H�[�}�b�g����܂��B
		 * close() ����ƁAContent-Type �� "multipart/form-data" �Ƃ��� POST ���N�G�X�g�����s���܂��B
		 *
		 * @param o {@link URLConnection#getOutputStream} �ŕԂ���� OutputStream
		 * @param boundary �}���`�p�[�g MIME �̋�ؕ�����
		 * @since v1.0.0
		 */
		public PostStream(OutputStream o, String boundary) {
			if (o == null) {
				throw new NullPointerException("o is null.");
			}
			
			out = new DataOutputStream(o);

			Writer tmp_wout = null;
			try {
				tmp_wout = new OutputStreamWriter(out, Const.UTF8);
			} catch (UnsupportedEncodingException e) {
				// ignore it
		    } finally {
		    	wout = tmp_wout;
		    }

		    this.boundary = boundary;
		}

		/**
		 * �p�����[�^���X�g���[���ɒǉ����܂��B
		 *
		 * @param name �p�����[�^��
		 * @param value �p�����[�^�̒l
		 * @throws IOException �������ݎ��ɃG���[����������
		 * @since v1.0.0
		 */
		public void addProperty(String name, String value) throws IOException {
			if (boundary == null) {
				if (needDelimeter) {
					wout.write('&');
				}
				String encoded = StringUtil.encode(name) + Const.EQUAL + StringUtil.encode(value);
				wout.write(encoded);
				wout.flush();
				needDelimeter = true;
				return;
			}
			writeSeparatorBoundary();
			writeContentDisposition(name);
			writeValue(value);
		}

		/**
		 * �t�@�C���̓��e���X�g���[���ɒǉ����܂��B<br>
		 * �u�}���`�p�[�g�v���[�h�̃X�g���[���ł����g�p�ł��܂���B
		 *
		 * @param file �ǉ�����t�@�C��
		 * @param name �p�����[�^��
		 * @param mimeType ���̃t�@�C���� MIME �^�C�v
		 * @throws IOException �ʐM�G���[������
		 * @since v1.0.0
		 */
		public void addFile(File file, String name, ImageMime mimeType) throws IOException {
			if (boundary == null) {
				throw new IllegalStateException("could not add a file for this stream.");
			}
			writeSeparatorBoundary();
			// �t�@�C�����͂Ƃ肠�����Ȃ�ł������悤�Ȃ̂ŁAmimeType�����H���ăt�@�C�����Ƃ��Ă���
			writeContentDisposition(name, mimeType.toTemporaryFileName());
			writeContentType(mimeType.getMimeType());
			writeValue(file);
		}
		
		/**
		 * URL�ォ��o�C�g�X�g���[�����擾���A�t�@�C���Ƃ��Ēǉ����܂��B<br>
		 * �u�}���`�p�[�g�v���[�h�̃X�g���[���ł����g�p�ł��܂���B
		 *
		 * @param imageUrl �ǉ�����t�@�C��
		 * @param name �p�����[�^��
		 * @param mimeType ���̃t�@�C���� MIME �^�C�v
		 * @throws IOException �ʐM�G���[������
		 * @since v1.0.0
		 */
		public void addImageUrl(String imageUrl, String name, ImageMime mimeType) throws IOException {
			if (boundary == null) {
				throw new IllegalStateException("could not add a image file for this stream.");
			}
			writeSeparatorBoundary();
			// �t�@�C�����͂Ƃ肠�����Ȃ�ł������悤�Ȃ̂ŁAmimeType�����H���ăt�@�C�����Ƃ��Ă���
			writeContentDisposition(name, mimeType.toTemporaryFileName());
			writeContentType(mimeType.getMimeType());
			writeValue(new URL(imageUrl));
		}

		/**
		 * �摜�o�C�i���f�[�^���X�g���[���ɒǉ����܂��B<br>
		 * �u�}���`�p�[�g�v���[�h�̃X�g���[���ł����g�p�ł��܂���B
		 *
		 * @param imageData �摜�o�C�i���f�[�^
		 * @param name �p�����[�^��
		 * @param mimeType ���̃t�@�C���� MIME �^�C�v
		 * @throws IOException �ʐM�G���[������
		 * @since v1.0.0
		 */
		public void addImageData(byte[] imageData, String name, ImageMime mimeType) throws IOException {
			if (boundary == null) {
				throw new IllegalStateException("could not add a image data for this stream.");
			}
			writeSeparatorBoundary();
			// �t�@�C�����͂Ƃ肠�����Ȃ�ł������悤�Ȃ̂ŁAmimeType�����H���ăt�@�C�����Ƃ��Ă���
			writeContentDisposition(name, mimeType.toTemporaryFileName());
			writeContentType(mimeType.getMimeType());
			writeValue(imageData);
		}

		/**
		 * ���̃X�g���[������āA�|�X�g���܂��B
		 * 
		 * @throws IOException
		 * @since v1.0.0
		 */
		public void close() throws IOException {
			if (boundary != null) {
				writeTerminate();
			}

			try {
				out.flush();
	    	} finally {
	    		try {
		    		if (out != null) {
						out.close();
		    		}
		    		if (wout != null) {
						wout.close();
		    		}
		    	} catch (Exception e) {
		    		// ignore it
		    	}
			}
		}
		
		/**
		 * �Z�p���[�^�[�{�o�E���_���[���X�g���[���ɏ������݂܂��B
		 * 
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeSeparatorBoundary() throws IOException {
			out.writeBytes(SEPARATOR);
			out.writeBytes(boundary);
			out.write(CRLF);
		}
		
		/**
		 * �R���e���g�f�B�X�|�W�V�������X�g���[���ɏ������݂܂��B
		 * 
		 * @param name �p�����[�^��
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeContentDisposition(String name) throws IOException {
			wout.write("Content-Disposition: form-data; name=\"" + name + "\"");
			wout.flush();
			out.write(CRLF);
		}
		
		/**
		 * �R���e���g�f�B�X�|�W�V�������X�g���[���ɏ������݂܂��B
		 * 
		 * @param name �p�����[�^��
		 * @param fileName �t�@�C����
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeContentDisposition(String name, String fileName) throws IOException {
			wout.write("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"");
			wout.flush();
			out.write(CRLF);
		}
		
		/**
		 * �R���e���c�^�C�v���X�g���[���ɏ������݂܂��B
		 * 
		 * @param mimeType �}�C���^�C�v
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeContentType(String mimeType) throws IOException {
			wout.write("Content-Type: " + mimeType);
			wout.flush();
			out.write(CRLF);
		}
		
		/**
		 * ��������X�g���[���ɏ������݂܂��B
		 * 
		 * @param value �X�g���[���ɏ������ޕ�����
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(String value) throws IOException {
			out.write(CRLF);
			wout.write(value);
			wout.flush();
			out.write(CRLF);
		}

		/**
		 * �o�C�g�z����X�g���[���ɏ������݂܂��B
		 * 
		 * @param value �X�g���[���ɏ������ރo�C�g�z��
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(byte[] value) throws IOException {
			out.write(CRLF);
			out.write(value, 0, value.length);
			out.write(CRLF);
		}
		
		/**
		 * �t�@�C�����X�g���[���ɏ������݂܂��B
		 * 
		 * @param file �X�g���[���ɏ������ރt�@�C��
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(File file) throws IOException {
		    writeValue(FileUtil.toByteArray(file));
		}

		/**
		 * 
		 * @param imageUrl
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeValue(URL imageUrl) throws IOException {
			InputStream in = null;
			ByteArrayOutputStream out = null;
		    try {
		    	in = imageUrl.openStream();
		    	out = new ByteArrayOutputStream();
		    	byte[] buffer = new byte[BUFSIZE];
		    	int rsize;
		    	while ((rsize = in.read(buffer)) != -1) {
		    		out.write(buffer, 0, rsize);
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
		    
		    writeValue(out.toByteArray());
		}
		
		/**
		 * 
		 * @throws IOException
		 * @since v1.0.0
		 */
		private void writeTerminate() throws IOException {
			out.writeBytes(SEPARATOR);
			out.writeBytes(boundary);
			out.writeBytes(SEPARATOR);
		}
	}
}
