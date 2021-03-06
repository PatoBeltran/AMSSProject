package entidades;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.sql.Date;

public class Articulo{
  Connection conn;
  Statement stmt;
	
	
	public Articulo (){
		try {
	      String userName = "root";
	      String password = "";
	      String url = "jdbc:mysql://localhost/AMSS";
	      Class.forName ("com.mysql.jdbc.Driver").newInstance();
	      conn = DriverManager.getConnection (url, userName, password);
	      stmt = conn.createStatement();
	    }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }
	   
	public  String getInformacionArticulo(int idArticulo) {	
    		try {
      			stmt.executeQuery("SELECT informacion FROM articulo WHERE idArticulo = " + idArticulo);
      			ResultSet rs = stmt.getResultSet();
      			if (rs.next()) {
      				return rs.getString("informacion");
      					}
    			} catch (SQLException e) { System.out.println ("Error reading database getInformacionArticulo"); }
    return "";
	}

	public  String getTituloArticulo(int idArticulo) {	
    		try {
      			stmt.executeQuery("SELECT titulo FROM articulo WHERE idArticulo = " + idArticulo);
      			ResultSet rs = stmt.getResultSet();
      			if (rs.next()) {
      				return rs.getString("titulo");
      					}
    			} catch (SQLException e) { System.out.println ("Error reading database getTituloArticulo"); }
    return "";
	}
	
	public  int getAutorIDArticulo(int idArticulo) {	
    		try {
      			stmt.executeQuery("SELECT IDautor FROM articulo WHERE idArticulo = " + idArticulo);
      			ResultSet rs = stmt.getResultSet();
      			if (rs.next()) {
      				return rs.getInt("IDautor");
      					}
    			} catch (SQLException e) { System.out.println ("Error reading database getAutorIDArticulo"); }
    return 0;
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

	
	public int [] getIDPublicidad(){
		int [] array = new int [256];
		try{
			stmt.executeQuery("SELECT IDPublicidad FROM publicidad");
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

	public void publicar(int idArticulo, int toggle){
		try{
			stmt.executeUpdate("UPDATE articulo SET publicado = " + toggle + "WHERE idArticulo = " + idArticulo);

		} catch (SQLException e) { System.out.println ("Error reading database editArticuloInfo"); }

	}

	public void nuevoArticulo(int idAutor, int idArticulo, String titulo, String info, String fechaPub, boolean publicado){
		try{
			String s = "INSERT INTO articulos (idarticulo, titulo, idautor, fechapublicacion, publicado, informacion)" 
			+ " VALUES (" + idArticulo + " ,'" + titulo + " ,'" + idAutor 
			+ " ,'" + fechaPub + " ,'" + publicado + " ,'" + info + " )";
			stmt.executeUpdate(s);
		} catch (SQLException e) { System.out.println ("Error updating database Articulo");}
	}
}
