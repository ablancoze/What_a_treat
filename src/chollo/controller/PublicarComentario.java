package chollo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.ComentarioDAO;
import chollo.dao.JDBCComentarioDAOImpl;
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.UserDAO;
import chollo.model.Comentario;
import chollo.model.User;

/**
 * Servlet implementation class PublicarComentario
 */
@WebServlet(description = "Servlet para lanzar la pagina de inicio de la app de chollos", urlPatterns = { "/PublicarComentario" })
public class PublicarComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicarComentario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Handling GET PublicarComentario");
		
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();
		
		
		ComentarioDAO comentarioDAO = new JDBCComentarioDAOImpl();
		comentarioDAO.setConnection(conn);
		
		User u = (User) session.getAttribute("user");
		
		
		String username=u.getUsername();
		String comentario=request.getParameter("comentario");
		String cid=request.getParameter("cid");
		
		Comentario c = new Comentario();
		
		c.setUsername(username);
		c.setCid(Long.parseLong(cid));
		c.setComentario(comentario);
		c.setPuntos(0);
		comentarioDAO.add(c);
		
		
		//TODO VAYA COSO FEO ESTO SE TIENE QUE PODER HACER DE OTRA MANERA
		response.sendRedirect(request.getRequestURL().substring(0, 35)+"/VerChollo?cholloid=" +request.getParameter("cid")+"&userid="+request.getParameter("uid")+"&shopid="+request.getParameter("sid"));
		
		
		
		
		
	}

}
