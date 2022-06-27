package com.qulix.shilomy.trainingtask.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedTomcatRun {
    public static void main(String[] args) throws LifecycleException {
        String contextPath = "";
        String webappDir = new File("src/main/webapp").getAbsolutePath();

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("trainingtask-web");
        tomcat.setPort(8089);
        tomcat.getConnector();

        tomcat.addWebapp(contextPath, webappDir);

        tomcat.start();
        tomcat.getServer().await();
    }
}
