package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created by Anna on 14.04.14.
 */
public class TabelaDataCzas
{
    private int id;
    private String data;
    private String czas;
    private boolean biegOdbyty;

    public TabelaDataCzas()
    {}

    public TabelaDataCzas(String _data, String _czas, boolean _biegOdbyty)
    {
        data=_data;
        czas=_czas;
        biegOdbyty=_biegOdbyty;
    }

    public TabelaDataCzas(int _id, String _data, String _czas, boolean _biegOdbyty)
    {
        id=_id;
        data=_data;
        czas=_czas;
        biegOdbyty=_biegOdbyty;
    }

    //@Override
    //public String toString() {
    //return "Data i czas biegu [id=" + id + ", data =" + data + ", czas =" + czas + "]";
    //}

    public void setId(int _id)
    {
        id=_id;
    }
    public Integer getId(){return id;}

    public String getData()
    {
        return data;
    }
    public void setData(String _data)
    {
        data=_data;
    }

    public String getCzas()
    {
        return  czas;
    }
    public void setCzas(String _czas)
    {
        czas=_czas;
    }

    public Boolean getCzyBiegOdbyty()
    {
        return  biegOdbyty;
    }
    public void setCzyBiegOdbyty(Boolean _czyObdyty)
    {
        biegOdbyty=_czyObdyty;
    }
}
