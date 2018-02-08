package es.unican.g3.tus.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.ParserJSON;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;
import es.unican.g3.tus.views.EstimacionesFragment;

/** Clase para obtener y sincronizar asincronamente los datos proporcionados por el
 *  Ayuntamiento de Santander sobre el servicio TUS.
 *
 *  Una vez realizado se invoca el fragmento de estimaciones.
 *
 * Created by fernando on 9/11/17.
 */

public class EstimacionesAsync extends AsyncTask<Object, Boolean, Boolean> {

    public static final String ENTRA = "ENTRA";
    private List<Parada> listaParadasBus;
    private RemoteFetch remoteFetchParadas;
    private List<Linea> listaLineasBus;
    private List<Estimacion> listaEstimacionesBus;
    private RemoteFetch remoteFetchLineas;
    private RemoteFetch remoteFetchEstimaciones;
    private Context contextLlamador;
    private Activity activityLlamadora;
    private Parada paradaLlamadora;
    private static final String ERROR="ERROR";
    private EstimacionesFragment fragmentEstimaciones;
    private FragmentManager fm;


    public EstimacionesAsync(Activity activity, Context context, Parada paradaLlamadora, FragmentManager fm) {
        this.remoteFetchParadas = new RemoteFetch();
        this.remoteFetchLineas = new RemoteFetch();
        this.remoteFetchEstimaciones = new RemoteFetch();
        this.contextLlamador = context;
        this.activityLlamadora = activity;
        this.paradaLlamadora = paradaLlamadora;
        this.fragmentEstimaciones = new EstimacionesFragment();
        this.fm = fm;
        fragmentEstimaciones.anhadeParada(paradaLlamadora.getNumero());
    }

    public Activity getActivityLlamadora(){
        return activityLlamadora;
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
        // Se cargan "de fondo" las paradas

        if(obtenEstimaciones(descargaEstimaciones())){
            List<Estimacion> estimacionesTotales = getListaEstimacionesBus();
            List<Estimacion> estimacionesFiltradas= new ArrayList<Estimacion>();

            for(Estimacion estimacion : estimacionesTotales){
                if(estimacion.getParadaId() == Integer.parseInt(paradaLlamadora.getNumero()) && estimacion.getDistancia() != 0){
                    estimacionesFiltradas.add(estimacion);
                }
            }

            Database db = new Database(this.contextLlamador);
            db.eliminaEstimacionParada(Integer.parseInt(paradaLlamadora.getNumero()));
            db.sincronizarEstimaciones(estimacionesFiltradas);
        }
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
        if(getListaEstimacionesBus()==null) {
            // Mensaje de error
            Toast.makeText(contextLlamador, R.string.app_fallo_conexion, Toast.LENGTH_SHORT).show();
        }
        this.fm.beginTransaction()
                .replace(R.id.frameLayoutElements, fragmentEstimaciones).commit();
    }

}