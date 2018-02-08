package es.unican.g3.tus.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;


import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Database;
import es.unican.g3.tus.model.Grupo;
import es.unican.g3.tus.model.GrupoParada;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.views.GruposFragment;
import es.unican.g3.tus.views.IListGruposView;

public class ListGruposPresenter  {
    private IListGruposView listaGruposView;
    private List<Object> listadoGruposConParadas;

    private List<Grupo> listaGrupos;
    private List<Parada> listaParadas;
    private Context context;

    private Database db;

    public ListGruposPresenter(Context context, GruposFragment listGruposView){
        this.context = context;
        this.listaGruposView = listGruposView;
        this.db = new Database(context);

        this.listaParadas = db.recuperarParadas();
        this.listaGrupos = db.recuperarGrupos();
        this.listadoGruposConParadas = getGruposConParadas(this.listaGrupos, db.recuperarParadasGrupo(this.listaParadas, this.listaGrupos));
        listaGruposView.showList(this.listadoGruposConParadas);
    }// ListGruposPresenter

    public static List<Object> getGruposConParadas(List<Grupo> grupos, List<GrupoParada> grupoParadaList){
        ArrayList<Object> listadoGruposConParadas = new ArrayList<>();
        for(Grupo grupo : grupos){
            List<Parada> paradas = GrupoParada.getParadasDeGrupo(grupoParadaList, grupo.getId());
            if(!paradas.isEmpty()){
                listadoGruposConParadas.add(grupo);
                for(Parada parada : paradas){
                    listadoGruposConParadas.add(parada);
                }
            }
        }
        return listadoGruposConParadas;
    }

    public List<Object> getListaGruposConParadas() {
        return listadoGruposConParadas;
    }//getListaGrupos

    public IListGruposView getlistGruposView(){
        return listaGruposView;
    }

    public Context getContext(){
        return context;
    }

    public void eliminaParadaColor(int position) {
        final int posicion = position;
        final Parada parada = (Parada) listadoGruposConParadas.get(posicion);
        // Personalización diálogo de confirmación
        String alertaTexto = getContext().getResources().getString(R.string.window_delete_text);
        alertaTexto = alertaTexto.replace("%%TITULO%%", parada.getName());
        // Creación y lanzamiento del diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(alertaTexto)
                .setPositiveButton(R.string.window_delete_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.eliminaParadaDeGrupo(parada.getDbId(), parada.getGrupoAsignado());
                        getlistGruposView().refreshView();
                    }
                })
                .setNegativeButton(R.string.window_cancel, null);

        builder.create().show();
    }
}// ListGruposPresenter
