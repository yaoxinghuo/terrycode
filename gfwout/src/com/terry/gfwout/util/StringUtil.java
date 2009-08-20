package com.terry.gfwout.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.google.appengine.api.memcache.stdimpl.GCacheFactory;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Aug 15, 2009 6:16:25 PM
 */
public class StringUtil {

	private static Cache cache;

	static {
		Map<Integer, Integer> props = new HashMap<Integer, Integer>();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600 * 24);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

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

	public static String readByParser(String result) {
		Parser parser = Parser.createParser(result, "utf8");
		PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
		factory.registerTag(new StyleLinkTag());
		parser.setNodeFactory(factory);

		NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
		NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
		NodeFilter scriptFilter = new NodeClassFilter(ScriptTag.class);
		NodeFilter styleFilter = new NodeClassFilter(StyleLinkTag.class);
		NodeFilter formFilter = new NodeClassFilter(FormTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { linkFilter, imageFilter,
				scriptFilter, styleFilter, formFilter });
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
			} else if (node instanceof FormTag) {
				FormTag form = (FormTag) node;
				return form.getAttribute("action");
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static String replace(String html, String baseUrl, String url) {
		int index = url.lastIndexOf("/");
		if (index != -1 && index >= 7)
			url = url.substring(0, index);
		StringBuffer sb = new StringBuffer("");
		Pattern pattern = Pattern
				.compile(
						"(<\\s*a\\s+(?:[^\\s>]\\s*){0,})href\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"// <a
						// href...
								+ "|"// <img src...
								+ "(<\\s*img\\s+(?:[^\\s>]\\s*){0,})src\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"
								+ "|"// <link href...
								+ "(<\\s*link\\s+(?:[^\\s>]\\s*){0,})href\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"
								+ "|"// <form action...
								+ "(<\\s*form\\s+(?:[^\\s>]\\s*){0,})action\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)"
								+ "|"// <script src...
								+ "(<\\s*script\\s+(?:[^\\s>]\\s*){0,})src\\s*=\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			String findStr = matcher.group();
			String link = readByParser(findStr);
			String replaceLink = link;
			String replacedUrl = url;

			String uuid = generateUUID();
			if (!replaceLink.startsWith("http")) {
				replacedUrl = url.endsWith("/") ? url + replaceLink : url + "/"
						+ replaceLink;
				replaceLink = baseUrl + "gfw?go=" + uuid;
				cache.put(uuid, replacedUrl);
			} else {

				replaceLink = baseUrl + "gfw?go=" + uuid;
				cache.put(uuid, link);

			}
			String mStr = findStr.replace(link, replaceLink);
			matcher.appendReplacement(sb, mStr);
		}
		matcher.appendTail(sb);

		Pattern pattern2 = Pattern
				.compile(
						"(<\\s*body\\s*(?:[^\\s>]\\s*){0,})\\s*(\"|'|)((?:\\s*[^\\s>]){0,}\\s*>)",
						Pattern.CASE_INSENSITIVE);
		Matcher matcher2 = pattern2.matcher(sb.toString());
		sb = new StringBuffer("");
		if (matcher2.find()) {
			StringBuffer sb2 = new StringBuffer(matcher2.group());
			int p = sb2.indexOf(">");
			if (p != -1) {
				sb2.insert(p + 1, Constants.ANALYTICS_HTML
						+ Constants.BACK_HOME_HTML);
			}
			matcher2.appendReplacement(sb, sb2.toString());
		}
		matcher2.appendTail(sb);
		return sb.toString();
	}

	public static String replaceForm(String html, String basePath) {
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

	public static boolean isImage(String url) {
		String[] ext = { ".png", ".gif", ".jpg", ".bmp", ".ico" };
		if (url.length() < 4)
			return false;
		url = removeQueryAndHash(url);
		String fext = url.substring(url.length() - 4);
		for (String s : ext) {
			if (fext.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	public static boolean isBinary(String url) {
		String[] ext = { ".exe", ".js", ".zip", ".doc", ".docx", ".xls",
				".xlsx", ".ppt", ".pptx", ".txt", ".rar", ".chm", ".jar",
				".java" };
		if (url.length() < 4)
			return false;
		url = removeQueryAndHash(url);
		String fext = url.substring(url.length() - 4);
		for (String s : ext) {
			if (fext.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	private static String removeQueryAndHash(String url) {
		// Pull off any hash.
		int i = url.indexOf('#');
		if (i != -1)
			url = url.substring(0, i);

		// Pull off any query string.
		i = url.indexOf('?');
		if (i != -1)
			url = url.substring(0, i);
		return url;
	}

	public static String getContentType(String html) {
		if (html != null && !html.equals("")) {
			int pos = html.indexOf("=");
			if (pos != -1)
				return html.substring(pos + 1).toUpperCase();
		}
		return "GB2312";
	}

	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	public static void main(String[] args) throws Exception {
		// regexpTest();
		// System.out.println(readByParser("<img src=xxx.css />"));
		// String s =
		// "Teat<img src=abc.jpg/><a href=a.html>Google</a>xxxx<script src=xxx.js />bbb<link href=xxx.css />";
		// System.out.println(replace(s, "http://gfwout.appspot.com/",
		// "http://www.google.com"));

		String s = "http://www.google.cn";
		URL url = new URL(s);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		String contentType = con.getContentType();
		if (contentType == null)
			contentType = "text/html; charset=UTF-8";
		BufferedReader reader = new BufferedReader(new InputStreamReader(con
				.getInputStream(), StringUtil.getContentType(contentType))); // 读取结果
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		con.disconnect();
		System.out.println(StringUtil.replace(sb.toString(),
				"http://gfwout.appspot.com/", "http://www.google.cn"));
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
