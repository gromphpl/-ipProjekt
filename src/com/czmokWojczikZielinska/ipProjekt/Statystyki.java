package com.czmokWojczikZielinska.ipProjekt;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Created by Anna on 09.09.14.
 */
public class Statystyki extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statystyki);

        MySQLiteHelper db=new MySQLiteHelper(this.getApplicationContext());
        List<Bieg> listaBiegow=db.getAllBieg();

        Button btnStatystykiMsc=(Button)findViewById(R.id.btnStatystykiMsc);
        TextView tvCalkowityDystans=(TextView)findViewById(R.id.tvCalkowityDystans);
        TextView tvSredniaPredkosc=(TextView)findViewById(R.id.tvSredniaPredkosc);

        double calkowityDystans=0;
        double sredniaPredkosc=0;
        int iloscBiegow=0;

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

        for(Bieg b:listaBiegow)
        {
            iloscBiegow+=1;
            calkowityDystans+=b.przebiegnietyDystans;
            sredniaPredkosc+=b.predkoscBiegu;
        }
        if(calkowityDystans>0 && sredniaPredkosc>0)
        {
        calkowityDystans=calkowityDystans/1000;
        sredniaPredkosc=sredniaPredkosc/iloscBiegow;
        }
        tvCalkowityDystans.setText(String.valueOf(calkowityDystans));
        tvSredniaPredkosc.setText(String.valueOf(sredniaPredkosc));

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

        XYSeries dystansMsc=new XYSeries("Całkowity dystans (km)");
        XYSeries predkoscMsc=new XYSeries("Średnia prędkość (m/s)");
        double dystans=0;
        double predkosc=0;
        int ile=0;
        for(Bieg b : styczen)
        {
          ile+=1;
          dystans+=b.getPrzebiegnietyDystans();
          predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(1,dystans/1000);
        predkoscMsc.add(1,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : luty)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(2,dystans/1000);
        predkoscMsc.add(2,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : marzec)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(3,dystans/1000);
        predkoscMsc.add(3,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : kwiecien)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(4,dystans/1000);
        predkoscMsc.add(4,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : maj)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(5,dystans/1000);
        predkoscMsc.add(5,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : czerwiec)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(6,dystans/1000);
        predkoscMsc.add(6,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : lipiec)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(7,dystans/1000);
        predkoscMsc.add(7,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : sierpien)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(8,dystans/1000);
        predkoscMsc.add(8,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : wrzesien)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(9,dystans/1000);
        predkoscMsc.add(9,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : pazdziernik)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(10,dystans/1000);
        predkoscMsc.add(10,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : listopad)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(11,dystans/1000);
        predkoscMsc.add(11,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;
        for(Bieg b : grudzien)
        {
            ile+=1;
            dystans+=b.getPrzebiegnietyDystans();
            predkosc+=b.getPredkoscBiegu();
        }
        if(dystans>0&&predkosc>0){
        dystansMsc.add(12,dystans/1000);
        predkoscMsc.add(12,predkosc/ile);}
        dystans=0;predkosc=0;ile=0;


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        //czas biegu
        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setColor(Color.RED);
        mRenderer.addSeriesRenderer(renderer1);
        //predkosc biegu
        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer2.setColor(Color.GREEN);
        mRenderer.addSeriesRenderer(renderer2);
        mRenderer.setLegendHeight(3);
        mRenderer.setMargins(new int[]{10, 35, 40, 5});
        mRenderer.setYAxisMin(0);
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        mRenderer.setPanEnabled(true, true);
        mRenderer.setShowGrid(true); // we show the grid
        mRenderer.setLegendTextSize(30);
        mRenderer.setZoomEnabled(true, true);
        mRenderer.setLabelsTextSize(25);
        mRenderer.setFitLegend(true);
        mRenderer.setXAxisMin(0);
        mRenderer.setXAxisMax(12);
        mRenderer.setBarSpacing(5);
        mRenderer.setBarWidth(10.0f);
        mRenderer.addXTextLabel(1,"Styczeń");
        mRenderer.addXTextLabel(2,"Luty");
        mRenderer.addXTextLabel(3,"Marzec");
        mRenderer.addXTextLabel(4,"Kwiecień");
        mRenderer.addXTextLabel(5,"Maj");
        mRenderer.addXTextLabel(6,"Czerwiec");
        mRenderer.addXTextLabel(7,"Lipiec");
        mRenderer.addXTextLabel(8,"Sierpień");
        mRenderer.addXTextLabel(9,"Wrzesień");
        mRenderer.addXTextLabel(10,"Październik");
        mRenderer.addXTextLabel(11,"Listopad");
        mRenderer.addXTextLabel(12,"Grudzień");


        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        dataset.addSeries(0,dystansMsc);
        dataset.addSeries(1,predkoscMsc);
        GraphicalView chartView = ChartFactory.getBarChartView(getApplicationContext(), dataset, mRenderer, BarChart.Type.DEFAULT);
        LinearLayout chart=(LinearLayout) findViewById(R.id.lytChart);
        chart.addView(chartView,0);

        btnStatystykiMsc.setOnClickListener
                (new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent myIntent = new Intent(view.getContext(), StatystykiMsc.class);
                        startActivity(myIntent);
                    }

                }
                );
    }
}
