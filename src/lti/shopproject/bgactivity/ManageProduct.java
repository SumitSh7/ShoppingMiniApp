package lti.shopproject.bgactivity;


import lti.shopproject.entity.*;

import lti.shopproject.uimain.loginpg.AdminLogged;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lti.shopproject.dbcon.DbConnection;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


//Managing the Product

@SuppressWarnings("serial")
public class ManageProduct extends JFrame {

	private JPanel contentPane;
	private JTextField keyid;
	private JTextField quantity;
	private JTextField price;
	private JTextField keyid2;
	private JTextField name;
	private JTable table;
	DbConnection db=new DbConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProduct frame = new ManageProduct();
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
	public ManageProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("MANAGE INVENTORY");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(337, 13, 362, 56);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("KEY ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(75, 505, 92, 27);
		contentPane.add(lblNewLabel_1);

		keyid = new JTextField();
		keyid.setBounds(52, 545, 140, 22);
		contentPane.add(keyid);
		keyid.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("QUANTITY");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(379, 505, 77, 27);
		contentPane.add(lblNewLabel_1_1);

		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(502, 545, 140, 22);
		contentPane.add(quantity);

		JButton update_button = new JButton("UPDATE "); //Update Product
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Product p = new Product();
				p.updateProduct(keyid.getText().toString(), name.getText(),price.getText(), quantity.getText() );

			}
		});
		update_button.setBounds(131, 580, 137, 25);
		contentPane.add(update_button);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(350, 545, 140, 22);
		contentPane.add(price);

		JLabel lblNewLabel_1_1_1 = new JLabel("PRICE");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(533, 505, 77, 27);
		contentPane.add(lblNewLabel_1_1_1);

		JButton delete_button = new JButton("DELETE");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Delete Product
				
				Product p = new Product();
				p.deleteProduct(keyid2.getText().toString());
			}
		});
		delete_button.setBounds(759, 580, 137, 25);
		contentPane.add(delete_button);

		JLabel lblNewLabel_1_2 = new JLabel("KEY ID");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(792, 505, 92, 27);
		contentPane.add(lblNewLabel_1_2);

		keyid2 = new JTextField();
		keyid2.setColumns(10);
		keyid2.setBounds(759, 545, 140, 22);
		contentPane.add(keyid2);

		JButton add_button = new JButton("ADD NEW");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Add new Product

				Product p = new Product();
				p.createProduct(keyid.getText().toString(), name.getText(), quantity.getText(), price.getText());
			}
		});
		add_button.setBounds(429, 580, 137, 25);
		contentPane.add(add_button);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(201, 545, 140, 22);
		contentPane.add(name);

		JLabel lblNewLabel_1_1_2 = new JLabel("NAME");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setBounds(232, 505, 77, 27);
		contentPane.add(lblNewLabel_1_1_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 174, 828, 318);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PRODUCT ID", "PRODUCT NAME", "PRODUCT PRICE", "PRODUCT QUANTITY"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(163);
		table.getColumnModel().getColumn(1).setPreferredWidth(163);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.getColumnModel().getColumn(3).setPreferredWidth(166);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("DISPLAY");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Display all products

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

						DefaultTableModel model = (DefaultTableModel) (table.getModel());
						model.addRow(new Object[]{a1,a2,a3,a4});

					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}

			}
		});
		btnNewButton.setBounds(393, 118, 97, 25);
		contentPane.add(btnNewButton);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 table.setModel(new DefaultTableModel(null,new String[]{"PRODUCT ID", "PRODUCT NAME", "PRODUCT PRICE", "PRODUCT QUANTITY"}));
			}
		});
		btnClear.setBounds(548, 118, 97, 25);
		contentPane.add(btnClear);
		
		JButton btnNewButton_1 = new JButton("HOME");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogged al = new AdminLogged();
				al.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(12, 13, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("By Sumit");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(918, 692, 76, 16);
		contentPane.add(lblNewLabel_2);
	}
}
