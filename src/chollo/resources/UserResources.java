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
import chollo.dao.JDBCUserDAOImpl;
import chollo.dao.UserDAO;
import chollo.model.Chollo;
import chollo.model.User;
import chollo.resources.exceptions.CustomBadRequestException;

@Path("/user")
public class UserResources {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			User returnUser = userDao.get(user.getUsername());
			return returnUser;
		}
		
		return null; 
	  }
	  
	  @GET
	  @Path("/{userid: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserByIdJSON(@PathParam("userid") long userid,@Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
				
		User returnUser = userDAO.get(userid);
		
		return returnUser; 
	  }
	  
		@DELETE
		@Path("/{userid: [0-9]+}")
		public Response deleteOrder(@PathParam("userid") long userid, @Context HttpServletRequest request) {

			Connection conn = (Connection) sc.getAttribute("dbWhat");
			UserDAO userDAO = new JDBCUserDAOImpl();
			userDAO.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			if (user != null) {
				userDAO.delete(userid);
				return Response.noContent().build(); // 204 no content
			}else {
				throw new CustomBadRequestException("Error in user/id. Or maybe sesion out");
			}
		}
}
