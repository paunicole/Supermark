package supermark;

import java.sql.SQLException;
import java.util.Scanner;

public class AppPrincipal {
	
	static private Scanner leer = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		
		Supermercado supermark = new Supermercado();
		Sistema sistema = new Sistema();
		String username, password;
		
        System.out.println("\tSUPERMARK");
		System.out.println("1. INICIAR SESION");
		System.out.println("2. REGISTRARSE");
		System.out.println("0. SALIR");
		System.out.print("Elija: ");
		
		int opcion = leer.nextInt();
		
		switch(opcion) {
			
			case 1:
							
				System.out.println("\tINICIAR SESION");
				
				System.out.print("INGRESE USERNAME: ");
				username = leer.next();
				System.out.print("INGRESE CONSTRASEÑA: ");
				password = leer.next();
				
				//InicioSesion inicioSesion = new InicioSesion(username, password);
				InicioSesion inicioSesion = new InicioSesion("pepito", "pepe123");
				
		        Usuario usuario = sistema.iniciarSesion(inicioSesion);
		        System.out.println("HOLA " + usuario.getNombre() + "! :)");
		        
		        if(supermark.isAdmin(usuario)) {
		        	//SE ABRE MENU DE ADMINISTRADOR
					MenuAdmin menuAdmin = new MenuAdmin();
				}
				else {
					//SE ABRE MENU DE CLIENTE
					Cliente cliente = new Cliente (usuario.getNombre(), usuario.getApellido(), usuario.getEmail()); //FALTA CARGAR EL CLIENTE CON SUS DATOS EN LA TABLA CLIENTE (BD). PROBAR EN TEST_MENU 
					//Cliente cliente = new Cliente(1, "pepe", "perez", "pepe@gmail.com", 1); --> (idUsuario, nombre, apellido, email, idCliente)
					MenuCliente menuCliente = new MenuCliente(cliente);
				}
		        
				break;
			
			case 2:
				
				System.out.println("BIENVENIDO REGISTRO. COMPLETE CON SUS DATOS");
				
				System.out.print("INGRESE SU NOMBRE: ");
				String nombre = leer.next();
				System.out.print("INGRESE SU APELLIDO: ");
				String apellido = leer.next();
				System.out.print("INGRESE SU EMAIL: ");
				String email = leer.next();
				System.out.print("INGRESE USERNAME: ");
				username = leer.next();
				System.out.print("INGRESE SU CONTRASEÑA: ");
				password = leer.next();
				System.out.print("REPITA SU CONTRASEÑA: ");
				String password2 = leer.next();

				NuevoUsuario nuevoUsuario = new NuevoUsuario(nombre, apellido, email, username, password, password2);
				//NuevoUsuario nuevoUsuario = new NuevoUsuario("Pepe", "Perez","pepe@gmail.com", "pepito", "pepe123", "pepe123");
				
				sistema.registrar(nuevoUsuario);
				System.out.println("BIENVENIDO " + nuevoUsuario.getNombre() + "! :)");
				
				break;
				
			case 0:
				break;
			
			default:
				System.out.println("Opcion incorrecta.");
				break;
		}
		
		System.out.println("Fin App");
        
	}
	
	

}

