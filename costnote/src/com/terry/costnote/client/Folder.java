package com.terry.costnote.client;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 11, 2009 8:07:26 PM
 */
public class Folder extends BaseTreeModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6089677520175664308L;
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

	@Override
	public String toString() {
		return getName();
	}
}
