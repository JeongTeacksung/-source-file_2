package day0107;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.sist.connection.GetConnection;

public class SelectTableEvt extends WindowAdapter implements ActionListener {

	private SelectTable st;
	
	public SelectTableEvt(SelectTable st) {
		this.st=st;
	}//SelectTableEvt

	public void selectTable(String id, String pass)throws SQLException{
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
		//2. Connection얻기
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			con=DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기
			stmt=con.createStatement();
		//4. 쿼리 수행 후 결과 얻기
			StringBuilder selectTableName=new StringBuilder();
			selectTableName
			.append("select table_name ")
			.append("from tabs ");
			rs=stmt.executeQuery(selectTableName.toString());
			
			while(rs.next()) {//조회된 레코드가 존재한다면
				st.getJcbTable().addItem(rs.getString("TABLE_NAME"));
			}//end while
			
		}finally {
		//5. 연결 끊기
			if( rs != null ) { rs.close(); }//end if
			if( stmt != null ) { stmt.close(); }//end if
			if( con != null ) { con.close(); }//end if
		}//end finally
		
	}//selectTable
	
	public void showTable(String table) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		ResultSetMetaData rsmd = null;

		try {
			// 1.
			// 2.
			GetConnection gc = GetConnection.getInstance();
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = gc.getConn(url, id, pass);
			// 3.
			StringBuilder selectEmp=new StringBuilder();
			selectEmp
			.append("select utc.column_name cname, utc.data_type dtype, utc.data_length dlength, nvl(ucc.constraint_name, ' ') conName ")
			.append("from user_tab_cols utc, user_cons_columns ucc ")
			.append("where utc.table_name = ucc.table_name(+) ")
			.append("and utc.column_name = ucc.column_name(+) ")
			.append("and utc.table_name=? ");
			
			pstmt = con.prepareStatement(selectEmp.toString());
			// 4.
			pstmt.setString(1, table);
			// 5.
			rs = pstmt.executeQuery();
			
			StringBuilder output=new StringBuilder();
			output
			.append("컬럼명 ").append("\t")
			.append("데이터형").append("\t")
			.append("데이터형 크기").append("\t")
			.append("제약사항").append("\n");
			while(rs.next()) {
				output
				.append(rs.getString("cname")).append("\t")
				.append(rs.getString("dtype")).append("\t")
				.append(rs.getString("dlength")).append("\t")
				.append(rs.getString("conName")).append("\n");
			}
			st.getJtaTable().setText(output.toString());
		} finally {
			// 6.
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally

	}// selectTable
	
	@Override
	public void windowClosing(WindowEvent we) {
		st.dispose();
	}//windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == st.getJbtSelect()) {
			try {
				showTable(st.getJcbTable().getSelectedItem().toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(st.getJcbTable().getSelectedItem());
	}//actionPerformed

}//class
