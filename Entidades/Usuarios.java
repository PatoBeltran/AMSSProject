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
      stmt.executeQuery("SELECT nombre FROM usuarios WHERE nombre = '" + nombre + "';");
      ResultSet rs = stmt.getResultSet();
      return (rs.next());//Va al primer registro si lo hay
    } catch (SQLException e) { System.out.println ("Error reading database validarPorNombre"); }
    return false;
  }

  public boolean login(String nombre, String password){
    try {
      stmt.executeQuery("SELECT password FROM usuarios WHERE nombre = '" + nombre + "'");
      ResultSet rs = stmt.getResultSet();
      if(rs.next()){
        String passConf = rs.getString("password");
        return (password.equals(passConf) );
      } else {
       return false;
      }
    } catch (SQLException e) { System.out.println ("Error reading database login " + e); }
    return false;
  }
  public boolean modificarUsuario(String tipo, int idUsuario, String nombre, String email, String pass){
    try{
      String s = "UPDATE usuarios SET tipo = '" + tipo + "' WHERE idUsuario = " + idUsuario;
      String d = "UPDATE usuarios SET nombre = '" + nombre + "' WHERE idUsuario = " + idUsuario;
      String f = "UPDATE usuarios SET email = '" + email + "' WHERE idUsuario = " + idUsuario;
      String g = "UPDATE usuarios SET password = '" + pass + "' WHERE idUsuario = " + idUsuario;
      stmt.executeUpdate(s);
      stmt.executeUpdate(d);
      stmt.executeUpdate(f);
      stmt.executeUpdate(g);
      return true;
    } catch (SQLException e) { System.out.println ("Error reading database edit user " + e); }
    return false;
  }

  public boolean suscrito(int IDUsuario){
    try {
      stmt.executeQuery("SELECT IDUsuario FROM usuarios WHERE IDUsuario = " + IDUsuario );
      ResultSet rs = stmt.getResultSet();
      rs.next();
        return true;
    } catch (SQLException e) { System.out.println ("Error reading database login " + e); }
    return true;
  }

  public boolean validarPorID(int IDNumber){
    try {
      stmt.executeQuery("SELECT IDUsuario FROM usuarios WHERE IDUsuario = " + IDNumber);
      ResultSet rs = stmt.getResultSet();
      if (rs.next()) { //Va al primer registro si lo hay
        int nIDNumber = rs.getInt ("IDUsuario");
        rs.close(); 
        return( IDNumber == nIDNumber );
      }else{ return false;}
    } catch (SQLException e) {}
    return false;
  }


  public String getTipo(int IDNumber){
    String tipo = "none"; 
    try {
      stmt.executeQuery("SELECT tipo FROM usuarios WHERE IDNumber = " + IDNumber);
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

  public int getUserID(String userName){
    int IDNumber = 0; 
    try {
      stmt.executeQuery("SELECT IDUsuario FROM usuarios WHERE nombre = '" + userName + "'");
      ResultSet rs = stmt.getResultSet();
      rs.next(); //Va al registro ya validado
      IDNumber = rs.getInt("IDUsuario");
      rs.close();
      return(IDNumber);
    } catch (SQLException e) {System.out.println ("Cannot getSaldo()" + e);}
    return IDNumber;
  }

  public String getUserName(int userID){
    String result;
    try {
      stmt.executeQuery("SELECT nombre FROM usuarios WHERE IDUsuario = " + userID);
      ResultSet rs = stmt.getResultSet();
      rs.next(); //Va al registro ya validado
      result = rs.getString("nombre");
      rs.close();
      return(result);
    } catch (SQLException e) {System.out.println ("Cannot getUserName" + e);}
    return "";
  }


  public boolean createAccount(String userName, String password){
    if(!validarPorNombre(userName))
    {
      try{
        String updateInsert = "INSERT INTO usuarios (nombre, password) VALUES " +  
          "('" + userName + "' , '" + password + "')";

        stmt.executeUpdate (updateInsert);
        return true;
      } catch (Exception e) { System.out.println ("Cannot update database" + e);}
    }
    return false;
  }

  public void votar(int userID, int articuloID, int favor){
    if(!validarPorID(userID))
    {
      try{
        String updateInsert = "REPLACE INTO votos VALUES " +  
          "(" + userID + "," + articuloID + ", "+ favor + ")";

        stmt.executeUpdate (updateInsert);
      } catch (Exception e) { System.out.println ("Cannot update database" + e);}
    }
  }


  public boolean getVoto(int userID, int articuloID){
    try {
      stmt.executeQuery("SELECT favor FROM votos WHERE IDUsuario = " + userID + " and idArticulo = " + articuloID);
      ResultSet rs = stmt.getResultSet();
      if(rs.next())
      {
      return rs.getInt("favor")==0 ? false : true;
      } else {
        rs.close();
        return false;
      }
      
    } catch (SQLException e) {System.out.println ("Cannot getUserName" + e);}
    return false;
  }

  ////legacy///
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
      stmt.executeQuery("SELECT saldo FROM cuenta WHERE ncuenta = " +ncuenta);
      ResultSet rs = stmt.getResultSet();
      rs.next(); //Va al registro ya validado
      saldo = rs.getFloat("saldo");
      rs.close();
      return(saldo);
    } catch (SQLException e) {System.out.println ("Cannot getSaldo()" + e);}
    return saldo;
  }

}
