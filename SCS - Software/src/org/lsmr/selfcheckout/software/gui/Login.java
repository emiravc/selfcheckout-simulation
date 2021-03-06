package org.lsmr.selfcheckout.software.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldUserID;
	private JPasswordField passwordField;
	private boolean pressed = false;
	private JDialog loginFailedDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		setModal(true);
		setTitle("Attendant Login");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 525, 363);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldUserID = new JTextField();
		textFieldUserID.setToolTipText("UserID");
		textFieldUserID.setBounds(101, 89, 299, 20);
		contentPane.add(textFieldUserID);
		textFieldUserID.setColumns(10);

		JTextArea txtrUserid = new JTextArea();
		txtrUserid.setEditable(false);
		txtrUserid.setText("UserID");
		txtrUserid.setBackground(UIManager.getColor("Button.background"));
		txtrUserid.setForeground(UIManager.getColor("CheckBox.foreground"));
		txtrUserid.setBounds(101, 59, 74, 22);
		contentPane.add(txtrUserid);

		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setEditable(false);
		txtrPassword.setText("Password");
		txtrPassword.setBackground(UIManager.getColor("Button.background"));
		txtrPassword.setBounds(101, 142, 74, 22);
		contentPane.add(txtrPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(101, 175, 299, 20);
		contentPane.add(passwordField);
		
		loginFailedDialog = new FailedLogin();
		loginFailedDialog.setLocationRelativeTo(contentPane);

		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressed = true;
				verifyLogin();
			}
		});
		LoginButton.setBounds(203, 224, 89, 23);
		contentPane.add(LoginButton);
	}
	
	private void verifyLogin() {
		String u = textFieldUserID.getText();
		if(u.compareToIgnoreCase("bad") == 0) {
			passwordField.setText("");
			textFieldUserID.setText("");
			loginFailedDialog.setVisible(true);
		}
		else {
			setVisible(false);
		}
	}

	public String getTextUserID() {
		if (pressed == true)
			;
		String enteredID;
		{
			enteredID = textFieldUserID.getText();
		}
		return enteredID;
	}

	public String getPasswordEntered() {
		if (pressed == true)
			;
		String pass;
		{
			pass = new String(passwordField.getPassword());
		}
		return pass;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setTextFieldUserID(JTextField textFieldUserID) {
		this.textFieldUserID = textFieldUserID;
	}

	public void setpasswordField(JPasswordField pass) {
		this.passwordField = pass;

	}

	public void unPressed() {
		pressed = false;
	}

}
