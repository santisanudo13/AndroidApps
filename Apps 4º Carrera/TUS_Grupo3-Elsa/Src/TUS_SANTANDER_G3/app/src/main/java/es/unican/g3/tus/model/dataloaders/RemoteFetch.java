package es.unican.g3.tus.model.dataloaders;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase en la que se realizan la descarga de los datos desde el servicio "REST"
 */

public class RemoteFetch {

    // URL para obtener un listado de las líneas de Bus de Santander (pero no de las sublineas)
    public static final String URL_LINEAS_BUS = "http://datos.santander.es/api/rest/datasets/lineas_bus.json";

    // URL para obtener un listado de las paradas de Bus de Santander
    public static final String URL_PARADAS_BUS = "http://datos.santander.es/api/rest/datasets/paradas_bus.json";

    // URL para obtener un listado de las paradas de Bus de Santander
    public static final String URL_ESTIMACIONES_BUS = "http://datos.santander.es/api/rest/datasets/control_flotas_estimaciones.json";

    // Contenido del JSON
    private BufferedInputStream bufferedData;

    /**
     * Metodo que a través de una dirección URL obtiene el bufferedInputStream correspondiente
     * al JSON alojado en el servidor y lo almacena en el atributo bufferedDataGasolineras de la
     * clase
     * @throws IOException excepcion de entrada/salida
     */
    public void getJSON(String urlJSON) throws IOException {
            URL url = new URL(urlJSON);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty("Accept", "application/json");
            bufferedData =  new BufferedInputStream(urlConnection.getInputStream());
    }//getJSON

    /**
     * Retorna el BufferedInputStream con el JSON, pero para que el objeto no este vacío debemos de
     * llamar antes a getJSON
     * @return BufferedInputStream con el JSON
     */
    public BufferedInputStream getBufferedData() {
        return bufferedData;
    }//getBufferedData

}//RemoteFetch
