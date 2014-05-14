package controles;
import entidades.Articulo;

public class ControlAprobar {
  Articulo cuenta;

  public ControlAprobar(){
    cuenta = new Articulo(); //Asume que la instancia persiste durante la sesion
  }

  public void publicar(int articuloID) {
    cuenta.publicar(articuloID, 1);
  }

  public void despublicar(int articuloID) {
    cuenta.publicar(articuloID, 0);
  }


}