package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.carrot2.core.DuplicatedKeyException;
import org.carrot2.core.InitializationException;
import org.carrot2.core.LocalComponent;
import org.carrot2.core.LocalComponentFactory;
import org.carrot2.core.LocalController;
import org.carrot2.core.LocalControllerBase;
import org.carrot2.core.LocalProcessBase;
import org.carrot2.core.MissingComponentException;
import org.carrot2.core.ProcessingResult;
import org.carrot2.core.clustering.RawCluster;
import org.carrot2.core.clustering.RawDocument;
import org.carrot2.core.clustering.RawDocumentSnippet;
import org.carrot2.core.impl.ArrayInputComponent;
import org.carrot2.core.impl.ArrayOutputComponent;
import org.carrot2.core.impl.ArrayOutputComponent.Result;
import org.carrot2.util.ArrayUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 17, 2009 5:45:34 PM
 */
public class Test {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {

			final String query = "程序";
			System.out.println("\r\nSearching \"" + query
					+ "\" using google...");
			/*
			 * Initialize local controller. Normally you'd run this only once
			 * for an entire application (controller is thread safe).
			 */
			final LocalController controller = initLocalController();

			// The documents are provided for clustering in the
			// PARAM_SOURCE_RAW_DOCUMENTS parameter, which should point to
			// a List of RawDocuments.
			ArrayList<SearchResult> al = analytics(getGoogleSearchResult(query));
			List documentList = new ArrayList(al.size());
			for (int i = 0; i < al.size(); i++) {
				SearchResult sr = al.get(i);
				documentList.add(new RawDocumentSnippet(new Integer(i), // unique
						// id of
						// the
						// document,
						// can
						// be a
						// plain
						// sequence
						// id
						sr.getTitle(), // document title
						sr.getContent(), // document body
						sr.getLink(), // URL (not required for clustering)
						0.0f) // document score, can be 0.0
						);
			}

			final HashMap params = new HashMap();
			params.put(ArrayInputComponent.PARAM_SOURCE_RAW_DOCUMENTS,
					documentList);
			final ProcessingResult pResult = controller.query(
					"direct-feed-lingo", query, params);
			final ArrayOutputComponent.Result result = (ArrayOutputComponent.Result) pResult
					.getQueryResult();

