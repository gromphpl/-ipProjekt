package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Anna on 04.06.14.
 */
public class OdbyteBiegi extends Activity
{

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.odbyte_biegi);

        ListView lv2;
        ListAdapterCustomBiegi adapter2;
        MySQLiteHelper db;

        lv2=(ListView) findViewById(R.id.listView2);
        db=new MySQLiteHelper(this.getApplicationContext());
        List<Bieg> b;
        b=db.getAllBieg();
        adapter2 = new ListAdapterCustomBiegi(OdbyteBiegi.this,b);
        int i=adapter2.getCount();
        if(i>0)
        {
            registerForContextMenu(lv2);
            lv2.setAdapter(adapter2);
        }



    }
}
