/**
 *
 * @author Cyrus Wilson
 */
package backend.dao;

import java.util.List;
import java.util.Optional;
import backend.exceptions.DatabaseException;

/**
 * PRIMARY EXPLANATION for BaseDAO or generic interface of the dao layer
 * Generic DAO interface that defines common database operations.
 * All specific DAO implementations should implement this interface.
 * 
 * @param <T> The entity type this DAO manages
 * @param <ID> The type of the entity's primary key
 */
public interface BaseDAO<T, ID> {
    
    /**
     * Saves a new entity to the database.
     * 
     * @param entity The entity to save
     * @return The saved entity with generated ID
     * @throws DatabaseException if database operation fails
     */
    T create(T entity) throws DatabaseException;
    
    /**
     * Finds an entity by its primary key.
     * 
     * @param id The primary key value
     * @return Optional containing the entity if found, empty otherwise
     * @throws DatabaseException if database operation fails
     */
    Optional<T> findById(ID id) throws DatabaseException;
    
    /**
     * Retrieves all entities from the database.
     * 
     * @return List of all entities
     * @throws DatabaseException if database operation fails
     */
    List<T> findAll() throws DatabaseException;
    
    /**
     * Updates an existing entity in the database.
     * 
     * @param entity The entity with updated values
     * @return The updated entity
     * @throws DatabaseException if database operation fails
     */
    T update(T entity) throws DatabaseException;
    
    /**
     * Deletes an entity by its primary key.
     * 
     * @param id The primary key of the entity to delete
     * @return true if deletion was successful, false otherwise
     * @throws DatabaseException if database operation fails
     */
    boolean delete(ID id) throws DatabaseException;
    
    /**
     * Checks if an entity exists with the given primary key.
     * 
     * @param id The primary key to check
     * @return true if entity exists, false otherwise
     * @throws DatabaseException if database operation fails
     */
    boolean exists(ID id) throws DatabaseException;
}