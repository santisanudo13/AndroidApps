package es.unican.g3.tus.presenter;

import android.content.Context;
import java.util.List;

import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.views.EstimacionesFragment;
import es.unican.g3.tus.views.IListEstimacionesView;

public class ListEstimacionesPresenter  {
    private IListEstimacionesView listEstimacionesView;
    private List<Estimacion> listaEstimacionesBus;
    private Context context;
    private Database db;

    public ListEstimacionesPresenter(Context context, EstimacionesFragment listEstimacionesView){
        this.context = context;
        this.listEstimacionesView = listEstimacionesView;

        db = new Database(context);
        this.listaEstimacionesBus = db.recuperarEstimaciones();
        listEstimacionesView.showList(getListaEstimacionesBus(), false);

    }// ListEstimacionesPresenter

    public List<Estimacion> getListaEstimacionesBus() {
        return listaEstimacionesBus;
    }

    public IListEstimacionesView getListEstimacionesView(){
        return listEstimacionesView;
    }

    public Context getContext(){
        return context;
    }




}// ListParadasPresenter
