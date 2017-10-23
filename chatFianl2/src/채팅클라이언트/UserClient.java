package ä��Ŭ���̾�Ʈ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
public class UserClient {
	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private LoginUi2 gui;
	private String msg;
	private String nickName;
	private String channel;
	private Thread thr=null;
	
	public final void setGui(LoginUi2 gui) {
		this.gui = gui;
		setMusic();
	}
	public void setMusic(){
		thr = new Thread(){public void run() {
			gui.gameStart();	
			};
		};
		thr.start();
	}

	public void UserClient() {
		try {
			socket = new Socket("203.236.209.202", 7777);
			System.out.println("���� �����.");
			
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			dos.writeUTF(channel+" "+nickName);
			System.out.println("Ŭ���̾�Ʈ : �г��� ���ۿϷ�");
			while(dis!=null){
				msg=dis.readUTF();
				gui.appendMsg(msg);
				if(msg.contains("%%^^�ʴ� �� �����߰ڴ�")){
					JOptionPane.showMessageDialog(null, "�� ����");
					System.exit(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				dis.close();
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		UserClient clientBackground = new UserClient();	
	}
	public void sendMessage(String msg2) {
		try {
			dos.writeUTF(msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
