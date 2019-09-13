package websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/ws/server")
public class ServerW {

    List<Session> sesiones = new ArrayList<>();

    @OnOpen
    public void open(Session session) {
        System.out.println("Sesion abierta");
        sesiones.add(session);
    }

    @OnMessage
    public void handleMessage(Session session, String tipo) {
        try {
            broadcast(session,tipo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Session sesion, String tipo) throws IOException {
        try {
            System.out.println("Haciendo broadcast!");
            for (Session s : sesion.getOpenSessions()) {
                s.getAsyncRemote().sendText(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session cerrada");
        sesiones.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
