package ca.ulaval.glo4002.game;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class GameServer implements Runnable {

    private static final int PORT = 8181;

    public static void main(String[] args) {
        new GameServer().run();
    }

    public void run() {
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ProjectConfig packageConfig = new ProjectConfig();

        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(server.isRunning()) {
                server.destroy();
            }
        }
    }
}
