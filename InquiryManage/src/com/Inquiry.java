package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Inquiry {
	
		private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricityborad?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertInquiry(String INname, String INadderss, String INdate, String INreason) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into inquiryservice(`INid`,`INname`,`INadderss`,`INdate`,`INreason`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, INname);
			preparedStmt.setString(3, INadderss);
			preparedStmt.setString(4, INdate);
			preparedStmt.setString(5, INreason);
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newInquiry = readInquiry();
			output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Inquiry Details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
		public String readInquiry() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Customer Name &nbsp; </th><th>Address</th><th>Date</th><th>Reason</th></tr>";
			String query = "select * from inquiryservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String INid = Integer.toString(rs.getInt("INid"));
				String INname = rs.getString("INname");
				String INadderss = rs.getString("INadderss");
				String INdate = rs.getString("INdate");
				String INreason = rs.getString("INreason");

				// Add into the html table
				output += "<tr><td><input id=\'hidInquiryIDUpdate\' name=\'hidInquiryIDUpdate\' type=\'hidden\' value=\'" + INid + "'>" 
						+ INname +  "</td>";
				output += "<td>" + INadderss + "</td>";
				output += "<td>" + INdate + "</td>";
				output += "<td>" + INreason + "</td>";
				
				// buttons     
				output += "<td><input name='btnUpdate' type='button' value='Update' " + "class='btnUpdate btn btn-success'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' " + "class='btnRemove btn btn-danger' data-INid='" + INid + "'>" + "</td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateInquiry(String INid, String INname, String INadderss, String INdate, String INreason) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE inquiryservice SET INname=?,INadderss=?,INdate=?,INreason=?" + "WHERE INid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, INname);
			preparedStmt.setString(2, INadderss);
			preparedStmt.setString(3, INdate);
			preparedStmt.setString(4, INreason);
			preparedStmt.setInt(5, Integer.parseInt(INid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newInquiry = readInquiry();    
			output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";  
		} catch (Exception e) {
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Inquiry Details.\"}"; 
			System.err.println(e.getMessage());
		}

		return output;
	}
	public String deleteInquiry(String INid) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from inquiryservice where INid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values    
						preparedStmt.setInt(1, Integer.parseInt(INid)); 
				 
						// execute the statement    
						preparedStmt.execute();    
						con.close(); 
				 
						String newInquiry = readInquiry();   
			output = "{\"status\":\"success\", \"data\": \"" +  newInquiry + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Inquiry.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
