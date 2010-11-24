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
	 * XML�m�[�h����q�m�[�h�̃G�������g���擾���ԋp���܂��B
	 * 
	 * @param node ������_�ƂȂ�XML�m�[�h
	 * @return �q�m�[�h�̃G�������g���X�g
	 */
	public static List<Element> getChildElements(Node node) {
		List<Element> result = new ArrayList<Element>();
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				result.add((Element) child);
			}
		}
		return result;
	}

	/**
	 * XML�G�������g����w��̃^�O�������q�m�[�h�̃G�������g��ԋp���܂��B
	 * 
	 * @param elem ������_�ƂȂ�XML�G�������g
	 * @param name ��������^�O��
	 * @return ��������^�O���Ƀ}�b�`����1���ڂ̃G�������g
	 */
	public static List<Element> getChildElementsByTagName(Element elem, String name) {
		List<Element> result = new ArrayList<Element>();
		NodeList nodeList = elem.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals(name)) {
				result.add((Element) child);
			}
		}
		return result;
	}

	/**
	 * XML�G�������g����w��̃^�O�������G�������g�̐擪1���ڂ�ԋp���܂��B<br/>
	 * ������Ȃ������ꍇnull��ԋp���܂��B
	 * 
	 * @param elem ������_�ƂȂ�XML�G�������g
	 * @param name ��������^�O��
	 * @return ��������^�O���Ƀ}�b�`����1���ڂ̃G�������g
	 */
	public static Element getFirstChildElement(Element elem, String name) {
		NodeList nodeList = elem.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node child = nodeList.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals(name)) {
				return (Element) child;
			}
		}
		return null;
	}

	/**
	 * XML�G�������g����w��̃^�O�������G�������g�̐擪1���ڂ̃e�L�X�g�R���e���c��ԋp���܂��B
	 * 
	 * @param elem ������_�ƂȂ�XML�G�������g
	 * @param name ��������^�O��
	 * @return ��������^�O���Ƀ}�b�`����1���ڂ̃G�������g�̃e�L�X�g�R���e���c
	 */
	public static String getText(Element elem, String name) {
		Element childElement = getFirstChildElement(elem, name);
		if (childElement == null) {
			return "";
		}
		Node node = childElement.getFirstChild();
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
		List<Element> childElements = getChildElementsByTagName(elem, name);
		for (Element childElem : childElements) {
			Node node = childElem.getFirstChild();
			result.add(node == null ? "" : node.getTextContent());
		}
		return result;
	}
}
