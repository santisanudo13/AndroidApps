package es.unican.g3.tus;


import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.LoadDataAsync;
import es.unican.g3.tus.views.ParadasFragment;

import static org.junit.Assert.assertTrue;

/** Pruebas unitarias: Consultar listado de líneas
 * Clase que implementa los tests unitarios del caso de uso "Consultar listado de líneas"
 */
public class Unitarias_US241953ConsultarListadoLineasTest {

    /**
     * Test para comprobar el correcto funcionamiento del listado de líneas
     * Caso: JSON proporcionado correcto
     * @throws Exception excepcion
     */
    @Test
    public void PU1US241953() throws Exception {

        // Líneas almacenadas en el JSON local
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_test);
        LoadDataAsync loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenLineas(is);
        List<Linea> lineas = loadDataAsync.getListaLineasBus();
        Collections.sort(lineas);

        // Líneas que deberían obtenerse
        List<Linea> lineasResultadoA = new ArrayList<Linea>();
        Linea linea1 = new Linea("COMPLEJO C2", "6C2", 62);
        Linea linea2 = new Linea("LUIS QUINTANILLA", "7C1", 71);
        Linea linea3 = new Linea("JOAQUIN BUSTAMANTE", "7C2", 72);
        Linea linea4 = new Linea("ESTACIONES-BARRIO LA TORRE", "20", 20);
        Linea linea5 = new Linea("CORBAN-COMPLEJO por G. Davila", "N2", 102);
        lineasResultadoA.add(linea1);
        lineasResultadoA.add(linea2);
        lineasResultadoA.add(linea3);
        lineasResultadoA.add(linea4);
        lineasResultadoA.add(linea5);

        // Se comprueba que los valores esperados y obtenidos coinciden
        for(int i = 0; i < lineas.size(); i++){
            assertTrue(lineas.get(i).equals(lineasResultadoA.get(i)));
        }

    }

    /**
     * Test para comprobar el correcto funcionamiento del listado de líneas
     * Caso: JSON proporcionado vacío
     * @throws Exception excepcion
     */
    @Test
    public void PU2US241953() throws Exception {
        LoadDataAsync loadDataAsync = new LoadDataAsync(null, null);
        List<Linea> lineas = loadDataAsync.getListaLineasBus();
        // Comprobación de que no se devuelven líneas
        assertTrue(lineas == null);
    }

}
