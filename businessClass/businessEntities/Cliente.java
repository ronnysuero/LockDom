// Cliente.java
package businessClass.businessEntities;

public class Cliente extends Persona {
	// Constructor por argumentos
	public Cliente(final long id, final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		super(id, nombre, apellido, cedula, direccion);
	}

	// Constructor por argumentos
	public Cliente(final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		super(nombre, apellido, cedula, direccion);
	}

	// Constructor por argumentos
	public Cliente(final String[] datos, final Direccion direccion) {
		super(datos, direccion);
	}
}