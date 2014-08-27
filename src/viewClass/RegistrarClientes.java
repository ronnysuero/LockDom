/**
 * RegistrarClientes.java
 *
 * @author Ronny Z. Suero
 */
package viewClass;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import businessClass.businessControllers.RegistrarClientesHandler;

public class RegistrarClientes extends JInternalFrame {
	private final RegistrarClientesHandler controlador;
	private JTextField textField_Cedula;
	private JTextField textField_Apellido;
	private JTextField textField_Nombre;
	private JPanel panel_DatosPersonales;
	private JPanel panel_Direccion;
	private JTextField textField_NombreCalle;
	private JTextField textField_Sector;
	private JButton btnAlmacenarCliente;
	private JComboBox<String> comboBox_Listado;
	private JTextField textField_Numero;

	/**
	 * Constructor que inicializa los componentes graficos de la ventana
	 * @param  tituloVentana Este atributo define el titulo que tendra la ventana
	 * @param  controlador   Este atributo define el controlador del frame RegistrarClientes
	 */
	public RegistrarClientes(final String tituloVentana,
			final RegistrarClientesHandler controlador) {
		super(tituloVentana, false, // resizable
				true, // closable
				false, // maximizable
				true); // mininizable

		this.controlador = controlador;

		crearPaneles();
		crearTextFields();
		crearBoton();
		crearComboBox();
		definirVentana();

		MenuPrincipal.javaHelp.agregarHelp(this, "registro cliente");
	}

