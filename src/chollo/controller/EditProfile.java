package chollo.controller;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
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
 * Servlet implementation class EditProfile
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/EditProfile" })
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la
		// basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat");

		// Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();
		

		
		User u = (User) session.getAttribute("user");
		

			
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/editar_perfil.jsp");
		view.forward(request,response);	
			
		
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
		
		//Ley doy un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
			
			u.setEmail(request.getParameter("email"));
			u.setUsername(request.getParameter("username"));
			
			
			if (!request.getParameter("Newpass").equals("")) {
				logger.info("hollllllllllllllllllllaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + request.getParameter("Newpass") );
				if (u.comprobarPasword(request.getParameter("Oldpass"))) {
					u.setPassword(request.getParameter("Newpass"));
					
				}else{
					request.setAttribute("messages"," password incorrecta");
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/editar_perfil.jsp");
					view.forward(request,response);	
				}
				
			}
			session.removeAttribute("user");
			session.setAttribute("user", u);
			userDAO.save(u);
			response.sendRedirect("UserProfile");
		}
	}

}
