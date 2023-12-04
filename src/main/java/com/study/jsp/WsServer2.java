package com.study.jsp;
import java.io.IOException;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.RemoteEndpoint.Basic;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocketendpoint")
public class WsServer2
{
	private static final java.util.Set<Session> sessions =
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id ; "+ session.getId());
		
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session " + session.getId()+ " has ended");
		sessions.remove(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from " + session.getId() + ":" + message);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to : "+message);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		sendAllSessionToMessage(session,message);
	}
	
	private void sendAllSessionToMessage(Session self, String message) {
		try{
			for(Session session : WsServer2.sessions) {
				if( ! self.getId().equals(session.getId()) )
					session.getBasicRemote().sendText("All : " + message);
			}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@OnError
		public void onError(Throwable e, Session session) {
			
		}
	}

