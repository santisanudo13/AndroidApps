package es.unican.g3.tus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.unican.g3.tus.R;


/**
 * Base de datos
 */

public class Database extends SQLiteOpenHelper {

    // Contexto
    private Context context;

    // Version
    private static final int VERSION_BASEDATOS = 3;

    // Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "database_g3.db";

    // Tablas
    private static final String TABLA_PARADAS = "CREATE TABLE paradas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, ALIAS TEXT, NOTAS TEXT, NUMERO INT, IDENTIFICADOR INT)";

    private static final String TABLA_LINEAS = "CREATE TABLE lineas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, NUMERO TEXT, NOMBRE TEXT, IDENTIFICADOR INT)";


    private static final String TABLA_ESTIMACIONES = "CREATE TABLE estimaciones " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, DISTANCIA INT, TIEMPO INT, IDENTIFICADOR INT,PARADAID INT,LINEA TEXT)";

    private static final String TABLA_GRUPOS = "CREATE TABLE grupos " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, COLOR TEXT)";

    private static final String TABLA_GRUPOS_PARADAS = "CREATE TABLE grupos_paradas " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, PARADA_ID INTEGER, GRUPO_ID INTEGER, " +
            "FOREIGN KEY(PARADA_ID) REFERENCES paradas(_id), FOREIGN KEY(GRUPO_ID) REFERENCES grupos(_id))";

    private static final String UNIQUEINDEX_GRUPOS_PARADAS = "CREATE UNIQUE INDEX grupo_parada " +
            "ON grupos_paradas (PARADA_ID,GRUPO_ID);";


    // Campos tabla
    private static final String NOMBRE = "NOMBRE";
    private static final String COLOR = "COLOR";
    private static final String ALIAS = "ALIAS";
    private static final String NOTAS = "NOTAS";

    private static final String NUMERO ="NUMERO";
    private static final String IDENTIFICADOR ="IDENTIFICADOR";
    private static final String DISTANCIA ="DISTANCIA";
    private static final String TIEMPO ="TIEMPO";
    private static final String PARADAID ="PARADAID";
    private static final String PARADA_ID ="PARADA_ID";
    private static final String LINEA ="LINEA";
    private static final String NOMBRE_PARADAS ="paradas";
    private static final String NOMBRE_LINEAS ="lineas";
    private static final String NOMBRE_ESTIMACIONES ="estimaciones";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS '";


    private static final String GRUPO_ID = "GRUPO_ID";



    private static final String NOMBRE_GRUPOS = "grupos";
    private static final String NOMBRE_GRUPOS_PARADAS = "grupos_paradas";



    public Database(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        reiniciar(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    private void inicializarGrupos(SQLiteDatabase sqLiteDatabase){
        SQLiteDatabase db = sqLiteDatabase;

        // Carga de grupos inicial
        List<Grupo> grupos = new ArrayList<>();
        grupos.add(new Grupo(0, "Aguamarina", "#00C2C2"));
        grupos.add(new Grupo(0, "Amarillo", "#FFC501"));
        grupos.add(new Grupo(0, "Azul", "#0062D8"));
        grupos.add(new Grupo(0, "Celeste", "#00F7FF"));
        grupos.add(new Grupo(0, "Cian", "#00A4EB"));
        grupos.add(new Grupo(0, "Coral", "#FF7676"));
        grupos.add(new Grupo(0, "Granate", "#7C0827"));
        grupos.add(new Grupo(0, "Gris", "#808080"));
        grupos.add(new Grupo(0, "Marrón", "#5A2D00"));
        grupos.add(new Grupo(0, "Morado", "#940055"));
        grupos.add(new Grupo(0, "Naranja", "#FF8000"));
        grupos.add(new Grupo(0, "Negro", "#000000"));
        grupos.add(new Grupo(0, "Rojo", "#DA0000"));
        grupos.add(new Grupo(0, "Rosa", "#F859C8"));
        grupos.add(new Grupo(0, "Verde", "#2CC100"));
        grupos.add(new Grupo(0, "Violeta", "#4E0094"));

        try {
            if(db != null){
                db.execSQL(TABLA_GRUPOS);

                for (Grupo grupo : grupos) {
                    ContentValues valores = new ContentValues();
                    valores.put(Database.NOMBRE, grupo.getNombre());
                    valores.put(Database.COLOR, grupo.getColor());
                    db.insert(NOMBRE_GRUPOS, null, valores);
                }

                db.execSQL(TABLA_GRUPOS_PARADAS);
                db.execSQL(UNIQUEINDEX_GRUPOS_PARADAS);
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }


    }

    public void reiniciar(SQLiteDatabase sqLiteDatabase){

        SQLiteDatabase db;

        if(sqLiteDatabase == null) {
            db = getWritableDatabase();
        }else{
            db = sqLiteDatabase;
        }

        try {
            if(db != null) {
                eliminarEstructura(db);
                db.execSQL(TABLA_PARADAS);
                db.execSQL(TABLA_LINEAS);
                db.execSQL(TABLA_ESTIMACIONES);
                inicializarGrupos(db);
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }

    }

    public void eliminarEstructura(SQLiteDatabase sqLiteDatabase){

        SQLiteDatabase db;

        if(sqLiteDatabase == null) {
            db = getWritableDatabase();
        }else{
            db = sqLiteDatabase;
        }

        try {
            if(db != null) {
                db.execSQL(DROP_TABLE + NOMBRE_PARADAS + "'");
                db.execSQL(DROP_TABLE + NOMBRE_LINEAS + "'");
                db.execSQL(DROP_TABLE + NOMBRE_GRUPOS + "'");
                db.execSQL(DROP_TABLE + NOMBRE_GRUPOS_PARADAS + "'");
                db.execSQL(DROP_TABLE + NOMBRE_ESTIMACIONES + "'");
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();

        }
    }

    public void insertarParada(String nombre, String alias, String notas, int numero, int codigo){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db != null){
                ContentValues valores = new ContentValues();
                valores.put(Database.NOMBRE, nombre);
                valores.put(Database.ALIAS, alias);
                valores.put(Database.NOTAS, notas);
                valores.put(Database.NUMERO, numero);
                valores.put(Database.IDENTIFICADOR, codigo);
                db.insert(NOMBRE_PARADAS, null, valores);
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }
    }

    public void insertarLinea(String numero, String nombre, int identificador){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db != null){
                ContentValues valores = new ContentValues();
                valores.put(Database.NUMERO, numero);
                valores.put(Database.NOMBRE, nombre);
                valores.put(Database.IDENTIFICADOR, identificador);
                db.insert(NOMBRE_LINEAS, null, valores);
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }
    }
    public void insertarEstimacion(int distancia, int tiempo, int identificador, int paradaId, String linea){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            ContentValues valores = new ContentValues();
            valores.put(Database.DISTANCIA, distancia);
            valores.put(Database.TIEMPO, tiempo);
            valores.put(Database.IDENTIFICADOR, identificador);
            valores.put(Database.PARADAID, paradaId);
            valores.put(Database.LINEA, linea);
            db.insert(NOMBRE_ESTIMACIONES, null, valores);
            db.close();
        }
    }

    public void modificarLinea(String numero, String nombre,  int identificador){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.NUMERO, numero);
        valores.put(Database.NOMBRE, nombre);
        valores.put(Database.IDENTIFICADOR, identificador);
        db.insert(Database.NOMBRE_LINEAS, null, valores);
        db.close();
    }

    public void modificarParada(String nombre, String alias, String notas, int numero, int codigo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.NOMBRE, nombre);
        valores.put(Database.ALIAS, alias);
        valores.put(Database.NOTAS, notas);
        valores.put(Database.NUMERO, numero);
        valores.put(Database.IDENTIFICADOR, codigo);
        db.insert(Database.NOMBRE_PARADAS, null, valores);
        db.close();
    }

    public void borrarParada(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Database.NOMBRE_PARADAS, Database.IDENTIFICADOR +id, null);
        db.close();
    }

    public void borrarLinea(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Database.NOMBRE_LINEAS, Database.IDENTIFICADOR +id, null);
        db.close();
    }
    public void borrarEstimacion(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Database.NOMBRE_ESTIMACIONES, Database.IDENTIFICADOR+id, null);
        db.close();
    }

    public Parada recuperarParada(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valoresRecuperar = {"_id", Database.NOMBRE, Database.ALIAS, Database.NOTAS, Database.NUMERO, Database.IDENTIFICADOR};
        Cursor c = db.query(Database.NOMBRE_PARADAS, valoresRecuperar, Database.IDENTIFICADOR + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            Parada parada = new Parada(c.getInt(0), c.getString(1), c.getString(4), c.getInt(5));
            db.close();
            c.close();
            return parada;
        }else{
            return null;
        }
    }

    public Linea recuperarLinea(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valoresRecuperar = {"_id", Database.NUMERO, Database.NOMBRE, Database.IDENTIFICADOR};
        Cursor c= db.query(Database.NOMBRE_LINEAS, valoresRecuperar, Database.IDENTIFICADOR + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
            Linea linea = new Linea(c.getString(1), c.getString(2), c.getInt(3));
            db.close();
            c.close();
            return linea;
        }else{
            return null;
        }
    }

    public List<Parada> recuperarParadas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Parada> listaParadas = new ArrayList<>();

        try {
            if(db != null){
                String[] valoresRecuperar = {"_id", Database.NOMBRE, Database.ALIAS, Database.NOTAS, Database.NUMERO, Database.IDENTIFICADOR};
                Cursor c = db.query(Database.NOMBRE_PARADAS, valoresRecuperar, null,
                        null, null, null, null, null);
                if(c != null){
                    if(c.moveToFirst()){
                        do {
                            Parada parada = new Parada(c.getInt(0), c.getString(1), c.getString(4), c.getInt(5));
                            parada.setAlias(c.getString(2));
                            parada.setNotas(c.getString(3));
                            listaParadas.add(parada);
                        } while (c.moveToNext());
                    }
                    c.close();
                }else{
                    muestraErrorBBDD();
                }
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }

        return listaParadas;
    }

    public List<Linea> recuperarLineas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Linea> listaLineas = new ArrayList<>();

        try {
            if(db != null){
                String[] valoresRecuperar = {"_id", Database.NUMERO, Database.NOMBRE, Database.IDENTIFICADOR};
                Cursor c = db.query(Database.NOMBRE_LINEAS, valoresRecuperar, null,
                        null, null, null, null, null);
                if(c.moveToFirst()) {
                    do {
                        Linea linea = new Linea(c.getString(2), c.getString(1), c.getInt(3));
                        listaLineas.add(linea);
                    } while (c.moveToNext());
                }
                db.close();
                c.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }

        return listaLineas;
    }
    public List<Estimacion> recuperarEstimaciones() {
        SQLiteDatabase db = getReadableDatabase();
        List<Estimacion> listaEstimaciones = new ArrayList<>();
        String[] valoresRecuperar = {"_id", Database.DISTANCIA, Database.TIEMPO, Database.IDENTIFICADOR,Database.PARADAID,Database.LINEA};
        Cursor c = db.query(Database.NOMBRE_ESTIMACIONES, valoresRecuperar, null,
                null, null, null, null, null);
        if(c.moveToFirst()) {
            do {
                Estimacion estimacion = new Estimacion(c.getInt(2), c.getInt(1), c.getInt(3),c.getInt(4),c.getString(5));
                listaEstimaciones.add(estimacion);
            } while (c.moveToNext());
        }
        db.close();
        c.close();
        return listaEstimaciones;
    }

    public List<Grupo> recuperarGrupos() {
        SQLiteDatabase db = getReadableDatabase();
        List<Grupo> listaGrupos = new ArrayList<>();

        try {
            if(db != null){
                String[] valoresRecuperar = {"_id", Database.NOMBRE, Database.COLOR};
                Cursor c = db.query(Database.NOMBRE_GRUPOS, valoresRecuperar, null,
                        null, null, null, null, null);
                if(c.moveToFirst()) {
                    do {
                        Grupo grupo = new Grupo(c.getInt(0), c.getString(1), c.getString(2));
                        listaGrupos.add(grupo);
                    } while (c.moveToNext());
                }
                db.close();
                c.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }

        return listaGrupos;
    }

    public List<GrupoParada> recuperarParadasGrupo(List<Parada> paradas, List<Grupo> grupos) {
        SQLiteDatabase db = getReadableDatabase();
        List<GrupoParada> listaGrupoParada = new ArrayList<>();

        try {
            if(db != null){
                String[] valoresRecuperar = {"_id", Database.PARADA_ID, Database.GRUPO_ID};
                Cursor c = db.query(Database.NOMBRE_GRUPOS_PARADAS, valoresRecuperar, null,
                        null, null, null, null, null);
                if(c.moveToFirst()) {
                    do {
                        listaGrupoParada.add(new GrupoParada(Grupo.buscaGrupo(grupos, c.getInt(2)), Parada.buscaParada(paradas, c.getInt(1))));
                    } while (c.moveToNext());
                }
                db.close();
                c.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }

        return listaGrupoParada;
    }

    public void agregaParadaAColor(int paradaId, int grupoId){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db != null){
                ContentValues valores = new ContentValues();
                valores.put(Database.PARADA_ID, paradaId);
                valores.put(Database.GRUPO_ID, grupoId);
                db.insertOrThrow(Database.NOMBRE_GRUPOS_PARADAS, null, valores);
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch(SQLiteConstraintException sqlException){
            Log.d("sql", ""+sqlException);
        }
        catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }
    }

    public void eliminaParadaDeGrupo(int paradaId, int grupoId){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db != null){
                db.delete(Database.NOMBRE_GRUPOS_PARADAS, Database.PARADA_ID + "=? and " + Database.GRUPO_ID + "=?", new String[]{Integer.toString(paradaId), Integer.toString(grupoId)});
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }
    }

    public void eliminaEstimacionParada(int paradaId){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db != null){
                db.delete(Database.NOMBRE_ESTIMACIONES, Database.PARADAID + "=?", new String[]{Integer.toString(paradaId)});
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }
    }

    public void eliminaParadasDeGrupos(){
        SQLiteDatabase db = getWritableDatabase();
        try {
            if(db != null){
                db.delete(Database.NOMBRE_GRUPOS_PARADAS, null, null);
                db.close();
            }else{
                muestraErrorBBDD();
            }
        }catch (SQLException sqlException) {
            Log.d("sql", ""+sqlException);
            muestraErrorBBDD();
        }
    }

    public void sincronizarParadas(List<Parada> paradasRemotas) {
        // Obtener listado paradas locales
        List<Parada> paradasLocales = recuperarParadas();

        // Sincronización de datos locales con remotos
        for (Parada parada : paradasRemotas) {
            // Si la parada remota no existe en las descargadas en la aplicación, se inserta
            if(!paradasLocales.contains(parada)){
                String alias = "";
                String notas = "";

                // Simulación de las funciones de tag y notas en dos paradas
                if(parada.getIdentifier() == 323){
                    alias = "mi casita";
                }else if(parada.getIdentifier() == 45){
                    notas = "domino";
                }

                insertarParada(parada.getName(), alias, notas, Integer.parseInt(parada.getNumero()), parada.getIdentifier());
            }
        }
    }

    public void sincronizarLineas(List<Linea> lineasRemotas) {
        // Obtener listado líneas locales
        List<Linea> lineasLocales = recuperarLineas();

        // Sincronización de datos locales con remotos
        for (Linea linea : lineasRemotas) {
            // Si la línea remota no existe en las descargadas en la aplicación, se inserta
            if(!lineasLocales.contains(linea)){
                insertarLinea(linea.getNumero(), linea.getName(), linea.getIdentifier());
            }
        }
    }

    public void muestraErrorBBDD(int mensaje){
        Toast.makeText(this.context, mensaje, Toast.LENGTH_LONG).show();
    }
    public void sincronizarEstimaciones(List<Estimacion> estimacionesRemotas) {

        // Sincronización de datos locales con remotos
        for (Estimacion estimacion : estimacionesRemotas) {
            // Si la línea remota no existe en las descargadas en la aplicación, se inserta
            insertarEstimacion(estimacion.getDistancia(), estimacion.getTiempo(), estimacion.getIdentifier(),estimacion.getParadaId(),estimacion.getLinea());
        }

    }

    public void muestraErrorBBDD(){
        Toast.makeText(this.context, R.string.app_fallo_bbdd, Toast.LENGTH_LONG).show();
    }
}