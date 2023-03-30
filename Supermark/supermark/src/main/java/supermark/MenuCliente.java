package supermark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuCliente {
	
	Scanner leer = new Scanner(System.in);
	
	private Supermercado supermark;
	private Cliente cliente;
	
	private static final String URL_BD = "jdbc:mysql://localhost:3306/supermark";
    private static final String USER = "root";
    private static final String PASS = "41122022";
	
	public MenuCliente(Cliente cliente) throws SQLException{
		
		this.supermark = new Supermercado();
		this.cliente = cliente;
		int opcion = 0;
		
		System.out.println("Bienvendo/a " + cliente.getNombre());
		
		do {
			System.out.println("\tMENU USUARIO");
			System.out.println("1. SELECCIONAR PRODUCTOS.");
			System.out.println("2. VER CARRITO.");
			System.out.println("3. AUTORIZAR COMPRA DE CARRITO."); //UPDATE
			System.out.println("0. SALIR."); //UPDATE
			System.out.print("Elija: ");
			
			opcion = leer.nextInt();
		
			switch(opcion) {
				
				case 1:
					this.seleccionarProductos();
					break;
				
				case 2:
					System.out.println("\nSU CARRITO:");
					Carrito carrito = new Carrito();
					carrito = supermark.getCarrito(cliente.getIdCliente());
					carrito.muestraCarrito();
					break;
				
				case 3:
					this.autorizarCompraCarrito(cliente);
					break;
					
				case 0:
					break;
				
				default: 
					System.out.println("Opcion incorrecta.");
				    break;
			}
		
		} while(opcion != 0);
	
	}
	
	
	//CONEXION
	private Statement getStatement(){
		try{
			Connection conn = DriverManager.getConnection(URL_BD, USER, PASS);
	        Statement stm = conn.createStatement();
	        return stm;
	     }catch(SQLException e){
	        System.out.println("No se pudo conectar");
	     }
	     return null;
	}
	
	//AGREGAR PRODUCTOS AL CARRITO
	public void seleccionarProductos() throws SQLException {
		
		Statement stm = getStatement();
		int control = 1;
		int topeCarrito = 30;
		int stockDisponible = 0;
		
		do {
			
			supermark.verProductos();
			
			System.out.println("Ingrese el Id del producto que quiere agregar al carrito y la cantidad del mismo");
			
			System.out.print(" - IdProducto: ");
			int idProducto = leer.nextInt();
			System.out.print(" - Cantidad: ");
			int cantidad = leer.nextInt();
			
			//PRIMER PASO: Controlar que cantidad <= Stock.
			stockDisponible = supermark.getStock(idProducto);
			
			if(cantidad <= stockDisponible) {
				
				//SEGUNDO PASO: Comprobar que la cantidad de productos que se quiere agregar no supere el tope del carrito.
				topeCarrito = topeCarrito - cantidad;
				System.out.println("CANT: " + topeCarrito);
				
				if(topeCarrito >= 0) {
					
					//TERCER PASO: Inserto
					String sql = "INSERT INTO CarritoxProducto VALUES((SELECT idCarrito FROM Carrito WHERE idCliente = " + cliente.getIdCliente() + "), " + idProducto + ", " + cantidad + ");";
					stm.executeUpdate(sql);
					
					System.out.println("PRODUCTO AÑADIDO!");
				}
				else {
					topeCarrito = topeCarrito + cantidad;
					System.out.println("UPS.. Carrito Lleno! Supero los 30 productos permitidos.");
				}
			}else {
				System.out.println("Solo quedan " + stockDisponible + " disponibles.");
			}
			
			System.out.println("Desea agregar otro producto? 1-SI 0-NO");
			control = leer.nextInt();
			
		}while(topeCarrito > 0 || control == 1);
		
		stm.close();
	
	}
	
	//AUTORIZAR LA COMPRA DE UN CARRITO
	public void autorizarCompraCarrito(Cliente cliente) throws SQLException {
		
		Statement stm = getStatement();
		String sql;
		double total = 0;
		double totalDescuento = 0;
	    
		System.out.println("Estas seguro/a de realizar esta compra? 1-SI 0-NO");
		int respuesta = leer.nextInt();
		
		if(respuesta == 1) {
			//CARGO COMPRA
			sql = "INSERT INTO Compra(idCliente, idCarrito) VALUES (" + cliente.getIdCliente() + ", (SELECT idCarrito FROM Carrito WHERE idCliente = " + cliente.getIdCliente() + "));";
			stm.executeUpdate(sql);
			
			Carrito carrito = new Carrito();
			carrito = supermark.getCarrito(cliente.getIdCliente());
			total = carrito.calcularTotalCarrito();
			totalDescuento = total *  ((100 - cliente.getDescuentoPorcentaje()) / 100);
			
			//AL HACER LA COMPRA SE GENERA UNA FACTURA
			String sql_idCompra = "SELECT idCompra FROM Compra WHERE idCliente = " + cliente.getIdCliente() + ";";
			ResultSet rs = stm.executeQuery(sql_idCompra);
			
			int idCompra = 0;
			if(rs.next()) {
	    		idCompra = rs.getInt("idCompra");
	    	}
			
			String sql2 = "INSERT INTO Factura (idCompra, total, totalDescuento) VALUES (" + rs.getInt("idCompra") + ", " + total + ", " + totalDescuento + ");"; 
			stm.executeUpdate(sql2);
			
			System.out.println("COMPRA REALIZADA CON EXITO!");
			
			stm.close();
		}
			
	}


}