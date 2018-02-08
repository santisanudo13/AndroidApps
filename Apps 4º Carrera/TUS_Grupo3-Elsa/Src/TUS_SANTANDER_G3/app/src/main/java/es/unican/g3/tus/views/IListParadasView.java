package es.unican.g3.tus.views;

import java.util.List;
import es.unican.g3.tus.model.Parada;


/**
 * Interfaz IListParadasView
 */

public interface IListParadasView {
    void showList(List<Parada> paradaList, boolean ordenadas);
    void showProgress(boolean state);
}//IListLineasView
