package lti.shopproject.uimain.addui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import lti.shopproject.uimain.dashboard.Dashboard;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

//A help Page

@SuppressWarnings("serial")
public class HelpPage extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpPage frame = new HelpPage();
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
	public HelpPage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);



		JLabel lblNewLabel = new JLabel("For Any Help -> Write us a Mail");
		lblNewLabel.setBounds(92, 13, 255, 73);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("HOME");
		btnNewButton.setBounds(139, 215, 155, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				Dashboard db = new Dashboard();
				db.setVisible(true);
				frame.dispose();


			}
		});
		btnNewButton.setBackground(new Color(255, 192, 203));
		contentPane.add(btnNewButton);

		JLabel lblShophelptheshopcom = new JLabel("shophelp@thesumitshop.com");
		lblShophelptheshopcom.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblShophelptheshopcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblShophelptheshopcom.setBounds(92, 99, 255, 73);
		contentPane.add(lblShophelptheshopcom);
	}
}
