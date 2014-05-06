package entidades;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.sql.Date;

public class Articulo{
  Connection conn;
  Statement stmt;
	
	
	public Articulo (){

   }
	   
	public  getArticulo(int idArticulo) {	
    try {
      stmt.executeQuery("SELECT informacion FROM articulo WHERE idArticulo = " + idArticulo);
      ResultSet rs = stmt.getResultSet();
      if (rs.next()) {
      	return rs.getString("informacion");
      }
    } catch (SQLException e) { System.out.println ("Error reading database validarPorNombre"); }
    return "";
	}
