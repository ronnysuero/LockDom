// MenuPrincipal.java
package viewClass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import viewClass.extrasUI.AcercaDe;
import viewClass.extrasUI.DesktopPane;
import viewClass.extrasUI.Help;
import businessClass.businessControllers.ActualizarMercanciasHandler;
import businessClass.businessControllers.BuscarClientesHandler;
import businessClass.businessControllers.BuscarMercanciasHandler;
import businessClass.businessControllers.ModificarClientesHandler;
import businessClass.businessControllers.RegistrarClientesHandler;
import businessClass.businessControllers.RegistrarMercanciasHandler;

public class MenuPrincipal extends JFrame {
	private DesktopPane desktop;
	private JMenu JMenu_menuArchivo;
	private JMenu JMenu_menuCliente;
	private JMenu JMenu_menuMercancia;
	private JMenu JMenu_menuAyuda;
	private JMenuBar JMenuBar_menuBar;
	private JMenuItem JMenuItem_acercaDe;
	private JMenuItem JMenuItem_ayuda;
	private JMenuItem JMenuItem_ModificarCliente;
	private JMenuItem JMenuItem_BuscarCliente;
	private JMenuItem JMenuItem_BuscarMercancia;
	private JMenuItem JMenuItem_ModificarMercancia;
	private JMenuItem JMenuItem_RegistrarCliente;
	private JMenuItem JMenuItem_RegistrarMercancia;
	private JMenuItem JMenuItem_Salir;

	private final Dimension tamanoVentana = Toolkit.getDefaultToolkit()
			.getScreenSize();

	protected static Help javaHelp;

