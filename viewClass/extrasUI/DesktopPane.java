// DesktopPane.java
package viewClass.extrasUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class DesktopPane extends JDesktopPane {
	private BufferedImage fondo;

	// Constructor por defecto
	public DesktopPane() {
		super();
		cargarImagen();
	}

	// Funcion que carga la imagen de fondo del JDesktopPane
	private void cargarImagen() {
		try {
			fondo = ImageIO.read(getClass().getClassLoader().getResource(
					"img/fondo.jpg"));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void paintChildren(final Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		super.paintChildren(g);
	}
}