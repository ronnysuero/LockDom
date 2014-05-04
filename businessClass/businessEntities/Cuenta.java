// Cuenta.java
package businessClass.businessEntities;

public class Cuenta {
	private long id;
	private String usuario;
	private String password;

	// Constructor por argumentos
	public Cuenta(final String usuario, final String password) {
		this.usuario = usuario;
		this.password = password;
	}

	// Funcion que devuelve el id de la cuenta
	public long getId() {
		return id;
	}

	// Funcion que devuelve el password de la cuenta
	public String getPassword() {
		return password;
	}

	// Funcion que devuelve el usuario de la cuenta
	public String getUsuario() {
		return usuario;
	}
}