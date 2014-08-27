/**
 * DerbyDB.java
 *
 * @author Ronny Z. Suero
 */
package dataAccessClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Clase abstracta que contienen metodos y variables que permitiran la 
 * creacion de la base de datos y leer el script SQL de la base de datos
 * en caso necesario para la creacion de las tablas necesarias para la App. 
*/
public abstract class DerbyDB {
	protected Connection derby;
	protected Statement query;

	private final String NOMBRE_BD = "LOCKDOM_DATABASE";

	/**
	 * Constructor de la clase
	 * 
	 */
	public DerbyDB() {
		conectarDB();
	}

	/**
	 * Funcion que cierra la conexion con la base de datos
	 *
	 * @return none
	 */
	protected void cerrarDB() {
		try {
			query.close();
			derby = DriverManager.getConnection("jdbc:derby:" + NOMBRE_BD
					+ "; shutdown = true");
			derby.close();
		} catch (final Exception e) { }
	}

	/**
	 * Funcion que conecta la aplicacion a la base de datos
	 *
	 * @return none
	 */
	private void conectarDB() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();

			derby = DriverManager.getConnection("jdbc:derby:" + NOMBRE_BD
					+ "; create = true; dataEncryption = true; "
					+ "bootPassword = proyectoLockDom!@#$%^&*");

			if (derby != null)
				query = derby.createStatement();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Funcion que extraerá el script SQL de la Base De Datos para la creacion
	 * de las tablas
	 *
	 * @param fichero Este parametro define el nombre del archivo a crear
	 *
	 * @return none
	 */
	public void crearFicheroTemporal(final String fichero) {
		try {
			final File directorio = new File(NOMBRE_BD);
			directorio.mkdirs();
			directorio.setWritable(true);

			final String url = directorio.getCanonicalPath() + "/" + fichero;
			System.out.println(url);
			final File temp = new File(url);
			final InputStream is = ClassLoader
					.getSystemResourceAsStream(fichero);
			final FileOutputStream archivoDestino = new FileOutputStream(temp);
			final FileWriter fw = new FileWriter(temp);

			final byte[] buffer = new byte[512 * 1024];
			int pLectura;

			while ((pLectura = is.read(buffer)) != -1)
				archivoDestino.write(buffer, 0, pLectura);

			fw.close();
			archivoDestino.close();
			is.close();

			leerScriptSQL(fichero);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Funcion que lee el fichero que contiene la informacion legal de la App
	 * 
	 * @param  fichero Este parametro define el nombre del archivo a leer
	 * 
	 * @return none
	 */
	public String leerFicheroLegal(final String fichero) {
		String informacion = new String("");

		try {
			final File archivo = new File(NOMBRE_BD + "/" + fichero);

			if (archivo.exists()) {
				final BufferedReader br = new BufferedReader(new FileReader(
						archivo));
				String linea = null;

				while ((linea = br.readLine()) != null)
					informacion += linea + "\n";

				br.close();
			}
		} catch (final Exception e) { /**/
		}

		return informacion;
	}

	/**
	 * Funcion que lee las sentencias SQL de un script y las ejecuta en la base
	 * de datos
	 * 
	 * @param fichero Este parametro define el nombre del archivo a leer
	 *
	 * @return none
	 */
	private void leerScriptSQL(final String fichero) {
		try {
			final File archivo = new File(NOMBRE_BD + "/" + fichero);
			System.out.println(NOMBRE_BD + "/" + fichero);
			if (archivo.exists()) {
				final BufferedReader br = new BufferedReader(new FileReader(
						archivo));
				String linea = null;
				String cadena = "";
				final List<String> listaQuery = new ArrayList<String>();

				while ((linea = br.readLine()) != null)
					cadena += linea;

				while (cadena.length() != 0) {
					final int i = cadena.indexOf(";");
					listaQuery.add(cadena.substring(0, i));
					cadena = cadena.substring(i + 1);
				}

				for (final String cad : listaQuery)
					query.execute(cad);

				br.close();

				new File(NOMBRE_BD + "/" + fichero).delete();
				cerrarDB();
			}
		} catch (final Exception e) { }
	}
}