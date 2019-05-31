package chollo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import chollo.model.Chollo;
import chollo.model.Comentario;

public class JDBCComentarioDAOImpl implements ComentarioDAO {
	
	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCCholloDAOImpl.class.getName());
	
	@Override
	public List<Comentario> getAll() {

		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return null;
		}
		
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM comentarios");
			while ( rs.next() ) {
				Comentario comentario = new Comentario();
				
				comentario.setId(rs.getInt("id"));
				comentario.setUsername(rs.getString("username"));
				comentario.setCid(rs.getInt("cid"));
				comentario.setComentario(rs.getString("comentario"));
				comentario.setPuntos(rs.getInt("puntos"));
				
				comentarios.add(comentario);
				logger.info("fetching comentarios: "+comentario.getId()+" "+ comentario.getUsername()+" "+ comentario.getCid()+" "+ comentario.getComentario()+" "+comentario.getPuntos());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comentarios;
	}
	
	
	@Override
	public List<Comentario> getComentAndUser(long cid){
		
		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return null;
		}
		
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM comentarios WHERE cid = "+cid+"");
			while ( rs.next() ) {
				
				Comentario comentario = new Comentario();
				comentario.setId(rs.getInt("id"));
				comentario.setUsername(rs.getString("username"));
				comentario.setCid(rs.getInt("cid"));
				comentario.setComentario(rs.getString("comentario"));
				comentario.setPuntos(rs.getInt("puntos"));
				
				comentarios.add(comentario);
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comentarios;
		
	}
	
	
	
	@Override
	public long add(Comentario comentario) {
		long id=-1;
		long lastid=-1;
		if (conn != null){
			
			Statement stmt;
			
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='comentarios'");			 
				if (!rs.next()) 
					return -1; 
				
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO comentarios (username,cid,comentario,puntos) VALUES('"+comentario.getUsername()+"',"+comentario.getCid()+",'"+comentario.getComentario()+"',"+ comentario.getPuntos()+")");
				
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='comentarios'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING comentario("+id+"): "+comentario.getCid()+" "+comentario.getComentario());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id;
	}
	
	
	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}
	
	
	

}
