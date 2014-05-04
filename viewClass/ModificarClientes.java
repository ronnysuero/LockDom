// ModificarClientes.java
package viewClass;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import businessClass.businessControllers.ModificarClientesHandler;

public class ModificarClientes extends JInternalFrame {
	private final ModificarClientesHandler controlador;
	private JTextField textField_Cedula;
	private JTextField textField_Apellido;
	private JTextField textField_Nombre;
	private JPanel panel_DatosPersonales;
	private JPanel panel_Direccion;
	private JTextField textField_NombreCalle;
	private JTextField textField_Sector;
	private JButton btn_Actualizar;
	private JComboBox<String> comboBox_Listado;
	private JTextField textField_Numero;
	private JTextField textField_Buscar;
	private JButton btn_Buscar;
	private JLabel lbl_Alerta;

	// Constructor que inicializa los componentes graficos de la ventana
	public ModificarClientes(final String tituloVentana,
			final ModificarClientesHandler controlador) {
		super(tituloVentana, false, // resizable
				true, // closable
				false, // maximizable
				true); // mininizable

		this.controlador = controlador;

		crearPaneles();
		crearTextFields();
		crearBoton();
		crearLabel();
		crearComboBox();
		desabilitarCampos();
		definirVentana();

		MenuPrincipal.javaHelp.agregarHelp(this, "actualizar cliente");
	}

	/*
	 * Funcion que crea un boton y una clase que interactuará como escucha del
	 * evento ActionListener
	 */
	private void crearBoton() {
		class AgregarEventoBoton implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == btn_Buscar) {
					controlador.buscarCliente(textField_Buscar.getText());

