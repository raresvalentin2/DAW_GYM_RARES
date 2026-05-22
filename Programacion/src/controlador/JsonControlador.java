package controlador;

import accesoDatos.GeneradorJSON;

/**
 * Controlador para generar el JSON
 */
public class JsonControlador {
    private GeneradorJSON generador = new GeneradorJSON();

    public void generar() throws Exception {
        generador.generarActividades();
    }
}