package chollo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.LikesDAO;
import chollo.dao.UserDAO;
import chollo.model.Category;
import chollo.model.Chollo;
import chollo.model.Shop;
import chollo.model.User;
import chollo.util.Triplet;
import chollo.dao.CategoryDAO;
import chollo.dao.ChollosCategoryDAO;
import chollo.dao.JDBCCategoryDAOImpl;
import chollo.dao.JDBCChollosCategoryDAOImpl;
import chollo.dao.JDBCLikesDAO;
import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCShopDAOImpl;
import chollo.dao.ShopDAO;



/**
 * Servlet implementation class whatAtreat
 */
@WebServlet(description = "Servlet para lanzar la pagina de inicio de la app de chollos", urlPatterns = { "/whatAtreat" })
public class WhatAtreat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WhatAtreat() {
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
		
		//Ley doy un usuario y lo conecto con la base de datos
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		//Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		//Lo mismo con las tiendas \\CATEGORIAS.
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		
		//Le doy las tiendas de las que hay chollos
		CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
		categoryDAO.setConnection(conn);
		
		//...
		ChollosCategoryDAO chollosCategoryDAO = new JDBCChollosCategoryDAOImpl();
		chollosCategoryDAO.setConnection(conn);
		
		
		LikesDAO likesDAO = new JDBCLikesDAO();
		likesDAO.setConnection(conn);
		
		
		List<Category> categoryList = categoryDAO.getAll();
		
		List<Shop> shoptList = shopDAO.getAll();
		
		//Lista con todos los chollos que hay en base de datos
		List<Chollo> cholloList = cholloDAO.getAll();
		
		Iterator<Chollo> itCholloList = cholloList.iterator();
		
		
		List<Triplet<Chollo, User, Shop>> chollosUserShopList = new ArrayList<Triplet<Chollo, User, Shop>>();
		
		//NI IDEA
		while(itCholloList.hasNext()) {
			Chollo chollo = (Chollo) itCholloList.next();
			User user = userDAO.get(chollo.getIdu());
			if(user==null) {
				user=userDAO.get(0);
			}
			
			Shop shop = shopDAO.get(chollo.getIds());

			chollosUserShopList.add(new Triplet<Chollo, User, Shop>(chollo,user,shop));
		}
		
		//Lista con los usuarios de la pagina de chollos
		List<User> listUser = new ArrayList<User>();
		listUser = userDAO.getAll();
		
		//NI IDEA
		Iterator<User> itUser = listUser.iterator();
		
		
		Map<User,List<Chollo>> userChollosMap = new HashMap<User,List<Chollo>>();
		
		while(itUser.hasNext()) {
			User user = itUser.next();
			cholloList = cholloDAO.getAllByUser(user.getId());
			userChollosMap.put(user, cholloList);
		}
		
		List<Chollo> ListaChollosHOT = cholloDAO.getTresChollosHot();
		
		
		//Almaceno en la requestes la lista de chollos 
		request.setAttribute("chollosList",chollosUserShopList);
		//Tambien guardo el map de usuarios.
		request.setAttribute("usersMap", userChollosMap);
		
		
		request.setAttribute("chollosHot", ListaChollosHOT);
		request.setAttribute("ListaCategorias", categoryList);
		request.setAttribute("ListaTiendas", shoptList);
		User u = (User) session.getAttribute("user");
		
		if (u!=null) {
			request.setAttribute("user", u);
		}
		
		session.setAttribute("chollosHot", ListaChollosHOT);
		session.setAttribute("ListaCategorias", categoryList);
		session.setAttribute("ListaTiendas", shoptList);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/whatatreat.jsp");
		view.forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
