package es.unican.g3.tus.model.dataloaders;

import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.model.Estimacion;
import es.unican.g3.tus.model.Linea;
import es.unican.g3.tus.model.Parada;


/**
 * Created by alejandro on 27/09/16.
 * Clase que contiene los metodos necesarios para parsear el JSON que devuelve el servicio "REST" con
 * los diferentes datos del TUS de Santander
 */
public class ParserJSON {

    public static final String DC_IDENTIFIER = "dc:identifier";
    public static final String AYTO_NUMERO = "ayto:numero";

    private ParserJSON() {
    }

    /**
     * Metodo para obtener todas las paradas de buses
     *
     * @param in InputStream del JSON con las paradas de buses
     * @return Lista con todas las paradas
     * @throws IOException excepcion de entrada/salida
     */
    public static List<Parada> readArrayParadasBus(InputStream in) throws IOException {
        return readArray(in, 0);
    }

    /**
     * Metodo para obtener todas las lineas de buses
     *
     * @param in InputStream del JSON con las lineas de buses
     * @return Lista con todas las lineas
     * @throws IOException excepcion de entrada/salida
     */
    public static List<Linea> readArrayLineasBus(InputStream in) throws IOException {
        return (readArray(in, 1));
    }

    /**
     * Metodo para obtener todas las estimaciones de buses
     *
     * @param in InputStream del JSON con las lineas de buses
     * @return Lista con todas las lineas
     * @throws IOException excepcion de entrada/salida
     */
    public static List<Estimacion> readArrayEstimacionesBus(InputStream in) throws IOException {
        return (readArray(in, 2));
    }


    public static List readArray(InputStream in, int tipo) throws IOException {
        //0 -> Paradas
        //1 -> Lineas
        List<Parada> listParadasBus = new ArrayList<>();
        List<Linea> listLineasBus = new ArrayList<>();
        List<Estimacion> listEstimacionesBus = new ArrayList<>();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext()) {
                    switch (tipo) {
                        case 0:
                            listParadasBus.add(readParada(reader));
                            break;
                        case 1:
                            listLineasBus.add(readLinea(reader));
                            break;
                        case 2:
                            listEstimacionesBus.add(readEstimacion(reader));
                            break;
                        default:
                            break;
                    }
                }
            } else {
                reader.skipValue();
            }
        }

        switch(tipo){
            case 0:
                return listParadasBus;
            case 1:
                return listLineasBus;
            default:
                return listEstimacionesBus;
        }
    }

    /**
     * Lee una Parada
     *
     * @param reader lector
     * @return parada a leer
     * @throws IOException excepcion de entrada/salida
     */
    private static Parada readParada(JsonReader reader) throws IOException {
        return ((Parada) read(reader, 1));
    }

    /**
     * Lee una Linea
     *
     * @param reader lector
     * @return parada a leer
     * @throws IOException excepcion de entrada/salida
     */
    private static Linea readLinea(JsonReader reader) throws IOException {
        return ((Linea) read(reader, 0));
    }

    private static Estimacion readEstimacion(JsonReader reader) throws IOException {
        return ((Estimacion) read(reader, 2));
    }

    private static Object read(JsonReader reader, int tipo) throws IOException {
        reader.beginObject(); //Leemos un object
        String name = "";
        String numero = "";
        int identifier = -1;
        int paradaId = 0;
        int distancia1 = 0;
        int tiempo1 = 0;
        String linea="";

        while (reader.hasNext()) {
            String n = reader.nextName();
            //linea
            if (tipo == 0) {
                switch (n) {
                    case AYTO_NUMERO:
                        numero = reader.nextString();
                        break;
                    case "dc:name":
                        name = reader.nextString();
                        break;
                    case DC_IDENTIFIER:
                        identifier = reader.nextInt();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            } else if (tipo == 1) {
                switch (n) {
                    case AYTO_NUMERO:
                        numero = reader.nextString();
                        break;
                    case "ayto:parada":
                        name = reader.nextString();
                        break;
                    case DC_IDENTIFIER:
                        identifier = reader.nextInt();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            } else if (tipo == 2) {
                switch (n) {
                    case "ayto:tiempo1":
                        tiempo1 = reader.nextInt();
                        break;
                    case "ayto:distancia1":
                        distancia1 = reader.nextInt();
                        break;
                    case "ayto:paradaId":
                        paradaId = reader.nextInt();
                        break;
                    case DC_IDENTIFIER:
                        identifier = reader.nextInt();
                        break;
                    case "ayto:etiqLinea":
                        linea = reader.nextString();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
        }
        reader.endObject();


        switch(tipo){
            case 0:
                return new Linea(name, numero, identifier);
            case 1:
                return new Parada(0,name,numero,identifier);
            case 2:
                return new Estimacion(distancia1,tiempo1,identifier,paradaId,linea);
            default:
                return null;
        }
    }


}//ParserJSON
