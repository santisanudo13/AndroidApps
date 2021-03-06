package es.unican.g3.tus.views;


/**
 * Interfaz que implementará la activitidad MainActivity para tener métodos que nos permitan comunicar
 * los distintos fragments de la app
 */
public interface DataCommunication {

    public int getLineaIdentifier();
    public void setLineaIdentifier(int identifier);
    public int getParadaIdentifier();
    public void setParadaIdentifier(int paradaIdentifier);
}
