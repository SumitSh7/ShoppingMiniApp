package lti.shopproject.uimain.addui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lti.shopproject.bgactivity.ThankYou;
import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.uimain.loginpg.LoggedIn;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


//The Shopping Basket Table

@SuppressWarnings("serial")
public class Basket extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tablebill;
	DbConnection db=new DbConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Basket frame = new Basket();
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
	public Basket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOUR BASKET");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(375, 13, 264, 56);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 88, 966, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PURCHASE ID", "PRODUCT NAME", "QUANTITY ADDED", "PRICE/UNIT", "Total Amount Each"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(113);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(2).setPreferredWidth(137);
		table.getColumnModel().getColumn(3).setPreferredWidth(116);
		table.getColumnModel().getColumn(4).setPreferredWidth(143);
		table.setBorder(new EmptyBorder(1, 0, 1, 1));
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("DISPLAY");
		btnNewButton.addActionListener(new ActionListener() { //Display Shopping Basket Table
			public void actionPerformed(ActionEvent e) {
				String query = "select * from PurchaseInfo ORDER BY prodid";
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
						Long a5 = Rs.getLong(5);

						DefaultTableModel model = (DefaultTableModel) (table.getModel());
						model.addRow(new Object[]{a1,a2,a3,a4,a5});

					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(897, 56, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("BILLING");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(477, 396, 91, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("CALCULATE");
		btnNewButton_1.addActionListener(new ActionListener() { //For calculating bill
			public void actionPerformed(ActionEvent e) {

				Connection con = db.getConnection();
				double value=0.0;
				try 
				{
					PreparedStatement ps = con.prepareStatement("select sum(bill) from PurchaseInfo");
					ResultSet result = ps.executeQuery();
					result.next();
					String sum = result.getString(1);	//StackOverflow Query
					value = Double.parseDouble(sum);
					DefaultTableModel model = (DefaultTableModel) (tablebill.getModel());
					model.addRow(new Object[]{value}); //Displaying the Bill Value 


				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setBounds(455, 428, 123, 25);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(455, 466, 123, 64);
		contentPane.add(scrollPane_1);
		
		tablebill = new JTable();
		tablebill.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"  FINAL BILL "
			}
		));
		tablebill.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		tablebill.setBorder(new EmptyBorder(1, 1, 1, 1));
		scrollPane_1.setViewportView(tablebill);
		
		JButton btnNewButton_2 = new JButton("CONTINUE SHOPPING");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Go back to Customer Logged IN Page
				
				LoggedIn l = new LoggedIn();
				l.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(799, 396, 195, 45);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CONFIRM PURCHASE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Go to final thank you page
				
				ThankYou ty = new ThankYou();
				ty.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(436, 543, 163, 45);
		contentPane.add(btnNewButton_3);
		
		JButton btnClearCart = new JButton("EMPTY CART");
		btnClearCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Delete from Basket
				
				String query1 = "DELETE FROM PurchaseInfo";

				Connection con = null;
		        @SuppressWarnings("unused")
				ResultSet rs = null;
				System.out.println(query1);
		        try {
		        	con = db.getConnection();
		        	PreparedStatement st = con.prepareStatement(query1);
					rs = st.executeQuery(query1);
					JOptionPane.showMessageDialog(null,"Cart Emptied!");
				}
		        catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Failed to delete Cart!");
					System.out.println("Exception : " +ex.getMessage());
				
			}
		}
		});
		btnClearCart.setBounds(38, 396, 123, 25);
		contentPane.add(btnClearCart);
		
		JButton btnClearScreen = new JButton("CLEAR SCREEN");
		btnClearScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(null,new String[]{"PURCHASE ID", "PRODUCT NAME", "QUANTITY ADDED", "PRICE/UNIT", "Total Amount Each"}));
			}
		});
		btnClearScreen.setBounds(29, 56, 123, 25);
		contentPane.add(btnClearScreen);
	}

}
