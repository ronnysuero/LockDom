// Splash.java
package viewClass.extrasUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.MatteBorder;

import businessClass.businessControllers.IniciarSesionHandler;

public class Splash extends JDialog {
	private JProgressBar progressBar;
	private JLabel lbl_TextoCambiante;
	private JLabel lblAllRightsReserved;
	private CargaImagen cargaLogo;

	// Constructor por defecto
	public Splash() {
		crearPanel();
		crearLabels();
		crearProgressBar();
		definirVentana();
	}

	// Funcion que crea un label
	private void crearLabels() {
		lbl_TextoCambiante = new JLabel("Preparando la aplicación....");
		lbl_TextoCambiante.setBounds(10, 257, 352, 20);
		cargaLogo.add(lbl_TextoCambiante);
		lbl_TextoCambiante.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblAllRightsReserved = new JLabel(
				"All rights reserved. Copyright\u00A9 2013 - 2014");
		lblAllRightsReserved.setBounds(343, 11, 291, 20);
		cargaLogo.add(lblAllRightsReserved);
		lblAllRightsReserved.setForeground(Color.DARK_GRAY);
		lblAllRightsReserved.setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	// Funcion que posiciona un JPanel en la ventana
	private void crearPanel() {
		cargaLogo = new CargaImagen("1.jpg");
		cargaLogo.setBounds(0, 0, 634, 305);
		getContentPane().add(cargaLogo);
		cargaLogo.setLayout(null);
	}

	// Funcion que crea una barra de progreso
	private void crearProgressBar() {
		progressBar = new JProgressBar();
		progressBar.setBounds(0, 278, 634, 16);
		cargaLogo.add(progressBar);
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(0, 0, 51));
		progressBar.setIndeterminate(true);
		progressBar.setBorder(new MatteBorder(1, 1, 1, 1, new Color(153, 204,
				255)));
	}

	// Funcion que define las propiedades que tendra la ventana
	private void definirVentana() {
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(634, 305);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);
	}

	// Funcion que simulará la carga de la app
	public Runnable IniciarApp() {
		class AppRun implements Runnable {
			public void run() {
				for (int i = 1; i <= 16; i++) {
					try {
						cargaLogo.insertarImagen(String.valueOf(i));
						if (Integer.compare(i, 11) == 0)
							lbl_TextoCambiante
									.setText("Cargando base de datos....");

						Thread.sleep(10);
					} catch (Exception e) {
					}
				}

				progressBar.setIndeterminate(false);
				progressBar.setValue(50);

				for (int i = 50; i <= 100; i++) {
					try {
						if (Integer.compare(i, 50) == 0)
							lbl_TextoCambiante.setText("Leyendo recursos....");
						else if (Integer.compare(i, 80) == 0)
							lbl_TextoCambiante
									.setText("Iniciando aplicación....");

						progressBar.setValue(i);
						Thread.sleep(80);
					} catch (final Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				dispose();
				new IniciarSesionHandler();
			}
		}
		return new AppRun();
	}
}