package chollo.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import chollo.dao.CholloDAO;
import chollo.dao.ComentarioDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.dao.JDBCComentarioDAOImpl;
import chollo.model.Chollo;
import chollo.model.Comentario;
import chollo.model.User;
import chollo.resources.exceptions.CustomBadRequestException;


@Path("/comment")
public class CommentResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;

	  @GET
	  @Path("/{cholloid: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Comentario> getComment(@PathParam("cholloid") long cholloid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		ComentarioDAO comentarioDAO = new JDBCComentarioDAOImpl();
		comentarioDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
		
		List<Comentario> comentariosList = comentarioDAO.getComentAndUser(cholloid);
		
		return comentariosList; 
	  }
	  
	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(Comentario newComentario, @Context HttpServletRequest request) throws Exception {

		Connection conn = (Connection) sc.getAttribute("dbWhat");
		ComentarioDAO comentarioDAO = new JDBCComentarioDAOImpl();
		comentarioDAO.setConnection(conn);	  	 
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Response res = null;

		if (user != null && newComentario.getUsername().equals(user.getUsername())) {
			long id = comentarioDAO.add(newComentario);
			res = Response //return 201 and Location: /orders/newid
					   .created(
						uriInfo.getAbsolutePathBuilder()
							   .path(Long.toString(id))
							   .build())
					   .contentLocation(
						uriInfo.getAbsolutePathBuilder()
						       .path(Long.toString(id))
						       .build())
						.build();
			    return res; 
		}else {
			throw new CustomBadRequestException("Errors in parameters");
		}
	  }
}
