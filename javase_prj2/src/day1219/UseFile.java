package day1219;

import java.io.File;
import java.io.IOException;

/**
 * File클래스
 * - 파일의 정보얻기
 * - 디렉토리 생성
 * - 파일 삭제
 * @author Owner
 */
public class UseFile {

	public UseFile() {
		String path="c:/dev/temp/java_read.txt";
		//1. 생성
		File file=new File(path);
		System.out.println(file);
		if(file.exists()) { // 파일이 존재할 때
			System.out.println("절대 경로 : "+file.getAbsolutePath());
			try {
				System.out.println("규범 경로 : "+file.getCanonicalPath());
			} catch (IOException ie) {
				ie.printStackTrace();
			}//end catch
			System.out.println("경로 : "+file.getPath());
			System.out.println("폴더명 : "+file.getParent()); //c:/dev/temp
			System.out.println("파일명 : "+file.getName()); //java_read.txt
			
			System.out.println(file.isFile()? "파일":"디렉토리");
			System.out.println(file.isDirectory()? "디렉토리":"파일");
			
			System.out.println("파일 길이 ; "+file.length());
			System.out.println("실행"+(file.canExecute()?"가능":"불가능"));
			System.out.println("읽기"+(file.canRead()?"가능":"불가능"));
			System.out.println("쓰기"+(file.canWrite()?"가능":"불가능"));
			System.out.println(file.isHidden()?"숨김파일":"일반파일");
//			Date d=new Data(file.)
//			(file.lastModified());
//			SimpleDateFormat sdf
//				new SimpleDatefamy)                                               
			System.out.println("마지막 수정일 : "+file.lastModified())
			;
			
			
		}else {
			System.out.println("경로를 확인해주세요.");
		}//end if
		
	}//UseFile
	
	public static void main(String[] args) {
		new UseFile();
	}//main

}//class
