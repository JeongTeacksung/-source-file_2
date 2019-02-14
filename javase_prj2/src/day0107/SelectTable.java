package day0107;

import java.awt.Panel;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SelectTable extends JFrame {
	private JLabel jlb;
	private JComboBox<String> jcbTable;
	private JButton jbtSelect;
	private JTextArea	jtaTable;
	private JScrollPane jsp;
	
	public SelectTable() {
		super("�����Ѱ����� ��� Table��");
		
		jlb = new JLabel("���̺� ����");
		jcbTable = new JComboBox<String>();
		jbtSelect = new JButton("����");
		jtaTable = new JTextArea(20,30);
		jsp = new JScrollPane(jtaTable);
		
		Panel panel=new Panel();
		panel.add(jlb);
		panel.add(jcbTable);
		panel.add(jbtSelect);
		
		add("North",panel);
		add("Center",jsp);
		
		SelectTableEvt ste=new SelectTableEvt(this);
		try {
			ste.selectTable("scott", "tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		addWindowListener(ste);
		jbtSelect.addActionListener(ste);
		
		setBounds(200, 200, 400, 300);
		setVisible(true);
		setResizable(false);
		
	}//SelectTable
	public static void main(String[] args) {
		new SelectTable();
	}
	public JLabel getJlb() {
		return jlb;
	}
	public JComboBox<String> getJcbTable() {
		return jcbTable;
	}
	public JButton getJbtSelect() {
		return jbtSelect;
	}
	public JTextArea getJtaTable() {
		return jtaTable;
	}
	public JScrollPane getJsp() {
		return jsp;
	}
	
}//class
