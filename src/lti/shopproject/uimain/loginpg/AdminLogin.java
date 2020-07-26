package lti.shopproject.uimain.loginpg;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.uimain.dashboard.Dashboard;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

//This is a basic Admin Login Page

@SuppressWarnings("serial")
public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;


	DbConnection db=new DbConnection();
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the contentPane.
	 */
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_3 = new JButton("LOGIN");
		btnNewButton_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				Connection con = db.getConnection();
				System.out.println("Connection Established..");
				PreparedStatement stl;
				ResultSet rsl;

				// get the username & password
				String uname = username.getText();
				String pass = password.getText();

				//create a select query to check if the username and the password exist in the database
				String query = "select * from AdminDetails where username = ? AND pass = ?";

				// show a message if the username or the password fields are empty
				if(uname.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter Your Username", "Empty Username", 2);
				}
				else if(pass.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Enter Your Password", "Empty Password", 2);
				}

				else{
					try {
						stl = con.prepareStatement(query);

						stl.setString(1, uname);
						stl.setString(2, pass);
						rsl = stl.executeQuery();

						if(rsl.next())
						{
							AdminLogged a = new AdminLogged();
							a.setVisible(true);
						}

						else
						{
							JOptionPane.showMessageDialog(null, "WRONG USER ID / PASSWORD", "WRONG DETAILS", 2);
						}


					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				}
			}});
		btnNewButton_3.setBounds(281, 272, 97, 25);
		contentPane.add(btnNewButton_3);


		JLabel lblNewLabel_1 = new JLabel("ADMINISTRATOR");
		lblNewLabel_1.setFont(new Font("Sitka Banner", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(203, 0, 250, 124);
		contentPane.add(lblNewLabel_1);


		JLabel lblNewLabel = new JLabel("USER ID");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(118, 155, 76, 35);
		contentPane.add(lblNewLabel);

		JLabel lblPass = new JLabel("PASSWORD");
		lblPass.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setBounds(92, 212, 109, 32);
		contentPane.add(lblPass);

		JButton btnNewButton = new JButton("HOME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //To go back to dashboard


				Dashboard db = new Dashboard();
				db.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 192, 203));
		btnNewButton.setBounds(12, 13, 97, 25);
		contentPane.add(btnNewButton);

		username = new JTextField();
		username.setBounds(203, 161, 283, 22);
		contentPane.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(203, 217, 283, 22);
		contentPane.add(password);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Pass");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(chckbxNewCheckBox.isSelected()) {

					password.setEchoChar((char)0);

				}
			}
		});
		chckbxNewCheckBox.setBounds(501, 216, 113, 25);
		contentPane.add(chckbxNewCheckBox);
	}
}
