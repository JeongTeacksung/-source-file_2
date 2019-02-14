package day0109.hwk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

public class CreateTable {

	private QueryView qv;

	public CreateTable(QueryView qv) throws SQLException {
		this.qv = qv;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1.
			// 2.
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			con = GetConnection.getInstance().getConn(url, id, pass);
			String tableName = qv.getJtfTable().getText();

			// 3. //���̺��� �����ϴ���?
			StringBuilder selectTname = new StringBuilder();
			selectTname.append("select tname from tab where tname=?");
			// 4.
			pstmt = con.prepareStatement(selectTname.toString());
			pstmt.setString(1, tableName.toUpperCase());
			// 5.
			rs = pstmt.executeQuery();
			boolean flag = false;
			if (rs.next()) {
				int key = JOptionPane.showConfirmDialog(null, tableName + "���̺��� �����մϴ�. ������ �� �����Ͻðڽ��ϱ�?");
				switch (key) {
				case 0:
					flag = true;
					break;

				default:
					return;
				}//end switch
			} // end if

			pstmt.close();
			if (flag) {// ���� �� �� ���̺� ����
				// 3.
				StringBuilder dropTable = new StringBuilder();
				dropTable.append("drop table ").append(tableName);
				pstmt = con.prepareStatement(dropTable.toString());
				// 4.
				pstmt.execute();
				pstmt.close();
				
				// 3.
				pstmt = con.prepareStatement(qv.getQueryViewText().toString() + ")");
				// 4.
				// 5.
				pstmt.execute();
				JOptionPane.showMessageDialog(null, "���̺��� �����Ǿ����ϴ�.");
				pstmt.close();
				
			}//end if
			
			if(!flag) {
				// 3.
				pstmt = con.prepareStatement(qv.getQueryViewText().toString() + ")");
				// 4.
				// 5.
				pstmt.execute();
				JOptionPane.showMessageDialog(null, "���̺��� �����Ǿ����ϴ�.");
			}//end if
		} finally {
			// 6.
			if (rs != null) {	rs.close(); } // end if
			if (pstmt != null) { pstmt.close(); } // end if
			if (con != null) { con.close(); } // end if
		} // end finally
	}// CreateTable
}// class
