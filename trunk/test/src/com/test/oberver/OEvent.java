package com.test.oberver;

import java.util.EventObject;

public class OEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6178247005066425069L;

	public OEvent(Object source) {
		super(source);
	}

}
