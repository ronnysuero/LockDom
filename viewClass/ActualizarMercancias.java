// ActualizarMercancias.java
package viewClass;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import businessClass.businessControllers.ActualizarMercanciasHandler;

public class ActualizarMercancias extends JInternalFrame {
	public static final String NOMBRES_COLUMNAS[] = { "ID", "Tipo", "Marca",
			"Cantidad", "Precio" };

	private JButton boton_BuscarMercancia;
	private final ActualizarMercanciasHandler controlador;
	private JLabel lbl_opcional;
	private JTextField textField_Busqueda;
	private JTextField textField_CantidadMercancia;
	private JTextField textField_PrecioMercancia;
	private JTextField textField_MarcaMercancia;
	private JTextField textField_TipoMercancia;
	private JButton btnActualizarMercancia;

	// Constructor que inicializa los componentes graficos de la ventana
	public ActualizarMercancias(final String tituloVentana,
			final ActualizarMercanciasHandler controlador) {
		super(tituloVentana, false, // resizable
				true, // closable
				false, // maximizable
				true); // minimizable

		this.controlador = controlador;

		definirVentana();
		crearLabels();
		crearTextFields();
		crearBoton();

		MenuPrincipal.javaHelp.agregarHelp(this, "actualizar mercancia");
	}

