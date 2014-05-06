package controles;
import entidades.*;
import java.sql.Date;

public class ControlAdmin{
    Usuarios cuenta;
    
    public ControlAdmin(){
    cuenta = new Usuarios();
    }
    
    public void modificarUsuario(String tipo, int idUsuario, String nombre, Date fechaNacimiento, String email, String pass){
      try{
      String s = "UPDATE usuario SET tipo = " + tipo + " WHERE idUsuario = " + idUsuario;
      String d = "UPDATE usuario SET nombre = " + nombre + " WHERE idUsuario = " + idUsuario;
      String e = "UPDATE usuario SET fechaDeNacimiento = " + fechaNacimiento + " WHERE idUsuario = " + idUsuario;
      String f = "UPDATE usuario SET email = " + email + " WHERE idUsuario = " + idUsuario;
      String g = "UPDATE usuario SET password = " + pass + " WHERE idUsuario = " + idUsuario;
      stmt.executeUpdate(s);
      stmt.executeUpdate(d);
      stmt.executeUpdate(e);
      stmt.executeUpdate(f);
      stmt.executeUpdate(g);
      }
      
    }
}
    
