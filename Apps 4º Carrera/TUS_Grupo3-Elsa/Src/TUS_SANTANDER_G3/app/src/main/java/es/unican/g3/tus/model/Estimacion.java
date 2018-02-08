package es.unican.g3.tus.model;


/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */

public class Estimacion{

    private int distancia;
    private int tiempo;
    private int identifier;
    private int paradaId;
    private String linea;

    public Estimacion(int distancia, int tiempo, int identifier,int paradaId,String linea){
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.identifier = identifier;
        this.paradaId=paradaId;
        this.linea=linea;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getParadaId() {
        return paradaId;
    }

    public void setParadaId(int paradaId) {
        this.paradaId = paradaId;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }


}
