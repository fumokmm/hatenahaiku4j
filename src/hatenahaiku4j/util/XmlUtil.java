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
	 * XMLエレメントから指定のタグ名を持つエレメントの先頭1件目のテキストコンテンツを返却します。
	 * 
	 * @param elem 走査基点となるXMLエレメント
	 * @param name 走査するタグ名
	 * @return 走査するタグ名にマッチした1件目のエレメントのテキストコンテンツ
	 */
	public static String getText(Element elem, String name) {
		Node node = elem.getElementsByTagName(name).item(0).getFirstChild();
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
		NodeList nodeList = elem.getElementsByTagName(name);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i).getFirstChild();
			result.add(node == null ? "" : node.getTextContent());
		}
		return result;
	}
	
	/**
	 * XMLエレメントから指定のタグ名を持つエレメントの先頭1件目を返却します。
	 * 
	 * @param elem 走査基点となるXMLエレメント
	 * @param name 走査するタグ名
	 * @return 走査するタグ名にマッチした1件目のエレメント
	 */
	public static Element getFirstChildElement(Element elem, String name) {
		return (Element) elem.getElementsByTagName(name).item(0);
	}
}
