package es.unican.g3.tus.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.EstimacionesAsync;
import es.unican.g3.tus.presenter.ListParadasPresenter;

/**
 * A fragment representing a list of Items.
 */
public class ParadasFragment extends ListFragment implements IListParadasView{
    private List<Parada> listAux=new ArrayList<>();
    private ProgressDialog dialog;
    private ListParadasPresenter listParadasPresenter;
    private List<Parada> fragmentListParadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_paradas_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listParadasPresenter = new ListParadasPresenter(getContext(),this);
        this.dialog = new ProgressDialog(getContext());
        // Habilitar opciones menú superior
        setHasOptionsMenu(true);

        // Detectar y actuar ante un click largo
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogoEligeColorFragment dialogo = new DialogoEligeColorFragment();
                dialogo.setParadaId(fragmentListParadas.get(position).getDbId());
                dialogo.show(getFragmentManager(), "dialogo");
                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+Integer.toString(position));
        new EstimacionesAsync( getActivity(), getContext(), listAux.get(position), getFragmentManager()).execute();
    }

    @Override
    public void showList(List<Parada> lineaList, boolean ordenadas) {
        if(lineaList!=null) {
            listAux=lineaList;
            ListParadasAdapter listParadasAdapter;
            if(ordenadas) {
                List<Parada> lineaListOrdenada = new ArrayList<>(lineaList);
                Collections.sort(lineaListOrdenada);
                listParadasAdapter = new ListParadasAdapter(getContext(), lineaListOrdenada);
                getListView().setAdapter(listParadasAdapter);
                this.fragmentListParadas = lineaListOrdenada;
            }else{

                listParadasAdapter = new ListParadasAdapter(getContext(), lineaList);
                getListView().setAdapter(listParadasAdapter);
                this.fragmentListParadas = lineaList;
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

    /**
     * Este método se encarga de devolver un listado con todas las paradas que tienen alguna
     * coincidencia en alguno de sus campos con el texto indicado
     *
     * @param filterText texto con el que filtrar los resultados
     * @return
     */
    public static List<Parada> searchFilterList (List<Parada> paradas, String filterText) {

        // Almacén de paradas coincidentes con el término de búsqueda
        List<Parada> paradaFiltradas = new ArrayList<>();
        String filterTextLowercase = filterText.toLowerCase();

        // Se recorren las paradas, almacenando aquellas que muestran coincidencias
        for(int i = 0; i < paradas.size(); i++)
        {
            if(paradas.get(i).getName().toLowerCase().indexOf(filterTextLowercase)!=-1 ||
                    paradas.get(i).getAlias().toLowerCase().indexOf(filterTextLowercase)!=-1 ||
                    paradas.get(i).getNumero().toLowerCase().indexOf(filterTextLowercase)!=-1 ||
                    paradas.get(i).getNotas().toLowerCase().indexOf(filterTextLowercase)!=-1)
            {
                paradaFiltradas.add(paradas.get(i));
            }
        }

        return paradaFiltradas;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu,menu);

        // Interfaz gráfica búsqueda
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.search_placeholder));

        // Atención de las consultas de búsqueda
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            private List<Parada> paradas = listParadasPresenter.getListaParadasBus();

            public boolean onQueryTextChange(String filterText) {
                showList(searchFilterList(paradas, filterText), false);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        boolean resultado = false;

        if(item.getItemId() == R.id.action_search) {
            resultado = true;
        } else if(item.getItemId() == R.id.action_ordenar_alfa){
            List<Parada> paradaList = listParadasPresenter.getListaParadasBus();
            showList(paradaList, true);
            resultado = true;
        } else if(item.getItemId() == R.id.action_return_original){
            List<Parada> paradaList = listParadasPresenter.getListaParadasBus();
            showList(paradaList, false);
            resultado = true;
        }

        return resultado;

    }
}
