package day0109.hwk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class QueryViewEvt extends WindowAdapter implements ActionListener {

	private QueryView qv;
	private StringBuilder queryViewText;
	String data;
	String column;

	public QueryViewEvt(QueryView qv) {
		this.qv = qv;
		queryViewText = new StringBuilder();
		qv.getJcbDataType().addItem("varchar2");
		qv.getJcbDataType().addItem("char");
		qv.getJcbDataType().addItem("number");
		qv.getJcbDataType().addItem("date");
		
		qv.getJcbConstraint().addItem("null");
		qv.getJcbConstraint().addItem("primary key");
		qv.getJcbConstraint().addItem("foreign key");
		qv.getJcbConstraint().addItem("unique");
		qv.getJcbConstraint().addItem("not null");
	}// QueryViewEvt
	
	@Override
	public void windowClosing(WindowEvent we) {
		qv.dispose();
	}
	
	public void insertTable() {
		if (qv.getJtaQueryView().getText().isEmpty()) {
			queryViewText.append("create table ")
			.append(qv.getJtfTable().getText()).append("(");
			
			qv.getJtaQueryView().setText(queryViewText.toString() + ");\n");
			qv.getJtaQueryView().setFont(new Font("SansSerif", Font.BOLD, 15));
		} // end if
	}// insertTable

	public void insertValue() {
		String foreignKey="";
		String[] tempKey=null;
		
		if(qv.getJcbConstraint().getSelectedItem().equals("foreign key")) {
			foreignKey=JOptionPane.showInputDialog("부모테이블명과 컬럼명을 입력하세요.\n 부모테이블명, 컬럼명");
			tempKey=foreignKey.split(",");
			value();
			queryViewText
			.append("\n")
			.append( " references ").append(tempKey[0]).append("(").append(tempKey[1]).append(")");
			qv.getJtaQueryView().setText(queryViewText.toString() + ");");
			qv.getJtaQueryView().setFont(new Font("SansSerif", Font.BOLD, 15));
		}else {
		if(qv.getJcbConstraint().getSelectedItem().equals("primary key")) {
			if(qv.getJtaQueryView().getText().contains("primary key")) {
				JOptionPane.showMessageDialog(null, "primary key를 중복으로 사용할 수 없습니다!");
			}else{
				value();
			}//end else
		}else {
			value();
		}//end else
		qv.getJtaQueryView().setText(queryViewText.toString() + ");");
		qv.getJtaQueryView().setFont(new Font("SansSerif", Font.BOLD, 15));
		}
	}// insertValue
	
	public void value() {
		String constraintKey="";
		String constraintName="";
		String constraint="";
		if(qv.getJcbConstraint().getSelectedItem()=="foreign key") {
			constraintKey="";
			constraintName=" "+qv.getJtfConstraintName().getText();
		}else if(qv.getJcbConstraint().getSelectedItem()=="null"){
			constraintKey="";
			constraintName=" ";
		}else {
			constraintKey=qv.getJcbConstraint().getSelectedItem().toString();
			constraintName=" "+qv.getJtfConstraintName().getText();
		}//end else
		if (qv.getJcbDataType().getSelectedItem().toString().contains("date")) {
			data="";
		} else {
			if (qv.getJtfData().getText().isEmpty()) {
				data = "";
			} else {
				try {
					data = "("+Integer.parseInt(qv.getJtfData().getText())+")";
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "데이터크기는 숫자이어야 합니다.");
					return;
				} // end catch
			} // end else
		} // end else
		
		if(qv.getJcbConstraint().getSelectedItem().equals("null")&&qv.getJtfConstraintName().getText().isEmpty()) {
			constraint="";
		}else if(qv.getJtfConstraintName().getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "제약사항명이 입력되지 않았습니다^^");
			return;
		}else {
			constraint="constraint";
		}//end else
		
		if (qv.getQueryViewText().toString().contains(qv.getJtfColumn().getText())) {
			JOptionPane.showMessageDialog(null, "컬럼명이 중복되거나 입력되지않았습니다.");
			return;
		} else {
				
			if(!qv.getJtfConstraintName().getText().isEmpty()&&
				qv.getQueryViewText().toString().contains(qv.getJtfConstraintName().getText())) {
					JOptionPane.showMessageDialog(null, "제약사항명이 중복 입력되었습니다.");
					return;
			}else {
				column = qv.getJtfColumn().getText();
				if (qv.getJtaQueryView().getText().length() > 30) {
					qv.getJtaQueryView().setText("");
					queryViewText.append(",\n").append("  ").append(column).append(" ")
							.append(qv.getJcbDataType().getSelectedItem()).append(data).append(" ")
							.append(constraint).append(constraintName).append(" ")
							.append(constraintKey).append(" ");
				} else {
					qv.getJtaQueryView().setText("");
					queryViewText.append("\n").append("  ").append(column).append(" ")
							.append(qv.getJcbDataType().getSelectedItem()).append(data).append(" ")
							.append(constraint).append(constraintName).append(" ")
							.append(constraintKey).append(" ");
				}//end else
//				qv.getJtaQueryView().setText(queryViewText.toString() + ");");
//				qv.getJtaQueryView().setFont(new Font("SansSerif", Font.BOLD, 15));
			}//end else
		} // end else
	}

	public void createTable() {
		try {
			new CreateTable(qv);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "제약사항을 다시 확인해주세요.");
			e.printStackTrace();
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "쿼리문이 비어있습니다.");
		} // end catch
	}// createTable

	public void remove() {
		qv.getJtaQueryView().setText("");
		queryViewText.delete(0, queryViewText.length());
	}// remove

	@Override
	public void actionPerformed(ActionEvent ae) throws NumberFormatException {

		if (ae.getSource() == qv.getJbtTableName()) {
			insertTable();
		} // end if

		if (ae.getSource() == qv.getJbtValue()) {
			insertValue();
		} // end if

		if (ae.getSource() == qv.getJbtCreateTable()) {
			createTable();
		} // end if

		if (ae.getSource() == qv.getJbtRemove()) {
			remove();
		} // end if

	}// actionPerformed

	public StringBuilder getQueryViewText() {
		return queryViewText;
	}

}// class
