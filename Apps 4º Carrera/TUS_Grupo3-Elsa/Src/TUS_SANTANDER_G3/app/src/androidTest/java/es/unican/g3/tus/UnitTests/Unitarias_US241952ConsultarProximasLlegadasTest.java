package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.presenter.LoadDataAsync;

import static org.junit.Assert.assertTrue;

public class Unitarias_US241952ConsultarProximasLlegadasTest {
    /**
     * Test para comprobar el correcto funcionamiento de las estimaciones
     * Caso: JSON proporcionado correcto
     * @throws Exception excepcion
     */
    @Test
    public void PU1US241952() throws Exception {

        // Estimaciones almacenadas en el JSON local
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.estimaciones_test);
        LoadDataAsync loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenEstimaciones(is);
        List<Estimacion> estimaciones = loadDataAsync.getListaEstimacionesBus();

        // Estimaciones que deberían obtenerse
        List<Estimacion> estimacionsResultadoA = new ArrayList<>();
        Estimacion estimacion1=new Estimacion(10,60,15,42063,"7C1");
        Estimacion estimacion2=new Estimacion(100,120,6,42063,"E2");
        Estimacion estimacion3=new Estimacion(1000,180,7,50693,"3");
        Estimacion estimacion4=new Estimacion(1010,190,4,42064,"5C1");
        Estimacion estimacion5=new Estimacion(1100,200,5,42063,"4");
        Estimacion estimacion6=new Estimacion(2000,240,106,42064,"1");
        estimacionsResultadoA.add(estimacion1);
        estimacionsResultadoA.add(estimacion2);
        estimacionsResultadoA.add(estimacion3);
        estimacionsResultadoA.add(estimacion4);
        estimacionsResultadoA.add(estimacion5);
        estimacionsResultadoA.add(estimacion6);

        // Se comprueba que los valores esperados y obtenidos coinciden
        for(int i = 0; i < estimaciones.size(); i++){
            assertTrue(estimaciones.get(i).getTiempo()==estimacionsResultadoA.get(i).getTiempo());
            assertTrue(estimaciones.get(i).getDistancia()==estimacionsResultadoA.get(i).getDistancia());
            assertTrue(estimaciones.get(i).getLinea().equals(estimacionsResultadoA.get(i).getLinea()));
            assertTrue(estimaciones.get(i).getParadaId()==(estimacionsResultadoA.get(i).getParadaId()));
            assertTrue(estimaciones.get(i).getIdentifier()==(estimacionsResultadoA.get(i).getIdentifier()));
        }

    }
}