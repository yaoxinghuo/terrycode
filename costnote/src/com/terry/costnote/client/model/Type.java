package com.terry.costnote.client.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 14, 2009 11:49:38 AM
 */
public class Type extends BaseModelData {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1735091373130678201L;

	public Type(int type, String name) {
		set("type", type);
		set("name", name);
	}

	public String getName() {
		return (String) get("name");
	}

	public int getType() {
		return (Integer) get("type");
	}

	@Override
	public String toString() {
		return getName();
	}
}
