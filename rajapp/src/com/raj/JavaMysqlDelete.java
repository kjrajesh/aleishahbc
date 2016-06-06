package com.raj;

import java.sql.*;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * A Java MySQL PreparedStatement INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 * 
 * Created by Rajesh Kameswaran
 */
@SuppressWarnings("serial")
public class JavaMysqlDelete extends HttpServlet{
	InputStream inputStream;
public void doGet(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException
{
 
                            String emailaddress = req.getParameter("emailaddr");
//  public static void main(String[] args)
  {
    try
    {

    	Properties prop = new Properties();
		String propFileName = "config.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

      // create a mysql database connection
      String myDriver = prop.getProperty("jdbc.driverClassName");
      String myUrl = prop.getProperty("jdbc.url");
      String dbuser = prop.getProperty("jdbc.username");
      String dbpassword = prop.getProperty("jdbc.password");
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl,dbuser,dbpassword);
     
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
 
       Statement st = conn.createStatement();
 
        // SET Foreign Key check disable
       st.executeUpdate("SET foreign_key_checks = 0");
      // note that i'm leaving "date_created" out of this insert statement
       st.executeUpdate("delete a.*,b.*,c.*,d.* from ACCOUNTS as a, CREDENTIALS as b, PHONE as c, ADDRESS as d where a.cust_id = b.cust_id AND b.cust_id = c.cust_id AND c.cust_id = d.cust_id AND a.email = ('"+emailaddress+"')");
	// SET Foreign Key check Enable 
       st.executeUpdate("SET foreign_key_checks = 1");
	System.out.println(emailaddress);	

/**          
      // the mysql insert statement
      String query = " insert into ACCOUNTS (cust_id,title,firstname,lastname,email,create_date)"
       + " values (?, ?, ?, ?, ?, ?)";
 
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString (1, "UUID()");
      preparedStmt.setString (2, "MR");
      preparedStmt.setString (3, "Rajesh");
      preparedStmt.setString (4, "Kameswaran");
      preparedStmt.setString (5, "kjrajesh@yahoo.com");
      preparedStmt.setDate   (6, NULL);
 
      // execute the preparedstatement
      preparedStmt.execute();
*/       
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
	req.setAttribute("message", "Sorry, Account Deletion Failed. Please try again");
    }
	res.sendRedirect("../delete.jsp");
  }
}}

