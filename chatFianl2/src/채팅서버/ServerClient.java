package 채팅서버;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServerClient {
	private ServerSocket serverSocket;
	private Socket socket;
	private ServerGui gui;
	private String msg;
	private Set<String> setChannel=new HashSet<>();
	private ArrayList<String> user_list = new ArrayList<String>();

	private Map<String,HashMap<String,DataOutputStream>> mainMap;
	
	private Map<String, DataOutputStream> clientsList1 ;
	private Map<String, DataOutputStream> clientsList2 ;
	private Map<String, DataOutputStream> clientsList3 ;
	private Map<String, DataOutputStream> clientsList4 ;
	private Map<String, DataOutputStream> clientsList5 ;
	private Map<String, DataOutputStream> clientsList6 ;

	private Map<String, Boolean> mainVoteMap;
	private Map<String, Integer> mainVoteCount;
	private Map<String, Integer> mainVoteDisagree;
	private static int userCnt=0;
	
	public ServerClient(){
		
		mainMap=new HashMap<String,HashMap<String,DataOutputStream>>();
		clientsList1 = new HashMap<String, DataOutputStream>();
		clientsList2 = new HashMap<String, DataOutputStream>();
		clientsList3 = new HashMap<String, DataOutputStream>();
		clientsList4 = new HashMap<String, DataOutputStream>();
		clientsList5 = new HashMap<String, DataOutputStream>();
		clientsList6 = new HashMap<String, DataOutputStream>();
		
		mainMap.put("LeagueOfLegends", (HashMap<String, DataOutputStream>) clientsList1);
		mainMap.put("FifaOnline3", (HashMap<String, DataOutputStream>)clientsList2);
		mainMap.put("MapleStory", (HashMap<String, DataOutputStream>)clientsList3);
		mainMap.put("Overwatch", (HashMap<String, DataOutputStream>)clientsList4);
		mainMap.put("TalesWeaver", (HashMap<String, DataOutputStream>)clientsList5);
		mainMap.put("Starcraft", (HashMap<String, DataOutputStream>)clientsList6);
		
		mainVoteMap = new HashMap<String, Boolean>();
		mainVoteMap.put("LeagueOfLegends",false);
		mainVoteMap.put("FifaOnline3", false);
		mainVoteMap.put("MapleStory", false);
		mainVoteMap.put("Overwatch", false);
		mainVoteMap.put("TalesWeaver", false);
		mainVoteMap.put("Starcraft", false);
		
		mainVoteCount = new HashMap<String, Integer>();
		mainVoteCount.put("LeagueOfLegends", 0);
		mainVoteCount.put("FifaOnline3", 0);
		mainVoteCount.put("MapleStory", 0);
		mainVoteCount.put("Overwatch", 0);
		mainVoteCount.put("TalesWeaver", 0);
		mainVoteCount.put("Starcraft", 0);
		
		mainVoteDisagree = new HashMap<String, Integer>();
		mainVoteDisagree.put("LeagueOfLegends", 0);
		mainVoteDisagree.put("FifaOnline3", 0);
		mainVoteDisagree.put("MapleStory", 0);
		mainVoteDisagree.put("Overwatch", 0);
		mainVoteDisagree.put("TalesWeaver", 0);
		mainVoteDisagree.put("Starcraft", 0);
		
	}
	public final void setGui(ServerGui gui) {
		this.gui = gui;
	}
	public void setting() throws IOException {	
		serverSocket=new ServerSocket(7777);
			while (true) {
				System.out.println("서버 대기중.");
				socket = serverSocket.accept(); 
				System.out.println(socket.getInetAddress() + "접속");
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
}
	public static void main(String[] args) throws IOException {	
		ServerClient server= new ServerClient();
		server.setting();
	}

	public void addClient(String channel,String nick, DataOutputStream out) throws IOException {
		sendMessage((nick + "님께서 접속하셨습니다.\n"),channel);	// 서요한 수정
		gui.sendList(channel);
		user_list.add(nick);
		mainMap.get(channel).put(nick, out);
	}
	public void removeClient(String channel,String nick) {		// 서요한 수정
		sendMessage(nick + "님께서 방을 나가셨습니다.\n",channel);
		mainMap.get(channel).remove(nick);
		gui.removeList(channel);
		String usL;
		usL = "!@#$%"+mainMap.get(channel).keySet().toString()+"!@#$%^\n";
		sendMessage(usL, channel);
	}

	public void sendMessage(String msg,String channel) {
		Iterator<String> it = mainMap.get(channel).keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();
			try {
				 mainMap.get(channel).get(key).writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	public void sendMessage2(String msg,String tmp[]) { //msg = 운영자의 말이고 , tmp[] 는 현재만들어진 채널 
		for(int i=0;i<tmp.length;i++){
			setChannel.add(tmp[i]);
		}
		Iterator<String> itit=setChannel.iterator();
		while(itit.hasNext()){
			String itChannel=itit.next();
			Iterator<String> it = mainMap.get(itChannel).keySet().iterator();
			String key = "";
			while (it.hasNext()) {
				key = it.next();
				try {
					 mainMap.get(itChannel).get(key).writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	class Receiver extends Thread {
		private DataInputStream dis;
		private DataOutputStream dos;
		private String nick;
		private String[] st;

		public Receiver(Socket socket) throws IOException {
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			nick = dis.readUTF();
			st=nick.split(" ");
			if(st[1].contains("#")){
			}else{
				st[1]+="#"+userCnt++;
			}
			addClient(st[0],st[1],dos);
			try {
				sleep(1000);
				String usL;
				usL = "!@#$%"+mainMap.get(st[0]).keySet().toString()+"!@#$%^\n"+"너는@@"+st[1];
				sendMessage(usL, st[0]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public void run() {
			try {
				while (dis != null) {
					msg = dis.readUTF();
					if(msg.startsWith("%%%^")){
						String movingNick = msg.substring(4, msg.indexOf("^%%%"));
						removeClient(st[0], st[1]);
					}
					if(msg.startsWith("&&*추방투표시작")){		// 수정했습니다 0914
						// 추방 건의 받음
						if(mainMap.get(st[0]).size()<=2){
							mainMap.get(st[0]).get(st[1]).writeUTF("채팅방 구성원이 2명 이하일 경우 추방투표가 불가능합니다\n");
							msg="";
						}else if(mainVoteMap.get(st[0])){	// 이미 투표가 진행중일 경우
							mainMap.get(st[0]).get(st[1]).writeUTF("이미 다른 투표가 진행중입니다\n");
							msg="";
						}else{
							mainVoteMap.put(st[0],true);	// 투표시작
							mainVoteCount.put(st[0], 0);	// 찬성 0
							mainVoteDisagree.put(st[0], 0);	// 반대 0
						}
					}
					if(msg.startsWith("추방동의")){
						msg=msg.replace("추방동의", "");		// 아이디만 뽑아내고
						mainVoteCount.put(st[0], mainVoteCount.get(st[0])+1);	// 동의한 사람 숫자 늘리고
						if((mainMap.get(st[0]).size()/2)<mainVoteCount.get(st[0])){	// 절반 이상이 동의하면
							msg=msg.replace("투표시작", "");
							mainMap.get(st[0]).get(msg).writeUTF("%%^^너는 좀 나가야겠다");	// 쫒아내고
//							removeClient(st[0], msg);										// 그 유저 목록에서 지운다
							mainVoteMap.put(st[0], false);	// 투표 진행중이지 않은 것으로
							mainVoteCount.put(st[0], 0);	// 동의자 숫자 0으로
							mainVoteDisagree.put(st[0], 0);	// 반대자 숫자 0으로
						}
						if((mainVoteCount.get(st[0])+mainVoteDisagree.get(st[0])+1) == mainMap.get(st[0]).size()){ // 모두 투표했다면
							mainVoteMap.put(st[0], false);	// 투표 진행중이지 않은 것으로
							mainVoteCount.put(st[0], 0);	// 동의자 숫자 0으로
							mainVoteDisagree.put(st[0], 0);	// 반대자 숫자 0으로
						}
						msg = "";
					}else if(msg.startsWith("추방반대")){	// 반대 메시지를 받으면
						mainVoteDisagree.put(st[0], mainVoteDisagree.get(st[0])+1);	// 반대표 늘리고
						if((mainVoteCount.get(st[0])+mainVoteDisagree.get(st[0])+1) == mainMap.get(st[0]).size()){ // 모두 투표했다면
							mainVoteMap.put(st[0], false);	// 투표 진행중이지 않은 것으로
							mainVoteCount.put(st[0], 0);	// 동의자 숫자 0으로
							mainVoteDisagree.put(st[0], 0);	// 반대자 숫자 0으로
						}
						msg="";
					}
					sendMessage(msg,st[0]);
					gui.appendMsg(msg);
				}
			} catch (IOException e) {
				removeClient(st[0],st[1]);
			}
		}
	}
}