					textField_Buscar.setText("");
					btn_Buscar.setEnabled(false);
					textField_Nombre.requestFocus();
				} else if (e.getSource() == btn_Actualizar) {
					controlador.actualizarCliente();

					textField_Buscar.requestFocus();
					desabilitarCampos();
				}
			}
		}

		btn_Actualizar = new JButton("Actualizar Cliente");
		btn_Actualizar.setBounds(493, 435, 146, 23);
		btn_Actualizar.addActionListener(new AgregarEventoBoton());
		getContentPane().add(btn_Actualizar);
		btn_Actualizar.setEnabled(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btn_Buscar = new JButton("Buscar Cliente");
		btn_Buscar.setEnabled(false);
		btn_Buscar.setBounds(493, 27, 146, 23);
		btn_Buscar.addActionListener(new AgregarEventoBoton());
		getContentPane().add(btn_Buscar);
	}

	// Funcion que crea un combo box y lo agrega al panel de direccion
	private void crearComboBox() {
		class Evento implements ItemListener {
			public void itemStateChanged(final ItemEvent e) {
				if (e.getSource() == comboBox_Listado
						&& !textField_Nombre.getText().isEmpty()
						&& !textField_Apellido.getText().isEmpty()
						&& !textField_Cedula.getText().isEmpty()
						&& !textField_NombreCalle.getText().isEmpty()
						&& !textField_Numero.getText().isEmpty()
						&& !textField_Sector.getText().isEmpty())
					btn_Actualizar.setEnabled(true);
			}

		}
		comboBox_Listado = new JComboBox<String>();
		// comboBox_Listado.setEnabled(false);
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
		comboBox_Listado.addItemListener(new Evento());
		comboBox_Listado.setBounds(10, 21, 190, 50);
		panel_Direccion.add(comboBox_Listado);
	}

	private void crearLabel() {
		lbl_Alerta = new JLabel("Registro no encontrado en la base de datos.");
		lbl_Alerta.setVisible(false);
		lbl_Alerta.setForeground(Color.RED);
		lbl_Alerta.setBounds(10, 80, 312, 14);
		getContentPane().add(lbl_Alerta);
	}

	// Funcion que define los paneles y los agrega al jinternalframe
	private void crearPaneles() {
		panel_DatosPersonales = new JPanel();
		panel_DatosPersonales.setLayout(null);
		panel_DatosPersonales.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Datos personales", TitledBorder.LEFT,
				TitledBorder.TOP, null, Color.BLACK));
		panel_DatosPersonales.setBackground(Color.WHITE);
		panel_DatosPersonales.setBounds(10, 125, 629, 105);
		getContentPane().add(panel_DatosPersonales);

		panel_Direccion = new JPanel();
		panel_Direccion.setLayout(null);
		panel_Direccion.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Datos del domicilio", TitledBorder.LEFT, TitledBorder.TOP,
				null, Color.BLACK));
		panel_Direccion.setBackground(Color.WHITE);
		panel_Direccion.setBounds(10, 241, 463, 217);
		getContentPane().add(panel_Direccion);
	}

	// Funcion que crea los textfields y los agrega a sus respectivos paneles
	private void crearTextFields() {
		class AgregarEvento implements KeyListener {
			public void keyPressed(final KeyEvent e) {
				if (e.getSource() == textField_Cedula
						&& e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
					if (textField_Cedula.getText().length() == 3
							|| textField_Cedula.getText().length() == 11)
						textField_Cedula.setText(textField_Cedula.getText()
								+ "-");
				} else if (e.getSource() == textField_Buscar
						&& e.getKeyCode() != KeyEvent.VK_BACK_SPACE)
					if (textField_Buscar.getText().length() == 3
							|| textField_Buscar.getText().length() == 11)
						textField_Buscar.setText(textField_Buscar.getText()
								+ "-");

				lbl_Alerta.setVisible(false);
			}

			public void keyReleased(final KeyEvent e) {
				if (textField_Buscar.getText().length() == 13)
					btn_Buscar.setEnabled(true);
				else
					btn_Buscar.setEnabled(false);

				if (!textField_Nombre.getText().isEmpty()
						&& !textField_Apellido.getText().isEmpty()
						&& !textField_Cedula.getText().isEmpty()
						&& textField_Cedula.getText().length() == 13
						&& !textField_NombreCalle.getText().isEmpty()
						&& !textField_Numero.getText().isEmpty()
						&& !textField_Sector.getText().isEmpty())
					btn_Actualizar.setEnabled(true);
				else
					btn_Actualizar.setEnabled(false);

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
						|| e.getSource() == textField_Cedula
						|| e.getSource() == textField_Buscar) {
					if ((e.getKeyChar() < '0' || e.getKeyChar() > '9')
							&& e.getKeyChar() != '\b')
						e.consume();
				} else if (e.getSource() == textField_Nombre
						|| e.getSource() == textField_Apellido
						|| e.getSource() == textField_Cedula
						|| e.getSource() == textField_NombreCalle
						|| e.getSource() == textField_Numero
						|| e.getSource() == textField_Sector
						|| e.getSource() == textField_Buscar)
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

				else if (e.getSource() == textField_Buscar
						&& textField_Buscar.getText().length() > 12)
					e.consume();
			}
		}

		textField_Nombre = new JTextField();
		// textField_Nombre.setEditable(false);
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
		// textField_Apellido.setEditable(false);
		textField_Apellido.setBounds(220, 31, 190, 47);
		panel_DatosPersonales.add(textField_Apellido);
		textField_Apellido.setToolTipText("Ingrese el apellido del cliente");
		textField_Apellido.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Apellido.addKeyListener(new AgregarEvento());
		textField_Apellido.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Apellido del cliente", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));

		textField_Cedula = new JTextField();
		// textField_Cedula.setEditable(false);
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
		// textField_NombreCalle.setEditable(false);
		textField_NombreCalle.setToolTipText("Ingrese el nombre de la calle");
		textField_NombreCalle.setHorizontalAlignment(SwingConstants.CENTER);
		textField_NombreCalle.addKeyListener(new AgregarEvento());
		textField_NombreCalle.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Nombre de la calle", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_NombreCalle.setBounds(260, 159, 190, 47);
		panel_Direccion.add(textField_NombreCalle);

		textField_Sector = new JTextField();
		// textField_Sector.setEditable(false);
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
		// textField_Numero.setEditable(false);
		textField_Numero.setToolTipText("Ingrese el numero de la casa");
		textField_Numero.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Numero.addKeyListener(new AgregarEvento());
		textField_Numero
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Numero de la casa", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Numero.setBounds(10, 159, 190, 47);
		panel_Direccion.add(textField_Numero);

		textField_Buscar = new JTextField();
		textField_Buscar.setToolTipText("Ingrese un criterio para realizar "
				+ "la busqueda");
		textField_Buscar.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Buscar
				.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
						"Cedula del cliente", TitledBorder.CENTER,
						TitledBorder.ABOVE_TOP, null, Color.BLACK));
		textField_Buscar.setBounds(10, 11, 194, 43);
		textField_Buscar.addKeyListener(new AgregarEvento());
		getContentPane().add(textField_Buscar);
	}

	// Funcion que define los parametros y configuraciones que tendra la ventana
	private void definirVentana() {
		setSize(661, 500);
		getContentPane().setLayout(null);
		setFrameIcon(null);
		setLocation(190, 100);
		setBorder(new LineBorder(new Color(0, 0, 204)));
		getContentPane().setBackground(Color.WHITE);
	}

	private void desabilitarCampos() {
		comboBox_Listado.setEnabled(false);
		textField_Nombre.setEditable(false);
		textField_Apellido.setEditable(false);
		textField_Cedula.setEditable(false);
		textField_NombreCalle.setEditable(false);
		textField_Numero.setEditable(false);
		textField_Sector.setEditable(false);
	}

	public String[] getDatosDireccion() {
		final String[] datos = { textField_NombreCalle.getText().toLowerCase(),
				textField_Numero.getText(),
				textField_Sector.getText().toLowerCase(),
				comboBox_Listado.getSelectedItem().toString() };

		limpiarTextFields();

		return datos;
	}

	public String[] getDatosPersonales() {
		final String[] datos = { textField_Nombre.getText().toLowerCase(),
				textField_Apellido.getText().toLowerCase(),
				textField_Cedula.getText().toLowerCase() };

		return datos;
	}

	// Funcion que limpia los textfield despues de haber extraido su informacion
	private void limpiarTextFields() {
		textField_Nombre.setText("");
		textField_Apellido.setText("");
		textField_Cedula.setText("");
		textField_NombreCalle.setText("");
		textField_Sector.setText("");
		textField_Numero.setText("");
		comboBox_Listado.setSelectedIndex(0);
		btn_Actualizar.setEnabled(false);
		textField_Nombre.requestFocus();
	}

	public void mostrarMensaje() {
		lbl_Alerta.setVisible(true);
		btn_Buscar.setEnabled(false);
		textField_Buscar.setText("");
	}

	public void setDatos(final String nombre, final String apellido,
			final String cedula, final String nombreCalle, final String numero,
			final String sector, final String ciudad) {
		textField_Nombre.setText(nombre);
		textField_Nombre.setEditable(true);
		textField_Apellido.setText(apellido);
		textField_Apellido.setEditable(true);
		textField_Cedula.setText(cedula);
		textField_Cedula.setEditable(true);
		textField_NombreCalle.setText(nombreCalle);
		textField_NombreCalle.setEditable(true);
		textField_Numero.setText(numero);
		textField_Numero.setEditable(true);
		textField_Sector.setText(sector);
		textField_Sector.setEditable(true);
		comboBox_Listado.setSelectedItem(new String(ciudad));
		comboBox_Listado.setEnabled(true);
	}
}