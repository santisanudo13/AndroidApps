package es.unican.g3.tus.views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.EstimacionesAsync;
import es.unican.g3.tus.presenter.ListGruposPresenter;


/**
 * A fragment representing a list of Items.
 */
public class GruposFragment extends ListFragment implements IListGruposView {

    private ListGruposPresenter listGruposPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return(inflater.inflate(R.layout.fragment_paradas_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listGruposPresenter = new ListGruposPresenter(getContext(),this);
        setHasOptionsMenu(false);

        // Detectar y actuar ante un click largo
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listGruposPresenter.eliminaParadaColor(position);

                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+Integer.toString(position));
        //Haciendo uso de la interfaz DataCommunzication podemos enviar los datos entre fragmentos
        new EstimacionesAsync( getActivity(), getContext(), (Parada)listGruposPresenter.getListaGruposConParadas().get(position), getFragmentManager()).execute();
    }

    @Override
    public void showList(List<Object> listadoGruposConParadas) {
        if(listadoGruposConParadas != null) {
            ListGruposAdapter listGruposAdapter = new ListGruposAdapter(getContext(), listadoGruposConParadas);
            getListView().setAdapter(listGruposAdapter);
        }
    }

    public void refreshView() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutElements, new GruposFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }
}
