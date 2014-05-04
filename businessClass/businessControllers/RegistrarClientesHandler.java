// RegistrarMercanciasHandler.java
package businessClass.businessControllers;

import java.util.Vector;

import javax.swing.JInternalFrame;

import viewClass.MenuPrincipal;
import viewClass.RegistrarClientes;
import viewClass.extrasUI.Popup;
import businessClass.businessEntities.Cliente;
import businessClass.businessEntities.Direccion;
import dataAccessClass.AccesoBD;

public class RegistrarClientesHandler {
	private RegistrarClientes registrarClientes;
	private final MenuPrincipal menu;

	public RegistrarClientesHandler(final MenuPrincipal mp) {
		menu = mp;
	}

	public Vector<String> cargarCiudades() {
		return new AccesoBD().consultarCiudades();
	}

	// Funcion que inicializa la ventana del modulo: Registro de mercancias
	public JInternalFrame iniciarJInternalFrame() {
		registrarClientes = new RegistrarClientes(
				"Modulo de registro de clientes", this);
		registrarClientes.setVisible(true);

		return registrarClientes;
	}

	/*
	 * Funcion que almacena los datos capturados del view en una clase Cliente,
	 * y por ultimo se le envia al DataAccess para almacenarlo en la base de
	 * datos
	 */
	public void registrarCliente(String[] datosPersonales,
			String[] datosDireccion) {
		if (new AccesoBD().almacenarCliente(new Cliente(datosPersonales,
				new Direccion(datosDireccion))))
			new Popup("Registro almacenado exitosamente :)", menu).start();
	}
}