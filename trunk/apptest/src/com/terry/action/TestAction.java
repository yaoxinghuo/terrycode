package com.terry.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.opensymphony.xwork2.ActionSupport;
import com.terry.client.BeanPost;
import com.terry.client.Post;

@Scope("prototype")
@Component("gwt-testAction")
public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1859767044000357740L;

	private List<Post> posts;
	private List<BeanPost> beanPosts;

	public String test(String name) {
		return "Struts2:Hello~" + name;
	}

	@SuppressWarnings("unchecked")
	public PagingLoadResult<Post> getPosts(final PagingLoadConfig config) {
		if (posts == null) {
			loadPosts();
		}

		if (config.getSortInfo().getSortField() != null) {
			final String sortField = config.getSortInfo().getSortField();
			if (sortField != null) {
				Collections.sort(posts, config.getSortInfo().getSortDir()
						.comparator(new Comparator() {
							public int compare(Object o1, Object o2) {
								Post p1 = (Post) o1;
								Post p2 = (Post) o2;
								if (sortField.equals("forum")) {
									return p1.getForum().compareTo(
											p2.getForum());
								} else if (sortField.equals("username")) {
									return p1.getUsername().compareTo(
											p2.getUsername());
								} else if (sortField.equals("subject")) {
									return p1.getSubject().compareTo(
											p2.getSubject());
								} else if (sortField.equals("date")) {
									return p1.getDate().compareTo(p2.getDate());
								}
								return 0;
							}
						}));
			}
		}

		ArrayList<Post> sublist = new ArrayList<Post>();
		int start = config.getOffset();
		int limit = posts.size();
		if (config.getLimit() > 0) {
			limit = Math.min(start + config.getLimit(), limit);
		}
		for (int i = config.getOffset(); i < limit; i++) {
			sublist.add(posts.get(i));
		}
		return new BasePagingLoadResult(sublist, config.getOffset(), posts
				.size());
	}

	private void loadPosts() {
		posts = new ArrayList<Post>();
		beanPosts = new ArrayList<BeanPost>();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db
					.parse(getClass().getResourceAsStream("posts.xml"));
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("row");

			for (int s = 0; s < nodeList.getLength(); s++) {
				Node fstNode = nodeList.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					NodeList fields = fstElmnt.getElementsByTagName("field");
					Post p = new Post();
					p.setForum(getValue(fields, 0));
					p.setDate(sf.parse(getValue(fields, 1)));
					p.setSubject(getValue(fields, 2));
					p.setUsername(getValue(fields, 4));
					posts.add(p);

					BeanPost beanPost = new BeanPost();
					beanPost.setForum(getValue(fields, 0));
					beanPost.setDate(sf.parse(getValue(fields, 1)));
					beanPost.setSubject(getValue(fields, 2));
					beanPost.setUsername(getValue(fields, 4));
					beanPosts.add(beanPost);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getValue(NodeList fields, int index) {
	    NodeList list = fields.item(index).getChildNodes();
	    if (list.getLength() > 0) {
	      return list.item(0).getNodeValue();
	    } else {
	      return "";
	    }
	  }

}
