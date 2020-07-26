package lti.shopproject.entity;

import java.sql.*;
import lti.shopproject.dbcon.DbConnection;
import javax.swing.*;


//THE PRODUCT PAGE



public class Product {
	private String productId;
	private String productName;
	private double price;
	private int quantity;
	public static String[] columnNames = {"Product ID", "Name", "Price", "Available Quantity"};
	DbConnection db=new DbConnection();

	public Product() {}
	public Product(String productId) {
		if (!productId.isEmpty())
			this.productId = productId;
		else
			throw new IllegalArgumentException("Fill in the ID");
	}

	public void setProductName(String name) {
		if (!name.isEmpty())
			this.productName = name;
		else
			throw new IllegalArgumentException("Fill in the name");
	}
	public void setPrice(double p) {
		this.price = p;
	}
	public void setQuantity(int q) {
		this.quantity = q;
	}
	public String getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}

	@SuppressWarnings("unused")
	public void sellProduct(String prodid1, int quantity) { //Add product to basket table

		String query = "INSERT INTO PurchaseInfo(prodid, pname, quantity,cost,bill) VALUES ("+prodid1+",(SELECT productname FROM ProductInfo WHERE prodid= "+prodid1+"), "+ quantity+ ", (SELECT price FROM ProductInfo WHERE prodid= "+prodid1+"),"+ quantity+"*(SELECT price FROM ProductInfo WHERE prodid= "+prodid1+"))";
		Connection con = null;
		ResultSet rs = null;

		try {
			con = db.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			rs = st.executeQuery(query);
			System.out.println("Data Inserted");
			updateProductPurchase(prodid1, quantity); //Update Product Table Quantity
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Wrong Input. Please Retry!"); 
			System.out.println("Exception : " +ex.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private void updateProductPurchase(String prodid1, int purchasedquan) { //Update Product Table Quantity

		String query = "UPDATE ProductInfo SET quantity = (quantity-"+purchasedquan+") where prodid = "+ prodid1;
		Connection con = null;
		int rs = 0;
		try {

			con = db.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			rs = st.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Done!");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed!");
			System.out.println("Exception : " +ex.getMessage());
		}



	}
	@SuppressWarnings("unused")
	public void updateProduct(String keyid, String name, String quantity, String price) { //For admin to update a product
		String query = "UPDATE ProductInfo SET productname = '" + name+ "' , price = "+ price+", quantity = "+quantity+" where prodid = "+ keyid;
		Connection con = null;
		int rs = 0;
		try {

			con = db.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			rs = st.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Done!");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed!");
			System.out.println("Exception : " +ex.getMessage());
		}

	}

	@SuppressWarnings("unused")
	public void createProduct(String keyid2, String name, String price, String quantity) { //For admin to create a new product
		String query = "INSERT INTO ProductInfo (prodid,productname, price, quantity) VALUES ('"+ keyid2 + "','"+ name+"','"+price+"','"+quantity+"')";
		Connection con = null;
		int rs = 0;
		System.out.println(query);
		try {

			con = db.getConnection();
			PreparedStatement st = con.prepareStatement(query);
			rs = st.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Product Created!");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to add Product!");
			System.out.println("Exception : " +ex.getMessage());
		}
	}


	@SuppressWarnings("unused")
	public void deleteProduct(String keyid2) { // For Admin to delete a product
		String query1 = "DELETE FROM ProductInfo WHERE prodid= "+keyid2;
		Connection con = null;
		ResultSet rs = null;
		System.out.println(query1);
		try {
			con = db.getConnection();
			PreparedStatement st = con.prepareStatement(query1);
			rs = st.executeQuery(query1);
			JOptionPane.showMessageDialog(null,"Product Deleted!");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to delete product!");
			System.out.println("Exception : " +ex.getMessage());
		}
	}
}