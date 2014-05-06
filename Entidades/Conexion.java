package entidades;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.sql.Date;

public class Conexion{
	Statement stmt;
	Connection conn;
	public Conexion(){   
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/AMSS";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
    }
   
    public ResultSet executeQuery(String query){
      try {
         stmt.executeQuery (query);
         ResultSet rs = stmt.getResultSet();
         //rs.next(); //Va al registro ya validad
         return(rs);
      } catch (SQLException e) {System.out.println ("Cannot execute query" + e);}
	  return null;
    }  
 
   
   public ResultSet executeUpdate(String update){	
        try {
			System.out.println(update); 
			stmt.executeUpdate(update, Statement.RETURN_GENERATED_KEYS);
			return stmt.getGeneratedKeys();
        }catch (Exception e) { System.out.println ("Cannot update database" + e ); }  
        return null; 
    }  
   
}  
