package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocket {
    private Session session;

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {

        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        session.close();
    }
}
