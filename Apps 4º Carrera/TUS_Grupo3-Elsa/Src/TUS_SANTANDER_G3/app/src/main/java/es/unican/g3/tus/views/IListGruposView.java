package es.unican.g3.tus.views;

import java.util.List;
import es.unican.g3.tus.model.Parada;


/**
 * Interfaz IListParadasView
 */

public interface IListGruposView {
    void showList(List<Object> listadoGruposConParadas);
    void refreshView();
}//IListLineasView
