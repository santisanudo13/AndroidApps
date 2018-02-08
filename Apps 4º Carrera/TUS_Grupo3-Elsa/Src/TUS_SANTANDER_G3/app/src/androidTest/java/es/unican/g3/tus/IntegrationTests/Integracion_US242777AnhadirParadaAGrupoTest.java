package es.unican.g3.tus;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.model.Grupo;
import es.unican.g3.tus.model.GrupoParada;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListGruposPresenter;
import es.unican.g3.tus.presenter.LoadDataAsync;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by LuisOsle on 26/11/17.
 */

public class Integracion_US242777AnhadirParadaAGrupoTest {

    @Test
    public void PI1US242777() throws Exception {

        InputStream is;
        LoadDataAsync loadDataAsync;

        // Paradas almacenadas en el JSON local
        is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_bus_completo);
        loadDataAsync = new LoadDataAsync(null, null);
        loadDataAsync.obtenParadas(is);
        List<Parada> paradas = loadDataAsync.getListaParadasBus();

        // Paradas sin ordenar que deberían obtenerse
        List<Parada> paradasResultado = new ArrayList<Parada>();
        Parada parada1 = new Parada(1,"Abilio Garcia baron 1 ( hote4l expres)", "296", 90);
        Parada parada2 = new Parada(2,"Adarzo", "455", 26416);
        Parada parada3 = new Parada(3,"Adarzo", "235", 54);
        Parada parada4 = new Parada(4,"Albert Einstein.14", "489", 41656);
        Parada parada5 = new Parada(5,"Alcalde vega lamera 1", "171", 301);
        Parada parada6 = new Parada(6,"Alto de la Peña", "83", 232);
        paradasResultado.add(parada1);
        paradasResultado.add(parada2);
        paradasResultado.add(parada3);
        paradasResultado.add(parada4);
        paradasResultado.add(parada5);
        paradasResultado.add(parada6);

        // Paradas: se comprueba que los valores esperados y obtenidos coinciden
        Collections.sort(paradas);
        for(int i = 0; i < 4; i++) {
            assertTrue(paradas.get(i).equals(paradasResultado.get(i)));
        }

        // Se crean los grupos y la lista
        List<Grupo> grupos = new ArrayList<Grupo>();
        Grupo aguamarina =new Grupo(1,"Aguamarina","#00C2C2");
        Grupo granate =new Grupo(2,"Granate","#7C0899");
        Grupo amarillo =new Grupo(3,"Amarillo","#FFC501");

        grupos.add(aguamarina);
        grupos.add(granate);
        grupos.add(amarillo);

        // Se realiza la asignación de paradas a grupos
        List<GrupoParada> grupoParadas = new ArrayList<GrupoParada>();
        GrupoParada gp1=new GrupoParada(aguamarina,paradas.get(0));
        GrupoParada gp2=new GrupoParada(aguamarina,paradas.get(1));
        GrupoParada gp3=new GrupoParada(aguamarina,paradas.get(2));
        GrupoParada gp4=new GrupoParada(granate,paradas.get(3));
        GrupoParada gp5=new GrupoParada(amarillo,paradas.get(4));
        GrupoParada gp6=new GrupoParada(amarillo,paradas.get(5));

        grupoParadas.add(gp1);
        grupoParadas.add(gp2);
        grupoParadas.add(gp3);
        grupoParadas.add(gp4);
        grupoParadas.add(gp5);
        grupoParadas.add(gp6);

        // Se comprueba la asignación
        assertTrue(gp1.getGrupo()==aguamarina && gp1.getParada().getName().equals("Abilio Garcia baron 1 ( hote4l expres)"));
        assertTrue(gp2.getGrupo()==aguamarina && gp2.getParada().getName().equals("Adarzo"));
        assertTrue(gp3.getGrupo()==aguamarina && gp3.getParada().getName().equals("Adarzo"));
        assertTrue(gp4.getGrupo()==granate && gp4.getParada().getName().equals("Albert Einstein.14"));
        assertTrue(gp5.getGrupo()==amarillo && gp5.getParada().getName().equals("Alcalde vega lamera 1"));
        assertTrue(gp6.getGrupo()==amarillo && gp6.getParada().getName().equals("Alto de la Peña"));

        // Se comprueba que se obtiene el listado esperado (aguamarina)
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(0).equals(aguamarina));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(1).equals(gp1.getParada()));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(2).equals(gp2.getParada()));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(3).equals(gp3.getParada()));

        // Se comprueba que se obtiene el listado esperado (granate)
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(4).equals(granate));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(5).equals(gp4.getParada()));

        // Se comprueba que se obtiene el listado esperado (amarillo)
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(6).equals(amarillo));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(7).equals(gp5.getParada()));
        assertTrue((ListGruposPresenter.getGruposConParadas(grupos,grupoParadas)).get(8).equals(gp6.getParada()));

    }
}
