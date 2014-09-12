package com.czmokWojczikZielinska.ipProjekt;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Anna on 11.09.14.
 */
public class StatystykiMsc extends Activity implements AdapterView.OnItemSelectedListener
{
    List<Bieg> styczen = new ArrayList<Bieg>();
    List<Bieg> luty=new ArrayList<Bieg>();
    List<Bieg> marzec=new ArrayList<Bieg>();
    List<Bieg> kwiecien=new ArrayList<Bieg>();
    List<Bieg> maj=new ArrayList<Bieg>();
    List<Bieg> czerwiec=new ArrayList<Bieg>();
    List<Bieg> lipiec=new ArrayList<Bieg>();
    List<Bieg> sierpien=new ArrayList<Bieg>();
    List<Bieg> wrzesien=new ArrayList<Bieg>();
    List<Bieg> pazdziernik=new ArrayList<Bieg>();
    List<Bieg> listopad=new ArrayList<Bieg>();
    List<Bieg> grudzien=new ArrayList<Bieg>();
    int waga=0;
    int calkowitaIloscSpalonychKalorii=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statystyki_msc);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tablica_miesiecy, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        MySQLiteHelper db=new MySQLiteHelper(this.getApplicationContext());
        List<Bieg> listaBiegow=db.getAllBieg();
        DaneOsoby daneOsoby=db.showOneDaneOsoby(1);
        waga=daneOsoby.getWaga();
        for(Bieg b:listaBiegow)
        {
            String data=b.dataBiegu;
            StringTokenizer StringData = new StringTokenizer(data, ".");
            String msc=StringData.nextToken();
            int miesiac=0;
            try
            {
                miesiac=Integer.parseInt(StringData.nextToken());
            }
            catch (Exception ex){miesiac=12;}
            switch(miesiac)
            {
                case 0: styczen.add(b); break;
                case 1: luty.add(b); break;
                case 2: marzec.add(b); break;
                case 3: kwiecien.add(b); break;
                case 4: maj.add(b); break;
                case 5: czerwiec.add(b); break;
                case 6: lipiec.add(b); break;
                case 7: sierpien.add(b); break;
                case 8: wrzesien.add(b); break;
                case 9: pazdziernik.add(b); break;
                case 10: listopad.add(b); break;
                case 11: grudzien.add(b); break;
                default:break;
            }
        }
    }

    int aktualnyMiesiac=0;
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        aktualnyMiesiac=parent.getSelectedItemPosition();
        XYSeries series1=new XYSeries("Spalone kalorie (kcal)");
        int i=0;
        double ACSM=0;
        double spaloneKalorie=0;
        TextView tvKalorie=(TextView)findViewById(R.id.tvLiczbaKalorii);
        switch(aktualnyMiesiac)
        {
            case 0:
                for(Bieg b : styczen)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 1:
                for(Bieg b : luty)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 2:
                for(Bieg b : marzec)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 3:
                for(Bieg b : kwiecien)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 4:
                for(Bieg b : maj)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 5:
                for(Bieg b : czerwiec)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 6:
                for(Bieg b : lipiec)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 7:
                for(Bieg b : sierpien)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 8:
                for(Bieg b : wrzesien)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 9:
                for(Bieg b : pazdziernik)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 10:
                for(Bieg b : listopad)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            case 11:
                for(Bieg b : grudzien)
                {
                    ACSM=3.5+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.2)+((b.getPrzebiegnietyDystans()/(b.getCzasPrzebiegniecia()/60))*0.02*0.9);
                    ACSM=ACSM/3.5;
                    spaloneKalorie=ACSM*waga/(b.getCzasPrzebiegniecia()/60);
                    series1.add(i,spaloneKalorie);
                    i++;
                    calkowitaIloscSpalonychKalorii+=spaloneKalorie;
                    ACSM=0;
                    spaloneKalorie=0;
                }
                i=0;
                tvKalorie.setText(String.valueOf(calkowitaIloscSpalonychKalorii));
                calkowitaIloscSpalonychKalorii=0;
                break;
            default:break;
        }
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        //kalorie
        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setLineWidth(4);
        renderer1.setColor(Color.BLUE);
        renderer1.setDisplayBoundingPoints(true);
        renderer1.setPointStyle(PointStyle.CIRCLE);
        renderer1.setPointStrokeWidth(12);
        mRenderer.addSeriesRenderer(renderer1);

        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        mRenderer.setPanEnabled(true, true);
        mRenderer.setZoomEnabled(true, true);
        mRenderer.setShowGrid(true); // we show the grid
        mRenderer.setLegendTextSize(30);
        mRenderer.setChartTitle("Spalone kalorie dla kolejnych biegów w wybranym miesiącu");
        mRenderer.setAxisTitleTextSize(25.0f);
        mRenderer.setChartTitleTextSize(30);
        mRenderer.setXTitle("Kolejne biegi");
        mRenderer.setLabelsTextSize(25.0f);
        mRenderer.setYTitle("kcal");
        mRenderer.setMargins(new int[]{5, 35, 20, 5});
        mRenderer.setFitLegend(true);
        mRenderer.setXAxisMin(0);
        mRenderer.setYAxisMin(0);
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        dataset.addSeries(series1);
        GraphicalView chartView = ChartFactory.getLineChartView(getApplicationContext(), dataset, mRenderer);
        LinearLayout chart=(LinearLayout) findViewById(R.id.chart);
        chart.addView(chartView,0);


    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        aktualnyMiesiac=0;
    }
}
