package lti.shopproject.bgactivity;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lti.shopproject.entity.User;
import lti.shopproject.uimain.userprofile.UserP;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//For Changing Password

@SuppressWarnings("serial")
public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField np2;
	public User up;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword(toString());
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
	public ChangePassword(String a) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640,480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("CONFIRM NEW PASSWORD");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(220, 62, 188, 33);
		contentPane.add(lblNewLabel_1_1);

		np2 = new JTextField();
		np2.setColumns(10);
		np2.setBounds(220, 111, 188, 22);
		contentPane.add(np2);



		JButton nps_button = new JButton("SUBMIT");
		nps_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String newpass = np2.getText();
				try {	
					System.out.println("Values " + a +" "+ newpass);
					up.changePassword(a, newpass);
					
				}
				catch (NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null,"Not Updated. We are Sorry. Use Previous Password!");
				}


			}
		}
				);
		nps_button.setFont(new Font("Tahoma", Font.BOLD, 14));
		nps_button.setBounds(275, 185, 97, 25);
		contentPane.add(nps_button);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserP u = new UserP();
				u.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(275, 240, 97, 25);
		contentPane.add(btnNewButton);
	}
}
