package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Anna on 01.05.14.
 */
public class ZaplanowaneBiegi extends Activity
{

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.zaplanowane_biegi);
        lv=(ListView) findViewById(R.id.listView);
        db=new MySQLiteHelper(this.getApplicationContext());
        List<TabelaDataCzas> dt;
        dt=db.getAllDataCzas();
        UsunJesliDataPrzeszla(dt);
        dt.clear();
        dt=db.getAllDataCzas();
        adapter = new ListAdapterCustom(ZaplanowaneBiegi.this,dt);
        int i=adapter.getCount();
        if(i>0)
        {
            lv.setAdapter(adapter);
        }


    }
    ListView lv;
    ListAdapterCustom adapter;
    MySQLiteHelper db;

    public void UsunJesliDataPrzeszla(List<TabelaDataCzas> dt)
    {
        for(TabelaDataCzas i:dt)
        {
            String czasTemp=i.getCzas();
            String dataTemp=i.getData();
            StringTokenizer StringData = new StringTokenizer(dataTemp, ".");
            StringTokenizer StringCzas = new StringTokenizer(czasTemp, ":");
            int dzien=Integer.parseInt(StringData.nextToken());
            int miesiac=Integer.parseInt(StringData.nextToken())-1;
            int rok=Integer.parseInt(StringData.nextToken());
            int godzina=Integer.parseInt(StringCzas.nextToken());
            int minuta=Integer.parseInt(StringCzas.nextToken());
            Calendar dataZBazy=Calendar.getInstance();
            dataZBazy.set(rok,miesiac,dzien,godzina,minuta);
            if(dataZBazy.before(Calendar.getInstance()))
            {
                db.deleteDataCzas(i);
            }
        }

    }

}
