package lti.shopproject.uimain.loginpg;

import java.awt.*;
import javax.swing.*;

import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.entity.Product;
import lti.shopproject.uimain.addui.Basket;
import lti.shopproject.uimain.addui.LoggedOut;
import lti.shopproject.uimain.userprofile.UserP;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

//When the User Logs In. They can go see their Profile Info or can Purchase Products.

@SuppressWarnings("serial")
public class LoggedIn extends JFrame {

	private JPanel contentPane;
	private JTable tablep;
	private JTextField prodid;
	private JTextField quan;
	DbConnection db=new DbConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggedIn frame = new LoggedIn();
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
	public LoggedIn() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("WELCOME TO THE SHOP!");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(311, 13, 404, 82);
		contentPane.add(lblNewLabel);

		JButton logout_button = new JButton("LOGOUT");
		logout_button.setBackground(new Color(255, 51, 102));
		logout_button.setBounds(894, 13, 100, 25);
		contentPane.add(logout_button);

		JButton userp = new JButton("USER PROFILE");
		userp.addActionListener(new ActionListener() { //To user profile
			public void actionPerformed(ActionEvent arg0) {
				UserP up = new UserP();
				up.setVisible(true);
				dispose();

			}
		});
		userp.setBackground(new Color(51, 255, 153));
		userp.setBounds(12, 13, 122, 25);
		contentPane.add(userp);

		JButton btnNewButton = new JButton("DISPLAY AVAILIBLE PRODUCTS");
		btnNewButton.addActionListener(new ActionListener() { //DISPLAY ALL AVAILIBLE PRODUCTS
			public void actionPerformed(ActionEvent e) {

				String query = "select * from ProductInfo ORDER BY prodid";
				Connection con = db.getConnection();
				try {

					System.out.println("Connection Established..");

					PreparedStatement pstm = con.prepareStatement(query);

					System.out.println("Connection Established 2..");
					ResultSet Rs = pstm.executeQuery();	    

					System.out.println("Connection Established 3..");

					while(Rs.next()){
						System.out.println("Connection Established 4..");
						Long a1 = Rs.getLong(1);
						String a2 = Rs.getString(2);
						Long a3 = Rs.getLong(3);
						Long a4 = Rs.getLong(4);

						DefaultTableModel model = (DefaultTableModel) (tablep.getModel()); //Display data in the table
						model.addRow(new Object[]{a1,a2,a3,a4});

					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}

			}
		});
		btnNewButton.setBounds(286, 108, 237, 38);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 167, 941, 377);
		contentPane.add(scrollPane);

		tablep = new JTable();
		tablep.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "PRODUCT QUANTITY"
				}
				));
		tablep.getColumnModel().getColumn(0).setPreferredWidth(167);
		tablep.getColumnModel().getColumn(0).setMinWidth(20);
		tablep.getColumnModel().getColumn(1).setPreferredWidth(170);
		tablep.getColumnModel().getColumn(2).setPreferredWidth(178);
		tablep.getColumnModel().getColumn(3).setPreferredWidth(149);
		scrollPane.setViewportView(tablep);

		JButton btnRefreshScreen = new JButton("REFRESH SCREEN");
		btnRefreshScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //For clearing the table view

				tablep.setModel(new DefaultTableModel(null,new String[]{"PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "PRODUCT QUANTITY"}));
			}
		});
		btnRefreshScreen.setBounds(557, 108, 192, 38);
		contentPane.add(btnRefreshScreen);

		JLabel lblNewLabel_1 = new JLabel("PURCHASE PRODUCT");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(175, 557, 205, 31);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("TO CART");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(704, 557, 205, 31);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(144, 610, 81, 25);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("QUANTITY");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(144, 655, 81, 25);
		contentPane.add(lblNewLabel_2_1);

		prodid = new JTextField();
		prodid.setBounds(257, 611, 156, 22);
		contentPane.add(prodid);
		prodid.setColumns(10);

		quan = new JTextField();
		quan.setColumns(10);
		quan.setBounds(257, 656, 156, 22);
		contentPane.add(quan);

		JButton btnNewButton_1 = new JButton("VIEW CART");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Go to Basket / Cart
				Basket b = new Basket();
				b.setVisible(true);
				dispose();

			}
		});
		btnNewButton_1.setBounds(745, 610, 122, 25);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("ADD TO CART");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //To add product to cart

				Product p = new Product();
				int productq =Integer.parseInt(quan.getText());
				System.out.println("Value -> " + productq);
				p.sellProduct(prodid.getText(), productq);

			}
		});
		btnNewButton_2.setBounds(463, 630, 127, 25);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel_2_2 = new JLabel("By Sumit");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setBounds(918, 692, 76, 16);
		contentPane.add(lblNewLabel_2_2);
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				LoggedOut lg = new LoggedOut();
				lg.setVisible(true);
				dispose();

			}
		});

	}
}

