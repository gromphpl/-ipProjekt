package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created by Anna on 04.05.14.
 */
public class Bieg
{

    int idBiegu;
    String dataBiegu; //wystapienie do kalendarza
    String czasBiegu; //wystapienie do kalendarza
    int przebiegnietyDystans;
    double predkoscBiegu;
    double czasPrzebiegniecia; // w jakim czasie pokonano dystans

    public Bieg()
    {}

    public Bieg(String _dataBiegu, String _czasBiegu, int _przebiegnietyDystans, double _predkoscBiegu, double _czasPrzebiegniecia)
    {
        dataBiegu=_dataBiegu;
        czasBiegu=_czasBiegu;
        przebiegnietyDystans=_przebiegnietyDystans;
        predkoscBiegu=_predkoscBiegu;
        czasPrzebiegniecia=_czasPrzebiegniecia;
    }

    public Bieg(int _idBiegu,String _dataBiegu, String _czasBiegu, int _przebiegnietyDystans, double _predkoscBiegu, double _czasPrzebiegniecia)
    {
        idBiegu=_idBiegu;
        dataBiegu=_dataBiegu;
        czasBiegu=_czasBiegu;
        przebiegnietyDystans=_przebiegnietyDystans;
        predkoscBiegu=_predkoscBiegu;
        czasPrzebiegniecia=_czasPrzebiegniecia;
    }

    public void setIdBiegu(int _idBiegu){idBiegu=_idBiegu;}
    public Integer getIdBiegu(){return idBiegu;}

    public void setDataBiegu(String _dataBiegu){dataBiegu=_dataBiegu;}
    public String getDataBiegu(){return dataBiegu;}

    public void setCzasBiegu(String _czasBiegu){czasBiegu=_czasBiegu;}
    public String getCzasBiegu(){return czasBiegu;}

    public void setPrzebiegnietyDystans(int _przebiegnietyDystans){przebiegnietyDystans=_przebiegnietyDystans;}
    public Integer getPrzebiegnietyDystans(){return przebiegnietyDystans;}

    public void setPredkoscBiegu(double _predkoscBiegu){predkoscBiegu=_predkoscBiegu;}
    public double getPredkoscBiegu(){return predkoscBiegu;}

    public void setCzasPrzebiegniecia(double _czasPrzebiegniecia){czasPrzebiegniecia=_czasPrzebiegniecia;}
    public double getCzasPrzebiegniecia(){return czasPrzebiegniecia;}

}
