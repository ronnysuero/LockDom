/**
 * Cliente.java
 *
 * @author Ronny Z. Suero
 */
package businessClass.businessEntities;

public class Cliente extends Persona {

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
	public Cliente(final long id, final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		super(id, nombre, apellido, cedula, direccion);
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
	public Cliente(final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		super(nombre, apellido, cedula, direccion);
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  datos        Este parametro define los datos personales del cliente
	 * @param  direccion    Este parametro define los datos de la direccion del cliente
	 * 
	 */
	public Cliente(final String[] datos, final Direccion direccion) {
		super(datos, direccion);
	}
}