package com.raj;

import java.sql.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class MySQLConnect
 */
 
@SuppressWarnings("serial")
public class validate extends HttpServlet {
 
	InputStream inputStream;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sign_in = request.getParameter("username");
        String secret = request.getParameter("password");
{

        try {


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
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javademo", "root", "");
            PreparedStatement pst = conn.prepareStatement("Select sign_in,secret from CREDENTIALS where sign_in='"+sign_in+"' and secret=PASSWORD('"+secret+"')");
//            pst.setString(1, sign_in);
//            pst.setString(2, secret);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                out.println("Correct login credentials");
		response.sendRedirect("../success.jsp");
            } 
            else {
                out.println("Incorrect login credentials");
		response.sendRedirect("../failure.jsp");
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}}

