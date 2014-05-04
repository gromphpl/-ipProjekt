package com.czmokWojczikZielinska.ipProjekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 14.04.14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BazaDanych";

    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Stworz tabele DataCzas
        String CREATE_DataCzas_TABLE = "CREATE TABLE DataCzas (id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT, czas TEXT, biegOdbyty BOOLEAN )";
        db.execSQL(CREATE_DataCzas_TABLE);

        // Stworz tabele Bieg
        String CREATE_Bieg_TABLE = "CREATE TABLE Bieg (idBiegu INTEGER PRIMARY KEY AUTOINCREMENT, dataBiegu TEXT, czasBiegu TEXT, przebiegnietyDystans INTEGER, predkoscBiegu DOUBLE, czasPrzebiegniecia DOUBLE )";
        db.execSQL(CREATE_Bieg_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //usun tabele jesli istnieja
        db.execSQL("DROP TABLE IF EXISTS DataCzas");
        db.execSQL("DROP TABLE IF EXISTS Bieg");
        this.onCreate(db);
    }

    // nazwy tabel
    private static final String TABLE_DATACZAS = "DataCzas";
    private static final String TABLE_BIEG = "Bieg";

    // nazwy kolumn tabeli DataCzas
    private static final String KEY_ID = "id";
    private static final String KEY_DATA = "data";
    private static final String KEY_CZAS = "czas";
    private static final String KEY_CZYODBYTY="biegOdbyty";

    //nazwy kolumn tabeli Bieg
    private static final String KEY_IDBIEG = "idBiegu";
    private static final String KEY_DATABIEGU = "dataBiegu";
    private static final String KEY_CZASBIEGU = "czasBiegu";
    private static final String KEY_DYSTANS ="przebiegnietyDystans";
    private static final String KEY_PREDKOSC ="predkoscBiegu";
    private static final String KEY_CZASPRZEBIEGNIECIA ="czasPrzebiegniecia";

    private static final String[] COLUMNS_DATACZAS = {KEY_ID,KEY_DATA,KEY_CZAS,KEY_CZYODBYTY};
    private static final String[] COLUMNS_BIEG={KEY_IDBIEG,KEY_DATABIEGU,KEY_CZASBIEGU,KEY_DYSTANS,KEY_PREDKOSC,KEY_CZASPRZEBIEGNIECIA};

    //nowy rekord do tabeli DataCzas
    public void dodajRekordDataCzas(TabelaDataCzas dt)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATA, dt.getData());
        values.put(KEY_CZAS, dt.getCzas());
        values.put(KEY_CZYODBYTY, dt.getCzyBiegOdbyty());

        db.insert(TABLE_DATACZAS, null, values);

        db.close();
    }

    //nowy rekord do tabeli Bieg
    public void dodajRekordBieg(Bieg b)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATABIEGU, b.getDataBiegu());
        values.put(KEY_CZASBIEGU, b.getCzasBiegu());
        values.put(KEY_DYSTANS,b.getPrzebiegnietyDystans());
        values.put(KEY_CZASPRZEBIEGNIECIA,b.getCzasPrzebiegniecia());
        values.put(KEY_PREDKOSC,b.getPredkoscBiegu());

        db.insert(TABLE_BIEG, null, values);

        db.close();
    }

    //pokaz 1 rekord tabeli DataCzas
    public TabelaDataCzas showOneDataCzas(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_DATACZAS, // a. table
                        COLUMNS_DATACZAS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        TabelaDataCzas pokazDT = new TabelaDataCzas();
        pokazDT.setId(Integer.parseInt(cursor.getString(0)));
        pokazDT.setData(cursor.getString(1));
        pokazDT.setCzas(cursor.getString(2));
        pokazDT.setCzyBiegOdbyty(Boolean.parseBoolean(cursor.getString(3)));

        return pokazDT;
    }

    //pokaz 1 rekord tabeli Bieg
    public Bieg showOneBieg(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_BIEG, // a. table
                        COLUMNS_BIEG, // b. column names
                        " idBiegu = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Bieg pokazB = new Bieg();
        pokazB.setIdBiegu(Integer.parseInt(cursor.getString(0))); //id biegu
        pokazB.setDataBiegu(cursor.getString(1)); //data biegu
        pokazB.setCzasBiegu(cursor.getString(2)); //czas biegu
        pokazB.setPrzebiegnietyDystans(Integer.parseInt(cursor.getString(3))); // dystans
        pokazB.setPredkoscBiegu(Double.parseDouble(cursor.getString(4))); //predkosc
        pokazB.setCzasPrzebiegniecia(Double.parseDouble(cursor.getString(5))); //czas przebiegniecia

        return pokazB;
    }

    public List<TabelaDataCzas> getAllDataCzas() {
        List<TabelaDataCzas> dataCzasList = new ArrayList<TabelaDataCzas>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATACZAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TabelaDataCzas dt = new TabelaDataCzas();
                dt.setId(Integer.parseInt(cursor.getString(0)));
                dt.setData(cursor.getString(1));
                dt.setCzas(cursor.getString(2));
                dt.setCzyBiegOdbyty(Boolean.parseBoolean(cursor.getString(3)));
                // Adding contact to list
                dataCzasList.add(dt);
            } while (cursor.moveToNext());
        }
        return dataCzasList;
    }

    public List<Bieg> getAllBieg() {
        List<Bieg> biegList = new ArrayList<Bieg>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BIEG;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do {
                Bieg bieg = new Bieg();
                bieg.setIdBiegu(Integer.parseInt(cursor.getString(0))); //id biegu
                bieg.setDataBiegu(cursor.getString(1)); //data biegu
                bieg.setCzasBiegu(cursor.getString(2)); //czas biegu
                bieg.setPrzebiegnietyDystans(Integer.parseInt(cursor.getString(3))); // dystans
                bieg.setPredkoscBiegu(Double.parseDouble(cursor.getString(4))); //predkosc
                bieg.setCzasPrzebiegniecia(Double.parseDouble(cursor.getString(5))); //czas przebiegniecia
                biegList.add(bieg);
            }
            while (cursor.moveToNext());
        }
        return biegList;
    }

    public int getDataCzasCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_DATACZAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int getBiegCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_BIEG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


    public int updateDataCzas(TabelaDataCzas dt)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATA, dt.getData());
        values.put(KEY_CZAS, dt.getCzas());
        values.put(KEY_CZYODBYTY, dt.getCzyBiegOdbyty());

        return db.update(TABLE_DATACZAS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(dt.getId()) });
    }

    public int updateBieg(Bieg b)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATABIEGU,b.getDataBiegu());
        values.put(KEY_CZASBIEGU,b.getCzasBiegu());
        values.put(KEY_PREDKOSC,b.getPredkoscBiegu());
        values.put(KEY_CZASPRZEBIEGNIECIA,b.getCzasPrzebiegniecia());
        values.put(KEY_DYSTANS,b.getPrzebiegnietyDystans());


        return db.update(TABLE_BIEG, values, KEY_IDBIEG + " = ?",
                new String[] { String.valueOf(b.getIdBiegu()) });
    }

    public void deleteDataCzas(TabelaDataCzas dt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATACZAS, KEY_ID + " = ?",
                new String[] { String.valueOf(dt.getId()) });
        db.close();
    }

    public void deleteBieg(Bieg b)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIEG, KEY_IDBIEG + " = ?",
                new String[] { String.valueOf(b.getIdBiegu()) });
        db.close();
    }



}
