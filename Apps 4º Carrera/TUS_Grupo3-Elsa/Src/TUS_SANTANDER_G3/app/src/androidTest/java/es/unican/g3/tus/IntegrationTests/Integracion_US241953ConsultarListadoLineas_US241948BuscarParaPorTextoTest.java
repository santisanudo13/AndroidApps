package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.LoadDataAsync;

import static org.junit.Assert.assertTrue;

/** Pruebas integración: Sprint 2
 * Clase que implementa los tests de integración del segundo sprint
 */

public class Integracion_US241953ConsultarListadoLineas_US241948BuscarParaPorTextoTest {

    /**
     * Test que implementa la prueba de integración PI1 utiliando un JSON local completo
     * con toda la información
     * @throws Exception excepcion
     */
    @Test
    public void PI1US241953_US241948() throws Exception {

        InputStream is;
        LoadDataAsync loadDataAsync;

        // Paradas almacenadas en el JSON local
        is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus_completo);
        loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenParadas(is);
        List<Parada> paradas = loadDataAsync.getListaParadasBus();

        // Lineas almacenadas en el JSON local
        is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_bus_completo);
        loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenLineas(is);
        List<Linea> lineas = loadDataAsync.getListaLineasBus();

        // Paradas sin ordenar que deberían obtenerse
        List<Parada> paradasResultadoA = new ArrayList<Parada>();
        Parada parada1 = new Parada(1,"Avenida de Cantabria nº 76", "506", 50694);
        Parada parada2 = new Parada(2,"Ortega y Gasset.28", "500", 42064);
        Parada parada3 = new Parada(3,"Avenida de Cantabria nº 35", "505", 50693);
        Parada parada4 = new Parada(4,"Eusebio Santamaria.2", "495", 42038);
        paradasResultadoA.add(parada1);
        paradasResultadoA.add(parada2);
        paradasResultadoA.add(parada3);
        paradasResultadoA.add(parada4);

        // Paradas: se comprueba que los valores esperados y obtenidos coinciden
        for(int i = 0; i < 4; i++){
            assertTrue(paradas.get(i).equals(paradasResultadoA.get(i)));
        }

        // Líneas sin ordenar que deberían obtenerse
        List<Linea> lineasResultadoA = new ArrayList<Linea>();
        Linea linea1 = new Linea("PUERTOCHICO-MONTE", "18", 18);
        Linea linea2 = new Linea("CIRIEGO-CORBAN-ESTACIONES", "17", 17);
        Linea linea3 = new Linea("PLAZA DE LOS REMEDIOS", "16", 16);
        Linea linea4 = new Linea("ESTACIONES-EL FARO", "15", 15);
        lineasResultadoA.add(linea1);
        lineasResultadoA.add(linea2);
        lineasResultadoA.add(linea3);
        lineasResultadoA.add(linea4);

        // Líneas: Se comprueba que los valores esperados y obtenidos coinciden
        for(int i = 0; i < 4; i++){
            assertTrue(lineas.get(i).equals(lineasResultadoA.get(i)));
        }

        // Paradas ordenadas que deberían obtenerse en la prueba de ordenación
        List<Parada> paradasResultadoB = new ArrayList<Parada>();
        Parada parada5 = new Parada(5,"Abilio Garcia baron 1 ( hote4l expres)", "296", 90);
        Parada parada6 = new Parada(6,"Adarzo", "455", 26416);
        Parada parada7 = new Parada(7,"Adarzo", "235", 54);
        Parada parada8 = new Parada(8,"Albert Einstein.14", "489", 41656);
        paradasResultadoB.add(parada5);
        paradasResultadoB.add(parada6);
        paradasResultadoB.add(parada7);
        paradasResultadoB.add(parada8);

        // Paradas: se comprueba que los valores esperados y obtenidos coinciden
        Collections.sort(paradas);
        for(int i = 0; i < 4; i++){
            assertTrue(paradas.get(i).equals(paradasResultadoB.get(i)));
        }

        // Líneas ordenadas que deberían obtenerse en la prueba de ordenación
        List<Linea> lineasResultadoB = new ArrayList<Linea>();
        Linea linea5 = new Linea("PCTCAN-VALDENOJA", "1", 1);
        Linea linea6 = new Linea("CORBAN-CONSUELO BERGES", "2", 2);
        Linea linea7 = new Linea("OJAIZ-PIQUIO", "3", 3);
        Linea linea8 = new Linea("BARRIO PESQUERO-PIQUIO", "4", 4);
        lineasResultadoB.add(linea5);
        lineasResultadoB.add(linea6);
        lineasResultadoB.add(linea7);
        lineasResultadoB.add(linea8);

        // Líneas: Se comprueba que los valores esperados y obtenidos coinciden
        Collections.sort(lineas);
        for(int i = 0; i < 4; i++){
            assertTrue(lineas.get(i).equals(lineasResultadoB.get(i)));
        }

    }

}