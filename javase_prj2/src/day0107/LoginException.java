package day0107;

/**
 * ����� ���� ����ó�� Ŭ����
 * Exception(Runtime Exception) ��ӹ޴´�.
 * @author Owner
 */
@SuppressWarnings("serial")
public class LoginException extends Exception {
	public LoginException() {
		this("�α��� ����");
	}//LoginException
	
	public LoginException(String msg) {
		super( msg );// ����ó�� ��ü�� ����Ͽ� ���ܸ޼����� ����� �� �ִ�.
		
	}//LoginException
	
	
}//class
