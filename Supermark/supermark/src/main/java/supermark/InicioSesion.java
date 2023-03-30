package supermark;

public class InicioSesion {
	
    //ATRIBUTOS
    private String username;
    private String password;

    //CONSTRUCTOR
    public InicioSesion(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //METODOS GET Y SET
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
