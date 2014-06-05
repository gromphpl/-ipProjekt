package com.czmokWojczikZielinska.ipProjekt;

/**
 * Created with IntelliJ IDEA.
 * User: magda
 * Date: 09.04.14
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

public class GPS extends Activity {

    private TextView tvProvider;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvInformations;
    private TextView v;
    private TextView s;
    private TextView t;
    private TextView end2;
    private Button koniec;
    double j=1.0;
    boolean dziala=true;
    double predkosc=0;
    double wynik=0;
    dystans d=new dystans();
    //droga pobierana od użytkownika-dystans do przebiegnięcia.
    int droga=d.dalej;
    double czas=0;
    String czasBiegu="";
    String dataBiegu="";
    double przebiegnietyDystans=0;
    long tStart=0;
    long tEnd=0;
    Chronometer timer;



    private LocationManager locationManager;
    private Location savedLocation = null;
    private LocationListener locationListener = new LocationListener()
    {
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}

        public void onLocationChanged(Location location)
        {
            if(j==1.0)
            {
                tStart=System.currentTimeMillis();
            }
            showLocation(location);
            CzyLokalizacjaWlaczona();
            showAdditionalInfo(location);
            if (savedLocation == null)
                savedLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps2);

        tvProvider = (TextView) findViewById(R.id.tvProvider);
        koniec=(Button)findViewById(R.id.button1);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        v = (TextView) findViewById(R.id.textView1);
        s = (TextView) findViewById(R.id.textView);
        t= (TextView) findViewById(R.id.textView2);
        end2 = (TextView) findViewById(R.id.textView2);
        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvInformations = (TextView) findViewById(R.id.tvInformations);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            tvProvider.setText("GPS włączony.");
        Calendar kal=Calendar.getInstance(Locale.getDefault());
        int dzien=kal.get(Calendar.DATE);
        int miesiac=kal.get(Calendar.MONTH);
        int rok=kal.get(Calendar.YEAR);
        int godzina=kal.get(Calendar.HOUR_OF_DAY);
        int minuta=kal.get(Calendar.MINUTE);

        dataBiegu=dzien+"."+miesiac+"."+rok;
        czasBiegu=godzina+":"+minuta;
        }
        else
        {
            tvProvider.setText("Gps nie jest włączony, proszę włączyć.");
            Ustawienia();
            CzyLokalizacjaWlaczona();
        }


        koniec.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                tEnd=System.currentTimeMillis();
                droga=(int)przebiegnietyDystans;
                czas=(tEnd-tStart)/1000;
                //predkosc=wynik/(j-1);
                predkosc=droga/czas;
                predkosc=(int)predkosc*100;
                predkosc=predkosc/100;
                // String czas2=Double.toString(czas);
                // s.setText("Średnia prędkość: "+predkosc);
                s.setText("Dystans: "+droga+" m "+" Średnia prędkość: "+predkosc+ " m/s");
                t.setText("Czas: "+ czas + " s ");
                //dodanie do bazy danych wartosci po przycisnieciu przycisku koniec
                dodajDoBazy(czas, droga, predkosc);
                //zatrzymaj dzialanie gps
                onStop();
                savedLocation=null;
//                Intent myIntent = new Intent(GPS.this, OdbyteBiegi.class);
//                finish();
//                startActivityForResult(myIntent, 0);

            }
        });
    }

    private void CzyLokalizacjaWlaczona()
    {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            tvProvider.setText("GPS włączony.");
            if(j==1)
            {
                Calendar kal=Calendar.getInstance(Locale.getDefault());
                int dzien=kal.get(Calendar.DATE);
                int miesiac=kal.get(Calendar.MONTH);
                int rok=kal.get(Calendar.YEAR);
                int godzina=kal.get(Calendar.HOUR_OF_DAY);
                int minuta=kal.get(Calendar.MINUTE);

                dataBiegu=dzien+"."+miesiac+"."+rok;
                czasBiegu=godzina+":"+minuta;

            }
        }
    }
    public void Ustawienia()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Ustawienia GPS");

        alertDialog.setMessage("GPS jest wyłączony. Czy chcesz przejść do panelu ustawień?");

        alertDialog.setPositiveButton("Ustawienia", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent,0);
            }
        });

        alertDialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000, 1, locationListener);

    }

    @Override
    protected void onStop() {
        locationManager.removeUpdates(locationListener);
        super.onStop();
    }

    private void showLocation(Location location) {
        String latitude = getString(R.string.width);
        String longitude = getString(R.string.lenght);
        if (location != null) {
            latitude += location.getLatitude();
            longitude += location.getLongitude();
            tvLatitude.setText(latitude);
            tvLongitude.setText(longitude);
        }
    }
    public void dodajDoBazy(double t,int d, double p)
    {
        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notif);
        r.play();
        Vibrator v2 = (Vibrator) getApplicationContext().getSystemService(this.getApplicationContext().VIBRATOR_SERVICE);
        v2.vibrate(500); //wibracja przez 500 ms
        MySQLiteHelper db=new MySQLiteHelper(this);
        Bieg b=new Bieg();
        b.setCzasBiegu(czasBiegu);
        b.setDataBiegu(dataBiegu);
        b.setPrzebiegnietyDystans(d);
        b.setPredkoscBiegu(p);
        b.setCzasPrzebiegniecia(t);
        db.dodajRekordBieg(b);
    }

    private void showAdditionalInfo(Location location)
    {
        String infos = "Dystans: ";

        if (savedLocation == null || location == null)
        {
            infos += "can't calculate";
        }
        else
        {
            infos += savedLocation.distanceTo(location) + "m\n";
            if(j==1)
            {
                przebiegnietyDystans=0;
            }
            else
            {
            przebiegnietyDystans+=Math.abs(savedLocation.distanceTo(location)-przebiegnietyDystans);

            }
            //przebiegnietyDystans=przebiegnietyDystans+(przebiegnietyDystans/j);
           /* if(savedLocation.distanceTo(location)> droga+(droga*0.20));
            {
                predkosc=wynik/(j-1);
                v.setText("Średnia prędkość: "+predkosc);
                s.setText("Dystans: "+droga);
                t.setText("Czas: "+(droga/predkosc));


            }
            */
            //infos += "Dokładność ";
            //infos += location.getAccuracy() + "m \n";
            infos += "Last fix: ";
            infos += new Date(location.getTime()) + "\n";
            infos += "Prędkość:  ";
            infos += location.getSpeed() + "m/s";


            j++;
            wynik=wynik+location.getSpeed();
            //double wynik2;
            //wynik2= wynik/(j-1);
            double odleglosc=droga+(droga*0.1);
            if(przebiegnietyDystans>=odleglosc)
            {
            //end.setText("wynik: "+ wynik+" licznik: "+j);

                tEnd=System.currentTimeMillis();
                czas=(tEnd-tStart)/1000;
                predkosc=droga/czas;
                predkosc=(int)predkosc*100;
                predkosc=predkosc/100;
            // String czas2=Double.toString(czas);
            // s.setText("Średnia prędkość: "+predkosc);
            s.setText("Dystans: "+droga+" m "+" Średnia prędkość: "+predkosc+ " m/s");
            t.setText("Czas: "+ czas + " s ");
            //dodanie do bazy danych wartosci po przycisnieciu przycisku koniec
            dodajDoBazy(czas, droga, predkosc);
            onStop();
                savedLocation=null;
//            Intent myIntent = new Intent(GPS.this, OdbyteBiegi.class);
//            finish();
//            startActivityForResult(myIntent, 0);
            }


        }
        tvInformations.setText(infos);


    }

}
