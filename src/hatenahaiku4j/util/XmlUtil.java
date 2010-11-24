package hatenahaiku4j.util;

import hatenahaiku4j.Const;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML�Ɋւ��郆�[�e�B���e�B�N���X
 * 
 * @author fumokmm
 */
public class XmlUtil {

	/**
	 * ����XML�����񂩂�A���[�g�G�������g���擾���܂��B
	 * 
	 * @param resultXml ����XML������
	 * @return ���[�g�G�������g
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Element getRootElement(String resultXml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		// �h�L�������g�r���_�[�t�@�N�g���𐶐�
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		// �h�L�������g�r���_�[�𐶐�
		DocumentBuilder builder = dbfactory.newDocumentBuilder();
		// �p�[�X�����s����Document�I�u�W�F�N�g���擾
		Document doc = builder.parse(new ByteArrayInputStream(resultXml.getBytes(Const.UTF8)));

		return doc.getDocumentElement();
	}
	
	/**
	 * XML�G�������g����w��̃^�O�������G�������g�̐擪1���ڂ̃e�L�X�g�R���e���c��ԋp���܂��B
	 * 
	 * @param elem ������_�ƂȂ�XML�G�������g
	 * @param name ��������^�O��
	 * @return ��������^�O���Ƀ}�b�`����1���ڂ̃G�������g�̃e�L�X�g�R���e���c
	 */
	public static String getText(Element elem, String name) {
		Node node = elem.getElementsByTagName(name).item(0).getFirstChild();
		return node == null ? "" : node.getTextContent();
	}
	
	/**
	 * XML�G�������g����w��̃^�O�������G�������g���ׂẴe�L�X�g�R���e���c�����X�g�ŕԋp���܂��B
	 * 
	 * @param elem ������_�ƂȂ�XML�G�������g
	 * @param name ��������^�O��
	 * @return ��������^�O���Ƀ}�b�`����v�f�̃G�������g�̃e�L�X�g�R���e���c�̃��X�g
	 */
	public static List<String> getTextList(Element elem, String name) {
		List<String> result = new ArrayList<String>();
		NodeList nodeList = elem.getElementsByTagName(name);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i).getFirstChild();
			result.add(node == null ? "" : node.getTextContent());
		}
		return result;
	}
	
	/**
	 * XML�G�������g����w��̃^�O�������G�������g�̐擪1���ڂ�ԋp���܂��B
	 * 
	 * @param elem ������_�ƂȂ�XML�G�������g
	 * @param name ��������^�O��
	 * @return ��������^�O���Ƀ}�b�`����1���ڂ̃G�������g
	 */
	public static Element getFirstChildElement(Element elem, String name) {
		return (Element) elem.getElementsByTagName(name).item(0);
	}
}
