package com.microblog.main;

import java.util.Observable;
import java.util.Observer;

import com.microblog.process.Command;
import com.microblog.process.ProcessControl;
import com.microblog.socket.SocketObservable;
import com.microblog.util.Settings;

public class Main {

	public static void main(final String[] args) throws Exception {
		final Settings settings = Settings.getInstance();
		if (settings == null)
			throw new Exception("Settings null");
		final ProcessControl processControl = new ProcessControl();
		final SocketObservable client = new SocketObservable(settings
				.getSocketHost(), settings.getSocketPort(), settings
				.getSocketPassport(), settings.getSocketPasscode());
		
		client.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				processControl.doUpdate((Command) arg);
			}
		});
		Thread thread = new Thread(client);
		thread.start();
	}
}
