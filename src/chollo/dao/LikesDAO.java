package chollo.dao;

import java.sql.Connection;
import java.util.List;

import chollo.model.Likes;

public interface LikesDAO {
	
	
	/**
	 * 
	 * @param idc
	 * @param idu
	 * @return
	 */
	public Likes getByChollo(long idc,long idu);
	
	/**
	 * 
	 * @param idc
	 * @return
	 */
	public List<Likes> getallByChollo(long idc);
	
	/**
	 * 
	 * @param idc
	 * @return
	 */
	public int getLikesChollo(long idc);
	
	
	/**
	 * 
	 * @param like
	 * @return
	 */
	public boolean save(Likes like);
	
	
	/**
	 * 
	 * @param like
	 * @return
	 */
	public long add(Likes like);
	

	
	/**
	 * 
	 * @param conn
	 */
	public void setConnection(Connection conn);

}
