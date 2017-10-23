package 채팅클라이언트;

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
			System.out.println("서버 연결됨.");
			
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			dos.writeUTF(channel+" "+nickName);
			System.out.println("클라이언트 : 닉네임 전송완료");
			while(dis!=null){
				msg=dis.readUTF();
				gui.appendMsg(msg);
				if(msg.contains("%%^^너는 좀 나가야겠다")){
					JOptionPane.showMessageDialog(null, "너 강퇴");
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
