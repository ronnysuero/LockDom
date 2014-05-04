// Persona.java
package businessClass.businessEntities;

public abstract class Persona {
	protected long id;
	protected String nombre;
	protected String apellido;
	protected String cedula;
	protected Direccion direccion;

	// Constructor por argumentos
	public Persona(final long id, final String nombre, final String apellido,
			final String cedula) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
	}

	// Constructor por argumentos
	public Persona(final long id, final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
	}

	// Constructor por argumentos
	public Persona(final String nombre, final String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	// Constructor por argumentos
	public Persona(final String nombre, final String apellido,
			final String cedula, final Direccion direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
	}

	// Constructor por argumentos
	public Persona(final String[] datos, final Direccion direccion) {
		setDatos(datos);
		this.direccion = direccion;
	}

	// Funcion que retorna el apellido de la persona
	public String getApellido() {
		return apellido;
	}

	// Funcion que retorna la cedula de la persona
	public String getCedula() {
		return cedula;
	}

	// Funcion que setea el nombre de la persona
	public Direccion getDireccion() {
		return direccion;
	}

	// Funcion que devuelve el id
	public long getId() {
		return id;
	}

	// Funcion que retorna el nombre
	public String getNombre() {
		return nombre;
	}

	public void setDatos(String[] datos) {
		nombre = datos[0];
		apellido = datos[1];
		cedula = datos[2];
	}
}