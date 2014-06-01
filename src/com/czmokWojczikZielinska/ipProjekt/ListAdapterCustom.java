package com.czmokWojczikZielinska.ipProjekt;

import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import static android.widget.AdapterView.OnItemClickListener;

/**
 * Created by Anna on 03.05.14.
 */
public class ListAdapterCustom extends BaseAdapter
{
    private List<TabelaDataCzas> items;
    Context mContext;
    LayoutInflater layoutInflater;
    TabelaDataCzas wybranaPozycja;
    boolean alarm;

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

    public void remove(int position){
        items.remove(position);
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
        TextView dzienTyg=(TextView)arg1.findViewById(R.id.tvDzienTyg);
        CheckBox odbyty=(CheckBox)arg1.findViewById(R.id.cbCzyOdbyty);
        odbyty.setFocusable(false);
        odbyty.setClickable(false);
        TabelaDataCzas dt = items.get(arg0);

        String dataTemp=FormatujWyswietlanieDATA(dt.getData());
        String czasTemp=FormatujWyswietlanieCZAS(dt.getCzas());
        String dataDzienTyg=WyswietlDzienTygodnia(dt);
        data.setText(dataTemp);
        czas.setText(czasTemp);
        dzienTyg.setText(dataDzienTyg);
        odbyty.setChecked(dt.getCzyBiegOdbyty());


        return arg1;
    }

    String WyswietlDzienTygodnia(TabelaDataCzas dt)
    {
        AlarmModel am=new AlarmModel(dt);
        am.setKalendarz();
        Calendar kal=am.getCalendarDate();
        int dzienTyg=kal.get(Calendar.DAY_OF_WEEK);
        String dzienTygStr="";
        switch(dzienTyg)
        {
            case 1:dzienTygStr="Niedziela"; break;
            case 2:dzienTygStr="Poniedziałek"; break;
            case 3:dzienTygStr="Wtorek"; break;
            case 4:dzienTygStr="Środa"; break;
            case 5:dzienTygStr="Czwartek"; break;
            case 6:dzienTygStr="Piątek"; break;
            case 7:dzienTygStr="Sobota"; break;
        }
        return dzienTygStr;
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
