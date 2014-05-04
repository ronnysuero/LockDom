// AccesoBD.java
package dataAccessClass;

import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import businessClass.businessEntities.Cliente;
import businessClass.businessEntities.Cuenta;
import businessClass.businessEntities.Direccion;
import businessClass.businessEntities.Empleado;
import businessClass.businessEntities.Mercancia;

public class AccesoBD extends DerbyDB {
	// Funcion que iniciará la base de datos
	public static Runnable RUN_DB() {
		/*
		 * Clase que funcionará en un Thread e iniciara la base de datos al
		 * tiempo que inicia la App.
		 */
		class IniciarDB implements Runnable {
			public void run() {
				new AccesoBD().crearFicheroTemporal("Scripts_DB.sql");
				new AccesoBD().crearFicheroTemporal("Scripts_Inserts.sql");
			}
		}
		return new IniciarDB();
	}

	private ResultSet resultado;

	// Constructor
	public AccesoBD() {
		super();
	}

	public boolean actualizarCliente(final Cliente unCliente) {
		boolean valor = false;

		try {
			query.execute("UPDATE Direcciones SET Direcciones_NombreCalle = '"
					+ unCliente.getDireccion().getNombreCalle()
					+ "', "
					+ "Direcciones_NumeroCasa = ' "
					+ unCliente.getDireccion().getNumeroCasa()
					+ "', "
					+ "Direcciones_Sector = '"
					+ unCliente.getDireccion().getSector()
					+ "', "
					+ "Ciudades_Id = "
					+ "(SELECT Ciudades_Id FROM Ciudades WHERE Ciudades_Nombre = '"
					+ unCliente.getDireccion().getCiudad()
					+ "') WHERE Direcciones_Id = "
					+ unCliente.getDireccion().getId());

			query.execute("UPDATE Clientes SET Clientes_PrimerNombre = '"
					+ unCliente.getNombre() + "', "
					+ "Clientes_PrimerApellido = '" + unCliente.getApellido()
					+ "', " + "Clientes_Cedula = '" + unCliente.getCedula()
					+ "' WHERE Clientes_Id = " + unCliente.getId());

			valor = true;
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		cerrarDB();

		return valor;
	}

	// Funcion que actualiza una registro de una mercancia en la base de datos
	public boolean actualizarMercancia(final Mercancia unaMercancia) {
		boolean valor = false;

		try {
			query.execute("UPDATE Mercancias SET Mercancias_Cantidad = "
					+ unaMercancia.getCantidad() + ", Mercancias_Precio = "
					+ unaMercancia.getPrecio() + " WHERE Mercancias_Id = "
					+ unaMercancia.getId());

			valor = true;
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		cerrarDB();

		return valor;
	}

	public boolean almacenarCliente(final Cliente unCliente) {
		boolean valor = false;

		try {
			query.execute("INSERT INTO Direcciones (Direcciones_NombreCalle, "
					+ "Direcciones_NumeroCasa, Direcciones_Sector,"
					+ "Ciudades_Id) VALUES('"
					+ unCliente.getDireccion().getNombreCalle()
					+ "', '"
					+ unCliente.getDireccion().getNumeroCasa()
					+ "', '"
					+ unCliente.getDireccion().getSector()
					+ "', "
					+ "(SELECT Ciudades_Id FROM Ciudades WHERE Ciudades_Nombre = '"
					+ unCliente.getDireccion().getCiudad() + "'))");

			query.execute("INSERT INTO Clientes (Clientes_PrimerNombre, "
					+ "Clientes_PrimerApellido, Clientes_Cedula, Direcciones_Id) "
					+ "VALUES('" + unCliente.getNombre() + "', '"
					+ unCliente.getApellido() + "', '" + unCliente.getCedula()
					+ "', " + "(SELECT MAX(Direcciones_Id) FROM Direcciones))");

			valor = true;
		} catch (SQLIntegrityConstraintViolationException ex) {
			JOptionPane.showMessageDialog(null,
					"Error!! ya existe un registro con esa misma cedula.");
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		cerrarDB();

		return valor;
	}

	// Funcion que almacena un nuevo registro de la mercancia en la base de
	// datos
	public boolean almacenarMercancia(final Mercancia unaMercancia) {
		boolean valor = false;

		try {
			query.execute("INSERT INTO Mercancias (Mercancias_Tipo, Mercancias_Marca, "
					+ "Mercancias_Cantidad, Mercancias_Precio) VALUES('"
					+ unaMercancia.getTipo()
					+ "', '"
					+ unaMercancia.getMarca()
					+ "', "
					+ unaMercancia.getCantidad()
					+ ", "
					+ unaMercancia.getPrecio() + ")");

			valor = true;
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		cerrarDB();

		return valor;
	}

	public List<Cliente> buscarCliente(final String busqueda) {
		final List<Cliente> listado = new ArrayList<Cliente>();

		try {
			resultado = query
					.executeQuery("SELECT c.Clientes_Id, c.Clientes_PrimerNombre, "
							+ "c.Clientes_PrimerApellido, c.Clientes_Cedula, "
							+ "d.Direcciones_NombreCalle, d.Direcciones_NumeroCasa, "
							+ "d.Direcciones_Sector, cd.Ciudades_Nombre FROM Clientes c, "
							+ "Direcciones d, Ciudades cd WHERE d.Ciudades_Id = cd.Ciudades_Id "
							+ "AND d.Direcciones_Id = c.Direcciones_Id AND (c.Clientes_PrimerNombre "
							+ "|| ' ' || c.Clientes_PrimerApellido || c.Clientes_Cedula) LIKE '%"
							+ busqueda + "%'");

			while (resultado.next())
				listado.add(new Cliente(resultado.getLong(1), resultado
						.getString(2), resultado.getString(3), resultado
						.getString(4), new Direccion(resultado.getString(5),
						resultado.getString(6), resultado.getString(7),
						resultado.getString(8))));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		cerrarDB();

		return listado;
	}

	public Cliente buscarClienteEspecifico(final String busqueda) {
		Cliente unCliente = null;

		try {
			resultado = query
					.executeQuery("SELECT c.Clientes_Id, c.Clientes_PrimerNombre, "
							+ "c.Clientes_PrimerApellido, c.Clientes_Cedula, "
							+ "d.Direcciones_Id, d.Direcciones_NombreCalle, d.Direcciones_NumeroCasa, "
							+ "d.Direcciones_Sector, cd.Ciudades_Nombre FROM Clientes c, "
							+ "Direcciones d, Ciudades cd WHERE d.Ciudades_Id = cd.Ciudades_Id "
							+ "AND d.Direcciones_Id = c.Direcciones_Id AND c.Clientes_Cedula = '"
							+ busqueda + "'");

			resultado.next();

			unCliente = new Cliente(resultado.getLong(1),
					resultado.getString(2), resultado.getString(3),
					resultado.getString(4), new Direccion(resultado.getLong(5),
							resultado.getString(6), resultado.getString(7),
							resultado.getString(8), resultado.getString(9)));
		} catch (final Exception e) {
		}

		cerrarDB();

		return unCliente;
	}

	// Funcion que busca un registro de una mercancia en la base de datos
	public List<Mercancia> buscarMercancia(final String busqueda) {
		final List<Mercancia> listado = new ArrayList<Mercancia>();

		try {
			resultado = query.executeQuery("SELECT * FROM MERCANCIAS WHERE "
					+ "(CAST(Mercancias_Id AS CHAR(15)) || "
					+ "Mercancias_Tipo || Mercancias_Marca) LIKE '%" + busqueda
					+ "%'");

			while (resultado.next())
				listado.add(new Mercancia(resultado.getLong(1), resultado
						.getString(2), resultado.getString(3), resultado
						.getInt(4), resultado.getDouble(5)));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		cerrarDB();

		return listado;
	}

	// Funcion que busca una mercancia en especifico en la base de datos
	public Mercancia buscarMercanciaEspecifica(final String busqueda) {
		Mercancia unaMercancia = null;

		try {
			resultado = query
					.executeQuery("SELECT * FROM MERCANCIAS WHERE Mercancias_Id = "
							+ busqueda);

			resultado.next();

			unaMercancia = new Mercancia(resultado.getLong(1),
					resultado.getString(2), resultado.getString(3),
					resultado.getInt(4), resultado.getDouble(5));
		} catch (final Exception e) {
		}

		cerrarDB();

		return unaMercancia;
	}

	public Vector<String> consultarCiudades() {
		final Vector<String> listado = new Vector<String>();

		try {
			resultado = query
					.executeQuery("SELECT Ciudades_Nombre FROM Ciudades "
							+ "ORDER BY Ciudades_Nombre ASC");

			while (resultado.next())
				listado.add(resultado.getString(1));
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return listado;
	}

	// Funcion que consulta los datos de inicio de sesion en la base de datos
	public Empleado consultarDB(final String user, final String password) {
		Empleado unEmpleado = null;

		try {
			resultado = query
					.executeQuery("SELECT Empleados_PrimerNombre, Empleados_PrimerApellido, "
							+ "Cuentas_NombreUsuario, Cuentas_Contrasena FROM Cuentas c "
							+ "INNER JOIN Empleados e on e.Empleados_Id = c.Empleados_Id WHERE "
							+ "Cuentas_NombreUsuario = '"
							+ user
							+ "' AND "
							+ "Cuentas_Contrasena = '" + password + "'");

			resultado.next();

			unEmpleado = new Empleado(resultado.getString(1),
					resultado.getString(2), new Cuenta(resultado.getString(3),
							resultado.getString(4)));
		} catch (final Exception e) {
		}

		cerrarDB();

		return unEmpleado;
	}
}