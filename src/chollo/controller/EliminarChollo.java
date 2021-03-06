package chollo.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.model.User;

/**
 * Servlet implementation class EliminarChollo
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/EliminarChollo" })
public class EliminarChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarChollo() {
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
		
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat");
		HttpSession session = request.getSession();
		// Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
			cholloDAO.delete(Long.parseLong(request.getParameter("cholloid")));
		}
		
		response.sendRedirect("whatAtreat");
		
	}

}
