package es.unican.g3.tus.model;

import java.util.List;

/**
 * Clase que almacena la información referente a un grupo
 * Created by fernando on 20/11/17.
 */

public class Grupo {

    private int id;
    private String nombre;
    private String color;

    public Grupo(int id, String nombre, String color){
        this.id = id;
        this.nombre = nombre;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static Grupo buscaGrupo(List<Grupo> listaGrupos, int id){
        for(Grupo grupo : listaGrupos){
            if(grupo.getId() == id)
                return grupo;
        }
        return null;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

}
