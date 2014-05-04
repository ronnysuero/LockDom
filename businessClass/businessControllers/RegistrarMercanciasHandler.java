// RegistrarMercanciasHandler.java
package businessClass.businessControllers;

import javax.swing.JInternalFrame;

import viewClass.MenuPrincipal;
import viewClass.RegistrarMercancias;
import viewClass.extrasUI.Popup;
import businessClass.businessEntities.Mercancia;
import dataAccessClass.AccesoBD;

public class RegistrarMercanciasHandler {
	private RegistrarMercancias registrarMercancias;
	private final MenuPrincipal menu;

	public RegistrarMercanciasHandler(final MenuPrincipal mp) {
		menu = mp;
	}

	// Funcion que inicializa la ventana del modulo: Registro de mercancias
	public JInternalFrame iniciarJInternalFrame() {
		registrarMercancias = new RegistrarMercancias(
				"Modulo de registro de mercancias", this);
		registrarMercancias.setVisible(true);

		return registrarMercancias;
	}

	/*
	 * Funcion que almacena los datos capturados del view en una clase
	 * Mercancia, y por ultimo se le envia al DataAccess para almacenarlo en la
	 * base de datos
	 */
	public void registrarMercancia(String[] datos) {
		if (new AccesoBD().almacenarMercancia(new Mercancia(datos)))
			new Popup("Registro almacenado exitosamente :)", menu).start();
	}
}