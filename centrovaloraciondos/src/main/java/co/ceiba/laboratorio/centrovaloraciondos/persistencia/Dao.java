package co.ceiba.laboratorio.centrovaloraciondos.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase generica que brinda un modelo para la construccion de los DAOs que
 * permitiran la manipulacion de las entidades
 * 
 * 
 * @param <Entidad>
 *            Entidad que sera manipulada
 * 
 * @author Christian A. Candela
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @version 2.0
 * @since 2013-04-12
 */
public abstract class Dao<Entidad extends Serializable> {
	/**
	 * Representa la clase a la que pertenece la entidad que esta manipulando el
	 * DAO
	 */
	private Class<Entidad> entityClass;

	/**
	 * Entity Manager que permitira la ejecucion de las sentencias JPQL
	 */
	@Inject
	protected EntityManager entityManager;

	/**
	 * Costructor del DAO que permite inicializar y determinar la clase a la que
	 * pertenece la Entidad que se manipulara
	 * 
	 * @param entityClass
	 */
	public Dao(Class<Entidad> entityClass) {
		this.entityClass = entityClass;
	}

	
	
	/**
	 * Metodo que permite obtener el valor del atributo entityManager
	 * @return El valor del atributo entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}



	/**
	 * Metodo que permite asignar un valor al atributo entityManager
	 * @param entityManager Valor a ser asignado al atributo entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	/**
	 * Metodo que permite la insercion de una entidad en la base de datos
	 * 
	 * @param entidad
	 *            Entidad a ser almacenada
	 * @return Retorna la entidad almacenada
	 */
	public Entidad insert(Entidad entidad) {
		entityManager.persist(entidad);
		return entidad;
	}

	/**
	 * Metodo que permite la actualizacion de una entidad
	 * 
	 * @param entidad
	 *            Entidad a ser actualizada
	 * @return Retorna la entidad actualizada
	 */
	public Entidad update(Entidad entidad) {
		return entityManager.merge(entidad);
	}

	/**
	 * Metodo que permite remover una entidad de la base de datos
	 * 
	 * @param entidad
	 *            Entidad a ser removida
	 */
	public void remove(Entidad entidad) {
		entityManager.remove(entityManager.merge(entidad));
	}

	/**
	 * Metodo que permite obtener la clase a la que pertenece la entidad
	 * panipulada
	 * 
	 * @return La clase a la que pertenece la entidad manipulada
	 */
	public Class<Entidad> getEntityClass() {
		return entityClass;
	}

	/**
	 * Permite buscar una entidad por medio de se llave
	 * 
	 * @param llave
	 *            Llave de la entidad que se desea buscar
	 * @return Retorna la entidad que corresponda a la llave dada, en caso de no
	 *         encontrar un resultado se retorna null
	 */
	public Entidad findByKey(Serializable llave) {
		return entityManager.find(getEntityClass(), llave);
	}

	/**
	 * Metodo que permite realizar una consulta JPQL sobre la base de datos
	 * 
	 * @param jpqlStmt
	 *            Sentencia JPQL a ser ejecutada
	 * @param firstResult
	 *            Posicion de inicio del primer resultado de la consulta
	 * @param maxResults
	 *            Maximo numero de resultados a ser obtenidos a partir de la
	 *            primera posicion
	 * @return Un listado con los registros obtenidos de la consulta ejecutada
	 */
	public List<?> queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		Query query = entityManager.createQuery(jpqlStmt);
		if (firstResult > 0) {
			query = query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			query = query.setMaxResults(maxResults);
		}
		return query.getResultList();
	}

	/**
	 * Permite obtener todos los registros almancenados de una determinada
	 * entidad partiendo de la posicion indicada hasta el maximo numero de
	 * registros dados.
	 * 
	 * @param firstResult
	 *            Posicion de inicio del primer resultado de la consulta
	 * @param maxResults
	 *            Maximo numero de resultados a ser obtenidos a partir de la
	 *            primera posicion
	 * @return El listado de entidades encontrado
	 */
	public List<Entidad> getAll(int firstResult, int maxResults) {
		TypedQuery<Entidad> query = entityManager.createQuery(
				"select entidad from " + getEntityClass().getSimpleName()
						+ " entidad ", getEntityClass());
		if (firstResult > 0) {
			query = query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			query = query.setMaxResults(maxResults);
		}
		return query.getResultList();
	}

	/**
	 * Permite obtener todos los registros almancenados de una determinada
	 * entidad.
	 * 
	 * @return El listado de entidades encontrado
	 */
	public List<Entidad> getAll() {
		return getAll(-1, -1);
	}

	/**
	 * Permite obtener todos los registros almancenados de una determinada
	 * entidad que se encuentren dentro del rango especificado por el parametro
	 * rango.
	 * 
	 * @param rango
	 *            Arreglo que contiene el rango de resultados que se desea
	 *            obtener, en la posicion 0 se envía el indice del primer
	 *            resultado que se desea obtener. En el indice 1 se envía el
	 *            indice del último registro que se desea obtener
	 * @return El listado de entidades encontrado
	 */
	public List<Entidad> findRange(int[] range) {
		// javax.persistence.criteria.CriteriaQuery<Entidad> cq =
		// entityManager.getCriteriaBuilder().createQuery(getEntityClass());
		// cq.select(cq.from(getEntityClass()));
		// javax.persistence.TypedQuery<Entidad> q =
		// entityManager.createQuery(cq);
		// q.setMaxResults(range[1] - range[0]);
		// q.setFirstResult(range[0]);
		// return q.getResultList();
		return getAll(range[0], range[1] - range[0]);
	}

	/**
	 * Permite obtener el número de registros de una determinada entidad
	 * 
	 * @return El número de registros de una determinada entidad
	 */
	public int count() {

		// javax.persistence.criteria.CriteriaQuery<Long> cq =
		// entityManager.getCriteriaBuilder().createQuery(Long.class);
		// javax.persistence.criteria.Root<Entidad> rt =
		// cq.from(getEntityClass());
		// cq.select(entityManager.getCriteriaBuilder().count(rt));
		// javax.persistence.TypedQuery<Long> q = entityManager.createQuery(cq);
		// return q.getSingleResult().intValue();

		TypedQuery<Long> query = entityManager.createQuery(
				"select COUNT(entidad) from "
						+ getEntityClass().getSimpleName() + " entidad ",
				Long.class);
		return query.getSingleResult().intValue();

	}

	/**
	 * Metodo que permite realizar una consulta JPQL sobre la base de datos,
	 * obtener todos los registros que se encuentren dentro del rango
	 * especificado por el parametro rango.
	 * 
	 * @param jpqlStmt
	 *            Sentencia JPQL a ser ejecutada
	 * @param rango
	 *            Arreglo que contiene el rango de resultados que se desea
	 *            obtener, en la posicion 0 se envía el indice del primer
	 *            resultado que se desea obtener. En el indice 1 se envía el
	 *            indice del último registro que se desea obtener
	 * @return Un listado con los registros obtenidos de la consulta ejecutada y
	 *         dentro del rango establecido
	 */
	public List<?> queryByRange(String jpqlStmt, int[] rango) {
		return queryByRange(jpqlStmt, rango[0], rango[1] - rango[0]);
	}

}