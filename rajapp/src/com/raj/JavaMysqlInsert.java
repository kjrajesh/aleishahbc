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
public class JavaMysqlInsert extends HttpServlet{
	InputStream inputStream;
public void doPost(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException
{
 
	String title  = req.getParameter("title");
	String fname = req.getParameter("fname");
	String lname = req.getParameter("lname");
	String password = req.getParameter("password");
	String phone = req.getParameter("phone");
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
 
      // Insert USER data to ACCOUNTS table
       st.executeUpdate("INSERT INTO ACCOUNTS (cust_id,title,firstname,lastname,email,create_date) "
         +"VALUES (UUID(), '"+title+"', '"+fname+"','"+lname+"','"+emailaddress+"',NULL)");
      // Insert USER Credential data to CREDENTIALS table
       st.executeUpdate("INSERT INTO CREDENTIALS (cust_id,credential_id,sign_in,secret,hash,create_date) "
         +"select cust_id,UUID(),'"+emailaddress+"',PASSWORD('"+password+"'),'',NULL from ACCOUNTS where email='"+emailaddress+"'");
      // Insert USER Phone data to PHONE table
       st.executeUpdate("INSERT INTO PHONE (cust_id,phone_id,phone,is_main,create_date) "
         +"select cust_id,UUID(),'"+phone+"','Y',NULL from ACCOUNTS where email='"+emailaddress+"'");
      // Insert USER Address data to ADDRESS table
       st.executeUpdate("INSERT INTO ADDRESS (cust_id,address_id,address_1,address_2,address_3,city,state,country,postal_code,is_main,create_date) "
         +"select cust_id,UUID(),'12212 Paseo Lucido','APT D','','San Diego','California','US','92128','Y',NULL from ACCOUNTS where email='"+emailaddress+"'");


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
	req.setAttribute("message", "Sorry, Account Registration Failed. Please try again");
    }
        res.sendRedirect("../result.jsp");
  }
}}

