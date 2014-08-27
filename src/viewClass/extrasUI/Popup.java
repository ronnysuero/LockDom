// Popup.java
package viewClass.extrasUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import viewClass.MenuPrincipal;

/* Clase que le notificara al usuario de cualquier evento
 que ocurra en el sistema. */
public class Popup extends Thread {
	// Clase interna para definir la ventana emergente
	static final class VentanaEmergente extends JDialog {
		private JLabel lbl_mensaje;

		// Constructor por defecto
		public VentanaEmergente() {
			definirVentana();
			crearLabel();
		}

		// Funcion que crea una etiqueta
		private void crearLabel() {
			lbl_mensaje = new JLabel("");
			lbl_mensaje.setFont(new Font("Tahoma", Font.BOLD, 17));
			getContentPane().add(lbl_mensaje);
		}

		// Funcion que define los limites de la ventana
		private void definirVentana() {
			getContentPane().setBackground(Color.WHITE);
			setSize(450, 80);
			getContentPane().setLayout(
					new FlowLayout(FlowLayout.CENTER, 30, 30));
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setAlwaysOnTop(true);
			setResizable(false);
			setUndecorated(true);
			setVisible(true);
		}

		// Funcion que modifica el texto de la ventana
		public void modificarAlerta(final String mensaje) {
			lbl_mensaje.setText(mensaje);
		}

		/*
		 * Funcion que posicionara la ventana emergente en una determinada
		 * posisicion en la pantalla
		 */
		public void ubicacionVentana(final int x, final int y) {
			final int puntoX = x - getWidth() - 20;
			final int puntoY = y - getHeight() - 20;

			setLocation(puntoX, puntoY);
		}
	}// Fin de la clase interna: VentanaEmergente

	private final VentanaEmergente ventana;
	private float transparencia;

	private final int TIEMPO = 3000;

	// Constructor por argumentos
	public Popup(final String nombre, MenuPrincipal mp) {
		ventana = new VentanaEmergente();
		ajustarTamano(mp.getWidth(), mp.getHeight());
		agregarTexto(nombre);
	}

	// Funcion que agrega el texto a la ventana
	private void agregarTexto(final String msj) {
		ventana.modificarAlerta(msj);
	}

	// Funcion que ajusta el tamaño de la ventana
	private void ajustarTamano(final int x, final int y) {
		ventana.ubicacionVentana(x, y);
	}

	// Funcion que realiza un efecto al cerrar la ventana
	private void desvanecer() {
		try {
			transparencia = 1.0f;
			while (transparencia > 0) {
				ventana.setOpacity(transparencia);
				transparencia -= 0.03f;
				Thread.sleep(50);
			}
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Funcion que realiza un efecto al abrir la ventana
	private void hacerVisible() {
		try {
			transparencia = 0.3f;
			while (transparencia < 1) {
				ventana.setOpacity(transparencia);
				transparencia += 0.03f;
				Thread.sleep(30);
			}
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void run() {
		try {
			hacerVisible();
			Thread.sleep(TIEMPO);
			desvanecer();
			ventana.dispose();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}