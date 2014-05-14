package controles;
import entidades.Usuarios;

public class ControlVoto {
  Usuarios cuenta;

  public ControlVoto(){
    cuenta = new Usuarios(); //Asume que la instancia persiste durante la sesion
  }

  public void enContra(int userID, int articuloID) {
    cuenta.votar(userID, articuloID, 0);
  }

  public void aFavor(int userID, int articuloID) {
    cuenta.votar(userID, articuloID, 1);
  }

  public boolean getVoto(int userID, int articuloID){
    return cuenta.getVoto(userID, articuloID);
  }

}