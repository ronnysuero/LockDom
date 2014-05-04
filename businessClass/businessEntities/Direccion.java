// Direccion.java
package businessClass.businessEntities;

public class Direccion {
	private long id;
	private String nombreCalle;
	private String numeroCasa;
	private String sector;
	private String ciudad;

	// Constructor por argumentos
	public Direccion(final long id, final String nombreCalle,
			final String numeroCasa, final String sector, final String ciudad) {
		this.id = id;
		this.nombreCalle = nombreCalle;
		this.numeroCasa = numeroCasa;
		this.sector = sector;
		this.ciudad = ciudad;
	}

	// Constructor por argumentos
	public Direccion(final String nombreCalle, final String numeroCasa,
			final String sector, final String ciudad) {
		this.nombreCalle = nombreCalle;
		this.numeroCasa = numeroCasa;
		this.sector = sector;
		this.ciudad = ciudad;
	}

	// Constructor por argumentos
	public Direccion(final String[] datos) {
		nombreCalle = datos[0];
		numeroCasa = datos[1];
		sector = datos[2];
		ciudad = datos[3];
	}

	// Funcion que retorna el nombre de la ciudad
	public String getCiudad() {
		return ciudad;
	}

	// Funcion que retorna el id de la direccion
	public long getId() {
		return id;
	}

	// Funcion que retorna el nombre de la calle
	public String getNombreCalle() {
		return nombreCalle;
	}

	// Funcion que retorna el numero de casa
	public String getNumeroCasa() {
		return numeroCasa;
	}

	// Funcion que setea el nombre del sector
	public String getSector() {
		return sector;
	}

	// Funcion que setea el nombre de la ciudad
	public void setCiudad(final String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDatos(String[] datos) {
		nombreCalle = datos[0];
		numeroCasa = datos[1];
		sector = datos[2];
		ciudad = datos[3];
	}

	// Funcion que setea el nombre de la calle
	public void setNombreCalle(final String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	// Funcion que setea el numero de casa
	public void setNumeroCasa(final String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	// Funcion que setea el nombre del sector
	public void setSector(final String sector) {
		this.sector = sector;
	}

}