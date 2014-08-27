// Mercancia.java
package businessClass.businessEntities;

public class Mercancia {
	private int cantidad;
	private long id;
	private final String marca;
	private double precio;
	private final String tipo;

	// Constructor por argumentos
	public Mercancia(final long id, final String tipo, final String marca,
			final int cantidad, final double precio) {
		this.id = id;
		this.tipo = tipo;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	// Constructor por argumentos
	public Mercancia(final String tipo, final String marca, final int cantidad,
			final double precio) {
		this.tipo = tipo;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	// Constructor por argumentos
	public Mercancia(final String[] datos) {
		tipo = datos[0];
		marca = datos[1];
		cantidad = Integer.parseInt(datos[2]);
		precio = Double.parseDouble(datos[3]);
	}

	// Retorna la cantidad de la mercancia
	public int getCantidad() {
		return cantidad;
	}

	// Retorna el id de la mercancia
	public long getId() {
		return id;
	}

	// Retorna la marca de la mercancia
	public String getMarca() {
		return marca;
	}

	// Retorna el precio de la mercancia
	public double getPrecio() {
		return precio;
	}

	// Retorna el tipo de la mercancia
	public String getTipo() {
		return tipo;
	}

	// Setea una cantidad a la mercancia
	public void setCantidad(final int cantidad) {
		this.cantidad = cantidad;
	}

	// Setea un precio a la mercancia
	public void setPrecio(final double precio) {
		this.precio = precio;
	}
}