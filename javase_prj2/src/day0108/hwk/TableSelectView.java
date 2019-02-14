package day0108.hwk;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableSelectView extends JFrame{

	private JComboBox<String> jcbTableSelect;
	private JButton jbtSelect;
	private DefaultTableModel dtmTableContents;
	
	public TableSelectView() {
		super("테이블 선택");
		
		jcbTableSelect = new JComboBox<String>();
		jbtSelect = new JButton("선택");
		
		String[] columnNames = {"컬럼명","데이터형","크기","제약사항"};
		dtmTableContents = new DefaultTableModel(columnNames, 5);
		JTable tabTableContents = new JTable(dtmTableContents);
		
		tabTableContents.getTableHeader().setReorderingAllowed(false);//컬럼 이동 막기
		tabTableContents.setRowHeight(24);
		
		JScrollPane jsp = new JScrollPane(tabTableContents);
		JPanel jplNorth = new JPanel();
		
		jplNorth.add(new JLabel("테이블 선택"));
		jplNorth.add(jcbTableSelect);
		jplNorth.add(jbtSelect);
		
		add("North", jplNorth);
		add("Center", jsp);
		
		TableSelectViewEvt tve = new TableSelectViewEvt(this);
		jcbTableSelect.addActionListener(tve);
		jbtSelect.addActionListener(tve);
		addWindowListener(tve);
		
		setBounds(100, 100, 500, 300);
		setVisible(true);

	}

	public JComboBox<String> getJcbTableSelect() {
		return jcbTableSelect;
	}

	public JButton getJbtSelect() {
		return jbtSelect;
	}

	public static void main(String[] args) {
		new TableSelectView();
	}

	public DefaultTableModel getDtmTableContents() {
		return dtmTableContents;
	}

}
