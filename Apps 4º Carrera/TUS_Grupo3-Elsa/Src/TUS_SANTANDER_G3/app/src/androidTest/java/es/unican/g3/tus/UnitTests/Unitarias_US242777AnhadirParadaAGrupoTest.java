package es.unican.g3.tus;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.model.Grupo;
import es.unican.g3.tus.model.GrupoParada;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListGruposPresenter;

import static org.junit.Assert.assertTrue;

/**
 * Pruebas unitarias: anhadir parada a grupo
 * Clase que implementa las pruebas unitarias asociadas a la historia
 * de usuario "Anhadir parada a grupo"
 */
public class Unitarias_US242777AnhadirParadaAGrupoTest {

    @Test
    public void PU1US242777(){
        Parada parada1=new Parada(1,"Avenida de Cantabria 10", "213",322);
        Parada parada2=new Parada(2,"Avenida Cantabria 12", "221",323);
        Parada parada3=new Parada(3,"Los Castros 63", "73",45);

        Grupo gAguamarina = new Grupo(1,"Aguamarina", "#00C2C2");
        Grupo gAmarillo = new Grupo(2, "Amarillo", "#FFC501");
        Grupo gCian = new Grupo(3, "Cian", "#00A4EB");


        GrupoParada gp1=new GrupoParada(gAguamarina, parada1);
        assertTrue(gp1.getParada().getGrupoAsignado()==gAguamarina.getId());

        GrupoParada gp2=new GrupoParada(gAmarillo, parada2);
        assertTrue(gp2.getParada().getGrupoAsignado()==gAmarillo.getId());

        GrupoParada gp3=new GrupoParada(gCian, parada3);
        assertTrue(gp3.getParada().getGrupoAsignado()==gCian.getId());
    }

    @Test
    public void PU2US242777() {
        List<Grupo> grupos=new ArrayList<>();
        List<GrupoParada> gruposParadas=new ArrayList<>();

        Grupo gAguamarina = new Grupo(1,"Aguamarina", "#00C2C2");
        Grupo gAmarillo = new Grupo(2, "Amarillo", "#FFC501");
        Grupo gCian = new Grupo(3, "Cian", "#00A4EB");
        grupos.add(gAguamarina);
        grupos.add(gAmarillo);
        grupos.add(gCian);

        Parada parada1=new Parada(1,"Avenida de Cantabria 10", "213",322);
        Parada parada2=new Parada(2,"Avenida Cantabria 12", "221",323);
        Parada parada3=new Parada(3,"Los Castros 63", "73",45);

        GrupoParada gp1=new GrupoParada(gAguamarina, parada1);
        GrupoParada gp2=new GrupoParada(gAguamarina, parada2);
        GrupoParada gp3=new GrupoParada(gAmarillo, parada3);
        gruposParadas.add(gp1);
        gruposParadas.add(gp2);
        gruposParadas.add(gp3);


        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,gruposParadas)).get(0).equals(gAguamarina));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,gruposParadas)).get(1).equals(gp1.getParada()));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,gruposParadas)).get(2).equals(gp2.getParada()));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,gruposParadas)).get(3).equals(gAmarillo));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,gruposParadas)).get(4).equals(gp3.getParada()));
    }
}
