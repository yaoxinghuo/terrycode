package com.terry.gfwout.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ScriptTag;
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

	public static String readImage(String result) {
		Parser parser = Parser.createParser(result, "utf8");

		NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { imageFilter });
		NodeList nodelist = null;
		try {
			nodelist = parser.parse(lastFilter);
		} catch (ParserException e) {
			return result;
		}
		Node[] nodes = nodelist.toNodeArray();
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof ImageTag) {
				ImageTag link = (ImageTag) node;
				return link.getImageURL();
			}
		}
		return result;
	}

	public static String readByParser(String result) {
		Parser parser = Parser.createParser(result, "utf8");
		PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
		factory.registerTag(new StyleLinkTag());
		parser.setNodeFactory(factory);

		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
		NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
		NodeFilter scriptFilter = new NodeClassFilter(ScriptTag.class);
		NodeFilter styleFilter = new NodeClassFilter(StyleLinkTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { linkFilter, imageFilter,
				scriptFilter, styleFilter });
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
			} else if (node instanceof ImageTag) {
				ImageTag image = (ImageTag) node;
				return image.getImageURL();
			} else if (node instanceof ScriptTag) {
				ScriptTag script = (ScriptTag) node;
				return script.getAttribute("src");
			} else if (node instanceof StyleLinkTag) {
				StyleLinkTag style = (StyleLinkTag) node;
				return style.getLink();
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

	public static String replaceImage(String html, String basePath) {
		StringBuffer sb = new StringBuffer("");
		Pattern pattern = Pattern
				.compile(
						"(<\\s*img\\s+(?:[^\\s>]\\s*){0,})src\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			String findStr = matcher.group();
			String image = readImage(findStr);
			String replaceImage = image;
			if (!replaceImage.startsWith("http")) {
				replaceImage = replaceImage.startsWith("/") ? basePath
						+ replaceImage : basePath + "/" + replaceImage;
			}
			String mStr = findStr.replace(image, replaceImage);
			matcher.appendReplacement(sb, mStr);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static void regexpTest() {
		Pattern pattern = Pattern
				.compile(
						"(<\\s*a\\s+(?:[^\\s>]\\s*){0,})href\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"// <a
						// href...
								+ "|"// <img src...
								+ "(<\\s*img\\s+(?:[^\\s>]\\s*){0,})src\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"
								+ "|"// <link href...
								+ "(<\\s*link\\s+(?:[^\\s>]\\s*){0,})href\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"
								+ "|"// <script src...
								+ "(<\\s*script\\s+(?:[^\\s>]\\s*){0,})src\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern
				.matcher("Teat<img src=abc.jpg/><a href=www.google.com>Google</a>xxxx<script src=xxx.js />bbb<link href=xxx.css />");
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static void main(String[] args) throws Exception {
		 regexpTest();
//		System.out.println(readByParser("<img src=xxx.css />"));
		// String s = "Teat<img src=abc.jpg/><a href=www.google.com>Google</a>";
		// System.out.println(replaceImage(s,
		// "http://gfwout.appspot.com/www.google.com"));
	}

	static class StyleLinkTag extends CompositeTag {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3262862026166805969L;
		private static final String[] mIds = new String[] { "LINK" };

		@Override
		public String[] getIds() {
			return (mIds);
		}

		@Override
		public String[] getEnders() {
			return (mIds);
		}

		public String getLink() {
			return super.getAttribute("href");
		}

	}
}
