// RegistrarMercanciasHandler.java
package businessClass.businessControllers;

import java.util.Vector;

import javax.swing.JInternalFrame;

import viewClass.MenuPrincipal;
import viewClass.ModificarClientes;
import viewClass.extrasUI.Popup;
import businessClass.businessEntities.Cliente;
import dataAccessClass.AccesoBD;

public class ModificarClientesHandler {
	private ModificarClientes actualizarCliente;
	private Cliente unCliente;
	private final MenuPrincipal menu;

	public ModificarClientesHandler(final MenuPrincipal mp) {
		menu = mp;
	}

	// Funcion que envia los datos actualizados a la base de datos para su
	// actualizacion
	public void actualizarCliente() {
		unCliente.setDatos(actualizarCliente.getDatosPersonales());
		unCliente.getDireccion()
				.setDatos(actualizarCliente.getDatosDireccion());

		if (new AccesoBD().actualizarCliente(unCliente))
			new Popup("Registro actualizado exitosamente :)", menu).start();
	}

	// Funcion que reliza la consulta de una mercancia en especifico
	public void buscarCliente(String busqueda) {
		unCliente = new AccesoBD().buscarClienteEspecifico(busqueda);

		if (unCliente == null)
			actualizarCliente.mostrarMensaje();
		else
			actualizarCliente.setDatos(unCliente.getNombre(), unCliente
					.getApellido(), unCliente.getCedula(), unCliente
					.getDireccion().getNombreCalle(), unCliente.getDireccion()
					.getNumeroCasa(), unCliente.getDireccion().getSector(),
					unCliente.getDireccion().getCiudad());
	}

	public Vector<String> cargarCiudades() {
		return new AccesoBD().consultarCiudades();
	}

	// Funcion que inicializa la ventana del modulo: Registro de mercancias
	public JInternalFrame iniciarJInternalFrame() {
		actualizarCliente = new ModificarClientes(
				"Modulo de actualización de clientes", this);
		actualizarCliente.setVisible(true);

		return actualizarCliente;
	}
}