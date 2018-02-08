package es.unican.g3.tus.views;

import java.util.List;

import es.unican.g3.tus.model.Linea;


/**
 * Interfaz IListLineasView
 */

public interface IListLineasView {
    void showList(List<Linea> lineaList, boolean ordenadas);
    void showProgress(boolean state);
}//IListLineasView
