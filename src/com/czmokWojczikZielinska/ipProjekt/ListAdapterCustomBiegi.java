package com.czmokWojczikZielinska.ipProjekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Anna on 04.06.14.
 */
public class ListAdapterCustomBiegi extends BaseAdapter
{
        private List<Bieg> items;
        Context mContext;
        LayoutInflater layoutInflater;
        Bieg wybranaPozycja;

        public ListAdapterCustomBiegi(Context context, List<Bieg> _items)
        {
            items=_items;
            mContext=context;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return items.size();
        }


        @Override
        public long getItemId(int arg0)
        {
            // TODO Auto-generated method stub
            return arg0;
        }

        public void remove(int position){
            items.remove(position);
        }

        @Override
        public Bieg getItem(int arg0)
        {
            // TODO Auto-generated method stub
            return items.get(arg0);
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2)
        {

            if(arg1==null)
            {
                //LayoutInflater inflater = (LayoutInflater) baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //arg1 = layoutInflater.inflate(R.layout.list_adapter_dt_layout, arg2,false);
                arg1 = layoutInflater.inflate(R.layout.odbyte_biegi_list_adapter, arg2,false);
            }

            TextView dataCzas = (TextView)arg1.findViewById(R.id.tvDataCzasBiegu);
            TextView predkosc=(TextView)arg1.findViewById(R.id.tvPredkoscBiegu);
            TextView dystans=(TextView)arg1.findViewById(R.id.tvDystans);
            TextView czasBiegu=(TextView)arg1.findViewById(R.id.tvCzasPrzebiegmiecia);
            Bieg b = items.get(arg0);

            String dataTemp=FormatujWyswietlanieDATA(b.getDataBiegu());
            String czasTemp=FormatujWyswietlanieCZAS(b.getCzasBiegu());
            dataCzas.setText(dataTemp+", "+czasTemp);
            predkosc.setText(String.valueOf(b.getPredkoscBiegu()) + " m/s");
            czasBiegu.setText(String.valueOf(b.getCzasPrzebiegniecia()) + " s");
            dystans.setText(String.valueOf(b.getPrzebiegnietyDystans()) + " m");


            return arg1;
        }


        String FormatujWyswietlanieDATA(String data)
        {
            StringTokenizer StringData = new StringTokenizer(data, ".");
            int dzien=Integer.parseInt(StringData.nextToken());
            int miesiac=Integer.parseInt(StringData.nextToken());
            int rok=Integer.parseInt(StringData.nextToken());
            String miesiacStr="";
            switch(miesiac)
            {
                case 0:miesiacStr=" Styczeń "; break;
                case 1:miesiacStr=" Luty "; break;
                case 2:miesiacStr=" Marzec "; break;
                case 3:miesiacStr=" Kwiecień "; break;
                case 4: miesiacStr=" Maj "; break;
                case 5:miesiacStr=" Czerwiec "; break;
                case 6:miesiacStr=" Lipiec "; break;
                case 7:miesiacStr=" Sierpień "; break;
                case 8:miesiacStr=" Wrzesień "; break;
                case 9:miesiacStr=" Październik "; break;
                case 10:miesiacStr=" Listopad "; break;
                case 11:miesiacStr=" Grudzień "; break;
            }
            data=dzien+miesiacStr+rok;
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


