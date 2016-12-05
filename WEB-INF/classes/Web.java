import java.io.IOException ;

import javax.websocket.* ;
import javax.websocket.server.ServerEndpoint ;

@ServerEndpoint("/websocket")
public class Web{
	private int count = 0 ;
	private Session session ;

	@OnOpen
	public void onOpen(Session session){
		this.session = session ;
		this.count ++ ;
		System.out.println("new user count is " + count);
	}

	@OnClose
	public void onClose(){
		this.count -- ;
		System.out.println("onclose count is " + count);
	}

	@OnMessage
	public void onMessage(String message,Session session){
		System.out.println("new msg " + message);
		try{
			session.getBasicRemote().sendText(message);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session,Throwable error){
		System.out.println("error");
		error.printStackTrace();
	}
}