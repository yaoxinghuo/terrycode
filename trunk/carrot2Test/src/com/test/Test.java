package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: Nov 17, 2009 5:45:34 PM
 */
public class Test {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			/*
			 * Initialize local controller. Normally you'd run this only once
			 * for an entire application (controller is thread safe).
			 */
			final LocalController controller = initLocalController();

			/*
			 * Once we have a controller we can run queries. Change the query to
			 * something that is relevant to the data in your index.
			 */

			// Data for clustering, containing documents consisting of
			// titles and bodies of documents.
			String[][] documents = new String[][] {
					{ "Data Mining - Wikipedia",
							"http://en.wikipedia.org/wiki/Data_mining" },
					{ "KD Nuggets", "http://www.kdnuggets.com/" },
					{ "The Data Mine", "http://www.the-data-mine.com/" },
					{ "DMG", "http://www.dmg.org/" },
					{ "Two Crows: Data mining glossary",
							"http://www.twocrows.com/glossary.htm" },
					{ "Jeff Ullman's Data Mining Lecture Notes",
							"http://www-db.stanford.edu/~ullman/mining/mining.html" },
					{ "Thearling.com", "http://www.thearling.com/" },
					{ "Data Mining",
							"http://www.eco.utexas.edu/~norman/BUS.FOR/course.mat/Alex" },
					{ "CCSU - Data Mining",
							"http://www.ccsu.edu/datamining/resources.html" },
					{
							"Data Mining: Practical Machine Learning Tools and Techniques",
							"http://www.cs.waikato.ac.nz/~ml/weka/book.html" },
					{ "Data Mining - Monografias.com",
							"http://www.monografias.com/trabajos/datamining/datamining.shtml" },
					{
							"Amazon.com: Data Mining: Books: Pieter Adriaans,Dolf Zantinge",
							"http://www.amazon.com/exec/obidos/tg/detail/-/0201403803?v=glance" },
					{ "DMReview", "http://www.dmreview.com/" },
					{ "Data Mining @ CCSU", "http://www.ccsu.edu/datamining" },
					{ "What is Data Mining",
							"http://www.megaputer.com/dm/dm101.php3" },
					{ "Electronic Statistics Textbook: Data Mining Techniques",
							"http://www.statsoft.com/textbook/stdatmin.html" },
					{
							"data mining - a definition from Whatis.com - see also: data miner, data analysis",
							"http://searchcrm.techtarget.com/sDefinition/0,,sid11_gci211901,00.html" },
					{ "St@tServ - About Data Mining",
							"http://www.statserv.com/datamining.html" },
					{ "DATA MINING 2005",
							"http://www.wessex.ac.uk/conferences/2005/data05" },
					{ "Investor Home - Data Mining",
							"http://www.investorhome.com/mining.htm" },
					{ "SAS | Data Mining and Text Mining",
							"http://www.sas.com/technologies/data_mining" },
					{ "Data Mining Student Notes, QUB",
							"http://www.pcc.qub.ac.uk/tec/courses/datamining/stu_notes/dm_book_1.html" },
					{ "Data Mining",
							"http://datamining.typepad.com/data_mining" },
					{ "Two Crows Corporation", "http://www.twocrows.com/" },
					{ "Statistical Data Mining Tutorials",
							"http://www.autonlab.org/tutorials" },
					{ "Data Mining: An Introduction",
							"http://databases.about.com/library/weekly/aa100700a.htm" },
					{ "Data Mining Project",
							"http://research.microsoft.com/dmx/datamining" },
					{ "An Introduction to Data Mining",
							"http://www.thearling.com/text/dmwhite/dmwhite.htm" },
					{ "Untangling Text Data Mining",
							"http://www.sims.berkeley.edu/~hearst/papers/acl99/acl99-tdm.html" },
					{ "Data Mining Technologies", "http://www.data-mine.com/" },
					{ "SQL Server Data Mining",
							"http://www.sqlserverdatamining.com/" },
					{ "Data Warehousing Information Center",
							"http://www.dwinfocenter.org/" },
					{ "ITworld.com - Data mining",
							"http://www.itworld.com/App/110/050805datamining" },
					{
							"IBM Research | Almaden Research Center | Computer Science",
							"http://www.almaden.ibm.com/cs/quest" },
					{ "Data Mining and Discovery",
							"http://www.aaai.org/AITopics/html/mining.html" },
					{ "Data Mining: An Overview",
							"http://www.fas.org/irp/crs/RL31798.pdf" },
					{ "Data Mining", "http://www.gr-fx.com/graf-fx.htm" },
					{ "Data Mining Benchmarking Association (DMBA)",
							"http://www.dmbenchmarking.com/" },
					{ "Data Mining",
							"http://www.computerworld.com/databasetopics/businessintelligence/datamining" },
					{
							"National Center for Data Mining (NCDM) - University of Illinois at Chicago",
							"http://www.ncdm.uic.edu/" }, };

			// Although the query will not be used to fetch any data, if the
			// data
			// that you're submitting for clustering is a response to some
			// search engine-like query, please provide it, as the clustering
			// algrithm may use it to improve the clustering quality.
			final String query = "data mining";

			// The documents are provided for clustering in the
			// PARAM_SOURCE_RAW_DOCUMENTS parameter, which should point to
			// a List of RawDocuments.
			List documentList = new ArrayList(documents.length);
			for (int i = 0; i < documents.length; i++) {
				documentList.add(new RawDocumentSnippet(new Integer(i), // unique
						// id of
						// the
						// document,
						// can
						// be a
						// plain
						// sequence
						// id
						documents[i][0], // document title
						documents[i][1], // document body
						"dummy://" + i, // URL (not required for clustering)
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
		System.out.println("Collected: " + documents.size() + " snippets.");

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
			System.out.print("     " + count + ": " + document.getUrl() + "\n");
		}

		// finally, if this cluster has subclusters, descend into recursion.
		int num = 1;
		for (Iterator c = cluster.getSubclusters().iterator(); c.hasNext(); num++) {
			displayCluster(level + 1, tag + "." + num, (RawCluster) c.next());
		}
	}
}
