package controles;
import entidades.Articulo;
import entidades.Usuarios;


public class ControlRevision {
  Usuarios cuenta;
  Articulo articulo;

  public ControlRevision(){
    articulo = new Articulo(); //Asume que la instancia persiste durante la sesion
    cuenta = new Usuarios();
  }

  public void publicar(int articuloID) {
    articulo.publicar(articuloID, 1);
  }

  public void despublicar(int articuloID) {
    articulo.publicar(articuloID, 0);
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

  public void editArticuloInfo(int idArticulo, String nInfo){
    articulo.editArticuloInfo(idArticulo, nInfo);
  }

  public void editArticuloTitulo(int idArticulo, String nTitulo){
    articulo.editArticuloTitulo(idArticulo, nTitulo);
  }

}