package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Anna on 23.03.14.
 */


//klasa zajmuje sie harmonogramem biegow
public class UstawDateCzasBiegu extends Activity
{

    MySQLiteHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ustaw_date_czas);

        db=new MySQLiteHelper(this);


        Button btnUstawNastepnyBieg = (Button) findViewById(R.id.btnUstaw);
        btnUstawNastepnyBieg.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        KtoreDniZaznaczone();

                        for(int i=0; i<data.size();i++)
                        {
                            db.dodajRekordDataCzas(new TabelaDataCzas(data.get(i),czas.get(i),false));
                        }

                        List<TabelaDataCzas> dt=db.getAllDataCzas();

                        UsunJesliDataPrzeszla(dt);

                        dt.clear();

                        dt=db.getAllDataCzas();

                        for(TabelaDataCzas i:dt)
                        {
                            if(i.getCzyBiegOdbyty()==false)
                            {
                                amList.add(new AlarmModel(i));
                                i.setCzyBiegOdbyty(true);
                                db.updateDataCzas(i);
                            }
                        }



                        for(int i=0;i<amList.size();i++)
                        {
                            UstawAlarm(amList.get(i));

                        }


                        Intent myIntent = new Intent(view.getContext(), MyActivity.class);
                        //myIntent.putExtra("obecnyDzienTygodnia",DzienTygodnia());
                        startActivityForResult(myIntent, 0);

                    }

                }
                );
    }

    public ArrayList<Calendar> listaDatBiegow=new ArrayList<Calendar>();
    public List<String> czas=new ArrayList<String>();
    public List<String> data=new ArrayList<String>();
    List<AlarmModel> amList=new LinkedList<AlarmModel>();

    public void KtoreDniZaznaczone()
    {
        CheckBox poniedzialek=(CheckBox) findViewById(R.id.cbPon);
        CheckBox wtorek=(CheckBox) findViewById(R.id.cbWt);
        CheckBox sroda=(CheckBox) findViewById(R.id.cbSr);
        CheckBox czwartek=(CheckBox) findViewById(R.id.cbCzw);
        CheckBox piatek=(CheckBox) findViewById(R.id.cbPt);
        CheckBox sobota=(CheckBox) findViewById(R.id.cbSb);
        CheckBox niedziela=(CheckBox) findViewById(R.id.cbNd);

        TimePicker wybranyCzasBiegu=(TimePicker) findViewById(R.id.timePicker);
        wybranyCzasBiegu.clearFocus();

        Calendar obecnaData=new GregorianCalendar();//teraz
        Calendar wybranaDataICzas=new GregorianCalendar();//przez uzytkownika
        obecnaData.getInstance();
        int godzina = wybranyCzasBiegu.getCurrentHour();
        int minuta = wybranyCzasBiegu.getCurrentMinute();
        int rok=obecnaData.get(Calendar.YEAR);
        int miesiac=obecnaData.get(Calendar.MONTH)+1;
        int dzien=obecnaData.get(Calendar.DAY_OF_MONTH);
        String aktualnyDzienTygodnia=DzienTygodnia();


        if(aktualnyDzienTygodnia.equalsIgnoreCase("Monday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);

                if(obecnaData.after(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien

                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));
            }
            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

        }
        if(aktualnyDzienTygodnia.equalsIgnoreCase("Tuesday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok, miesiac, dzien, godzina, minuta);
                if(!obecnaData.before(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien
                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }
        }
        if(aktualnyDzienTygodnia.equalsIgnoreCase("Wednesday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok, miesiac, dzien, godzina, minuta);
                if(!obecnaData.before(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien
                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }
        }
        if(aktualnyDzienTygodnia.equalsIgnoreCase("Thursday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(wybranaDataICzas.get(Calendar.HOUR)+":"+wybranaDataICzas.get(Calendar.MINUTE));
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));
            }

            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                if(!obecnaData.before(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien
                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }
        }
        if(aktualnyDzienTygodnia.equalsIgnoreCase("Friday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                if(!obecnaData.before(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien
                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }
        }

        if(aktualnyDzienTygodnia.equalsIgnoreCase("Saturday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH, 6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                if(!obecnaData.before(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien
                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }
        }
        if(aktualnyDzienTygodnia.equalsIgnoreCase("Sunday"))
        {

            if(poniedzialek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,1);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(wtorek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,2);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sroda.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,3);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(czwartek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,4);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(piatek.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH,5);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(sobota.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                wybranaDataICzas.add(Calendar.DAY_OF_MONTH, 6);
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }

            if(niedziela.isChecked())
            {
                wybranaDataICzas.set(rok,miesiac,dzien,godzina,minuta);
                if(!obecnaData.before(wybranaDataICzas))//jesli juz nie w ten sam dzien
                {
                    wybranaDataICzas.add(Calendar.DAY_OF_MONTH,7); // za tydzien
                }
                czas.add(godzina+":"+minuta);
                data.add(wybranaDataICzas.get(Calendar.DATE)+"."+wybranaDataICzas.get(Calendar.MONTH)+"."+wybranaDataICzas.get(Calendar.YEAR));

            }
        }
    }

    public String DzienTygodnia()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE",Locale.US);
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        dayOfTheWeek=dayOfTheWeek.toString();
        return  dayOfTheWeek;
    }

    public void UstawAlarm(AlarmModel am)
    {

        // UstawDateCzasBiegu to obecna Activity a AlarmReceiver to
        // BoradCastReceiver
        Intent myIntent = new Intent(UstawDateCzasBiegu.this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(UstawDateCzasBiegu.this,
                0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC, am.getCalendarDate().getTimeInMillis(),
                pendingIntent);

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
