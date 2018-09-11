package util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//��ע������ָ��һ��url
@ServerEndpoint("/websocket")
public class MyWebSocket {

	//Set���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
	public static Set<MyWebSocket> set = new HashSet<MyWebSocket>();

	// ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;


	/**
	 * ���ӽ����ɹ����õķ���
	 * 
	 * @param session
	 *            ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		set.add(this); // ����set��
	
		System.out.println("�������Ӽ��룡");
	}

	/**
	 * ���ӹرյ��õķ���
	 */
	@OnClose
	public void onClose() {
		set.remove(this); // ��set��ɾ��
		
		System.out.println("��һ���ӹرգ�");
		
	}
	public void sendMessage(String message) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendMessageAll(String message) {
		for(MyWebSocket item:set) {
			item.sendMessage(String.valueOf(message));
		}
	}

	/**
	 * �յ��ͻ�����Ϣ����õķ���
	 * 
	 * @param message
	 *            �ͻ��˷��͹�������Ϣ
	 * @param session
	 *            ��ѡ�Ĳ���
	 */
//	@OnMessage
//	public void onCount(Session session) {
//		
//
//		// Ⱥ����Ϣ
//		for (MyWebSocket item : set) {
//			
//				try {
//					item.session.getBasicRemote().sendObject(count);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (EncodeException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		
//		}
//	}

	/**
	 * ��������ʱ����
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("��������");
		error.printStackTrace();
	}

}
