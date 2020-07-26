package lti.shopproject.bgactivity;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


//Just a basic Thank you page. 

@SuppressWarnings("serial")
public class ThankYou extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThankYou frame = new ThankYou();
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
	public ThankYou() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THANK YOU FOR YOUR PURCHASE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setBounds(120, 0, 532, 91);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("See you again soon!");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.ITALIC, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(305, 96, 178, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CREATED BY -> Sumit");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(570, 419, 170, 16);
		contentPane.add(lblNewLabel_2);
	}

}
