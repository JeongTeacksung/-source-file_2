package day0111;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class UseJTableImage extends JFrame {
	private JTable jt;
	private ImageIcon ii1;
	private ImageIcon ii2;
	private ImageIcon ii3;
	private DefaultTableModel dtm;
	private UseJTableImage ujI;
	
	public UseJTableImage() {
		super("JTable");
		ujI=this;
		
		ii1=new ImageIcon("C://dev//workspace//javase_prj2//src//day0111//images//naver.png");
		ii2=new ImageIcon("C://dev//workspace//javase_prj2//src//day0111//images//daum.png");
		ii3=new ImageIcon("C://dev//workspace//javase_prj2//src//day0111//images//google.png");
		
		String[] columnNames= {"�ΰ�","URL","Ư¡"};
		Object[][] object= { 
					{ii1, "http://daum.net", "īī��������" },
					{ii2, "http://naver.com", "����" },
					{ii3, "https://google.com", "�˻������� ���ƿ�" }
					};
		
		dtm=new DefaultTableModel(object,columnNames) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}//end isCellEditable
		};
		
		//�Էµ� Ŭ������ �״�� Cell(columns)�� ǥ���ǵ��� method Override
		//getColumnClass(int column)
		
		jt=new JTable(dtm) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column){
				//row-JTable�� �Էµ� ������ �迭�� �࿡ ���Ѵٸ�
				//��� �÷��� ���� �Էµ� ������ ��ȯ�Ѵ�.
				return getValueAt(0, column).getClass();
			}
		};
		jt.getTableHeader().setReorderingAllowed(false);//�÷��� �̵�����
		jt.getTableHeader().setResizingAllowed(false);//�÷��� ũ�� ���� ����
		
		jt.getColumnModel().getColumn(1).setPreferredWidth(140);
		jt.getColumnModel().getColumn(2).setPreferredWidth(180);
		jt.setRowHeight(70);
		
		JScrollPane jsp=new JScrollPane(jt);
		
		add("Center",jsp);
		
		setBounds(200, 200, 600, 300);
		setVisible(true);
	}//UseJTableImage
	
	public static void main(String[] args) {
		new UseJTableImage();
	}
}
