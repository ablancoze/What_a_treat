package chollo.dao;

import java.sql.Connection;
import java.util.List;

import chollo.model.Comentario;

public interface ComentarioDAO {

	
	/**
	 * 
	 * @return
	 */
	public List<Comentario> getAll();
	
	
	
	/**
	 * 
	 * @param cid
	 * @return
	 */
	public List<Comentario> getComentAndUser(long cid);
	
	
	/**
	 * 
	 * @param comentario
	 * @return
	 */
	public long add(Comentario comentario);
	
	/**
	 * 
	 * @param conn
	 */
	public void setConnection(Connection conn);
	
	
	
}
