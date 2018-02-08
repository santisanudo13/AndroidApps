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
import es.unican.g3.tus.model.Linea;

/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListLineasAdapter extends ArrayAdapter {
    private List<Linea> lineasBus;
    private Context context;

    public ListLineasAdapter(Context context, List<Linea> lineasBus){
        super(context, R.layout.custom_list_lineas_layout,lineasBus);
        this.context = context;
        this.lineasBus = lineasBus;
    }// ListparadasAdapter



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewNumero =  viewRow.findViewById(R.id.textViewNumero);
        TextView textViewName = viewRow.findViewById(R.id.textViewName);
        int color;
        switch (lineasBus.get(position).getNumero())
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
        textViewNumero.setText(lineasBus.get(position).getNumero().trim());
        textViewName.setText(lineasBus.get(position).getName().trim());

        return viewRow;
    }

}// ListParadasAdapter
