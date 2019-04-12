package chollo.controller;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.CategoryDAO;
import chollo.dao.CholloDAO;
import chollo.dao.JDBCCategoryDAOImpl;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCShopDAOImpl;
import chollo.dao.ShopDAO;
import chollo.model.Category;
import chollo.model.Chollo;
import chollo.model.Shop;
import chollo.model.User;

/**
 * Servlet implementation class EditChollo
 */
@WebServlet(description = "Servlet para el registro de usuarios", urlPatterns = { "/EditChollo" })
public class EditChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditChollo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();
		
		//Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		
		CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
		categoryDAO.setConnection(conn);
		

		
		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
			String sid = request.getParameter("id");
			long id = Long.parseLong(sid);
			List <Shop> shopList = shopDAO.getAll();
			List <Category> categoryList = categoryDAO.getAll();
			Chollo c =  cholloDAO.get(id);
			session.setAttribute("chollo", c);
			String shop = shopDAO.getNameById(c.getIds());
			request.setAttribute("user", u);
			request.setAttribute("shopList", shopList);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("tienda", shop);
			request.setAttribute("chollo", c);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/editar_chollo.jsp");
			view.forward(request,response);	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat");

		// Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();

		// Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);

		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);

		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
			Chollo c = (Chollo) session.getAttribute("chollo");
			
			c.setTitle(request.getParameter("titulo"));
			c.setDescription(request.getParameter("descripcion"));
			
			if (!request.getParameter("precio").equals("")) {
				c.setPrice(Long.parseLong(request.getParameter("precio")));
			}
			
			if (!request.getParameter("shop").equals("")) {
				c.setIds(Long.parseLong(request.getParameter("shop")));
			}
			
			if (!request.getParameter("soldout").equals("")) {
				if (Integer.parseInt(request.getParameter("soldout"))==1) {
					c.setSoldout(1);
				}else {
					c.setSoldout(0);
				}
			}

			
			c.setImagen(request.getParameter("imagen"));
			cholloDAO.save(c);
			session.removeAttribute("chollo");
			response.sendRedirect("UserProfile");
		}
				
				
	}

}
