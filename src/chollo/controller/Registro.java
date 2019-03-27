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
		logger.info("Handling GET");
		
		//Obtengo la base de datos
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Creo un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		HttpSession session = request.getSession();//obtengo la sesion de la requeste que ha hecho el usuario

		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
		view.forward(request,response);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Handling Post");
		//Obtengo la base de datos
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Creo un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		
		User u = userDAO.get(request.getParameter("user"));
		
		String fallo;
		
		if (u!=null) { // usuario en base de datos. Lo devuelvo a la pagina principal como usuario registrado.
			if (u.comprobarPasword(request.getParameter("pass"))){
				HttpSession session = request.getSession();//obtengo la sesion de la requeste que ha hecho el usuario
				session.setAttribute("user", u);
				logger.info("USUARIO CONECTADO");
				response.sendRedirect("whatAtreat"); // fallo aqui no se porque.
			}else{
				fallo="";
				request.setAttribute("fallo",fallo);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
				view.forward(request,response);	
			}
		}else{
			fallo="";
			request.setAttribute("fallo",fallo);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
			view.forward(request,response);	
		}
	}

}
