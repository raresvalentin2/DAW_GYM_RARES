package accesoDatos;

// esta clase queda preparada para cuando conectemos la base de datos
/**
 * Clase preparada para la conexion con la base de datos
 */
public class ConexionBD {
    private String url = "jdbc:mysql://localhost:3306/RARES_GYM";
    private String usuario = "root";
    private String password = "";

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
}
