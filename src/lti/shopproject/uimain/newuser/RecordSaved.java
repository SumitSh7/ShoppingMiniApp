package lti.shopproject.uimain.newuser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lti.shopproject.uimain.dashboard.Dashboard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Pop up when record saved

@SuppressWarnings("serial")
public class RecordSaved extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordSaved frame = new RecordSaved();
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
	public RecordSaved() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("RECORD SAVED SUCCESSFULLY!");
		lblNewLabel.setBounds(98, 13, 259, 107);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("DONE");
		btnNewButton.addActionListener(new ActionListener() { //Takes us back to login dashboard
			public void actionPerformed(ActionEvent arg0) {


				Dashboard db = new Dashboard();
				db.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(124, 252, 0));
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnNewButton.setBounds(171, 142, 97, 25);
		contentPane.add(btnNewButton);
	}

}
