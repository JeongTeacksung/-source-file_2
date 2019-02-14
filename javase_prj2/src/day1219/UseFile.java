package day1219;

import java.io.File;
import java.io.IOException;

/**
 * FileŬ����
 * - ������ �������
 * - ���丮 ����
 * - ���� ����
 * @author Owner
 */
public class UseFile {

	public UseFile() {
		String path="c:/dev/temp/java_read.txt";
		//1. ����
		File file=new File(path);
		System.out.println(file);
		if(file.exists()) { // ������ ������ ��
			System.out.println("���� ��� : "+file.getAbsolutePath());
			try {
				System.out.println("�Թ� ��� : "+file.getCanonicalPath());
			} catch (IOException ie) {
				ie.printStackTrace();
			}//end catch
			System.out.println("��� : "+file.getPath());
			System.out.println("������ : "+file.getParent()); //c:/dev/temp
			System.out.println("���ϸ� : "+file.getName()); //java_read.txt
			
			System.out.println(file.isFile()? "����":"���丮");
			System.out.println(file.isDirectory()? "���丮":"����");
			
			System.out.println("���� ���� ; "+file.length());
			System.out.println("����"+(file.canExecute()?"����":"�Ұ���"));
			System.out.println("�б�"+(file.canRead()?"����":"�Ұ���"));
			System.out.println("����"+(file.canWrite()?"����":"�Ұ���"));
			System.out.println(file.isHidden()?"��������":"�Ϲ�����");
//			Date d=new Data(file.)
//			(file.lastModified());
//			SimpleDateFormat sdf
//				new SimpleDatefamy)                                               
			System.out.println("������ ������ : "+file.lastModified())
			;
			
			
		}else {
			System.out.println("��θ� Ȯ�����ּ���.");
		}//end if
		
	}//UseFile
	
	public static void main(String[] args) {
		new UseFile();
	}//main

}//class
