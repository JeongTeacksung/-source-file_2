package kr.co.sist.login.view;

	import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import kr.co.sist.login.evt.LoginEvt;

	@SuppressWarnings("serial")
	public class LoginView extends JFrame{

		private JButton jbtOk;
		private JLabel jlbId;
		private JLabel jlbPassword;
		private JTextField jtfId;
		private JPasswordField jpfPassword;
		private JLabel jlbImage;

		public LoginView() {
			super("로그파일정보");

			ImageIcon ii = new ImageIcon("C:/dev/workspace/javase_prj2/src/day1226image/login1.jpg");
			jlbImage = new JLabel(ii);
			jlbId = new JLabel("아이디");
			jlbPassword = new JLabel("비밀번호");
			jbtOk = new JButton("로그인");
			jtfId = new JTextField(10);
			jpfPassword = new JPasswordField(10);

			setLayout(null);
			Color color = new Color(0xF17F1E);
			jbtOk.setBackground(color);
			jbtOk.setFont(new Font("Dialog", Font.BOLD, 15));
			jbtOk.setForeground(Color.WHITE);

			jlbImage.setBounds(0, -62, 300, 200);
			jlbId.setBounds(15, 80, 100, 70);
			jtfId.setBounds(70, 100, 120, 30);

			jlbPassword.setBounds(10, 120, 100, 70);
			jpfPassword.setBounds(70, 140, 120, 30);

			jbtOk.setBounds(200, 100, 80, 70);

			add(jlbImage);
			add(jlbId);
			add(jtfId);
			add(jlbPassword);
			add(jpfPassword);
			add(jbtOk);
			
			LoginEvt le=new LoginEvt(this);
			jtfId.addActionListener(le);
			jpfPassword.addActionListener(le);
			jbtOk.addActionListener(le);
			addWindowListener(le);

			setBounds(600, 200, 310, 230);

			setVisible(true);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}// LoginView

		public JTextField getJtfId() {
			return jtfId;
		}

		public void setJtfId(JTextField jtfId) {
			this.jtfId = jtfId;
		}
		
		public JButton getJbtOk() {
			return jbtOk;
		}

		public JLabel getJlbId() {
			return jlbId;
		}

		public JLabel getJlbPassword() {
			return jlbPassword;
		}

		public JPasswordField getJpfPassword() {
			return jpfPassword;
		}

		public JLabel getJlbImage() {
			return jlbImage;
		}

	}// class

