package com.portal.test;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

/**
 * Developer: Terry DateTime : 2007-12-6 上午11:26:38
 */

public class Test {
	public static void main(String[] args) throws Exception {
		Parser parser = new Parser("http://www.weathercn.com/tqyb/detail.jsp?sta_id=58354");
		NodeList tableNodes = parser.parse(new NodeClassFilter(TableTag.class));
		for(int i=0;i<tableNodes.size();i++){
			Node node = tableNodes.elementAt(i);
			System.out.println("table"+i+"-----------------------");
			System.out.println(node.toHtml().replace("src=\"", "src=\"http://www.weathercn.com"));
			System.out.println();
		}
//		URL url = new URL(
//				"http://www.weathercn.com/tqyb/detail.jsp?sta_id=58257");
//		URLConnection conn = url.openConnection();
//		InputStream is = conn.getInputStream();
//		byte[] b = new byte[1024];
//		while (true) {
//			int i = is.read(b);
//			if (i == -1)
//				break;
//			System.out.println(new String(b, 0, i));
//		}
	}
}
