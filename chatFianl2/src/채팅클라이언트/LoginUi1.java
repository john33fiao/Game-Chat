package 채팅클라이언트;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginUi1 extends JFrame implements ActionListener,Serializable {
	private static final long serialVersionUID = 1L;
	private JPanel Login_pa;
	private List channel_list;
	private JTextField id_tf;
	private InputStream IN;
	private JButton login_btn = new JButton("Contact");
	private Music loginBgm =new Music("메인테마.mp3",true);
	private Toolkit kit =Toolkit.getDefaultToolkit();

	private Image img3=kit.getImage(LoginUi1.class.getResource("/버튼.png"));
	private Image img=kit.getImage(LoginUi1.class.getResource("/나무.jpg"));
	private  Icon icon=new ImageIcon(img);
	private  Icon icon3=new ImageIcon(img3);
	private JButton bgm_off=new JButton(icon3);
	private JLabel imlb=new JLabel(icon);
	private int rightLeft=0;
	
	public static void main(String[] args) {
		LoginUi1 login=new LoginUi1();
		login.login_ui();
	}
	
	private void login_ui(){
		
		login_btn.addActionListener(this);
		setTitle("GameChannelChatting");		// 서요한 수정함
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 408);
		Login_pa = new JPanel();
		
		bgm_off.setBounds(355,340,40,40);
		Login_pa.add(bgm_off);
		bgm_off.addActionListener(this);
		
		setContentPane(Login_pa);
		Login_pa.setLayout(null);
		
		JLabel lb4 = new JLabel("  Channel");
		lb4.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,15));
		lb4.setForeground(new Color(255, 255, 255));
		lb4.setBounds(38, 95, 100, 15);
		Login_pa.add(lb4);
		
		JLabel lblId = new JLabel("   I  D");
		lblId.setBounds(38, 244, 100, 15);
		lblId.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,15));
		lblId.setForeground(new Color(255, 255,255));
		Login_pa.add(lblId);
		
		channel_list = new List();
		channel_list.add("LeagueOfLegends");
		channel_list.add("FifaOnline3");
		channel_list.add("MapleStory");
		channel_list.add("Overwatch");
		channel_list.add("TalesWeaver");
		channel_list.add("Starcraft");			// 여기 서요한 수정함
		channel_list.setBounds(154, 50, 153, 100);
		channel_list.setBackground(new Color(255, 255, 255));
		Login_pa.add(channel_list);
		channel_list.addActionListener(this);
		
		id_tf = new JTextField();
		id_tf.setColumns(10);
		id_tf.setBounds(154, 241, 153, 21);
		Login_pa.add(id_tf);
		
		login_btn.setBounds(83, 330, 217, 32);
		login_btn.setFont(new Font("Bitstream Vera Sans Mono",Font.BOLD,12));
		Login_pa.add(login_btn);
		
		imlb.setBounds(0, 0, 400, 408);
		Login_pa.add(imlb);
		setResizable(false);
		
		setVisible(true);
		
		loginBgm.start();
}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(channel_list.getSelectedItem()!=null){

			String msg=channel_list.getSelectedItem();
			img=kit.getImage(LoginUi1.class.getResource("/"+msg+".jpg"));
			icon=new ImageIcon(img);
			imlb.setIcon(icon);
			Login_pa.add(imlb);
			imlb.invalidate();
		}
		if(e.getSource()==bgm_off){
			loginBgm.stop();
		}
		if(e.getSource()==login_btn || e.getSource()==id_tf){
			
			boolean tf = false;
			char[] arr = id_tf.getText().toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if(((int)arr[i]>=48 && (int)arr[i]<=57) || (int)arr[i]>=97 && (int)arr[i]<=122){
					tf=true;
				}else{
					tf=false;
					break;
				}
			}
			if(id_tf.getText()==null){
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			}else if(channel_list.getSelectedItem()==null){
				JOptionPane.showMessageDialog(null, "채널을 선택해주세요");
			}else if(!tf){
				JOptionPane.showMessageDialog(null, "아이디는 영문 소문자와 숫자만 사용 가능합니다");
				id_tf.setText("");
			}else{
				String nickMsg=id_tf.getText();
				String channelMsg=channel_list.getSelectedItem();
				loginBgm.stop();
				dispose();
				ClientThread ct;
				ct=new ClientThread(nickMsg,channelMsg);
				ct.start();
			}
		}
	}
}