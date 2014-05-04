// IniciarSesion.java
package viewClass;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import viewClass.extrasUI.CargaImagen;
import businessClass.businessControllers.IniciarSesionHandler;

public class IniciarSesion extends JFrame {
	// Clase que utiliza los eventos del teclado para los textfields y
	// jpasswordfield
	class AddEventoTextField implements KeyListener {
		public void keyPressed(final KeyEvent e) { /**/
		}

		public void keyReleased(final KeyEvent e) {
			if (e.getSource() == textField_user
					|| e.getSource() == passwordField_contrasena) {
				if (!textField_user.getText().isEmpty()
						&& !String.valueOf(
								passwordField_contrasena.getPassword())
								.isEmpty()) {
					btn_Login.setEnabled(true);
					btn_Login.setBackground(new Color(0, 102, 255));
					btn_Login.setForeground(Color.WHITE);
				} else {
					btn_Login.setEnabled(false);
					btn_Login.setBackground(Color.RED);
				}

				lbl_Alerta.setVisible(false);
			}

			if (e.getKeyCode() == KeyEvent.VK_ENTER
					&& btn_Login.isEnabled() == true)
				controlador.login(textField_user.getText(),
						String.valueOf(passwordField_contrasena.getPassword()));
		}

		public void keyTyped(final KeyEvent e) {
			if (e.getSource() == textField_user
					|| e.getSource() == passwordField_contrasena)
				if (e.getKeyChar() == '\'' || e.getKeyChar() == '"')
					e.consume();

			if (e.getSource() == textField_user
					&& textField_user.getText().length() > 20)
				e.consume();

			else if (e.getSource() == passwordField_contrasena
					&& String.valueOf(passwordField_contrasena.getPassword())
							.length() > 20)
				e.consume();
		}
	}

	private JTextField textField_user;
	private JPasswordField passwordField_contrasena;
	private JLabel lbl_Alerta;
	private JButton btn_Login;

	private CargaImagen logo;

	private final IniciarSesionHandler controlador;

	// Constructor por argumentos
	public IniciarSesion(final IniciarSesionHandler c) {
		super("Inicie sesión para entrar al sistema");
		controlador = c;
		definirVentana();
		crearTextField();
		crearPasswordField();
		crearBoton();
		crearLabel();
		mostrarLogo();
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

	// Funcion que crea un boton y lo posiciona en la ventana
	private void crearBoton() {
		class AddActionListener implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == btn_Login)
					controlador.login(textField_user.getText(), String
							.valueOf(passwordField_contrasena.getPassword()));
			}
		}

		btn_Login = new JButton("Iniciar Sesi\u00F3n");
		btn_Login.setEnabled(false);
		btn_Login.setRequestFocusEnabled(false);
		btn_Login.setBorder(new LineBorder(Color.BLACK));
		btn_Login.setBackground(Color.RED);
		btn_Login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_Login.setToolTipText("Haga click para iniciar sesion");
		btn_Login.setBounds(45, 356, 291, 40);
		btn_Login.addActionListener(new AddActionListener());
		getContentPane().add(btn_Login);
	}

	// Funcion que crea un label y lo posiciona en la ventana
	private void crearLabel() {
		lbl_Alerta = new JLabel("Nombre de usuario / Contrase\u00F1a invalidas");
		lbl_Alerta.setVisible(false);
		lbl_Alerta.setForeground(Color.RED);
		lbl_Alerta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_Alerta.setBounds(30, 407, 340, 28);
		getContentPane().add(lbl_Alerta);
	}

	// Funcion que crea un textfield de tipo password y lo posiciona en la
	// ventana
	private void crearPasswordField() {
		passwordField_contrasena = new JPasswordField();
		passwordField_contrasena.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField_contrasena.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField_contrasena
				.setToolTipText("Introduzca su contrase\u00F1a");
		passwordField_contrasena.setBorder(new TitledBorder(new LineBorder(
				Color.BLACK), "Contrase\u00F1a", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		passwordField_contrasena.setBounds(45, 290, 291, 48);
		passwordField_contrasena.addKeyListener(new AddEventoTextField());
		getContentPane().add(passwordField_contrasena);
	}

	// Funcion que crea los textfiels y los posiciona en la ventana
	private void crearTextField() {
		textField_user = new JTextField();
		textField_user.setHorizontalAlignment(SwingConstants.CENTER);
		textField_user.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_user.setToolTipText("Introduzca su nombre de usuario");
		textField_user.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Nombre de Usuario", TitledBorder.CENTER, TitledBorder.TOP,
				null, Color.BLACK));
		textField_user.setBounds(45, 221, 291, 48);
		textField_user.addKeyListener(new AddEventoTextField());
		getContentPane().add(textField_user);
	}

	// Funcion que define los limites del frame
	private void definirVentana() {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(387, 481);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setResizable(false);
		agregarIconoFrame();
	}

	// Funcion que crea el logo y lo posiciona en la ventana
	private void mostrarLogo() {
		logo = new CargaImagen("inicie-sesion.png");
		logo.setBounds(65, 11, 252, 218);
		getContentPane().add(logo);
	}

	// Funcion que muestra un mensaje en caso de error
	public void mostrarMensaje() {
		lbl_Alerta.setVisible(true);
		btn_Login.setEnabled(false);
		textField_user.setText("");
		passwordField_contrasena.setText("");
		btn_Login.setBackground(Color.RED);
		textField_user.requestFocus();
	}
}