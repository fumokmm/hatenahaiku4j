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
 * @since v0.0.1
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
	 * @since v0.0.1
	 */
	public static Element getRootElement(String resultXml)
			throws ParserConfigurationException, UnsupportedEncodingException,
			SAXException, IOException {
		
		// ドキュメントビルダーファクトリを生成
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		dbfactory.setIgnoringElementContentWhitespace(true); // 余分なホワイトスペースは削除
		dbfactory.setIgnoringComments(true);                 // コメントノードは不要
		
		// ドキュメントビルダーを生成
		DocumentBuilder builder = dbfactory.newDocumentBuilder();

		// パースを実行してDocumentオブジェクトを取得
		Document doc = builder.parse(
			new ByteArrayInputStream(
				escapeAmp(removeIllegalChar(resultXml)).getBytes(Const.UTF8)
			)
		);

		return doc.getDocumentElement();
	}
	
	/**
	 * XMLノードから子ノードのエレメントを取得し返却します。
	 * 
	 * @param node 走査基点となるXMLノード
	 * @return 子ノードのエレメントリスト
	 * @since v0.1.0
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
	 * @since v0.1.0
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
	 * @since v0.1.0
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
	 * @since v0.0.1
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
	 * @since v0.0.1
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

	/**
	 * 0x1bを除去する。
	 * 
	 * @param source 元の文字列
	 * @return 0x1bを除去後の文字列
	 * @since v1.2.0
	 */
	private static String removeIllegalChar(String source) {
		return source.replaceAll(new String(new char[]{ (char)0x1b }), "");
	}

	/**
	 * &amp; を &amp;amp; に変換します。
	 * 
	 * @param source 元の文字列
	 * @return 変換した文字列
	 * @since v1.2.0
	 */
	private static String escapeAmp(String source) {
		/*
		 * thanks:
		 * http://www.tipsblogger.com/2009/02/reference-to-entity-xx-must-end-with.html
		 */
		return source.replaceAll("&", "&amp;");
	}

}
