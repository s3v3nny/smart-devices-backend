package ru.s3v3nny.smartdevices;

import lombok.SneakyThrows;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class SmartDevicesServer {
    private Server server;

    @SneakyThrows
    public void start(int port) {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");

        server = new Server();
        server.setHandler(contextHandler);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        ServletHolder holder = contextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        holder.setInitOrder(0);
        holder.setInitParameter("jersey.config.server.provider.packages",
                "ru.s3v3nny.smartdevices.servlets");

        server.start();
    }

    @SneakyThrows
    public void stop() {
        server.stop();
    }
}
