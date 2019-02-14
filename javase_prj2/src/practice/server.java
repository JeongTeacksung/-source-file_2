package practice;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class server extends JFrame implements ActionListener, Runnable {

	private DefaultListModel<String> dlm;
	private JButton jbt;
	private Thread serverThread;
	private	ServerSocket serverSocket;
	
	
	public server() {
		super("파일서버");
		dlm=new DefaultListModel<String>();
		JList<String> list= new JList<>(dlm);
		JScrollPane jsp=new JScrollPane(list);
		
		jsp.setBorder(new TitledBorder("파일목록"));
		
		jbt=new JButton("서버가동");
		
		Panel panel=new Panel();
		panel.add(jbt);
		
		add("South",panel);
		add("Center", jsp);
		
		jbt.addActionListener(this);
		
		setBounds(100, 100, 400, 600);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//server
	
	@Override
	public void run() {
		
		try {
			Socket clientSocket=null;
			StringBuilder fileName=new StringBuilder();
			DataInputStream dis=null;
			try {
				while(true) {
					clientSocket=serverSocket.accept();
					
					dis=new DataInputStream(clientSocket.getInputStream());
					
					fileName.delete(0, fileName.length());
					fileName.append(dis.readUTF());
					
					fileName.insert(fileName.lastIndexOf("."),
							"_".concat(String.valueOf(System.currentTimeMillis())));
					
					dlm.addElement(fileName.toString()+" "+clientSocket.getInetAddress());
					
				}
			}finally {
				if(dis != null) {dis.close();}
				if(clientSocket != null) {clientSocket.close();}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		
		
		
	}//run
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(serverThread==null) {
			try {
				serverSocket = new ServerSocket(10000);
				dlm.addElement("10000번 포트가 실행중입니다.");
				serverThread=new Thread(this);
				serverThread.start();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "10000번 포트가 이미 실행중입니다.");
				e.printStackTrace();
			}//end catch
		}else {
			JOptionPane.showMessageDialog(this, "서버가 이미 동작중입니다.");
		}
		
		
		
	}//actionPerformed

	public static void main(String[] args) {
		new server();
		
	}//main


}//class
