package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created by Anna on 04.05.14.
 */
/**
 * Created by Anna on 03.05.14.
 */


import android.net.Uri;

import java.util.Calendar;
import java.util.StringTokenizer;

public class AlarmModel
{

    int idDataCzas;
    int dzien;
    int miesiac;
    int rok;
    int godzina;
    int minuta;

    Calendar kalendarz=Calendar.getInstance();


    public AlarmModel(TabelaDataCzas dt)
    {
        if(dt.getCzyBiegOdbyty()==false)
        {
            String data=dt.getData();
            String czas=dt.getCzas();
            idDataCzas=dt.getId();
            StringTokenizer StringData = new StringTokenizer(data, ".");
            StringTokenizer StringCzas = new StringTokenizer(czas, ":");
            dzien=Integer.parseInt(StringData.nextToken());
            miesiac=Integer.parseInt(StringData.nextToken());
            rok=Integer.parseInt(StringData.nextToken());
            godzina=Integer.parseInt(StringCzas.nextToken());
            minuta=Integer.parseInt(StringCzas.nextToken());
            setKalendarz();
        }
    }

    public void setDzien(int _dzien)
    {
        dzien=_dzien;
    }
    public Integer getDzien(){return dzien;}

    public void setMiesiac(int _miesiac)
    {
        miesiac=_miesiac;
    }
    public Integer getMiesiac(){return miesiac;}

    public void setRok(int _rok)
    {
        rok=_rok;
    }
    public Integer getRok(){return rok;}

    public void setGodzina(int _godzina)
    {
        godzina=_godzina;
    }

    public Integer getGodzina(){return godzina;}

    public void setMinuta(int _minuta)
    {
        minuta=_minuta;
    }

    public Integer getMinuta(){return minuta;}

    void setKalendarz()
    {
        kalendarz.set(rok,miesiac,dzien,godzina,minuta);
    }

    public Calendar getCalendarDate(){return kalendarz;}

    public Integer getID(){return idDataCzas;}
    public void setID(int _id)
    {
        idDataCzas=_id;
    }
}
