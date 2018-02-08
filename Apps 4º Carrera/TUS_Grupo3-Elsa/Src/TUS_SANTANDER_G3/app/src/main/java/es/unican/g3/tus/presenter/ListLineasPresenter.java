package es.unican.g3.tus.presenter;

import android.content.Context;

import java.util.List;

import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.views.IListLineasView;
import es.unican.g3.tus.views.LineasFragment;
/**
 * Clase presenter
 */

public class ListLineasPresenter {
    private IListLineasView listLineasView;
    private List<Linea> listaLineasBus;


    public ListLineasPresenter(Context context, LineasFragment listLineasView){
        this.listLineasView = listLineasView;
        Database db = new Database(context);
        this.listaLineasBus = db.recuperarLineas();
        listLineasView.showList(listaLineasBus, true);
    }// ListParadasPresenter


    public IListLineasView getListLineasView(){
        return listLineasView;
    }

    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus
}
