package unican.es.grupo4_tus_santander.presenter.paradas;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import unican.es.grupo4_tus_santander.models.basedatos.helper.DatabaseHelper;
import unican.es.grupo4_tus_santander.models.pojos.Color;
import unican.es.grupo4_tus_santander.models.pojos.Linea;
import unican.es.grupo4_tus_santander.models.pojos.Parada;
import unican.es.grupo4_tus_santander.models.pojos.ParadaConNombre;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.ParserJSON;
import unican.es.grupo4_tus_santander.models.webservice.dataloaders.RemoteFetch;
import unican.es.grupo4_tus_santander.presenter.paradas.asynctasks.GetDataServicioParadas;
import unican.es.grupo4_tus_santander.view.paradas.ParadasActivity;


public class RecargaBaseDatosParadas {

    private static final Logger LOGGER = Logger.getLogger( RecargaBaseDatosParadas.class.getName() );

    private ParadasActivity activityParadas;
    private Context contextParadas;
    private ListParadasPresenter presenterParadas;

    private List<Linea> listLineas = new ArrayList<>();
    private List<Parada> listParadas = new ArrayList<>();
    private List<ParadaConNombre> listParadasConNombre = new ArrayList<>();

    private ServicioListener listenerParadas;

    private ConnectivityManager cm = null;


    private  RemoteFetch remoteFetchParadas = new RemoteFetch();

    DatabaseHelper dbParadas;

    public RecargaBaseDatosParadas(Context context, ParadasActivity activity, ListParadasPresenter presenter){
        this.activityParadas = activity;
        this.contextParadas = context;
        this.presenterParadas = presenter;
        this.cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }// MainPresenter


    public void start(){
        activityParadas.showProgress(true, 0);
        new GetDataServicioParadas(this).execute();
    }// start


    public boolean obtenData(){

        //SI NO TIENE INTERNET DEVUELVE FALSE
        if(cm == null)
            return false;

        //LINEAS
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_LINEAS_BUS);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del JSON de líneas");
        }
        try {
            listLineas = ParserJSON.readArrayLineasBus(remoteFetchParadas.getBufferedData());
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al leer el array de líneas");
        }
        //PARADAS
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_PARADAS);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del JSON de paradas");
        }
        try {
            listParadas = ParserJSON.readArrayParadas(remoteFetchParadas.getBufferedData());
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al leer el array de paradas");
        }
        //PARADAS CON NOMBRE
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_PARADAS_NOMBRE);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al obtener datos del JSON de paradas con nombre");
        }
        try {
            listParadasConNombre = ParserJSON.readArrayParadasConNombre(remoteFetchParadas.getBufferedData());
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Excepción al leer el array de paradas con nombre");        }

        return !listLineas.isEmpty() && !listParadas.isEmpty();
    }



    public void guardaDataEnBaseDatos() {
        dbParadas = new DatabaseHelper(this.contextParadas,1);
        dbParadas.reiniciarTablas();

        for(Linea l: listLineas) {
            long colorParadasID = -1;

            switch (l.getNumero()+"") {
                case "1":
                    colorParadasID = dbParadas.createColor(new Color(255, 255, 0, 0));
                    break;
                case "2":
                    colorParadasID = dbParadas.createColor(new Color(255, 171, 68, 206));
                    break;
                case "3":
                    colorParadasID = dbParadas.createColor(new Color(255, 253, 205, 47));
                    break;
                case "4":
                    colorParadasID = dbParadas.createColor(new Color(255, 48, 180, 214));
                    break;
                case "5C1":
                    colorParadasID = dbParadas.createColor(new Color(255, 150, 150, 150));
                    break;
                case "5C2":
                    colorParadasID = dbParadas.createColor(new Color(255, 150, 150, 150));
                    break;
                case "6C1":
                    colorParadasID = dbParadas.createColor(new Color(255, 15, 127, 52));
                    break;
                case "6C2":
                    colorParadasID = dbParadas.createColor(new Color(255, 15, 127, 52));
                    break;
                case "7C1":
                    colorParadasID = dbParadas.createColor(new Color(255, 244, 98, 38));
                    break;
                case "7C2":
                    colorParadasID = dbParadas.createColor(new Color(255, 244, 98, 38));
                    break;
                case "11":
                    colorParadasID = dbParadas.createColor(new Color(255, 2, 23, 91));
                    break;
                case "12":
                    colorParadasID = dbParadas.createColor(new Color(255, 164, 211, 99));
                    break;
                case "13":
                    colorParadasID = dbParadas.createColor(new Color(255, 144, 129, 176));
                    break;
                case "14":
                    colorParadasID = dbParadas.createColor(new Color(255, 14, 105, 175));
                    break;
                case "16":
                    colorParadasID = dbParadas.createColor(new Color(255, 98, 24, 54));
                    break;
                case "17":
                    colorParadasID = dbParadas.createColor(new Color(255, 246, 128, 132));
                    break;
                case "18":
                    colorParadasID = dbParadas.createColor(new Color(255, 177, 232, 224));
                    break;
                case "19":
                    colorParadasID = dbParadas.createColor(new Color(255, 18, 132, 147));
                    break;
                case "20":
                    colorParadasID = dbParadas.createColor(new Color(255, 136, 248, 170));
                    break;
                case "21":
                    colorParadasID = dbParadas.createColor(new Color(255, 163, 211, 98));
                    break;
                case "23":
                    colorParadasID = dbParadas.createColor(new Color(255, 202, 202, 202));
                    break;
                default:
                    colorParadasID = dbParadas.createColor(new Color(255, 0, 0, 0));
                    break;
            }
            long lineaID = dbParadas.createLinea(l, colorParadasID);
            l.setId((int) lineaID);

            for(Parada parada : listParadas){
                if(parada.getIdentifierLinea() == l.getIdentifier()){
                    for(ParadaConNombre paradaConNombre : listParadasConNombre){
                        if(parada.getNumParada() == paradaConNombre.getNumero()) {
                            parada.setNombre(paradaConNombre.getParada());
                            parada.setFavorito(0);
                        }
                    }

                    dbParadas.createParada(parada, l.getId());
                }
            }
        }
        dbParadas.closeDB();
    }


    public static interface ServicioListener {
        public void onComplete();
    }


    public ServicioListener getListener() {
        return listenerParadas;
    }

    public void setListener(ServicioListener listener) {
        this.listenerParadas = listener;
    }


    public ConnectivityManager getCm() {
        return cm;
    }

    public void setCm(ConnectivityManager cm) {
        this.cm = cm;
    }

    public List<Linea> getListLineas() {
        return listLineas;
    }

    public List<Parada> getListParadas() {
        return listParadas;
    }

    public List<ParadaConNombre> getListParadasConNombre() {
        return listParadasConNombre;
    }

    public ParadasActivity getActivity() {
        return activityParadas;
    }

    public void setActivity(ParadasActivity activity) {
        this.activityParadas = activity;
    }

    public ListParadasPresenter getPresenter() {
        return presenterParadas;
    }

    public void setPresenter(ListParadasPresenter presenter) {
        this.presenterParadas = presenter;
    }
}