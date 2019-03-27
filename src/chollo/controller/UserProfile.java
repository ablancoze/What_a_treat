package chollo.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.media.jfxmedia.logging.Logger;

import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.UserDAO;
import chollo.model.User;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/whatAtreat" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtengo la base de datos
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Creo un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		HttpSession session = request.getSession();//obtengo la sesion de la requeste que ha hecho el usuario
		
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		
		
		User u = (User) session.getAttribute("user");
		
		request.setAttribute("user", u);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
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
