package main;

import chat.ChatWebSocket;
import chat.WebSocketChatServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.ServletContext;

public class Main {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);
        WebSocketHandler webSocketHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(ChatWebSocket.class);
            }
        };
        server.setHandler(webSocketHandler);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
