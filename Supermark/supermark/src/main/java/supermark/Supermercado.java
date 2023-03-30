package supermark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supermercado {
    
	Scanner leer = new Scanner(System.in);
	
    private static final String URL_BD = "jdbc:mysql://localhost:3306/supermark";
    private static final String USER = "root";
    private static final String PASS = "41122022";
    
    //ATRIBUTOS
    private int horaApertura;
    private int horaCierre;
    
    //CONSTRUCTOR POR DEFECTO
    public Supermercado() {
		super();
		this.horaApertura = 800; //08:00
		this.horaCierre = 2000;  //20:00
	}
    
    //CONSTRUCTOR COMUN
    public Supermercado(int horaApertura, int horaCierre) {
		super();
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
	}

    //METODOS GET Y SET
	public int getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(int horaApertura) {
		this.horaApertura = horaApertura;
	}

	public int getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(int horaCierre) {
		this.horaCierre = horaCierre;
	}

	//METODOS DE LA CLASE
	
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

    //DEVUELVE LA LISTA DE PRODUCTOS
    public List<Producto> getProductos(){
        List<Producto> listaProducto = new ArrayList<Producto>();
        Statement stm = this.getStatement();
        try{
            ResultSet rs = stm.executeQuery("SELECT * FROM Producto");
            while(rs.next()){
                Producto unProducto = new Producto(rs.getInt("idProducto"), rs.getString("descripcion"), rs.getString("marca"), rs.getInt("cantStock"), rs.getDouble("precio"), rs.getString("categoria"));
                listaProducto.add(unProducto);
            }
            stm.close();
            return listaProducto;
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return listaProducto;
    }
    
    //MUESTRA LISTA DE PRODUCTOS
	public void verProductos() {
		
		List<Producto> listaProducto = new ArrayList<Producto>();
    	listaProducto = this.getProductos();
    	
    	System.out.println("\tLISTADO DE PRODUCTOS");
    	
    	for(int i = 0; i < listaProducto.size(); i++){
    		listaProducto.get(i).muestraProducto();
    		System.out.println();
    	}
	}

    //CARGA UN PRODUCTO EN LA BASE DE DATOS
    public void guardarNuevoProducto(Producto nuevoProducto) throws SQLException{
        Statement stm = getStatement();
        stm.executeUpdate("INSERT INTO Producto(descripcion, marca, cantStock, precio, categoria) VALUE ('" + nuevoProducto.getDescripcion() + "','" + nuevoProducto.getMarca() + "'," + nuevoProducto.getCantStock() + "," + nuevoProducto.getPrecio() + ",'"+ nuevoProducto.getCategoria()+"')");
        stm.close();
    }

    //MODIFICA UN PRODUCTO EN LA BASE DE DATOS
    public void modificarProducto(int idProducto) throws SQLException {	
		
    	int stock = 0, precio = 0; 
		int respuesta;
		
		Statement stm = this.getStatement();
		
		System.out.print("Desea modificar el stock? 1-SI 0-NO: ");
		respuesta = leer.nextInt();
		if(respuesta == 1) {
			System.out.print("Ingrese nuevo stock: ");
			stock = leer.nextInt();
			stm.executeUpdate("UPDATE Producto SET cantStock = "+ stock +" WHERE (idProducto = " + idProducto + ");");
		}
		
		System.out.print("Desea modificar el precio? 1-SI 0-NO: ");
		respuesta = leer.nextInt();
		if(respuesta == 1) {
			System.out.print("Ingrese nuevo precio: ");
			precio = leer.nextInt();
			stm.executeUpdate("UPDATE Producto SET precio = "+ precio +" WHERE (idProducto = " + idProducto + ");");
		}

	}
    
    //DEVUELVE STOCK
    public int getStock(int idProducto) throws SQLException {
    	
    	int cantStock = 0;
    	Statement stm = this.getStatement();
        
    	ResultSet rs = stm.executeQuery("SELECT cantStock FROM Producto WHERE idProducto = " + idProducto + ";");
        
    	if(rs.next()) {
    		cantStock = rs.getInt("cantStock");
    	}
        stm.close();

		return cantStock;

    }

    //DEVUELVE CARRITO
    public Carrito getCarrito(int idCliente){
        Carrito carrito = new Carrito();
        Statement stm = this.getStatement();
        try{
        	//DEVUELVE CADA PRODUCTO SELECCIONADO CON LA CANTIDAD REQUERIDA POR idCliente
        	String sql = "SELECT p.idProducto, p.descripcion, p.marca, p.categoria, p.precio, cxp.cantidad, cxp.cantidad*p.precio FROM Producto p INNER JOIN CarritoxProducto cxp ON p.idProducto = cxp.idProducto INNER JOIN Carrito c ON cxp.idCarrito = c.idCarrito AND idCliente = " + idCliente + ";";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Producto unProducto = new Producto(rs.getInt("idProducto"), rs.getString("descripcion"), rs.getString("marca"), rs.getString("categoria"), rs.getDouble("precio"), rs.getInt("cantidad"));
                carrito.agregaProducto(unProducto);
            }
            stm.close();
            return carrito;
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return carrito;
    }
    
    //DEVUELVE LISTA DE CLIENTES
    public List<Cliente> getClientes(){
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        Statement stm = this.getStatement();
        try{
            ResultSet rs = stm.executeQuery("SELECT u.idUsuario, idCliente, nombre, apellido, email FROM Usuario u INNER JOIN Cliente c ON u.idUsuario = c.idUsuario; ");
            while(rs.next()){
                Cliente unCliente = new Cliente(rs.getInt("idUsuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"),  rs.getInt("idCliente"));
                listaClientes.add(unCliente);
            }
            stm.close();
            return listaClientes;
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return listaClientes;
    }
    
    //DEVUELVE LISTA DE CLIENTES QUE HICIERON AL MENOS UNA COMPRA
    public List<Cliente> getClientesQueCompraron(){
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        Statement stm = this.getStatement();
        try{
        	String sql = "SELECT Usuario.idUsuario, Usuario.nombre, Usuario.apellido, Usuario.email, Cliente.idCliente FROM Compra INNER JOIN Cliente ON Compra.idCliente = Cliente.idCliente INNER JOIN Usuario ON Cliente.idUsuario = Usuario.idUsuario GROUP BY Compra.idCliente HAVING COUNT(*) > 0;";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Cliente unCliente = new Cliente(rs.getInt("Usuario.idUsuario"), rs.getString("Usuario.nombre"), rs.getString("Usuario.apellido"), rs.getString("Usuario.email"),  rs.getInt("Cliente.idCliente"));
                listaClientes.add(unCliente);
            }
            stm.close();
            return listaClientes;
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
        return listaClientes;
    }
    
	
	//MUESTRA LISTA DE CLIENTES
	public void verClientes(List<Cliente> listaClientes) {
	    	
	    System.out.println("\tLISTADO DE CLIENTES");
	    	
	    for(int i = 0; i < listaClientes.size(); i++){
	    	listaClientes.get(i).muestraCliente();
	    	System.out.println();
	    }
	}
	
    
    public Boolean isAdmin(Usuario usuario) {
        Statement stm = getStatement();

        try{
            ResultSet rs = stm.executeQuery("SELECT isAdmin FROM Usuario WHERE email='"+usuario.getEmail()+"'");
            if(rs.next()){
                return rs.getBoolean(1);
            }
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
 
        return false;
    }



}