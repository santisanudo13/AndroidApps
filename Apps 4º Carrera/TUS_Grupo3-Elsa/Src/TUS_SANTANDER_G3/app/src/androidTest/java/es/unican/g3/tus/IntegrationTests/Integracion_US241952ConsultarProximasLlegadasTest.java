package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.LoadDataAsync;

import static junit.framework.Assert.assertTrue;

/**
 * Created by LuisOsle on 28/11/17.
 */

public class Integracion_US241952ConsultarProximasLlegadasTest {

    @Test
    public void PI1US241952() throws Exception {

        InputStream is;
        LoadDataAsync loadDataAsync;

        // Paradas almacenadas en el JSON local
        is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus_completo);
        loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenParadas(is);
        List<Parada> paradas = loadDataAsync.getListaParadasBus();

        Parada adarzo=null;
        Parada puertochico=null;

        for (int i = 0; i < paradas.size(); i++) {
            if (paradas.get(i).getNumero().equals("455")) {
                adarzo = paradas.get(i);
            }
            if (paradas.get(i).getNumero().equals("16")) {
                puertochico = paradas.get(i);
            }
        }

        is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.estimaciones_bus_completo);
        loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenEstimaciones(is);
        List<Estimacion> estimaciones = loadDataAsync.getListaEstimacionesBus();

        List<Estimacion > puertochiEstimacion = new ArrayList<Estimacion>();
        List<Estimacion > adarzoEstimacion = new ArrayList<Estimacion>();

        for (int i = 0; i < estimaciones.size(); i++) {
            if (estimaciones.get(i).getParadaId() == 164) {
                puertochiEstimacion.add(estimaciones.get(i));
            }
            if(estimaciones.get(i).getParadaId()== 26416){
                adarzoEstimacion.add(estimaciones.get(i));
            }
        }

        assertTrue(puertochiEstimacion.get(0).getParadaId() == puertochico.getIdentifier());
        assertTrue(adarzoEstimacion.size()==0);
    }
}