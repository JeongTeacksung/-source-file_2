package kr.co.sist.login.evt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Procedure를 사용한 CRUD
 * @author Owner
 */
public class LoginDAO {
	
	private static LoginDAO ucs_dao;
	private List<String> idList;
	
	
	private LoginDAO() {
		
	}//LoginDAO
	
	public static LoginDAO getInstance() {
		if(ucs_dao == null) {
			ucs_dao=new LoginDAO();
		}//end if
		return ucs_dao;
	}
	
	private Connection getConn() throws SQLException{
		Connection con=null;
		//1.
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2.
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConn
	
	
	public List<String> loginIdList() throws SQLException {
		idList = new ArrayList<String>();
		StringBuilder selectId = new StringBuilder();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			//2. Connection얻기
				con=getConn();
			//3.쿼리문 생성 객체 얻기
			//PreparedStatement 객체는 실행되는 쿼리문을 알고있다.
			selectId
			.append("select id ")
			.append("from ex_login ");
			
			pstmt=con.prepareStatement(selectId.toString());
			//4.바인드 변수에 값 설정
			//5.쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idList.add(rs.getString("id"));
			}//end while
			
		} finally {
			//6.연결 끊기
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}//end finally
		
		return idList;
	}//loginIdList
	
	public String loginPass(String loginId) throws SQLException {
		StringBuilder loginPass = new StringBuilder();
		String passWard="";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1.드라이버로딩
			//2.Connection얻기
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
			
			con=GetConnection.getInstance().getConn(url, id, pass);
			//3.쿼리문 생성 객체 얻기
			//PreparedStatement 객체는 실행되는 쿼리문을 알고있다.
			loginPass
			.append("select pass ")
			.append("from ex_login ")
			.append("where id='").append(loginId).append("'");
			
			pstmt=con.prepareStatement(loginPass.toString());
			//4.바인드 변수에 값 설정
			//5.쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				passWard = rs.getString("pass");
			}
		} finally {
			//6.연결 끊기
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}//loginPass
		
		return passWard;
	}
	
}//class

