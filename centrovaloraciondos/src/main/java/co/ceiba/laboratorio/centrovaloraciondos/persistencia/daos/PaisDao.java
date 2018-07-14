package co.ceiba.laboratorio.centrovaloraciondos.persistencia.daos;

import co.ceiba.laboratorio.centrovaloraciondos.entidades.Pais;
import co.ceiba.laboratorio.centrovaloraciondos.persistencia.Dao;

/**
 * Clase utilitaria de la capa de persistencia que permite manipular el acceso a
 * los datos almacenados en la entidad {@link Pais}
 * 
 * @author Christian A. Candela
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 2013-08-08
 * 
 */
public class PaisDao extends Dao<Pais> {

	/**
	 * Metodo que permite inicializar los elementos de la clase PaisDao
	 */
	public PaisDao() {
		super(Pais.class);
	}

}
