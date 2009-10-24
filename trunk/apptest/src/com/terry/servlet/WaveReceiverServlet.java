package com.terry.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Oct 24, 2009 8:45:13 AM
 */
public class WaveReceiverServlet extends AbstractRobotServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1209131804208251939L;

	private static Log log = LogFactory.getLog(WaveReceiverServlet.class);

	@Override
	public void processEvents(RobotMessageBundle bundle) {
		log.debug("wave event received...");
		Wavelet wavelet = bundle.getWavelet();

		if (bundle.wasSelfAdded()) {
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			textView.append("和我聊天吧~");
		}

		for (Event e : bundle.getEvents()) {
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				Blip blip = wavelet.appendBlip();
				TextView textView = blip.getDocument();
				textView.append("我现在只会说你好,其他啥也不会~~");
			}
		}

	}

}
