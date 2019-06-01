package chollo.resources;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import chollo.dao.CholloDAO;
import chollo.dao.JDBCCholloDAOImpl;
import chollo.model.Chollo;
import chollo.model.User;
import chollo.resources.exceptions.CustomBadRequestException;
import chollo.resources.exceptions.CustomNotFoundException;

@Path("/chollos")
public class ChollosResources {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Chollo> getCholloJSON (@Context HttpServletRequest request){
		  
		  Connection conn = (Connection) sc.getAttribute("dbWhat");
		  
		  HttpSession session = request.getSession();
		  
		  List<Chollo> chollos;
		  
		  // Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		  CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		  cholloDAO.setConnection(conn);
		  
		  
		  chollos = cholloDAO.getAll();
		  
		  return chollos;
	  }
	  
	  @GET
	  @Path("/{search: [a-zA-Z][a-zA-Z_0-9]*}")	
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Chollo> getCholloBySearchJSON (@PathParam("search") String search, @Context HttpServletRequest request){
		  
		  Connection conn = (Connection) sc.getAttribute("dbWhat");
		  
		  HttpSession session = request.getSession();
		  
		  List<Chollo> chollos;
		  
		  // Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		  CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		  cholloDAO.setConnection(conn);
		  
		  
		  chollos = cholloDAO.getAllBySearchAll(search);
		  
		  return chollos;
	  }
	  
	  @GET
	  @Path("/shop/{shopid: [0-9]+}")	
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Chollo> getCholloByIdShopJSON (@PathParam("shopid") long shopid, @Context HttpServletRequest request){
		  
		  Connection conn = (Connection) sc.getAttribute("dbWhat");
		  
		  HttpSession session = request.getSession();
		  
		  List<Chollo> chollos;
		  
		  // Le doy los chollos y los conecyo con la base de datospara que pueda verlos
		  CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		  cholloDAO.setConnection(conn);
		  
		  
		  chollos = cholloDAO.getAllBySearchShop(shopid);
		  
		  return chollos;
	  }
	  
	  @GET
	  @Path("/{cholloid: [0-9]+}")	  
	  @Produces(MediaType.APPLICATION_JSON)
	  public Chollo getCholloJSON(@PathParam("cholloid") long cholloid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
		
		Chollo chollo = cholloDAO.get(cholloid);
		
		if ((chollo != null)) 
			return chollo;
		else 
			throw new CustomNotFoundException("Order ("+ cholloid + ") is not found");		   
	  }
	  
	  @GET
	  @Path ("/hot")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Chollo> getCholloHotJSON (@Context HttpServletRequest request){
		  
		  Connection conn = (Connection) sc.getAttribute("dbWhat");
		  CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		  cholloDAO.setConnection(conn);
		  
		  HttpSession session = request.getSession();
		  
		  List<Chollo> chollosHot;
		  chollosHot = cholloDAO.getTresChollosHot();
		  
		if ((chollosHot != null))
			return chollosHot;
		else
			throw new CustomNotFoundException("Order no hay chollos is not found");
	  }
	  
	  
	  @GET
	  @Path("/user/{userid: [0-9]+}")	  //¿Esta bien? 
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Chollo> getCholloByIdUserJSON(@PathParam("userid") long userid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
		List<Chollo> chollos;
		chollos = cholloDAO.getAllByUser(userid);
		
		if ((chollos != null)) 
			return chollos;
		else 
			throw new CustomNotFoundException("Order ("+ userid + ") is not found");		   
	  }
	  
	  
	  @POST	  	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response post(Chollo newChollo, @Context HttpServletRequest request) throws Exception {

		Connection conn = (Connection) sc.getAttribute("dbWhat");
		CholloDAO cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);	  	 
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Response res = null;

		if (user != null && newChollo.getIdu()==user.getId()) {
			long id = cholloDao.add(newChollo);
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
	  
	  //POST that receives a new order via webform
	  @POST	  	 
	  @Consumes("application/x-www-form-urlencoded")
	  public Response post(MultivaluedMap<String, String> formParams, @Context HttpServletRequest request) {
		  
		Connection conn = (Connection) sc.getAttribute("dbWhat");
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		  
		Response res;
		
		
		if (user != null) {
			Chollo chollo = new Chollo();
	        chollo.setTitle(formParams.getFirst("titulo"));
	        chollo.setDescription(formParams.getFirst("descripcion"));
	        chollo.setLink(formParams.getFirst("link"));
	        chollo.setPrice(Long.parseLong(formParams.getFirst("precio")));
	        chollo.setIds(Long.parseLong(formParams.getFirst("shop")));
	        chollo.setIdu(user.getId());
	        chollo.setLikes(0);
	        chollo.setSoldout((int) Long.parseLong(formParams.getFirst("soldout")));
	        chollo.setImagen(formParams.getFirst("imagen"));
	        long id = cholloDAO.add(chollo);
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
	  
	  
	  @PUT
	  @Path("/{cholloid: [0-9]+}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(Chollo cholloUpdate, @PathParam("cholloid") long cholloid, @Context HttpServletRequest request) throws Exception{
		  Connection conn = (Connection)sc.getAttribute("dbWhat");
		  CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		  cholloDAO.setConnection(conn);
		  
		  HttpSession session = request.getSession();
		  User user = (User) session.getAttribute("user");
			
		  Response response = null;
					
		  //We check that the order exists
		  
		  Chollo chollo = cholloDAO.get(cholloUpdate.getId());
		  
		  if ((chollo != null) && (user.getId() == chollo.getIdu()) ){
					if (chollo.getId()!=cholloid) { 
						throw new WebApplicationException(Response.Status.NOT_FOUND);
					}else{
						cholloDAO.save(cholloUpdate);
					}
					
				}else {
					throw new CustomBadRequestException("Error on id, u are not allowed to modify this trat ");
						
				}		
		  return response;
		}
	  
	  
	@DELETE
	@Path("/{cholloid: [0-9]+}")
	public Response deleteOrder(@PathParam("cholloid") long cholloid, @Context HttpServletRequest request) {

		Connection conn = (Connection) sc.getAttribute("dbWhat");
		CholloDAO cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Chollo chollo = cholloDao.get(cholloid);
		
		if (user != null && chollo.getIdu() == user.getId()) {
			cholloDao.delete(cholloid);
			return Response.noContent().build(); // 204 no content
		}else {
			throw new CustomBadRequestException("Error in user or id");
		}
	}
}
