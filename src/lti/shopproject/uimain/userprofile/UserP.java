package lti.shopproject.uimain.userprofile;

import lti.shopproject.dbcon.*;

import lti.shopproject.bgactivity.ChangePassword;
import lti.shopproject.uimain.loginpg.LoggedIn;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;


//User Profile Page

@SuppressWarnings("serial")
public class UserP extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JButton submit_button;
	DbConnection db=new DbConnection();
	private JTable table1;
	private JPasswordField pass1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserP frame = new UserP();
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
	public UserP() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton back_button = new JButton("Back");
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoggedIn lg = new LoggedIn();
				lg.setVisible(true);
				dispose();
			}
		});
		back_button.setBounds(404, 480, 97, 25);
		contentPane.add(back_button);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(292, 131, 79, 32);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(292, 166, 79, 32);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(383, 137, 139, 22);
		contentPane.add(username);
		username.setColumns(10);

		submit_button = new JButton("SUBMIT");
		submit_button.addActionListener(new ActionListener() { //Display your data. Need to enter ID and Pass
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String uname = username.getText();
				String pass = pass1.getText();
				String query = "select * from CustomerInfo where ID = ? AND Pass = ?";

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
						Connection con = db.getConnection();
						System.out.println("Connection Established..");

						PreparedStatement pstm = con.prepareStatement(query);
						pstm.setString(1, uname);
						pstm.setString(2, pass);
						
						System.out.println("Connection Established 2..");
						ResultSet Rs = pstm.executeQuery();	    
					    
					    System.out.println("Connection Established 3..");
					    
		 					while(Rs.next()){
		 						System.out.println("Connection Established 4..");
		 						String a1 = Rs.getString(1);
		 			            String a2 = Rs.getString(2);
		 			            String a3 = Rs.getString(3);
		 			            String a4 = Rs.getString(4);
		 			            String a5 = Rs.getString(5);
		 			            String a6 = Rs.getString(6);
		 			            DefaultTableModel model = (DefaultTableModel) (table1.getModel());
		 			            model.addRow(new Object[]{a1,a2,a3,a4,a5,a6});
							
							}
				
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				
				}
			}
		});
		submit_button.setBounds(383, 226, 97, 25);
		contentPane.add(submit_button);
		
		JButton back_button_1 = new JButton("Change Password");
		back_button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) { //For Changing Password
				
				new ChangePassword(username.getText()).setVisible(true);
				dispose();
				
			}
		});
		back_button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		back_button_1.setBounds(363, 435, 175, 32);
		contentPane.add(back_button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 264, 818, 93);
		contentPane.add(scrollPane);
		
		table1 = new JTable();
		table1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table1);
		table1.setBorder(new EmptyBorder(1, 0, 0, 1));
		table1.setColumnSelectionAllowed(true);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Email", "Mobile", "Username", "Password", "Address"
			}
		));
		
		JLabel lblNewLabel_1 = new JLabel("YOUR INFO");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(295, 13, 243, 38);
		contentPane.add(lblNewLabel_1);
		
		pass1 = new JPasswordField();
		pass1.setBounds(383, 172, 139, 22);
		contentPane.add(pass1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Pass");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxNewCheckBox.isSelected()) {
					
					pass1.setEchoChar((char)0);
					
				}
			}
		});
		chckbxNewCheckBox.setBounds(531, 171, 113, 25);
		contentPane.add(chckbxNewCheckBox);
		table1.getColumnModel().getColumn(0).setPreferredWidth(112);
		table1.getColumnModel().getColumn(1).setPreferredWidth(122);
		table1.getColumnModel().getColumn(2).setPreferredWidth(126);
		table1.getColumnModel().getColumn(3).setPreferredWidth(111);
		table1.getColumnModel().getColumn(4).setPreferredWidth(128);
		table1.getColumnModel().getColumn(5).setPreferredWidth(188);

		
	}
}

