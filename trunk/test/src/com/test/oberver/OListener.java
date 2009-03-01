package com.test.oberver;

import java.util.Observable;
import java.util.Observer;

public class OListener implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.err.println("Passed '"+arg+"' by "+o+" to "+this);
	}

}
