package day0108.hwk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import day0107.ZipcodeVO;

public class TableSelectViewEvt extends WindowAdapter implements ActionListener{
	private TableSelectView tv;
	private String selectTName;
	private TableSelectDAO tsdao;
	
	public TableSelectViewEvt(TableSelectView tv) {
		this.tv = tv;
		List<String> tableNames = new ArrayList<String>();
		tsdao = new TableSelectDAO();
		
		try {
			tableNames = tsdao.getTableName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<tableNames.size(); i++) {
			tv.getJcbTableSelect().addItem(tableNames.get(i));
		}
		
		selectTName = tv.getJcbTableSelect().getItemAt(0);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == tv.getJcbTableSelect()) {
			selectTName = selectTableName();
		}
		if(ae.getSource() == tv.getJbtSelect()) {
			// DAO에 매개변수로 테이블명 넣어줄 것 
			applyTableContents();
		}	
	}
	
	//선택시 테이블 이름 저장하는 애
	public String selectTableName() {
		String selectTName = "";
		selectTName = tv.getJcbTableSelect().getSelectedItem().toString();

		return selectTName;
	}
	
	//선택한 테이블 이름 적용하는 애
	public void applyTableContents() {
		try {
			//DB에서 조회한 결과를 받아 
			List<String[]> listTableColums = tsdao.getTableColums(selectTName);
			//defaultTableModel에 추가 => Jtable 반영
			DefaultTableModel dtm = tv.getDtmTableContents();
			//D.T.M 초기화
			dtm.setRowCount(0);
			
			String[] rowData = null;
			//조회된 결과를 가지고
			for(int i=0; i<listTableColums.size(); i++) {
				rowData = new String[4];
				rowData[0] = listTableColums.get(i)[0];
				rowData[1] = listTableColums.get(i)[1];
				rowData[2] = listTableColums.get(i)[2];
				rowData[3] = listTableColums.get(i)[3];
				
				//D.T.M에 행(Row : 우편번호, 주소) 추가
				dtm.addRow(rowData);
			}
			
			if(listTableColums.isEmpty()) {
				rowData=new String[4];
				rowData[0]="해당 테이블에";
				rowData[1]="값이";
				rowData[2]="존재하지";
				rowData[3]="않습니다.";
				
				dtm.addRow(rowData);
			}
			
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(tv, "DB에서 문제가 발생");
			se.printStackTrace();
		}	
	}
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		tv.dispose();
	}
}

