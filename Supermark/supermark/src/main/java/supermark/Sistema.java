package supermark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sistema {
        
    private static final String BD_URL = "jdbc:mysql://localhost:3306/supermark";
    private static final String USER = "root";
    private static final String PASS = "41122022";

    //METODO PARA REGISTRARSE
    public void registrar(NuevoUsuario nuevoUsuario){
        if(!nuevoUsuario.getPassword().equals(nuevoUsuario.getRepeatPassword())){
            throw new RuntimeException("Las contraseñas no coinciden");
        }
        Connection con = null;
        Statement stm = null;
        try {
            con = DriverManager.getConnection(BD_URL, USER, PASS);
            stm = con.createStatement();
            stm.executeUpdate("INSERT INTO Usuario(nombre, apellido, email, username, password) VALUE('"+ 
                                nuevoUsuario.getNombre() +"','"+ nuevoUsuario.getApellido()+"','"+ nuevoUsuario.getEmail() +
                                "','"+nuevoUsuario.getUsername()+"','"+ nuevoUsuario.getPassword()+"')");
            System.out.println("Registro Existoso!");
            stm.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }
    }


    //METODO PARA INICIAR SESION
    public Usuario iniciarSesion(InicioSesion inicioSesion){
        Connection con = null;
        Statement stm = null;
        try {
            con = DriverManager.getConnection(BD_URL, USER, PASS);
            stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM Usuario WHERE username='" + inicioSesion.getUsername() + "'");
            
            if(result.next()){
              
            	if(result.getString("password").equals(inicioSesion.getPassword())){
                
            		if(result.getBoolean("isAdmin")){
                        //ES Administrador
            			System.out.println("Iniciando Sesion");
                        return new Administrador(result.getString("nombre"), result.getString("apellido"), result.getString("email"));
                    }else{
                        //ES Cliente
                    	System.out.println("Iniciando Sesion");
                        return new Cliente(result.getString("nombre"), result.getString("apellido"), result.getString("email"));
                    }
                }else{
                    throw new RuntimeException("Constraseña incorrecta");    
                }
            }else{
                throw new RuntimeException("No se encontro el usuario");
            }
        }catch(SQLException e){
            System.out.println(e.getLocalizedMessage());
        }

        return null;
    }
    
    /*
    public Cliente cargarDatosCliente(Usuario usuario) {

    	Cliente cliente = new Cliente(usuario.getIdUsuario(), usuario.getNombre(), usuario.getEmail());
    	
    	return cliente;
    }
    */


}