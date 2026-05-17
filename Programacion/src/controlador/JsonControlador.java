package controlador;

import accesoDatos.GeneradorJSON;

public class JsonControlador {
    private GeneradorJSON generador = new GeneradorJSON();

    public void generar() throws Exception {
        generador.generarActividades();
    }
}