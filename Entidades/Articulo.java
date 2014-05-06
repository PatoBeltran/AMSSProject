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
	   
	public  String getArticulo(int idArticulo) {	
    		try {
      			stmt.executeQuery("SELECT informacion FROM articulo WHERE idArticulo = " + idArticulo);
      			ResultSet rs = stmt.getResultSet();
      			if (rs.next()) {
      				return rs.getString("informacion");
      					}
    			} catch (SQLException e) { System.out.println ("Error reading database validarPorNombre"); }
    return "";
	}
	
	public boolean editArticuloInfo(int idArticulo, String nInfo){
		try{
			stmt.executeUpdate("UPDATE articulo SET informacion = '" + nInfo + "' WHERE idArticulo = " + idArticulo);
			return true;
		} catch (SQLException e) { System.out.println ("Error reading database editArticuloInfo"); }
		return false;
	}

	public boolean editArticuloTitulo(int idArticulo, String nInfo){
		try{
			stmt.executeUpdate("UPDATE articulo SET informacion = '" + nInfo + "' WHERE idArticulo = " + idArticulo);
			return true;
		} catch (SQLException e) { System.out.println ("Error reading database editArticuloInfo"); }
		return false;
	}
}
