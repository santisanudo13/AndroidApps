package es.unican.g3.tus.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.unican.g3.tus.R;
import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Parada;



public class ListEstimacionesAdapter extends ArrayAdapter {
    private List<Estimacion> estimacionesBus;
    private Context context;

    public ListEstimacionesAdapter(Context context, List<Estimacion> estimacionesBus){
        super(context, R.layout.custom_list_estimaciones_layout,estimacionesBus);
        this.context = context;
        this.estimacionesBus = estimacionesBus;
    }// ListparadasAdapter




    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_estimaciones_layout,null,true);
        View viewRow1 = layoutInflater.inflate(R.layout.fragment_estimaciones_list,null,true);
        TextView textViewTitle = viewRow1.findViewById(R.id.textViewTitle);
        TextView textViewDistancia = viewRow.findViewById(R.id.textViewDistancia);
        textViewTitle.setText("Estimaciones");
        TextView textViewTiempo =  viewRow.findViewById(R.id.textViewTiempo);
        TextView textViewNumero = viewRow.findViewById(R.id.textViewLineaa);
        textViewNumero.setText(estimacionesBus.get(position).getLinea());
        textViewDistancia.setText(String.valueOf("Distancia: "+(estimacionesBus.get(position).getTiempo()))+" m");
        long tiempoRedondeo = Math.round(estimacionesBus.get(position).getDistancia()/60.0);
        String tiempoTexto;
        if(tiempoRedondeo == 0){
            tiempoTexto = "Menos de un minuto";
        }else{
            tiempoTexto = Long.toString(tiempoRedondeo) + " min";
        }
        textViewTiempo.setText(String.valueOf("Tiempo: "+ tiempoTexto));
        int color;
        switch (estimacionesBus.get(position).getLinea())
        {
            case "1":
                color = Color.parseColor("#ff0000");
                break;
            case "2":
                color = Color.parseColor("#a800d0");
                break;
            case "3":
                color = Color.parseColor("#ffcd00");
                break;
            case "4":
                color = Color.parseColor("#25b3d8");
                break;
            case "5C1":
            case "5C2":
                color = Color.parseColor("#969696");
                break;
            case "6C1":
            case "6C2":
                color = Color.parseColor("#008032");
                break;
            case "7C1":
            case "7C2":
                color = Color.parseColor("#f66210");
                break;
            case "11":
                color = Color.parseColor("#01125c");
                break;
            case "12":
                color = Color.parseColor("#a2d65c");
                break;
            case "13":
                color = Color.parseColor("#9080b0");
                break;
            case "14":
                color = Color.parseColor("#0066b0");
                break;
            case "16":
                color = Color.parseColor("#631637");
                break;
            case "17":
                color = Color.parseColor("#f98083");
                break;
            case "19":
                color = Color.parseColor("#038495");
                break;
            case "18":
                color = Color.parseColor("#b0e8df");
                break;
            case "20":
                color = Color.parseColor("#78fa9e");
                break;
            case "21":
                color = Color.parseColor("#a1d55d");
                break;
            case "23":
                color = Color.parseColor("#cacaca");
                break;
            case "N1":
            case "N2":
            case "N3":
            default:
                color = Color.parseColor("#000000");
                break;
        }

        textViewNumero.setTextColor(Color.parseColor("#FFFFFF"));
        GradientDrawable drawable = (GradientDrawable) textViewNumero.getBackground();
        drawable.setColor(color);

        return viewRow;
    }

}// ListParadasAdapter
