package chollo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
 * Servlet implementation class Registro
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/Registro" })
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
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
		logger.info("Handling POST PublicarChollo");
		
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();
		
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		User u = new User();
		
		u.setUsername(request.getParameter("userName"));
		u.setEmail(request.getParameter("email"));
		u.setPassword(request.getParameter("pass"));
		
		if (userDAO.get(u.getUsername()) != null) {
			
		}
			
		
		
		

		

	}

}
