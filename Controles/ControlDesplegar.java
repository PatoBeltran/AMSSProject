package controles;

import entidades.*;
import java.sql.Date;

public class ControlDesplegar{
  Articulo articulo;
  
  public ControlDesplegar(){
   articulo = new Articulo();
  }
  
  public String getTexto(int idArt){
    return articulo.getInformacionArticulo(idArt);
  }
  public String getTitulo(int idArt){
    return articulo.getTituloArticulo(idArt);
  }

  public int getAutorIDArticulo(int idArt){
  return articulo.getAutorIDArticulo(idArt);

  }
  
  
}
