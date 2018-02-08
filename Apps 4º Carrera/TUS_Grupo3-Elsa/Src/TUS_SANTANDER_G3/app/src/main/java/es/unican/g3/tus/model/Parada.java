package es.unican.g3.tus.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */

public class Parada implements Comparable{

    private int dbId;
    private String name;
    private String alias="";
    private String notas="";
    private String numero;
    private int identifier;
    private int grupoAsignado = -1;

    public Parada(int dbId, String name, String numero, int identifier){
        this.dbId = dbId;
        this.name = name;
        this.numero = numero;
        this.identifier = identifier;
    }

    public int getDbId() {
        return dbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getAlias(){ return alias; }

    public void setAlias(String alias){ this.alias=alias;}

    public String getNotas(){ return notas;}

    public void setNotas(String notas){ this.notas=notas;}

    public int getGrupoAsignado(){ return grupoAsignado;}

    public void setGrupoAsignado(int grupoAsignado){ this.grupoAsignado=grupoAsignado;}

    @Override
    public int compareTo(@NonNull Object o) {

        Parada parada=(Parada)o;
        String nombreP1=this.getName();
        String nombreP2=parada.getName();

        //si es menor que 0, nombre p1 va antes que nombre p2
        //si mayor que 0, lo contrario
        //si es 0, son iguales
        return(nombreP1.compareTo(nombreP2));
    }

    public boolean equals(Object o) {
        boolean aRetornar;
        if(o==null || this.getClass()!=o.getClass()){
            aRetornar=false;
        }else {
            Parada parada = (Parada) o;
            aRetornar=this.identifier == parada.getIdentifier() && this.name.equals(parada.getName())
                    && this.numero.equals(parada.getNumero()) && this.grupoAsignado == parada.getGrupoAsignado();
        }
        return aRetornar;
    }

    public static Parada buscaParada(List<Parada> listaParadas, int id){
        for(Parada parada : listaParadas){
            if(parada.getDbId() == id)
                return parada;
        }
        return null;
    }

    public int hashCode(){
        return 5*getIdentifier()*getName().hashCode()*getNumero().hashCode();
    }
}
