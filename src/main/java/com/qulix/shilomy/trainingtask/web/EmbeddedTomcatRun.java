package com.qulix.shilomy.trainingtask.web;

import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedTomcatRun {
    public static final String WEBAPPDIR = "src/main/webapp";
    private static final String PROPERTY_SERVER_BASE_DIR = "server.base_dir";
    private static final String PROPERTY_SERVER_PORT = "server.port";

    public static void main(String[] args) throws LifecycleException {
        PropertyContext propertyContext = PropertyContext.getInstance();
        String contextPath = "";
        String webappDir = new File(WEBAPPDIR).getAbsolutePath();

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(propertyContext.get(PROPERTY_SERVER_BASE_DIR));

        tomcat.setPort(Integer.parseInt(propertyContext.get(PROPERTY_SERVER_PORT)));
        tomcat.getConnector();

        tomcat.addWebapp(contextPath, webappDir);

        tomcat.start();
        tomcat.getServer().await();
    }
}
