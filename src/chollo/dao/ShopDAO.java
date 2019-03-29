package chollo.dao;

import java.sql.Connection;
import java.util.List;

import chollo.model.Shop;


public interface ShopDAO {

	/**
	 * set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Gets a shop from the DB using id.
	 * 
	 * @param id
	 *            Shop Identifier.
	 * 
	 * @return Shop object with that id.
	 */
	public Shop get(long id);
	
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public long getIdByName(String name);
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getNameById(long id);

	/**
	 * Gets a shop from the DB using name.
	 * 
	 * @param name
	 *            Shop name.
	 * 
	 * @return Shop object with that name.
	 */
	public Shop get(String name);

	
	
	
	
	/**
	 * Gets all the shops from the database.
	 * 
	 * @return List of all the shops from the database.
	 */
	public List<Shop> getAll();
	
	/**
	 * 
	 * @return
	 */
	public List<String> getAllShopName();
	
	/**
	 * Gets all the shops from the database that contain a text in the name.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the shops from the database that contain a text in the name.
	 */	
	public List<Shop> getAllBySearchName(String search);


	/**
	 * Adds a shop to the database.
	 * 
	 * @param shop
	 *            Shop object with the shop details.
	 * 
	 * @return Shop identifier or -1 in case the operation failed.
	 */
	
	public long add(Shop shop);

	/**
	 * Updates an existing shop in the database.
	 * 
	 * @param shop
	 *            Shop object with the new details of the existing shop.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	
	
	public boolean save(Shop shop);

	/**
	 * Deletes a shop from the database.
	 * 
	 * @param id
	 *            Shop identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	
	public boolean delete(long id);
}
