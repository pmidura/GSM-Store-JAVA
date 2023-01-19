package projectapp;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Midura Patryk
 */

public class LoginPanel {
    
    Container Content;
    JFrame LoginFrame;
    JPanel LoginPnl, EmailPnl, PasswdPnl, SubmitPnl, TitlePnl;
    JLabel LoginLbl, PasswdLbl, EmailLbl, BlankLbl;
    JTextField EmailTxtField;
    JPasswordField PasswdField;
    JButton LoginBtn, RegisterBtn;
    
    public void LoginPanel() {
        LoginFrame = new JFrame();
        LoginFrame.setSize(400, 200);
        LoginFrame.setLocationRelativeTo(null);
	LoginFrame.setTitle("Login");
	Content = LoginFrame.getContentPane();
        
        //Login Panel
        LoginPnl = new JPanel(new GridLayout(4,1));
        TitlePnl = new JPanel(new FlowLayout());
	LoginLbl = new JLabel();
	//LoginLbl.setText("Panel Logowania");
	LoginLbl.setFont(new Font("Arial", Font.PLAIN ,20));
	TitlePnl.add(LoginLbl);
        
        //Email Panel
        EmailPnl = new JPanel(new FlowLayout());
	EmailLbl = new JLabel();
	EmailLbl.setText("E-mail:");
	BlankLbl = new JLabel();
	BlankLbl.setText(" ");
	EmailTxtField = new JTextField();
	EmailTxtField.setPreferredSize(new Dimension(100,20));
	EmailPnl.add(EmailLbl);
	EmailPnl.add(BlankLbl);
	EmailPnl.add(EmailTxtField);
        
        //Password Panel
        PasswdPnl = new JPanel(new FlowLayout());	
	PasswdLbl = new JLabel();
	PasswdLbl.setText("Hasło:");
        BlankLbl = new JLabel();
        BlankLbl.setText("  ");
	PasswdField = new JPasswordField();
	PasswdField.setPreferredSize(new Dimension(100,20));
	PasswdField.setEchoChar('*');
	PasswdPnl.add(PasswdLbl);
        PasswdPnl.add(BlankLbl);
	PasswdPnl.add(PasswdField);
        
        //Buttons Panel
        SubmitPnl = new JPanel(new FlowLayout()); 
	LoginBtn = new JButton("Zaloguj");
	RegisterBtn = new JButton("Rejestracja");		
	SubmitPnl.add(LoginBtn);
	SubmitPnl.add(RegisterBtn);
        
        //Add to Login Panel & Login Frame
        LoginPnl.add(TitlePnl);
	LoginPnl.add(EmailPnl);
	LoginPnl.add(PasswdPnl);
	LoginPnl.add(SubmitPnl);
	LoginFrame.add(TitlePnl, "North");
	LoginFrame.add(LoginPnl, "Center");
        LoginFrame.setVisible(true);
    }
    
}

