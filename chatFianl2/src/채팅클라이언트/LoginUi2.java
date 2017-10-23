package ä��Ŭ���̾�Ʈ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class LoginUi2 extends JFrame implements ActionListener,Serializable {

	
	private UserClient userClient = new UserClient();
	private JPanel jp;
	private JTextField msg_tf;
	private JButton note_btn = new JButton("Whisper");
	private JButton joinroom_btn = new JButton("RoomJoin");

	private JButton send_btn = new JButton("Send");
	private List user_list = new List();
	private List room_list = new List();
	private JTextArea Chat_area;
	private String nickName;
	private String channel;

	private JMenuItem bgmStart=new JMenuItem("Bgm On");
	private JMenuItem bgmStop=new JMenuItem("Bgm off");
	private JMenuItem ����=new JMenuItem("����");
	
	private JMenuBar mb= new JMenuBar();

	private JMenu muBan = new JMenu("Ban");
	private JMenuItem ban = new JMenuItem("���� �߹�");
	
	private JMenu muBgm = new JMenu("Bgm");

	private JMenu muOther = new JMenu("Other");

	private int rightLeft=0;
	private JLabel mlb=new JLabel();

	
	private Music mainBgm=null;
	public void setMusic(){
		mainBgm =new Music(this.channel+".mp3",true);
		mainBgm.start();
	}
	public void UiNickName(String nickName){
		this.nickName=nickName;
	}
	public void UiChannel(String channel){
		this.channel=channel;
	}
	public static void main(String[] args){
		LoginUi2 loginUi2=new LoginUi2();
	}
	private void start(){
		note_btn.addActionListener(this);
		joinroom_btn.addActionListener(this);	
		send_btn .addActionListener(this);
		
		muBan.addActionListener(this);
		muOther.addActionListener(this);
		����.addActionListener(this);
		muBgm.addActionListener(this);
		
		bgmStart.addActionListener(this);
		bgmStop.addActionListener(this);
		ban.addActionListener(this);
		
	}
	Toolkit kit =Toolkit.getDefaultToolkit();
	Image img2=kit.getImage(LoginUi1.class.getResource("/����.jpg"));

	public void Main_init(String nickName2,String channel2){
		
		setMusic();
		start();
		Image testimg = new ImageIcon(LoginUi1.class.getResource("/"+channel2+"2.jpg")).getImage();
		Chat_area = new JTextArea(){
			{setOpaque(false);}
			protected void paintComponent(java.awt.Graphics g) {
				g.drawImage(testimg, 0, 0, this);
				super.paintComponent(g);
				
			}
		};
		this.setTitle(channel2+" ä�ù� / "+"��: "+nickName);
		jp = new JPanel();

		user_list.setBackground(new Color(255,255,255));
		room_list.setBackground(new Color(255,255,255));
		
		setContentPane(jp);
		jp.setLayout(null);
	
		Chat_area.setForeground(Color.YELLOW);
		Chat_area.setBounds(14, 63, 303, 404);
		Chat_area.setEditable(false);
		Chat_area.setLineWrap(true);
		jp.add(Chat_area);
		
		JScrollPane scrollPane = new JScrollPane(Chat_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(14, 63, 283, 404);
		jp.add(scrollPane);
		
		room_list.add("LeagueOfLegends");
		room_list.add("FifaOnline3");
		room_list.add("MapleStory");
		room_list.add("Overwatch");
		room_list.add("TalesWeaver");
		room_list.add("Starcraft");
		
		room_list.addActionListener(this);
		
		user_list.setBounds(307,80, 115, 165);
		jp.add(user_list);
		room_list.addActionListener(this);
		
		room_list.setBounds(307, 302, 115, 165);
		jp.add(room_list);
		
		JLabel label = new JLabel("UserList");
		label.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		label.setBounds(307, 62, 79, 15);
		label.setForeground(Color.WHITE);
		jp.add(label);
		
		note_btn.setBounds(307, 250, 115, 23);
		note_btn.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,11));
		jp.add(note_btn);
		
		JLabel lab2 = new JLabel("RoomList");
		lab2.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		lab2.setBounds(307, 220, 70, 140);
		lab2.setForeground(Color.WHITE);
		jp.add(lab2);
		
		joinroom_btn.setBounds(305, 480, 117, 23);
		joinroom_btn.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,11));
		jp.add(joinroom_btn);
		
		msg_tf = new JTextField();
		msg_tf.setBounds(12, 480, 219, 22);
		jp.add(msg_tf);
		msg_tf.setColumns(10);
		msg_tf.addActionListener(this);
		
		send_btn.setBounds(235, 480, 65, 23);
		send_btn.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,10));
		jp.add(send_btn);
		this.setVisible(true);
		
		mb.setBounds(0, 0, 445, 23);
		muBgm.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		mb.add(muBgm);
		
		bgmStart.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		muBgm.add(bgmStart);
		bgmStart.setEnabled(false);
		
		bgmStop.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		muBgm.add(bgmStop);
		mb.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		mb.add(muBan);
		
		ban.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		muBan.add(ban);
		
		mb.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		mb.add(muOther);
		
		����.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		muOther.add(����);
		

		jp.add(mb);
		
		mb.invalidate();
		
		mlb.setBounds(rightLeft,30,445,23);
		mlb.setForeground(Color.white);
		jp.add(mlb);
		
		Toolkit kit =Toolkit.getDefaultToolkit();
		Image img=kit.getImage(LoginUi1.class.getResource("/����.jpg"));
		Icon icon2=new ImageIcon(img);
		JLabel ijlb=new JLabel(icon2);
		ijlb.setBounds(0, 0, 445, 540);
		jp.add(ijlb);
		
		
		setResizable(false);
		mlb.setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 540);

		
		userClient.setNickname(nickName2);
		userClient.setChannel(channel2);
		userClient.setGui(this);
		userClient.UserClient();		
	}
	public void gameStart(){
		int a=1;
		while(true){
			try {
				Thread.sleep(16);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(rightLeft>430){
				rightLeft=0;
			}
			rightLeft+=a;
			
			mlb.setLocation(rightLeft,30);
		}
	}
	private static Boolean beforeLogin=true;
	private static String meNick = "";
	private int userTotal=0;
	
	public void appendMsg(String msg) {

		if(msg.contains(":�ӼӸ� to ")){
			if(msg.contains(":�ӼӸ� to "+meNick)){
				msg=msg.replaceFirst(":�ӼӸ� to "+meNick+">", "�� �ӼӸ� : \n");
			}else if(msg.contains(meNick+":�ӼӸ� to ")){
				msg=msg.substring(msg.indexOf("to ")+3, msg.lastIndexOf(">"))+"���� �ӼӸ�\n"+msg.substring(msg.indexOf(">")+1, msg.lastIndexOf(")")+1)+"\n";
			}else{
				msg="";
			}
		}
		if(msg.startsWith(channel)){
	         msg = msg.replaceFirst(channel, "");
	      }
		if(msg.startsWith("!@#$%[")){
			if(beforeLogin){
				meNick=msg.substring(msg.indexOf("�ʴ�@@")+4, msg.length());
				beforeLogin=false;
			}
			msg=msg.substring(6, msg.lastIndexOf("]!@#$%^"));
			usL=msg.split(", ");
			setTitle(channel+"ä�� / �� : "+meNick);
			mlb.setText(channel+" ä���Դϴ�");
			user_list.removeAll();
			userTotal=0;		// �߰��� �κ��Դϴ� 0914
			for (int i = 0; i < usL.length; i++) {
				user_list.add(usL[i]);
				userTotal++;
			}
			setTitle(channel+"ä�� / �� : "+meNick+" / ���� "+userTotal+"�� ������");	
			msg="";
		}
		if(msg.startsWith("%%%^")){	// �� ������ ���
			msg="";
		}
		if(msg.startsWith(meNick)){
		msg = msg.replaceFirst(meNick, "��");
		}
		
		if(msg.startsWith("&&*�߹�")){	
			if(msg.contains(meNick)){
				msg="";
			}else{
				msg = msg.replace("&&*�߹�", "");
				int btf = JOptionPane.showConfirmDialog(null, msg+"���� �߹濡 �����Ͻʴϱ�?", msg+"���� �߹� ��ǥ", 
						JOptionPane.YES_NO_OPTION);
				if(btf==0){
					userClient.sendMessage("�߹浿��"+msg);
				}else if(btf==1){
					userClient.sendMessage("�߹�ݴ�"+msg);
				}
				msg="";
			}
		}
		if(msg.startsWith("ä�ù� �������� 2�� ������ ���")){
			JOptionPane.showMessageDialog(null, msg);
			msg="";
		}
		
		//----------------------- ���۽���
		  Chat_area.append(msg);
	      Chat_area.setCaretPosition(Chat_area.getDocument().getLength());
	    // ---------------------- ���ۿϷ�
	}
	
	String usL[]; //
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ClientThread ct ;
		if(e.getSource()==ban){	// ���� �߰��մϴ� 0914
			String banUser;
			String ULT[] = new String[usL.length-1];
			for (int i = 0; i < usL.length; i++) {
				if(usL[i].equals(meNick)){
					for (int j = 0; j < i; j++) {
						ULT[j] = usL[j];
					}
					for (int j = i; j < ULT.length; j++) {
						ULT[j] = usL[j+1];
					}
				}
			}
			banUser = (String) JOptionPane.showInputDialog(null,"�߹��ϰ������ ������ �������ֽø� ��ǥ�� ���۵˴ϴ�", "���� �߹�",
					JOptionPane.QUESTION_MESSAGE,null, ULT, null);
			if(banUser!=null && banUser!=meNick){
				userClient.sendMessage("&&*�߹���ǥ����"+banUser);
			}else if(banUser==meNick){
			}
		}
		if(e.getSource()==����){
			JOptionPane.showMessageDialog(null, "UserList���� ���� ������ Whisper Ŭ���ϸ� �ӼӸ��� �����մϴ�.\n"
				+ "RoomList���� ä�� ������ RoomJoin Ŭ���� �ش� ������ �����մϴ�.");
		}
		if(e.getSource()==bgmStart){setMusic();bgmStart.setEnabled(false);bgmStop.setEnabled(true);}
        if(e.getSource()==bgmStop){mainBgm.stop();bgmStart.setEnabled(true);bgmStop.setEnabled(false);}
		
		if(e.getSource()==note_btn){
			msg_tf.setText("�ӼӸ� to "+user_list.getSelectedItem()+">");
		}else if(e.getSource()==joinroom_btn && room_list.getSelectedItem()!=null){	
			channel=room_list.getSelectedItem();
			mainBgm.stop();
			
			dispose();
			ct=new ClientThread(meNick, channel);
			ct.start();
			userClient.sendMessage("%%%^"+meNick+"^%%%�� ���� ������");
		
		}else if(e.getSource()==send_btn || e.getSource()==msg_tf){
			if(msg_tf.getText().isEmpty()){	
			}else{
				long time = System.currentTimeMillis(); 
				SimpleDateFormat dayTime = new SimpleDateFormat("hh : mm");
				String str = dayTime.format(new Date(time));
				String msg3 = channel + meNick + ":" + msg_tf.getText()+" ("+str+")\n";
				userClient.sendMessage(msg3);
				msg_tf.setText("");
			}
		}
	}

}
class ClientThread extends Thread{
	private String nickName;
	private String channel;
	private int rightLeft;
	public ClientThread(String nickName,String channel){
		this.channel=channel;
		this.nickName=nickName;
	}
	public void run(){
			LoginUi2 ui=new LoginUi2();
			ui.UiNickName(nickName);
			ui.UiChannel(channel);
			ui.Main_init(nickName,channel);
	}
}