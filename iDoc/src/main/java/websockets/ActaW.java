package websockets;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/ws/actas")
public class ActaW{
    
    private Set<Session> sessions = new HashSet<>();
 
    @OnOpen
    public void open(Session session) {
        System.out.println("Session opened ==>");
        sessions.add(session);
    }
 
    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("Mensaje" + message);
    }
 
    @OnClose
    public void close(Session session) {
        System.out.println("Session cerrada");
        sessions.remove(session);
    }
 
    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}