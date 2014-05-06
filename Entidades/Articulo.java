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

	public boolean editArticuloTitulo(int idArticulo, String nTitulo){
		try{
			stmt.executeUpdate("UPDATE articulo SET titulo = '" + nTitulo + "' WHERE idArticulo = " + idArticulo);
			return true;
		} catch (SQLException e) { System.out.println ("Error reading database editArticuloTitulo"); }
		return false;
	}

	public int [] getIDArticulos(){
		int [] array = new int [256];
		try{
			stmt.executeQuery("SELECT idArticulo FROM articulo WHERE publicado = 1");
			ResultSet rs = stmt.getResultSet();
			int counter=0;
			while(rs.next())
			{
				array[counter]=rs.getInt("idArticulo");
				counter++;
			}
			int [] array2 = new int[counter];
			System.arraycopy( array, 0, array2, 0, array2.length );
			return array2;
		} catch (SQLException e) { System.out.println ("Error reading database editArticuloTitulo"); }
		return new int [] {0};
	}
}
