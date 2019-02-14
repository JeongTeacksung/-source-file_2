package day1219;

import java.io.File;

/**
 * 폴더생성, 파일삭제
 * @author Owner
 */
public class UseFile2 {
	public UseFile2() {
	}//UseFl
	
	public void createDirectory() {
		File file=new File("c:/teacksung/jeong");
		if(file.mkdir()) {
			System.out.println("폴더생성 성공");
		}else {
			System.out.println("같은 이름의 폴더 존재");
		}//end if
		
		
	}//createDirectory
	public void removeFile() {
		File file=new File("c:/dev/temp/a.txt");
		boolean flag=file.delete();
		if(flag) {
			System.out.println("파일 삭제 성공");
		}else {
			System.out.println("파일 삭제 실패");
			
		}//end else
	}//removeFile
	
	public static void main(String[] args) {
		UseFile2 uf2=new UseFile2();
		uf2.createDirectory();
		System.out.println("-----------------------");
		uf2.removeFile();
	}//main

}//class
