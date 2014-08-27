// BuscarMercancias.java
package viewClass;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businessClass.businessControllers.BuscarClientesHandler;

public class BuscarClientes extends JInternalFrame {
	public static final String NOMBRES_COLUMNAS[] = { "ID", "Nombre",
			"Apellido", "Cedula", "Calle", "Numero De Casa", "Sector", "Ciudad" };

	private JButton boton_BuscarCliente;
	private final BuscarClientesHandler controlador;
	private JLabel lbl_opcional;
	private JScrollPane scrollPane;
	private JTable tabla;
	private JTextField textField_Busqueda;

	// Constructor que inicializa los componentes graficos de la ventana
	public BuscarClientes(final String tituloVentana,
			final BuscarClientesHandler controlador) {
		super(tituloVentana, false, // resizable
				true, // closable
				false, // maximizable
				true); // minimizable

		this.controlador = controlador;

		definirVentana();
		crearLabels();
		crearTextFields();
		crearBoton();
		crearScrollPane();
		crearTabla();

		MenuPrincipal.javaHelp.agregarHelp(this, "buscar cliente");
	}

	/*
	 * Funcion que crea un boton y una clase que interactuará como escucha del
	 * evento ActionListener
	 */
	private void crearBoton() {
		class AgregarEventoBoton implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				controlador.buscarCliente(textField_Busqueda.getText()
						.toLowerCase());
				limpiarTextField();
				textField_Busqueda.requestFocus();
			}
		}

		boton_BuscarCliente = new JButton("Buscar Cliente");
		boton_BuscarCliente.setBounds(728, 53, 146, 23);
		boton_BuscarCliente.setEnabled(false);
		boton_BuscarCliente.addActionListener(new AgregarEventoBoton());
		getContentPane().add(boton_BuscarCliente);
	}

	// Funcion que crea los labels y los agrega al frame
	private void crearLabels() {
		lbl_opcional = new JLabel("Registro no encontrado en la base de datos.");
		lbl_opcional.setForeground(Color.RED);
		lbl_opcional.setBounds(10, 115, 274, 14);
		lbl_opcional.setVisible(false);
		getContentPane().add(lbl_opcional);
	}

	// Funcion que crea un panel desplazable y lo agrega al frame
	private void crearScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBounds(10, 141, 864, 199);
		getContentPane().add(scrollPane);
	}

	private void crearTabla() {
		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(null, NOMBRES_COLUMNAS));
		scrollPane.setViewportView(tabla);
	}

	// Funcion que crea los textfields y los agrega al frame
	private void crearTextFields() {
		class AddKeyListener implements KeyListener {
			public void keyPressed(final KeyEvent e) {
				lbl_opcional.setVisible(false);
			}

			public void keyReleased(final KeyEvent e) {
				if (!textField_Busqueda.getText().isEmpty())
					boton_BuscarCliente.setEnabled(true);
				else
					boton_BuscarCliente.setEnabled(false);

				if (e.getKeyCode() == KeyEvent.VK_ENTER
						&& boton_BuscarCliente.isEnabled() == true) {
					controlador.buscarCliente(textField_Busqueda.getText()
							.toLowerCase());
					limpiarTextField();
					textField_Busqueda.requestFocus();
				}
			}

			public void keyTyped(final KeyEvent e) {
				if (e.getSource() == textField_Busqueda)
					if (e.getKeyChar() == '\'' || e.getKeyChar() == '"')
						e.consume();

				if (e.getSource() == textField_Busqueda
						&& textField_Busqueda.getText().length() > 20)
					e.consume();
			}
		}

		textField_Busqueda = new JTextField();
		textField_Busqueda
				.setToolTipText("Ingrese un criterio para realizar la busqueda");
		textField_Busqueda.setBounds(10, 37, 408, 43);
		textField_Busqueda.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Busqueda
				.setBorder(new TitledBorder(
						new LineBorder(Color.BLACK),
						"Criterios de busqueda: Cedula, Nombre o Apellido del cliente",
						TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null,
						Color.BLACK));
		textField_Busqueda.addKeyListener(new AddKeyListener());
		getContentPane().add(textField_Busqueda);
	}

	// Funcion que define los parametros y configuraciones que tendra la ventana
	private void definirVentana() {
		setSize(892, 381);
		getContentPane().setLayout(null);
		setFrameIcon(null);
		setLocation(100, 100);
		setBorder(new LineBorder(new Color(0, 0, 204)));
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Funcion que limpia el textfield despues de haber extraido su informacion
	private void limpiarTextField() {
		textField_Busqueda.setText("");
		boton_BuscarCliente.setEnabled(false);
	}

	// Funcion que despliega en el JInternalFrame un mensaje avisando que no se
	// encontro el registro
	public void mostrarMensaje() {
		lbl_opcional.setVisible(true);
		textField_Busqueda.requestFocus();
	}

	// Funcion que actualiza la tabla del view
	public void setModelTable(final DefaultTableModel DTM) {
		tabla.setModel(DTM);
	}
}