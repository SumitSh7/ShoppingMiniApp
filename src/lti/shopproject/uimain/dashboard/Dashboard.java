package lti.shopproject.uimain.dashboard;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.uimain.loginpg.AdminLogin;
import lti.shopproject.uimain.loginpg.LoggedIn;
import lti.shopproject.uimain.newuser.NewUser;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;


//This is the dashboard. You can create New User or Login as existing or as an Admin

@SuppressWarnings("serial")
public class Dashboard extends JFrame {

	@SuppressWarnings("unused")
	private JFrame frame;
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
					Dashboard frame = new Dashboard();
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
	public Dashboard() {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JButton admin_button = new JButton("ADMIN LOGIN");
		admin_button.setFont(new Font("Georgia", Font.PLAIN, 12));
		admin_button.setBackground(UIManager.getColor("ColorChooser.background"));
		admin_button.setBounds(22, 395, 133, 25);
		contentPane.add(admin_button);

		admin_button.addActionListener(new ActionListener() { //For Admin Login
			public void actionPerformed(ActionEvent arg0) {


				AdminLogin admin = new AdminLogin();
				admin.setVisible(true);
				dispose();


			}
		});

		JButton new_user_button = new JButton("NEW USER");
		new_user_button.addActionListener(new ActionListener() { //For New User
			public void actionPerformed(ActionEvent arg0) {


				NewUser user = new NewUser();
				user.setVisible(true);
				dispose();


			}
		});
		new_user_button.setBounds(209, 259, 97, 25);
		contentPane.add(new_user_button);

		JButton login_button = new JButton("LOGIN");
		login_button.addActionListener(new ActionListener() { //For Normal Login (Existing Users)
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
				String query = "select * from CustomerInfo where ID = ? AND Pass = ?";

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
							LoggedIn u = new LoggedIn();
							u.setVisible(true);
							dispose();
						}
						else

						{
							JOptionPane.showMessageDialog(null, "WRONG USER ID / PASSWORD", "WRONG DETAILS", 2);
						}


					} catch (SQLException e2) {
						e2.printStackTrace();
					}


				}
			}
		});
		login_button.setBounds(351, 259, 97, 25);
		contentPane.add(login_button);

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

		JLabel lblNewLabel_1 = new JLabel("THE SHOP");
		lblNewLabel_1.setFont(new Font("Sitka Banner", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(237, 13, 182, 84);
		contentPane.add(lblNewLabel_1);

		username = new JTextField();
		username.setBounds(209, 161, 239, 22);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("By Sumit");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(534, 404, 76, 16);
		contentPane.add(lblNewLabel_2);
		
		password = new JPasswordField();
		password.setBounds(209, 217, 239, 22);
		contentPane.add(password);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("SHOW PASS");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxNewCheckBox.isSelected()) {
					
					password.setEchoChar((char)0);
					
				}

			}
		});
		chckbxNewCheckBox.setBounds(456, 216, 113, 25);
		contentPane.add(chckbxNewCheckBox);

	}
}
