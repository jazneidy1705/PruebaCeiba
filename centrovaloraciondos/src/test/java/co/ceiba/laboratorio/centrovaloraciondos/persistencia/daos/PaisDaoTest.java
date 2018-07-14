package co.ceiba.laboratorio.centrovaloraciondos.persistencia.daos;

import javax.inject.Inject;

import junit.framework.TestCase;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.ceiba.laboratorio.centrovaloraciondos.entidades.Pais;
import co.ceiba.laboratorio.centrovaloraciondos.persistencia.Dao;
import co.ceiba.laboratorio.centrovaloraciondos.util.Resources;

/**
 * Clase que prueba el correcto funcionamiento de la clase {@link PaisDao}
 * 
 * @author Christian A. Candela
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0  
 * @since 2013-08-15
 *
 */
@RunWith(Arquillian.class)
public class PaisDaoTest extends TestCase {

	/**
	 * Variable que representa el atributo paisDao de la clase.
	 */
	@Inject
	private PaisDao paisDao;
	
	/**
	 * Metodo encargado de construir el archivo a ser desplegado para la prueba
	 * 
	 * @return Archivo a ser desplegado
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Pais.class, Dao.class, PaisDao.class,
						Resources.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}


	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#insert(Pais)}
	 */
	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	public void testInsert() {
		Pais pais = new Pais();
		pais.setCodigo("co");
		pais.setNombre("Colombia");
		paisDao.insert(pais);
		
		Pais almacenado = paisDao.findByKey("co");
		
		Assert.assertEquals(pais, almacenado);
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#update(Pais)}
	 */
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#remove(Pais)}
	 */
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#getEntityClass()}
	 */
	public void testGetEntityClass() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#findByKey(java.io.Serializable)}
	 */
	public void testFindByKey() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#queryByRange(String, int, int)}
	 */
	public void testQueryByRangeStringIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#getAll(int, int)}
	 */
	public void testGetAllIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#getAll()}
	 */
	public void testGetAll() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#findRange(int[])}
	 */
	public void testFindRange() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#count()}
	 */
	public void testCount() {
		fail("Not yet implemented");
	}

	/**
	 * Metodo que prueba el funcionamiento del metodo {@link PaisDao#queryByRange(String, int[])}
	 */
	public void testQueryByRangeStringIntArray() {
		fail("Not yet implemented");
	}

}
