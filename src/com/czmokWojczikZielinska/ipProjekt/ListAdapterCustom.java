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

        data.setText(dt.getData());
        czas.setText(dt.getCzas());
        odbyty.setChecked(dt.getCzyBiegOdbyty());

        return arg1;
    }

}
