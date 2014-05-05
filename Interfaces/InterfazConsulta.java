package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlExtraccion;

public class InterfazConsulta extends HttpServlet {
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
    out.println("<h3>Consultar Saldo</h3>");

    String operacion = request.getParameter("operacion");

    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarConsulta();
    }else if(operacion.equals("validar")){
      validarCuenta();
    } else if (operacion.equals("saldo")){    
      hacerConsulta();
    }
  }
  public void iniciarConsulta(){  
    out.println("<p>Indique el numero de cuenta</p>");
    out.println("<form method=\"GET\" action=\"Consulta\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"validar\"/>");
    out.println("<p> Cuenta  <input type=\"text\" name=\"cuenta\" size=\"15\"></p>");
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
    int cuenta = Integer.parseInt(thisRequest.getParameter("cuenta").trim());
    boolean existente = ce.validarCuenta(cuenta);
    if (existente){
      out.println("<p>Cuenta Valida</p>");
      out.println("<form method=\"GET\" action=\"Consulta\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"saldo\"/>");
      out.println("<input type=\"hidden\" name=\"cuenta\" value=\"" + cuenta + "\"/>");
      out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B1\"></p>");
      out.println("</form>");

      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
      out.println("</form>");

      out.println("</BODY>");
      out.println("</HTML>");    
    } else {
      iniciarConsulta();
    }
  }

  public void hacerConsulta(){  
    int cuenta = Integer.parseInt(thisRequest.getParameter("cuenta").trim());
    float resultado = ce.consultaSaldo(cuenta);
    out.println("<p>Su cuenta tiene: " + resultado + "</p>");
    out.println("<p>Fue un place servirlo. Gracias por operar con nuestro banco.</p>");
    out.println("<p>Presione el boton para terminar.</p>");

    out.println("<form method=\"GET\" action=\"index.html\">");
    out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
    out.println("</form>");
    out.println("</BODY>");
    out.println("</HTML>");
  } 
}
