package supermark;

public class Domicilio {
	//ATRIBUTOS
	private String calle;
	private int numero;
	
	//CONSTRUCTORES
	public Domicilio(String calle, int numero) {
		super();
		this.calle = calle;
		this.numero = numero;
	}

	//METODOS GET Y SET
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}