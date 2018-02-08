package es.unican.g3.tus;


import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import es.unican.g3.tus.model.Parada;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class Unitarias_US242062OrdenarParadasAlfabeticamenteTest {

    /**
     * Test para comprobar la que el sistema de ordenación de paradas, funciona correctamente.
     * @throws Exception excepcion
     */

    @Test
    public void PU1US242062() throws Exception {

        List<Parada> lista = new LinkedList<>();
        Parada p1= new Parada(1,"Camarreal Penacastillo","499", 42063);
        Parada p2= new Parada(2,"Ortega y Gasset.28","500", 42064);
        Parada p3= new Parada(3,"Avenida de Cantabria.35","505", 50693);
        Parada p4= new Parada(4,"Nuevo Parque","307", 100);

        lista.add(p4);
        lista.add(p2);
        lista.add(p3);
        lista.add(p1);

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

    }// tesParadas

    @Test
    public void PU2US242062() throws Exception {

        List<Parada> lista = new LinkedList<>();
        Parada p1= new Parada(1,"Camarreal Peñacastillo","499", 42063);
        Parada p2= new Parada(2,"Ortega y Gasset.28","500", 42064);
        Parada p3= new Parada(3,"Avenida de Cantabria.35","505", 50693);
        Parada p4= new Parada(4,"Nuevo Parque","307", 100);

        //Si le paso una lista vacia
        assertTrue(lista.size()==0);

        lista.add(p1);
        lista.add(p4);
        lista.add(p2);
        lista.add(p3);
        //Lo ordeno

        Collections.sort(lista);

        assertTrue(lista.get(0).equals(p3));
    }
}

