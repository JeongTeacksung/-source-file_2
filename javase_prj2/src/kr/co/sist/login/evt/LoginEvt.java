package kr.co.sist.login.evt;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import kr.co.sist.login.view.LoginView;


public class LoginEvt extends WindowAdapter implements ActionListener {

	private LoginView lv;
	private LoginDAO ldao;
	
	public LoginEvt(LoginView lv) {
		this.lv=lv;
		ldao=LoginDAO.getInstance();
		
	}//LoginEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String inputId = "";
		inputId = lv.getJtfId().getText().trim();
		String inputPass = "";
		inputPass = new String(lv.getJpfPassword().getPassword());
		List<String> Idlist=new ArrayList<String>();
		try {
			Idlist=ldao.loginIdList();
			System.out.println("디비작업");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if ((ae.getSource() == lv.getJbtOk()) || ae.getSource() == lv.getJtfId()) {
			if (!inputId.equals("")) {
				lv.getJpfPassword().requestFocus();
			} // end if
		} // end if

		if ((ae.getSource() == lv.getJbtOk()) || ae.getSource() == lv.getJpfPassword()) {
			if (inputId.equals("")) {
				lv.getJtfId().requestFocus();
				JOptionPane.showMessageDialog(lv, "아이디를 입력해주세요. ", "로그인을 할 수 없습니다.", JOptionPane.ERROR_MESSAGE);
				return;
			} // end if

			if (inputPass.trim().equals("")) {
				JOptionPane.showMessageDialog(lv, "비밀번호를 입력해주세요. ", "로그인을 할 수 없습니다.", JOptionPane.ERROR_MESSAGE);
				return;
			} // end if

			try {
				if ( Idlist.toString().contains(inputId) && inputPass.equals(ldao.loginPass(inputId)) ) {
					JOptionPane.showMessageDialog(lv, "로그인 성공");
				} else {
					JOptionPane.showMessageDialog(lv, "아이디와 비밀번호 중 잘못입력되었습니다. ", "로그인을 할 수 없습니다.",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end else
		} // end if
	}// actionPerformed
}
