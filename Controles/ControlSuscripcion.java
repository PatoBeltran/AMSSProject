package controles;
import entidades.Usuarios;

public class ControlSuscripcion {
  Usuarios cuenta;

  public ControlSuscripcion(){
    cuenta = new Usuarios(); //Asume que la instancia persiste durante la sesion
  }

  public boolean createAccount(String username, String password, String password_confirmation){
    if(password.equals(password_confirmation)) {
      return true;
    } else {
      return false;
    }
  }

  public int getUserID(String username) {
    return cuenta.getUserID(username);
  }

  // ======================== CODIGO DEL PROFE ==================
  //Valida si la cuenta existe en la base de datos
  public boolean validarCuenta(int ncuenta){
    return(cuenta.validarPorID(ncuenta));
  }

  //Implementa una regla de negocio;
  //no se puede extaer mas de $3000 en una transaccion
  public boolean extraerEfectivo(int ncuenta, float cantidad){
    if(cantidad < 3000.0f) {
      float saldo = cuenta.getSaldo(ncuenta);
      if (saldo > cantidad) {
        saldo = saldo - cantidad;
        cuenta.setSaldo(ncuenta,saldo);
        return true; //Transaccion aceptada
      }else {
        return false; //No hay fondos suficientes en la cuenta
      }
    } else {
      return false; //Cantidad demasiado alta
    }
  }

  public boolean transferirEfectivo(int ncuenta1, int ncuenta2, float cantidad){
    if(cantidad < 3000.0f) {
      float saldo1 = cuenta.getSaldo(ncuenta1);
      float saldo2 = cuenta.getSaldo(ncuenta2);
      if (saldo1 > cantidad) {
        saldo1 = saldo1 - cantidad;
        saldo2 = saldo2 + cantidad;
        cuenta.setSaldo(ncuenta1,saldo1);
        cuenta.setSaldo(ncuenta2,saldo2);
        return true; //Transaccion aceptada
      }else {
        return false; //No hay fondos suficientes en la cuenta
      }
    } else {
      return false; //Cantidad demasiado alta
    }
  }


  public float consultaSaldo(int ncuenta){
    return cuenta.getSaldo(ncuenta);
  }     
}
