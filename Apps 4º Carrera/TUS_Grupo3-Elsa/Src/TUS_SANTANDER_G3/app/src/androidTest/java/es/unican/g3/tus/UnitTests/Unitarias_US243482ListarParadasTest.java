package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListParadasPresenter;
import es.unican.g3.tus.presenter.LoadDataAsync;

import static org.junit.Assert.assertTrue;

/** Pruebas unitarias: Listar paradas
 * Clase que implementa los tests unitarios del caso de uso "Listar paradas"
 */

public class Unitarias_US243482ListarParadasTest {

    /**
     * Test para comprobar el parseo y procesamiento del JSON correspondiente a las paradas de TUS Santander
     * Caso: JSON proporcionado correcto
     * @throws Exception excepcion
     */
    @Test
    public void PU1US243482() throws Exception {

        // Paradas almacenadas en el JSON local
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus);
        LoadDataAsync loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenParadas(is);
        List<Parada> paradas = loadDataAsync.getListaParadasBus();

        // Paradas que deberían obtenerse
        List<Parada> paradasResultadoA = new ArrayList<Parada>();
        Parada parada1 = new Parada(1,"Camarreal Penacastillo", "499", 42063);
        Parada parada2 = new Parada(2,"Ortega y Gasset.28", "500", 42064);
        Parada parada3 = new Parada(3,"Avenida de Cantabria.35", "505", 50693);
        Parada parada4 = new Parada(4,"Nuevo Parque", "307", 100);
        paradasResultadoA.add(parada1);
        paradasResultadoA.add(parada2);
        paradasResultadoA.add(parada3);
        paradasResultadoA.add(parada4);

        // Se comprueba que los valores esperados y obtenidos coinciden
        for(int i = 0; i < paradas.size(); i++){
            assertTrue(paradas.get(i).equals(paradasResultadoA.get(i)));
        }

    }

    /**
     * Test para comprobar el parseo y procesamiento del JSON correspondiente a las paradas de TUS Santander
     * Caso: JSON proporcionado vacío
     * @throws Exception excepcion
     */
    @Test
    public void PI2US243482() throws Exception {
        LoadDataAsync loadDataAsync = new LoadDataAsync(null, null);
        List<Parada> paradas = loadDataAsync.getListaParadasBus();
        // Comprobación de que no se devuelven paradas
        assertTrue(paradas == null);
    }

}