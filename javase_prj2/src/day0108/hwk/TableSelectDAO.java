package day0108.hwk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableSelectDAO {
	
	public TableSelectDAO() {
	
	}
	
	//커넥션 가져오는애
	private Connection getConn() throws SQLException{
		Connection con = null;
		//1.
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id = "scott";
		String pass = "tiger";
		
		con = DriverManager.getConnection(url, id, pass);
		return con;
	}
	
	//콤보박스 목록 가꼬오는애
	public List<String> getTableName() throws SQLException {
		List<String> listTablenames = new ArrayList<String>();
		String tableName = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1.드라이버로딩
			//2.Connection얻기
			con = getConn();
			//3.쿼리문 생성 객체 얻기
			//PreparedStatement 객체는 실행되는 쿼리문을 알고있다.
			String selectTab="select tname from tab";
			pstmt=con.prepareStatement(selectTab);
			//4.바인드 변수에 값 설정
			//5.쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				tableName = rs.getString("tname");
				
				//tv.getJcbTableSelect().addItem(tableName);
				listTablenames.add(tableName);
			}
		} finally {
			//6.연결 끊기
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}
		
		return listTablenames;
	}
	
	//테이블 내용 가꼬오는애
	public List<String[]> getTableColums(String tableName) throws SQLException {
		List<String[]> listTablenames = new ArrayList<String[]>();
		String[] tableColums = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1.드라이버로딩
			//2.Connection얻기
			con = getConn();
			//3.쿼리문 생성 객체 얻기
			//PreparedStatement 객체는 실행되는 쿼리문을 알고있다.		
			StringBuilder selectJoinTab = new StringBuilder();
			selectJoinTab
			.append("select utc.column_name cname, utc.data_type dtype, utc.data_length dlength, ucc.constraint_name conName ")
			.append("from user_tab_cols utc, user_cons_columns ucc ")
			.append("where utc.table_name = ucc.table_name(+) ")
			.append("and utc.column_name = ucc.column_name(+) ")
			.append("and utc.table_name=? ");

			pstmt=con.prepareStatement(selectJoinTab.toString());
			//4.바인드 변수에 값 설정
			pstmt.setString(1, tableName);
			//5.쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				tableColums = new String[4];
				tableColums[0] = rs.getString("cname");
				tableColums[1] = rs.getString("dtype");
				tableColums[2] = rs.getString("dlength");
				tableColums[3] = rs.getString("conName");
				listTablenames.add(tableColums);
			}
		} finally {
			//6.연결 끊기
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
		}
		
		return listTablenames;
	}
	
}
