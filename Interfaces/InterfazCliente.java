package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlExtraccion;

public class InterfazCliente extends HttpServlet {
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

    header(request);

    String operacion = request.getParameter("operacion");
    String dentro = request.getParameter("dentro");


    if(dentro == "1") {
      if(operacion == null) {
      } else if(operacion.equals("ver_archivo")) {
        //verArchivo();
      }
    } else {
      if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
        landingContent();
      } else if(operacion.equals("entrar")){
        iniciarSesion();
      } else if (operacion.equals("crear")){
        crearCuenta();
      }
    }
    footer();
  }

  void landingContent() {
    out.println("<div class='wrapper landing'>");
    out.println("<div class='container p-90 row'>");
    out.println("<div class='col-6'></div>");
    out.println("<div class='col-6'>");
    out.println("<h1 class='sub-title'>Bienvenido a</h1>");
    out.println("<h2 class='main-title'>SEng Bytes & Bits</h2>");
    out.println("<div class='impact-section'>");
    out.println("<h4 class='call-to-action'>Se parte de la revista de software con mayor experiencia en el país.</h4>");
    out.println("<div class='col-12 row'>");
    out.println("<div class='col-6'>");
    out.println("<a href='?operacion=entrar' class='enter'>Iniciar Sesión</a>");
    out.println("</div>");
    out.println("<div class='col-6'>");
    out.println("<a href='?operacion=crear' class='enter'>Crear Cuenta</a>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
  }
  void iniciarSesion() {
    out.println("<div class='wrapper landing'>");
    out.println("<div class='container p-90 row'>");
    out.println("<div class='col-6'></div>");
    out.println("<div class='col-6 row'>");
    out.println("<h1 class='sub-title'>Bienvenido a</h1>");
    out.println("<h2 class='main-title'>SEng Bytes & Bits</h2>");
    out.println("<div class='impact-section col-12 row'>");
    out.println("<h4 class='call-to-action'>Iniciar Sesión</h4>");
    out.println("<div class='col-12 row'>");
    out.println("<form method='GET' action='Cliente'>");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"entrar\"/>");
    out.println("<input type='text' name='email' id='email' value='' placeholder='Correo Electrónico'>");
    out.println("<input type='text' name='password' id='password' value='' placeholder='Contraseña'>");
    out.println("<input type=\"submit\" class='col-6 button alpha primary accept' value=\"Entrar\"name=\"B1\">");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"index.html\">");
    out.println("<p><input type=\"submit\" class='col-6 button alpha danger cancel' value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
  }
   void crearCuenta() {
    out.println("<div class='wrapper landing'>");
    out.println("<div class='container p-90 row'>");
    out.println("<div class='col-6'></div>");
    out.println("<div class='col-6 row'>");
    out.println("<h1 class='sub-title'>Bienvenido a</h1>");
    out.println("<h2 class='main-title'>SEng Bytes & Bits</h2>");
    out.println("<div class='impact-section col-12 row'>");
    out.println("<h4 class='call-to-action'>Crear Cuenta</h4>");
    out.println("<div class='col-12 row'>");
    out.println("<form method='GET' action='Cliente'>");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"entrar\"/>");
    out.println("<input type='text' name='email' id='email' value='' placeholder='Correo Electrónico'>");
    out.println("<input type='text' name='password' id='password' value='' placeholder='Contraseña'>");
    out.println("<input type='text' name='password_confirmation' id='password_confirmation' value='' placeholder='Confirmar Contraseña'>");
    out.println("<input type=\"submit\" class='col-6 button alpha primary accept' value=\"Crear\"name=\"B1\">");
    out.println("</form>");

    out.println("<form method=\"GET\" action=\"index.html\">");
    out.println("<p><input type=\"submit\" class='col-6 button alpha danger cancel' value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
  }

  void header(HttpServletRequest request) {
    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("<!--includes meta tags, title and more header definitions-->");
    out.println("<meta charset='utf-8'>");
    out.println("<meta http-equiv=Content-Type content=\"text/html\">");
    out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
    out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no'>");
    out.println("<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>");
    out.println("<title>SEng Bytes & Bits</title>");

    out.println("<!-- Furatto core CSS -->");
    out.println("<link href='http://icalialabs.github.io/furatto/dist/css/furatto.css' rel='stylesheet' />");
    
    out.println("<!-- Main app CSS -->");
    out.println("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/styles/main.css' />");

    out.println("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->");
    out.println("<!--[if lt IE 9]>");
    out.println("<script src='https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js'></script>");
    out.println("<script src='https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js'></script>");
    out.println("<![endif]-->");
    out.println("</head>");

    out.println("<body>");
  }

  void footer() {
    out.println("<div class='footer'>");
    out.println("<div class='general-info'>");
    out.println("<div class='row'>");
    out.println("<p>Handcrafted by <a href='#'>TeamSEngBytesNBits</a></p>");
    out.println("<ul class='navigation inline'>");
    out.println("<li>Patricio Beltrán</li>");
    out.println("<li>| Adrían Lozano</li>");
    out.println("<li>| Daniel Jauregui</li>");
    out.println("<li>| Alexander Baumann</li>");
    out.println("</ul>");
    out.println("<br>");
    out.println("<ul class='navigation inline'>");
    out.println("<li class='header'>Currently v1.0.0</li>");
    out.println("</ul>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");

    out.println("<!--includes javascript at the bottom so the page loads faster-->");
    out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>");
    out.println("<script type='text/javascript' charset='utf-8' src='http://icalialabs.github.io/furatto/dist/js/furatto.js'></script>");
    out.println("</body>");
    out.println("</html>");
  }
}

