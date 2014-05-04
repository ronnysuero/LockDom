// AcercaDe.java
package viewClass.extrasUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AcercaDe extends JDialog {
	private JButton btn_Ok;
	private CargaImagen cargaLogo;
	private JLabel lbl_CreadoPor;
	private JLabel lbl_derechos;
	private JLabel lbl_SitioWeb;
	private JLabel lbl_Version;
	private JLabel lbl_Creditos1;
	private JLabel lbl_Creditos2;
	private JLabel lbl_CreditoURL;
	private JLabel lbl_Github;
	private JLabel lblIconsBy;
	private JLabel lbl_dryicons;
	private JButton btn_AvisosLegales;

	// Contructor por defecto
	public AcercaDe() {
		crearPanel();
		crearBoton();
		crearLabels();
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

	// Funcion que inserta un boton en el JDialog
	private void crearBoton() {
		class AddActionListener implements ActionListener {
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == btn_Ok)
					dispose();
				else if (e.getSource() == btn_AvisosLegales)
					new AvisosLegales();
			}
		}

		btn_Ok = new JButton("OK");
		btn_Ok.setBounds(350, 278, 127, 23);
		btn_Ok.addActionListener(new AddActionListener());
		btn_Ok.setFocusable(false);
		getContentPane().add(btn_Ok);

		btn_AvisosLegales = new JButton("Avisos Legales");
		btn_AvisosLegales.setFocusable(false);
		btn_AvisosLegales.setBounds(350, 244, 127, 23);
		btn_AvisosLegales.addActionListener(new AddActionListener());
		getContentPane().add(btn_AvisosLegales);
	}

	// Funcion que inserta los Jlabels en el JDialog
	private void crearLabels() {
		class Evento implements MouseListener {

			public void mouseClicked(final MouseEvent e) {
				if (e.getSource() == lbl_CreditoURL)
					try {
						Desktop.getDesktop().browse(
								new URI(lbl_CreditoURL.getText()));
					} catch (final Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}

				else if (e.getSource() == lbl_Github)
					try {
						Desktop.getDesktop()
								.browse(new URI(
										"https://github.com/ronnysuero/INF-522_PF/tree/master/src"));
					} catch (final Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}

				else if (e.getSource() == lbl_dryicons)
					try {
						Desktop.getDesktop().browse(
								new URI(lbl_dryicons.getText()));
					} catch (final Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
			}

			public void mouseEntered(final MouseEvent e) { /**/
			}

			public void mouseExited(final MouseEvent e) { /**/
			}

			public void mousePressed(final MouseEvent e) { /**/
			}

			public void mouseReleased(final MouseEvent e) { /**/
			}

		}

		lbl_SitioWeb = new JLabel("Sitio web del codigo fuente:");
		lbl_SitioWeb.setBounds(241, 84, 162, 14);
		getContentPane().add(lbl_SitioWeb);

		lbl_CreadoPor = new JLabel("Desarrollado por: Ronny Z. Suero");
		lbl_CreadoPor.setBounds(241, 45, 204, 14);
		getContentPane().add(lbl_CreadoPor);

		lbl_derechos = new JLabel("(c) Copyright 2014.  All rights reserved.");
		lbl_derechos.setBounds(236, 179, 241, 14);
		getContentPane().add(lbl_derechos);

		lbl_Version = new JLabel("Versi\u00F3n: 1.0 Final");
		lbl_Version.setBounds(241, 148, 107, 14);
		getContentPane().add(lbl_Version);

		lbl_Creditos1 = new JLabel(
				"This product includes software developed by");
		lbl_Creditos1.setBounds(10, 258, 275, 23);
		getContentPane().add(lbl_Creditos1);

		lbl_Creditos2 = new JLabel("the Apache Software Foundation ");
		lbl_Creditos2.setBounds(10, 282, 196, 14);
		getContentPane().add(lbl_Creditos2);

		lbl_CreditoURL = new JLabel("http://apache.org/");
		lbl_CreditoURL.setToolTipText("Web oficial de APACHE");
		lbl_CreditoURL
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_CreditoURL.setForeground(Color.BLUE);
		lbl_CreditoURL.addMouseListener(new Evento());
		lbl_CreditoURL.setBounds(196, 282, 101, 14);
		getContentPane().add(lbl_CreditoURL);

		lbl_Github = new JLabel("GitHub");
		lbl_Github.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_Github.setForeground(Color.BLUE);
		lbl_Github.addMouseListener(new Evento());
		lbl_Github.setToolTipText("Sitio web que contiene el codigo fuente");
		lbl_Github.setBounds(401, 84, 54, 14);
		getContentPane().add(lbl_Github);

		lblIconsBy = new JLabel("Menu icons was supplied by:");
		lblIconsBy.setBounds(10, 233, 171, 14);
		getContentPane().add(lblIconsBy);

		lbl_dryicons = new JLabel("http://dryicons.com");
		lbl_dryicons.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_dryicons.setToolTipText("Web oficial de DroyIcons");
		lbl_dryicons.addMouseListener(new Evento());
		lbl_dryicons.setForeground(Color.BLUE);
		lbl_dryicons.setBounds(175, 233, 133, 14);
		getContentPane().add(lbl_dryicons);
	}

	// Funcion que crea el logo de la aplicacion
	private void crearPanel() {
		cargaLogo = new CargaImagen("logo.png");
		cargaLogo.setBounds(10, 11, 209, 182);
		getContentPane().add(cargaLogo);
	}

	// Funcion que define las propiedades que tendra la ventana
	private void definirVentana() {
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		setTitle("Acerca de LockDom, S.A.");
		setSize(493, 339);
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
	}
}