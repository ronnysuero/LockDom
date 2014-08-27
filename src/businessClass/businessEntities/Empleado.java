/**
 * Empleado.java
 *
 * @author Ronny Z.Suero
 */
package businessClass.businessEntities;

public class Empleado extends Persona {
	private double sueldo;
	private Cuenta unaCuenta;

	/**
	 * Constructor por argumentos
	 * 
	 * @param  id        Este parametro define el id del empleado
	 * @param  nombre    Este parametro define el nombre del empleado
	 * @param  apellido  Este parametro define el apellido del empleado
	 * @param  cedula    Este parametro define la cedula del empleado
	 * @param  sueldo    Este parametro define el sueldo del empleado
	 * @param  unaCuenta Este objeto define un objeto Cuenta
	 */
	public Empleado(final long id, final String nombre, final String apellido,
			final String cedula, final double sueldo, Cuenta unaCuenta) {
		super(id, nombre, apellido, cedula);
		this.sueldo = sueldo;
		this.unaCuenta = unaCuenta;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  nombre    Este parametro define el nombre del empleado
	 * @param  apellido  Este parametro define el apellido del empleado
	 * @param  unaCuenta Este objeto define un objeto Cuenta
	 */
	public Empleado(final String nombre, final String apellido, Cuenta unaCuenta) {
		super(nombre, apellido);
		this.unaCuenta = unaCuenta;
	}

	/**
	 * Funcion que retorna la Cuenta de acceso del cliente
	 * 
	 * @return Cuenta
	 */
	public Cuenta getCuenta() {
		return unaCuenta;
	}

	/**
	 * Funcion que retorna el sueldo del empleado
	 * 
	 * @return double
	 */
	public double getSueldo() {
		return sueldo;
	}
}