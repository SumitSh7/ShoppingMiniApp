package lti.shopproject.entity;

import lti.shopproject.dbcon.*;
import java.sql.*;
import javax.swing.*;

//Simple User 

public abstract class User {


	protected String userId;
	protected String password;

	DbConnection dbu=new DbConnection(); //Database Connection Here


	public User(String userId, String password) {

		this.userId = userId;
		this.password = password;
		
	}


	public String getUserId() {
		return userId;
	}

	public void setPassword(String passwd) {
		if (!passwd.isEmpty())
			this.password = passwd;
		else
			throw new IllegalArgumentException("Fill in the password");
	}


	public void changePassword(String a, String newPass) { //Change Password

		String userId = a;
		String query = "UPDATE CustomerInfo SET Pass = "+ newPass +" WHERE ID= "+userId;
		Connection con1 = dbu.getConnection();
		System.out.println("Connection Established..");
		System.out.println(query);
		try {
			PreparedStatement st = con1.prepareStatement(query);
			int res = st.executeUpdate(query);
			System.out.println("Data inserted");
			if (res > 0) {
				JOptionPane.showMessageDialog(null,"Password Updated!");
			}
			else {
				JOptionPane.showMessageDialog(null,"Password didn't match!");
			}
		}
		catch(Exception ex) {
			System.out.println("Exception : " +ex.getMessage());
		}

	}
}
