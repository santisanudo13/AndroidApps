package es.unican.g3.tus.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.ParserJSON;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;

/** Clase para obtener y sincronizar asincronamente los datos proporcionados por el
 *  Ayuntamiento de Santander sobre el servicio TUS.
 *
 *  Una vez realizado se invoca la actividad principal.
 *
 * Created by fernando on 9/11/17.
 */

public class LoadDataAsync extends AsyncTask<Object, Boolean, Boolean> {

    public static final String ENTRA = "ENTRA";
    private List<Parada> listaParadasBus;
    private RemoteFetch remoteFetchParadas;
    private List<Linea> listaLineasBus;
    private List<Estimacion> listaEstimacionesBus;
    private RemoteFetch remoteFetchLineas;
    private RemoteFetch remoteFetchEstimaciones;
    private Context contextLlamador;
    private Activity activityLlamadora;
    private static final String ERROR="ERROR";

    public LoadDataAsync(Activity activity, Context context) {
        this.remoteFetchParadas = new RemoteFetch();
        this.remoteFetchLineas = new RemoteFetch();
        this.remoteFetchEstimaciones = new RemoteFetch();
        this.contextLlamador = context;
        this.activityLlamadora = activity;
    }

    public Activity getActivityLlamadora(){
        return activityLlamadora;
    }

    /**
     * Descarga las paradas del servicio remoto externo.
     *
     * @return InputStream
     */
    private InputStream descargaParadas()
    {
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_PARADAS_BUS);
            return remoteFetchParadas.getBufferedData();
        } catch (IOException e) {
            Log.w("Error", e);
            return null;
        }
    }
    /**
     * Descarga las lineas del servicio remoto externo.
     *
     * @return InputStream
     */
    private InputStream descargaLineas()
    {
        try {
            remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
            return remoteFetchLineas.getBufferedData();
        } catch (IOException e) {
            Log.w(ERROR, e);
            return null;
        }
    }
    /**
     * Descarga las estimaciones del servicio remoto externo.
     *
     * @return InputStream
     */
    private InputStream descargaEstimaciones()
    {
        try {
            remoteFetchEstimaciones.getJSON(RemoteFetch.URL_ESTIMACIONES_BUS);
            return remoteFetchEstimaciones.getBufferedData();
        } catch (IOException e) {
            Log.w(ERROR, e);
            return null;
        }
    }

    /**
     * Método a través del cual se almacenan las paradas de buses en el atributo listaParadasBus
     * de esta clase. Para ello se parsea el JSON recibido por argumento.
     * @param i stream que contiene el JSON
     * @return boolean
     */
    public boolean obtenParadas(InputStream i) {
        try {
            if(i != null) {
                listaParadasBus = ParserJSON.readArrayParadasBus(i);
                Log.d(ENTRA, "Obten paradas: " + listaParadasBus.size());
                return true;
            }else{
                Log.e(ERROR, "Input obtenparadas nulo");
                return false;
            }
        }catch(Exception e){
            Log.e(ERROR,"Error en la obtención de las paradas de bus: "+e.getMessage());
            Log.w("", e);
            return false;
        }//try
    }//obtenParadas

    public boolean obtenLineas(InputStream i) {
        try {
            if(i != null) {
                listaLineasBus = ParserJSON.readArrayLineasBus(i);
                Log.d(ENTRA, "Obten lineas: " + listaLineasBus.size());
                return true;
            }else{
                Log.e(ERROR, "Input obten lineas nulo");
                return false;
            }
        }catch(Exception e){
            Log.e(ERROR,"Error en la obtención de las lineas de bus: "+e.getMessage());
            Log.w("", e);
            return false;
        }//try
    }//obtenParadas
    public boolean obtenEstimaciones(InputStream i) {
        try {
            if(i != null) {
                listaEstimacionesBus = ParserJSON.readArrayEstimacionesBus(i);
                Log.d(ENTRA, "Obten lineas: " + listaEstimacionesBus.size());
                return true;
            }else{
                Log.e(ERROR, "Input obten estimaciones nulo");
                return false;
            }
        }catch(Exception e){
            Log.e(ERROR,"Error en la obtención de las lineas de bus: "+e.getMessage());
            Log.w("", e);
            return false;
        }//try
    }//obtenParadas


    public List<Parada> getListaParadasBus() {
        return listaParadasBus;
    }//getListaParadasBus
    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaParadasBus
    public List<Estimacion> getListaEstimacionesBus() {
        return listaEstimacionesBus;
    }//getListaParadasBus

    @Override
    protected Boolean doInBackground(Object... objects) {
        // Se descargan "de fondo" las paradas y líneas
        obtenParadas(descargaParadas());
        obtenLineas(descargaLineas());
        return true;
    }

    /**
     * Acciones a ejecutar una vez se ha realizado la carga de datos.
     *
     * @param bool estado
     */
    @Override
    protected void onPostExecute(Boolean bool) {
        // Se muestra mensaje de error o correcto
        if(getListaParadasBus() == null || getListaLineasBus()==null) {
            // Mensaje de error
            Toast.makeText(contextLlamador, R.string.app_fallo_conexion, Toast.LENGTH_SHORT).show();
        } else {
            // Sincronización de datos remotos con locales
            Database db = new Database(contextLlamador);
            db.sincronizarParadas(getListaParadasBus());
            db.sincronizarLineas(getListaLineasBus());
            // Mensaje correcto
            Toast.makeText(contextLlamador, R.string.app_carga_datos_ok, Toast.LENGTH_SHORT).show();
        }
    }

}