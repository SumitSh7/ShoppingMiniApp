package lti.shopproject.uimain.newuser;
import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.uimain.addui.HelpPage;
import lti.shopproject.uimain.dashboard.Dashboard;

import java.sql.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;

//THE NEW USER REGISTRATION PAGE

@SuppressWarnings("serial")
public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField mobile;
	private JTextField id;
	private JTextField passf1;
	private JTextField passf2;
	private JFrame frame;
	private JTextField addr;
	@SuppressWarnings("unused")
	private JCheckBox terms;
	private boolean flag;


	DbConnection db=new DbConnection();




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
					frame.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
				);
	}


	public boolean emailValidation(String uemail) //Email Validation
	{
		String email_pattern = "^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(email_pattern);
		Matcher mat = pat.matcher(uemail);

		if(mat.matches()==false) {
			{
				JOptionPane.showMessageDialog(null, "Email not correct","Confirm Email",2); 
				return false;
			}
		}
		else {
			return true;
		}

	}

	public boolean verifyNull() //Null Value Validation
	{

		String uname = name.getText().toString();
		String uemail = email.getText().toString();
		String umobile = mobile.getText().toString();
		String uid = id.getText().toString();
		String pass1 = passf1.getText().toString();
		String pass2 = passf2.getText().toString();
		String address = addr.getText().toString();
		// check empty fields
		if(uname.trim().equals("") || uemail.trim().equals("") || umobile.trim().equals("") || uid.trim().equals("")
				|| pass1.trim().equals("") || pass2.trim().equals("") || address.trim().equals(""))
		{
			JOptionPane.showMessageDialog(null, "One Or More Fields is/are Empty","Empty Fields",2);
			return false;
		}

		// check if the two password are equal
		else if(!pass1.equals(pass2))
		{
			JOptionPane.showMessageDialog(null, "Password don't Match","Confirm Password",2); 
			return false;
		}

		// if everything is ok
		else{
			return true;
		}
	}

	/*	public boolean checkAgree() {


		{
			return true;
		}
		else {
			return false;
		}

	}*/


	public boolean checkUsername(String uid){ //For Checking Username from Database

		Connection con2 = db.getConnection();
		System.out.println("Connection Established..");
		PreparedStatement psu;
		ResultSet rsu;
		boolean username_exist = false;

		String query = "SELECT * FROM CustomerInfo WHERE ID = ?";

		try {

			psu = con2.prepareStatement(query);
			psu.setString(1, uid);
			rsu = psu.executeQuery();

			if(rsu.next())
			{
				username_exist = true;
				JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return username_exist;
	}


	public NewUser() {


		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("NEW USER REGISTRATION");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(248, 13, 558, 63);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Welcome to THE SHOP");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(394, 61, 267, 63);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("NAME");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2.setBounds(86, 203, 122, 32);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("EMAIL ID");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(86, 260, 122, 32);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("MOBILE");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(86, 305, 122, 32);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("USER NAME");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(96, 356, 122, 32);
		contentPane.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_3 = new JLabel("PASSWORD");
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_3.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_1_3.setBounds(86, 413, 122, 32);
		contentPane.add(lblNewLabel_2_1_3);

		JLabel lblNewLabel_2_1_3_1 = new JLabel("ADDRESS");
		lblNewLabel_2_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_3_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_1_3_1.setBounds(86, 516, 122, 32);
		contentPane.add(lblNewLabel_2_1_3_1);

		JButton create_button = new JButton("CREATE ACCOUNT");
		create_button.addActionListener(new ActionListener() { //When Create account button is hit.
			public void actionPerformed(ActionEvent e) {


				String uname = name.getText().toString();
				String uemail = email.getText().toString();
				String umobile = mobile.getText().toString();
				String uid = id.getText().toString();
				String pass2 = passf2.getText().toString();
				String address = addr.getText().toString();


				int i=0;

				if(flag==true) {	

					if(verifyNull()) //Check for Null
					{
						System.out.println("No Null Validated!");
						if(emailValidation(uemail))//Check Email
						{ 
							System.out.println("Email Validated!");
							// check if the username already exists
							if(!checkUsername(uid))
							{
								System.out.println("Username Validated!");


								System.out.println("Terms Agreed!");
								try {
									Connection con1 = db.getConnection();
									PreparedStatement ps1 = con1.prepareStatement("insert into CustomerInfo values (?,?,?,?,?,?)");
									ps1.setString(1,uname);
									ps1.setString(2,uemail);
									ps1.setString(3,umobile);
									ps1.setString(4,uid);
									ps1.setString(5,pass2);
									ps1.setString(6,address);

									i=ps1.executeUpdate();

									if(i>0) {
										RecordSaved rse = new RecordSaved();
										rse.setVisible(true);

										dispose();
									}

								}
								catch (SQLException e1) {
									e1.printStackTrace();
								}


							}

						}
					}
				}

			}

		});



		create_button.setBackground(new Color(50, 205, 50));
		create_button.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		create_button.setBounds(362, 630, 332, 55);
		contentPane.add(create_button);

		JButton btnNewButton_1 = new JButton("HOME");
		btnNewButton_1.addActionListener(new ActionListener() { //To go back to home dashboard
			public void actionPerformed(ActionEvent e) {

				dispose();
				Dashboard db = new Dashboard();
				db.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 182, 193));
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnNewButton_1.setBounds(12, 13, 97, 25);
		contentPane.add(btnNewButton_1);

		name = new JTextField();
		name.setBounds(248, 204, 389, 32);
		contentPane.add(name);
		name.setColumns(10);


		email = new JTextField();
		email.setColumns(10);
		email.setBounds(248, 261, 389, 32);
		contentPane.add(email);

		mobile = new JTextField();
		mobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				String phonenum = mobile.getText();
				int length = phonenum.length();
				//Checking Phone number validation;
				//Works as in can only take numbers and cannot go more than 10. 
				if(evt.getKeyChar()>= '0'&& evt.getKeyChar()<= '9') 
				{

					if(length<10)
					{	
						mobile.setEditable(true);
					}
					else 
					{
						mobile.setEditable(false);
					}
				}

				else {

					if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE) 
					{
						mobile.setEditable(true);
					}
					else
					{
						mobile.setEditable(false);
					}

				}
			}





		});
		mobile.setColumns(10);
		mobile.setBounds(248, 306, 389, 32);
		contentPane.add(mobile);

		id = new JTextField();
		id.setColumns(10);
		id.setBounds(248, 357, 389, 32);
		contentPane.add(id);

		passf1 = new JTextField();
		passf1.setColumns(10);
		passf1.setBounds(248, 414, 389, 32);
		contentPane.add(passf1);

		passf2 = new JTextField();
		passf2.setColumns(10);
		passf2.setBounds(248, 459, 389, 32);
		contentPane.add(passf2);

		JButton help_button = new JButton("HELP?");
		help_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HelpPage help = new HelpPage();
				help.setVisible(true);
			}
		});
		help_button.setBackground(new Color(30, 144, 255));
		help_button.setBounds(897, 13, 97, 25);
		contentPane.add(help_button);

		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(662, 212, 253, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("abc@gmail.com");
		lblNewLabel_3_1.setBounds(662, 260, 253, 16);
		contentPane.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Numeral Value Only. 10 Digits");
		lblNewLabel_3_1_1.setBounds(662, 314, 253, 16);
		contentPane.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("Your Username");
		lblNewLabel_3_1_1_1.setBounds(662, 365, 253, 16);
		contentPane.add(lblNewLabel_3_1_1_1);

		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Your Password");
		lblNewLabel_3_1_1_1_1.setBounds(662, 422, 253, 16);
		contentPane.add(lblNewLabel_3_1_1_1_1);

		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("Enter Address. No Spaces");
		lblNewLabel_3_1_1_1_1_1.setBounds(662, 525, 253, 16);
		contentPane.add(lblNewLabel_3_1_1_1_1_1);

		addr = new JTextField();
		addr.setColumns(10);
		addr.setBounds(248, 517, 389, 32);
		contentPane.add(addr);

		JLabel lblNewLabel_3_1_1_1_1_2 = new JLabel("Confirm Password");
		lblNewLabel_3_1_1_1_1_2.setBounds(662, 467, 253, 16);
		contentPane.add(lblNewLabel_3_1_1_1_1_2);

		JLabel lblNewLabel_2_1_3_2 = new JLabel("RE ENTER PASSWORD");
		lblNewLabel_2_1_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_3_2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_1_3_2.setBounds(39, 458, 169, 32);
		contentPane.add(lblNewLabel_2_1_3_2);

		JRadioButton Male = new JRadioButton("MALE");
		Male.setBounds(248, 147, 69, 25);
		contentPane.add(Male);

		JRadioButton Female = new JRadioButton("FEMALE");
		Female.setBounds(329, 147, 89, 25);
		contentPane.add(Female);

		JLabel lblNewLabel_2_2 = new JLabel("GENDER");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(86, 142, 122, 32);
		contentPane.add(lblNewLabel_2_2);

		JRadioButton Other = new JRadioButton("OTHER");
		Other.setBounds(423, 147, 127, 25);
		contentPane.add(Other);

		JCheckBox terms = new JCheckBox("I Agree to Terms and Conditions");
		terms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(terms.isSelected()) {
					flag=true;
				}
				else 
				if(!terms.isSelected())
				{
					JOptionPane.showMessageDialog(null, "Kindly Accept terms and conditions!", "Terms Failed", 2);
					flag=false;
					
				}
			}
		});
		terms.setBounds(423, 576, 224, 25);
		contentPane.add(terms);



	}
}
