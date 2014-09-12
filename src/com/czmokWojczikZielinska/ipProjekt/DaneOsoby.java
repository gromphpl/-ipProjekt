package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created by Anna on 11.09.14.
 */
public class DaneOsoby
{
    int id;
    String imie;
    int wiek;
    int waga;
    int wzrost;

    public DaneOsoby(){}

    public DaneOsoby(String _imie, int _wiek, int _waga, int _wzrost)
    {
        imie=_imie;
        wiek=_wiek;
        waga=_waga;
        wzrost=_wzrost;
    }

    public void setImie(String _imie){imie=_imie;}
    public String getImie(){return imie;}

    public void setWiek(int _wiek){wiek=_wiek;}
    public int getWiek(){return wiek;}

    public void setWaga(int _waga){waga=_waga;}
    public int getWaga(){return waga;}

    public void setWzrost(int _wzrost){wzrost=_wzrost;}
    public int getWzrost(){return wzrost;}

    public void setId(int _id){id=_id;}
    public int getId(){return id;}

}
