package co.ceiba.laboratorio.centrovaloraciondos.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Propietario
 *
 */
@Entity

public class Propietario implements Serializable {

	   


	@Id
	private String Cedula;
	private String Nombre;
	private static final long serialVersionUID = 1L;

	public Propietario() {
		super();
	}   
	public String getCedula() {
		return this.Cedula;
	}

	public void setCedula(String Cedula) {
		this.Cedula = Cedula;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Cedula == null) ? 0 : Cedula.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Propietario other = (Propietario) obj;
		if (Cedula == null) {
			if (other.Cedula != null)
				return false;
		} else if (!Cedula.equals(other.Cedula))
			return false;
		return true;
	}
	
	
   
}
