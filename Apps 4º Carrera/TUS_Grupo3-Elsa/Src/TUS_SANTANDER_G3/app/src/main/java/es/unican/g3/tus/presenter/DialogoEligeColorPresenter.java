package es.unican.g3.tus.presenter;

import android.content.Context;


import java.util.List;
import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Grupo;
import es.unican.g3.tus.views.DialogoEligeColorFragment;
import es.unican.g3.tus.views.IListEligeColorView;

public class DialogoEligeColorPresenter  {
    private IListEligeColorView listDialogoEligeColorView;
    private List<Grupo> listaGrupos;
    private Context context;
    private Database db;

    public DialogoEligeColorPresenter(Context context, DialogoEligeColorFragment listDialogoEligeColorView){
        this.context = context;
        this.db = new Database(context);
        this.listaGrupos = db.recuperarGrupos();
        listDialogoEligeColorView.showList(getListaGrupos());
    }// ListGruposPresenter

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }//getListaGrupos

    public IListEligeColorView getlistDialogoEligeColorView(){
        return listDialogoEligeColorView;
    }

    public Context getContext(){
        return context;
    }

    public void guardaParadaColor(int paradaId, int grupoId) {
        this.db.agregaParadaAColor(paradaId, grupoId);
    }

}// ListGruposPresenter
