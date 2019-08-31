package websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/ws/actas")
public class ActaW {

    List<Session> sesiones = Collections.synchronizedList(new ArrayList<Session>());

    @OnOpen
    public void open(Session session) {
        System.out.println("Sesion abierta");
        sesiones.add(session);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("Mensaje recibido en Java: " + message + " de " + session.getId());
        try {
            broadcast(session,message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Session sesion, String mensaje) throws IOException {
        try {
            for (Session s : sesion.getOpenSessions()) {
                s.getAsyncRemote().sendText(mensaje);
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
