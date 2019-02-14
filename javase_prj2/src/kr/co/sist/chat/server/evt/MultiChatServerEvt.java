package kr.co.sist.chat.server.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import kr.co.sist.chat.server.helper.MultiChatServerHelper;
import kr.co.sist.chat.server.view.MultiChatServerView;


public class MultiChatServerEvt extends	WindowAdapter implements ActionListener, Runnable {

	private MultiChatServerView mcsv;
	private Thread threadServer; // 접속자에 대한 처리를 하기 위한 Thread
	private ServerSocket server; // PORT열기
	private List<MultiChatServerHelper> listClient; // 모든 접속자를 관리할 List
	
	public MultiChatServerEvt(MultiChatServerView mcsv) {
		this.mcsv = mcsv;
		listClient=new ArrayList<MultiChatServerHelper>();
	}//MultiChatServerEvt
	
	@Override
	public void run() {
		//서버소켓을 열고 접속자 소켓을 받는다.
		try {
			server = new ServerSocket(35000); // 0~65535개의 PORT가 열릴 수 있다.
			DefaultListModel<String> dlmTemp=mcsv.getDlmChatList();
			dlmTemp.addElement("서버가 35000 PORT를 열고 가동 중...");
			
			Socket someClient=null; //접속자 소켓을 저장할 객체
			InetAddress ia=null; //접속자의 ip address를 얻기위한 객체
			
			MultiChatServerHelper mcsh=null; //접속자 소켓을 받아 스트림을 연결하고,
			//대화를 읽거나 모든 접속자에게 전송하는 일.
			for(int cnt=0; ; cnt++) {
				someClient=server.accept();
				ia=someClient.getInetAddress();
//				dlmTemp.addElement("["+ia+"] 접속자 접속");
				
				//소켓, 서버의 화면, 접속순서를 할당하여 Helper class 생성
				mcsh=new MultiChatServerHelper(someClient, dlmTemp, cnt, mcsv, listClient, mcsv.getJspList());
				//생성된 같은 이름의 소켓객체를 여러개 관리하기 위해 List에 추가
				listClient.add(mcsh);
				//접속자의 메세지를 읽어들이기위한 Thread 시작
				mcsh.start();
				
				
				
			}//end for
			
		} catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
		
		
		
	}//run

	@Override
	public void windowClosing(WindowEvent we) {
		mcsv.dispose();
	}//windowClosing
	
	@Override
	public void windowClosed(WindowEvent we) {			
		try {
			if(server != null) {	server.close();}//end if
		}catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
	}//windowClosed
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mcsv.getJbtOpenServer()) {
			if(threadServer == null) {
				threadServer=new Thread(this); 
				threadServer.start(); // start() -> run()
			}else {
				JOptionPane.showMessageDialog(mcsv, "채팅서버가 이미 가동중입니다.");
			}//end else
		}//end if
		
		if(ae.getSource()==mcsv.getJbtCloseServer()) {
			switch( JOptionPane.showConfirmDialog(mcsv,
					"채팅서버를 종료 하시겠습니까?\n종료하시면 모든 접속자의 연결이 끊어집니다.")) {
			case JOptionPane.OK_OPTION : mcsv.dispose();
			}//end switch
		}//end if
		
	}//actionPerformed

}//class
