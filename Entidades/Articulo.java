package entidades;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.sql.Date;

public class Articulo{
	public int idArticulo;
	public String titulo;
	public int idAutor;
	public boolean estatus;
	public String textoArticulo;
	public Date fechaPublicacion;
	
	
	public Articulo (ResultSet rs){
	  init(rs);
	  }
	  
	  public void init (ResultSet rs){
	  try {
	      idArticulo = rs.getInt("idarticulo");
	      titulo = rs.getString("titulo");
	      idAutor = rs.getInt("idautor");
	      estatus = rs.getBoolean("publicado");
	      textoArticulo = rs.getString("informacion");
	      fechaPublicacion = rs.getDate("fechapublicacion");
	      } catch (Exception e) {}
	   }
	   
	public Articulo(int id) {
		Conexion c = new Conexion();
		ResultSet rs = c.executeQuery("select * from Articulo where idarticulo = " + id);
		try {
			if(rs.next()) {
				init(rs);
				rs.close();
			} // else error		
			rs.close();
		} catch (Exception e){}
	}
}
