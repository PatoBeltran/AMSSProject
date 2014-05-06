package entidades;
import java.sql.*;
import java.io.*;

public class Usuarios {
   Connection conn;
   Statement stmt;

   public Usuarios(){
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/AMSS";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
      }catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }

   public boolean validarPorNombre(String nombre){
      try {
         stmt.executeQuery ("SELECT nombre FROM usuarios WHERE nombre = " + nombre);
         ResultSet rs = stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
            String nnombre = rs.getString("nombre");
            return( nnombre == nombre );
         }else{ return false;}
      } catch (SQLException e) { System.out.println ("Error reading database"); }
      return false;
   }

   public boolean validarPorId(int IdNumber){
      try {
         stmt.executeQuery ("SELECT IdUsuario FROM usuarios WHERE IdUsuario = " + IdNumber);
         ResultSet rs = stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
            int nIdNumber = rs.getInt ("IdUsuario");
            rs.close(); 
            return( IdNumber == nIdNumber );
         }else{ return false;}
      } catch (SQLException e) {}
      return false;
   }


   public String getTipo(int IdNumber){
      String tipo = "none"; 
      try {
         stmt.executeQuery ("SELECT tipo FROM usuarios WHERE IdNumber = " + IdNumber);
         ResultSet rs = stmt.getResultSet();
         rs.next(); //Va al registro ya validado
          tipo = rs.getString("tipo");
         rs.close();
         if (tipo == null) {
            tipo = "Sin tipo";           
         }
         return(tipo);
      } catch (SQLException e) {System.out.println ("Cannot getTipo" + e);}
      return tipo;
   }

   public int 


      
   public void agregar(int cuenta, String nombre, float saldo){
      try {
         String s = "INSERT INTO CUENTA (ncuenta, nombre, saldo)" +
                   " VALUES ("+ cuenta + " , '" + nombre + "', " + saldo + " )";
         System.out.println(s); 
         stmt.executeUpdate(s);
      }catch (Exception e) { System.out.println ("Cannot update database" + e ); }   
   }    
   
   public void setSaldo(int cuenta, float saldo){
      try {
         String s = "UPDATE cuenta SET saldo = " + saldo + " WHERE ncuenta = " + cuenta;
         stmt.executeUpdate(s);
      } catch (SQLException e) {System.out.println ("Cannot execute disposicion()" + e);}
   }

   public float getSaldo(int ncuenta){
      float saldo = 0.0f; 
      try {
         stmt.executeQuery ("SELECT saldo FROM cuenta WHERE ncuenta = " +ncuenta);
         ResultSet rs = stmt.getResultSet();
         rs.next(); //Va al registro ya validado
         saldo = rs.getFloat("saldo");
         rs.close();
         return(saldo);
      } catch (SQLException e) {System.out.println ("Cannot getSaldo()" + e);}
      return saldo;
   }
   
}
