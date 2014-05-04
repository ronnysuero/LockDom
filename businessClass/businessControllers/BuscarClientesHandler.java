// buscarClientesHandler.java
package businessClass.businessControllers;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

import viewClass.BuscarClientes;
import businessClass.businessEntities.Cliente;
import dataAccessClass.AccesoBD;

public class BuscarClientesHandler {
	private BuscarClientes buscarClientes;

	/*
	 * Funcion que almacena los datos capturados del view, lo almacena en una
	 * clase Cliente, y por ultimo se le envia al DataAccess para almacenarlo en
	 * la base de datos
	 */
	public void buscarCliente(String busqueda) {
		final List<Cliente> listadoClientes = new AccesoBD()
				.buscarCliente(busqueda);

		final DefaultTableModel modeloTabla = new DefaultTableModel(null,
				BuscarClientes.NOMBRES_COLUMNAS) {
			// Evita que las celdas de la tabla sean editables
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};

		if (!listadoClientes.isEmpty()) {
			final String celdas[] = new String[BuscarClientes.NOMBRES_COLUMNAS.length];

			for (final Cliente c : listadoClientes) {
				celdas[0] = String.valueOf(c.getId());
				celdas[1] = c.getNombre();
				celdas[2] = c.getApellido();
				celdas[3] = c.getCedula();
				celdas[4] = c.getDireccion().getNombreCalle();
				celdas[5] = c.getDireccion().getNumeroCasa();
				celdas[6] = c.getDireccion().getSector();
				celdas[7] = c.getDireccion().getCiudad();

				modeloTabla.addRow(celdas);
			}
		} else
			buscarClientes.mostrarMensaje();

		buscarClientes.setModelTable(modeloTabla);
	}

	// Funcion que inicializa la ventana del modulo: Busqueda de mercancias
	public JInternalFrame iniciarJInternalFrame() {
		buscarClientes = new BuscarClientes("Modulo de busqueda de clientes",
				this);
		buscarClientes.setVisible(true);

		return buscarClientes;
	}
}