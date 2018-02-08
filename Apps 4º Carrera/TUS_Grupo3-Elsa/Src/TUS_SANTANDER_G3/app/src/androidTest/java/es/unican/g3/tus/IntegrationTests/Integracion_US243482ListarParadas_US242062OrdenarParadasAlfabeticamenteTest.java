package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.model.dataloaders.ParserJSON;
import es.unican.g3.tus.model.dataloaders.RemoteFetch;

import static junit.framework.Assert.assertEquals;

public class Integracion_US243482ListarParadas_US242062OrdenarParadasAlfabeticamenteTest {

    /**
     * Test que implementa la prueba de integración PI1
     * @throws Exception excepcion
     */
    @Test
    public void PI1US243482() throws Exception {

        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus);

        List<Parada> lista = ParserJSON.readArrayParadasBus(is);

        //Lo ordeno
        Collections.sort(lista);

        //primera parada
        assertEquals("Avenida de Cantabria.35", lista.get(0).getName());
        assertEquals("505", lista.get(0).getNumero());
        assertEquals(50693,lista.get(0).getIdentifier());

        //Segunda parada
        assertEquals("Camarreal Penacastillo", lista.get(1).getName());
        assertEquals("499", lista.get(1).getNumero());
        assertEquals(42063,lista.get(1).getIdentifier());

        //Tercera parada
        assertEquals("Nuevo Parque", lista.get(2).getName());
        assertEquals("307", lista.get(2).getNumero());
        assertEquals(100,lista.get(2).getIdentifier());

        //Cuarta parada
        assertEquals("Ortega y Gasset.28", lista.get(3).getName());
        assertEquals("500", lista.get(3).getNumero());
        assertEquals(42064,lista.get(3).getIdentifier());



    }


    /**
     * Test que implementa la prueba de integración PI2
     * @throws Exception excepcion
     */
    @Test
    public void PI2US242062() throws Exception {

        RemoteFetch remoteFetch = new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_PARADAS_BUS);
        InputStream is = remoteFetch.getBufferedData();
        List<Parada> lista = ParserJSON.readArrayParadasBus(is);

        // Se ordena
        Collections.sort(lista);

        // Primera parada
        assertEquals("Abilio Garcia baron 1 ( hote4l expres)", lista.get(0).getName());
        assertEquals("296", lista.get(0).getNumero());
        assertEquals(90,lista.get(0).getIdentifier());

        // Segunda parada
        assertEquals("Adarzo", lista.get(1).getName());
        assertEquals("455", lista.get(1).getNumero());
        assertEquals(26416,lista.get(1).getIdentifier());

        // Tercera parada
        assertEquals("Adarzo", lista.get(2).getName());
        assertEquals("235", lista.get(2).getNumero());
        assertEquals(54,lista.get(2).getIdentifier());

        // Cuarta parada
        assertEquals("Albert Einstein.14", lista.get(3).getName());
        assertEquals("489", lista.get(3).getNumero());
        assertEquals(41656,lista.get(3).getIdentifier());

        // Quinta parada
        assertEquals("Alcalde vega lamera 1", lista.get(4).getName());
        assertEquals("171", lista.get(4).getNumero());
        assertEquals(301,lista.get(4).getIdentifier());

    }

}