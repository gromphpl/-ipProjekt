package com.czmokWojczikZielinska.ipProjekt;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 03.05.14.
 */
public class ListAdapterCustom extends BaseAdapter
{
    private List<TabelaDataCzas> items;
    Context mContext;
    LayoutInflater layoutInflater;

    public ListAdapterCustom(Context context, List<TabelaDataCzas> _items)
    {
        items=_items;
        mContext=context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }


    @Override
    public long getItemId(int arg0)
    {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public TabelaDataCzas getItem(int arg0)
    {
        // TODO Auto-generated method stub
        return items.get(arg0);
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        if(arg1==null)
        {
            //LayoutInflater inflater = (LayoutInflater) baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //arg1 = layoutInflater.inflate(R.layout.list_adapter_dt_layout, arg2,false);
            arg1 = layoutInflater.inflate(R.layout.list_adapter_dt_layout, arg2,false);
        }

        TextView data = (TextView)arg1.findViewById(R.id.tvData);
        TextView czas = (TextView)arg1.findViewById(R.id.tvCzas);
        CheckBox odbyty=(CheckBox)arg1.findViewById(R.id.cbCzyOdbyty);

        TabelaDataCzas dt = items.get(arg0);

        String dataTemp=FormatujWyswietlanieDATA(dt.getData());
        String czasTemp=FormatujWyswietlanieCZAS(dt.getCzas());
        data.setText(dataTemp);
        czas.setText(czasTemp);
        odbyty.setChecked(dt.getCzyBiegOdbyty());

        return arg1;
    }

    String FormatujWyswietlanieDATA(String data)
    {
        int dlugoscDaty=data.length();
        //max 10
        if(dlugoscDaty!=10)
        {
            if(dlugoscDaty==8)
            {
                data="0"+data;
                data=data.substring(0,3)+"0"+data.substring(3,data.length());
            }

            if(dlugoscDaty==9)
            {
                if(data.substring(2,3).contains("."))
                {
                    data=data.substring(0,3)+"0"+data.substring(3,data.length());
                }
                else
                {
                    data="0"+data;
                }
            }
        }

        return data;
    }

    String FormatujWyswietlanieCZAS(String czas)
    {
        int dlugoscCzasu=czas.length();
        if(dlugoscCzasu==3)
        {
            czas="0"+czas;
            czas=czas.substring(0,3)+"0"+czas.substring(3,czas.length());
            dlugoscCzasu=czas.length();
        }
        if(dlugoscCzasu==4)
        {
            if(czas.substring(2,3).contains(":"))
            {
                czas=czas.substring(0,3)+"0"+czas.substring(3,czas.length());
            }
            else
            {
                czas="0"+czas;
            }
        }

        return czas;
    }

}
