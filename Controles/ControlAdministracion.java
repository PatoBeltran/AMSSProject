package controles;
import entidades.*;
import java.sql.Date;

public class ControlAdministracion{
  Usuarios cuenta;

  public ControlAdministracion(){
    cuenta = new Usuarios();
  }

  public boolean modificarUsuario(String tipo, int idUsuario, String nombre, String email, String pass){
    return cuenta.modificarUsuario(tipo, idUsuario, nombre, email, pass);
  }
}
