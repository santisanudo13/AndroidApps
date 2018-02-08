package es.unican.g3.tus.presenter;

import android.content.Context;

import java.util.List;


import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.views.IListParadasView;
import es.unican.g3.tus.views.ParadasFragment;

public class ListParadasPresenter  {
    private IListParadasView listParadasView;
    private List<Parada> listaParadasBus;
    private Context context;

    public ListParadasPresenter(Context context, ParadasFragment listParadasView){
        this.context = context;
        this.listParadasView = listParadasView;
        Database db = new Database(context);
        this.listaParadasBus = db.recuperarParadas();
        listParadasView.showList(getListaParadasBus(), false);
    }// ListParadasPresenter

    public List<Parada> getListaParadasBus() {
        return listaParadasBus;
    }//getListaParadasBus

    public IListParadasView getListParadasView(){
        return listParadasView;
    }

    public Context getContext(){
        return context;
    }
}// ListParadasPresenter
