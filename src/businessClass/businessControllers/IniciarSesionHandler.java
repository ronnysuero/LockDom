// iniciarSesionHandler.java
package businessClass.businessControllers;

import viewClass.IniciarSesion;
import viewClass.MenuPrincipal;
import viewClass.extrasUI.Popup;
import businessClass.businessEntities.Empleado;
import dataAccessClass.AccesoBD;

public class IniciarSesionHandler {
	private final IniciarSesion iniciarSesion;
	private final MenuPrincipal mp;

	public IniciarSesionHandler() {
		iniciarSesion = new IniciarSesion(this);
		iniciarSesion.setVisible(true);
		mp = new MenuPrincipal();
	}

	public void login(final String user, final String password) {
		final Empleado unEmpleado = new AccesoBD().consultarDB(user, password);

		if (unEmpleado == null)
			iniciarSesion.mostrarMensaje();
		else {
			iniciarSesion.dispose();
			mp.setVisible(true);
			
			new Popup("Bienvenido: " + unEmpleado.getNombre() + " "
					+ unEmpleado.getApellido(), mp).start();
		}
	}
}