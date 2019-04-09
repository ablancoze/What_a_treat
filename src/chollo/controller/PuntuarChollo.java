
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
import chollo.dao.JDBCLikesDAO;
import chollo.dao.LikesDAO;
import chollo.model.Likes;
import chollo.model.User;

/**
 * Servlet implementation class PuntuarChollo
 */
@WebServlet("/PuntuarChollo")
public class PuntuarChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PuntuarChollo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	
		LikesDAO likesDAO = new JDBCLikesDAO();
		likesDAO.setConnection(conn);
		
		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
			
			Long cholloid = Long.parseLong(request.getParameter("chollo"));
			Long userid = u.getId();
			Long like = Long.parseLong(request.getParameter("like"));
			Likes l = new Likes();
			l.setIdc(cholloid);
			l.setIdu(userid);
			l.setLike(like);
			
			Likes l2=likesDAO.getByChollo(cholloid, userid);
			
			if (l2!=null) {
				likesDAO.save(l);
			}else {
				likesDAO.add(l);
			}
			
			
		}
			response.sendRedirect(request.getRequestURL().substring(0, 35)+"/VerChollo?cholloid=" + request.getParameter("chollo"));
			
		
		
	}

}
