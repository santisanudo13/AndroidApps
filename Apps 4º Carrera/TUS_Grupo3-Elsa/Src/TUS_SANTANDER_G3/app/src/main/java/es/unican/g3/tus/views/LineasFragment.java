package es.unican.g3.tus.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.presenter.ListLineasPresenter;

/**
 * A fragment representing a list of Items.
 */
public class LineasFragment extends ListFragment implements IListLineasView{

    private ProgressDialog dialog;
    private ListLineasPresenter listLineasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_lineas_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listLineasPresenter = new ListLineasPresenter(getContext(),this);
        this.dialog = new ProgressDialog(getContext());
        setHasOptionsMenu(false);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+Integer.toString(position));
        //Haciendo uso de la interfaz DataCommunzication podemos enviar los datos entre fragmentos
    }

    @Override
    public void showList(List<Linea> lineaList, boolean ordenadas) {
        if(lineaList!=null) {
            ListLineasAdapter listLineasAdapter;
            if(ordenadas) {
                Collections.sort(lineaList);
                listLineasAdapter = new ListLineasAdapter(getContext(), lineaList);
                getListView().setAdapter(listLineasAdapter);
            }else{
                listLineasAdapter = new ListLineasAdapter(getContext(), lineaList);
                getListView().setAdapter(listLineasAdapter);
            }
        }
    }


    /**
     * Este método cuando es llamado se encarga de mostrar un progressDialog
     * @param state si es true pone el progressDialog en la interfaz, si es false lo cancela
     */
    @Override
    public void showProgress (boolean state){
        if(state){
            dialog.setMessage(getContext().getString(R.string.app_carga_datos_enproceso));
            dialog.show();
        }else{
            dialog.dismiss();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        List<Linea> lineaList=listLineasPresenter.getListaLineasBus();
        showList(lineaList, true);
        return true;
    }
}
