package chollo.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCShopDAOImpl;
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.ShopDAO;
import chollo.dao.UserDAO;
import chollo.model.Chollo;
import chollo.model.Shop;
import chollo.model.User;
import chollo.resources.exceptions.CustomBadRequestException;

@Path("/shop")
public class ShopResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Shop> getShopJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		HttpSession session = request.getSession();
		
		List<Shop> shoptList = shopDAO.getAll();
		
		return shoptList; 
	  }
	  
	  @GET
	  @Path("/{shopid: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Shop getShopJSON(@PathParam("shopid") long shopid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		HttpSession session = request.getSession();
		
		Shop shop = shopDAO.get(shopid);
		
		return shop; 
	  }
}
