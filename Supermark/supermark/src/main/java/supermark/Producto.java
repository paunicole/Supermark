package supermark;

public class Producto {

	//ATRIBUTOS
    private int id;
    private String descripcion; 
    private String marca;
    private int cantStock;
    private double precio;
    private String categoria;
    private int cantidad; //Atributo util para MENU_CLIENTE
    private double subtotal; //Atributo util para MENU_CLIENTE

	//CONSTRUCTOR
    public Producto(int id, String descripcion, String marca, int cantStock, double precio, String categoria) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantStock = cantStock;
        this.precio = precio;
        this.categoria = categoria;
    }
    
    public Producto(String descripcion, String marca, int cantStock, double precio, String categoria) {
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantStock = cantStock;
        this.precio = precio;
        this.categoria = categoria;
    }
    
    //CONSTRUCTOR UTIL PARA CLIENTE
    public Producto(int id, String descripcion, String marca, String categoria, double precio, int cantidad) {
        this.id = id;
    	this.descripcion = descripcion;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = precio * cantidad;
    }
    

    //METODOS GET Y SET
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
        return this.marca;
    }
	
    public void setMarca(String marca) {
        this.marca = marca;
    }
	
	public int getCantStock() {
		return cantStock;
	}
	
	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}
    
    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


    //METODOS DE LA CLASE
  	public void muestraProducto() {
  		System.out.println("****************************");
  		System.out.println("\tPRODUCTO");
  		System.out.println("Id: " + id);
  		System.out.println("Descripcion: " + descripcion);
  		System.out.println("Marca: " + marca);
  		System.out.println("Stock: " + cantStock);
  		System.out.println("Precio: $" + precio);
  		System.out.println("Categoria " + categoria);
  	}
  	
  	//MUESTRA PRODUCTO UTIL PARA CLIENTE
    public void muestraProductoCliente() {
    	System.out.println("****************************");
  		System.out.println("\tPRODUCTO");
  		System.out.println("Id: " + id);
  		System.out.println("Descripcion: " + descripcion);
  		System.out.println("Marca: " + marca);
  		System.out.println("Categoria: " + categoria);
  		System.out.println("Precio: $" + precio);
  		
  		System.out.println();
  		
  		System.out.println("CANTIDAD: " + cantidad);
  		System.out.println("SUBTOTAL: $" + subtotal);
    }

}
