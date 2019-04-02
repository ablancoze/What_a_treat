package chollo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.CholloDAO;
import chollo.dao.ComentarioDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCComentarioDAOImpl;
import chollo.dao.JDBCShopDAOImpl;
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.ShopDAO;
import chollo.dao.UserDAO;
import chollo.model.Chollo;
import chollo.model.Comentario;
import chollo.model.Shop;
import chollo.model.User;

/**
 * Servlet implementation class VerChollo
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/VerChollo" })
public class VerChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerChollo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Handling GET VerChollo ");
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
				Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
				
				//Agrego una nueva sesion a la requeste que ha hecho el usuario
				HttpSession session = request.getSession();
				
				//Ley doy un usuario y lo conecto con la base de datos
				UserDAO userDAO = new JDBCUserDAOImpl();
				userDAO.setConnection(conn);
				
				//Le doy los chollos y los conecyo con la base de datospara que pueda verlos
				CholloDAO cholloDAO = new JDBCCholloDAOImpl();
				cholloDAO.setConnection(conn);
				
				//Lo mismo con las tiendas \\CATEGORIAS.
				ShopDAO shopDAO = new JDBCShopDAOImpl();
				shopDAO.setConnection(conn);
				
				ComentarioDAO comentarioDAO = new JDBCComentarioDAOImpl();
				comentarioDAO.setConnection(conn);
				
				
				
				User u = (User) session.getAttribute("user");
				
				String cid = request.getParameter("cholloid");
				String uid = request.getParameter("userid");
				String sid= request.getParameter("shopid");
					
				List<Comentario>comentariosChollo;
				comentariosChollo=comentarioDAO.getComentAndUser(Long.parseLong(cid));
					
				Shop s = shopDAO.get(Long.parseLong(sid));
				Chollo c = cholloDAO.get(Long.parseLong(cid));
				User userPublicacion = userDAO.get(Long.parseLong(uid));
					
					
				request.setAttribute("chollo", c);
				request.setAttribute("userPublicacion", userPublicacion);
				request.setAttribute("shop", s);
				request.setAttribute("comentarios", comentariosChollo);
				
				
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/paginaChollo.jsp");
				view.forward(request,response);	
					
				
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
