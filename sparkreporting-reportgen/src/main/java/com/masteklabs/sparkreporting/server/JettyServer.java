package com.masteklabs.sparkreporting.server;

import java.util.EnumSet;
import java.util.Properties;

import javax.servlet.DispatcherType;

import org.apache.spark.sql.SparkSession;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class JettyServer implements Runnable {

	SparkSession sparkSession;

	Properties appProperties;

	public JettyServer(SparkSession sparkSession, Properties appProperties) {
		this.sparkSession = sparkSession;
		this.appProperties = appProperties;
	}

	@Override
	public void run() {

		Server server = new Server();

		ServerConnector http = new ServerConnector(server);
		http.setHost("0.0.0.0");
		int port = Integer.parseInt(appProperties.getProperty("restserviceport"));

		// http.setHost("localhost");
		http.setPort(port);

		server.addConnector(http);

		ContextHandler testDataHandler = new ContextHandler("/home");
		testDataHandler.setContextPath("/home");
		testDataHandler.setHandler(new TestHandler(sparkSession));

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/");
		servletContextHandler.addServlet(DefaultServlet.class, "/");

		FilterHolder holder = servletContextHandler.addFilter(CrossOriginFilter.class, "/*",
				EnumSet.of(DispatcherType.REQUEST));
		holder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		holder.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
		holder.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
		HandlerCollection collection = new HandlerCollection();

		collection.addHandler(testDataHandler);
		collection.addHandler(servletContextHandler);

		server.setHandler(collection);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.destroy();
		}

	}

}
