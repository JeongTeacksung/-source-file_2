package practice;

import java.awt.FileDialog;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class Client extends JFrame implements ActionListener{

	private DefaultListModel<String> dlm;
	private JButton jbt;
	
	public Client() {
		dlm=new DefaultListModel<String>();
		JList<String> list=new JList<String>(dlm);
		JScrollPane jsp=new JScrollPane(list);
		jbt=new JButton("파일선택");
		
		jsp.setBorder(new TitledBorder("전송한 파일"));		
		Panel panel=new Panel();
		panel.add(jbt);
		
		add("Center",jsp);
		add("South", panel);
		
		jbt.addActionListener(this);
		
		setBounds(100, 100, 300, 250);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//Client
	
	public void selectFile() {
		FileDialog fd=new FileDialog(this, "파일선택", FileDialog.LOAD);
		fd.setVisible(true);
		String filePath=fd.getDirectory();
		String fileName=fd.getFile();
		
		if(fileName != null) {
			boolean flag=false;
			String ext=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			String[] arrCompare= {"jpg","jpeg","gif","png","bmp"};
			
			for(String temp : arrCompare) {
				if(temp.equals(ext)) {
					flag=true;
				}//end if
			}//end for
			
			if(!flag) {
				JOptionPane.showMessageDialog(this, "사진파일이아니야");
				return;
			}
			try {
				sendFile(new File(filePath+"/"+fileName));
				JOptionPane.showMessageDialog(this, "전송함");
				dlm.addElement(fileName);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendFile(File file) throws UnknownHostException, IOException {
		Socket clientSocket=null;
		DataOutputStream dos=null;
		
		try {
			String ip=JOptionPane.showInputDialog("ip입력");
			clientSocket=new Socket("211.63.89."+ip, 10000);
			String fileName=file.getName();
			dos=new DataOutputStream(clientSocket.getOutputStream());
			dos.writeUTF(fileName);
			dos.flush();
			
		}finally {
			if(dos != null) {dos.close();}
			if(clientSocket != null) {clientSocket.close();}
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		selectFile();
		
		
	}//actionPerformed

	public static void main(String[] args) {
		new Client();
	}
}//class
