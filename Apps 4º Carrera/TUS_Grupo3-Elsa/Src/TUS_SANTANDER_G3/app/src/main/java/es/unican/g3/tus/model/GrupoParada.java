package es.unican.g3.tus.model;

import java.util.ArrayList;
import java.util.List;

/** Clase que representa la relación de un grupo con una parada y viceversa, así como operaciones
 * relativas a esta unión
 * Created by fernando on 23/11/17.
 */

public class GrupoParada {

    private Grupo grupo;
    private Parada parada;

    public GrupoParada(Grupo grupo, Parada parada){
        this.grupo = grupo;
        // Se debe crear un *nuevo* objeto de parada, de cara a grupos son objetos distintos cada uno con su grupo asignado
        this.parada = new Parada(parada.getDbId(), parada.getName(), parada.getNumero(), parada.getIdentifier());
        this.parada.setGrupoAsignado(grupo.getId());
    }

    public Grupo getGrupo(){
        return this.grupo;
    }

    public Parada getParada(){
        return this.parada;
    }

    public static List<Parada> getParadasDeGrupo(List<GrupoParada> grupoParadaLista, int grupo){

        List<Parada> paradas = new ArrayList<>();

        for(GrupoParada grupoParada : grupoParadaLista){
            if(grupoParada.getGrupo().getId() == grupo){
                paradas.add(grupoParada.getParada());
            }
        }

        return paradas;
    }
}
