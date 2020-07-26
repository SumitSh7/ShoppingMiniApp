package lti.shopproject.entity;

import lti.shopproject.dbcon.*;

import java.sql.*;
import javax.swing.*;

// Basic Admin 

public class Administrator {
	private String name;
	private String phoneNumber;
	DbConnection db=new DbConnection();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	
	@SuppressWarnings("unused")
	public void deleteCustomer(String cid) { //To delete an existing customer
		String query1 = "DELETE FROM CustomerInfo WHERE ID='"+cid +"'";
		
		Connection con = null;
        ResultSet rs = null;
		System.out.println(query1);
        try {
        	con = db.getConnection();
        	PreparedStatement st = con.prepareStatement(query1);
			rs = st.executeQuery(query1);
			JOptionPane.showMessageDialog(null,"Customer Deleted!");
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to delete Customer!");
			System.out.println("Exception : " +ex.getMessage());
        }
	}
	
}