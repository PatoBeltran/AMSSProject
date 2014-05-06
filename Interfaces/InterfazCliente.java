package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.*;

public class InterfazCliente extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlAutenticacion ca;
  
   public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();

    header(request);

    String operacion = request.getParameter("operacion");
    String dentro = request.getParameter("dentro");

    if(dentro == null) {
      if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
        landingContent();
      } else if(operacion.equals("entrar")){
        iniciarSesion();
      } else if(operacion.equals("crear")){
        crearCuenta();
      } else if(operacion.equals("crearCuenta")){
        cuentaCreada(request);
      } else if(operacion.equals("entrarCuenta")){
        entrarCuenta(request);
      }
    } else if (dentro.equals("si")) {
      int id = Integer.parseInt(request.getParameter("user_id"));
      if(operacion == null) {
        verPerfil(id);
      } else if(operacion.equals("ver_archivo")) {
        //verArchivo();
      }
    }
    footer();
  }

  void verPerfil(int id){
    out.println("<div class='wrapper profile'>");
    out.println("<div class='container p-90 row'>");
    out.println("<div class='information col-9 row'>");
    out.println("<div class='photo col-3'><img src='http://drrop.it/files/536885ad423c4.jpg' class='responsive circular' /></div>");
    out.println("<div class='personal col-9 row'>");
    out.println("<div class='col-12 user-name row'>");
    out.println("<h1 class='col-6'>" + ca.getUserName(id) + "</h1>");
    out.println("<div class='col-6' style='text-align: right;'>");
    out.println("<h2 class='suscription'>" + "Suscrito" + "</h2>");
    out.println("<h4 class='expiration'>Expira: " + "1/1/10" + "</h4>");
    out.println("</div>");
    out.println("</div>");
    out.println("<div class='col-12 user-info row'>");
    out.println("<div clas='col-8'>");
    out.println("<h4>Informacion Personal</h4>");
    out.println("<p>" + "" + "</p>");
    out.println("</div>");
    out.println("<div class='col-4' style='text-align: right;'>");
    out.println("<a href='#' class='enter'>Editar Informacion</a>");
    out.println("<a href='#' class='enter'>Enviar Publicidad</a>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("<hr />");
    out.println("<div class='col-12 row'>");
    out.println("<h2>Publicaciones Recibidas</h2>");
    out.println("<div class='col-12 article-item row'>");
    out.println("<div class='col-4'><a href='#'>Titulo del articulo</a></div>");
    out.println("<div class='col-8'>Lorem ipsum dolor sit amet, maiores ornare ac fermentum, imperdiet ut vivamus a, nam lectus at nunc. Quam euismod sem, semper ut potenti pellentesque quisque.... Más</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("<div class='publicidad col-3 row'>");
    out.println("<div class='publi-item col-12'>");
    out.println("<p>Lorem ipsum dolor sit amet, maiores ornare ac fermentum, imperdiet ut vivamus a, nam lectus at nunc. Quam euismod sem, semper ut potenti pellentesque quisque. In eget sapien sed.</p>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
  }

  void landingContent() {
    out.println("<div class='wrapper landing'>");
    out.println("<div class='container p-90 row'>");
    out.println("<div class='col-6'></div>");
    out.println("<div class='col-6'>");
    out.println("<h1 class='sub-title'>Bienvenido a</h1>");
    out.println("<h2 class='main-title'>SEng Bytes & Bits</h2>");
    out.println("<div class='impact-section'>");
    out.println("<h4 class='call-to-action'>Se parte de la revista de software con mayor experiencia en el pais.</h4>");
    out.println("<div class='col-12 row'>");
    out.println("<div class='col-6'>");
    out.println("<a href='?operacion=entrar' class='enter'>Iniciar Sesion</a>");
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
    out.println("<h4 class='call-to-action'>Iniciar Sesion</h4>");
    out.println("<div class='col-12 row'>");
    out.println("<form method='GET' action='Cliente'>");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"entrarCuenta\"/>");
    out.println("<input type='text' name='usuario' id='usuario' value='' placeholder='Nombre de Usuario'>");
    out.println("<input type='password' name='password' id='password' value='' placeholder='Contrasena'>");
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
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"crearCuenta\"/>");
    out.println("<input type='text' name='usuario' id='usuario' value='' placeholder='Nombre de Usuario'>");
    out.println("<input type='password' name='password' id='password' value='' placeholder='Contrasena'>");
    out.println("<input type='password' name='password_confirmation' id='password_confirmation' value='' placeholder='Confirmar Contrasena'>");
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

  void cuentaCreada(HttpServletRequest request) {
    ca = new ControlAutenticacion();

    String username = request.getParameter("usuario");
    String password = request.getParameter("password");
    String password_confirmation = request.getParameter("password_confirmation");

    boolean valid = ca.createAccount(username, password, password_confirmation);
    int id;

    if(valid) {
      id = ca.getUserID(username);
      out.println("<div class='wrapper landing'>");
      out.println("<div class='container p-90 row'>");
      out.println("<div class='col-6'></div>");
      out.println("<div class='col-6 row'>");
      out.println("<h2 class='main-title'>SEng Bytes & Bits</h2>");
      out.println("<div class='impact-section col-12 row'>");
      out.println("<h4 class='call-to-action'>Bienvenido " + username + "</h4>");
      out.println("<div class='col-12 row perfect'>");
      out.println("<form method=\"GET\" action=\"Cliente\">");
      out.println("<input type=\"hidden\" name=\"user_id\" value=\"" + id + "\"/>");
      out.println("<input type=\"hidden\" name=\"dentro\" value=\"si\"/>");
      out.println("<input type=\"submit\" class='col-12 button alpha primary accept' value=\"Continuar\"name=\"B1\">");
      out.println("</form>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
    } else {
      crearCuenta();
    }
  }
  void entrarCuenta(HttpServletRequest request) {
    ca = new ControlAutenticacion();

    String username = request.getParameter("usuario");
    String password = request.getParameter("password");

    boolean valid = ca.entrar(username, password);
 
    int id;

    if(valid) {
      id = ca.getUserID(username);
      out.println("<div class='wrapper landing'>");
      out.println("<div class='container p-90 row'>");
      out.println("<div class='col-6'></div>");
      out.println("<div class='col-6 row'>");
      out.println("<h2 class='main-title'>SEng Bytes & Bits</h2>");
      out.println("<div class='impact-section col-12 row'>");
      out.println("<h4 class='call-to-action'>Bienvenido " + username + "</h4>");
      out.println("<div class='col-12 row perfect'>");
      out.println("<form method=\"GET\" action=\"Cliente\">");
      out.println("<input type=\"hidden\" name=\"user_id\" value=\"" + id + "\"/>");
      out.println("<input type=\"hidden\" name=\"dentro\" value=\"si\"/>");
      out.println("<input type=\"submit\" class='col-12 button alpha primary accept' value=\"Continuar\"name=\"B1\">");
      out.println("</form>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
    } else {
      System.out.println("AVALIABLE: " + valid + " : " + username + " : " + password);
      iniciarSesion();
    }
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
    out.println("<li>Adrían Lozano</li>");
    out.println("<li>Daniel Jauregui</li>");
    out.println("<li>Alexander Baumann</li>");
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

