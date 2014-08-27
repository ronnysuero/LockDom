package viewClass.extrasUI;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dataAccessClass.AccesoBD;

public class AvisosLegales extends JDialog {
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public AvisosLegales() {
		crearScrollPane();
		crearTextArea();
		agregarIconoFrame();
		definirVentana();
	}

	// Funcion que agrega un icono personalizado en la ventana de la aplicacion.
	private void agregarIconoFrame() {
		try {
			setIconImage(ImageIO.read(getClass().getClassLoader().getResource(
					"img/icon.png")));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void crearScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 470, 288);
		getContentPane().add(scrollPane);
	}

	private void crearTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(new AccesoBD().leerFicheroLegal("legal"));
		textArea.setCaretPosition(0);
		scrollPane.setViewportView(textArea);
	}

	private void definirVentana() {
		setSize(496, 339);
		setModal(true);
		setTitle("Información Legal Del Software");
		setLocationRelativeTo(getParent());
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setVisible(true);
	}
}