// Cuenta.java
package businessClass.businessEntities;

public class Cuenta {
	private long id;
	private String usuario;
	private String password;

	/**
	 * Constructor por argumentos
	 * 
	 * @param  usuario  Este parametro define el nombre del usuario
	 * @param  password Este parametro define el password del usuario
	 */
	public Cuenta(final String usuario, final String password) {
		this.usuario = usuario;
		this.password = password;
	}

	/**
	 * Funcion que devuelve el id de la cuenta
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Funcion que devuelve el password de la cuenta
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Funcion que devuelve el usuario de la cuenta
	 * 
	 * @return String
	 */
	public String getUsuario() {
		return usuario;
	}
}