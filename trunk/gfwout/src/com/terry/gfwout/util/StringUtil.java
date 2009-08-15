package com.terry.gfwout.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 15, 2009 6:16:25 PM
 */
public class StringUtil {
	public static String readLink(String result) {
		Parser parser = Parser.createParser(result, "utf8");

		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { linkFilter });
		NodeList nodelist = null;
		try {
			nodelist = parser.parse(lastFilter);
		} catch (ParserException e) {
			return result;
		}
		Node[] nodes = nodelist.toNodeArray();
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof LinkTag) {
				LinkTag link = (LinkTag) node;
				return link.getLink();
			}
		}
		return result;
	}

	public static String replaceLink(String html, String basePath) {
		StringBuffer sb = new StringBuffer("");
		Pattern pattern = Pattern
				.compile(
						"(<\\s*a\\s+(?:[^\\s>]\\s*){0,})href\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			String findStr = matcher.group();
			String link = readLink(findStr);
			String replaceLink = link;
			if (!replaceLink.startsWith("http")) {
				replaceLink = replaceLink.startsWith("/") ? basePath
						+ replaceLink : basePath + "/" + replaceLink;
			}
			String mStr = findStr.replace(link, replaceLink);
			matcher.appendReplacement(sb, mStr);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
