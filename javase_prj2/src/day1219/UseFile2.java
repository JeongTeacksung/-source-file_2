package day1219;

import java.io.File;

/**
 * ��������, ���ϻ���
 * @author Owner
 */
public class UseFile2 {
	public UseFile2() {
	}//UseFl
	
	public void createDirectory() {
		File file=new File("c:/teacksung/jeong");
		if(file.mkdir()) {
			System.out.println("�������� ����");
		}else {
			System.out.println("���� �̸��� ���� ����");
		}//end if
		
		
	}//createDirectory
	public void removeFile() {
		File file=new File("c:/dev/temp/a.txt");
		boolean flag=file.delete();
		if(flag) {
			System.out.println("���� ���� ����");
		}else {
			System.out.println("���� ���� ����");
			
		}//end else
	}//removeFile
	
	public static void main(String[] args) {
		UseFile2 uf2=new UseFile2();
		uf2.createDirectory();
		System.out.println("-----------------------");
		uf2.removeFile();
	}//main

}//class
