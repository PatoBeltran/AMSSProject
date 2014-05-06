package entidades;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.sql.Date;

public class iSuscripcion {
	public int idSuscripcion;
	public int idSuscriptor;
	public Date fechaInicio;
	public Date fechaFin;
	
	public iSuscripcion(ResultSet rs) {
		init(rs);
	}
	
	public void init(ResultSet rs) {
		try {
			idSuscripcion = rs.getInt("idsuscripcion");
			idSuscriptor = rs.getString("idsuscriptor");
			fechaInicio = rs.getDate("fechadeinicio");
			fechaFin = rs.getDate("fechadefin");
		} catch (Exception e){}
	}
	
	public NotaActualidad(int id) {
		Conexion c = new Conexion();
		ResultSet rs = c.executeQuery("select * from Nota where idNota = " + id);
		try {
			if(rs.next()) {
				init(rs);
				rs.close();
			} // else error		
			rs.close();
		} catch (Exception e){}
	}
	
}