	/**
	 * Funcion que crea un boton y una clase que interactuará como escucha del
	 * evento ActionListener
	 *
	 * @return none
	 */
	private void crearBoton() {
		class AgregarEventoBoton implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				controlador.registrarCliente(getDatosPersonales(),
						getDatosDireccion());
				textField_Nombre.requestFocus();
			}
		}

		btnAlmacenarCliente = new JButton("Almacenar Cliente");
		btnAlmacenarCliente.setBounds(493, 341, 146, 23);
		btnAlmacenarCliente.addActionListener(new AgregarEventoBoton());
		getContentPane().add(btnAlmacenarCliente);
		btnAlmacenarCliente.setEnabled(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Funcion que crea un combo box y lo agrega al panel de direccion
	 *
	 * @return none
	 */
	private void crearComboBox() {
		comboBox_Listado = new JComboBox<String>();
		comboBox_Listado.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_Listado.setVerifyInputWhenFocusTarget(false);
		comboBox_Listado
				.setToolTipText("Seleccione una de las siguientes cuidades");
		comboBox_Listado.setModel(new DefaultComboBoxModel<String>(controlador
				.cargarCiudades()));
		comboBox_Listado
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Lista de ciudades", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		comboBox_Listado.setBackground(Color.WHITE);
		comboBox_Listado.setBounds(10, 21, 190, 50);
		panel_Direccion.add(comboBox_Listado);
	}

	/**
	 * Funcion que define los paneles y los agrega al jinternalframe
	 *
	 * @return none
	 */
	private void crearPaneles() {
		panel_DatosPersonales = new JPanel();
		panel_DatosPersonales.setLayout(null);
		panel_DatosPersonales.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Datos personales", TitledBorder.LEFT,
				TitledBorder.TOP, null, Color.BLACK));
		panel_DatosPersonales.setBackground(Color.WHITE);
		panel_DatosPersonales.setBounds(10, 11, 629, 105);
		getContentPane().add(panel_DatosPersonales);

		panel_Direccion = new JPanel();
		panel_Direccion.setLayout(null);
		panel_Direccion.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Datos del domicilio", TitledBorder.LEFT, TitledBorder.TOP,
				null, Color.BLACK));
		panel_Direccion.setBackground(Color.WHITE);
		panel_Direccion.setBounds(10, 147, 463, 217);
		getContentPane().add(panel_Direccion);
	}

	/**
	 * Funcion que crea los textfields y los agrega a sus respectivos paneles
	 *
	 * @return none
	 */
	private void crearTextFields() {
		class AgregarEvento implements KeyListener {
			public void keyPressed(final KeyEvent e) {
				if (e.getSource() == textField_Cedula
						&& e.getKeyCode() != KeyEvent.VK_BACK_SPACE)
					if (textField_Cedula.getText().length() == 3
							|| textField_Cedula.getText().length() == 11)
						textField_Cedula.setText(textField_Cedula.getText()
								+ "-");
			}

			public void keyReleased(final KeyEvent e) {
				if (!textField_Nombre.getText().isEmpty()
						&& !textField_Apellido.getText().isEmpty()
						&& !textField_Cedula.getText().isEmpty()
						&& !textField_NombreCalle.getText().isEmpty()
						&& !textField_Numero.getText().isEmpty()
						&& !textField_Sector.getText().isEmpty()
						&& textField_Cedula.getText().length() == 13)
					btnAlmacenarCliente.setEnabled(true);
				else
					btnAlmacenarCliente.setEnabled(false);

				if (e.getSource() == textField_Numero
						&& textField_Numero.getText() != "")
					if (Integer.parseInt(textField_Numero.getText()) > 2000) {
						textField_Numero.setText("2000");
						JOptionPane.showMessageDialog(null,
								"Valor maximo: 2000");
					}
			}

			public void keyTyped(final KeyEvent e) {
				if (e.getSource() == textField_Numero
						|| e.getSource() == textField_Cedula) {
					if ((e.getKeyChar() < '0' || e.getKeyChar() > '9')
							&& e.getKeyChar() != '\b')
						e.consume();
				} else if (e.getSource() == textField_Nombre
						|| e.getSource() == textField_Apellido
						|| e.getSource() == textField_Cedula
						|| e.getSource() == textField_NombreCalle
						|| e.getSource() == textField_Numero
						|| e.getSource() == textField_Sector)
					if (e.getKeyChar() == '\'' || e.getKeyChar() == '"')
						e.consume();

				if (e.getSource() == textField_Cedula
						&& textField_Cedula.getText().length() > 12)
					e.consume();

				else if (e.getSource() == textField_Nombre
						&& textField_Nombre.getText().length() > 15)
					e.consume();

				else if (e.getSource() == textField_Apellido
						&& textField_Apellido.getText().length() > 15)
					e.consume();

				else if (e.getSource() == textField_NombreCalle
						&& textField_NombreCalle.getText().length() > 20)
					e.consume();

				else if (e.getSource() == textField_Sector
						&& textField_Sector.getText().length() > 20)
					e.consume();

				else if (e.getSource() == textField_Numero
						&& textField_Numero.getText().length() > 3)
					e.consume();
			}
		}

		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(10, 31, 190, 47);
		panel_DatosPersonales.add(textField_Nombre);
		textField_Nombre.setToolTipText("Ingrese el nombre del cliente");
		textField_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Nombre.addKeyListener(new AgregarEvento());
		textField_Nombre
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Nombre del cliente", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));

		textField_Apellido = new JTextField();
		textField_Apellido.setBounds(220, 31, 190, 47);
		panel_DatosPersonales.add(textField_Apellido);
		textField_Apellido.setToolTipText("Ingrese el apellido del cliente");
		textField_Apellido.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Apellido.addKeyListener(new AgregarEvento());
		textField_Apellido.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Apellido del cliente", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));

		textField_Cedula = new JTextField();
		textField_Cedula.setBounds(429, 31, 190, 47);
		panel_DatosPersonales.add(textField_Cedula);
		textField_Cedula.setToolTipText("Ingrese la cedula del cliente");
		textField_Cedula.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Cedula.addKeyListener(new AgregarEvento());
		textField_Cedula
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Cedula del cliente", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));

		textField_NombreCalle = new JTextField();
		textField_NombreCalle.setToolTipText("Ingrese el nombre de la calle");
		textField_NombreCalle.setHorizontalAlignment(SwingConstants.CENTER);
		textField_NombreCalle.addKeyListener(new AgregarEvento());
		textField_NombreCalle.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Nombre de la calle", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_NombreCalle.setBounds(260, 159, 190, 47);
		panel_Direccion.add(textField_NombreCalle);

		textField_Sector = new JTextField();
		textField_Sector.setToolTipText("Ingrese el nombre del sector");
		textField_Sector.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Sector.addKeyListener(new AgregarEvento());
		textField_Sector
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Nombre del sector", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Sector.setBounds(260, 23, 190, 47);
		panel_Direccion.add(textField_Sector);

		textField_Numero = new JTextField();
		textField_Numero.setToolTipText("Ingrese el numero de la casa");
		textField_Numero.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Numero.addKeyListener(new AgregarEvento());
		textField_Numero
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Numero de la casa", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Numero.setBounds(10, 159, 190, 47);
		panel_Direccion.add(textField_Numero);
	}

	/**
	 * Funcion que define los parametros y configuraciones que tendra la ventana
	 *
	 * @return none
	 */
	private void definirVentana() {
		setSize(659, 405);
		getContentPane().setLayout(null);
		setFrameIcon(null);
		setLocation(190, 100);
		setBorder(new LineBorder(new Color(0, 0, 204)));
		getContentPane().setBackground(Color.WHITE);
	}

	/**
	 * Funcion que retorna los datos de la direccion de un cliente en array
	 *
	 * @return String[]
	 */
	private String[] getDatosDireccion() {
		final String[] datos = { textField_NombreCalle.getText().toLowerCase(),
				textField_Numero.getText(),
				textField_Sector.getText().toLowerCase(),
				comboBox_Listado.getSelectedItem().toString() };

		limpiarTextFields();

		return datos;
	}

	/**
	 *Funcion que retorna los datos personales de un cliente de un cliente en array
	 *
	 * @return String[]
	 */
	private String[] getDatosPersonales() {
		final String[] datos = { textField_Nombre.getText().toLowerCase(),
				textField_Apellido.getText().toLowerCase(),
				textField_Cedula.getText() };

		return datos;

	}

	/**
	 * Funcion que limpia los textfield despues de haber extraido su informacion
	 *
	 * @return none
	 */
	private void limpiarTextFields() {
		textField_Nombre.setText("");
		textField_Apellido.setText("");
		textField_Cedula.setText("");
		textField_NombreCalle.setText("");
		textField_Sector.setText("");
		textField_Numero.setText("");
		comboBox_Listado.setSelectedIndex(0);
		btnAlmacenarCliente.setEnabled(false);
		textField_Nombre.requestFocus();
	}
}