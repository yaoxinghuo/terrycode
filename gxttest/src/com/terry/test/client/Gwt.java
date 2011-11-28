package com.terry.test.client;

import java.io.Serializable;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {

	private Viewport viewport;

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	@Override
	public void onModuleLoad() {

		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		createNorth();
		createWest();

		TabPanel panel = new TabPanel();
		panel.setResizeTabs(true);
		TabItem example = new TabItem("Example");
		example.setScrollMode(Scroll.AUTO);
		example.add(new GxtGridExample());
		panel.add(example);

		TabItem source = new TabItem("View Source");
		panel.add(source);

		viewport.add(panel, new BorderLayoutData(LayoutRegion.CENTER));

		RootPanel.get().add(viewport);

		// Window w = new Window();
		// w.setHeading("Product Information");
		// w.setModal(true);
		// w.setSize(600, 400);
		// w.setMaximizable(true);
		// w.setToolTip("The ExtGWT product page...");
		// w.setUrl("http://www.extjs.com/products/gxt");
		// w.show();

		// RequestBuilder br = new RequestBuilder(RequestBuilder.GET, null);
		// try {
		// br.sendRequest(null, new RequestCallback() {
		//
		// @Override
		// public void onResponseReceived(Request request, Response response) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onError(Request request, Throwable exception) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		// } catch (RequestException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private void createNorth() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-header' class='x-small-editor'><div id='demo-theme'></div><div id=demo-title>Ext GWT Examples</div></div>");

		HtmlContainer northPanel = new HtmlContainer(sb.toString());
		northPanel.setStateful(false);

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 33);
		data.setMargins(new Margins());
		viewport.add(northPanel, data);
	}

	private void createWest() {
		ContentPanel panel = new ContentPanel();
		panel.setHeading("导航栏");
		panel.setBodyBorder(false);
		panel.setWidth(200);

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 200);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins());

		Folder model = getTreeModel();

		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);

		final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setWidth(300);
		tree.setDisplayProperty("name");

		panel.add(tree);

		viewport.add(panel, westData);

	}

	public static Folder getTreeModel() {
		Folder[] folders = new Folder[] {
				new Folder("Beethoven", new Folder[] {

						new Folder("Quartets", new Music[] {
								new Music("Six String Quartets", "Beethoven",
										"Quartets"),
								new Music("Three String Quartets", "Beethoven",
										"Quartets"),
								new Music("Grosse Fugue for String Quartets",
										"Beethoven", "Quartets"), }),
						new Folder("Sonatas", new Music[] {
								new Music("Sonata in A Minor", "Beethoven",
										"Sonatas"),
								new Music("Sonata in F Major", "Beethoven",
										"Sonatas"), }),

						new Folder("Concertos",
								new Music[] {
										new Music("No. 1 - C", "Beethoven",
												"Concertos"),
										new Music("No. 2 - B-Flat Major",
												"Beethoven", "Concertos"),
										new Music("No. 3 - C Minor",
												"Beethoven", "Concertos"),
										new Music("No. 4 - G Major",
												"Beethoven", "Concertos"),
										new Music("No. 5 - E-Flat Major",
												"Beethoven", "Concertos"), }),

						new Folder("Symphonies", new Music[] {
								new Music("No. 1 - C Major", "Beethoven",
										"Symphonies"),
								new Music("No. 2 - D Major", "Beethoven",
										"Symphonies"),
								new Music("No. 3 - E-Flat Major", "Beethoven",
										"Symphonies"),
								new Music("No. 4 - B-Flat Major", "Beethoven",
										"Symphonies"),
								new Music("No. 5 - C Minor", "Beethoven",
										"Symphonies"),
								new Music("No. 6 - F Major", "Beethoven",
										"Symphonies"),
								new Music("No. 7 - A Major", "Beethoven",
										"Symphonies"),
								new Music("No. 8 - F Major", "Beethoven",
										"Symphonies"),
								new Music("No. 9 - D Minor", "Beethoven",
										"Symphonies"), }), }),
				new Folder(
						"Brahms",
						new Folder[] {
								new Folder(
										"Concertos",
										new Music[] {
												new Music("Violin Concerto",
														"Brahms", "Concertos"),
												new Music(
														"Double Concerto - A Minor",
														"Brahms", "Concertos"),
												new Music(
														"Piano Concerto No. 1 - D Minor",
														"Brahms", "Concertos"),
												new Music(
														"Piano Concerto No. 2 - B-Flat Major",
														"Brahms", "Concertos"), }),
								new Folder(
										"Quartets",
										new Music[] {
												new Music(
														"Piano Quartet No. 1 - G Minor",
														"Brahms", "Quartets"),
												new Music(
														"Piano Quartet No. 2 - A Major",
														"Brahms", "Quartets"),
												new Music(
														"Piano Quartet No. 3 - C Minor",
														"Brahms", "Quartets"),
												new Music(
														"String Quartet No. 3 - B-Flat Minor",
														"Brahms", "Quartets"), }),
								new Folder(
										"Sonatas",
										new Music[] {
												new Music(
														"Two Sonatas for Clarinet - F Minor",
														"Brahms", "Sonatas"),
												new Music(
														"Two Sonatas for Clarinet - E-Flat Major",
														"Brahms", "Sonatas"), }),
								new Folder("Symphonies", new Music[] {
										new Music("No. 1 - C Minor", "Brahms",
												"Symphonies"),
										new Music("No. 2 - D Minor", "Brahms",
												"Symphonies"),
										new Music("No. 3 - F Major", "Brahms",
												"Symphonies"),
										new Music("No. 4 - E Minor", "Brahms",
												"Symphonies"), }), }),
				new Folder("Mozart", new Folder[] { new Folder("Concertos",
						new Music[] {
								new Music("Piano Concerto No. 12", "Mozart",
										"Concertos"),
								new Music("Piano Concerto No. 17", "Mozart",
										"Concertos"),
								new Music("Clarinet Concerto", "Mozart",
										"Concertos"),
								new Music("Violin Concerto No. 5", "Mozart",
										"Concertos"),
								new Music("Violin Concerto No. 4", "Mozart",
										"Concertos"), }), }), };

		Folder root = new Folder("root");
		for (int i = 0; i < folders.length; i++) {
			root.add((Folder) folders[i]);
		}

		return root;
	}

}

class Music extends BaseTreeModel {

	public Music() {

	}

	public Music(String name) {
		set("name", name);
	}

	public Music(String name, String author, String genre) {
		set("name", name);
		set("author", author);
		set("genre", genre);
	}

	public String getName() {
		return (String) get("name");
	}

	public String getAuthor() {
		return (String) get("author");
	}

	public String getGenre() {
		return (String) get("genre");
	}

	public String toString() {
		return getName();
	}
}

class Folder extends BaseTreeModel implements Serializable {

	private static int ID = 0;

	public Folder() {
		set("id", ID++);
	}

	public Folder(String name) {
		set("id", ID++);
		set("name", name);
	}

	public Folder(String name, BaseTreeModel[] children) {
		this(name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public Integer getId() {
		return (Integer) get("id");
	}

	public String getName() {
		return (String) get("name");
	}

	public String toString() {
		return getName();
	}

}
