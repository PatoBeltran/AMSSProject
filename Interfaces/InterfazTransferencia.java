package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlExtraccion;

public class InterfazTransferencia extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlExtraccion ce;

  public void doGet(HttpServletRequest request,
      HttpServletResponse response)
    throws IOException {
    thisResponse = response;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Pagina Web de respuesta
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<META http-equiv=Content-Type content=\"text/html\">");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<TITLE>Banco AMSS</TITLE>");
    out.println("<h2>Cajero Electronico</h2>");
    out.println("<h3>Transferencia de Saldo</h3>");

    String operacion = request.getParameter("operacion");

    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarTransferencia();
    }else if(operacion.equals("validar")){
      validarCuenta();
    } else if (operacion.equals("transferencia")){    
      hacerTransferencia();
    }
  }
  public void iniciarTransferencia(){  
    out.println("<p>Indique los numeros de cuenta</p>");
    out.println("<form method=\"GET\" action=\"Transferencia\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
    out.println("<p> Cuenta  <input type=\"text\" name=\"cuenta1\" size=\"15\"></p>");
    out.println("<p> Cuenta  <input type=\"text\" name=\"cuenta2\" size=\"15\"></p>");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"menu.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");    
  }

  public void validarCuenta(){  
    ce = new ControlExtraccion();
    //La funcion trim() elimina espacios antes y despues del valor
    int cuenta1 = Integer.parseInt(thisRequest.getParameter("cuenta1").trim());
    int cuenta2 = Integer.parseInt(thisRequest.getParameter("cuenta2").trim());
    boolean existente1 = ce.validarCuenta(cuenta1);
    boolean existente2 = ce.validarCuenta(cuenta2);
    if (existente1 && existente2){
      out.println("<p>Indique la cantidad a transferir</p>");
      out.println("<form method=\"GET\" action=\"Transferencia\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"transferir\"/>");
      out.println("<input type=\"hidden\" name=\"cuenta1\" value=\"" + cuenta1 + "\"/>");
      out.println("<input type=\"hidden\" name=\"cuenta2\" value=\"" + cuenta2 + "\"/>");
      out.println("<p> Cantidad  <input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
      out.println("</form>");

      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
      out.println("</form>");

      out.println("</BODY>");
      out.println("</HTML>");    
    } else {
      iniciarTransferencia();
    }
  }

  public void hacerTransferencia(){  
    int cuenta1 = Integer.parseInt(thisRequest.getParameter("cuenta1").trim());
    int cuenta2 = Integer.parseInt(thisRequest.getParameter("cuenta2").trim());
    float cantidad = Float.valueOf(thisRequest.getParameter("cantidad").trim()).floatValue();
    boolean resultado = ce.transferirEfectivo(cuenta1,cuenta2,cantidad);
    if (resultado == true) {
      out.println("<p>Tome su efectivo.</p>");
      out.println("<p>Fue un place servirlo. Gracias por operar con nuestro banco.</p>");
      out.println("<p>Presione el boton para terminar.</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
    }else {
      out.println("<h3>Esa es una cantidad excesiva, indique una cantidad menor.</h3>");
      out.println("<p>Indique la cantidad a transferir</p>");
      out.println("<form method=\"GET\" action=\"Transferencia\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"transferir\"/>");
      out.println("<input type=\"hidden\" name=\"cuenta1\" value=\"" + cuenta1 + "\"/>");
      out.println("<input type=\"hidden\" name=\"cuenta2\" value=\"" + cuenta2 + "\"/>");
      out.println("<p> Cantidad  <input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");            
    }    
  } 
}
