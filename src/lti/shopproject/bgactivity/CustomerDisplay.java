package lti.shopproject.bgactivity;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import lti.shopproject.dbcon.DbConnection;
import lti.shopproject.entity.Administrator;
import lti.shopproject.uimain.loginpg.AdminLogged;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;


//For Displaying Customer

@SuppressWarnings("serial")
public class CustomerDisplay extends JFrame {

	private JPanel contentPane;
	private JTable tablec;
	DbConnection db=new DbConnection();
	private JTextField cid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDisplay frame = new CustomerDisplay();
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
	public CustomerDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DISPLAYING CUSTOMER INFO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 268, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminLogged al = new AdminLogged();
				al.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(882, 395, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.addActionListener(new ActionListener() { //Display Customer
			public void actionPerformed(ActionEvent e) {
				
				String query = "select Name,Email,Mobile,ID,Address from CustomerInfo";
				Connection con = db.getConnection();
				try {
					
					System.out.println("Connection Established..");

					PreparedStatement pstm = con.prepareStatement(query);
								
					System.out.println("Connection Established 2..");
					ResultSet Rs = pstm.executeQuery();	    
				    
				    System.out.println("Connection Established 3..");
				    
	 					while(Rs.next()){
	 						System.out.println("Connection Established 4..");
	 						String a1 = Rs.getString(1);
	 			            String a2 = Rs.getString(2);
	 			            String a3 = Rs.getString(3);
	 			            String a4 = Rs.getString(4);
	 			            String a6 = Rs.getString(5);
	 			            			            
	 			            DefaultTableModel model = (DefaultTableModel) (tablec.getModel());
	 			            model.addRow(new Object[]{a1,a2,a3,a4,a6});
						
						}
			
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		btnDisplay.setBounds(773, 22, 97, 25);
		contentPane.add(btnDisplay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 66, 972, 291);
		contentPane.add(scrollPane);
		
		tablec = new JTable();
		tablec.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NAME", "EMAIL", "MOBILE", "ID", "ADDRESS"
			}
		));
		tablec.getColumnModel().getColumn(0).setPreferredWidth(115);
		tablec.getColumnModel().getColumn(1).setPreferredWidth(156);
		tablec.getColumnModel().getColumn(2).setPreferredWidth(137);
		tablec.getColumnModel().getColumn(3).setPreferredWidth(111);
		tablec.getColumnModel().getColumn(4).setPreferredWidth(274);
		tablec.setBorder(new EmptyBorder(1, 0, 0, 1));
		tablec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(tablec);
		
		JButton btnDeleteCustomer = new JButton("DELETE CUSTOMER?");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Administrator ad = new Administrator();
				ad.deleteCustomer(cid.getText().toString());
				
			}
		});
		btnDeleteCustomer.setBounds(197, 395, 164, 25);
		contentPane.add(btnDeleteCustomer);
		
		cid = new JTextField();
		cid.setBounds(40, 396, 116, 22);
		contentPane.add(cid);
		cid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("USER ID");
		lblNewLabel_1.setBounds(66, 370, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tablec.setModel(new DefaultTableModel(null,new String[]{"NAME", "EMAIL", "MOBILE", "ID", "ADDRESS"}));
			}
		});
		btnClear.setBounds(882, 22, 97, 25);
		contentPane.add(btnClear);
	}
}
