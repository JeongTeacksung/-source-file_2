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
		
		String[] columnNames= {"로고","URL","특징"};
		Object[][] object= { 
					{ii1, "http://daum.net", "카카오프렌즈" },
					{ii2, "http://naver.com", "웹툰" },
					{ii3, "https://google.com", "검색엔진이 좋아요" }
					};
		
		dtm=new DefaultTableModel(object,columnNames) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}//end isCellEditable
		};
		
		//입력된 클래스가 그대로 Cell(columns)에 표현되도록 method Override
		//getColumnClass(int column)
		
		jt=new JTable(dtm) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column){
				//row-JTable에 입력된 이차원 배열의 행에 속한다면
				//모든 컬럼의 값을 입력된 형으로 반환한다.
				return getValueAt(0, column).getClass();
			}
		};
		jt.getTableHeader().setReorderingAllowed(false);//컬럼의 이동막기
		jt.getTableHeader().setResizingAllowed(false);//컬럼의 크기 변경 막기
		
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
