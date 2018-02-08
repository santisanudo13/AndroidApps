package es.unican.g3.tus;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.views.ParadasFragment;

import static org.junit.Assert.assertTrue;

/** Pruebas unitarias: Buscar parada por texto
 * Clase que implementa los tests unitarios del caso de uso "Buscar parada por texto"
 */
public class Unitarias_US241948BuscarParadaPorTextoTest {

    /**
     * Test para comprobar el filtrado de paradas en base a una cadena de caracteres
     * Caso: Cadena de caracteres proporcionada
     * @throws Exception excepcion
     */
    @Test
    public void PU1US241948() throws Exception {
        // Paradas que deberían leerse y procesarse en el método searchFilterList
        List<Parada> paradasEntrada = new ArrayList<Parada>();
        Parada parada1 = new Parada(1,"Avenida de Cantabria 10", "213", 322);
        Parada parada2 = new Parada(2,"Avenida Cantabria 12", "221", 323);
        parada2.setAlias("Mi casita");
        Parada parada3 = new Parada(3,"Los Castros 63", "73", 45);
        parada3.setNotas("Domino");
        paradasEntrada.add(parada1);
        paradasEntrada.add(parada2);
        paradasEntrada.add(parada3);

        // Primer test
        List<Parada> paradasResultadoA = new ArrayList<Parada>();
        paradasResultadoA.add(parada1);
        paradasResultadoA.add(parada2);

        List<Parada> ejecucionResultadoA = ParadasFragment.searchFilterList(paradasEntrada, "Avenida");
        assertTrue(ejecucionResultadoA.get(0).equals(paradasResultadoA.get(0)));
        assertTrue(ejecucionResultadoA.get(1).equals(paradasResultadoA.get(1)));

        // Segundo test
        List<Parada> paradasResultadoB = new ArrayList<Parada>();
        paradasResultadoB.add(parada2);

        List<Parada> ejecucionResultadoB = ParadasFragment.searchFilterList(paradasEntrada, "Mi casita");
        assertTrue(ejecucionResultadoB.get(0).equals(paradasResultadoB.get(0)));

        // Tercer test
        List<Parada> paradasResultadoC = new ArrayList<Parada>();
        paradasResultadoC.add(parada3);

        List<Parada> ejecucionResultadoC = ParadasFragment.searchFilterList(paradasEntrada, "Domino");
        assertTrue(ejecucionResultadoC.get(0).equals(paradasResultadoC.get(0)));
    }

    /**
     * Test para comprobar el filtrado de paradas en base a una cadena de caracteres
     * Caso: Cadena de caracteres nula
     * @throws Exception excepcion
     */
    @Test
    public void PU2US241948() throws Exception {
        // Paradas que deberían leerse y procesarse en el método searchFilterList
        List<Parada> paradasEntrada = new ArrayList<Parada>();

        // Primer test
        List<Parada> ejecucionResultadoA = ParadasFragment.searchFilterList(paradasEntrada, "");
        assertTrue(ejecucionResultadoA.size() == 0);
    }
}
