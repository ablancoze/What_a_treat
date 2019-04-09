package chollo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import chollo.model.Chollo;

public class JDBCCholloDAOImpl implements CholloDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCCholloDAOImpl.class.getName());
	
	@Override
	public Chollo get(long id) {
		if (conn == null) return null;
		
		Chollo chollo = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT  id, title,description,link,price,idu,ids,(SELECT SUM(likes)FROM like WHERE idc = id) as likes,soldout,imagen FROM chollo WHERE id = "+id+"");			 
			if (!rs.next()) return null; 
			chollo  = new Chollo();	 
			chollo.setId(rs.getInt("id"));
			chollo.setTitle(rs.getString("title"));
			chollo.setDescription(rs.getString("description"));
			chollo.setLink(rs.getString("link"));
			chollo.setPrice(rs.getFloat("price"));
			chollo.setIdu(rs.getInt("idu"));
			chollo.setIds(rs.getInt("ids"));
			chollo.setLikes(rs.getInt("likes"));
			chollo.setSoldout(rs.getInt("soldout"));
			chollo.setImagen(rs.getString("imagen"));
			
			logger.info("fetching chollos: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
						+ " " + chollo.getIdu() + " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chollo;
	}
	
	public List<Chollo> getAll() {

		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return null;
		}
		
		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT  id, title,description,link,price,idu,ids,(SELECT SUM(likes)FROM like WHERE idc = id) as likes,soldout,imagen FROM chollo ORDER BY id DESC");
			while ( rs.next() ) {
				Chollo chollo = new Chollo();
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
						
				
				chollos.add(chollo);
				logger.info("fetching chollos: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
						+ " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chollos;
	}
	
	@Override
	public List<Chollo> getTresChollosHot(){
		
		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return null;
		}
		
		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM chollo ORDER by likes DESC");
			int i = 0;
			while ( rs.next() && i<3 ) {
				Chollo chollo = new Chollo();
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
						
				
				chollos.add(chollo);
				logger.info("fetching chollos: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
						+ " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());
				
				i++;				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chollos;
		
	}
	
	@Override
	public List<Chollo> getAllBySearchTitle(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM chollo WHERE UPPER(title) LIKE '%" + search + "%' ORDER BY id DESC");

			while (rs.next()) {
				Chollo chollo = new Chollo();
				
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
				
				chollos.add(chollo);
				
				logger.info("fetching chollos by text in the title: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
				+  " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chollos;
	}
	
	public List<Chollo> getAllBySearchDescription(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM chollo WHERE UPPER(description) LIKE '%" + search + "%' ORDER BY id DESC");

			while (rs.next()) {
				Chollo chollo = new Chollo();
				
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
				
				chollos.add(chollo);
				
				logger.info("fetching chollos by text in the description: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
				+ " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chollos;
	}
	
	public List<Chollo> getAllBySearchAll(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM chollo WHERE UPPER(title) LIKE '%" + search + "%' OR UPPER(description) LIKE '%" + search + "%' ORDER BY id DESC");

			while (rs.next()) {
				Chollo chollo = new Chollo();
				
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
				
				chollos.add(chollo);
				
				logger.info("fetching chollos by text either in the title or in the description: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
				+ " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chollos;
	}
	
	
	public List<Chollo> getAllBySearchShop(long search) {
		if (conn == null)
			return null;

		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM chollo WHERE ids =" + search +" ORDER BY id DESC");

			while (rs.next()) {
				Chollo chollo = new Chollo();
				
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
				
				chollos.add(chollo);
				
				logger.info("fetching chollos by text either in the title or in the description: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
				+ " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chollos;
	}

	
	public List<Chollo> getAllByUser(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Chollo> chollos = new ArrayList<Chollo>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Chollo WHERE idu = "+ idu + "ORDER BY id DESC");

			while (rs.next()) {
				Chollo chollo = new Chollo();
				
				chollo.setId(rs.getInt("id"));
				chollo.setTitle(rs.getString("title"));
				chollo.setDescription(rs.getString("description"));
				chollo.setLink(rs.getString("link"));
				chollo.setPrice(rs.getFloat("price"));
				chollo.setIdu(rs.getInt("idu"));
				chollo.setIds(rs.getInt("ids"));
				chollo.setLikes(rs.getInt("likes"));
				chollo.setSoldout(rs.getInt("soldout"));
				chollo.setImagen(rs.getString("imagen"));
				
				chollos.add(chollo);
				
				logger.info("fetching chollos by text either in the title or in the description: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription()+ " " + chollo.getLink() + " " + chollo.getPrice() 
				+ " " + chollo.getIdu()+ " " + chollo.getIds() + " " + chollo.getLikes() + " " + chollo.getSoldout());				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return chollos;
	}

	
	
	
	@Override
	public long add(Chollo chollo) {
		long id=-1;
		long lastid=-1;
		if (conn != null){
			logger.info("Venga a ver que cojones pasa aqui La conexion no es nula algo es algo");
			Statement stmt;
			
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='chollo'");			 
				if (!rs.next()) return -1; 
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			try {
				
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO chollo (title,description,link,price,idu,ids,likes,soldout,imagen) VALUES( '"+chollo.getTitle()+"','"+chollo.getDescription()+"', '"+ chollo.getLink() +"',"+ chollo.getPrice()+","+ chollo.getIdu()+","+ chollo.getIds() +","+chollo.getLikes()+" , "+chollo.getSoldout()+",'"+chollo.getImagen()+"')");
				
								
			} catch (SQLException e) {
				logger.info("Venga a ver que cojones pasa aqui  segundo catch");
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='chollo'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING Chollo("+id+"): "+chollo.getTitle()+" "+chollo.getDescription());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
	}

	@Override
	public boolean save(Chollo chollo) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE Chollo SET title='"+chollo.getTitle()+"', description='"+chollo.getDescription()+"',link='"+chollo.getLink()+"',price="+chollo.getPrice()+",idu="+chollo.getIdu()+",ids="+chollo.getIds()+",likes="+chollo.getLikes()+",soldout="+chollo.getSoldout()+",imagen='"+chollo.getImagen()+"' WHERE id = "+chollo.getId());
				
				logger.info("updating Chollo: "+chollo.getId()+" "+chollo.getTitle()+" "+chollo.getDescription());
						
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;

	}

	@Override
	public boolean delete(long id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM Chollo WHERE id ="+id);
				logger.info("deleting Chollo: "+id);
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}

	
}
