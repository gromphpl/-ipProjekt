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
        MySQLiteHelper db=new MySQLiteHelper(this.getApplicationContext());
        List<TabelaDataCzas> dt;
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


}
