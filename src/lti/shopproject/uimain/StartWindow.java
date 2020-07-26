package lti.shopproject.uimain;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import lti.shopproject.uimain.dashboard.Dashboard;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//THIS IS THE START OF THE APPLICATION


public class StartWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("WELCOME TO THE SHOP APP");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(106, 13, 421, 108);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("The Best In Buisness");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(211, 82, 215, 108);
		frame.getContentPane().add(lblNewLabel_1);

		JButton dbbutton = new JButton("TO DASHBOARD"); //Takes us to the Login Dashboard
		dbbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				Dashboard db = new Dashboard();
				db.setVisible(true);
				frame.dispose();

			}
		});
		dbbutton.setBackground(new Color(102, 205, 170));
		dbbutton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		dbbutton.setBounds(238, 187, 163, 64);
		frame.getContentPane().add(dbbutton);
	}

}