	/*
	 * Funcion que crea un boton y una clase que interactuará como escucha del
	 * evento ActionListener
	 */
	private void crearBoton() {
		class AgregarEventoBoton implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == boton_BuscarMercancia) {
					controlador.buscarMercancia();
					textField_CantidadMercancia.requestFocus();
				} else if (e.getSource() == btnActualizarMercancia) {
					controlador.actualizarMercancia();
					limpiarTextFielsActualizados();
				}
			}
		}

		boton_BuscarMercancia = new JButton("Buscar Mercancia");
		boton_BuscarMercancia.setBounds(324, 53, 146, 23);
		boton_BuscarMercancia.setEnabled(false);
		boton_BuscarMercancia.addActionListener(new AgregarEventoBoton());
		getContentPane().add(boton_BuscarMercancia);

		btnActualizarMercancia = new JButton("Actualizar Mercancia");
		btnActualizarMercancia.setEnabled(false);
		btnActualizarMercancia.setBounds(316, 295, 154, 23);
		btnActualizarMercancia.addActionListener(new AgregarEventoBoton());
		getContentPane().add(btnActualizarMercancia);
	}

	// Funcion que crea los labels y los agrega al frame
	private void crearLabels() {
		lbl_opcional = new JLabel("Registro no encontrado en la base de datos.");
		lbl_opcional.setForeground(Color.RED);
		lbl_opcional.setBounds(10, 115, 274, 14);
		lbl_opcional.setVisible(false);
		getContentPane().add(lbl_opcional);
	}

	// Funcion que crea los textfields y los agrega al frame
	private void crearTextFields() {
		class AddKeyListener implements KeyListener {
			public void keyPressed(final KeyEvent e) {
				lbl_opcional.setVisible(false);
			}

			public void keyReleased(final KeyEvent e) {
				if (!textField_Busqueda.getText().isEmpty())
					boton_BuscarMercancia.setEnabled(true);
				else
					boton_BuscarMercancia.setEnabled(false);

				if (e.getSource() == textField_CantidadMercancia
						&& Integer.parseInt(textField_CantidadMercancia
								.getText()) > 5000) {
					textField_CantidadMercancia.setText("5000");
					JOptionPane.showMessageDialog(null, "Valor maximo: 5000");
				} else if (e.getSource() == textField_PrecioMercancia
						&& Integer
								.parseInt(textField_PrecioMercancia.getText()) > 10000) {
					textField_PrecioMercancia.setText("10000");
					JOptionPane.showMessageDialog(null, "Valor maximo: 10000");
				}
			}

			public void keyTyped(final KeyEvent e) {
				if ((e.getSource() == textField_CantidadMercancia
						|| e.getSource() == textField_PrecioMercancia || e
						.getSource() == textField_Busqueda)
						&& (e.getKeyChar() < '0' || e.getKeyChar() > '9')
						&& e.getKeyChar() != '\b')
					e.consume();

				else if (e.getSource() == textField_Busqueda
						&& (e.getKeyChar() == '\'' || e.getKeyChar() == '"'))
					e.consume();

				if (e.getSource() == textField_Busqueda
						&& textField_Busqueda.getText().length() > 8)
					e.consume();

				else if (e.getSource() == textField_CantidadMercancia
						&& textField_CantidadMercancia.getText().length() > 3)
					e.consume();

				else if (e.getSource() == textField_PrecioMercancia
						&& textField_PrecioMercancia.getText().length() > 4)
					e.consume();
			}
		}

		textField_Busqueda = new JTextField();
		textField_Busqueda.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Busqueda
				.setToolTipText("Ingrese un criterio para realizar la busqueda");
		textField_Busqueda.setBounds(10, 36, 174, 44);
		textField_Busqueda.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Codido de la mercancia", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Busqueda.addKeyListener(new AddKeyListener());
		getContentPane().add(textField_Busqueda);

		textField_CantidadMercancia = new JTextField();
		textField_CantidadMercancia
				.setHorizontalAlignment(SwingConstants.CENTER);
		textField_CantidadMercancia.setEnabled(false);
		textField_CantidadMercancia
				.setToolTipText("Modifique la cantidad de la mercancia");
		textField_CantidadMercancia.setBounds(10, 208, 201, 49);
		textField_CantidadMercancia.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Cantidad de la mercancia", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_CantidadMercancia.addKeyListener(new AddKeyListener());
		getContentPane().add(textField_CantidadMercancia);

		textField_PrecioMercancia = new JTextField();
		textField_PrecioMercancia.setHorizontalAlignment(SwingConstants.CENTER);
		textField_PrecioMercancia.setEnabled(false);
		textField_PrecioMercancia
				.setToolTipText("Modifique el precio de la mercancia");
		textField_PrecioMercancia.setBounds(269, 210, 201, 47);
		textField_PrecioMercancia
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Precio unitario de la mercancia", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_PrecioMercancia.addKeyListener(new AddKeyListener());
		getContentPane().add(textField_PrecioMercancia);

		textField_MarcaMercancia = new JTextField();
		textField_MarcaMercancia.setHorizontalAlignment(SwingConstants.CENTER);
		textField_MarcaMercancia.setEditable(false);
		textField_MarcaMercancia.setToolTipText("");
		textField_MarcaMercancia.setBounds(269, 140, 201, 44);
		textField_MarcaMercancia.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Marca de la mercancia", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(textField_MarcaMercancia);

		textField_TipoMercancia = new JTextField();
		textField_TipoMercancia.setHorizontalAlignment(SwingConstants.CENTER);
		textField_TipoMercancia.setToolTipText("");
		textField_TipoMercancia.setEditable(false);
		textField_TipoMercancia.setBounds(10, 140, 201, 44);
		textField_TipoMercancia.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Tipo de la mercancia", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.BLACK));
		getContentPane().add(textField_TipoMercancia);
	}

	// Funcion que define los parametros y configuraciones que tendra la ventana
	private void definirVentana() {
		setSize(497, 368);
		getContentPane().setLayout(null);
		setFrameIcon(null);
		setLocation(210, 100);
		setBorder(new LineBorder(new Color(0, 0, 204)));
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Funcion que retorna el valor del textfield: Cantidad
	public int getCantidadTextField() {
		return Integer.parseInt(textField_CantidadMercancia.getText());
	}

	// Funcion que retorna la cadena de busqueda
	public String getDatoTextField() {
		final String texto = textField_Busqueda.getText().toLowerCase();
		limpiarTextField();

		return texto;
	}

	// Funcion que retorna el valor del textfield: Precio
	public double getPrecioTextField() {
		return Double.parseDouble(textField_PrecioMercancia.getText());
	}

	// Funcion que limpia el textfield despues de haber extraido su informacion
	private void limpiarTextField() {
		textField_Busqueda.setText("");
		boton_BuscarMercancia.setEnabled(false);
		textField_Busqueda.requestFocus();
	}

	// Funcion que deja en blancos los valores de los textfields
	private void limpiarTextFielsActualizados() {
		textField_TipoMercancia.setText("");
		textField_MarcaMercancia.setText("");
		textField_PrecioMercancia.setText("");
		textField_CantidadMercancia.setText("");

		textField_PrecioMercancia.setEnabled(false);
		textField_CantidadMercancia.setEnabled(false);
		btnActualizarMercancia.setEnabled(false);
		textField_Busqueda.requestFocus();
	}

	// Funcion que despliega en el JInternalFrame un mensaje avisando que no se
	// encontro el registro
	public void mostrarMensaje() {
		lbl_opcional.setVisible(true);
		limpiarTextFielsActualizados();
	}

	// Funcion que despliega en el textfields la busqueda hecha en la base de
	// datos
	public void setDatos(final String tipo, final String marca,
			final int cantidad, final double precio) {
		textField_TipoMercancia.setText(tipo);
		textField_MarcaMercancia.setText(marca);
		textField_PrecioMercancia.setText(String.valueOf(precio));
		textField_CantidadMercancia.setText(String.valueOf(cantidad));

		textField_PrecioMercancia.setEnabled(true);
		textField_CantidadMercancia.setEnabled(true);
		btnActualizarMercancia.setEnabled(true);
	}
}