package es.unican.g3.tus.views;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Grupo;
import es.unican.g3.tus.presenter.DialogoEligeColorPresenter;

public class DialogoEligeColorFragment extends DialogFragment implements OnItemClickListener, IListEligeColorView {
    ListView listaGruposView;
    int paradaId;
    private DialogoEligeColorPresenter dialogoEligeColorPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, null, false);
        listaGruposView =  view.findViewById(R.id.list);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.dialogoEligeColorPresenter = new DialogoEligeColorPresenter(getContext(), this);
    }

    @Override
    public void showList(List<Grupo> gruposList) {
        ArrayAdapter<Grupo> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, gruposList);
        listaGruposView.setAdapter(adapter);
        listaGruposView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.dialogoEligeColorPresenter.guardaParadaColor(this.paradaId, this.dialogoEligeColorPresenter.getListaGrupos().get(position).getId());
        dismiss();
    }

    public void setParadaId(int paradaId) {
        this.paradaId = paradaId;
    }

}