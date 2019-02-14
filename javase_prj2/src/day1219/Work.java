package day1219;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Work {
	private String str;
	private File file;
	
	public Work() {
		System.out.println("���丮�� �Է� : ");
		
		BufferedReader br=new BufferedReader(
				new InputStreamReader(System.in));
		
		try {
			str = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		file=new File(str);
		if(file.exists()) {
			fileOut();
		}else {
			System.err.println("������ ������� �ʽ��ϴ�.");
		}//end if
		
		
	}//Work

	public void fileOut() {
		StringBuilder viewFile=new StringBuilder();
		viewFile.append("�̸�\t\t\t����\t\t\tũ��\t\t\t���������� �����ѳ�¥\n");
		
		File[] arrayFile=this.file.listFiles();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd");
		
		for(int i=0; i<arrayFile.length; i++) {
			if(arrayFile[i].isFile()) {
				String fi="����";
				viewFile.append(arrayFile[i].getName()).append("\t\t\t")
						.append(fi).append("\t\t\t").append(arrayFile[i].length()).append("byte")
						.append("\t\t\t").append(sdf.format(arrayFile[i].lastModified())).append("\n");
			}else if(arrayFile[i].isDirectory()) {
				String fi="����";
				viewFile.append(arrayFile[i].getName()).append("\t\t\t").append(fi)
						.append("\t\t\t").append(arrayFile[i].length()).append("byte")
						.append("\t\t\t").append(sdf.format(arrayFile[i].lastModified())).append("\n");
			}//end else
			
		}//end for
		
		JTextArea jta=new JTextArea(20,100);
		jta.append(viewFile.toString());
		JScrollPane jsp=new JScrollPane(jta);
		JOptionPane.showMessageDialog(null, jsp);
		
		
	}//fileOut
	
	
	
	public static void main(String[] args) {
		new Work();
	}//main
}//class
