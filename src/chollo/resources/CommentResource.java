package chollo.resources;

import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import chollo.dao.ComentarioDAO;
import chollo.dao.JDBCComentarioDAOImpl;
import chollo.model.Comentario;


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
}
