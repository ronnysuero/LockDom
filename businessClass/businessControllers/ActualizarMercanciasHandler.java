//ActualizarMercanciasHandler.java
package businessClass.businessControllers;

import javax.swing.JInternalFrame;

import viewClass.ActualizarMercancias;
import viewClass.MenuPrincipal;
import viewClass.extrasUI.Popup;
import businessClass.businessEntities.Mercancia;
import dataAccessClass.AccesoBD;

public class ActualizarMercanciasHandler {
	private ActualizarMercancias actualizarMercancia;
	private Mercancia unaMercancia;
	private final MenuPrincipal menu;

	public ActualizarMercanciasHandler(final MenuPrincipal mp) {
		menu = mp;
	}

	// Funcion que envia los datos actualizados a la base de datos para su
	// actualizacion
	public void actualizarMercancia() {
		unaMercancia.setCantidad(actualizarMercancia.getCantidadTextField());
		unaMercancia.setPrecio(actualizarMercancia.getPrecioTextField());

		if (new AccesoBD().actualizarMercancia(unaMercancia))
			new Popup("Registro actualizado exitosamente :)", menu).start();
	}

	// Funcion que reliza la consulta de una mercancia en especifico
	public void buscarMercancia() {
		final String datoBusqueda = actualizarMercancia.getDatoTextField();
		unaMercancia = new AccesoBD().buscarMercanciaEspecifica(datoBusqueda);

		if (unaMercancia == null)
			actualizarMercancia.mostrarMensaje();
		else
			actualizarMercancia.setDatos(unaMercancia.getTipo(),
					unaMercancia.getMarca(), unaMercancia.getCantidad(),
					unaMercancia.getPrecio());
	}

	// Funcion que inicializa la ventana del modulo: Busqueda de mercancias
	public JInternalFrame iniciarJInternalFrame() {
		actualizarMercancia = new ActualizarMercancias(
				"Modulo de actualización de mercancias", this);
		actualizarMercancia.setVisible(true);

		return actualizarMercancia;
	}
}