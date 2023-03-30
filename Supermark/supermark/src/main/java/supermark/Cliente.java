package supermark;

import java.util.ArrayList;

public class Cliente extends Usuario {

	//ATRIBUTOS
	private int idCliente;
	private Domicilio domicilio;
	private int descuentoPorcentaje;
	private Carrito carrito;
	private ArrayList<Compra> listaCompras;

	//CONSTRUCTORES
    public Cliente(String nombre, String apellido, String email) {
        super(nombre, apellido, email);
    }
    
    public Cliente(int idUsuario, String nombre, String apellido, String email) {
        super(idUsuario, nombre, apellido, email);
    }
    
    public Cliente(int idUsuario, String nombre, String apellido, String email, int idCliente) {
        super(idUsuario, nombre, apellido, email);
        this.idCliente = idCliente;
    }

    public Cliente(int id, String nombre, String apellido, String correo, Domicilio domicilio) {
		super(id, nombre, apellido, correo);
		this.domicilio = domicilio;
		this.descuentoPorcentaje = 10;
		this.carrito = new Carrito();
		this.listaCompras = new ArrayList<Compra> ();	
	}
    
    //METODOS GET Y SET
    public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Domicilio getIdDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	
	public int getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(int descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
	public ArrayList<Compra> getListaCompra() {
		return listaCompras;
	}

	public void setListaCompra(ArrayList<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}
    
    //METODOS DE LA CLASE
	public void muestraCliente() {
		System.out.println("****************************");
  		System.out.println("\tCLIENTE");
  		System.out.println("Id: " + idCliente);
  		System.out.println("Nombre: " + getNombre());
  		System.out.println("Apellido: " + getApellido());
  		System.out.println("Email: " + getEmail());
	}
	
    public void verCompras(){
        
    }

	public void VerProductosSeleccionados() {
		
		
	}
    
}