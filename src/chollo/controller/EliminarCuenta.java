package chollo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.UserDAO;
import chollo.model.Chollo;
import chollo.model.User;

/**
 * Servlet implementation class EliminarCuenta
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/EliminarCuenta" })
public class EliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCuenta() {
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
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la
		// basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat");

		// Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();

		// Ley doy un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);

		// Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
//			List<Chollo> c = cholloDAO.getAllByUser(u.getId());
//			for (int i = 0; i < c.size(); i++) {
//				c.get(i).setIdu(null);
//				cholloDAO.save(c.get(i));
//			}
			
			
			userDAO.delete(u.getId());
			response.sendRedirect("Logout");
			
			
			
		}else{
			response.sendRedirect("WhatAtreat");
		}
	}

}
