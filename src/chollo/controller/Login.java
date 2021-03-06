package chollo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.UserDAO;
import chollo.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Handling GET");
		
		//Obtengo la base de datos
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Creo un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		HttpSession session = request.getSession();//obtengo la sesion de la requeste que ha hecho el usuario

		
		RequestDispatcher view = request.getRequestDispatcher("pages/Registro.jsp");
		view.forward(request,response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Handling Post");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		String userName = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		User u = userDAO.get(userName);
		
		if ((u!=null) && (u.getPassword().equals(pass))) { // usuario en base de datos. Lo devuelvo a la pagina principal como usuario registrado.
				HttpSession session = request.getSession();//obtengo la sesion de la requeste que ha hecho el usuario
				session.setAttribute("user", u);
				logger.info("USUARIO CONECTADO");
				response.sendRedirect("pages/Index.html");
			}else{
				request.setAttribute("messages","Nombre de usuario o password incorrecto");
				RequestDispatcher view = request.getRequestDispatcher("pages/Registro.jsp");
				view.forward(request,response);	
			}

	}

}