			/*
			 * Once we have the buffered snippets and clusters, we can display
			 * them somehow. We'll reuse the simple text-dumping method
			 * available in {@link Example}.
			 */
			displayResults(result);
		} catch (Exception e) {
			// There shouldn't be any, but just in case.
			System.err.println("An exception occurred: " + e.toString());
			e.printStackTrace();
		}

	}

	/**
	 * <p>
	 * In this method we put together a {@link LocalController}.
	 * 
	 * @return This method returns a fully configured, reusable instance a
	 *         {@link LocalController}.
	 */
	private static LocalController initLocalController()
			throws DuplicatedKeyException {
		final LocalController controller = new LocalControllerBase();

		//
		// Create direct document feed input component factory. The documents
		// that that this component will feed will be provided at clustering
		// request time.
		//
		final LocalComponentFactory input = new LocalComponentFactory() {
			public LocalComponent getInstance() {
				return new ArrayInputComponent();
			}
		};

		// add direct document feed input as 'input-direct'
		controller.addLocalComponentFactory("input-direct", input);

		//
		// Now it's time to create filters. We will use Lingo clustering
		// component.
		//
		final LocalComponentFactory lingo = new LocalComponentFactory() {
			public LocalComponent getInstance() {
				HashMap<String, String> defaults = new HashMap<String, String>();

				// These are adjustments settings for the clustering
				// algorithm...
				// You can play with them, but the values below are our 'best
				// guess'
				// settings that we acquired experimentally.
				defaults.put("lsi.threshold.clusterAssignment", "0.150");
				defaults.put("lsi.threshold.candidateCluster", "0.775");
				// we will use the defaults here, see {@link Example}
				// for more verbose configuration.
				return new ICTCALLingoLocalFilterComponent(defaults);
				// return new EnglishLingoLocalFilterComponent();
			}
		};

		// add the clustering component as "lingo-classic"
		controller.addLocalComponentFactory("lingo-classic", lingo);

		//
		// Finally, create a result-catcher component
		//
		final LocalComponentFactory output = new LocalComponentFactory() {
			public LocalComponent getInstance() {
				return new ArrayOutputComponent();
			}
		};

		// add the output component as "buffer"
		controller.addLocalComponentFactory("buffer", output);

		//
		// In the final step, assemble a process from the above.
		//
		try {
			controller
					.addProcess("direct-feed-lingo", new LocalProcessBase(
							"input-direct", "buffer",
							new String[] { "lingo-classic" }));
		} catch (InitializationException e) {
			// This exception is thrown during verification of the added
			// component chain,
			// when a component cannot properly initialize for some reason. We
			// don't
			// expect it here, so rethrow it as runtime exception.
			throw new RuntimeException(e);
		} catch (MissingComponentException e) {
			// If you give an identifier of a component for which factory has
			// not been
			// added to the controller, you'll get this exception. Impossible in
			// our
			// example.
			throw new RuntimeException(e);
		}

		return controller;
	}

	@SuppressWarnings("unchecked")
	static void displayResults(Result result) {
		//
		// let's display a list of snippets recieved from the input
		// component first.
		//
		final List documents = result.documents;
		System.out.println("\r\nCollected: " + documents.size() + " snippets.");

		int num = 1;
		for (Iterator i = documents.iterator(); i.hasNext(); num++) {
			// Results of almost all input components in Carrot<sup>2</sup>
			// are of type {@link RawDocument}. Cast it.
			final RawDocument document = (RawDocument) i.next();

			final String url = document.getUrl();
			final String title = document.getTitle();
			// we don't display this here
			// final String snippet = document.getSnippet();

			// Get the list of sources
			final String sources = "["
					+ ArrayUtils.toString((String[]) document
							.getProperty(RawDocument.PROPERTY_SOURCES)) + "]";

			System.out.print(num + ": " + url + " " + sources + "\n\t-> "
					+ (title.length() > 70 ? title.substring(0, 70) : title)
					+ "\n\n");
		}

		//
		// Now the clusters. Clustering components will return a list of
		// top-level clusters, instances of {@link RawCluster} interface. These
		// objects may contain both documents ({@link RawDocument}), but also
		// subgroups - again a list of {@link RawCluster} objects. A recursive
		// routine is probably best to show how to traverse a set of clusters
		// easily.
		//
		System.out.print("\n\nClusters:\n");
		final List clusters = result.clusters;
		num = 1;
		for (Iterator i = clusters.iterator(); i.hasNext(); num++) {
			displayCluster(0, "CL-" + num, (RawCluster) i.next());
		}
	}

	/**
	 * Shows the content of a single cluster, descending recursively to
	 * subclusters.
	 * 
	 * @param level
	 *            current nesting level.
	 * @param tag
	 *            prefix for the current nesting level.
	 * @param cluster
	 *            cluster to display.
	 */
	@SuppressWarnings("unchecked")
	private static void displayCluster(final int level, String tag,
			RawCluster cluster) {
		// Detect and skip "junk" clusters -- clusters that have no meaning.
		// Also note that clusters have properties. Algorithms may pass
		// additional
		// information about clusters this way.
		if (cluster.getProperty(RawCluster.PROPERTY_JUNK_CLUSTER) != null) {
			return;
		}

		// Get the label of the current cluster. The description of a cluster
		// is a list of strings, ordered according to the accuracy of their
		// relationship with the cluster's content. Typically you'll just
		// show the first few phrases. We'll limit ourselves to just one.
		final List phrases = cluster.getClusterDescription();
		final String label = (String) phrases.get(0);

		// indent up to level and display this cluster's description phrase
		for (int i = 0; i < level; i++)
			System.out.print("  ");
		System.out.println("\n" + tag + " " + label + " ("
				+ cluster.getDocuments().size() + " documents)");

		// if this cluster has documents, display three topmost documents.
		int count = 1;
		for (Iterator d = cluster.getDocuments().iterator(); d.hasNext()
				&& count <= 3; count++) {
			final RawDocument document = (RawDocument) d.next();

			for (int i = 0; i < level; i++)
				System.out.print("  ");
			System.out.print("     " + count + ": " + document.getTitle()
					+ "\r\n     \t\t\t" + document.getUrl() + "\n");
		}

		// finally, if this cluster has subclusters, descend into recursion.
		int num = 1;
		for (Iterator c = cluster.getSubclusters().iterator(); c.hasNext(); num++) {
			displayCluster(level + 1, tag + "." + num, (RawCluster) c.next());
		}
	}

	public static ArrayList<SearchResult> analytics(String result) {
		ArrayList<SearchResult> al = new ArrayList<SearchResult>();
		Parser parser = Parser.createParser(result, "utf8");

		// 注册新的结点解析器
		PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
		factory.registerTag(new LiTag());
		parser.setNodeFactory(factory);

		NodeFilter emFilter = new NodeClassFilter(LiTag.class);
		NodeList nodelist;
		try {
			nodelist = parser.extractAllNodesThatMatch(emFilter);
		} catch (ParserException e) {
			return al;
		}

		Node[] nodes = nodelist.toNodeArray();

		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			if (node instanceof LiTag) {
				LiTag lt = (LiTag) node;
				if (lt.getAttribute("class") != null
						&& lt.getAttribute("class").equals("g")) {
					SearchResult sr = new SearchResult();
					String content = lt.getStringText();
					Pattern p = Pattern
							.compile("<a[\\s]+[^<]*?href[\\s]?=(\"|'|)(.*?)\\1.*?>([^<]+|.*?)?<\\/a>");
					Matcher m = p.matcher(content);
					if (m.find()) {
						sr.setLink(m.group(2));
						sr.setTitle(HTMLToTEXT(m.group(3)));
					} else
						continue;
					Pattern p2 = Pattern.compile("<div class=\"s\">(.*)<br>");
					Matcher m2 = p2.matcher(content);
					if (m2.find()) {
						sr.setContent(HTMLToTEXT(m2.group(1)));
						al.add(sr);
					}
				}
			}
		}
		return al;
	}

	public static String getGoogleSearchResult(String keyword) {
		try {
			String s = "http://www.google.cn/search?hl=zh-CN&source=hp&q="
					+ URLEncoder.encode(keyword, "UTF-8")
					+ "&num=100&btnG=Google+%E6%90%9C%E7%B4%A2&aq=f&oq=";
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "IIC2.0/PC 2.1.0.0");
			con.setRequestProperty("connection", "Close");
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "GBK"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			con.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String HTMLToTEXT(String html) {
		// html=html.replaceAll("<([^<>]+)>","");
		// html=StringUtils.replace(html, "&nbsp;"," ");
		// html=StringUtils.replace(html, "&#160;"," ");
		// html=StringUtils.replace(html, "&lt;","<");
		// html=StringUtils.replace(html, "&gt;",">");
		// html=StringUtils.replace(html, "&quot;","\"");
		// html=StringUtils.replace(html, "&amp;","&");

		return html.replaceAll("<([^<>]+)>", "");

	}

	private static class SearchResult {
		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		private String content = "<no content>";
		private String link = "<no link>";
		private String title = "<no title>";
	}

	static class LiTag extends CompositeTag {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4128765593530859643L;

		private static final String[] mIds = new String[] { "LI" };

		// private static final String[] mEndTagEnders = new String[] {"DIV"};
		@Override
		public String[] getIds() {
			return (mIds);
		}

		@Override
		public String[] getEnders() {
			return (mIds);
		}

		// @Override
		// public String[] getEndTagEnders (){
		// return (mEndTagEnders);
		// }

		public String getLink() {
			return super.getAttribute("href");
		}

		public String getMethod() {
			return super.getAttribute("method");
		}

	}
}
