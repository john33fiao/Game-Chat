package 채팅서버;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import 채팅클라이언트.LoginUi1;

public class ServerGui extends JFrame implements ActionListener,Serializable{
	private static final long serialVersionUID = 1L;
	private JTextArea jta;
	private JTextField jtf = new JTextField();
	private ServerClient server = new ServerClient();
    private JLabel jl=new JLabel();

	private ArrayList<String> alist=new ArrayList<String>();
	public ServerGui() throws IOException {
		
		Image testimg = new ImageIcon(ServerGui.class.getResource("/나무2.jpg")).getImage();
		jta= new JTextArea(){
			{setOpaque(false);}
			protected void paintComponent(java.awt.Graphics g) {
				g.drawImage(testimg, 0, 0, this);
				super.paintComponent(g);
			}
		};
		
		JScrollPane scrollPane = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(54, 83, 324, 361);
		
		jta.setForeground(Color.WHITE);
		setLayout(null);
		jta .setBounds(54, 83, 308, 361);
		jta.setColumns(10);
		jta.setBackground(new Color(224,255,255));
		add(jta);
		add(scrollPane);

		jtf.setBounds(54, 470, 308, 28);
		jtf.setColumns(10);
		add(jtf);
		jtf.addActionListener(this);
		Toolkit kit =Toolkit.getDefaultToolkit();
		Image img2=kit.getImage(ServerGui.class.getResource("/나무.jpg"));
		
		jl.setIcon(new ImageIcon(img2));
		jl.setBounds(0, 0, 422, 519);
		add(jl);
		setVisible(true);

		setTitle("서버 클라이언트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 545);
	    setResizable(false);
		server.setGui(this);
		server.setting();
	}
	public static void main(String[] args) throws IOException {
		new ServerGui();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] tmp= new String[alist.size()];
		for(int i=0;i<alist.size();i++){
			tmp[i]=alist.get(i);
		}
		String msg = "운영자 : "+ jtf.getText() +"\n";
		server.sendMessage2(msg,tmp);
		jtf.setText("");
	}

	public void appendMsg(String msg) {
		if(msg.startsWith("%%%^")){
			msg="";
		}
		if(msg.startsWith("&&*추방투표시작")){
			msg="";
		}
		jta.append(msg);
	}
	public void sendList(String channel){
		alist.add(channel);
	}
	public void removeList(String channel){
		alist.remove(channel);
	}
}