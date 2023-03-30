package supermark;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAdmin {

	Scanner leer = new Scanner(System.in);
	
	private Supermercado supermark;
	
	public MenuAdmin() throws SQLException{
	
		this.supermark = new Supermercado();
		int opcion = 0;
		
		do {
			
			System.out.println("\tMENU ADMINISTRADOR");
			System.out.println("1. VER LISTADO DE PRODUCTOS");
			System.out.println("2. CARGAR PRODUCTOS A LA APLICACIÓN");
			System.out.println("3. MODIFICAR LOS DATOS DE LOS PRODUCTOS CARGADOS");
			System.out.println("4. VER TODOS LOS USUARIOS QUE REALIZARON UNA COMPRA");
			System.out.println("5. VER LISTADO DE PRODUCTOS SELECCIONADOS POR EL USUARIO");
			System.out.println("0. SALIR.");
			System.out.print("Elija: ");
			
			opcion = leer.nextInt();
			
			switch(opcion) {
			
				case 1:
					supermark.verProductos();
					break;
				
				case 2:
					cargarProductoAdmin();
					break;
				
				case 3:
					modificarProductoAdmin();
					break;
				
				case 4:
					List<Cliente> listaClientes = new ArrayList<Cliente>();
			    	listaClientes = supermark.getClientesQueCompraron();
			    	supermark.verClientes(listaClientes);
					break;
				
				case 5:
					verCarrito();
					break;
					
				case 0:
					break;
					
				default: 
					System.out.println("Opcion incorrecta.");
				    break;
			}
			
		}while(opcion != 0);
		
	
	}
	
	
    public void cargarProductoAdmin() throws SQLException{
		
		System.out.println("\tESTA POR AGREGAR UN PRODUCTO");
		
		System.out.println("INGRESE LA DESCRIPCION DEL PRODUCTO: ");
		String descripcion = leer.next();
		System.out.println("INGRESE LA MARCA: ");
		String marca = leer.next();
		System.out.println("INGRESE EL STOCK: ");
		int cantStock = leer.nextInt();
		System.out.println("INGRESE EL PRECIO:");
		Double precio = leer.nextDouble();
		System.out.println("INGRESE LA CATEGORIA (GALLETAS, BEBIDAS, CARNES): ");
		String categoria = leer.next();
		
		Producto nuevoProducto = new Producto(descripcion, marca, cantStock, precio, categoria);
		nuevoProducto.toString();
		this.supermark.guardarNuevoProducto(nuevoProducto);
		
		System.out.println("PRODUCTO AÑADIDO CON EXITO!");
	}
    
    
    public void modificarProductoAdmin() throws SQLException {	
		
		supermark.verProductos();
		
		System.out.println("\tESTA POR MODIFICAR UN PRODUCTO");
		System.out.print("Ingrese el Id del producto a modificar: ");
		int idProducto = leer.nextInt();
		
		this.supermark.modificarProducto(idProducto);
		
		System.out.println("PRODUCTO ACTUALIZADO!");
	}
    
    public void verCarrito() {
    	
    	Carrito carrito = new Carrito();
    	
    	List<Cliente> listaClientes = new ArrayList<Cliente>();
    	listaClientes = supermark.getClientes();
    	
    	supermark.verClientes(listaClientes);
		
    	System.out.print("Ingrese el id del Cliente: ");
    	int idCliente = leer.nextInt();
    	
    	System.out.println("CARRITO del CLIENTE con ID = " + idCliente + ": ");
    	carrito = supermark.getCarrito(idCliente);
		carrito.muestraCarrito();

    }
    
	
}