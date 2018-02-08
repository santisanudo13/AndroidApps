package es.unican.g3.tus.views;

import java.util.List;

import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Linea;


/**
 * Interfaz IListLineasView
 */

public interface IListEstimacionesView {
    void showList(List<Estimacion> lineaList, boolean ordenadas);
    void showProgress(boolean state);
}//IListLineasView
