// Empleado.java
package businessClass.businessEntities;

public class Empleado extends Persona {
	private double sueldo;
	private Cuenta unaCuenta;

	// Constructor por argumentos
	public Empleado(final long id, final String nombre, final String apellido,
			final String cedula, final double sueldo, Cuenta unaCuenta) {
		super(id, nombre, apellido, cedula);
		this.sueldo = sueldo;
		this.unaCuenta = unaCuenta;
	}

	// Constructor por argumentos
	public Empleado(final String nombre, final String apellido, Cuenta unaCuenta) {
		super(nombre, apellido);
		this.unaCuenta = unaCuenta;
	}

	public Cuenta getCuenta() {
		return unaCuenta;
	}

	public double getSueldo() {
		return sueldo;
	}
}