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
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.ShopDAO;
import chollo.dao.UserDAO;
import chollo.model.Category;
import chollo.model.Chollo;
import chollo.model.Shop;
import chollo.model.User;

/**
 * Servlet implementation class PublicarChollo
 */
@WebServlet(description = "Servlet para lanzar la pagina de inicio de la app de chollos", urlPatterns = { "/PublicarChollo" })
public class PublicarChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicarChollo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Handling GET");
		
		// servlet context es como un servlet de tomcat, al inciarse tomcat carga la basde datos y la añade como atributo
		Connection conn = (Connection) getServletContext().getAttribute("dbWhat"); 
		
		//Agrego una nueva sesion a la requeste que ha hecho el usuario
		HttpSession session = request.getSession();
		
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		//Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		
		
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		List <String> shopList = shopDAO.getAllShopName();
		
		CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
		categoryDAO.setConnection(conn);
		List<String> categoryList = categoryDAO.getAllCategoryName();
		
		request.setAttribute("shop", shopList);
		request.setAttribute("category", categoryList);
		
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/PublicarChollo.jsp");
		view.forward(request,response);	
		
		
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
		User u = (User) session.getAttribute("user");
		if (u!=null) {
			CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
			categoryDAO.setConnection(conn);
			ShopDAO shopDAO = new JDBCShopDAOImpl();
			shopDAO.setConnection(conn);
			CholloDAO cholloDAO = new JDBCCholloDAOImpl();
			cholloDAO.setConnection(conn);
			
			Chollo c = new Chollo ();
			//TODO ME FALTA LA ID DEL Chollo que ni idea
			c.setTitle(request.getParameter("titulo"));
			c.setDescription(request.getParameter("descripcion"));
			c.setLink(request.getParameter("link"));
			c.setPrice(Long.parseLong(request.getParameter("precio")));
			c.setIds(shopDAO.getIdByName(request.getParameter("shop")));
			c.setIdu(u.getId());
			c.setLikes(0);
			c.setSoldout(0);
			c.setImagen(request.getParameter("imagen"));
			//Ya esta todo asi que ahora tenemos que comprobar que ninguno de los parametros que ha introducido el usuario contiene un caracter especial			
			
			
			//Añado el chollo a la base de datos
			logger.info("Venga vamos a consegirlo a ver si podemos");
			cholloDAO.add(c);
			response.sendRedirect("whatAtreat");
			
			
		}
		
	}

}
