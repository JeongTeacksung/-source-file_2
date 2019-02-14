package day0109.hwk;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class QueryView extends JFrame {
	private JLabel lblTable, lblColumn, lblDataType, lblData, lblConstraint, lblConstraintName;
//	private JLabel lblColumn;
//	private JLabel lblDataType;
//	private JLabel lblData;
//	private JLabel lblConstraint;
//	private JLabel lblConstraintName;
	
	private JTextField jtfTable, jtfColumn, jtfData, jtfConstraintName;
//	private JTextField jtfColumn;
	private JComboBox<String> jcbDataType, jcbConstraint;
//	private JTextField jtfData;
//	private JComboBox<String> jcbConstraint;
//	private JTextField jtfConstraintName;
	
	private JButton jbtTableName, jbtValue, jbtCreateTable, jbtRemove;
//	private JButton jbtValue;
//	private JButton jbtCreateTable;
//	private JButton jbtRemove;
	
	private JTextArea jtaQueryView;
	private JScrollPane jspQueryView;
	
	private StringBuilder queryViewText;
	
	public QueryView() {
		lblTable=new JLabel("테이블명");
		lblColumn=new JLabel("컬럼명");
		lblDataType=new JLabel("데이터형");
		lblData=new JLabel("크기");
		lblConstraint=new JLabel("제약사항");
		lblConstraintName=new JLabel("제약사항명");
		
		jtfTable=new JTextField(10);
		jtfColumn=new JTextField(10);
		jcbDataType=new JComboBox<String>();
		jtfData=new JTextField(10);
		jcbConstraint=new JComboBox<String>();
		jtfConstraintName=new JTextField(10);
		jbtTableName=new JButton("추가");
		jbtValue=new JButton("추가");
		jbtCreateTable=new JButton("테이블 생성");
		jbtRemove=new JButton("초기화");
		
		jtaQueryView = new JTextArea(16,40);
		jspQueryView = new JScrollPane(jtaQueryView);
		
//		jcbDataType.addItem("varchar2");
//		jcbDataType.addItem("char");
//		jcbDataType.addItem("number");
//		jcbDataType.addItem("date");
//		
//		jcbConstraint.addItem("null");
//		jcbConstraint.addItem("primary key");
//		jcbConstraint.addItem("unique");
//		jcbConstraint.addItem("not null");
		
		jtaQueryView.setEditable(false);
		
		Panel panel1=new Panel();
		panel1.add(lblTable);
		panel1.add(jtfTable);
		panel1.add(jbtTableName);
		
		Panel panel2=new Panel();
		panel2.add(lblColumn);
		panel2.add(jtfColumn);
		panel2.add(lblDataType);
		panel2.add(jcbDataType);
		panel2.add(lblData);
		panel2.add(jtfData);
		
		Panel panel3=new Panel();
		panel3.add(lblConstraint);
		panel3.add(jcbConstraint);
		panel3.add(lblConstraintName);
		panel3.add(jtfConstraintName);
		panel3.add(jbtValue);
		
		Panel North=new Panel();
		North.add(panel1);
		North.add(panel2);
		North.add(panel3);
		
		North.setLayout(new GridLayout(3,3));
		
		Panel Center=new Panel();
		Center.add(jspQueryView);
		
		Panel South=new Panel();
		South.add(jbtCreateTable);
		South.add(jbtRemove);
		
		add("North", North);
		add("Center", Center);
		add("South", South);
		
		QueryViewEvt qve=new QueryViewEvt(this);
		jbtTableName.addActionListener(qve);
		jbtValue.addActionListener(qve);
		jbtCreateTable.addActionListener(qve);
		jbtRemove.addActionListener(qve);
		addWindowListener(qve);
		queryViewText=qve.getQueryViewText();
		
		setBounds(200, 200, 600, 500);
		setVisible(true);
	}//QueryView
	
	
	public JLabel getLblTable() {
		return lblTable;
	}


	public JLabel getLblColumn() {
		return lblColumn;
	}


	public JLabel getLblDataType() {
		return lblDataType;
	}


	public JLabel getLblData() {
		return lblData;
	}


	public JLabel getLblConstraint() {
		return lblConstraint;
	}


	public JLabel getLblConstraintName() {
		return lblConstraintName;
	}


	public JTextField getJtfTable() {
		return jtfTable;
	}


	public JTextField getJtfColumn() {
		return jtfColumn;
	}


	public JComboBox<String> getJcbDataType() {
		return jcbDataType;
	}


	public JTextField getJtfData() {
		return jtfData;
	}


	public JComboBox<String> getJcbConstraint() {
		return jcbConstraint;
	}


	public JTextField getJtfConstraintName() {
		return jtfConstraintName;
	}


	public JButton getJbtTableName() {
		return jbtTableName;
	}


	public JButton getJbtValue() {
		return jbtValue;
	}


	public JButton getJbtCreateTable() {
		return jbtCreateTable;
	}


	public JButton getJbtRemove() {
		return jbtRemove;
	}


	public JTextArea getJtaQueryView() {
		return jtaQueryView;
	}


	public JScrollPane getJspQueryView() {
		return jspQueryView;
	}

	public StringBuilder getQueryViewText() {
		return queryViewText;
	}

	public static void main(String[] args) {
		new QueryView();
	}
	
}//class
