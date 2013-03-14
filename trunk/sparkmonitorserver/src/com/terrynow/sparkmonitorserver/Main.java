package com.terrynow.sparkmonitorserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @date 2012-3-27 下午1:58:03
 */
public class Main extends Application {
	private static Log log = LogFactory.getLog(Main.class);

	private static final int PORT = 8182;
	private static final String MAILPATH = "/spark";

	public static void main(String[] args) throws Exception {
		try {
			Component component = new Component();
			component.getServers().add(Protocol.HTTP, PORT);
			component.getDefaultHost().attach(new Main());
			component.start();
			log.info("listening imcoming request on port: " + PORT + ", with path: "
					+ MAILPATH);
		} catch (Exception e) {
			log.error("can not start server, exception: " + e.getMessage(), e);
		}
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(this.getContext());
		// router.attach("/mail/{test}", MailResource.class);
		router.attach(MAILPATH, SparkResource.class);
		return router;
	}
}
