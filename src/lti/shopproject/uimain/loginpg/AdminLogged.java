package lti.shopproject.uimain.loginpg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lti.shopproject.bgactivity.CustomerDisplay;
import lti.shopproject.bgactivity.ManageProduct;
import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.uimain.addui.LoggedOut;

import javax.swing.JScrollPane;


//Admin Can do stuff from here.

@SuppressWarnings("serial")
public class AdminLogged extends JFrame {

	private JPanel contentPane;
	DbConnection db=new DbConnection();
	private JTable tablecur;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogged frame = new AdminLogged();
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
	public AdminLogged() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN PAGE");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(201, 0, 249, 68);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("CURRENT INVENTORY"); //Check current inventory!
		btnNewButton.addActionListener(new ActionListener() {
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
	 			            			            
	 			            DefaultTableModel model = (DefaultTableModel) (tablecur.getModel());
	 			            model.addRow(new Object[]{a1,a2,a3,a4});
						
						}
			
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		btnNewButton.setBounds(243, 81, 159, 36);
		contentPane.add(btnNewButton);
		
		JButton btnCustomerList = new JButton("CUSTOMER LIST");
		btnCustomerList.addActionListener(new ActionListener() { //display customer list of al  registered users
			public void actionPerformed(ActionEvent e) {
				
				CustomerDisplay cd = new CustomerDisplay();
				cd.setVisible(true);
				dispose();
				
			}
		});
		btnCustomerList.setBounds(93, 354, 159, 36);
		contentPane.add(btnCustomerList);
		
		JButton btnManageProduct = new JButton("MANAGE PRODUCT");
		btnManageProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //To Manipulate Product table
				ManageProduct mp = new ManageProduct();
				mp.setVisible(true);
				dispose();
				
			}
		});
		btnManageProduct.setBounds(393, 354, 159, 36);
		contentPane.add(btnManageProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 130, 598, 198);
		contentPane.add(scrollPane);
		
		tablecur = new JTable();
		tablecur.setBorder(new EmptyBorder(1, 0, 0, 1));
		tablecur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablecur.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ProductID", "Product Name", "Price", "Quantity"
			}
		));
		tablecur.getColumnModel().getColumn(0).setPreferredWidth(147);
		tablecur.getColumnModel().getColumn(0).setMinWidth(20);
		tablecur.getColumnModel().getColumn(1).setPreferredWidth(155);
		tablecur.getColumnModel().getColumn(2).setPreferredWidth(140);
		tablecur.getColumnModel().getColumn(3).setPreferredWidth(126);
		scrollPane.setViewportView(tablecur);
		
		JButton btnNewButton_1 = new JButton("LOGOUT");
		btnNewButton_1.addActionListener(new ActionListener() { //Logout Page
			public void actionPerformed(ActionEvent e) {
				LoggedOut lo = new LoggedOut();
				lo.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(513, 13, 97, 25);
		contentPane.add(btnNewButton_1);
	}
}
