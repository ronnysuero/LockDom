// BuscarMercanciasHandler.java
package businessClass.businessControllers;

import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

import viewClass.BuscarMercancias;
import businessClass.businessEntities.Mercancia;
import dataAccessClass.AccesoBD;

public class BuscarMercanciasHandler {
	private BuscarMercancias buscarMercancias;

	/*
	 * Funcion que almacena los datos capturados del view, lo almacena en una
	 * clase Mercancia, y por ultimo se le envia al DataAccess para almacenarlo
	 * en la base de datos
	 */
	public void buscarMercancia(String busqueda) {
		final List<Mercancia> listadoMercancia = new AccesoBD()
				.buscarMercancia(busqueda);

		final DefaultTableModel modeloTabla = new DefaultTableModel(null,
				BuscarMercancias.NOMBRES_COLUMNAS) {
			// Evita que las celdas de la tabla sean editables
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};

		if (!listadoMercancia.isEmpty()) {
			final String celdas[] = new String[BuscarMercancias.NOMBRES_COLUMNAS.length];

			for (final Mercancia m : listadoMercancia) {
				celdas[0] = String.valueOf(m.getId());
				celdas[1] = m.getTipo();
				celdas[2] = m.getMarca();
				celdas[3] = String.valueOf(m.getCantidad());
				celdas[4] = String.valueOf(m.getPrecio());

				modeloTabla.addRow(celdas);
			}
		} else
			buscarMercancias.mostrarMensaje();

		buscarMercancias.setModelTable(modeloTabla);
	}

	// Funcion que inicializa la ventana del modulo: Busqueda de mercancias
	public JInternalFrame iniciarJInternalFrame() {
		buscarMercancias = new BuscarMercancias(
				"Modulo de busqueda de mercancias", this);
		buscarMercancias.setVisible(true);

		return buscarMercancias;
	}
}