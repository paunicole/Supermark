package supermark;

import static spark.Spark.*;
import java.sql.SQLException;
import com.google.gson.Gson;

public class AppGson {

	public static void main(String[] args) {
		Gson mapper = new Gson();

        Supermercado supermark = new Supermercado();

        port(8080);

        //CONSULTA LISTA DE PRODUCTOS
        get("/getProducto", (request, response) -> {
            response.type("application/json");
            return supermark.getProductos();
        }, mapper::toJson);
        
        //CREAR PRODUCTO
        /*
        post("/crearProducto", "application/json", (request, response) -> {
            if(supermark.isAdmin( mapper.fromJson(request.headers("Authorization"), Usuario.class) )){
                //Es Admin
                try{
                    Producto nuevoProducto = mapper.fromJson(request.body(), Producto.class);
                    supermark.guardarNuevoProducto(nuevoProducto);
                    return "OK";
                }catch(SQLException e){
                    response.status(400);
                    return e.getLocalizedMessage();
                }
            }else{
                //No es Admin
                response.status(401);
                return "FAIL";
            }
        });
     	*/

	}

}