	// Constructor que inicializa los componentes graficos de la ventana
	public MenuPrincipal() {
		super("LockDom, S.A - Menu Principal");
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

	// Funcion que agrega los items del menu archivo
	private void agregarMenuArchivo(final JMenuBar JMB) {
		class AddActionListener implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == JMenuItem_Salir) {
					if (Integer.compare(JOptionPane.showOptionDialog(null,
							"Click en OK para salir",
							"Esta seguro que desa salir?",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, new Object[] {
									"OK", "CANCELAR" }, "OK"), 0) == 0)
						dispose();
				} else if (e.getSource() == JMenuItem_acercaDe)
					new AcercaDe();
			}
		}

		JMenu_menuArchivo = new JMenu("Archivo");
		JMenu_menuArchivo.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/archivo.png")));
		JMB.add(JMenu_menuArchivo);

		JMenuItem_acercaDe = new JMenuItem("Acerca de");
		JMenuItem_acercaDe.setBackground(new Color(220, 225, 255));
		JMenuItem_acercaDe.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/acerca-de.png")));
		JMenuItem_acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		JMenuItem_acercaDe.addActionListener(new AddActionListener());
		JMenu_menuArchivo.add(JMenuItem_acercaDe);
		JMenu_menuArchivo.addSeparator();

		JMenuItem_Salir = new JMenuItem("Salir");
		JMenuItem_Salir.setBackground(new Color(220, 225, 255));
		JMenuItem_Salir.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/salir.png")));
		JMenuItem_Salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		JMenuItem_Salir.addActionListener(new AddActionListener());
		JMenu_menuArchivo.add(JMenuItem_Salir);
	}

	// Funcion que agrega los items del menu ayuda
	private void agregarMenuAyuda(final JMenuBar JMB) {
		JMenu_menuAyuda = new JMenu("Ayuda");
		JMenu_menuAyuda.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/help.png")));
		JMB.add(JMenu_menuAyuda);

		JMenuItem_ayuda = new JMenuItem("Ayuda");
		JMenuItem_ayuda.setMnemonic(KeyEvent.VK_F1);
		JMenuItem_ayuda.setBackground(new Color(220, 225, 255));
		JMenuItem_ayuda.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/ayuda.png")));
		JMenuItem_ayuda.setAccelerator(KeyStroke.getKeyStroke("F1"));
		JMenu_menuAyuda.add(JMenuItem_ayuda);
	}

	// Funcion que agrega los items del menu cliente
	private void agregarMenuClientes(final JMenuBar JMB) {
		class AddActionListener implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				desktop.removeAll();
				desktop.repaint();

				if (e.getSource() == JMenuItem_RegistrarCliente)
					desktop.add(new RegistrarClientesHandler(MenuPrincipal.this)
							.iniciarJInternalFrame());

				else if (e.getSource() == JMenuItem_BuscarCliente)
					desktop.add(new BuscarClientesHandler()
							.iniciarJInternalFrame());

				else if (e.getSource() == JMenuItem_ModificarCliente)
					desktop.add(new ModificarClientesHandler(MenuPrincipal.this)
							.iniciarJInternalFrame());
			}
		}

		JMenu_menuCliente = new JMenu("Clientes");
		JMenu_menuCliente.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/clientes.png")));
		JMB.add(JMenu_menuCliente);

		JMenuItem_BuscarCliente = new JMenuItem("Buscar");
		JMenuItem_BuscarCliente.setBackground(new Color(220, 225, 255));
		JMenuItem_BuscarCliente.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("img/buscar.png")));
		JMenuItem_BuscarCliente.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_B, ActionEvent.ALT_MASK));
		JMenuItem_BuscarCliente.addActionListener(new AddActionListener());
		JMenu_menuCliente.add(JMenuItem_BuscarCliente);
		JMenu_menuCliente.addSeparator();

		JMenuItem_RegistrarCliente = new JMenuItem("Registrar");
		JMenuItem_RegistrarCliente.setBackground(new Color(220, 225, 255));
		JMenuItem_RegistrarCliente.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("img/agregar.png")));
		JMenuItem_RegistrarCliente.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, ActionEvent.ALT_MASK));
		JMenuItem_RegistrarCliente.addActionListener(new AddActionListener());
		JMenu_menuCliente.add(JMenuItem_RegistrarCliente);
		JMenu_menuCliente.addSeparator();

		JMenuItem_ModificarCliente = new JMenuItem("Modificar");
		JMenuItem_ModificarCliente.setBackground(new Color(220, 225, 255));
		JMenuItem_ModificarCliente.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("img/actualizar.png")));
		JMenuItem_ModificarCliente.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.ALT_MASK));
		JMenuItem_ModificarCliente.addActionListener(new AddActionListener());
		JMenu_menuCliente.add(JMenuItem_ModificarCliente);
	}

	// Funcion que agrega los items del menu mercancia
	private void agregarMenuMercancia(final JMenuBar JMB) {
		class AddActionListener implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				desktop.removeAll();
				desktop.repaint();

				if (e.getSource() == JMenuItem_RegistrarMercancia)
					desktop.add(new RegistrarMercanciasHandler(
							MenuPrincipal.this).iniciarJInternalFrame());

				else if (e.getSource() == JMenuItem_BuscarMercancia)
					desktop.add(new BuscarMercanciasHandler()
							.iniciarJInternalFrame());

				else if (e.getSource() == JMenuItem_ModificarMercancia)
					desktop.add(new ActualizarMercanciasHandler(
							MenuPrincipal.this).iniciarJInternalFrame());
			}
		}

		JMenu_menuMercancia = new JMenu("Mercancias");
		JMenu_menuMercancia.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("img/mercancia.png")));
		JMB.add(JMenu_menuMercancia);

		JMenuItem_ModificarMercancia = new JMenuItem("Actualizar");
		JMenuItem_ModificarMercancia.setBackground(new Color(220, 225, 255));
		JMenuItem_ModificarMercancia.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("img/actualizar.png")));
		JMenuItem_ModificarMercancia.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		JMenuItem_ModificarMercancia.addActionListener(new AddActionListener());
		JMenu_menuMercancia.add(JMenuItem_ModificarMercancia);
		JMenu_menuMercancia.addSeparator();

		JMenuItem_BuscarMercancia = new JMenuItem("Buscar");
		JMenuItem_BuscarMercancia.setBackground(new Color(220, 225, 255));
		JMenuItem_BuscarMercancia.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("img/buscar.png")));
		JMenuItem_BuscarMercancia.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		JMenuItem_BuscarMercancia.addActionListener(new AddActionListener());
		JMenu_menuMercancia.add(JMenuItem_BuscarMercancia);
		JMenu_menuMercancia.addSeparator();

		JMenuItem_RegistrarMercancia = new JMenuItem("Registrar");
		JMenuItem_RegistrarMercancia.setBackground(new Color(220, 225, 255));
		JMenuItem_RegistrarMercancia.setIcon(new ImageIcon(getClass()
				.getClassLoader().getResource("img/agregar.png")));
		JMenuItem_RegistrarMercancia.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		JMenuItem_RegistrarMercancia.addActionListener(new AddActionListener());
		JMenu_menuMercancia.add(JMenuItem_RegistrarMercancia);
	}

	// Funcion que agrega los diferentes menu a la barra
	private JMenuBar crearBarraMenu() {
		JMenuBar_menuBar = new JMenuBar();
		JMenuBar_menuBar.setBorder(new LineBorder(new Color(0, 102, 204)));
		JMenuBar_menuBar.setBackground(new Color(220, 225, 255));
		JMenuBar_menuBar.setBorderPainted(true);

		agregarMenuArchivo(JMenuBar_menuBar);
		agregarMenuClientes(JMenuBar_menuBar);
		agregarMenuMercancia(JMenuBar_menuBar);
		agregarMenuAyuda(JMenuBar_menuBar);

		return JMenuBar_menuBar;
	}

	// Funcion que agrega el JDesktopPane al JFrame
	private void crearJDesktopPane() {
		desktop = new DesktopPane();
		setContentPane(desktop);
	}

	// Funcion que define los parametros y configuraciones que tendra la ventana
	private void definirVentana() {
		crearJDesktopPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamanoVentana.width, tamanoVentana.height - 40);
		setLocationRelativeTo(null);
		setJMenuBar(crearBarraMenu());
		javaHelp = new Help(JMenuItem_ayuda, this);
		agregarIconoFrame();
		requestFocus();
	}
}