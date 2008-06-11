package com.bank.server;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInit implements ServiceLifeCycle {
	 /**
     * This will be called during the deployement time of the service. 
     * irrespective
     * of the service scope this method will be called
     */
    public void startUp(ConfigurationContext ignore, AxisService service) {
 
        try {
            //System.out.println("Starting spring init");
            ClassLoader classLoader = service.getClassLoader();
            ClassPathXmlApplicationContext appCtx = new
            ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"}, false);
                appCtx.setClassLoader(classLoader);
                appCtx.refresh();
            //System.out.println("spring loaded");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This will be called during the system shut down time. 
     * irrespective
     * of the service scope this method will be called
     */
    public void shutDown(ConfigurationContext ctxIgnore, AxisService ignore) {
    }
}
