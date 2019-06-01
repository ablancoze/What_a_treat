package chollo.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
import chollo.resources.exceptions.CustomNotFoundException;

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
		
		if (user!=null) {
			return user;
		}else {
			throw new CustomNotFoundException("User not connected");
		}
	  }
	  
	  @GET
	  @Path("/{userid: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserByIdJSON(@PathParam("userid") long userid, @Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
				
		User returnUser = userDAO.get(userid);
		
		return returnUser; 
	  }
	  
	  @GET
	  @Path("/name/{userid: [0-9]+}")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getUserNameById(@PathParam("userid") long userid, @Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
				
		User returnUser = userDAO.get(userid);
		
		if ((returnUser != null)) 
			return returnUser.getUsername(); 
		else 
			throw new CustomNotFoundException("No hay ningun usuario con ese nombre");	
		
		
	  }
	  
	  @GET
	  @Path("/{username: [a-zA-Z][a-zA-Z_0-9]*}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserNameByName(@PathParam("username") String username, @Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
				
		User returnUser = userDAO.get(username);
		
		if ((returnUser != null)) 
			return returnUser; 
		else 
			throw new CustomNotFoundException("No hay ningun usuario con ese nombre");	
		
		
	  }
	  
	  @GET
	  @Path("/{email: [\\w-]+@([\\w-]+\\.)+[\\w-]+}")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getUserEmailByEmail(@PathParam("email") String email, @Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
				
		String returnUser = userDAO.getEmail(email);
		
		if (returnUser != null) 
			return returnUser; 
		else 
			throw new CustomNotFoundException("No hay ningun usuario con ese email");
	  }
	  
	  @GET
	  @Path("/name{userid: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String getUserName(@PathParam("userid") long userid, @Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		HttpSession session = request.getSession();	
		
		String name = userDAO.get(userid).getUsername();
		return name; 
	  }
	  
	  @PUT
	  @Path("/{userid: [0-9]+}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response put(User userUpdate, @PathParam("userid") long cholloid, @Context HttpServletRequest request)throws Exception {
		  
		  Connection conn = (Connection) sc.getAttribute("dbWhat"); 
		  UserDAO userDAO = new JDBCUserDAOImpl();
		  userDAO.setConnection(conn);
	
		  HttpSession session = request.getSession();
		  User user = (User) session.getAttribute("user");
		  Response response = null;

		  User u = userDAO.get(userUpdate.getId());
		  
		  if ((u != null) && (user.getId() == u.getId()) ){
				  	userDAO.save(userUpdate);
				  	session.setAttribute("user", userUpdate);
		  }else {
			  throw new CustomBadRequestException("Error on id, u are not allowed to modify this user ");	
		  }	
		 
		  return Response.noContent().build();
		}
	  
		@DELETE
		@Path("/{userid: [0-9]+}")
		public Response deleteOrder(@PathParam("userid") long userid, @Context HttpServletRequest request) {

			Connection conn = (Connection) sc.getAttribute("dbWhat");
			UserDAO userDAO = new JDBCUserDAOImpl();
			userDAO.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			session = request.getSession(false);
			if (user != null) {
				userDAO.delete(userid);
				session.invalidate();
				return Response.noContent().build(); // 204 no content
			}else {
				throw new CustomBadRequestException("Error in user/id. Or maybe sesion out");
			}
		}
}
