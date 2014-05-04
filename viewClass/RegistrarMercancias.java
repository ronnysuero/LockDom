//RegistrarMercancias.java
package viewClass;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import businessClass.businessControllers.RegistrarMercanciasHandler;

public class RegistrarMercancias extends JInternalFrame {
	private final RegistrarMercanciasHandler controlador;

	private JButton boton_GuardarMercancia;
	private JTextField textField_Cantidad;
	private JTextField textField_Marca;
	private JTextField textField_Precio;
	private JTextField textField_Tipo;

	// Constructor que inicializa los componentes graficos de la ventana
	public RegistrarMercancias(final String tituloVentana,
			final RegistrarMercanciasHandler controlador) {
		super(tituloVentana, false, // resizable
				true, // closable
				false, // maximizable
				true); // minimizable

		this.controlador = controlador;

		crearTextFields();
		crearBoton();
		definirVentana();

		MenuPrincipal.javaHelp.agregarHelp(this, "registro mercancia");
	}

	/*
	 * Funcion que crea un boton y una clase que interactuará como escucha del
	 * evento ActionListener
	 */
	private void crearBoton() {
		class AgregarEventoBoton implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				controlador.registrarMercancia(getDatosTextFields());

				textField_Tipo.requestFocus();
			}
		}

		boton_GuardarMercancia = new JButton("Guardar Mercancia");
		boton_GuardarMercancia.setBounds(319, 195, 146, 23);
		boton_GuardarMercancia.setEnabled(false);
		boton_GuardarMercancia.addActionListener(new AgregarEventoBoton());
		getContentPane().add(boton_GuardarMercancia);
	}

	// Funcion que crea los textfields y los agrega al frame
	private void crearTextFields() {
		class Validacion implements KeyListener {
			public void keyPressed(final KeyEvent e) { /**/
			}

			public void keyReleased(final KeyEvent e) {
				if (!textField_Tipo.getText().isEmpty()
						&& !textField_Marca.getText().isEmpty()
						&& !textField_Cantidad.getText().isEmpty()
						&& !textField_Precio.getText().isEmpty())
					boton_GuardarMercancia.setEnabled(true);
				else
					boton_GuardarMercancia.setEnabled(false);

				if (e.getSource() == textField_Cantidad
						&& Integer.parseInt(textField_Cantidad.getText()) > 5000) {
					textField_Cantidad.setText("5000");
					JOptionPane.showMessageDialog(null, "Valor maximo: 5000");
				} else if (e.getSource() == textField_Precio
						&& Integer.parseInt(textField_Precio.getText()) > 10000) {
					textField_Precio.setText("10000");
					JOptionPane.showMessageDialog(null, "Valor maximo: 10000");
				}
			}

			public void keyTyped(final KeyEvent e) {
				if ((e.getSource() == textField_Cantidad || e.getSource() == textField_Precio)
						&& (e.getKeyChar() < '0' || e.getKeyChar() > '9')
						&& e.getKeyChar() != '\b')
					e.consume();

				else if ((e.getSource() == textField_Tipo || e.getSource() == textField_Marca)
						&& (e.getKeyChar() == '\'' || e.getKeyChar() == '"'))
					e.consume();

				else if (e.getSource() == textField_Tipo
						&& textField_Tipo.getText().length() > 15)
					e.consume();

				else if (e.getSource() == textField_Marca
						&& textField_Marca.getText().length() > 15)
					e.consume();

				else if (e.getSource() == textField_Cantidad
						&& textField_Cantidad.getText().length() > 3)
					e.consume();

				else if (e.getSource() == textField_Precio
						&& textField_Precio.getText().length() > 4)
					e.consume();
			}
		}

		textField_Tipo = new JTextField();
		textField_Tipo.setToolTipText("Ingrese el tipo del producto");
		textField_Tipo.setBounds(10, 29, 190, 47);
		textField_Tipo.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Tipo.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Tipo de mercancia", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Tipo.addKeyListener(new Validacion());
		getContentPane().add(textField_Tipo);

		textField_Marca = new JTextField();
		textField_Marca
				.setToolTipText("Ingrese la marca especifica del producto");
		textField_Marca.setBounds(275, 29, 190, 47);
		textField_Marca.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Marca.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Marca de la mercancia", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Marca.addKeyListener(new Validacion());
		getContentPane().add(textField_Marca);

		textField_Cantidad = new JTextField();
		textField_Cantidad
				.setToolTipText("Ingrese la cantidad total que hay del producto");
		textField_Cantidad.setBounds(10, 134, 190, 47);
		textField_Cantidad.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Cantidad.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Cantidad de la mercancia", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Cantidad.addKeyListener(new Validacion());
		getContentPane().add(textField_Cantidad);

		textField_Precio = new JTextField();
		textField_Precio
				.setToolTipText("Ingrese el precio a que se vender\u00E1 cada producto");
		textField_Precio.setBounds(275, 134, 190, 47);
		textField_Precio.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Precio
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Precio de la mercancia", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Precio.addKeyListener(new Validacion());
		getContentPane().add(textField_Precio);
	}

	// Funcion que define los parametros y configuraciones que tendra la ventana
	private void definirVentana() {
		setSize(491, 259);
		getContentPane().setLayout(null);
		setFrameIcon(null);
		setLocation(190, 100);
		setBorder(new LineBorder(new Color(0, 0, 204)));
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Funcion que retorna los datos de todos los textfields en un arreglo
	private String[] getDatosTextFields() {
		final String[] datos = { textField_Tipo.getText().toLowerCase(),
				textField_Marca.getText().toLowerCase(),
				textField_Cantidad.getText(), textField_Precio.getText() };

		limpiarTextFields();
		return datos;
	}

	// Funcion que limpia los textfield despues de haber extraido su informacion
	private void limpiarTextFields() {
		textField_Tipo.setText("");
		textField_Marca.setText("");
		textField_Precio.setText("");
		textField_Cantidad.setText("");
		boton_GuardarMercancia.setEnabled(false);
	}
}