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

import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.UserDAO;
import chollo.model.User;

/**
 * Servlet implementation class ConfirmarRegistro
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/ConfirmarRegistro" })
public class ConfirmarRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtengo la base de datos
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		HttpSession session = request.getSession();
		
		request.setAttribute("username", session.getAttribute("username"));
		request.setAttribute("email", session.getAttribute("email"));
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/registroConfirmado.jsp");
		view.forward(request,response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();
		
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		User u = new User();
		
		u.setUsername((String) session.getAttribute("username"));
		u.setEmail((String) session.getAttribute("email"));
		u.setPassword((String) session.getAttribute("pass"));
		
		userDAO.add(u);
		session.setAttribute("user", u);
		
		session.removeAttribute("username");
		session.removeAttribute("email");
		session.removeAttribute("pass");
		response.sendRedirect("whatAtreat");

		
	}

}
