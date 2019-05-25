package chollo.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.model.Chollo;
import chollo.model.User;

@Path("/chollosHot")
public class HotChollosResource {
	
	@Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Chollo> getCholloHotJSON (@Context HttpServletRequest request){
		  
		  Connection conn = (Connection) sc.getAttribute("dbWhat");
		  
		  HttpSession session = request.getSession();
		  
		  User user = (User) session.getAttribute("user");
		  
		  List<Chollo> chollosHot;
		  

		  // Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		  CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		  cholloDAO.setConnection(conn);
		  
		  
		  chollosHot = cholloDAO.getTresChollosHot();
		  
		  return chollosHot;
	  }

}
