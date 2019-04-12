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
		
		request.setAttribute("emailProblem", session.getAttribute("emailProblem"));
		request.setAttribute("usernameProblem", session.getAttribute("usernameProblem"));
		request.setAttribute("messages", session.getAttribute("messages"));
		
		session.removeAttribute("emailProblem");
		session.removeAttribute("usernameProblem");
		session.removeAttribute("messages");
			
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
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String Newpass = request.getParameter("Newpass");
		
		boolean  bandera = false;
		
		if (!email.equals("") || !username.equals("") || !Newpass.equals("") ) {
			
			if (!email.equals("")) {
				if (userDAO.getEmail(email)==null) {
					u.setEmail(email);
					bandera = true;
				}else{
					session.setAttribute("emailProblem","El correo al que intenta cambiar ya existe");
				}
			}
			
			if (!username.equals("")) {
				if (userDAO.getUserName(username)==null) {
					u.setUsername(username);
					bandera = true;
				}else{
					session.setAttribute("usernameProblem","El nombre de usuario al que intenta cambiar ya existe");
				}
			}
			
			if (!Newpass.equals("")) {
				
				if (u.comprobarPasword(request.getParameter("Oldpass"))) {
					u.setPassword(request.getParameter("Newpass"));
					bandera = true;
				}else{
					session.setAttribute("messages"," password incorrecta");
				}
			}
			
		}
		
		if (bandera == true) {
			userDAO.save(u);
			u= userDAO.get(u.getUsername());
			session.setAttribute("user", u);
		}
		
		response.sendRedirect("EditProfile");
	}

}
