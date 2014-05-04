// CargaImagen.java
package viewClass.extrasUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CargaImagen extends JPanel {
	private BufferedImage logo;
	private final String nombre;

	// Constructor por defecto
	public CargaImagen(final String logo) {
		nombre = logo;
		cargarImagen();
	}

	// Funcion que carga el logo de la app
	private void cargarImagen() {
		try {
			logo = ImageIO.read(getClass().getClassLoader().getResource(
					"img/" + nombre));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void insertarImagen(String img) {
		try {
			logo = ImageIO.read(getClass().getClassLoader().getResource(
					"img/" + img + ".jpg"));
			repaint();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Metodo sobreescrito
	public void paintComponent(final Graphics g) {
		g.drawImage(logo, 0, 0, getWidth(), getHeight(), this);
		super.paintComponents(g);
	}
}