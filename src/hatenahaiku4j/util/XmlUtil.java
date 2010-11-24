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
 * XMLに関するユーティリティクラス
 * 
 * @author fumokmm
 */
public class XmlUtil {

	/**
	 * 結果XML文字列から、ルートエレメントを取得します。
	 * 
	 * @param resultXml 結果XML文字列
	 * @return ルートエレメント
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Element getRootElement(String resultXml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		// ドキュメントビルダーファクトリを生成
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		// ドキュメントビルダーを生成
		DocumentBuilder builder = dbfactory.newDocumentBuilder();
		// パースを実行してDocumentオブジェクトを取得
		Document doc = builder.parse(new ByteArrayInputStream(resultXml.getBytes(Const.UTF8)));

		return doc.getDocumentElement();
	}
	
	/**
	 * XMLノードから子ノードのエレメントを取得し返却します。
	 * 
	 * @param node 走査基点となるXMLノード
	 * @return 子ノードのエレメントリスト
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
	 * XMLエレメントから指定のタグ名を持つ子ノードのエレメントを返却します。
	 * 
	 * @param elem 走査基点となるXMLエレメント
	 * @param name 走査するタグ名
	 * @return 走査するタグ名にマッチした1件目のエレメント
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
	 * XMLエレメントから指定のタグ名を持つエレメントの先頭1件目を返却します。<br/>
	 * 見つからなかった場合nullを返却します。
	 * 
	 * @param elem 走査基点となるXMLエレメント
	 * @param name 走査するタグ名
	 * @return 走査するタグ名にマッチした1件目のエレメント
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
	 * XMLエレメントから指定のタグ名を持つエレメントの先頭1件目のテキストコンテンツを返却します。
	 * 
	 * @param elem 走査基点となるXMLエレメント
	 * @param name 走査するタグ名
	 * @return 走査するタグ名にマッチした1件目のエレメントのテキストコンテンツ
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
	 * XMLエレメントから指定のタグ名を持つエレメントすべてのテキストコンテンツをリストで返却します。
	 * 
	 * @param elem 走査基点となるXMLエレメント
	 * @param name 走査するタグ名
	 * @return 走査するタグ名にマッチする要素のエレメントのテキストコンテンツのリスト
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
