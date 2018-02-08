package es.unican.g3.tus.views;
import java.util.List;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;


import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Parada;
import es.unican.g3.tus.presenter.ListEstimacionesPresenter;


/**
 * A fragment representing a list of Items.
 */
public class EstimacionesFragment extends ListFragment implements IListEstimacionesView{

    private ProgressDialog dialog;
    private ListEstimacionesPresenter listEstimacionesPresenter;
    private String paradaId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return(inflater.inflate(R.layout.fragment_estimaciones_list, container, false));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listEstimacionesPresenter = new ListEstimacionesPresenter(getContext(),this);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+Integer.toString(position));
        //Haciendo uso de la interfaz DataCommunzication podemos enviar los datos entre fragmentos

    }

    @Override
    public void showList(List<Estimacion> lineaList, boolean ordenadas) {
        if(lineaList!=null) {

            ListEstimacionesAdapter lista= new ListEstimacionesAdapter(getContext(), estimacionesPorParada(lineaList,paradaId));
            getListView().setAdapter(lista);

        }
        else{



            dialog.dismiss();
        }
    }
    public List<Estimacion> estimacionesPorParada(List<Estimacion> lineaList,String id)
    {
        List<Estimacion> listAux= new ArrayList<>();
        for(int i=0;i<lineaList.size();i++)
        {
            if(lineaList.get(i).getParadaId()==Integer.parseInt(id))
            {
                listAux.add(lineaList.get(i));
            }
        }
        //Aqui antes se lanzaba un toast que ahora es inutil (se ha eliminado)
        //(Estimaciones no disponibles)
        return listAux;
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
     * @return lista de paradas
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
    public boolean onOptionsItemSelected(MenuItem item){

        boolean resultado= false;

        if(item.getItemId()==R.id.action_search) {
            resultado= true;
        } else {
            List<Estimacion> estimacionList = listEstimacionesPresenter.getListaEstimacionesBus();
            showList(estimacionList, true);
            resultado= true;
        }

        return resultado;

    }
    public void anhadeParada(String id)
    {
        paradaId=id;
    }
}
