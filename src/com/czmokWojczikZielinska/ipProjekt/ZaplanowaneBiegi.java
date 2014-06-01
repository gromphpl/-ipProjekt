package com.czmokWojczikZielinska.ipProjekt;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.*;

import static android.widget.AdapterView.OnItemClickListener;

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
            registerForContextMenu(lv);
            lv.setAdapter(adapter);
            lv.setLongClickable(true);
            lv.setClickable(true);


        }



    }
    ListView lv;
    ListAdapterCustom adapter;
    MySQLiteHelper db;
    int clickedPosition;
    AlarmModel am;
    UstawDateCzasBiegu dataCzas=new UstawDateCzasBiegu();

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_zaplanowane_biegi, menu);
        menu.setHeaderTitle("Wybierz");
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        clickedPosition=info.position;
        TabelaDataCzas obiekt=adapter.getItem(clickedPosition);
        am=new AlarmModel(obiekt);
        switch (item.getItemId()) {
            case R.id.menuDelete:
                if(obiekt.getCzyBiegOdbyty())
                {
                    SkasujAlarm(am);
                }
                UsunRekord(obiekt);
                return true;
            case R.id.menuUpdate:
                if(obiekt.getCzyBiegOdbyty())
                {
                    SkasujAlarm(am);
                    obiekt.setCzyBiegOdbyty(false);
                    db.updateDataCzas(obiekt);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    obiekt.setCzyBiegOdbyty(true);
                    db.updateDataCzas(obiekt);
                    UstawAlarm(am);
                    adapter.notifyDataSetChanged();
                }
                return  true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    public void UsunJesliDataPrzeszla(List<TabelaDataCzas> dt)
    {
        for(TabelaDataCzas i:dt)
        {
            String czasTemp=i.getCzas();
            String dataTemp=i.getData();
            StringTokenizer StringData = new StringTokenizer(dataTemp, ".");
            StringTokenizer StringCzas = new StringTokenizer(czasTemp, ":");
            int dzien=Integer.parseInt(StringData.nextToken());
            int miesiac=Integer.parseInt(StringData.nextToken());
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

    void UsunRekord(TabelaDataCzas obiekt)
    {
        db.deleteDataCzas(obiekt);
        adapter.remove(clickedPosition);
        adapter.notifyDataSetChanged();
    }

    public void UstawAlarm(AlarmModel am)
    {
        Calendar dataGodzina=am.getCalendarDate();

        int unikalneId=am.getID();//(int)dataGodzina.getTimeInMillis();

        Intent myIntent = new Intent(getApplicationContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),unikalneId,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC, dataGodzina.getTimeInMillis(),pendingIntent);

    }

    public void SkasujAlarm(AlarmModel am)
    {
        Calendar dataGodzina=am.getCalendarDate();

        int unikalneId=am.getID();//(int)dataGodzina.getTimeInMillis();

        Intent myIntent = new Intent(this.getApplicationContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),unikalneId,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);

    }



}
