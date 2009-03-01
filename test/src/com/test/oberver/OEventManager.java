package com.test.oberver;

import java.util.Observable;

/*
 * 观察者/监听器模式测试
 */
public class OEventManager extends Observable {
	public static void main(String[] args) {
		OEventManager mgr = new OEventManager();
		mgr.addObserver(new OListener());
		mgr.addObserver(new OListener());
		mgr.addObserver(new OListener());
		mgr.fireChange("Changed. ");
	}

	public void fireChange(String msg) {
		setChanged();
		notifyObservers(new OEvent(msg));
	}
}
