/**
 * Direccion.java
 *
 * @author Ronny Z. Suero
 */
package businessClass.businessEntities;

public class Direccion {
	private long id;
	private String nombreCalle;
	private String numeroCasa;
	private String sector;
	private String ciudad;

	/**
	 * Constructor por argumentos
	 * 
	 * @param  id          Este parametro define el id de la direccion
	 * @param  nombreCalle Este parametro define el nombre de la calle
	 * @param  numeroCasa  Este parametro define el numero de la casa
	 * @param  sector      Este parametro define el nombre del sector
	 * @param  ciudad      Este parametro define el nombre de la ciudad
	 */
	public Direccion(final long id, final String nombreCalle,
			final String numeroCasa, final String sector, final String ciudad) {
		this.id = id;
		this.nombreCalle = nombreCalle;
		this.numeroCasa = numeroCasa;
		this.sector = sector;
		this.ciudad = ciudad;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  nombreCalle Este parametro define el nombre de la calle
	 * @param  numeroCasa  Este parametro define el numero de la casa
	 * @param  sector      Este parametro define el nombre del sector
	 * @param  ciudad      Este parametro define el nombre de la ciudad
	 */
	public Direccion(final String nombreCalle, final String numeroCasa,
			final String sector, final String ciudad) {
		this.nombreCalle = nombreCalle;
		this.numeroCasa = numeroCasa;
		this.sector = sector;
		this.ciudad = ciudad;
	}

	/**
	 * Constructor por argumentos
	 * 
	 * @param  datos          Este parametro define un array 
	 *                        con los datos de la direccion
	 */
	public Direccion(final String[] datos) {
		nombreCalle = datos[0];
		numeroCasa = datos[1];
		sector = datos[2];
		ciudad = datos[3];
	}

	/**
	 * Funcion que retorna el nombre de la ciudad
	 * 
	 * @return String
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Funcion que retorna el id de la direccion
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Funcion que retorna el nombre de la calle
	 * 
	 * @return String
	 */
	public String getNombreCalle() {
		return nombreCalle;
	}

	/**
	 * Funcion que retorna el numero de casa
	 * 
	 * @return String
	 */
	public String getNumeroCasa() {
		return numeroCasa;
	}

	/**
	 * Funcion que setea el nombre del sector
	 * 
	 * @return String
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Funcion que setea el nombre de la ciudad
	 * 
	 * @param ciudad Este parametro define el nombre 
	 *               de la ciudad a actualizar
	 *
	 * @return none
	 */
	public void setCiudad(final String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Funcion que actualiza los datos de la direccion
	 * 
	 * @param datos Este parametro define un array con 
	 *              los datos de la direccion
	 *
	 * @return none
	 */
	public void setDatos(String[] datos) {
		nombreCalle = datos[0];
		numeroCasa = datos[1];
		sector = datos[2];
		ciudad = datos[3];
	}

	/**
	 * Funcion que setea el nombre de la calle
	 * 
	 * @param nombreCalle Este parametro define el nomnbre de la calle
	 *
	 * @return none
	 */
	public void setNombreCalle(final String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	/**
	 * Funcion que setea el numero de casa
	 * 
	 * @param numeroCasa Este parametro define el numero de la casa
	 *
	 * @return none
	 */
	public void setNumeroCasa(final String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	/**
	 * Funcion que setea el nombre del sector
	 * 
	 * @param sector Este parametro define el nombre del sector
	 *
	 * @return none
	 */
	public void setSector(final String sector) {
		this.sector = sector;
	}

}