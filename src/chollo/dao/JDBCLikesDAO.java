package chollo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import chollo.model.Comentario;
import chollo.model.Likes;
import chollo.model.Shop;

public class JDBCLikesDAO implements LikesDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCCholloDAOImpl.class.getName());
	
	
	@Override
	public Likes getByChollo(long idc,long idu) {
		
		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return null;
		}
		
		Likes l = new Likes();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM like WHERE idc = "+idc+" AND idu = "+idu+"");
			if (!rs.next()) {
				return null;
			}else {
				l.setIdc(rs.getInt("idc"));
				l.setIdu(rs.getInt("idu"));
				l.setLike(rs.getInt("likes"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return l;
		
	}
	
	@Override
	public List<Likes> getallByChollo(long idc) {
		
		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return null;
		}
		
		ArrayList<Likes> likes = new ArrayList<Likes>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM like WHERE idc = "+idc+"");
			while ( rs.next() ) {
				Likes l = new Likes();
				
				l.setIdu(rs.getInt("idu"));
				l.setIdc(rs.getInt("idc"));
				l.setLike(rs.getInt("like"));
				
				likes.add(l);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return likes;
	}
	
	@Override
	public int getLikesChollo(long idc){
		
		if (conn == null) {
			System.out.println("NO ESTA CARGANDO LA BD");
			return -1;
		}
		
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT SUM(likes) as likes FROM like WHERE idc ="+idc+"");
			
			if (!rs.next()) 
				return 0; 
			
			return rs.getInt("likes");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	@Override
	public boolean save(Likes like) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE like SET likes ="+like.getLike()+" WHERE idu ="+like.getIdu()+" AND idc ="+like.getIdc());
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
	
	
	@Override
	public long add(Likes like) {
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO like (idu,idc,likes) VALUES("+like.getIdu()+","+like.getIdc()+","+like.getLike()+")");
				
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	
	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}
}
