/**
 * Persona.java
 *
 * @author Ronny Z. Suero
 */
package businessClass.businessEntities;

public abstract class Persona {
	protected long id;
	protected String nombre;
	protected String apellido;
	protected String cedula;
	protected Direccion direccion;

	/**
	 * Constructor por argumentos
	 * 
	 * @param  id        Este parametro define el id del cliente
	 * @param  nombre    Este parametro define el nombre del cliente
	 * @param  apellido  Este parametro define el apellido del cliente
	 * @param  cedula    Este parametro define la cedula del cliente
	 * 
	 */
	public Persona(final long id, final String nombre, final String apellido,
			final String cedula) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  id        Este parametro define el id del cliente
	 * @param  nombre    Este parametro define el nombre del cliente
	 * @param  apellido  Este parametro define el apellido del cliente
	 * @param  cedula    Este parametro define la cedula del cliente
	 * @param  direccion Este parametro define la direccion del cliente
	 * 
	 */
	public Persona(final long id, final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  nombre    Este parametro define el nombre del cliente
	 * @param  apellido  Este parametro define el apellido del cliente
	 * 
	 */
	public Persona(final String nombre, final String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  nombre    Este parametro define el nombre del cliente
	 * @param  apellido  Este parametro define el apellido del cliente
	 * @param  cedula    Este parametro define la cedula del cliente
	 * @param  direccion Este parametro define la direccion del cliente
	 * 
	 */
	public Persona(final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  datos        Este parametro define los datos personales del cliente
	 * @param  direccion    Este parametro define un objeto con la direccion del cliente
	 * 
	 */
	public Persona(final String[] datos, final Direccion direccion) {
		setDatos(datos);
		this.direccion = direccion;
	}

	/**
	 * Funcion que retorna el apellido de la persona
	 * 
	 * @return String
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Funcion que retorna la cedula de la persona
	 * 
	 * @return String
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Funcion que setea el nombre de la persona
	 * 
	 * @return Direccion
	 */
	public Direccion getDireccion() {
		return direccion;
	}

	/**
	 * Funcion que devuelve el id
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Funcion que retorna el nombre
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Funcion que modifica los datos del cliente
	 * 
	 * @param datos Este parametro define los datos del cliente a actualizar
	 *
	 * @return none
	 */
	public void setDatos(String[] datos) {
		nombre = datos[0];
		apellido = datos[1];
		cedula = datos[2];
	}
}